// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: HangupPKByProto.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "HangupPKByProto.pb.h"

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

const ::google::protobuf::Descriptor* HangupPKByProtoReq_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  HangupPKByProtoReq_reflection_ = NULL;
const ::google::protobuf::Descriptor* HangupPKByProtoRes_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  HangupPKByProtoRes_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_HangupPKByProto_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_HangupPKByProto_2eproto() {
  protobuf_AddDesc_HangupPKByProto_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "HangupPKByProto.proto");
  GOOGLE_CHECK(file != NULL);
  HangupPKByProtoReq_descriptor_ = file->message_type(0);
  static const int HangupPKByProtoReq_offsets_[5] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(HangupPKByProtoReq, pkid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(HangupPKByProtoReq, tagroomid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(HangupPKByProtoReq, myroomid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(HangupPKByProtoReq, taguserid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(HangupPKByProtoReq, type_),
  };
  HangupPKByProtoReq_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      HangupPKByProtoReq_descriptor_,
      HangupPKByProtoReq::internal_default_instance(),
      HangupPKByProtoReq_offsets_,
      -1,
      -1,
      -1,
      sizeof(HangupPKByProtoReq),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(HangupPKByProtoReq, _internal_metadata_));
  HangupPKByProtoRes_descriptor_ = file->message_type(1);
  static const int HangupPKByProtoRes_offsets_[1] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(HangupPKByProtoRes, msg_),
  };
  HangupPKByProtoRes_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      HangupPKByProtoRes_descriptor_,
      HangupPKByProtoRes::internal_default_instance(),
      HangupPKByProtoRes_offsets_,
      -1,
      -1,
      -1,
      sizeof(HangupPKByProtoRes),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(HangupPKByProtoRes, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_HangupPKByProto_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      HangupPKByProtoReq_descriptor_, HangupPKByProtoReq::internal_default_instance());
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      HangupPKByProtoRes_descriptor_, HangupPKByProtoRes::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_HangupPKByProto_2eproto() {
  HangupPKByProtoReq_default_instance_.Shutdown();
  delete HangupPKByProtoReq_reflection_;
  HangupPKByProtoRes_default_instance_.Shutdown();
  delete HangupPKByProtoRes_reflection_;
}

void protobuf_InitDefaults_HangupPKByProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::proto::protobuf_InitDefaults_Entity_2eproto();
  ::google::protobuf::internal::GetEmptyString();
  HangupPKByProtoReq_default_instance_.DefaultConstruct();
  HangupPKByProtoRes_default_instance_.DefaultConstruct();
  HangupPKByProtoReq_default_instance_.get_mutable()->InitAsDefaultInstance();
  HangupPKByProtoRes_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_HangupPKByProto_2eproto_once_);
void protobuf_InitDefaults_HangupPKByProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_HangupPKByProto_2eproto_once_,
                 &protobuf_InitDefaults_HangupPKByProto_2eproto_impl);
}
void protobuf_AddDesc_HangupPKByProto_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_HangupPKByProto_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\025HangupPKByProto.proto\022\005proto\032\014Entity.p"
    "roto\"h\n\022HangupPKByProtoReq\022\014\n\004pkId\030\001 \001(\t"
    "\022\021\n\ttagRoomId\030\002 \001(\t\022\020\n\010myRoomId\030\003 \001(\t\022\021\n"
    "\ttagUserId\030\004 \001(\t\022\014\n\004type\030\005 \001(\005\"3\n\022Hangup"
    "PKByProtoRes\022\035\n\003msg\030\001 \001(\0132\020.proto.Return"
    "MsgB \n\036com.sencorsta.ids.common.protob\006p"
    "roto3", 245);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "HangupPKByProto.proto", &protobuf_RegisterTypes);
  ::proto::protobuf_AddDesc_Entity_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_HangupPKByProto_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_HangupPKByProto_2eproto_once_);
void protobuf_AddDesc_HangupPKByProto_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_HangupPKByProto_2eproto_once_,
                 &protobuf_AddDesc_HangupPKByProto_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_HangupPKByProto_2eproto {
  StaticDescriptorInitializer_HangupPKByProto_2eproto() {
    protobuf_AddDesc_HangupPKByProto_2eproto();
  }
} static_descriptor_initializer_HangupPKByProto_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int HangupPKByProtoReq::kPkIdFieldNumber;
const int HangupPKByProtoReq::kTagRoomIdFieldNumber;
const int HangupPKByProtoReq::kMyRoomIdFieldNumber;
const int HangupPKByProtoReq::kTagUserIdFieldNumber;
const int HangupPKByProtoReq::kTypeFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

HangupPKByProtoReq::HangupPKByProtoReq()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_HangupPKByProto_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.HangupPKByProtoReq)
}

void HangupPKByProtoReq::InitAsDefaultInstance() {
}

HangupPKByProtoReq::HangupPKByProtoReq(const HangupPKByProtoReq& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.HangupPKByProtoReq)
}

void HangupPKByProtoReq::SharedCtor() {
  pkid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  tagroomid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  myroomid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  taguserid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  type_ = 0;
  _cached_size_ = 0;
}

HangupPKByProtoReq::~HangupPKByProtoReq() {
  // @@protoc_insertion_point(destructor:proto.HangupPKByProtoReq)
  SharedDtor();
}

void HangupPKByProtoReq::SharedDtor() {
  pkid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  tagroomid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  myroomid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  taguserid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void HangupPKByProtoReq::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* HangupPKByProtoReq::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return HangupPKByProtoReq_descriptor_;
}

const HangupPKByProtoReq& HangupPKByProtoReq::default_instance() {
  protobuf_InitDefaults_HangupPKByProto_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<HangupPKByProtoReq> HangupPKByProtoReq_default_instance_;

HangupPKByProtoReq* HangupPKByProtoReq::New(::google::protobuf::Arena* arena) const {
  HangupPKByProtoReq* n = new HangupPKByProtoReq;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void HangupPKByProtoReq::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.HangupPKByProtoReq)
  pkid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  tagroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  myroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  taguserid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  type_ = 0;
}

bool HangupPKByProtoReq::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.HangupPKByProtoReq)
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
            "proto.HangupPKByProtoReq.pkId"));
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
            "proto.HangupPKByProtoReq.tagRoomId"));
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
            "proto.HangupPKByProtoReq.myRoomId"));
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
            "proto.HangupPKByProtoReq.tagUserId"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(40)) goto parse_type;
        break;
      }

      // optional int32 type = 5;
      case 5: {
        if (tag == 40) {
         parse_type:

          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::int32, ::google::protobuf::internal::WireFormatLite::TYPE_INT32>(
                 input, &type_)));
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
  // @@protoc_insertion_point(parse_success:proto.HangupPKByProtoReq)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.HangupPKByProtoReq)
  return false;
#undef DO_
}

void HangupPKByProtoReq::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.HangupPKByProtoReq)
  // optional string pkId = 1;
  if (this->pkid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->pkid().data(), this->pkid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.HangupPKByProtoReq.pkId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      1, this->pkid(), output);
  }

  // optional string tagRoomId = 2;
  if (this->tagroomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->tagroomid().data(), this->tagroomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.HangupPKByProtoReq.tagRoomId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->tagroomid(), output);
  }

  // optional string myRoomId = 3;
  if (this->myroomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->myroomid().data(), this->myroomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.HangupPKByProtoReq.myRoomId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      3, this->myroomid(), output);
  }

  // optional string tagUserId = 4;
  if (this->taguserid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->taguserid().data(), this->taguserid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.HangupPKByProtoReq.tagUserId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      4, this->taguserid(), output);
  }

  // optional int32 type = 5;
  if (this->type() != 0) {
    ::google::protobuf::internal::WireFormatLite::WriteInt32(5, this->type(), output);
  }

  // @@protoc_insertion_point(serialize_end:proto.HangupPKByProtoReq)
}

::google::protobuf::uint8* HangupPKByProtoReq::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.HangupPKByProtoReq)
  // optional string pkId = 1;
  if (this->pkid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->pkid().data(), this->pkid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.HangupPKByProtoReq.pkId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        1, this->pkid(), target);
  }

  // optional string tagRoomId = 2;
  if (this->tagroomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->tagroomid().data(), this->tagroomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.HangupPKByProtoReq.tagRoomId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->tagroomid(), target);
  }

  // optional string myRoomId = 3;
  if (this->myroomid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->myroomid().data(), this->myroomid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.HangupPKByProtoReq.myRoomId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        3, this->myroomid(), target);
  }

  // optional string tagUserId = 4;
  if (this->taguserid().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->taguserid().data(), this->taguserid().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "proto.HangupPKByProtoReq.tagUserId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        4, this->taguserid(), target);
  }

  // optional int32 type = 5;
  if (this->type() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArray(5, this->type(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.HangupPKByProtoReq)
  return target;
}

size_t HangupPKByProtoReq::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.HangupPKByProtoReq)
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

  // optional int32 type = 5;
  if (this->type() != 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::Int32Size(
        this->type());
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void HangupPKByProtoReq::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.HangupPKByProtoReq)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const HangupPKByProtoReq* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const HangupPKByProtoReq>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.HangupPKByProtoReq)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.HangupPKByProtoReq)
    UnsafeMergeFrom(*source);
  }
}

void HangupPKByProtoReq::MergeFrom(const HangupPKByProtoReq& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.HangupPKByProtoReq)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void HangupPKByProtoReq::UnsafeMergeFrom(const HangupPKByProtoReq& from) {
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
  if (from.type() != 0) {
    set_type(from.type());
  }
}

void HangupPKByProtoReq::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.HangupPKByProtoReq)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void HangupPKByProtoReq::CopyFrom(const HangupPKByProtoReq& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.HangupPKByProtoReq)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool HangupPKByProtoReq::IsInitialized() const {

  return true;
}

void HangupPKByProtoReq::Swap(HangupPKByProtoReq* other) {
  if (other == this) return;
  InternalSwap(other);
}
void HangupPKByProtoReq::InternalSwap(HangupPKByProtoReq* other) {
  pkid_.Swap(&other->pkid_);
  tagroomid_.Swap(&other->tagroomid_);
  myroomid_.Swap(&other->myroomid_);
  taguserid_.Swap(&other->taguserid_);
  std::swap(type_, other->type_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata HangupPKByProtoReq::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = HangupPKByProtoReq_descriptor_;
  metadata.reflection = HangupPKByProtoReq_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// HangupPKByProtoReq

// optional string pkId = 1;
void HangupPKByProtoReq::clear_pkid() {
  pkid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& HangupPKByProtoReq::pkid() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.pkId)
  return pkid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void HangupPKByProtoReq::set_pkid(const ::std::string& value) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.pkId)
}
void HangupPKByProtoReq::set_pkid(const char* value) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.HangupPKByProtoReq.pkId)
}
void HangupPKByProtoReq::set_pkid(const char* value, size_t size) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.HangupPKByProtoReq.pkId)
}
::std::string* HangupPKByProtoReq::mutable_pkid() {
  
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoReq.pkId)
  return pkid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* HangupPKByProtoReq::release_pkid() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoReq.pkId)
  
  return pkid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void HangupPKByProtoReq::set_allocated_pkid(::std::string* pkid) {
  if (pkid != NULL) {
    
  } else {
    
  }
  pkid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), pkid);
  // @@protoc_insertion_point(field_set_allocated:proto.HangupPKByProtoReq.pkId)
}

// optional string tagRoomId = 2;
void HangupPKByProtoReq::clear_tagroomid() {
  tagroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& HangupPKByProtoReq::tagroomid() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.tagRoomId)
  return tagroomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void HangupPKByProtoReq::set_tagroomid(const ::std::string& value) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.tagRoomId)
}
void HangupPKByProtoReq::set_tagroomid(const char* value) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.HangupPKByProtoReq.tagRoomId)
}
void HangupPKByProtoReq::set_tagroomid(const char* value, size_t size) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.HangupPKByProtoReq.tagRoomId)
}
::std::string* HangupPKByProtoReq::mutable_tagroomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoReq.tagRoomId)
  return tagroomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* HangupPKByProtoReq::release_tagroomid() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoReq.tagRoomId)
  
  return tagroomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void HangupPKByProtoReq::set_allocated_tagroomid(::std::string* tagroomid) {
  if (tagroomid != NULL) {
    
  } else {
    
  }
  tagroomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), tagroomid);
  // @@protoc_insertion_point(field_set_allocated:proto.HangupPKByProtoReq.tagRoomId)
}

// optional string myRoomId = 3;
void HangupPKByProtoReq::clear_myroomid() {
  myroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& HangupPKByProtoReq::myroomid() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.myRoomId)
  return myroomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void HangupPKByProtoReq::set_myroomid(const ::std::string& value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.myRoomId)
}
void HangupPKByProtoReq::set_myroomid(const char* value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.HangupPKByProtoReq.myRoomId)
}
void HangupPKByProtoReq::set_myroomid(const char* value, size_t size) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.HangupPKByProtoReq.myRoomId)
}
::std::string* HangupPKByProtoReq::mutable_myroomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoReq.myRoomId)
  return myroomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* HangupPKByProtoReq::release_myroomid() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoReq.myRoomId)
  
  return myroomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void HangupPKByProtoReq::set_allocated_myroomid(::std::string* myroomid) {
  if (myroomid != NULL) {
    
  } else {
    
  }
  myroomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), myroomid);
  // @@protoc_insertion_point(field_set_allocated:proto.HangupPKByProtoReq.myRoomId)
}

// optional string tagUserId = 4;
void HangupPKByProtoReq::clear_taguserid() {
  taguserid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& HangupPKByProtoReq::taguserid() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.tagUserId)
  return taguserid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void HangupPKByProtoReq::set_taguserid(const ::std::string& value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.tagUserId)
}
void HangupPKByProtoReq::set_taguserid(const char* value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.HangupPKByProtoReq.tagUserId)
}
void HangupPKByProtoReq::set_taguserid(const char* value, size_t size) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.HangupPKByProtoReq.tagUserId)
}
::std::string* HangupPKByProtoReq::mutable_taguserid() {
  
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoReq.tagUserId)
  return taguserid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* HangupPKByProtoReq::release_taguserid() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoReq.tagUserId)
  
  return taguserid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void HangupPKByProtoReq::set_allocated_taguserid(::std::string* taguserid) {
  if (taguserid != NULL) {
    
  } else {
    
  }
  taguserid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), taguserid);
  // @@protoc_insertion_point(field_set_allocated:proto.HangupPKByProtoReq.tagUserId)
}

// optional int32 type = 5;
void HangupPKByProtoReq::clear_type() {
  type_ = 0;
}
::google::protobuf::int32 HangupPKByProtoReq::type() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.type)
  return type_;
}
void HangupPKByProtoReq::set_type(::google::protobuf::int32 value) {
  
  type_ = value;
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.type)
}

inline const HangupPKByProtoReq* HangupPKByProtoReq::internal_default_instance() {
  return &HangupPKByProtoReq_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int HangupPKByProtoRes::kMsgFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

HangupPKByProtoRes::HangupPKByProtoRes()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_HangupPKByProto_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:proto.HangupPKByProtoRes)
}

void HangupPKByProtoRes::InitAsDefaultInstance() {
  msg_ = const_cast< ::proto::ReturnMsg*>(
      ::proto::ReturnMsg::internal_default_instance());
}

HangupPKByProtoRes::HangupPKByProtoRes(const HangupPKByProtoRes& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:proto.HangupPKByProtoRes)
}

void HangupPKByProtoRes::SharedCtor() {
  msg_ = NULL;
  _cached_size_ = 0;
}

HangupPKByProtoRes::~HangupPKByProtoRes() {
  // @@protoc_insertion_point(destructor:proto.HangupPKByProtoRes)
  SharedDtor();
}

void HangupPKByProtoRes::SharedDtor() {
  if (this != &HangupPKByProtoRes_default_instance_.get()) {
    delete msg_;
  }
}

void HangupPKByProtoRes::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* HangupPKByProtoRes::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return HangupPKByProtoRes_descriptor_;
}

const HangupPKByProtoRes& HangupPKByProtoRes::default_instance() {
  protobuf_InitDefaults_HangupPKByProto_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<HangupPKByProtoRes> HangupPKByProtoRes_default_instance_;

HangupPKByProtoRes* HangupPKByProtoRes::New(::google::protobuf::Arena* arena) const {
  HangupPKByProtoRes* n = new HangupPKByProtoRes;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void HangupPKByProtoRes::Clear() {
// @@protoc_insertion_point(message_clear_start:proto.HangupPKByProtoRes)
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}

bool HangupPKByProtoRes::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:proto.HangupPKByProtoRes)
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
  // @@protoc_insertion_point(parse_success:proto.HangupPKByProtoRes)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:proto.HangupPKByProtoRes)
  return false;
#undef DO_
}

void HangupPKByProtoRes::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:proto.HangupPKByProtoRes)
  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    ::google::protobuf::internal::WireFormatLite::WriteMessageMaybeToArray(
      1, *this->msg_, output);
  }

  // @@protoc_insertion_point(serialize_end:proto.HangupPKByProtoRes)
}

::google::protobuf::uint8* HangupPKByProtoRes::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:proto.HangupPKByProtoRes)
  // optional .proto.ReturnMsg msg = 1;
  if (this->has_msg()) {
    target = ::google::protobuf::internal::WireFormatLite::
      InternalWriteMessageNoVirtualToArray(
        1, *this->msg_, false, target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:proto.HangupPKByProtoRes)
  return target;
}

size_t HangupPKByProtoRes::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:proto.HangupPKByProtoRes)
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

void HangupPKByProtoRes::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:proto.HangupPKByProtoRes)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const HangupPKByProtoRes* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const HangupPKByProtoRes>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:proto.HangupPKByProtoRes)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:proto.HangupPKByProtoRes)
    UnsafeMergeFrom(*source);
  }
}

void HangupPKByProtoRes::MergeFrom(const HangupPKByProtoRes& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:proto.HangupPKByProtoRes)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void HangupPKByProtoRes::UnsafeMergeFrom(const HangupPKByProtoRes& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.has_msg()) {
    mutable_msg()->::proto::ReturnMsg::MergeFrom(from.msg());
  }
}

void HangupPKByProtoRes::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:proto.HangupPKByProtoRes)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void HangupPKByProtoRes::CopyFrom(const HangupPKByProtoRes& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:proto.HangupPKByProtoRes)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool HangupPKByProtoRes::IsInitialized() const {

  return true;
}

void HangupPKByProtoRes::Swap(HangupPKByProtoRes* other) {
  if (other == this) return;
  InternalSwap(other);
}
void HangupPKByProtoRes::InternalSwap(HangupPKByProtoRes* other) {
  std::swap(msg_, other->msg_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata HangupPKByProtoRes::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = HangupPKByProtoRes_descriptor_;
  metadata.reflection = HangupPKByProtoRes_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// HangupPKByProtoRes

// optional .proto.ReturnMsg msg = 1;
bool HangupPKByProtoRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
void HangupPKByProtoRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
const ::proto::ReturnMsg& HangupPKByProtoRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
::proto::ReturnMsg* HangupPKByProtoRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoRes.msg)
  return msg_;
}
::proto::ReturnMsg* HangupPKByProtoRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
void HangupPKByProtoRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.HangupPKByProtoRes.msg)
}

inline const HangupPKByProtoRes* HangupPKByProtoRes::internal_default_instance() {
  return &HangupPKByProtoRes_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)