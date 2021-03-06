// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushGame.proto

package com.jk.jkproject.net.im.info;

public final class PushGame {
  private PushGame() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PushGameMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.PushGameMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *正面压注总数量
     * </pre>
     *
     * <code>optional int32 front = 2;</code>
     */
    int getFront();

    /**
     * <pre>
     *反面压注总数量
     * </pre>
     *
     * <code>optional int32 contrary = 3;</code>
     */
    int getContrary();

    /**
     * <pre>
     *最大竞猜数量
     * </pre>
     *
     * <code>optional int32 maxBet = 4;</code>
     */
    int getMaxBet();

    /**
     * <pre>
     * 当前倒计时时间
     * </pre>
     *
     * <code>optional int32 time = 8;</code>
     */
    int getTime();
  }
  /**
   * Protobuf type {@code proto.PushGameMessage}
   */
  public  static final class PushGameMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:proto.PushGameMessage)
      PushGameMessageOrBuilder {
    // Use PushGameMessage.newBuilder() to construct.
    private PushGameMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PushGameMessage() {
      front_ = 0;
      contrary_ = 0;
      maxBet_ = 0;
      time_ = 0;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private PushGameMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 16: {

              front_ = input.readInt32();
              break;
            }
            case 24: {

              contrary_ = input.readInt32();
              break;
            }
            case 32: {

              maxBet_ = input.readInt32();
              break;
            }
            case 64: {

              time_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return PushGame.internal_static_proto_PushGameMessage_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PushGame.internal_static_proto_PushGameMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              PushGame.PushGameMessage.class, PushGame.PushGameMessage.Builder.class);
    }

    public static final int FRONT_FIELD_NUMBER = 2;
    private int front_;
    /**
     * <pre>
     *正面压注总数量
     * </pre>
     *
     * <code>optional int32 front = 2;</code>
     */
    public int getFront() {
      return front_;
    }

    public static final int CONTRARY_FIELD_NUMBER = 3;
    private int contrary_;
    /**
     * <pre>
     *反面压注总数量
     * </pre>
     *
     * <code>optional int32 contrary = 3;</code>
     */
    public int getContrary() {
      return contrary_;
    }

    public static final int MAXBET_FIELD_NUMBER = 4;
    private int maxBet_;
    /**
     * <pre>
     *最大竞猜数量
     * </pre>
     *
     * <code>optional int32 maxBet = 4;</code>
     */
    public int getMaxBet() {
      return maxBet_;
    }

    public static final int TIME_FIELD_NUMBER = 8;
    private int time_;
    /**
     * <pre>
     * 当前倒计时时间
     * </pre>
     *
     * <code>optional int32 time = 8;</code>
     */
    public int getTime() {
      return time_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (front_ != 0) {
        output.writeInt32(2, front_);
      }
      if (contrary_ != 0) {
        output.writeInt32(3, contrary_);
      }
      if (maxBet_ != 0) {
        output.writeInt32(4, maxBet_);
      }
      if (time_ != 0) {
        output.writeInt32(8, time_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (front_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, front_);
      }
      if (contrary_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, contrary_);
      }
      if (maxBet_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, maxBet_);
      }
      if (time_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(8, time_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof PushGame.PushGameMessage)) {
        return super.equals(obj);
      }
      PushGame.PushGameMessage other = (PushGame.PushGameMessage) obj;

      boolean result = true;
      result = result && (getFront()
          == other.getFront());
      result = result && (getContrary()
          == other.getContrary());
      result = result && (getMaxBet()
          == other.getMaxBet());
      result = result && (getTime()
          == other.getTime());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + FRONT_FIELD_NUMBER;
      hash = (53 * hash) + getFront();
      hash = (37 * hash) + CONTRARY_FIELD_NUMBER;
      hash = (53 * hash) + getContrary();
      hash = (37 * hash) + MAXBET_FIELD_NUMBER;
      hash = (53 * hash) + getMaxBet();
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + getTime();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static PushGame.PushGameMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushGame.PushGameMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushGame.PushGameMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushGame.PushGameMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushGame.PushGameMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushGame.PushGameMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushGame.PushGameMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static PushGame.PushGameMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushGame.PushGameMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushGame.PushGameMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(PushGame.PushGameMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code proto.PushGameMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.PushGameMessage)
        PushGame.PushGameMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return PushGame.internal_static_proto_PushGameMessage_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return PushGame.internal_static_proto_PushGameMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                PushGame.PushGameMessage.class, PushGame.PushGameMessage.Builder.class);
      }

      // Construct using PushGame.PushGameMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        front_ = 0;

        contrary_ = 0;

        maxBet_ = 0;

        time_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return PushGame.internal_static_proto_PushGameMessage_descriptor;
      }

      public PushGame.PushGameMessage getDefaultInstanceForType() {
        return PushGame.PushGameMessage.getDefaultInstance();
      }

      public PushGame.PushGameMessage build() {
        PushGame.PushGameMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public PushGame.PushGameMessage buildPartial() {
        PushGame.PushGameMessage result = new PushGame.PushGameMessage(this);
        result.front_ = front_;
        result.contrary_ = contrary_;
        result.maxBet_ = maxBet_;
        result.time_ = time_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof PushGame.PushGameMessage) {
          return mergeFrom((PushGame.PushGameMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(PushGame.PushGameMessage other) {
        if (other == PushGame.PushGameMessage.getDefaultInstance()) return this;
        if (other.getFront() != 0) {
          setFront(other.getFront());
        }
        if (other.getContrary() != 0) {
          setContrary(other.getContrary());
        }
        if (other.getMaxBet() != 0) {
          setMaxBet(other.getMaxBet());
        }
        if (other.getTime() != 0) {
          setTime(other.getTime());
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        PushGame.PushGameMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (PushGame.PushGameMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int front_ ;
      /**
       * <pre>
       *正面压注总数量
       * </pre>
       *
       * <code>optional int32 front = 2;</code>
       */
      public int getFront() {
        return front_;
      }
      /**
       * <pre>
       *正面压注总数量
       * </pre>
       *
       * <code>optional int32 front = 2;</code>
       */
      public Builder setFront(int value) {
        
        front_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *正面压注总数量
       * </pre>
       *
       * <code>optional int32 front = 2;</code>
       */
      public Builder clearFront() {
        
        front_ = 0;
        onChanged();
        return this;
      }

      private int contrary_ ;
      /**
       * <pre>
       *反面压注总数量
       * </pre>
       *
       * <code>optional int32 contrary = 3;</code>
       */
      public int getContrary() {
        return contrary_;
      }
      /**
       * <pre>
       *反面压注总数量
       * </pre>
       *
       * <code>optional int32 contrary = 3;</code>
       */
      public Builder setContrary(int value) {
        
        contrary_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *反面压注总数量
       * </pre>
       *
       * <code>optional int32 contrary = 3;</code>
       */
      public Builder clearContrary() {
        
        contrary_ = 0;
        onChanged();
        return this;
      }

      private int maxBet_ ;
      /**
       * <pre>
       *最大竞猜数量
       * </pre>
       *
       * <code>optional int32 maxBet = 4;</code>
       */
      public int getMaxBet() {
        return maxBet_;
      }
      /**
       * <pre>
       *最大竞猜数量
       * </pre>
       *
       * <code>optional int32 maxBet = 4;</code>
       */
      public Builder setMaxBet(int value) {
        
        maxBet_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *最大竞猜数量
       * </pre>
       *
       * <code>optional int32 maxBet = 4;</code>
       */
      public Builder clearMaxBet() {
        
        maxBet_ = 0;
        onChanged();
        return this;
      }

      private int time_ ;
      /**
       * <pre>
       * 当前倒计时时间
       * </pre>
       *
       * <code>optional int32 time = 8;</code>
       */
      public int getTime() {
        return time_;
      }
      /**
       * <pre>
       * 当前倒计时时间
       * </pre>
       *
       * <code>optional int32 time = 8;</code>
       */
      public Builder setTime(int value) {
        
        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 当前倒计时时间
       * </pre>
       *
       * <code>optional int32 time = 8;</code>
       */
      public Builder clearTime() {
        
        time_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:proto.PushGameMessage)
    }

    // @@protoc_insertion_point(class_scope:proto.PushGameMessage)
    private static final PushGame.PushGameMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new PushGame.PushGameMessage();
    }

    public static PushGame.PushGameMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PushGameMessage>
        PARSER = new com.google.protobuf.AbstractParser<PushGameMessage>() {
      public PushGameMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new PushGameMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PushGameMessage> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<PushGameMessage> getParserForType() {
      return PARSER;
    }

    public PushGame.PushGameMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_PushGameMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_PushGameMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\016PushGame.proto\022\005proto\032\014Entity.proto\"P\n" +
      "\017PushGameMessage\022\r\n\005front\030\002 \001(\005\022\020\n\010contr" +
      "ary\030\003 \001(\005\022\016\n\006maxBet\030\004 \001(\005\022\014\n\004time\030\010 \001(\005B" +
      " \n\036com.sencorsta.ids.common.protob\006proto" +
      "3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          Entity.getDescriptor(),
        }, assigner);
    internal_static_proto_PushGameMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_PushGameMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_PushGameMessage_descriptor,
        new String[] { "Front", "Contrary", "MaxBet", "Time", });
    Entity.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
