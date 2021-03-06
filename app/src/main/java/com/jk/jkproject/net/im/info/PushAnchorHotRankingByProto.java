// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushAnchorHotRankingByProto.proto

package com.jk.jkproject.net.im.info;

public final class PushAnchorHotRankingByProto {
  private PushAnchorHotRankingByProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface pushAnchorHotRankingOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.pushAnchorHotRanking)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *  主播日榜排名排名
     * </pre>
     *
     * <code>optional int32 ranking = 1;</code>
     */
    int getRanking();

    /**
     * <pre>
     *  推送的房间id
     * </pre>
     *
     * <code>optional string roomId = 2;</code>
     */
    String getRoomId();
    /**
     * <pre>
     *  推送的房间id
     * </pre>
     *
     * <code>optional string roomId = 2;</code>
     */
    com.google.protobuf.ByteString
        getRoomIdBytes();
  }
  /**
   * Protobuf type {@code proto.pushAnchorHotRanking}
   */
  public  static final class pushAnchorHotRanking extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:proto.pushAnchorHotRanking)
      pushAnchorHotRankingOrBuilder {
    // Use pushAnchorHotRanking.newBuilder() to construct.
    private pushAnchorHotRanking(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private pushAnchorHotRanking() {
      ranking_ = 0;
      roomId_ = "";
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private pushAnchorHotRanking(
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

              ranking_ = input.readInt32();
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              roomId_ = s;
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
      return PushAnchorHotRankingByProto.internal_static_proto_pushAnchorHotRanking_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PushAnchorHotRankingByProto.internal_static_proto_pushAnchorHotRanking_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              PushAnchorHotRankingByProto.pushAnchorHotRanking.class, PushAnchorHotRankingByProto.pushAnchorHotRanking.Builder.class);
    }

    public static final int RANKING_FIELD_NUMBER = 1;
    private int ranking_;
    /**
     * <pre>
     *  主播日榜排名排名
     * </pre>
     *
     * <code>optional int32 ranking = 1;</code>
     */
    public int getRanking() {
      return ranking_;
    }

    public static final int ROOMID_FIELD_NUMBER = 2;
    private volatile Object roomId_;
    /**
     * <pre>
     *  推送的房间id
     * </pre>
     *
     * <code>optional string roomId = 2;</code>
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
     *  推送的房间id
     * </pre>
     *
     * <code>optional string roomId = 2;</code>
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
      if (ranking_ != 0) {
        output.writeInt32(1, ranking_);
      }
      if (!getRoomIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, roomId_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (ranking_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, ranking_);
      }
      if (!getRoomIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, roomId_);
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
      if (!(obj instanceof PushAnchorHotRankingByProto.pushAnchorHotRanking)) {
        return super.equals(obj);
      }
      PushAnchorHotRankingByProto.pushAnchorHotRanking other = (PushAnchorHotRankingByProto.pushAnchorHotRanking) obj;

      boolean result = true;
      result = result && (getRanking()
          == other.getRanking());
      result = result && getRoomId()
          .equals(other.getRoomId());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + RANKING_FIELD_NUMBER;
      hash = (53 * hash) + getRanking();
      hash = (37 * hash) + ROOMID_FIELD_NUMBER;
      hash = (53 * hash) + getRoomId().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushAnchorHotRankingByProto.pushAnchorHotRanking parseFrom(
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
    public static Builder newBuilder(PushAnchorHotRankingByProto.pushAnchorHotRanking prototype) {
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
     * Protobuf type {@code proto.pushAnchorHotRanking}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.pushAnchorHotRanking)
        PushAnchorHotRankingByProto.pushAnchorHotRankingOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return PushAnchorHotRankingByProto.internal_static_proto_pushAnchorHotRanking_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return PushAnchorHotRankingByProto.internal_static_proto_pushAnchorHotRanking_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                PushAnchorHotRankingByProto.pushAnchorHotRanking.class, PushAnchorHotRankingByProto.pushAnchorHotRanking.Builder.class);
      }

      // Construct using com.sencorsta.ids.common.proto.PushAnchorHotRankingByProto.pushAnchorHotRanking.newBuilder()
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
        ranking_ = 0;

        roomId_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return PushAnchorHotRankingByProto.internal_static_proto_pushAnchorHotRanking_descriptor;
      }

      public PushAnchorHotRankingByProto.pushAnchorHotRanking getDefaultInstanceForType() {
        return PushAnchorHotRankingByProto.pushAnchorHotRanking.getDefaultInstance();
      }

      public PushAnchorHotRankingByProto.pushAnchorHotRanking build() {
        PushAnchorHotRankingByProto.pushAnchorHotRanking result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public PushAnchorHotRankingByProto.pushAnchorHotRanking buildPartial() {
        PushAnchorHotRankingByProto.pushAnchorHotRanking result = new PushAnchorHotRankingByProto.pushAnchorHotRanking(this);
        result.ranking_ = ranking_;
        result.roomId_ = roomId_;
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
        if (other instanceof PushAnchorHotRankingByProto.pushAnchorHotRanking) {
          return mergeFrom((PushAnchorHotRankingByProto.pushAnchorHotRanking)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(PushAnchorHotRankingByProto.pushAnchorHotRanking other) {
        if (other == PushAnchorHotRankingByProto.pushAnchorHotRanking.getDefaultInstance()) return this;
        if (other.getRanking() != 0) {
          setRanking(other.getRanking());
        }
        if (!other.getRoomId().isEmpty()) {
          roomId_ = other.roomId_;
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
        PushAnchorHotRankingByProto.pushAnchorHotRanking parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (PushAnchorHotRankingByProto.pushAnchorHotRanking) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int ranking_ ;
      /**
       * <pre>
       *  主播日榜排名排名
       * </pre>
       *
       * <code>optional int32 ranking = 1;</code>
       */
      public int getRanking() {
        return ranking_;
      }
      /**
       * <pre>
       *  主播日榜排名排名
       * </pre>
       *
       * <code>optional int32 ranking = 1;</code>
       */
      public Builder setRanking(int value) {

        ranking_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *  主播日榜排名排名
       * </pre>
       *
       * <code>optional int32 ranking = 1;</code>
       */
      public Builder clearRanking() {

        ranking_ = 0;
        onChanged();
        return this;
      }

      private Object roomId_ = "";
      /**
       * <pre>
       *  推送的房间id
       * </pre>
       *
       * <code>optional string roomId = 2;</code>
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
       *  推送的房间id
       * </pre>
       *
       * <code>optional string roomId = 2;</code>
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
       *  推送的房间id
       * </pre>
       *
       * <code>optional string roomId = 2;</code>
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
       *  推送的房间id
       * </pre>
       *
       * <code>optional string roomId = 2;</code>
       */
      public Builder clearRoomId() {

        roomId_ = getDefaultInstance().getRoomId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *  推送的房间id
       * </pre>
       *
       * <code>optional string roomId = 2;</code>
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
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:proto.pushAnchorHotRanking)
    }

    // @@protoc_insertion_point(class_scope:proto.pushAnchorHotRanking)
    private static final PushAnchorHotRankingByProto.pushAnchorHotRanking DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new PushAnchorHotRankingByProto.pushAnchorHotRanking();
    }

    public static PushAnchorHotRankingByProto.pushAnchorHotRanking getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<pushAnchorHotRanking>
        PARSER = new com.google.protobuf.AbstractParser<pushAnchorHotRanking>() {
      public pushAnchorHotRanking parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new pushAnchorHotRanking(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<pushAnchorHotRanking> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<pushAnchorHotRanking> getParserForType() {
      return PARSER;
    }

    public PushAnchorHotRankingByProto.pushAnchorHotRanking getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_pushAnchorHotRanking_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_pushAnchorHotRanking_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n!PushAnchorHotRankingByProto.proto\022\005pro" +
      "to\032\014Entity.proto\"7\n\024pushAnchorHotRanking" +
      "\022\017\n\007ranking\030\001 \001(\005\022\016\n\006roomId\030\002 \001(\tB \n\036com" +
      ".sencorsta.ids.common.protob\006proto3"
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
    internal_static_proto_pushAnchorHotRanking_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_pushAnchorHotRanking_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_pushAnchorHotRanking_descriptor,
        new String[] { "Ranking", "RoomId", });
    Entity.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
