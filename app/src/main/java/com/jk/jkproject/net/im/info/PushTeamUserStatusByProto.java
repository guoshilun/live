// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushTeamUserStatusByProto.proto

package com.jk.jkproject.net.im.info;

public final class PushTeamUserStatusByProto {
  private PushTeamUserStatusByProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PushTeamUserStatusOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.PushTeamUserStatus)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *  1 战队未加入  2 申请加入战队 3 申请创建战队 4 已加入战队
     * </pre>
     *
     * <code>optional int32 teamType = 1;</code>
     */
    int getTeamType();
  }
  /**
   * Protobuf type {@code proto.PushTeamUserStatus}
   */
  public  static final class PushTeamUserStatus extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:proto.PushTeamUserStatus)
      PushTeamUserStatusOrBuilder {
    // Use PushTeamUserStatus.newBuilder() to construct.
    private PushTeamUserStatus(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PushTeamUserStatus() {
      teamType_ = 0;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private PushTeamUserStatus(
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

              teamType_ = input.readInt32();
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
      return PushTeamUserStatusByProto.internal_static_proto_PushTeamUserStatus_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PushTeamUserStatusByProto.internal_static_proto_PushTeamUserStatus_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              PushTeamUserStatus.class, Builder.class);
    }

    public static final int TEAMTYPE_FIELD_NUMBER = 1;
    private int teamType_;
    /**
     * <pre>
     *  1 战队未加入  2 申请加入战队 3 申请创建战队 4 已加入战队
     * </pre>
     *
     * <code>optional int32 teamType = 1;</code>
     */
    public int getTeamType() {
      return teamType_;
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
      if (teamType_ != 0) {
        output.writeInt32(1, teamType_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (teamType_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, teamType_);
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
      if (!(obj instanceof PushTeamUserStatus)) {
        return super.equals(obj);
      }
      PushTeamUserStatus other = (PushTeamUserStatus) obj;

      boolean result = true;
      result = result && (getTeamType()
          == other.getTeamType());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + TEAMTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getTeamType();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static PushTeamUserStatus parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushTeamUserStatus parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushTeamUserStatus parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static PushTeamUserStatus parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static PushTeamUserStatus parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushTeamUserStatus parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushTeamUserStatus parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static PushTeamUserStatus parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static PushTeamUserStatus parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static PushTeamUserStatus parseFrom(
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
    public static Builder newBuilder(PushTeamUserStatus prototype) {
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
     * Protobuf type {@code proto.PushTeamUserStatus}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.PushTeamUserStatus)
        PushTeamUserStatusOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return PushTeamUserStatusByProto.internal_static_proto_PushTeamUserStatus_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return PushTeamUserStatusByProto.internal_static_proto_PushTeamUserStatus_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                PushTeamUserStatus.class, Builder.class);
      }

      // Construct using com.sencorsta.ids.common.proto.PushTeamUserStatusByProto.PushTeamUserStatus.newBuilder()
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
        teamType_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return PushTeamUserStatusByProto.internal_static_proto_PushTeamUserStatus_descriptor;
      }

      public PushTeamUserStatus getDefaultInstanceForType() {
        return PushTeamUserStatus.getDefaultInstance();
      }

      public PushTeamUserStatus build() {
        PushTeamUserStatus result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public PushTeamUserStatus buildPartial() {
        PushTeamUserStatus result = new PushTeamUserStatus(this);
        result.teamType_ = teamType_;
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
        if (other instanceof PushTeamUserStatus) {
          return mergeFrom((PushTeamUserStatus)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(PushTeamUserStatus other) {
        if (other == PushTeamUserStatus.getDefaultInstance()) return this;
        if (other.getTeamType() != 0) {
          setTeamType(other.getTeamType());
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
        PushTeamUserStatus parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (PushTeamUserStatus) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int teamType_ ;
      /**
       * <pre>
       *  1 战队未加入  2 申请加入战队 3 申请创建战队 4 已加入战队
       * </pre>
       *
       * <code>optional int32 teamType = 1;</code>
       */
      public int getTeamType() {
        return teamType_;
      }
      /**
       * <pre>
       *  1 战队未加入  2 申请加入战队 3 申请创建战队 4 已加入战队
       * </pre>
       *
       * <code>optional int32 teamType = 1;</code>
       */
      public Builder setTeamType(int value) {

        teamType_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *  1 战队未加入  2 申请加入战队 3 申请创建战队 4 已加入战队
       * </pre>
       *
       * <code>optional int32 teamType = 1;</code>
       */
      public Builder clearTeamType() {

        teamType_ = 0;
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


      // @@protoc_insertion_point(builder_scope:proto.PushTeamUserStatus)
    }

    // @@protoc_insertion_point(class_scope:proto.PushTeamUserStatus)
    private static final PushTeamUserStatus DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new PushTeamUserStatus();
    }

    public static PushTeamUserStatus getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PushTeamUserStatus>
        PARSER = new com.google.protobuf.AbstractParser<PushTeamUserStatus>() {
      public PushTeamUserStatus parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new PushTeamUserStatus(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PushTeamUserStatus> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<PushTeamUserStatus> getParserForType() {
      return PARSER;
    }

    public PushTeamUserStatus getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_PushTeamUserStatus_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_PushTeamUserStatus_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\037PushTeamUserStatusByProto.proto\022\005proto" +
      "\032\014Entity.proto\"&\n\022PushTeamUserStatus\022\020\n\010" +
      "teamType\030\001 \001(\005B \n\036com.sencorsta.ids.comm" +
      "on.protob\006proto3"
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
    internal_static_proto_PushTeamUserStatus_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_PushTeamUserStatus_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_PushTeamUserStatus_descriptor,
        new String[] { "TeamType", });
    Entity.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
