package com.zxq.learn.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-15 21:01
 **/
public class NettyServer {

    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }
    public void start() throws InterruptedException {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(mainGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                        pipeline.addLast("decoder",new StringDecoder());
                        pipeline.addLast("encoder",new StringEncoder());
                        pipeline.addLast("handler",new ServerHandle());
                    }
                })
                .option(ChannelOption.SO_BACKLOG,128)//tcp协议设置参数
                .childOption(ChannelOption.SO_KEEPALIVE,true);
        System.out.println("netty server is inited");

        try {
            ChannelFuture future = bootstrap.bind(port).sync();//异步地绑定服务器，调用sync()阻塞，直到绑定完成
            future.channel().closeFuture().sync();////阻塞当前线程，直到完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mainGroup.shutdownGracefully().sync();
            workGroup.shutdownGracefully().sync();
            System.out.println("server closed!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyServer(8080).start();
    }
}
