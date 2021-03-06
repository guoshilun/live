// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CancelPKByProto.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "CancelPKByProto.pb.h"

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

const ::google::protobuf::Descriptor* CancelPKByProtoReq_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  CancelPKByProtoReq_reflection_ = NULL;
const ::google::protobuf::Descriptor* CancelPKByProtoRes_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  CancelPKByProtoRes_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_CancelPKByProto_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_CancelPKByProto_2eproto() {
  protobuf_AddDesc_CancelPKByProto_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "CancelPKByProto.proto");
  GOOGLE_CHECK(file != NULL);
  CancelPKByProtoReq_descriptor_ = file->message_type(0);
  static const int CancelPKByProtoReq_offsets_[4] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(CancelPKByProtoReq, pkid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(CancelPKByProtoReq, tagroomid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(CancelPKByProtoReq, myroomid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(CancelPKByProtoReq, taguserid_),
  };
  CancelPKByProtoReq_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      CancelPKByProtoReq_descriptor_,
      CancelPKByProtoReq::internal_default_instance(),
      CancelPKByProtoReq_offsets_,
      -1,
      -1,
      -1,
      sizeof(CancelPKByProtoReq),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(CancelPKByProtoReq, _internal_metadata_));
  CancelPKByProtoRes_descriptor_ = file->message_type(1);
  static const int CancelPKByProtoRes_offsets_[1] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(CancelPKByProtoRes, msg_),
  };
  CancelPKByProtoRes_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      CancelPKByProtoRes_descriptor_,
      CancelPKByProtoRes::internal_default_instance(),
      CancelPKByProtoRes_offsets_,
      -1,
      -1,
      -1,
      sizeof(CancelPKByProtoRes),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(CancelPKByProtoRes, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_CancelPKByProto_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      CancelPKByProtoReq_descriptor_, CancelPKByProtoReq::internal_default_instance());
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      CancelPKByProtoRes_descriptor_, CancelPKByProtoRes::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_CancelPKByProto_2eproto() {
  CancelPKByProtoReq_default_instance_.Shutdown();
  delete CancelPKByProtoReq_reflection_;
  CancelPKByProtoRes_default_instance_.Shutdown();
  delete CancelPKByProtoRes_reflection_;
}

void protobuf_InitDefaults_CancelPKByProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::proto::protobuf_InitDefaults_Entity_2eproto();
  ::google::protobuf::internal::GetEmptyString();
  CancelPKByProtoReq_default_instance_.DefaultConstruct();
  CancelPKByProtoRes_default_instance_.DefaultConstruct();
  CancelPKByProtoReq_default_instance_.get_mutable()->InitAsDefaultInstance();
  CancelPKByProtoRes_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_CancelPKByProto_2eproto_once_);
void protobuf_InitDefaults_CancelPKByProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_CancelPKByProto_2eproto_once_,
                 &protobuf_InitDefaults_CancelPKByProto_2eproto_impl);
}
void protobuf_AddDesc_CancelPKByProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_CancelPKByProto_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\025CancelPKByProto.proto\022\005proto\032\014Entity.p"
    "roto\"Z\n\022CancelPKByProtoReq\022\014\n\004pkId\030\001 \001(\t"
    "\022\021\n\ttagRoomId\030\002 \001(\t\022\020\n\010myRoomId\030\003 \001(\t\022\021\n"
    "\ttagUserId\030\004 \001(\t\"3\n\022CancelPKByProtoRes\022\035"
    "\n\003msg\030\001 \001(\0132\020.proto.ReturnMsgB \n\036com.sen"
    "corsta.ids.common.protob\006proto3", 231);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "CancelPKByProto.proto", &protobuf_RegisterTypes);
  ::proto::protobuf_AddDesc_Entity_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_CancelPKByProto_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_CancelPKByProto_2eproto_once_);
void protobuf_AddDesc_CancelPKByProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_CancelPKByProto_2eproto_once_,
                 &protobuf_AddDesc_CancelPKByProto_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_CancelPKByProto_2eproto {
  StaticDescriptorInitializer_CancelPKByProto_2eproto() {
    protobuf_AddDesc_CancelPKByProto_2eproto();
  }
} static_descriptor_initializer_CancelPKByProto_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int CancelPKByProtoReq::kPkIdFieldNumber;
const int CancelPKByProtoReq::kTagRoomIdFieldNumber;
const int CancelPKByProtoReq::kMyRoomIdFieldNumber;
const int CancelPKByProtoReq::kTagUserIdFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

CancelPKByProtoReq::CancelPKByProtoReq()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_CancelPKByProto_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.CancelPKByProtoReq)
}

void CancelPKByProtoReq::InitAsDefaultInstance() {
}

CancelPKByProtoReq::CancelPKByProtoReq(const CancelPKByProtoReq& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.CancelPKByProtoReq)
}

void CancelPKByProtoReq::SharedCtor() {
  pkid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  tagroomid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  myroomid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  taguserid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  _cached_size_ = 0;
}

CancelPKByProtoReq::~CancelPKByProtoReq() {
  // @@protoc_insertion_point(destructor:proto.CancelPKByProtoReq)
  SharedDtor();
}

void CancelPKByProtoReq::SharedDtor() {
  pkid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  tagroomid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  myroomid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  taguserid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void CancelPKByProtoReq::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* CancelPKByProtoReq::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return CancelPKByProtoReq_descriptor_;
}

const CancelPKByProtoReq& CancelPKByProtoReq::default_instance() {
  protobuf_InitDefaults_CancelPKByProto_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<CancelPKByProtoReq> CancelPKByProtoReq_default_instance_;

CancelPKByProtoReq* CancelPKByProtoReq::New(::google::protobuf::Arena* arena) const {
  CancelPKByProtoReq* n = new CancelPKByProtoReq;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void CancelPKByProtoReq::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.CancelPKByProtoReq)
  pkid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  tagroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  myroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  taguserid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

bool CancelPKByProtoReq::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.CancelPKByProtoReq)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // optional string pkId = 1;
      case 1: {
        if (tag == 10) {
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_pkid()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->pkid().data(), this->pkid().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.CancelPKByProtoReq.pkId"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_tagRoomId;
        break;
      }

      // optional string tagRoomId = 2;
      case 2: {
        if (tag == 18) {
         parse_tagRoomId:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_tagroomid()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->tagroomid().data(), this->tagroomid().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.CancelPKByProtoReq.tagRoomId"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(26)) goto parse_myRoomId;
        break;
      }

      // optional string myRoomId = 3;
      case 3: {
        if (tag == 26) {
         parse_myRoomId:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_myroomid()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->myroomid().data(), this->myroomid().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.CancelPKByProtoReq.myRoomId"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(34)) goto parse_tagUserId;
        break;
      }

      // optional string tagUserId = 4;
      case 4: {
        if (tag == 34) {
         parse_tagUserId:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_taguserid()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->taguserid().data(), this->taguserid().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "proto.CancelPKByProtoReq.tagUserId"));
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
  // @@protoc_insertion_point(parse_success:proto.CancelPKByProtoReq)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.CancelPKByProtoReq)
  return false;
#undef DO_
}

void CancelPKByProtoReq::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.CancelPKByProtoReq)
  // optional string pkId = 1;
  if (this->pkid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->pkid().data(), this->pkid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.CancelPKByProtoReq.pkId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      1, this->pkid(), output);
  }

  // optional string tagRoomId = 2;
  if (this->tagroomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->tagroomid().data(), this->tagroomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.CancelPKByProtoReq.tagRoomId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->tagroomid(), output);
  }

  // optional string myRoomId = 3;
  if (this->myroomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->myroomid().data(), this->myroomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.CancelPKByProtoReq.myRoomId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      3, this->myroomid(), output);
  }

  // optional string tagUserId = 4;
  if (this->taguserid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->taguserid().data(), this->taguserid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.CancelPKByProtoReq.tagUserId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      4, this->taguserid(), output);
  }

  // @@protoc_insertion_point(serialize_end:proto.CancelPKByProtoReq)
}

::google::protobuf::uint8* CancelPKByProtoReq::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.CancelPKByProtoReq)
  // optional string pkId = 1;
  if (this->pkid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->pkid().data(), this->pkid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.CancelPKByProtoReq.pkId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        1, this->pkid(), target);
  }

  // optional string tagRoomId = 2;
  if (this->tagroomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->tagroomid().data(), this->tagroomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.CancelPKByProtoReq.tagRoomId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->tagroomid(), target);
  }

  // optional string myRoomId = 3;
  if (this->myroomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->myroomid().data(), this->myroomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.CancelPKByProtoReq.myRoomId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        3, this->myroomid(), target);
  }

  // optional string tagUserId = 4;
  if (this->taguserid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->taguserid().data(), this->taguserid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.CancelPKByProtoReq.tagUserId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        4, this->taguserid(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.CancelPKByProtoReq)
  return target;
}

size_t CancelPKByProtoReq::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.CancelPKByProtoReq)
  size_t total_size = 0;

  // optional string pkId = 1;
  if (this->pkid().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->pkid());
  }

  // optional string tagRoomId = 2;
  if (this->tagroomid().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->tagroomid());
  }

  // optional string myRoomId = 3;
  if (this->myroomid().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->myroomid());
  }

  // optional string tagUserId = 4;
  if (this->taguserid().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->taguserid());
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void CancelPKByProtoReq::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.CancelPKByProtoReq)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const CancelPKByProtoReq* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const CancelPKByProtoReq>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.CancelPKByProtoReq)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.CancelPKByProtoReq)
    UnsafeMergeFrom(*source);
  }
}

void CancelPKByProtoReq::MergeFrom(const CancelPKByProtoReq& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.CancelPKByProtoReq)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void CancelPKByProtoReq::UnsafeMergeFrom(const CancelPKByProtoReq& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.pkid().size() > 0) {

    pkid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.pkid_);
  }
  if (from.tagroomid().size() > 0) {

    tagroomid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.tagroomid_);
  }
  if (from.myroomid().size() > 0) {

    myroomid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.myroomid_);
  }
  if (from.taguserid().size() > 0) {

    taguserid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.taguserid_);
  }
}

void CancelPKByProtoReq::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.CancelPKByProtoReq)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void CancelPKByProtoReq::CopyFrom(const CancelPKByProtoReq& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.CancelPKByProtoReq)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool CancelPKByProtoReq::IsInitialized() const {

  return true;
}

void CancelPKByProtoReq::Swap(CancelPKByProtoReq* other) {
  if (other == this) return;
  InternalSwap(other);
}
void CancelPKByProtoReq::InternalSwap(CancelPKByProtoReq* other) {
  pkid_.Swap(&other->pkid_);
  tagroomid_.Swap(&other->tagroomid_);
  myroomid_.Swap(&other->myroomid_);
  taguserid_.Swap(&other->taguserid_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata CancelPKByProtoReq::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = CancelPKByProtoReq_descriptor_;
  metadata.reflection = CancelPKByProtoReq_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// CancelPKByProtoReq

// optional string pkId = 1;
void CancelPKByProtoReq::clear_pkid() {
  pkid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& CancelPKByProtoReq::pkid() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoReq.pkId)
  return pkid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void CancelPKByProtoReq::set_pkid(const ::std::string& value) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.CancelPKByProtoReq.pkId)
}
void CancelPKByProtoReq::set_pkid(const char* value) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.CancelPKByProtoReq.pkId)
}
void CancelPKByProtoReq::set_pkid(const char* value, size_t size) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.CancelPKByProtoReq.pkId)
}
::std::string* CancelPKByProtoReq::mutable_pkid() {
  
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoReq.pkId)
  return pkid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* CancelPKByProtoReq::release_pkid() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoReq.pkId)
  
  return pkid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void CancelPKByProtoReq::set_allocated_pkid(::std::string* pkid) {
  if (pkid != NULL) {
    
  } else {
    
  }
  pkid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), pkid);
  // @@protoc_insertion_point(field_set_allocated:proto.CancelPKByProtoReq.pkId)
}

// optional string tagRoomId = 2;
void CancelPKByProtoReq::clear_tagroomid() {
  tagroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& CancelPKByProtoReq::tagroomid() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoReq.tagRoomId)
  return tagroomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void CancelPKByProtoReq::set_tagroomid(const ::std::string& value) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.CancelPKByProtoReq.tagRoomId)
}
void CancelPKByProtoReq::set_tagroomid(const char* value) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.CancelPKByProtoReq.tagRoomId)
}
void CancelPKByProtoReq::set_tagroomid(const char* value, size_t size) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.CancelPKByProtoReq.tagRoomId)
}
::std::string* CancelPKByProtoReq::mutable_tagroomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoReq.tagRoomId)
  return tagroomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* CancelPKByProtoReq::release_tagroomid() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoReq.tagRoomId)
  
  return tagroomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void CancelPKByProtoReq::set_allocated_tagroomid(::std::string* tagroomid) {
  if (tagroomid != NULL) {
    
  } else {
    
  }
  tagroomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), tagroomid);
  // @@protoc_insertion_point(field_set_allocated:proto.CancelPKByProtoReq.tagRoomId)
}

// optional string myRoomId = 3;
void CancelPKByProtoReq::clear_myroomid() {
  myroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& CancelPKByProtoReq::myroomid() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoReq.myRoomId)
  return myroomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void CancelPKByProtoReq::set_myroomid(const ::std::string& value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.CancelPKByProtoReq.myRoomId)
}
void CancelPKByProtoReq::set_myroomid(const char* value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.CancelPKByProtoReq.myRoomId)
}
void CancelPKByProtoReq::set_myroomid(const char* value, size_t size) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.CancelPKByProtoReq.myRoomId)
}
::std::string* CancelPKByProtoReq::mutable_myroomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoReq.myRoomId)
  return myroomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* CancelPKByProtoReq::release_myroomid() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoReq.myRoomId)
  
  return myroomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void CancelPKByProtoReq::set_allocated_myroomid(::std::string* myroomid) {
  if (myroomid != NULL) {
    
  } else {
    
  }
  myroomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), myroomid);
  // @@protoc_insertion_point(field_set_allocated:proto.CancelPKByProtoReq.myRoomId)
}

// optional string tagUserId = 4;
void CancelPKByProtoReq::clear_taguserid() {
  taguserid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& CancelPKByProtoReq::taguserid() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoReq.tagUserId)
  return taguserid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void CancelPKByProtoReq::set_taguserid(const ::std::string& value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.CancelPKByProtoReq.tagUserId)
}
void CancelPKByProtoReq::set_taguserid(const char* value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.CancelPKByProtoReq.tagUserId)
}
void CancelPKByProtoReq::set_taguserid(const char* value, size_t size) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.CancelPKByProtoReq.tagUserId)
}
::std::string* CancelPKByProtoReq::mutable_taguserid() {
  
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoReq.tagUserId)
  return taguserid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* CancelPKByProtoReq::release_taguserid() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoReq.tagUserId)
  
  return taguserid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void CancelPKByProtoReq::set_allocated_taguserid(::std::string* taguserid) {
  if (taguserid != NULL) {
    
  } else {
    
  }
  taguserid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), taguserid);
  // @@protoc_insertion_point(field_set_allocated:proto.CancelPKByProtoReq.tagUserId)
}

inline const CancelPKByProtoReq* CancelPKByProtoReq::internal_default_instance() {
  return &CancelPKByProtoReq_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int CancelPKByProtoRes::kMsgFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

CancelPKByProtoRes::CancelPKByProtoRes()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_CancelPKByProto_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.CancelPKByProtoRes)
}

void CancelPKByProtoRes::InitAsDefaultInstance() {
  msg_ = const_cast< ::proto::ReturnMsg*>(
      ::proto::ReturnMsg::internal_default_instance());
}

CancelPKByProtoRes::CancelPKByProtoRes(const CancelPKByProtoRes& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.CancelPKByProtoRes)
}

void CancelPKByProtoRes::SharedCtor() {
  msg_ = NULL;
  _cached_size_ = 0;
}

CancelPKByProtoRes::~CancelPKByProtoRes() {
  // @@protoc_insertion_point(destructor:proto.CancelPKByProtoRes)
  SharedDtor();
}

void CancelPKByProtoRes::SharedDtor() {
  if (this != &CancelPKByProtoRes_default_instance_.get()) {
    delete msg_;
  }
}

void CancelPKByProtoRes::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* CancelPKByProtoRes::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return CancelPKByProtoRes_descriptor_;
}

const CancelPKByProtoRes& CancelPKByProtoRes::default_instance() {
  protobuf_InitDefaults_CancelPKByProto_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<CancelPKByProtoRes> CancelPKByProtoRes_default_instance_;

CancelPKByProtoRes* CancelPKByProtoRes::New(::google::protobuf::Arena* arena) const {
  CancelPKByProtoRes* n = new CancelPKByProtoRes;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void CancelPKByProtoRes::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.CancelPKByProtoRes)
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}

bool CancelPKByProtoRes::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.CancelPKByProtoRes)
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
  // @@protoc_insertion_point(parse_success:proto.CancelPKByProtoRes)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.CancelPKByProtoRes)
  return false;
#undef DO_
}

void CancelPKByProtoRes::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.CancelPKByProtoRes)
  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    ::google::protobuf::internal::WireFormatLite::WriteMessageMaybeToArray(
      1, *this->msg_, output);
  }

  // @@protoc_insertion_point(serialize_end:proto.CancelPKByProtoRes)
}

::google::protobuf::uint8* CancelPKByProtoRes::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.CancelPKByProtoRes)
  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    target = ::google::protobuf::internal::WireFormatLite::
      InternalWriteMessageNoVirtualToArray(
        1, *this->msg_, false, target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.CancelPKByProtoRes)
  return target;
}

size_t CancelPKByProtoRes::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.CancelPKByProtoRes)
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

void CancelPKByProtoRes::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.CancelPKByProtoRes)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const CancelPKByProtoRes* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const CancelPKByProtoRes>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.CancelPKByProtoRes)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.CancelPKByProtoRes)
    UnsafeMergeFrom(*source);
  }
}

void CancelPKByProtoRes::MergeFrom(const CancelPKByProtoRes& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.CancelPKByProtoRes)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void CancelPKByProtoRes::UnsafeMergeFrom(const CancelPKByProtoRes& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.has_msg()) {
    mutable_msg()->::proto::ReturnMsg::MergeFrom(from.msg());
  }
}

void CancelPKByProtoRes::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.CancelPKByProtoRes)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void CancelPKByProtoRes::CopyFrom(const CancelPKByProtoRes& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.CancelPKByProtoRes)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool CancelPKByProtoRes::IsInitialized() const {

  return true;
}

void CancelPKByProtoRes::Swap(CancelPKByProtoRes* other) {
  if (other == this) return;
  InternalSwap(other);
}
void CancelPKByProtoRes::InternalSwap(CancelPKByProtoRes* other) {
  std::swap(msg_, other->msg_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata CancelPKByProtoRes::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = CancelPKByProtoRes_descriptor_;
  metadata.reflection = CancelPKByProtoRes_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// CancelPKByProtoRes

// optional .proto.ReturnMsg msg = 1;
bool CancelPKByProtoRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
void CancelPKByProtoRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
const ::proto::ReturnMsg& CancelPKByProtoRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
::proto::ReturnMsg* CancelPKByProtoRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoRes.msg)
  return msg_;
}
::proto::ReturnMsg* CancelPKByProtoRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
void CancelPKByProtoRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.CancelPKByProtoRes.msg)
}

inline const CancelPKByProtoRes* CancelPKByProtoRes::internal_default_instance() {
  return &CancelPKByProtoRes_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)
