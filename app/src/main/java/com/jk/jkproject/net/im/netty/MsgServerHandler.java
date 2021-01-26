package com.jk.jkproject.net.im.netty;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.net.im.RequestPacket;
import com.jk.jkproject.net.im.manager.IMLoginManager;
import com.jk.jkproject.net.im.manager.IMPacketDispatcher;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;

public class MsgServerHandler extends BaseServerHandler {
  public void channelConnected(ChannelHandlerContext paramChannelHandlerContext, ChannelStateEvent paramChannelStateEvent) throws Exception {
    super.channelConnected(paramChannelHandlerContext, paramChannelStateEvent);
    LogUtils.e("IMLogin", "channelConnected");
    IMLoginManager.instance().onMsgServerConnected();
  }
  
  public void channelDisconnected(ChannelHandlerContext paramChannelHandlerContext, ChannelStateEvent paramChannelStateEvent) throws Exception {
    super.channelDisconnected(paramChannelHandlerContext, paramChannelStateEvent);
    LogUtils.e("IMLogin", "channelDisconnected");
    IMLoginManager.instance().onMsgServerDisconnected();
  }
  
  protected void channelUnconnected() {
    LogUtils.e("IMLogin", "channelUnconnected");
    IMLoginManager.instance().onMsgServerUnconnected();
  }
  
  public void messageReceived(ChannelHandlerContext paramChannelHandlerContext, MessageEvent paramMessageEvent) throws Exception {
    IMPacketDispatcher.dispatch((RequestPacket)paramMessageEvent.getMessage());
    super.messageReceived(paramChannelHandlerContext, paramMessageEvent);
  }
}