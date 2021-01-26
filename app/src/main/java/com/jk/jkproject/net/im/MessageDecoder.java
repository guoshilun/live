package com.jk.jkproject.net.im;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;


public class MessageDecoder extends FrameDecoder {

    private String TAG = "MessageDecoder";
    // 用来临时保留没有处理过的请求报文
    ChannelBuffer tempMsg = ChannelBuffers.dynamicBuffer();
    /**
     * {[消息类型 short 2][包体大小 int 4]}
     * {[序列化类型 short 2][路径字符串长度short 2][路径 string X][数据 byte数组 Y]}
     * <p>
     * {[2][4]}     {[2][2][X][Y]}
     * 头    包体
     *
     * @param channelHandlerContext
     * @param channel
     * @param channelBuffer
     * @return
     * @throws Exception
     */
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        // 合并报文
        ChannelBuffer message = null;
        int tmpMsgSize = tempMsg.readableBytes();

        // 如果暂存有上一次余下的请求报文，则合并
//        LogUtils.e(TAG, "包的长度" + channelBuffer.readableBytes());
        if (tmpMsgSize > 0) {
            message = ChannelBuffers.dynamicBuffer();
            message.writeBytes(tempMsg);
            message.writeBytes(channelBuffer);
//            LogUtils.e(TAG,"合并：上一数据包余下的长度为：" + tmpMsgSize + ",合并后长度为:" + message.readableBytes());
        } else {
            message = channelBuffer;
        }
        while (true) {
            channelBuffer.markReaderIndex();
            if (message.readableBytes() < 6) {
                channelBuffer.resetReaderIndex();
                break;
            }
            short type = message.readShort();
            int i = message.readInt();
            if (message.readableBytes() < i) {
                channelBuffer.resetReaderIndex();
                break;
            }
            byte[] arrayOfByte1 = new byte[1], arrayOfByte2 = new byte[1];
            if (i > 0) {
                short i1 = message.readShort();
                int length = message.readShort();
                arrayOfByte1 = new byte[length];
                message.readBytes(arrayOfByte1);
                i = i - 2 - 2 - arrayOfByte1.length;
                arrayOfByte2 = new byte[i];
                message.readBytes(arrayOfByte2);
            }
            if (type == 5) {
                return ProtocolUtil.decode("header_method", new byte[1]);
            } else {
                return ProtocolUtil.decode(new String(arrayOfByte1, "utf-8"), arrayOfByte2);
            }
        }
        int size = message.readableBytes();
        if (size != 0) {
//            LogUtils.e(TAG,"剩下来的数据放到tempMsg暂存" + size);
            // 剩下来的数据放到tempMsg暂存
            tempMsg.clear();
            tempMsg.writeBytes(message.readBytes(size));
        }
        return null;
    }
}
