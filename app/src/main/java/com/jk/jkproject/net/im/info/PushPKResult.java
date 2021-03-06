// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushPKResult.proto

package com.jk.jkproject.net.im.info;

public final class PushPKResult {
  private PushPKResult() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PushPKResultMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.PushPKResultMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *胜利的用户id
     * </pre>
     *
     * <code>optional string victoryUserId = 1;</code>
     */
    String getVictoryUserId();
    /**
     * <pre>
     *胜利的用户id
     * </pre>
     *
     * <code>optional string victoryUserId = 1;</code>
     */
    com.google.protobuf.ByteString
        getVictoryUserIdBytes();

    /**
     * <pre>
     *头像
     * </pre>
     *
     * <code>optional string picture = 2;</code>
     */
    String getPicture();
    /**
     * <pre>
     *头像
     * </pre>
     *
     * <code>optional string picture = 2;</code>
     */
    com.google.protobuf.ByteString
        getPictureBytes();

    /**
     * <pre>
     *输赢情况  1.赢  2输
     * </pre>
     *
     * <code>optional int32 condition = 3;</code>
     */
    int getCondition();
  }
  /**
   * Protobuf type {@code proto.PushPKResultMessage}
   */
  public  static final class PushPKResultMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:proto.PushPKResultMessage)
      PushPKResultMessageOrBuilder {
    // Use PushPKResultMessage.newBuilder() to construct.
    private PushPKResultMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PushPKResultMessage() {
      victoryUserId_ = "";
      picture_ = "";
      condition_ = 0;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private PushPKResultMessage(
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

              victoryUserId_ = s;
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              picture_ = s;
              break;
            }
            case 24: {

              condition_ = input.readInt32();
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
      return PushPKResult.internal_static_proto_PushPKResultMessage_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PushPKResult.internal_static_proto_PushPKResultMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              PushPKResult.PushPKResultMessage.class, PushPKResult.PushPKResultMessage.Builder.class);
    }

    public static final int VICTORYUSERID_FIELD_NUMBER = 1;
    private volatile Object victoryUserId_;
    /**
     * <pre>
     *胜利的用户id
     * </pre>
     *
     * <code>optional string victoryUserId = 1;</code>
     */
    public String getVictoryUserId() {
      Object ref = victoryUserId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        victoryUserId_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *胜利的用户id
     * </pre>
     *
     * <code>optional string victoryUserId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getVictoryUserIdBytes() {
      Object ref = victoryUserId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        victoryUserId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PICTURE_FIELD_NUMBER = 2;
    private volatile Object picture_;
    /**
     * <pre>
     *头像
     * </pre>
     *
     * <code>optional string picture = 2;</code>
     */
    public String getPicture() {
      Object ref = picture_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        picture_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *头像
     * </pre>
     *
     * <code>optional string picture = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPictureBytes() {
      Object ref = picture_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        picture_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int CONDITION_FIELD_NUMBER = 3;
    private int condition_;
    /**
     * <pre>
     *输赢情况  1.赢  2输
     * </pre>
     *
     * <code>optional int32 condition = 3;</code>
     */
    public int getCondition() {
      return condition_;
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
      if (!getVictoryUserIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, victoryUserId_);
      }
      if (!getPictureBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, picture_);
      }
      if (condition_ != 0) {
        output.writeInt32(3, condition_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getVictoryUserIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, victoryUserId_);
      }
      if (!getPictureBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, picture_);
      }
      if (condition_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, condition_);
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
      if (!(obj instanceof PushPKResult.PushPKResultMessage)) {
        return super.equals(obj);
      }
      PushPKResult.PushPKResultMessage other = (PushPKResult.PushPKResultMessage) obj;

      boolean result = true;
      result = result && getVictoryUserId()
          .equals(other.getVictoryUserId());
      result = result && getPicture()
          .equals(other.getPicture());
      result = result && (getCondition()
          == other.getCondition());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + VICTORYUSERID_FIELD_NUMBER;
      hash = (53 * hash) + getVictoryUserId().hashCode();
      hash = (37 * hash) + PICTURE_FIELD_NUMBER;
      hash = (53 * hash) + getPicture().hashCode();
      hash = (37 * hash) + CONDITION_FIELD_NUMBER;
      hash = (53 * hash) + getCondition();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static PushPKResult.PushPKResultMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushPKResult.PushPKResultMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushPKResult.PushPKResultMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushPKResult.PushPKResultMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushPKResult.PushPKResultMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushPKResult.PushPKResultMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushPKResult.PushPKResultMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static PushPKResult.PushPKResultMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushPKResult.PushPKResultMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushPKResult.PushPKResultMessage parseFrom(
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
    public static Builder newBuilder(PushPKResult.PushPKResultMessage prototype) {
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
     * Protobuf type {@code proto.PushPKResultMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.PushPKResultMessage)
        PushPKResult.PushPKResultMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return PushPKResult.internal_static_proto_PushPKResultMessage_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return PushPKResult.internal_static_proto_PushPKResultMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                PushPKResult.PushPKResultMessage.class, PushPKResult.PushPKResultMessage.Builder.class);
      }

      // Construct using PushPKResult.PushPKResultMessage.newBuilder()
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
        victoryUserId_ = "";

        picture_ = "";

        condition_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return PushPKResult.internal_static_proto_PushPKResultMessage_descriptor;
      }

      public PushPKResult.PushPKResultMessage getDefaultInstanceForType() {
        return PushPKResult.PushPKResultMessage.getDefaultInstance();
      }

      public PushPKResult.PushPKResultMessage build() {
        PushPKResult.PushPKResultMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public PushPKResult.PushPKResultMessage buildPartial() {
        PushPKResult.PushPKResultMessage result = new PushPKResult.PushPKResultMessage(this);
        result.victoryUserId_ = victoryUserId_;
        result.picture_ = picture_;
        result.condition_ = condition_;
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
        if (other instanceof PushPKResult.PushPKResultMessage) {
          return mergeFrom((PushPKResult.PushPKResultMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(PushPKResult.PushPKResultMessage other) {
        if (other == PushPKResult.PushPKResultMessage.getDefaultInstance()) return this;
        if (!other.getVictoryUserId().isEmpty()) {
          victoryUserId_ = other.victoryUserId_;
          onChanged();
        }
        if (!other.getPicture().isEmpty()) {
          picture_ = other.picture_;
          onChanged();
        }
        if (other.getCondition() != 0) {
          setCondition(other.getCondition());
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
        PushPKResult.PushPKResultMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (PushPKResult.PushPKResultMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private Object victoryUserId_ = "";
      /**
       * <pre>
       *胜利的用户id
       * </pre>
       *
       * <code>optional string victoryUserId = 1;</code>
       */
      public String getVictoryUserId() {
        Object ref = victoryUserId_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          victoryUserId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       *胜利的用户id
       * </pre>
       *
       * <code>optional string victoryUserId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getVictoryUserIdBytes() {
        Object ref = victoryUserId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          victoryUserId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *胜利的用户id
       * </pre>
       *
       * <code>optional string victoryUserId = 1;</code>
       */
      public Builder setVictoryUserId(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        victoryUserId_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *胜利的用户id
       * </pre>
       *
       * <code>optional string victoryUserId = 1;</code>
       */
      public Builder clearVictoryUserId() {
        
        victoryUserId_ = getDefaultInstance().getVictoryUserId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *胜利的用户id
       * </pre>
       *
       * <code>optional string victoryUserId = 1;</code>
       */
      public Builder setVictoryUserIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        victoryUserId_ = value;
        onChanged();
        return this;
      }

      private Object picture_ = "";
      /**
       * <pre>
       *头像
       * </pre>
       *
       * <code>optional string picture = 2;</code>
       */
      public String getPicture() {
        Object ref = picture_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          picture_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       *头像
       * </pre>
       *
       * <code>optional string picture = 2;</code>
       */
      public com.google.protobuf.ByteString
          getPictureBytes() {
        Object ref = picture_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          picture_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *头像
       * </pre>
       *
       * <code>optional string picture = 2;</code>
       */
      public Builder setPicture(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        picture_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *头像
       * </pre>
       *
       * <code>optional string picture = 2;</code>
       */
      public Builder clearPicture() {
        
        picture_ = getDefaultInstance().getPicture();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *头像
       * </pre>
       *
       * <code>optional string picture = 2;</code>
       */
      public Builder setPictureBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        picture_ = value;
        onChanged();
        return this;
      }

      private int condition_ ;
      /**
       * <pre>
       *输赢情况  1.赢  2输
       * </pre>
       *
       * <code>optional int32 condition = 3;</code>
       */
      public int getCondition() {
        return condition_;
      }
      /**
       * <pre>
       *输赢情况  1.赢  2输
       * </pre>
       *
       * <code>optional int32 condition = 3;</code>
       */
      public Builder setCondition(int value) {
        
        condition_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *输赢情况  1.赢  2输
       * </pre>
       *
       * <code>optional int32 condition = 3;</code>
       */
      public Builder clearCondition() {
        
        condition_ = 0;
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


      // @@protoc_insertion_point(builder_scope:proto.PushPKResultMessage)
    }

    // @@protoc_insertion_point(class_scope:proto.PushPKResultMessage)
    private static final PushPKResult.PushPKResultMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new PushPKResult.PushPKResultMessage();
    }

    public static PushPKResult.PushPKResultMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PushPKResultMessage>
        PARSER = new com.google.protobuf.AbstractParser<PushPKResultMessage>() {
      public PushPKResultMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new PushPKResultMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PushPKResultMessage> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<PushPKResultMessage> getParserForType() {
      return PARSER;
    }

    public PushPKResult.PushPKResultMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_PushPKResultMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_PushPKResultMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022PushPKResult.proto\022\005proto\032\014Entity.prot" +
      "o\"P\n\023PushPKResultMessage\022\025\n\rvictoryUserI" +
      "d\030\001 \001(\t\022\017\n\007picture\030\002 \001(\t\022\021\n\tcondition\030\003 " +
      "\001(\005B \n\036com.sencorsta.ids.common.protob\006p" +
      "roto3"
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
    internal_static_proto_PushPKResultMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_PushPKResultMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_PushPKResultMessage_descriptor,
        new String[] { "VictoryUserId", "Picture", "Condition", });
    Entity.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
