package com.zxq.learn.heartbeat.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-15 22:43
 **/
public class HeartBeatRespHandler extends SimpleChannelInboundHandler<String> {

    private static final String resp = "I have received successfully$_";

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

        if (msg.equals("start notify with server")){
            ctx.writeAndFlush(Unpooled.copiedBuffer("ok$_".getBytes()));
        }else {
            //返回心跳应答信息
            System.out.println("Receive client heart beat message: ---->"+ msg);
            ctx.writeAndFlush(Unpooled.copiedBuffer(resp.getBytes()));
        }
    }

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
}
