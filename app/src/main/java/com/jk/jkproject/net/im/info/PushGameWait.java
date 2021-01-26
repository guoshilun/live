// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushGameWait.proto

package com.jk.jkproject.net.im.info;

public final class PushGameWait {
  private PushGameWait() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PushGameWaitMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.PushGameWaitMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *倒计时（秒）
     * </pre>
     *
     * <code>optional int32 time = 1;</code>
     */
    int getTime();

    /**
     * <pre>
     *&#47;/ 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
     * </pre>
     *
     * <code>optional string stage = 2;</code>
     */
    String getStage();
    /**
     * <pre>
     *&#47;/ 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
     * </pre>
     *
     * <code>optional string stage = 2;</code>
     */
    com.google.protobuf.ByteString
        getStageBytes();
  }
  /**
   * Protobuf type {@code proto.PushGameWaitMessage}
   */
  public  static final class PushGameWaitMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:proto.PushGameWaitMessage)
      PushGameWaitMessageOrBuilder {
    // Use PushGameWaitMessage.newBuilder() to construct.
    private PushGameWaitMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PushGameWaitMessage() {
      time_ = 0;
      stage_ = "";
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private PushGameWaitMessage(
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
            case 8: {

              time_ = input.readInt32();
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              stage_ = s;
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
      return PushGameWait.internal_static_proto_PushGameWaitMessage_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PushGameWait.internal_static_proto_PushGameWaitMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              PushGameWait.PushGameWaitMessage.class, PushGameWait.PushGameWaitMessage.Builder.class);
    }

    public static final int TIME_FIELD_NUMBER = 1;
    private int time_;
    /**
     * <pre>
     *倒计时（秒）
     * </pre>
     *
     * <code>optional int32 time = 1;</code>
     */
    public int getTime() {
      return time_;
    }

    public static final int STAGE_FIELD_NUMBER = 2;
    private volatile Object stage_;
    /**
     * <pre>
     *&#47;/ 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
     * </pre>
     *
     * <code>optional string stage = 2;</code>
     */
    public String getStage() {
      Object ref = stage_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        stage_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *&#47;/ 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
     * </pre>
     *
     * <code>optional string stage = 2;</code>
     */
    public com.google.protobuf.ByteString
        getStageBytes() {
      Object ref = stage_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        stage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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
      if (time_ != 0) {
        output.writeInt32(1, time_);
      }
      if (!getStageBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, stage_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (time_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, time_);
      }
      if (!getStageBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, stage_);
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
      if (!(obj instanceof PushGameWait.PushGameWaitMessage)) {
        return super.equals(obj);
      }
      PushGameWait.PushGameWaitMessage other = (PushGameWait.PushGameWaitMessage) obj;

      boolean result = true;
      result = result && (getTime()
          == other.getTime());
      result = result && getStage()
          .equals(other.getStage());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + getTime();
      hash = (37 * hash) + STAGE_FIELD_NUMBER;
      hash = (53 * hash) + getStage().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static PushGameWait.PushGameWaitMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushGameWait.PushGameWaitMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushGameWait.PushGameWaitMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushGameWait.PushGameWaitMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushGameWait.PushGameWaitMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushGameWait.PushGameWaitMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushGameWait.PushGameWaitMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static PushGameWait.PushGameWaitMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushGameWait.PushGameWaitMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushGameWait.PushGameWaitMessage parseFrom(
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
    public static Builder newBuilder(PushGameWait.PushGameWaitMessage prototype) {
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
     * Protobuf type {@code proto.PushGameWaitMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.PushGameWaitMessage)
        PushGameWait.PushGameWaitMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return PushGameWait.internal_static_proto_PushGameWaitMessage_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return PushGameWait.internal_static_proto_PushGameWaitMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                PushGameWait.PushGameWaitMessage.class, PushGameWait.PushGameWaitMessage.Builder.class);
      }

      // Construct using PushGameWait.PushGameWaitMessage.newBuilder()
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
        time_ = 0;

        stage_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return PushGameWait.internal_static_proto_PushGameWaitMessage_descriptor;
      }

      public PushGameWait.PushGameWaitMessage getDefaultInstanceForType() {
        return PushGameWait.PushGameWaitMessage.getDefaultInstance();
      }

      public PushGameWait.PushGameWaitMessage build() {
        PushGameWait.PushGameWaitMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public PushGameWait.PushGameWaitMessage buildPartial() {
        PushGameWait.PushGameWaitMessage result = new PushGameWait.PushGameWaitMessage(this);
        result.time_ = time_;
        result.stage_ = stage_;
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
        if (other instanceof PushGameWait.PushGameWaitMessage) {
          return mergeFrom((PushGameWait.PushGameWaitMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(PushGameWait.PushGameWaitMessage other) {
        if (other == PushGameWait.PushGameWaitMessage.getDefaultInstance()) return this;
        if (other.getTime() != 0) {
          setTime(other.getTime());
        }
        if (!other.getStage().isEmpty()) {
          stage_ = other.stage_;
          onChanged();
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
        PushGameWait.PushGameWaitMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (PushGameWait.PushGameWaitMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int time_ ;
      /**
       * <pre>
       *倒计时（秒）
       * </pre>
       *
       * <code>optional int32 time = 1;</code>
       */
      public int getTime() {
        return time_;
      }
      /**
       * <pre>
       *倒计时（秒）
       * </pre>
       *
       * <code>optional int32 time = 1;</code>
       */
      public Builder setTime(int value) {
        
        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *倒计时（秒）
       * </pre>
       *
       * <code>optional int32 time = 1;</code>
       */
      public Builder clearTime() {
        
        time_ = 0;
        onChanged();
        return this;
      }

      private Object stage_ = "";
      /**
       * <pre>
       *&#47;/ 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
       * </pre>
       *
       * <code>optional string stage = 2;</code>
       */
      public String getStage() {
        Object ref = stage_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          stage_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       *&#47;/ 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
       * </pre>
       *
       * <code>optional string stage = 2;</code>
       */
      public com.google.protobuf.ByteString
          getStageBytes() {
        Object ref = stage_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          stage_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *&#47;/ 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
       * </pre>
       *
       * <code>optional string stage = 2;</code>
       */
      public Builder setStage(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        stage_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *&#47;/ 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
       * </pre>
       *
       * <code>optional string stage = 2;</code>
       */
      public Builder clearStage() {
        
        stage_ = getDefaultInstance().getStage();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *&#47;/ 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
       * </pre>
       *
       * <code>optional string stage = 2;</code>
       */
      public Builder setStageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        stage_ = value;
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


      // @@protoc_insertion_point(builder_scope:proto.PushGameWaitMessage)
    }

    // @@protoc_insertion_point(class_scope:proto.PushGameWaitMessage)
    private static final PushGameWait.PushGameWaitMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new PushGameWait.PushGameWaitMessage();
    }

    public static PushGameWait.PushGameWaitMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PushGameWaitMessage>
        PARSER = new com.google.protobuf.AbstractParser<PushGameWaitMessage>() {
      public PushGameWaitMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new PushGameWaitMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PushGameWaitMessage> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<PushGameWaitMessage> getParserForType() {
      return PARSER;
    }

    public PushGameWait.PushGameWaitMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_PushGameWaitMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_PushGameWaitMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022PushGameWait.proto\022\005proto\032\014Entity.prot" +
      "o\"2\n\023PushGameWaitMessage\022\014\n\004time\030\001 \001(\005\022\r" +
      "\n\005stage\030\002 \001(\tB \n\036com.sencorsta.ids.commo" +
      "n.protob\006proto3"
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
    internal_static_proto_PushGameWaitMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_PushGameWaitMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_PushGameWaitMessage_descriptor,
        new String[] { "Time", "Stage", });
    Entity.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}