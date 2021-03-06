// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushKickMessage.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "PushKickMessage.pb.h"

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

const ::google::protobuf::Descriptor* PushKick_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  PushKick_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_PushKickMessage_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_PushKickMessage_2eproto() {
  protobuf_AddDesc_PushKickMessage_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "PushKickMessage.proto");
  GOOGLE_CHECK(file != NULL);
  PushKick_descriptor_ = file->message_type(0);
  static const int PushKick_offsets_[1] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushKick, msg_),
  };
  PushKick_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      PushKick_descriptor_,
      PushKick::internal_default_instance(),
      PushKick_offsets_,
      -1,
      -1,
      -1,
      sizeof(PushKick),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(PushKick, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_PushKickMessage_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      PushKick_descriptor_, PushKick::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_PushKickMessage_2eproto() {
  PushKick_default_instance_.Shutdown();
  delete PushKick_reflection_;
}

void protobuf_InitDefaults_PushKickMessage_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::proto::protobuf_InitDefaults_Entity_2eproto();
  PushKick_default_instance_.DefaultConstruct();
  PushKick_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_PushKickMessage_2eproto_once_);
void protobuf_InitDefaults_PushKickMessage_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_PushKickMessage_2eproto_once_,
                 &protobuf_InitDefaults_PushKickMessage_2eproto_impl);
}
void protobuf_AddDesc_PushKickMessage_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_PushKickMessage_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\025PushKickMessage.proto\022\005proto\032\014Entity.p"
    "roto\")\n\010PushKick\022\035\n\003msg\030\001 \001(\0132\020.proto.Re"
    "turnMsgB \n\036com.sencorsta.ids.common.prot"
    "ob\006proto3", 129);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "PushKickMessage.proto", &protobuf_RegisterTypes);
  ::proto::protobuf_AddDesc_Entity_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_PushKickMessage_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_PushKickMessage_2eproto_once_);
void protobuf_AddDesc_PushKickMessage_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_PushKickMessage_2eproto_once_,
                 &protobuf_AddDesc_PushKickMessage_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_PushKickMessage_2eproto {
  StaticDescriptorInitializer_PushKickMessage_2eproto() {
    protobuf_AddDesc_PushKickMessage_2eproto();
  }
} static_descriptor_initializer_PushKickMessage_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int PushKick::kMsgFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

PushKick::PushKick()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_PushKickMessage_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.PushKick)
}

void PushKick::InitAsDefaultInstance() {
  msg_ = const_cast< ::proto::ReturnMsg*>(
      ::proto::ReturnMsg::internal_default_instance());
}

PushKick::PushKick(const PushKick& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.PushKick)
}

void PushKick::SharedCtor() {
  msg_ = NULL;
  _cached_size_ = 0;
}

PushKick::~PushKick() {
  // @@protoc_insertion_point(destructor:proto.PushKick)
  SharedDtor();
}

void PushKick::SharedDtor() {
  if (this != &PushKick_default_instance_.get()) {
    delete msg_;
  }
}

void PushKick::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* PushKick::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return PushKick_descriptor_;
}

const PushKick& PushKick::default_instance() {
  protobuf_InitDefaults_PushKickMessage_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<PushKick> PushKick_default_instance_;

PushKick* PushKick::New(::google::protobuf::Arena* arena) const {
  PushKick* n = new PushKick;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void PushKick::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.PushKick)
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}

bool PushKick::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.PushKick)
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
  // @@protoc_insertion_point(parse_success:proto.PushKick)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.PushKick)
  return false;
#undef DO_
}

void PushKick::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.PushKick)
  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    ::google::protobuf::internal::WireFormatLite::WriteMessageMaybeToArray(
      1, *this->msg_, output);
  }

  // @@protoc_insertion_point(serialize_end:proto.PushKick)
}

::google::protobuf::uint8* PushKick::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.PushKick)
  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    target = ::google::protobuf::internal::WireFormatLite::
      InternalWriteMessageNoVirtualToArray(
        1, *this->msg_, false, target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.PushKick)
  return target;
}

size_t PushKick::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.PushKick)
  size_t total_size = 0;

  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::MessageSizeNoVirtual(
        *this->msg_);
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void PushKick::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.PushKick)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const PushKick* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const PushKick>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.PushKick)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.PushKick)
    UnsafeMergeFrom(*source);
  }
}

void PushKick::MergeFrom(const PushKick& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.PushKick)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void PushKick::UnsafeMergeFrom(const PushKick& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.has_msg()) {
    mutable_msg()->::proto::ReturnMsg::MergeFrom(from.msg());
  }
}

void PushKick::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.PushKick)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void PushKick::CopyFrom(const PushKick& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.PushKick)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool PushKick::IsInitialized() const {

  return true;
}

void PushKick::Swap(PushKick* other) {
  if (other == this) return;
  InternalSwap(other);
}
void PushKick::InternalSwap(PushKick* other) {
  std::swap(msg_, other->msg_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata PushKick::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = PushKick_descriptor_;
  metadata.reflection = PushKick_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// PushKick

// optional .proto.ReturnMsg msg = 1;
bool PushKick::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
void PushKick::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
const ::proto::ReturnMsg& PushKick::msg() const {
  // @@protoc_insertion_point(field_get:proto.PushKick.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
::proto::ReturnMsg* PushKick::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.PushKick.msg)
  return msg_;
}
::proto::ReturnMsg* PushKick::release_msg() {
  // @@protoc_insertion_point(field_release:proto.PushKick.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
void PushKick::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.PushKick.msg)
}

inline const PushKick* PushKick::internal_default_instance() {
  return &PushKick_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)
