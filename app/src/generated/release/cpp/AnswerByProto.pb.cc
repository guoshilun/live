// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AnswerByProto.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "AnswerByProto.pb.h"

#include <algorithm>

#include <google/protobuf/stubs/common.h>
#include <google/protobuf/stubs/port.h>
#include <google/protobuf/stubs/once.h>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/wire_format_lite_inl.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/generated_message_reflection.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// @@protoc_insertion_point(includes)

namespace proto {

namespace {

const ::google::protobuf::Descriptor* AnswerByProtoReq_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  AnswerByProtoReq_reflection_ = NULL;
const ::google::protobuf::Descriptor* AnswerByProtoRes_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  AnswerByProtoRes_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_AnswerByProto_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_AnswerByProto_2eproto() {
  protobuf_AddDesc_AnswerByProto_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "AnswerByProto.proto");
  GOOGLE_CHECK(file != NULL);
  AnswerByProtoReq_descriptor_ = file->message_type(0);
  static const int AnswerByProtoReq_offsets_[4] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(AnswerByProtoReq, userid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(AnswerByProtoReq, type_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(AnswerByProtoReq, streamid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(AnswerByProtoReq, roomid_),
  };
  AnswerByProtoReq_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      AnswerByProtoReq_descriptor_,
      AnswerByProtoReq::internal_default_instance(),
      AnswerByProtoReq_offsets_,
      -1,
      -1,
      -1,
      sizeof(AnswerByProtoReq),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(AnswerByProtoReq, _internal_metadata_));
  AnswerByProtoRes_descriptor_ = file->message_type(1);
  static const int AnswerByProtoRes_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(AnswerByProtoRes, msg_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(AnswerByProtoRes, userid_),
  };
  AnswerByProtoRes_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      AnswerByProtoRes_descriptor_,
      AnswerByProtoRes::internal_default_instance(),
      AnswerByProtoRes_offsets_,
      -1,
      -1,
      -1,
      sizeof(AnswerByProtoRes),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(AnswerByProtoRes, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_AnswerByProto_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      AnswerByProtoReq_descriptor_, AnswerByProtoReq::internal_default_instance());
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      AnswerByProtoRes_descriptor_, AnswerByProtoRes::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_AnswerByProto_2eproto() {
  AnswerByProtoReq_default_instance_.Shutdown();
  delete AnswerByProtoReq_reflection_;
  AnswerByProtoRes_default_instance_.Shutdown();
  delete AnswerByProtoRes_reflection_;
}

void protobuf_InitDefaults_AnswerByProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::proto::protobuf_InitDefaults_Entity_2eproto();
  ::google::protobuf::internal::GetEmptyString();
  AnswerByProtoReq_default_instance_.DefaultConstruct();
  ::google::protobuf::internal::GetEmptyString();
  AnswerByProtoRes_default_instance_.DefaultConstruct();
  AnswerByProtoReq_default_instance_.get_mutable()->InitAsDefaultInstance();
  AnswerByProtoRes_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_AnswerByProto_2eproto_once_);
void protobuf_InitDefaults_AnswerByProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_AnswerByProto_2eproto_once_,
                 &protobuf_InitDefaults_AnswerByProto_2eproto_impl);
}
void protobuf_AddDesc_AnswerByProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_AnswerByProto_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\023AnswerByProto.proto\022\005proto\032\014Entity.pro"
    "to\"R\n\020AnswerByProtoReq\022\016\n\006userId\030\001 \001(\t\022\014"
    "\n\004type\030\002 \001(\005\022\020\n\010streamId\030\003 \001(\t\022\016\n\006roomId"
    "\030\004 \001(\t\"A\n\020AnswerByProtoRes\022\035\n\003msg\030\001 \001(\0132"
    "\020.proto.ReturnMsg\022\016\n\006userId\030\002 \001(\tB \n\036com"
    ".sencorsta.ids.common.protob\006proto3", 235);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "AnswerByProto.proto", &protobuf_RegisterTypes);
  ::proto::protobuf_AddDesc_Entity_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_AnswerByProto_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_AnswerByProto_2eproto_once_);
void protobuf_AddDesc_AnswerByProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_AnswerByProto_2eproto_once_,
                 &protobuf_AddDesc_AnswerByProto_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_AnswerByProto_2eproto {
  StaticDescriptorInitializer_AnswerByProto_2eproto() {
    protobuf_AddDesc_AnswerByProto_2eproto();
  }
} static_descriptor_initializer_AnswerByProto_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int AnswerByProtoReq::kUserIdFieldNumber;
const int AnswerByProtoReq::kTypeFieldNumber;
const int AnswerByProtoReq::kStreamIdFieldNumber;
const int AnswerByProtoReq::kRoomIdFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

AnswerByProtoReq::AnswerByProtoReq()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_AnswerByProto_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.AnswerByProtoReq)
}

void AnswerByProtoReq::InitAsDefaultInstance() {
}

AnswerByProtoReq::AnswerByProtoReq(const AnswerByProtoReq& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.AnswerByProtoReq)
}

void AnswerByProtoReq::SharedCtor() {
  userid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  streamid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  roomid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  type_ = 0;
  _cached_size_ = 0;
}

AnswerByProtoReq::~AnswerByProtoReq() {
  // @@protoc_insertion_point(destructor:proto.AnswerByProtoReq)
  SharedDtor();
}

void AnswerByProtoReq::SharedDtor() {
  userid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  streamid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  roomid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void AnswerByProtoReq::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* AnswerByProtoReq::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return AnswerByProtoReq_descriptor_;
}

const AnswerByProtoReq& AnswerByProtoReq::default_instance() {
  protobuf_InitDefaults_AnswerByProto_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<AnswerByProtoReq> AnswerByProtoReq_default_instance_;

AnswerByProtoReq* AnswerByProtoReq::New(::google::protobuf::Arena* arena) const {
  AnswerByProtoReq* n = new AnswerByProtoReq;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void AnswerByProtoReq::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.AnswerByProtoReq)
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  type_ = 0;
  streamid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

bool AnswerByProtoReq::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.AnswerByProtoReq)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // optional string userId = 1;
      case 1: {
        if (tag == 10) {
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_userid()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->userid().data(), this->userid().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.AnswerByProtoReq.userId"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(16)) goto parse_type;
        break;
      }

      // optional int32 type = 2;
      case 2: {
        if (tag == 16) {
         parse_type:

          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::int32, ::google::protobuf::internal::WireFormatLite::TYPE_INT32>(
                 input, &type_)));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(26)) goto parse_streamId;
        break;
      }

      // optional string streamId = 3;
      case 3: {
        if (tag == 26) {
         parse_streamId:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_streamid()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->streamid().data(), this->streamid().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.AnswerByProtoReq.streamId"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(34)) goto parse_roomId;
        break;
      }

      // optional string roomId = 4;
      case 4: {
        if (tag == 34) {
         parse_roomId:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_roomid()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->roomid().data(), this->roomid().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.AnswerByProtoReq.roomId"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectAtEnd()) goto success;
        break;
      }

      default: {
      handle_unusual:
        if (tag == 0 ||
            ::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_END_GROUP) {
          goto success;
        }
        DO_(::google::protobuf::internal::WireFormatLite::SkipField(input, tag));
        break;
      }
    }
  }
success:
  // @@protoc_insertion_point(parse_success:proto.AnswerByProtoReq)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.AnswerByProtoReq)
  return false;
#undef DO_
}

void AnswerByProtoReq::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.AnswerByProtoReq)
  // optional string userId = 1;
  if (this->userid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->userid().data(), this->userid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.AnswerByProtoReq.userId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      1, this->userid(), output);
  }

  // optional int32 type = 2;
  if (this->type() != 0) {
    ::google::protobuf::internal::WireFormatLite::WriteInt32(2, this->type(), output);
  }

  // optional string streamId = 3;
  if (this->streamid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->streamid().data(), this->streamid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.AnswerByProtoReq.streamId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      3, this->streamid(), output);
  }

  // optional string roomId = 4;
  if (this->roomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->roomid().data(), this->roomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.AnswerByProtoReq.roomId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      4, this->roomid(), output);
  }

  // @@protoc_insertion_point(serialize_end:proto.AnswerByProtoReq)
}

::google::protobuf::uint8* AnswerByProtoReq::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.AnswerByProtoReq)
  // optional string userId = 1;
  if (this->userid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->userid().data(), this->userid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.AnswerByProtoReq.userId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        1, this->userid(), target);
  }

  // optional int32 type = 2;
  if (this->type() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArray(2, this->type(), target);
  }

  // optional string streamId = 3;
  if (this->streamid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->streamid().data(), this->streamid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.AnswerByProtoReq.streamId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        3, this->streamid(), target);
  }

  // optional string roomId = 4;
  if (this->roomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->roomid().data(), this->roomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.AnswerByProtoReq.roomId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        4, this->roomid(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.AnswerByProtoReq)
  return target;
}

size_t AnswerByProtoReq::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.AnswerByProtoReq)
  size_t total_size = 0;

  // optional string userId = 1;
  if (this->userid().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->userid());
  }

  // optional int32 type = 2;
  if (this->type() != 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::Int32Size(
        this->type());
  }

  // optional string streamId = 3;
  if (this->streamid().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->streamid());
  }

  // optional string roomId = 4;
  if (this->roomid().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->roomid());
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void AnswerByProtoReq::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.AnswerByProtoReq)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const AnswerByProtoReq* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const AnswerByProtoReq>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.AnswerByProtoReq)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.AnswerByProtoReq)
    UnsafeMergeFrom(*source);
  }
}

void AnswerByProtoReq::MergeFrom(const AnswerByProtoReq& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.AnswerByProtoReq)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void AnswerByProtoReq::UnsafeMergeFrom(const AnswerByProtoReq& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.userid().size() > 0) {

    userid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.userid_);
  }
  if (from.type() != 0) {
    set_type(from.type());
  }
  if (from.streamid().size() > 0) {

    streamid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.streamid_);
  }
  if (from.roomid().size() > 0) {

    roomid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.roomid_);
  }
}

void AnswerByProtoReq::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.AnswerByProtoReq)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void AnswerByProtoReq::CopyFrom(const AnswerByProtoReq& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.AnswerByProtoReq)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool AnswerByProtoReq::IsInitialized() const {

  return true;
}

void AnswerByProtoReq::Swap(AnswerByProtoReq* other) {
  if (other == this) return;
  InternalSwap(other);
}
void AnswerByProtoReq::InternalSwap(AnswerByProtoReq* other) {
  userid_.Swap(&other->userid_);
  std::swap(type_, other->type_);
  streamid_.Swap(&other->streamid_);
  roomid_.Swap(&other->roomid_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata AnswerByProtoReq::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = AnswerByProtoReq_descriptor_;
  metadata.reflection = AnswerByProtoReq_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// AnswerByProtoReq

// optional string userId = 1;
void AnswerByProtoReq::clear_userid() {
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& AnswerByProtoReq::userid() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoReq.userId)
  return userid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void AnswerByProtoReq::set_userid(const ::std::string& value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoReq.userId)
}
void AnswerByProtoReq::set_userid(const char* value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.AnswerByProtoReq.userId)
}
void AnswerByProtoReq::set_userid(const char* value, size_t size) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.AnswerByProtoReq.userId)
}
::std::string* AnswerByProtoReq::mutable_userid() {
  
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoReq.userId)
  return userid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* AnswerByProtoReq::release_userid() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoReq.userId)
  
  return userid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void AnswerByProtoReq::set_allocated_userid(::std::string* userid) {
  if (userid != NULL) {
    
  } else {
    
  }
  userid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userid);
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoReq.userId)
}

// optional int32 type = 2;
void AnswerByProtoReq::clear_type() {
  type_ = 0;
}
::google::protobuf::int32 AnswerByProtoReq::type() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoReq.type)
  return type_;
}
void AnswerByProtoReq::set_type(::google::protobuf::int32 value) {
  
  type_ = value;
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoReq.type)
}

// optional string streamId = 3;
void AnswerByProtoReq::clear_streamid() {
  streamid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& AnswerByProtoReq::streamid() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoReq.streamId)
  return streamid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void AnswerByProtoReq::set_streamid(const ::std::string& value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoReq.streamId)
}
void AnswerByProtoReq::set_streamid(const char* value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.AnswerByProtoReq.streamId)
}
void AnswerByProtoReq::set_streamid(const char* value, size_t size) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.AnswerByProtoReq.streamId)
}
::std::string* AnswerByProtoReq::mutable_streamid() {
  
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoReq.streamId)
  return streamid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* AnswerByProtoReq::release_streamid() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoReq.streamId)
  
  return streamid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void AnswerByProtoReq::set_allocated_streamid(::std::string* streamid) {
  if (streamid != NULL) {
    
  } else {
    
  }
  streamid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), streamid);
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoReq.streamId)
}

// optional string roomId = 4;
void AnswerByProtoReq::clear_roomid() {
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& AnswerByProtoReq::roomid() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoReq.roomId)
  return roomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void AnswerByProtoReq::set_roomid(const ::std::string& value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoReq.roomId)
}
void AnswerByProtoReq::set_roomid(const char* value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.AnswerByProtoReq.roomId)
}
void AnswerByProtoReq::set_roomid(const char* value, size_t size) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.AnswerByProtoReq.roomId)
}
::std::string* AnswerByProtoReq::mutable_roomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoReq.roomId)
  return roomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* AnswerByProtoReq::release_roomid() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoReq.roomId)
  
  return roomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void AnswerByProtoReq::set_allocated_roomid(::std::string* roomid) {
  if (roomid != NULL) {
    
  } else {
    
  }
  roomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), roomid);
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoReq.roomId)
}

inline const AnswerByProtoReq* AnswerByProtoReq::internal_default_instance() {
  return &AnswerByProtoReq_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int AnswerByProtoRes::kMsgFieldNumber;
const int AnswerByProtoRes::kUserIdFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

AnswerByProtoRes::AnswerByProtoRes()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_AnswerByProto_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.AnswerByProtoRes)
}

void AnswerByProtoRes::InitAsDefaultInstance() {
  msg_ = const_cast< ::proto::ReturnMsg*>(
      ::proto::ReturnMsg::internal_default_instance());
}

AnswerByProtoRes::AnswerByProtoRes(const AnswerByProtoRes& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.AnswerByProtoRes)
}

void AnswerByProtoRes::SharedCtor() {
  userid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  msg_ = NULL;
  _cached_size_ = 0;
}

AnswerByProtoRes::~AnswerByProtoRes() {
  // @@protoc_insertion_point(destructor:proto.AnswerByProtoRes)
  SharedDtor();
}

void AnswerByProtoRes::SharedDtor() {
  userid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  if (this != &AnswerByProtoRes_default_instance_.get()) {
    delete msg_;
  }
}

void AnswerByProtoRes::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* AnswerByProtoRes::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return AnswerByProtoRes_descriptor_;
}

const AnswerByProtoRes& AnswerByProtoRes::default_instance() {
  protobuf_InitDefaults_AnswerByProto_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<AnswerByProtoRes> AnswerByProtoRes_default_instance_;

AnswerByProtoRes* AnswerByProtoRes::New(::google::protobuf::Arena* arena) const {
  AnswerByProtoRes* n = new AnswerByProtoRes;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void AnswerByProtoRes::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.AnswerByProtoRes)
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

bool AnswerByProtoRes::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.AnswerByProtoRes)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // optional .proto.ReturnMsg msg = 1;
      case 1: {
        if (tag == 10) {
          DO_(::google::protobuf::internal::WireFormatLite::ReadMessageNoVirtual(
               input, mutable_msg()));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_userId;
        break;
      }

      // optional string userId = 2;
      case 2: {
        if (tag == 18) {
         parse_userId:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_userid()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->userid().data(), this->userid().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.AnswerByProtoRes.userId"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectAtEnd()) goto success;
        break;
      }

      default: {
      handle_unusual:
        if (tag == 0 ||
            ::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_END_GROUP) {
          goto success;
        }
        DO_(::google::protobuf::internal::WireFormatLite::SkipField(input, tag));
        break;
      }
    }
  }
success:
  // @@protoc_insertion_point(parse_success:proto.AnswerByProtoRes)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.AnswerByProtoRes)
  return false;
#undef DO_
}

void AnswerByProtoRes::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.AnswerByProtoRes)
  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    ::google::protobuf::internal::WireFormatLite::WriteMessageMaybeToArray(
      1, *this->msg_, output);
  }

  // optional string userId = 2;
  if (this->userid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->userid().data(), this->userid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.AnswerByProtoRes.userId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->userid(), output);
  }

  // @@protoc_insertion_point(serialize_end:proto.AnswerByProtoRes)
}

::google::protobuf::uint8* AnswerByProtoRes::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.AnswerByProtoRes)
  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    target = ::google::protobuf::internal::WireFormatLite::
      InternalWriteMessageNoVirtualToArray(
        1, *this->msg_, false, target);
  }

  // optional string userId = 2;
  if (this->userid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->userid().data(), this->userid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.AnswerByProtoRes.userId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->userid(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.AnswerByProtoRes)
  return target;
}

size_t AnswerByProtoRes::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.AnswerByProtoRes)
  size_t total_size = 0;

  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::MessageSizeNoVirtual(
        *this->msg_);
  }

  // optional string userId = 2;
  if (this->userid().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->userid());
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void AnswerByProtoRes::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.AnswerByProtoRes)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const AnswerByProtoRes* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const AnswerByProtoRes>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.AnswerByProtoRes)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.AnswerByProtoRes)
    UnsafeMergeFrom(*source);
  }
}

void AnswerByProtoRes::MergeFrom(const AnswerByProtoRes& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.AnswerByProtoRes)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void AnswerByProtoRes::UnsafeMergeFrom(const AnswerByProtoRes& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.has_msg()) {
    mutable_msg()->::proto::ReturnMsg::MergeFrom(from.msg());
  }
  if (from.userid().size() > 0) {

    userid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.userid_);
  }
}

void AnswerByProtoRes::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.AnswerByProtoRes)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void AnswerByProtoRes::CopyFrom(const AnswerByProtoRes& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.AnswerByProtoRes)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool AnswerByProtoRes::IsInitialized() const {

  return true;
}

void AnswerByProtoRes::Swap(AnswerByProtoRes* other) {
  if (other == this) return;
  InternalSwap(other);
}
void AnswerByProtoRes::InternalSwap(AnswerByProtoRes* other) {
  std::swap(msg_, other->msg_);
  userid_.Swap(&other->userid_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata AnswerByProtoRes::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = AnswerByProtoRes_descriptor_;
  metadata.reflection = AnswerByProtoRes_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// AnswerByProtoRes

// optional .proto.ReturnMsg msg = 1;
bool AnswerByProtoRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
void AnswerByProtoRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
const ::proto::ReturnMsg& AnswerByProtoRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
::proto::ReturnMsg* AnswerByProtoRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoRes.msg)
  return msg_;
}
::proto::ReturnMsg* AnswerByProtoRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
void AnswerByProtoRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoRes.msg)
}

// optional string userId = 2;
void AnswerByProtoRes::clear_userid() {
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& AnswerByProtoRes::userid() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoRes.userId)
  return userid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void AnswerByProtoRes::set_userid(const ::std::string& value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoRes.userId)
}
void AnswerByProtoRes::set_userid(const char* value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.AnswerByProtoRes.userId)
}
void AnswerByProtoRes::set_userid(const char* value, size_t size) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.AnswerByProtoRes.userId)
}
::std::string* AnswerByProtoRes::mutable_userid() {
  
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoRes.userId)
  return userid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* AnswerByProtoRes::release_userid() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoRes.userId)
  
  return userid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void AnswerByProtoRes::set_allocated_userid(::std::string* userid) {
  if (userid != NULL) {
    
  } else {
    
  }
  userid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userid);
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoRes.userId)
}

inline const AnswerByProtoRes* AnswerByProtoRes::internal_default_instance() {
  return &AnswerByProtoRes_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)
