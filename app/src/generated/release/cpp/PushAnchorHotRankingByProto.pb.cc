// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushAnchorHotRankingByProto.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "PushAnchorHotRankingByProto.pb.h"

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

const ::google::protobuf::Descriptor* pushAnchorHotRanking_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  pushAnchorHotRanking_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_PushAnchorHotRankingByProto_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_PushAnchorHotRankingByProto_2eproto() {
  protobuf_AddDesc_PushAnchorHotRankingByProto_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "PushAnchorHotRankingByProto.proto");
  GOOGLE_CHECK(file != NULL);
  pushAnchorHotRanking_descriptor_ = file->message_type(0);
  static const int pushAnchorHotRanking_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(pushAnchorHotRanking, ranking_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(pushAnchorHotRanking, roomid_),
  };
  pushAnchorHotRanking_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      pushAnchorHotRanking_descriptor_,
      pushAnchorHotRanking::internal_default_instance(),
      pushAnchorHotRanking_offsets_,
      -1,
      -1,
      -1,
      sizeof(pushAnchorHotRanking),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(pushAnchorHotRanking, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_PushAnchorHotRankingByProto_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      pushAnchorHotRanking_descriptor_, pushAnchorHotRanking::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_PushAnchorHotRankingByProto_2eproto() {
  pushAnchorHotRanking_default_instance_.Shutdown();
  delete pushAnchorHotRanking_reflection_;
}

void protobuf_InitDefaults_PushAnchorHotRankingByProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::proto::protobuf_InitDefaults_Entity_2eproto();
  ::google::protobuf::internal::GetEmptyString();
  pushAnchorHotRanking_default_instance_.DefaultConstruct();
  pushAnchorHotRanking_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_PushAnchorHotRankingByProto_2eproto_once_);
void protobuf_InitDefaults_PushAnchorHotRankingByProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_PushAnchorHotRankingByProto_2eproto_once_,
                 &protobuf_InitDefaults_PushAnchorHotRankingByProto_2eproto_impl);
}
void protobuf_AddDesc_PushAnchorHotRankingByProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_PushAnchorHotRankingByProto_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n!PushAnchorHotRankingByProto.proto\022\005pro"
    "to\032\014Entity.proto\"7\n\024pushAnchorHotRanking"
    "\022\017\n\007ranking\030\001 \001(\005\022\016\n\006roomId\030\002 \001(\tB \n\036com"
    ".sencorsta.ids.common.protob\006proto3", 155);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "PushAnchorHotRankingByProto.proto", &protobuf_RegisterTypes);
  ::proto::protobuf_AddDesc_Entity_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_PushAnchorHotRankingByProto_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_PushAnchorHotRankingByProto_2eproto_once_);
void protobuf_AddDesc_PushAnchorHotRankingByProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_PushAnchorHotRankingByProto_2eproto_once_,
                 &protobuf_AddDesc_PushAnchorHotRankingByProto_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_PushAnchorHotRankingByProto_2eproto {
  StaticDescriptorInitializer_PushAnchorHotRankingByProto_2eproto() {
    protobuf_AddDesc_PushAnchorHotRankingByProto_2eproto();
  }
} static_descriptor_initializer_PushAnchorHotRankingByProto_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int pushAnchorHotRanking::kRankingFieldNumber;
const int pushAnchorHotRanking::kRoomIdFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

pushAnchorHotRanking::pushAnchorHotRanking()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_PushAnchorHotRankingByProto_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.pushAnchorHotRanking)
}

void pushAnchorHotRanking::InitAsDefaultInstance() {
}

pushAnchorHotRanking::pushAnchorHotRanking(const pushAnchorHotRanking& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.pushAnchorHotRanking)
}

void pushAnchorHotRanking::SharedCtor() {
  roomid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  ranking_ = 0;
  _cached_size_ = 0;
}

pushAnchorHotRanking::~pushAnchorHotRanking() {
  // @@protoc_insertion_point(destructor:proto.pushAnchorHotRanking)
  SharedDtor();
}

void pushAnchorHotRanking::SharedDtor() {
  roomid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void pushAnchorHotRanking::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* pushAnchorHotRanking::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return pushAnchorHotRanking_descriptor_;
}

const pushAnchorHotRanking& pushAnchorHotRanking::default_instance() {
  protobuf_InitDefaults_PushAnchorHotRankingByProto_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<pushAnchorHotRanking> pushAnchorHotRanking_default_instance_;

pushAnchorHotRanking* pushAnchorHotRanking::New(::google::protobuf::Arena* arena) const {
  pushAnchorHotRanking* n = new pushAnchorHotRanking;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void pushAnchorHotRanking::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.pushAnchorHotRanking)
  ranking_ = 0;
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

bool pushAnchorHotRanking::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.pushAnchorHotRanking)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // optional int32 ranking = 1;
      case 1: {
        if (tag == 8) {

          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::int32, ::google::protobuf::internal::WireFormatLite::TYPE_INT32>(
                 input, &ranking_)));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_roomId;
        break;
      }

      // optional string roomId = 2;
      case 2: {
        if (tag == 18) {
         parse_roomId:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_roomid()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->roomid().data(), this->roomid().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.pushAnchorHotRanking.roomId"));
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
  // @@protoc_insertion_point(parse_success:proto.pushAnchorHotRanking)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.pushAnchorHotRanking)
  return false;
#undef DO_
}

void pushAnchorHotRanking::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.pushAnchorHotRanking)
  // optional int32 ranking = 1;
  if (this->ranking() != 0) {
    ::google::protobuf::internal::WireFormatLite::WriteInt32(1, this->ranking(), output);
  }

  // optional string roomId = 2;
  if (this->roomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->roomid().data(), this->roomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.pushAnchorHotRanking.roomId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->roomid(), output);
  }

  // @@protoc_insertion_point(serialize_end:proto.pushAnchorHotRanking)
}

::google::protobuf::uint8* pushAnchorHotRanking::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.pushAnchorHotRanking)
  // optional int32 ranking = 1;
  if (this->ranking() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArray(1, this->ranking(), target);
  }

  // optional string roomId = 2;
  if (this->roomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->roomid().data(), this->roomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.pushAnchorHotRanking.roomId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->roomid(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.pushAnchorHotRanking)
  return target;
}

size_t pushAnchorHotRanking::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.pushAnchorHotRanking)
  size_t total_size = 0;

  // optional int32 ranking = 1;
  if (this->ranking() != 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::Int32Size(
        this->ranking());
  }

  // optional string roomId = 2;
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

void pushAnchorHotRanking::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.pushAnchorHotRanking)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const pushAnchorHotRanking* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const pushAnchorHotRanking>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.pushAnchorHotRanking)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.pushAnchorHotRanking)
    UnsafeMergeFrom(*source);
  }
}

void pushAnchorHotRanking::MergeFrom(const pushAnchorHotRanking& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.pushAnchorHotRanking)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void pushAnchorHotRanking::UnsafeMergeFrom(const pushAnchorHotRanking& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.ranking() != 0) {
    set_ranking(from.ranking());
  }
  if (from.roomid().size() > 0) {

    roomid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.roomid_);
  }
}

void pushAnchorHotRanking::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.pushAnchorHotRanking)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void pushAnchorHotRanking::CopyFrom(const pushAnchorHotRanking& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.pushAnchorHotRanking)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool pushAnchorHotRanking::IsInitialized() const {

  return true;
}

void pushAnchorHotRanking::Swap(pushAnchorHotRanking* other) {
  if (other == this) return;
  InternalSwap(other);
}
void pushAnchorHotRanking::InternalSwap(pushAnchorHotRanking* other) {
  std::swap(ranking_, other->ranking_);
  roomid_.Swap(&other->roomid_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata pushAnchorHotRanking::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = pushAnchorHotRanking_descriptor_;
  metadata.reflection = pushAnchorHotRanking_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// pushAnchorHotRanking

// optional int32 ranking = 1;
void pushAnchorHotRanking::clear_ranking() {
  ranking_ = 0;
}
::google::protobuf::int32 pushAnchorHotRanking::ranking() const {
  // @@protoc_insertion_point(field_get:proto.pushAnchorHotRanking.ranking)
  return ranking_;
}
void pushAnchorHotRanking::set_ranking(::google::protobuf::int32 value) {
  
  ranking_ = value;
  // @@protoc_insertion_point(field_set:proto.pushAnchorHotRanking.ranking)
}

// optional string roomId = 2;
void pushAnchorHotRanking::clear_roomid() {
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& pushAnchorHotRanking::roomid() const {
  // @@protoc_insertion_point(field_get:proto.pushAnchorHotRanking.roomId)
  return roomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void pushAnchorHotRanking::set_roomid(const ::std::string& value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.pushAnchorHotRanking.roomId)
}
void pushAnchorHotRanking::set_roomid(const char* value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.pushAnchorHotRanking.roomId)
}
void pushAnchorHotRanking::set_roomid(const char* value, size_t size) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.pushAnchorHotRanking.roomId)
}
::std::string* pushAnchorHotRanking::mutable_roomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.pushAnchorHotRanking.roomId)
  return roomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* pushAnchorHotRanking::release_roomid() {
  // @@protoc_insertion_point(field_release:proto.pushAnchorHotRanking.roomId)
  
  return roomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void pushAnchorHotRanking::set_allocated_roomid(::std::string* roomid) {
  if (roomid != NULL) {
    
  } else {
    
  }
  roomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), roomid);
  // @@protoc_insertion_point(field_set_allocated:proto.pushAnchorHotRanking.roomId)
}

inline const pushAnchorHotRanking* pushAnchorHotRanking::internal_default_instance() {
  return &pushAnchorHotRanking_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)