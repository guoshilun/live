// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushUserRealNameStateProto.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "PushUserRealNameStateProto.pb.h"

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

const ::google::protobuf::Descriptor* PushUserRealNameStateProtoReq_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  PushUserRealNameStateProtoReq_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_PushUserRealNameStateProto_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_PushUserRealNameStateProto_2eproto() {
  protobuf_AddDesc_PushUserRealNameStateProto_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "PushUserRealNameStateProto.proto");
  GOOGLE_CHECK(file != NULL);
  PushUserRealNameStateProtoReq_descriptor_ = file->message_type(0);
  static const int PushUserRealNameStateProtoReq_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushUserRealNameStateProtoReq, state_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushUserRealNameStateProtoReq, message_),
  };
  PushUserRealNameStateProtoReq_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      PushUserRealNameStateProtoReq_descriptor_,
      PushUserRealNameStateProtoReq::internal_default_instance(),
      PushUserRealNameStateProtoReq_offsets_,
      -1,
      -1,
      -1,
      sizeof(PushUserRealNameStateProtoReq),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushUserRealNameStateProtoReq, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_PushUserRealNameStateProto_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      PushUserRealNameStateProtoReq_descriptor_, PushUserRealNameStateProtoReq::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_PushUserRealNameStateProto_2eproto() {
  PushUserRealNameStateProtoReq_default_instance_.Shutdown();
  delete PushUserRealNameStateProtoReq_reflection_;
}

void protobuf_InitDefaults_PushUserRealNameStateProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::proto::protobuf_InitDefaults_Entity_2eproto();
  ::google::protobuf::internal::GetEmptyString();
  PushUserRealNameStateProtoReq_default_instance_.DefaultConstruct();
  PushUserRealNameStateProtoReq_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_PushUserRealNameStateProto_2eproto_once_);
void protobuf_InitDefaults_PushUserRealNameStateProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_PushUserRealNameStateProto_2eproto_once_,
                 &protobuf_InitDefaults_PushUserRealNameStateProto_2eproto_impl);
}
void protobuf_AddDesc_PushUserRealNameStateProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_PushUserRealNameStateProto_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n PushUserRealNameStateProto.proto\022\005prot"
    "o\032\014Entity.proto\"\?\n\035PushUserRealNameState"
    "ProtoReq\022\r\n\005state\030\001 \001(\005\022\017\n\007message\030\002 \001(\t"
    "B \n\036com.sencorsta.ids.common.protob\006prot"
    "o3", 162);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "PushUserRealNameStateProto.proto", &protobuf_RegisterTypes);
  ::proto::protobuf_AddDesc_Entity_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_PushUserRealNameStateProto_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_PushUserRealNameStateProto_2eproto_once_);
void protobuf_AddDesc_PushUserRealNameStateProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_PushUserRealNameStateProto_2eproto_once_,
                 &protobuf_AddDesc_PushUserRealNameStateProto_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_PushUserRealNameStateProto_2eproto {
  StaticDescriptorInitializer_PushUserRealNameStateProto_2eproto() {
    protobuf_AddDesc_PushUserRealNameStateProto_2eproto();
  }
} static_descriptor_initializer_PushUserRealNameStateProto_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int PushUserRealNameStateProtoReq::kStateFieldNumber;
const int PushUserRealNameStateProtoReq::kMessageFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

PushUserRealNameStateProtoReq::PushUserRealNameStateProtoReq()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_PushUserRealNameStateProto_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.PushUserRealNameStateProtoReq)
}

void PushUserRealNameStateProtoReq::InitAsDefaultInstance() {
}

PushUserRealNameStateProtoReq::PushUserRealNameStateProtoReq(const PushUserRealNameStateProtoReq& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.PushUserRealNameStateProtoReq)
}

void PushUserRealNameStateProtoReq::SharedCtor() {
  message_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  state_ = 0;
  _cached_size_ = 0;
}

PushUserRealNameStateProtoReq::~PushUserRealNameStateProtoReq() {
  // @@protoc_insertion_point(destructor:proto.PushUserRealNameStateProtoReq)
  SharedDtor();
}

void PushUserRealNameStateProtoReq::SharedDtor() {
  message_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void PushUserRealNameStateProtoReq::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* PushUserRealNameStateProtoReq::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return PushUserRealNameStateProtoReq_descriptor_;
}

const PushUserRealNameStateProtoReq& PushUserRealNameStateProtoReq::default_instance() {
  protobuf_InitDefaults_PushUserRealNameStateProto_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<PushUserRealNameStateProtoReq> PushUserRealNameStateProtoReq_default_instance_;

PushUserRealNameStateProtoReq* PushUserRealNameStateProtoReq::New(::google::protobuf::Arena* arena) const {
  PushUserRealNameStateProtoReq* n = new PushUserRealNameStateProtoReq;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void PushUserRealNameStateProtoReq::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.PushUserRealNameStateProtoReq)
  state_ = 0;
  message_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

bool PushUserRealNameStateProtoReq::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.PushUserRealNameStateProtoReq)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // optional int32 state = 1;
      case 1: {
        if (tag == 8) {

          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::int32, ::google::protobuf::internal::WireFormatLite::TYPE_INT32>(
                 input, &state_)));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_message;
        break;
      }

      // optional string message = 2;
      case 2: {
        if (tag == 18) {
         parse_message:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_message()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->message().data(), this->message().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.PushUserRealNameStateProtoReq.message"));
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
  // @@protoc_insertion_point(parse_success:proto.PushUserRealNameStateProtoReq)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.PushUserRealNameStateProtoReq)
  return false;
#undef DO_
}

void PushUserRealNameStateProtoReq::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.PushUserRealNameStateProtoReq)
  // optional int32 state = 1;
  if (this->state() != 0) {
    ::google::protobuf::internal::WireFormatLite::WriteInt32(1, this->state(), output);
  }

  // optional string message = 2;
  if (this->message().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->message().data(), this->message().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.PushUserRealNameStateProtoReq.message");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->message(), output);
  }

  // @@protoc_insertion_point(serialize_end:proto.PushUserRealNameStateProtoReq)
}

::google::protobuf::uint8* PushUserRealNameStateProtoReq::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.PushUserRealNameStateProtoReq)
  // optional int32 state = 1;
  if (this->state() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArray(1, this->state(), target);
  }

  // optional string message = 2;
  if (this->message().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->message().data(), this->message().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.PushUserRealNameStateProtoReq.message");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->message(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.PushUserRealNameStateProtoReq)
  return target;
}

size_t PushUserRealNameStateProtoReq::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.PushUserRealNameStateProtoReq)
  size_t total_size = 0;

  // optional int32 state = 1;
  if (this->state() != 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::Int32Size(
        this->state());
  }

  // optional string message = 2;
  if (this->message().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->message());
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void PushUserRealNameStateProtoReq::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.PushUserRealNameStateProtoReq)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const PushUserRealNameStateProtoReq* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const PushUserRealNameStateProtoReq>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.PushUserRealNameStateProtoReq)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.PushUserRealNameStateProtoReq)
    UnsafeMergeFrom(*source);
  }
}

void PushUserRealNameStateProtoReq::MergeFrom(const PushUserRealNameStateProtoReq& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.PushUserRealNameStateProtoReq)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void PushUserRealNameStateProtoReq::UnsafeMergeFrom(const PushUserRealNameStateProtoReq& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.state() != 0) {
    set_state(from.state());
  }
  if (from.message().size() > 0) {

    message_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.message_);
  }
}

void PushUserRealNameStateProtoReq::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.PushUserRealNameStateProtoReq)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void PushUserRealNameStateProtoReq::CopyFrom(const PushUserRealNameStateProtoReq& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.PushUserRealNameStateProtoReq)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool PushUserRealNameStateProtoReq::IsInitialized() const {

  return true;
}

void PushUserRealNameStateProtoReq::Swap(PushUserRealNameStateProtoReq* other) {
  if (other == this) return;
  InternalSwap(other);
}
void PushUserRealNameStateProtoReq::InternalSwap(PushUserRealNameStateProtoReq* other) {
  std::swap(state_, other->state_);
  message_.Swap(&other->message_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata PushUserRealNameStateProtoReq::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = PushUserRealNameStateProtoReq_descriptor_;
  metadata.reflection = PushUserRealNameStateProtoReq_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// PushUserRealNameStateProtoReq

// optional int32 state = 1;
void PushUserRealNameStateProtoReq::clear_state() {
  state_ = 0;
}
::google::protobuf::int32 PushUserRealNameStateProtoReq::state() const {
  // @@protoc_insertion_point(field_get:proto.PushUserRealNameStateProtoReq.state)
  return state_;
}
void PushUserRealNameStateProtoReq::set_state(::google::protobuf::int32 value) {
  
  state_ = value;
  // @@protoc_insertion_point(field_set:proto.PushUserRealNameStateProtoReq.state)
}

// optional string message = 2;
void PushUserRealNameStateProtoReq::clear_message() {
  message_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& PushUserRealNameStateProtoReq::message() const {
  // @@protoc_insertion_point(field_get:proto.PushUserRealNameStateProtoReq.message)
  return message_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void PushUserRealNameStateProtoReq::set_message(const ::std::string& value) {
  
  message_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PushUserRealNameStateProtoReq.message)
}
void PushUserRealNameStateProtoReq::set_message(const char* value) {
  
  message_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PushUserRealNameStateProtoReq.message)
}
void PushUserRealNameStateProtoReq::set_message(const char* value, size_t size) {
  
  message_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PushUserRealNameStateProtoReq.message)
}
::std::string* PushUserRealNameStateProtoReq::mutable_message() {
  
  // @@protoc_insertion_point(field_mutable:proto.PushUserRealNameStateProtoReq.message)
  return message_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* PushUserRealNameStateProtoReq::release_message() {
  // @@protoc_insertion_point(field_release:proto.PushUserRealNameStateProtoReq.message)
  
  return message_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void PushUserRealNameStateProtoReq::set_allocated_message(::std::string* message) {
  if (message != NULL) {
    
  } else {
    
  }
  message_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), message);
  // @@protoc_insertion_point(field_set_allocated:proto.PushUserRealNameStateProtoReq.message)
}

inline const PushUserRealNameStateProtoReq* PushUserRealNameStateProtoReq::internal_default_instance() {
  return &PushUserRealNameStateProtoReq_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)
