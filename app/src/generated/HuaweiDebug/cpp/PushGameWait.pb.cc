// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushGameWait.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "PushGameWait.pb.h"

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

const ::google::protobuf::Descriptor* PushGameWaitMessage_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  PushGameWaitMessage_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_PushGameWait_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_PushGameWait_2eproto() {
  protobuf_AddDesc_PushGameWait_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "PushGameWait.proto");
  GOOGLE_CHECK(file != NULL);
  PushGameWaitMessage_descriptor_ = file->message_type(0);
  static const int PushGameWaitMessage_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushGameWaitMessage, time_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushGameWaitMessage, stage_),
  };
  PushGameWaitMessage_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      PushGameWaitMessage_descriptor_,
      PushGameWaitMessage::internal_default_instance(),
      PushGameWaitMessage_offsets_,
      -1,
      -1,
      -1,
      sizeof(PushGameWaitMessage),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushGameWaitMessage, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_PushGameWait_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      PushGameWaitMessage_descriptor_, PushGameWaitMessage::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_PushGameWait_2eproto() {
  PushGameWaitMessage_default_instance_.Shutdown();
  delete PushGameWaitMessage_reflection_;
}

void protobuf_InitDefaults_PushGameWait_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::proto::protobuf_InitDefaults_Entity_2eproto();
  ::google::protobuf::internal::GetEmptyString();
  PushGameWaitMessage_default_instance_.DefaultConstruct();
  PushGameWaitMessage_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_PushGameWait_2eproto_once_);
void protobuf_InitDefaults_PushGameWait_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_PushGameWait_2eproto_once_,
                 &protobuf_InitDefaults_PushGameWait_2eproto_impl);
}
void protobuf_AddDesc_PushGameWait_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_PushGameWait_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\022PushGameWait.proto\022\005proto\032\014Entity.prot"
    "o\"2\n\023PushGameWaitMessage\022\014\n\004time\030\001 \001(\005\022\r"
    "\n\005stage\030\002 \001(\tB \n\036com.sencorsta.ids.commo"
    "n.protob\006proto3", 135);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "PushGameWait.proto", &protobuf_RegisterTypes);
  ::proto::protobuf_AddDesc_Entity_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_PushGameWait_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_PushGameWait_2eproto_once_);
void protobuf_AddDesc_PushGameWait_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_PushGameWait_2eproto_once_,
                 &protobuf_AddDesc_PushGameWait_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_PushGameWait_2eproto {
  StaticDescriptorInitializer_PushGameWait_2eproto() {
    protobuf_AddDesc_PushGameWait_2eproto();
  }
} static_descriptor_initializer_PushGameWait_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int PushGameWaitMessage::kTimeFieldNumber;
const int PushGameWaitMessage::kStageFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

PushGameWaitMessage::PushGameWaitMessage()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_PushGameWait_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.PushGameWaitMessage)
}

void PushGameWaitMessage::InitAsDefaultInstance() {
}

PushGameWaitMessage::PushGameWaitMessage(const PushGameWaitMessage& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.PushGameWaitMessage)
}

void PushGameWaitMessage::SharedCtor() {
  stage_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  time_ = 0;
  _cached_size_ = 0;
}

PushGameWaitMessage::~PushGameWaitMessage() {
  // @@protoc_insertion_point(destructor:proto.PushGameWaitMessage)
  SharedDtor();
}

void PushGameWaitMessage::SharedDtor() {
  stage_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void PushGameWaitMessage::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* PushGameWaitMessage::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return PushGameWaitMessage_descriptor_;
}

const PushGameWaitMessage& PushGameWaitMessage::default_instance() {
  protobuf_InitDefaults_PushGameWait_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<PushGameWaitMessage> PushGameWaitMessage_default_instance_;

PushGameWaitMessage* PushGameWaitMessage::New(::google::protobuf::Arena* arena) const {
  PushGameWaitMessage* n = new PushGameWaitMessage;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void PushGameWaitMessage::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.PushGameWaitMessage)
  time_ = 0;
  stage_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

bool PushGameWaitMessage::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.PushGameWaitMessage)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // optional int32 time = 1;
      case 1: {
        if (tag == 8) {

          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::int32, ::google::protobuf::internal::WireFormatLite::TYPE_INT32>(
                 input, &time_)));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_stage;
        break;
      }

      // optional string stage = 2;
      case 2: {
        if (tag == 18) {
         parse_stage:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_stage()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->stage().data(), this->stage().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.PushGameWaitMessage.stage"));
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
  // @@protoc_insertion_point(parse_success:proto.PushGameWaitMessage)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.PushGameWaitMessage)
  return false;
#undef DO_
}

void PushGameWaitMessage::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.PushGameWaitMessage)
  // optional int32 time = 1;
  if (this->time() != 0) {
    ::google::protobuf::internal::WireFormatLite::WriteInt32(1, this->time(), output);
  }

  // optional string stage = 2;
  if (this->stage().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->stage().data(), this->stage().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.PushGameWaitMessage.stage");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->stage(), output);
  }

  // @@protoc_insertion_point(serialize_end:proto.PushGameWaitMessage)
}

::google::protobuf::uint8* PushGameWaitMessage::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.PushGameWaitMessage)
  // optional int32 time = 1;
  if (this->time() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArray(1, this->time(), target);
  }

  // optional string stage = 2;
  if (this->stage().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->stage().data(), this->stage().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.PushGameWaitMessage.stage");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->stage(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.PushGameWaitMessage)
  return target;
}

size_t PushGameWaitMessage::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.PushGameWaitMessage)
  size_t total_size = 0;

  // optional int32 time = 1;
  if (this->time() != 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::Int32Size(
        this->time());
  }

  // optional string stage = 2;
  if (this->stage().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->stage());
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void PushGameWaitMessage::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.PushGameWaitMessage)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const PushGameWaitMessage* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const PushGameWaitMessage>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.PushGameWaitMessage)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.PushGameWaitMessage)
    UnsafeMergeFrom(*source);
  }
}

void PushGameWaitMessage::MergeFrom(const PushGameWaitMessage& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.PushGameWaitMessage)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void PushGameWaitMessage::UnsafeMergeFrom(const PushGameWaitMessage& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.time() != 0) {
    set_time(from.time());
  }
  if (from.stage().size() > 0) {

    stage_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.stage_);
  }
}

void PushGameWaitMessage::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.PushGameWaitMessage)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void PushGameWaitMessage::CopyFrom(const PushGameWaitMessage& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.PushGameWaitMessage)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool PushGameWaitMessage::IsInitialized() const {

  return true;
}

void PushGameWaitMessage::Swap(PushGameWaitMessage* other) {
  if (other == this) return;
  InternalSwap(other);
}
void PushGameWaitMessage::InternalSwap(PushGameWaitMessage* other) {
  std::swap(time_, other->time_);
  stage_.Swap(&other->stage_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata PushGameWaitMessage::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = PushGameWaitMessage_descriptor_;
  metadata.reflection = PushGameWaitMessage_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// PushGameWaitMessage

// optional int32 time = 1;
void PushGameWaitMessage::clear_time() {
  time_ = 0;
}
::google::protobuf::int32 PushGameWaitMessage::time() const {
  // @@protoc_insertion_point(field_get:proto.PushGameWaitMessage.time)
  return time_;
}
void PushGameWaitMessage::set_time(::google::protobuf::int32 value) {
  
  time_ = value;
  // @@protoc_insertion_point(field_set:proto.PushGameWaitMessage.time)
}

// optional string stage = 2;
void PushGameWaitMessage::clear_stage() {
  stage_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& PushGameWaitMessage::stage() const {
  // @@protoc_insertion_point(field_get:proto.PushGameWaitMessage.stage)
  return stage_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void PushGameWaitMessage::set_stage(const ::std::string& value) {
  
  stage_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PushGameWaitMessage.stage)
}
void PushGameWaitMessage::set_stage(const char* value) {
  
  stage_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PushGameWaitMessage.stage)
}
void PushGameWaitMessage::set_stage(const char* value, size_t size) {
  
  stage_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PushGameWaitMessage.stage)
}
::std::string* PushGameWaitMessage::mutable_stage() {
  
  // @@protoc_insertion_point(field_mutable:proto.PushGameWaitMessage.stage)
  return stage_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* PushGameWaitMessage::release_stage() {
  // @@protoc_insertion_point(field_release:proto.PushGameWaitMessage.stage)
  
  return stage_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void PushGameWaitMessage::set_allocated_stage(::std::string* stage) {
  if (stage != NULL) {
    
  } else {
    
  }
  stage_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), stage);
  // @@protoc_insertion_point(field_set_allocated:proto.PushGameWaitMessage.stage)
}

inline const PushGameWaitMessage* PushGameWaitMessage::internal_default_instance() {
  return &PushGameWaitMessage_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)
