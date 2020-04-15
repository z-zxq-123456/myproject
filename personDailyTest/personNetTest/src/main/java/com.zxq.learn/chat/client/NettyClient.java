package com.zxq.learn.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-15 21:28
 **/
public class NettyClient {


    private final  int port;
    private final  String host;

    public NettyClient( String host,int port) {
        this.port = port;
        this.host = host;
    }

    public void run(){

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                        pipeline.addLast("decoder",new StringDecoder());
                        pipeline.addLast("encoder",new StringEncoder());
                        pipeline.addLast("handler",new ClientHandle());
                    }
                });
        try {
            Channel channel = bootstrap.connect(host,port).sync().channel();
            BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                channel.writeAndFlush(in.readLine()+"\r\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyClient("localhost",8080).run();
    }
}
