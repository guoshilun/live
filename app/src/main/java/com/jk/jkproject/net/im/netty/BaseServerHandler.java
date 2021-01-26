package com.jk.jkproject.net.im.netty;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.utils.SPUtils;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public abstract class BaseServerHandler extends SimpleChannelUpstreamHandler {
    private static final String TAG = "BaseServerHandler";
    protected boolean connected = false;

    protected abstract void channelUnconnected();

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception {
        super.channelConnected(ctx, e);
        LogUtils.e(TAG, "channel#channelConnected");
        connected = true;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
            throws Exception {
        super.messageReceived(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx,
                                    ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        LogUtils.e(TAG, "channel#channelDisconnected");
        connected = false;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
            throws Exception {
        super.exceptionCaught(ctx, e);
        LogUtils.e(TAG, "channel#exceptionCaught:%s:" + e.getCause().toString());
        if (SPUtils.getIP().isEmpty()) {
            return;
        }
        if (!connected) {
            channelUnconnected();
        }
    }
}