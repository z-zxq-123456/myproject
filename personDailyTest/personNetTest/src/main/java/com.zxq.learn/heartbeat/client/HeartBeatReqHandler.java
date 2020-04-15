package com.zxq.learn.heartbeat.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-15 22:30
 **/
public class HeartBeatReqHandler extends SimpleChannelInboundHandler<String> {


    private volatile ScheduledFuture<?> heartBeat;

    private static final String hello = "start notify with server$_";


    /**
     * Calls {@link ChannelHandlerContext#fireUserEventTriggered(Object)} to forward
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param evt
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            String type = "";
            if (event.state() == IdleState.READER_IDLE) {
                type = "read idle";
            } else if (event.state() == IdleState.WRITER_IDLE) {
                type = "write idle";
            } else if (event.state() == IdleState.ALL_IDLE) {
                type = "all idle";
            }

            ctx.writeAndFlush("HeatBeat timeOut").addListener(
                    ChannelFutureListener.CLOSE_ON_FAILURE);  // 3

            System.out.println( ctx.channel().remoteAddress()+"超时类型：" + type);
        }else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer(hello.getBytes()));
        System.out.println("------------------------");
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param msg
     */
    /**
     * <strong>Please keep in mind that this method will be renamed to
     * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
     * <p>
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if ("ok".equalsIgnoreCase(msg)){
            //服务端返回ok开始心跳
            heartBeat = ctx.executor().scheduleAtFixedRate(()->{
                String heartBeat = "I am ok";
                System.out.println("Client send heart beat message to server: ----->"+heartBeat);
                ctx.writeAndFlush(Unpooled.copiedBuffer((heartBeat+"$_").getBytes()));
                },0,5000, TimeUnit.MILLISECONDS);
        }else {
            System.out.println("Client receive server heart beat message : --->"+msg);
        }
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
        if (heartBeat != null){
            heartBeat.cancel(true);
            heartBeat = null;
        }
        super.exceptionCaught(ctx,cause);
        Channel channel = ctx.channel();
        if (channel.isActive())
            channel.close();
    }
}
