package com.zxq.learn.heartbeat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-15 22:42
 **/
public class HearBeanServer {


    private void bind(int port){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());

                            ch.pipeline()
                                    .addLast(new DelimiterBasedFrameDecoder(1024,delimiter))
                                    .addLast(new StringDecoder())
                                    .addLast("readTimeOutHandler",new IdleStateHandler(5,
                                            5, 5, TimeUnit.SECONDS))
                                    .addLast("HeartBeatHandler",new HeartBeatRespHandler());
                        }
                    });
            // 绑定端口，同步等待成功
            b.bind(port).sync();
            System.out.println("Netty Server start ok ....");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void bind2(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast("readTimeOutHandler",new IdleStateHandler(5,
                                    5, 5, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new HeartBeatRespHandler());
                        }
                    });

            // Start the server.
            ChannelFuture f = b.bind(port).sync();
            System.out.println("Netty Server start ok ....");
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {

//        new HearBeanServer().bind(8080);

        try {
            new HearBeanServer().bind2(8080);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
