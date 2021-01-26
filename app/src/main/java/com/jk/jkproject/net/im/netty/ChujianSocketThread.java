package com.jk.jkproject.net.im.netty;

import android.os.Handler;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.net.im.MessageDecoder;
import com.jk.jkproject.net.im.MessageEncoder;
import com.jk.jkproject.net.im.RequestPacket;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class ChujianSocketThread extends Thread {

    private ClientBootstrap clientBootstrap = null;
    private ChannelFactory channelFactory = null;
    private ChannelFuture channelFuture = null;
    private Channel channel = null;
    public Handler subHandler = null;
    private String strHost = null;
    private int nPort = 0;

    public ChujianSocketThread(String strHost, int nPort,
                               SimpleChannelUpstreamHandler handler) {
        this.strHost = strHost;
        this.nPort = nPort;
        init(handler);
    }

    @Override
    public void run() {
        doConnect();
    }

    /* netty socket客户端连接初始化参数设置 */
    private void init(final SimpleChannelUpstreamHandler handler) {

        // only one IO thread
        channelFactory = new NioClientSocketChannelFactory(
                Executors.newSingleThreadExecutor(),
                Executors.newSingleThreadExecutor());
        clientBootstrap = new ClientBootstrap(channelFactory);
        clientBootstrap.setOption("connectTimeoutMillis", 6000);
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {

                ChannelPipeline channelPipeline = Channels.pipeline();
                channelPipeline.addLast("encoder", new MessageEncoder());
                channelPipeline.addLast("decoder", new MessageDecoder());
                channelPipeline.addLast("handler", handler);
                return channelPipeline;

            }

        });
        clientBootstrap.setOption("tcpNoDelay", true);
        clientBootstrap.setOption("keepAlive", true);
        // clientBootstrap.setOption("keepIdle", 20);
        // clientBootstrap.setOption("keepInterval", 5);
        // clientBootstrap.setOption("keepCount", 3);
    }

    /* 连接服务器 */
    public boolean doConnect() {
        try {

            if (null == channel && null != this.strHost && this.nPort > 0) {
                channelFuture = clientBootstrap.connect(new InetSocketAddress(
                        strHost, nPort));
                channel = channelFuture.awaitUninterruptibly().getChannel();
                if (!channelFuture.isSuccess()) {
                    channelFuture.getCause().printStackTrace();
                    clientBootstrap.releaseExternalResources();
                    return false;
                }

            }

            // Wait until the connection is closed or the connection attemp
            // fails.
            channelFuture.getChannel().getCloseFuture().awaitUninterruptibly();
            // Shut down thread pools to exit.
            clientBootstrap.releaseExternalResources();
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public Channel getChannel() {
        return channel;

    }

    /* 向服务器端发送对象 */
    public boolean sendPacket(RequestPacket packet) {
        if (null != packet && null != channelFuture
                && null != channelFuture.getChannel()) {
            channelFuture.getChannel().write(packet);
            LogUtils.e("ChujianIMService", "packet#send ok");
            return true;
        } else {
            LogUtils.e("ChujianIMService", "packet#send failed");
            return false;

        }

    }

    /* 关闭socket通道连接 */
    public void close() {
        if (null == channelFuture)
            return;
        if (null != channelFuture.getChannel()) {
            channelFuture.getChannel().close();
        }
        channelFuture.cancel();
    }

}
