// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushRemovePK.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "PushRemovePK.pb.h"

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

const ::google::protobuf::Descriptor* PushRemovePKMessage_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  PushRemovePKMessage_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_PushRemovePK_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_PushRemovePK_2eproto() {
  protobuf_AddDesc_PushRemovePK_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "PushRemovePK.proto");
  GOOGLE_CHECK(file != NULL);
  PushRemovePKMessage_descriptor_ = file->message_type(0);
  static const int PushRemovePKMessage_offsets_[1] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushRemovePKMessage, userid_),
  };
  PushRemovePKMessage_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      PushRemovePKMessage_descriptor_,
      PushRemovePKMessage::internal_default_instance(),
      PushRemovePKMessage_offsets_,
      -1,
      -1,
      -1,
      sizeof(PushRemovePKMessage),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushRemovePKMessage, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_PushRemovePK_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      PushRemovePKMessage_descriptor_, PushRemovePKMessage::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_PushRemovePK_2eproto() {
  PushRemovePKMessage_default_instance_.Shutdown();
  delete PushRemovePKMessage_reflection_;
}

void protobuf_InitDefaults_PushRemovePK_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::proto::protobuf_InitDefaults_Entity_2eproto();
  ::google::protobuf::internal::GetEmptyString();
  PushRemovePKMessage_default_instance_.DefaultConstruct();
  PushRemovePKMessage_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_PushRemovePK_2eproto_once_);
void protobuf_InitDefaults_PushRemovePK_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_PushRemovePK_2eproto_once_,
                 &protobuf_InitDefaults_PushRemovePK_2eproto_impl);
}
void protobuf_AddDesc_PushRemovePK_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_PushRemovePK_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\022PushRemovePK.proto\022\005proto\032\014Entity.prot"
    "o\"%\n\023PushRemovePKMessage\022\016\n\006userId\030\001 \001(\t"
    "B \n\036com.sencorsta.ids.common.protob\006prot"
    "o3", 122);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "PushRemovePK.proto", &protobuf_RegisterTypes);
  ::proto::protobuf_AddDesc_Entity_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_PushRemovePK_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_PushRemovePK_2eproto_once_);
void protobuf_AddDesc_PushRemovePK_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_PushRemovePK_2eproto_once_,
                 &protobuf_AddDesc_PushRemovePK_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_PushRemovePK_2eproto {
  StaticDescriptorInitializer_PushRemovePK_2eproto() {
    protobuf_AddDesc_PushRemovePK_2eproto();
  }
} static_descriptor_initializer_PushRemovePK_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int PushRemovePKMessage::kUserIdFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

PushRemovePKMessage::PushRemovePKMessage()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_PushRemovePK_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.PushRemovePKMessage)
}

void PushRemovePKMessage::InitAsDefaultInstance() {
}

PushRemovePKMessage::PushRemovePKMessage(const PushRemovePKMessage& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.PushRemovePKMessage)
}

void PushRemovePKMessage::SharedCtor() {
  userid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  _cached_size_ = 0;
}

PushRemovePKMessage::~PushRemovePKMessage() {
  // @@protoc_insertion_point(destructor:proto.PushRemovePKMessage)
  SharedDtor();
}

void PushRemovePKMessage::SharedDtor() {
  userid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void PushRemovePKMessage::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* PushRemovePKMessage::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return PushRemovePKMessage_descriptor_;
}

const PushRemovePKMessage& PushRemovePKMessage::default_instance() {
  protobuf_InitDefaults_PushRemovePK_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<PushRemovePKMessage> PushRemovePKMessage_default_instance_;

PushRemovePKMessage* PushRemovePKMessage::New(::google::protobuf::Arena* arena) const {
  PushRemovePKMessage* n = new PushRemovePKMessage;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void PushRemovePKMessage::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.PushRemovePKMessage)
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

bool PushRemovePKMessage::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.PushRemovePKMessage)
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
            "proto.PushRemovePKMessage.userId"));
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
  // @@protoc_insertion_point(parse_success:proto.PushRemovePKMessage)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.PushRemovePKMessage)
  return false;
#undef DO_
}

void PushRemovePKMessage::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.PushRemovePKMessage)
  // optional string userId = 1;
  if (this->userid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->userid().data(), this->userid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.PushRemovePKMessage.userId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      1, this->userid(), output);
  }

  // @@protoc_insertion_point(serialize_end:proto.PushRemovePKMessage)
}

::google::protobuf::uint8* PushRemovePKMessage::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.PushRemovePKMessage)
  // optional string userId = 1;
  if (this->userid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->userid().data(), this->userid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.PushRemovePKMessage.userId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        1, this->userid(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.PushRemovePKMessage)
  return target;
}

size_t PushRemovePKMessage::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.PushRemovePKMessage)
  size_t total_size = 0;

  // optional string userId = 1;
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

void PushRemovePKMessage::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.PushRemovePKMessage)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const PushRemovePKMessage* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const PushRemovePKMessage>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.PushRemovePKMessage)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.PushRemovePKMessage)
    UnsafeMergeFrom(*source);
  }
}

void PushRemovePKMessage::MergeFrom(const PushRemovePKMessage& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.PushRemovePKMessage)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void PushRemovePKMessage::UnsafeMergeFrom(const PushRemovePKMessage& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.userid().size() > 0) {

    userid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.userid_);
  }
}

void PushRemovePKMessage::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.PushRemovePKMessage)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void PushRemovePKMessage::CopyFrom(const PushRemovePKMessage& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.PushRemovePKMessage)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool PushRemovePKMessage::IsInitialized() const {

  return true;
}

void PushRemovePKMessage::Swap(PushRemovePKMessage* other) {
  if (other == this) return;
  InternalSwap(other);
}
void PushRemovePKMessage::InternalSwap(PushRemovePKMessage* other) {
  userid_.Swap(&other->userid_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata PushRemovePKMessage::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = PushRemovePKMessage_descriptor_;
  metadata.reflection = PushRemovePKMessage_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// PushRemovePKMessage

// optional string userId = 1;
void PushRemovePKMessage::clear_userid() {
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& PushRemovePKMessage::userid() const {
  // @@protoc_insertion_point(field_get:proto.PushRemovePKMessage.userId)
  return userid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void PushRemovePKMessage::set_userid(const ::std::string& value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PushRemovePKMessage.userId)
}
void PushRemovePKMessage::set_userid(const char* value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PushRemovePKMessage.userId)
}
void PushRemovePKMessage::set_userid(const char* value, size_t size) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PushRemovePKMessage.userId)
}
::std::string* PushRemovePKMessage::mutable_userid() {
  
  // @@protoc_insertion_point(field_mutable:proto.PushRemovePKMessage.userId)
  return userid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* PushRemovePKMessage::release_userid() {
  // @@protoc_insertion_point(field_release:proto.PushRemovePKMessage.userId)
  
  return userid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void PushRemovePKMessage::set_allocated_userid(::std::string* userid) {
  if (userid != NULL) {
    
  } else {
    
  }
  userid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userid);
  // @@protoc_insertion_point(field_set_allocated:proto.PushRemovePKMessage.userId)
}

inline const PushRemovePKMessage* PushRemovePKMessage::internal_default_instance() {
  return &PushRemovePKMessage_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)
