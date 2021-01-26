// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushTeamChatByProto.proto

package com.jk.jkproject.net.im.info;

public final class PushTeamChatByProto {
  private PushTeamChatByProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PushTeamChatOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.PushTeamChat)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *  战队信息内容
     * </pre>
     *
     * <code>optional string message = 1;</code>
     */
    String getMessage();
    /**
     * <pre>
     *  战队信息内容
     * </pre>
     *
     * <code>optional string message = 1;</code>
     */
    com.google.protobuf.ByteString
        getMessageBytes();

    /**
     * <pre>
     *  消息发送时间
     * </pre>
     *
     * <code>optional uint64 time = 2;</code>
     */
    long getTime();
  }
  /**
   * Protobuf type {@code proto.PushTeamChat}
   */
  public  static final class PushTeamChat extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:proto.PushTeamChat)
      PushTeamChatOrBuilder {
    // Use PushTeamChat.newBuilder() to construct.
    private PushTeamChat(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PushTeamChat() {
      message_ = "";
      time_ = 0L;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private PushTeamChat(
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

              message_ = s;
              break;
            }
            case 16: {

              time_ = input.readUInt64();
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
      return PushTeamChatByProto.internal_static_proto_PushTeamChat_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PushTeamChatByProto.internal_static_proto_PushTeamChat_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              PushTeamChat.class, Builder.class);
    }

    public static final int MESSAGE_FIELD_NUMBER = 1;
    private volatile Object message_;
    /**
     * <pre>
     *  战队信息内容
     * </pre>
     *
     * <code>optional string message = 1;</code>
     */
    public String getMessage() {
      Object ref = message_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        message_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *  战队信息内容
     * </pre>
     *
     * <code>optional string message = 1;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TIME_FIELD_NUMBER = 2;
    private long time_;
    /**
     * <pre>
     *  消息发送时间
     * </pre>
     *
     * <code>optional uint64 time = 2;</code>
     */
    public long getTime() {
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
      if (!getMessageBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, message_);
      }
      if (time_ != 0L) {
        output.writeUInt64(2, time_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getMessageBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, message_);
      }
      if (time_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(2, time_);
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
      if (!(obj instanceof PushTeamChat)) {
        return super.equals(obj);
      }
      PushTeamChat other = (PushTeamChat) obj;

      boolean result = true;
      result = result && getMessage()
          .equals(other.getMessage());
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
      hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getMessage().hashCode();
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTime());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static PushTeamChat parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushTeamChat parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushTeamChat parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushTeamChat parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushTeamChat parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushTeamChat parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushTeamChat parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static PushTeamChat parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushTeamChat parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushTeamChat parseFrom(
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
    public static Builder newBuilder(PushTeamChat prototype) {
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
     * Protobuf type {@code proto.PushTeamChat}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.PushTeamChat)
        PushTeamChatOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return PushTeamChatByProto.internal_static_proto_PushTeamChat_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return PushTeamChatByProto.internal_static_proto_PushTeamChat_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                PushTeamChat.class, Builder.class);
      }

      // Construct using com.sencorsta.ids.common.proto.PushTeamChatByProto.PushTeamChat.newBuilder()
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
        message_ = "";

        time_ = 0L;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return PushTeamChatByProto.internal_static_proto_PushTeamChat_descriptor;
      }

      public PushTeamChat getDefaultInstanceForType() {
        return PushTeamChat.getDefaultInstance();
      }

      public PushTeamChat build() {
        PushTeamChat result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public PushTeamChat buildPartial() {
        PushTeamChat result = new PushTeamChat(this);
        result.message_ = message_;
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
        if (other instanceof PushTeamChat) {
          return mergeFrom((PushTeamChat)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(PushTeamChat other) {
        if (other == PushTeamChat.getDefaultInstance()) return this;
        if (!other.getMessage().isEmpty()) {
          message_ = other.message_;
          onChanged();
        }
        if (other.getTime() != 0L) {
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
        PushTeamChat parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (PushTeamChat) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private Object message_ = "";
      /**
       * <pre>
       *  战队信息内容
       * </pre>
       *
       * <code>optional string message = 1;</code>
       */
      public String getMessage() {
        Object ref = message_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          message_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       *  战队信息内容
       * </pre>
       *
       * <code>optional string message = 1;</code>
       */
      public com.google.protobuf.ByteString
          getMessageBytes() {
        Object ref = message_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          message_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *  战队信息内容
       * </pre>
       *
       * <code>optional string message = 1;</code>
       */
      public Builder setMessage(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        message_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *  战队信息内容
       * </pre>
       *
       * <code>optional string message = 1;</code>
       */
      public Builder clearMessage() {

        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *  战队信息内容
       * </pre>
       *
       * <code>optional string message = 1;</code>
       */
      public Builder setMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        message_ = value;
        onChanged();
        return this;
      }

      private long time_ ;
      /**
       * <pre>
       *  消息发送时间
       * </pre>
       *
       * <code>optional uint64 time = 2;</code>
       */
      public long getTime() {
        return time_;
      }
      /**
       * <pre>
       *  消息发送时间
       * </pre>
       *
       * <code>optional uint64 time = 2;</code>
       */
      public Builder setTime(long value) {

        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *  消息发送时间
       * </pre>
       *
       * <code>optional uint64 time = 2;</code>
       */
      public Builder clearTime() {

        time_ = 0L;
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


      // @@protoc_insertion_point(builder_scope:proto.PushTeamChat)
    }

    // @@protoc_insertion_point(class_scope:proto.PushTeamChat)
    private static final PushTeamChat DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new PushTeamChat();
    }

    public static PushTeamChat getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PushTeamChat>
        PARSER = new com.google.protobuf.AbstractParser<PushTeamChat>() {
      public PushTeamChat parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new PushTeamChat(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PushTeamChat> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<PushTeamChat> getParserForType() {
      return PARSER;
    }

    public PushTeamChat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_PushTeamChat_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_PushTeamChat_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\031PushTeamChatByProto.proto\022\005proto\032\014Enti" +
      "ty.proto\"-\n\014PushTeamChat\022\017\n\007message\030\001 \001(" +
      "\t\022\014\n\004time\030\002 \001(\004B \n\036com.sencorsta.ids.com" +
      "mon.protob\006proto3"
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
    internal_static_proto_PushTeamChat_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_PushTeamChat_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_PushTeamChat_descriptor,
        new String[] { "Message", "Time", });
    Entity.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
