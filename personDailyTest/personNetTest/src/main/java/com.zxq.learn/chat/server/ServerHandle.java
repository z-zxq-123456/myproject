package com.zxq.learn.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-15 21:02
 **/
@ChannelHandler.Sharable
public class ServerHandle extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel ch = ctx.channel();
        for (Channel channel:channels){
            if (channel!= ch){
                channel.writeAndFlush(ch.remoteAddress()+ " : "+ msg+" \n");
            }else {
                channel.writeAndFlush("U : "+msg + "\n ");
            }
        }
    }

    //ChannelHandlerContext：代表了ChannelHandler和ChannelPipeline之间的关联
    //handlerAdded在ChannelHandler添加到ChannelPipeline中时被调用
    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for (Channel channel1:channels){
            channel1.writeAndFlush("System :" +channel.remoteAddress()+" comes \n");
        }
        channels.add(channel);
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelActive()} to forward
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        System.out.println("System : " + channel.remoteAddress() + " in");
    }

    /**
     * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        Channel channel = ctx.channel();
        System.out.println("System : " + channel.remoteAddress()+ " with some bugs \n");
        cause.printStackTrace();
        ctx.close();
    }
}
