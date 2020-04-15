package com.zxq.learn.heartbeat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-15 22:29
 **/
public class HeartBeatClient {

    private final int port;
    private final String host;
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public HeartBeatClient(int port, String host) {
        this.port = port;
        this.host = host;
    }


    public void run(){

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_", CharsetUtil.UTF_8);
                            ch.pipeline()
                                    .addLast(new DelimiterBasedFrameDecoder(1024,delimiter))
                                    .addLast(new StringDecoder())
                                    // 当一定周期内(默认5s)没有收到对方任何消息时，需要主动关闭链接
                                    .addLast("readTimeOutHandler",new ReadTimeoutHandler(5))
                                    .addLast("heartBeatHandler",new HeartBeatReqHandler());
                        }
                    });
            // 发起异步连接操作
            ChannelFuture future = b.connect().sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 所有资源释放完之后，清空资源，再次发起重连操作
            executor.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(5);
                    //发起重连操作
                    run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) {
        new HeartBeatClient(8080,"localhost").run();
    }
}
