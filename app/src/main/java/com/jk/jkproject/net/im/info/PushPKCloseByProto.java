// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushPKCloseByProto.proto

package com.jk.jkproject.net.im.info;

public final class PushPKCloseByProto {
  private PushPKCloseByProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface pushPKCloseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.pushPKClose)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *房间Id
     * </pre>
     *
     * <code>optional string roomId = 1;</code>
     */
    String getRoomId();
    /**
     * <pre>
     *房间Id
     * </pre>
     *
     * <code>optional string roomId = 1;</code>
     */
    com.google.protobuf.ByteString
        getRoomIdBytes();

    /**
     * <pre>
     *挂断类型  1 5分钟内正常强制挂断  2 5秒倒计时挂断  3 惩罚时间挂断  4 异常挂断     5 pk正常结束
     * </pre>
     *
     * <code>optional int32 type = 2;</code>
     */
    int getType();
  }
  /**
   * Protobuf type {@code proto.pushPKClose}
   */
  public  static final class pushPKClose extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:proto.pushPKClose)
      pushPKCloseOrBuilder {
    // Use pushPKClose.newBuilder() to construct.
    private pushPKClose(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private pushPKClose() {
      roomId_ = "";
      type_ = 0;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private pushPKClose(
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
            case 10: {
              String s = input.readStringRequireUtf8();

              roomId_ = s;
              break;
            }
            case 16: {

              type_ = input.readInt32();
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
      return PushPKCloseByProto.internal_static_proto_pushPKClose_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PushPKCloseByProto.internal_static_proto_pushPKClose_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              PushPKCloseByProto.pushPKClose.class, PushPKCloseByProto.pushPKClose.Builder.class);
    }

    public static final int ROOMID_FIELD_NUMBER = 1;
    private volatile Object roomId_;
    /**
     * <pre>
     *房间Id
     * </pre>
     *
     * <code>optional string roomId = 1;</code>
     */
    public String getRoomId() {
      Object ref = roomId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        roomId_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *房间Id
     * </pre>
     *
     * <code>optional string roomId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getRoomIdBytes() {
      Object ref = roomId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        roomId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TYPE_FIELD_NUMBER = 2;
    private int type_;
    /**
     * <pre>
     *挂断类型  1 5分钟内正常强制挂断  2 5秒倒计时挂断  3 惩罚时间挂断  4 异常挂断     5 pk正常结束
     * </pre>
     *
     * <code>optional int32 type = 2;</code>
     */
    public int getType() {
      return type_;
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
      if (!getRoomIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, roomId_);
      }
      if (type_ != 0) {
        output.writeInt32(2, type_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getRoomIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, roomId_);
      }
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, type_);
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
      if (!(obj instanceof PushPKCloseByProto.pushPKClose)) {
        return super.equals(obj);
      }
      PushPKCloseByProto.pushPKClose other = (PushPKCloseByProto.pushPKClose) obj;

      boolean result = true;
      result = result && getRoomId()
          .equals(other.getRoomId());
      result = result && (getType()
          == other.getType());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + ROOMID_FIELD_NUMBER;
      hash = (53 * hash) + getRoomId().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static PushPKCloseByProto.pushPKClose parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushPKCloseByProto.pushPKClose parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushPKCloseByProto.pushPKClose parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushPKCloseByProto.pushPKClose parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushPKCloseByProto.pushPKClose parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushPKCloseByProto.pushPKClose parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushPKCloseByProto.pushPKClose parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static PushPKCloseByProto.pushPKClose parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushPKCloseByProto.pushPKClose parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushPKCloseByProto.pushPKClose parseFrom(
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
    public static Builder newBuilder(PushPKCloseByProto.pushPKClose prototype) {
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
     * Protobuf type {@code proto.pushPKClose}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.pushPKClose)
        PushPKCloseByProto.pushPKCloseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return PushPKCloseByProto.internal_static_proto_pushPKClose_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return PushPKCloseByProto.internal_static_proto_pushPKClose_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                PushPKCloseByProto.pushPKClose.class, PushPKCloseByProto.pushPKClose.Builder.class);
      }

      // Construct using PushPKCloseByProto.pushPKClose.newBuilder()
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
        roomId_ = "";

        type_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return PushPKCloseByProto.internal_static_proto_pushPKClose_descriptor;
      }

      public PushPKCloseByProto.pushPKClose getDefaultInstanceForType() {
        return PushPKCloseByProto.pushPKClose.getDefaultInstance();
      }

      public PushPKCloseByProto.pushPKClose build() {
        PushPKCloseByProto.pushPKClose result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public PushPKCloseByProto.pushPKClose buildPartial() {
        PushPKCloseByProto.pushPKClose result = new PushPKCloseByProto.pushPKClose(this);
        result.roomId_ = roomId_;
        result.type_ = type_;
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
        if (other instanceof PushPKCloseByProto.pushPKClose) {
          return mergeFrom((PushPKCloseByProto.pushPKClose)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(PushPKCloseByProto.pushPKClose other) {
        if (other == PushPKCloseByProto.pushPKClose.getDefaultInstance()) return this;
        if (!other.getRoomId().isEmpty()) {
          roomId_ = other.roomId_;
          onChanged();
        }
        if (other.getType() != 0) {
          setType(other.getType());
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
        PushPKCloseByProto.pushPKClose parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (PushPKCloseByProto.pushPKClose) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private Object roomId_ = "";
      /**
       * <pre>
       *房间Id
       * </pre>
       *
       * <code>optional string roomId = 1;</code>
       */
      public String getRoomId() {
        Object ref = roomId_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          roomId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       *房间Id
       * </pre>
       *
       * <code>optional string roomId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getRoomIdBytes() {
        Object ref = roomId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          roomId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *房间Id
       * </pre>
       *
       * <code>optional string roomId = 1;</code>
       */
      public Builder setRoomId(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        roomId_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *房间Id
       * </pre>
       *
       * <code>optional string roomId = 1;</code>
       */
      public Builder clearRoomId() {
        
        roomId_ = getDefaultInstance().getRoomId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *房间Id
       * </pre>
       *
       * <code>optional string roomId = 1;</code>
       */
      public Builder setRoomIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        roomId_ = value;
        onChanged();
        return this;
      }

      private int type_ ;
      /**
       * <pre>
       *挂断类型  1 5分钟内正常强制挂断  2 5秒倒计时挂断  3 惩罚时间挂断  4 异常挂断     5 pk正常结束
       * </pre>
       *
       * <code>optional int32 type = 2;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <pre>
       *挂断类型  1 5分钟内正常强制挂断  2 5秒倒计时挂断  3 惩罚时间挂断  4 异常挂断     5 pk正常结束
       * </pre>
       *
       * <code>optional int32 type = 2;</code>
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *挂断类型  1 5分钟内正常强制挂断  2 5秒倒计时挂断  3 惩罚时间挂断  4 异常挂断     5 pk正常结束
       * </pre>
       *
       * <code>optional int32 type = 2;</code>
       */
      public Builder clearType() {
        
        type_ = 0;
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


      // @@protoc_insertion_point(builder_scope:proto.pushPKClose)
    }

    // @@protoc_insertion_point(class_scope:proto.pushPKClose)
    private static final PushPKCloseByProto.pushPKClose DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new PushPKCloseByProto.pushPKClose();
    }

    public static PushPKCloseByProto.pushPKClose getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<pushPKClose>
        PARSER = new com.google.protobuf.AbstractParser<pushPKClose>() {
      public pushPKClose parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new pushPKClose(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<pushPKClose> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<pushPKClose> getParserForType() {
      return PARSER;
    }

    public PushPKCloseByProto.pushPKClose getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_pushPKClose_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_pushPKClose_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\030PushPKCloseByProto.proto\022\005proto\032\014Entit" +
      "y.proto\"+\n\013pushPKClose\022\016\n\006roomId\030\001 \001(\t\022\014" +
      "\n\004type\030\002 \001(\005B \n\036com.sencorsta.ids.common" +
      ".protob\006proto3"
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
    internal_static_proto_pushPKClose_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_pushPKClose_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_pushPKClose_descriptor,
        new String[] { "RoomId", "Type", });
    Entity.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
