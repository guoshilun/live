package com.jk.jkproject.net.im;

import com.blankj.utilcode.util.LogUtils;

import org.jboss.netty.channel.Channel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class RequestPacket {
  public static final byte SIZE = 6;
  
  public Channel channel;
  
  private short commandId;
  
  private byte[] contentBytes;
  
  public byte[] data;
  
  public int length;
  
  public String method;
  
  public String method_len;
  
  private boolean needMonitor = false;
  
  public short serializeType = (short)0;
  
  private short squenceNo;
  
  public short type;
  
  private long uuid;
  
  public RequestPacket(String paramString, byte[] paramArrayOfbyte) {
    LogUtils.e("paramString=method_len=",paramString+"===="+paramArrayOfbyte);
    this.method_len = paramString;
    this.contentBytes = paramArrayOfbyte;
  }
  
  public RequestPacket(short paramShort) {
    this.type = (short)paramShort;
  }
  
  public RequestPacket(short paramShort1, String paramString, short paramShort2, byte[] paramArrayOfbyte) {
    LogUtils.e("paramString=",paramString);
    this.type = (short)paramShort1;
    this.method = paramString;
    this.serializeType = (short)paramShort2;
    this.contentBytes = paramArrayOfbyte;
  }
  
  public static short bytesToShort(byte[] paramArrayOfbyte) {
    return ByteBuffer.wrap(paramArrayOfbyte).order(ByteOrder.LITTLE_ENDIAN).getShort();
  }
  
  public static short getCommandId(byte[] paramArrayOfbyte) {
    return ByteUtils.getShort(ByteUtils.subBytes(paramArrayOfbyte, 4, 2));
  }
  
  public static int getTotalSize(byte[] paramArrayOfbyte) {
    return ByteUtils.byteArrayToInt(ByteUtils.subBytes(paramArrayOfbyte, 0, 4)) + 4;
  }
  
  public static long getUUIDId(byte[] paramArrayOfbyte) {
    return ByteUtils.byteArrayToLong(ByteUtils.subBytes(paramArrayOfbyte, 6, 8));
  }
  
  public short getCommandId() {
    return this.commandId;
  }
  
  public byte[] getContentBytes() {
    return this.contentBytes;
  }
  
  public String getMethod() {
    return this.method_len;
  }
  
  public String getMethodName() {
    return this.method_len;
  }
  
  public short getSquenceNo() {
    return this.squenceNo;
  }
  
  public long getUuid() {
    return this.uuid;
  }
  
  public boolean isNeedMonitor() {
    return this.needMonitor;
  }
  
  public byte[] produceByteArray() {
    byte[] arrayOfByte1 = ByteUtils.shortToByteArray(this.type);
    ArrayList<byte[]> arrayList = new ArrayList();
    arrayList.add(arrayOfByte1);
    arrayOfByte1 = ByteUtils.shortToByteArray(this.serializeType);
    byte[] arrayOfByte2 = this.method.getBytes();
    byte[] arrayOfByte3 = ByteUtils.shortToByteArray((short)arrayOfByte2.length);
    arrayList.add(ByteUtils.intToByteArray(arrayOfByte2.length + 4 + this.contentBytes.length));
    arrayList.add(arrayOfByte1);
    arrayList.add(arrayOfByte3);
    arrayList.add(arrayOfByte2);
    arrayList.add(this.contentBytes);
    return ByteUtils.mergeBytes((List<byte[]>)arrayList);
  }
  
  public void setCommandId(short paramShort) {
    this.commandId = (short)paramShort;
  }
  
  public void setContentBytes(byte[] paramArrayOfbyte) {
    this.contentBytes = paramArrayOfbyte;
  }
  
  public void setNeedMonitor(boolean paramBoolean) {
    this.needMonitor = paramBoolean;
  }
  
  public void setSquenceNo(short paramShort) {
    this.squenceNo = (short)paramShort;
  }
  
  public void setUuid(long paramLong) {
    this.uuid = paramLong;
  }
}
