package com.jk.jkproject.net.im;

import android.util.Log;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class MessageEncoder extends OneToOneEncoder {
  protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
    if (!(obj instanceof RequestPacket))
      return obj;
    byte[] arrayOfByte = ((RequestPacket)obj).produceByteArray();
    Log.e("===encode=", obj.toString());
    ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
    channelBuffer.writeBytes(arrayOfByte);
    return channelBuffer;
  }
}
