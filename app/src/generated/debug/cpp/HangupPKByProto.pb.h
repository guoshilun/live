// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: HangupPKByProto.proto

#ifndef PROTOBUF_HangupPKByProto_2eproto__INCLUDED
#define PROTOBUF_HangupPKByProto_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 3001000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 3001000 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/metadata.h>
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/unknown_field_set.h>
#include "Entity.pb.h"
// @@protoc_insertion_point(includes)

namespace proto {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_HangupPKByProto_2eproto();
void protobuf_InitDefaults_HangupPKByProto_2eproto();
void protobuf_AssignDesc_HangupPKByProto_2eproto();
void protobuf_ShutdownFile_HangupPKByProto_2eproto();

class HangupPKByProtoReq;
class HangupPKByProtoRes;

// ===================================================================

class HangupPKByProtoReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.HangupPKByProtoReq) */ {
 public:
  HangupPKByProtoReq();
  virtual ~HangupPKByProtoReq();

  HangupPKByProtoReq(const HangupPKByProtoReq& from);

  inline HangupPKByProtoReq& operator=(const HangupPKByProtoReq& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const HangupPKByProtoReq& default_instance();

  static const HangupPKByProtoReq* internal_default_instance();

  void Swap(HangupPKByProtoReq* other);

  // implements Message ----------------------------------------------

  inline HangupPKByProtoReq* New() const { return New(NULL); }

  HangupPKByProtoReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const HangupPKByProtoReq& from);
  void MergeFrom(const HangupPKByProtoReq& from);
  void Clear();
  bool IsInitialized() const;

  size_t ByteSizeLong() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const {
    return InternalSerializeWithCachedSizesToArray(false, output);
  }
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(HangupPKByProtoReq* other);
  void UnsafeMergeFrom(const HangupPKByProtoReq& from);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return _internal_metadata_.arena();
  }
  inline void* MaybeArenaPtr() const {
    return _internal_metadata_.raw_arena_ptr();
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // optional string pkId = 1;
  void clear_pkid();
  static const int kPkIdFieldNumber = 1;
  const ::std::string& pkid() const;
  void set_pkid(const ::std::string& value);
  void set_pkid(const char* value);
  void set_pkid(const char* value, size_t size);
  ::std::string* mutable_pkid();
  ::std::string* release_pkid();
  void set_allocated_pkid(::std::string* pkid);

  // optional string tagRoomId = 2;
  void clear_tagroomid();
  static const int kTagRoomIdFieldNumber = 2;
  const ::std::string& tagroomid() const;
  void set_tagroomid(const ::std::string& value);
  void set_tagroomid(const char* value);
  void set_tagroomid(const char* value, size_t size);
  ::std::string* mutable_tagroomid();
  ::std::string* release_tagroomid();
  void set_allocated_tagroomid(::std::string* tagroomid);

  // optional string myRoomId = 3;
  void clear_myroomid();
  static const int kMyRoomIdFieldNumber = 3;
  const ::std::string& myroomid() const;
  void set_myroomid(const ::std::string& value);
  void set_myroomid(const char* value);
  void set_myroomid(const char* value, size_t size);
  ::std::string* mutable_myroomid();
  ::std::string* release_myroomid();
  void set_allocated_myroomid(::std::string* myroomid);

  // optional string tagUserId = 4;
  void clear_taguserid();
  static const int kTagUserIdFieldNumber = 4;
  const ::std::string& taguserid() const;
  void set_taguserid(const ::std::string& value);
  void set_taguserid(const char* value);
  void set_taguserid(const char* value, size_t size);
  ::std::string* mutable_taguserid();
  ::std::string* release_taguserid();
  void set_allocated_taguserid(::std::string* taguserid);

  // optional int32 type = 5;
  void clear_type();
  static const int kTypeFieldNumber = 5;
  ::google::protobuf::int32 type() const;
  void set_type(::google::protobuf::int32 value);

  // @@protoc_insertion_point(class_scope:proto.HangupPKByProtoReq)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr pkid_;
  ::google::protobuf::internal::ArenaStringPtr tagroomid_;
  ::google::protobuf::internal::ArenaStringPtr myroomid_;
  ::google::protobuf::internal::ArenaStringPtr taguserid_;
  ::google::protobuf::int32 type_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_HangupPKByProto_2eproto_impl();
  friend void  protobuf_AddDesc_HangupPKByProto_2eproto_impl();
  friend void protobuf_AssignDesc_HangupPKByProto_2eproto();
  friend void protobuf_ShutdownFile_HangupPKByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<HangupPKByProtoReq> HangupPKByProtoReq_default_instance_;

// -------------------------------------------------------------------

class HangupPKByProtoRes : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.HangupPKByProtoRes) */ {
 public:
  HangupPKByProtoRes();
  virtual ~HangupPKByProtoRes();

  HangupPKByProtoRes(const HangupPKByProtoRes& from);

  inline HangupPKByProtoRes& operator=(const HangupPKByProtoRes& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const HangupPKByProtoRes& default_instance();

  static const HangupPKByProtoRes* internal_default_instance();

  void Swap(HangupPKByProtoRes* other);

  // implements Message ----------------------------------------------

  inline HangupPKByProtoRes* New() const { return New(NULL); }

  HangupPKByProtoRes* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const HangupPKByProtoRes& from);
  void MergeFrom(const HangupPKByProtoRes& from);
  void Clear();
  bool IsInitialized() const;

  size_t ByteSizeLong() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const {
    return InternalSerializeWithCachedSizesToArray(false, output);
  }
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(HangupPKByProtoRes* other);
  void UnsafeMergeFrom(const HangupPKByProtoRes& from);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return _internal_metadata_.arena();
  }
  inline void* MaybeArenaPtr() const {
    return _internal_metadata_.raw_arena_ptr();
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // optional .proto.ReturnMsg msg = 1;
  bool has_msg() const;
  void clear_msg();
  static const int kMsgFieldNumber = 1;
  const ::proto::ReturnMsg& msg() const;
  ::proto::ReturnMsg* mutable_msg();
  ::proto::ReturnMsg* release_msg();
  void set_allocated_msg(::proto::ReturnMsg* msg);

  // @@protoc_insertion_point(class_scope:proto.HangupPKByProtoRes)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::proto::ReturnMsg* msg_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_HangupPKByProto_2eproto_impl();
  friend void  protobuf_AddDesc_HangupPKByProto_2eproto_impl();
  friend void protobuf_AssignDesc_HangupPKByProto_2eproto();
  friend void protobuf_ShutdownFile_HangupPKByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<HangupPKByProtoRes> HangupPKByProtoRes_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// HangupPKByProtoReq

// optional string pkId = 1;
inline void HangupPKByProtoReq::clear_pkid() {
  pkid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& HangupPKByProtoReq::pkid() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.pkId)
  return pkid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void HangupPKByProtoReq::set_pkid(const ::std::string& value) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.pkId)
}
inline void HangupPKByProtoReq::set_pkid(const char* value) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.HangupPKByProtoReq.pkId)
}
inline void HangupPKByProtoReq::set_pkid(const char* value, size_t size) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.HangupPKByProtoReq.pkId)
}
inline ::std::string* HangupPKByProtoReq::mutable_pkid() {
  
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoReq.pkId)
  return pkid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* HangupPKByProtoReq::release_pkid() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoReq.pkId)
  
  return pkid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void HangupPKByProtoReq::set_allocated_pkid(::std::string* pkid) {
  if (pkid != NULL) {
    
  } else {
    
  }
  pkid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), pkid);
  // @@protoc_insertion_point(field_set_allocated:proto.HangupPKByProtoReq.pkId)
}

// optional string tagRoomId = 2;
inline void HangupPKByProtoReq::clear_tagroomid() {
  tagroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& HangupPKByProtoReq::tagroomid() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.tagRoomId)
  return tagroomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void HangupPKByProtoReq::set_tagroomid(const ::std::string& value) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.tagRoomId)
}
inline void HangupPKByProtoReq::set_tagroomid(const char* value) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.HangupPKByProtoReq.tagRoomId)
}
inline void HangupPKByProtoReq::set_tagroomid(const char* value, size_t size) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.HangupPKByProtoReq.tagRoomId)
}
inline ::std::string* HangupPKByProtoReq::mutable_tagroomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoReq.tagRoomId)
  return tagroomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* HangupPKByProtoReq::release_tagroomid() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoReq.tagRoomId)
  
  return tagroomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void HangupPKByProtoReq::set_allocated_tagroomid(::std::string* tagroomid) {
  if (tagroomid != NULL) {
    
  } else {
    
  }
  tagroomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), tagroomid);
  // @@protoc_insertion_point(field_set_allocated:proto.HangupPKByProtoReq.tagRoomId)
}

// optional string myRoomId = 3;
inline void HangupPKByProtoReq::clear_myroomid() {
  myroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& HangupPKByProtoReq::myroomid() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.myRoomId)
  return myroomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void HangupPKByProtoReq::set_myroomid(const ::std::string& value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.myRoomId)
}
inline void HangupPKByProtoReq::set_myroomid(const char* value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.HangupPKByProtoReq.myRoomId)
}
inline void HangupPKByProtoReq::set_myroomid(const char* value, size_t size) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.HangupPKByProtoReq.myRoomId)
}
inline ::std::string* HangupPKByProtoReq::mutable_myroomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoReq.myRoomId)
  return myroomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* HangupPKByProtoReq::release_myroomid() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoReq.myRoomId)
  
  return myroomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void HangupPKByProtoReq::set_allocated_myroomid(::std::string* myroomid) {
  if (myroomid != NULL) {
    
  } else {
    
  }
  myroomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), myroomid);
  // @@protoc_insertion_point(field_set_allocated:proto.HangupPKByProtoReq.myRoomId)
}

// optional string tagUserId = 4;
inline void HangupPKByProtoReq::clear_taguserid() {
  taguserid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& HangupPKByProtoReq::taguserid() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.tagUserId)
  return taguserid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void HangupPKByProtoReq::set_taguserid(const ::std::string& value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.tagUserId)
}
inline void HangupPKByProtoReq::set_taguserid(const char* value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.HangupPKByProtoReq.tagUserId)
}
inline void HangupPKByProtoReq::set_taguserid(const char* value, size_t size) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.HangupPKByProtoReq.tagUserId)
}
inline ::std::string* HangupPKByProtoReq::mutable_taguserid() {
  
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoReq.tagUserId)
  return taguserid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* HangupPKByProtoReq::release_taguserid() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoReq.tagUserId)
  
  return taguserid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void HangupPKByProtoReq::set_allocated_taguserid(::std::string* taguserid) {
  if (taguserid != NULL) {
    
  } else {
    
  }
  taguserid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), taguserid);
  // @@protoc_insertion_point(field_set_allocated:proto.HangupPKByProtoReq.tagUserId)
}

// optional int32 type = 5;
inline void HangupPKByProtoReq::clear_type() {
  type_ = 0;
}
inline ::google::protobuf::int32 HangupPKByProtoReq::type() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoReq.type)
  return type_;
}
inline void HangupPKByProtoReq::set_type(::google::protobuf::int32 value) {
  
  type_ = value;
  // @@protoc_insertion_point(field_set:proto.HangupPKByProtoReq.type)
}

inline const HangupPKByProtoReq* HangupPKByProtoReq::internal_default_instance() {
  return &HangupPKByProtoReq_default_instance_.get();
}
// -------------------------------------------------------------------

// HangupPKByProtoRes

// optional .proto.ReturnMsg msg = 1;
inline bool HangupPKByProtoRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void HangupPKByProtoRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::proto::ReturnMsg& HangupPKByProtoRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.HangupPKByProtoRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
inline ::proto::ReturnMsg* HangupPKByProtoRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.HangupPKByProtoRes.msg)
  return msg_;
}
inline ::proto::ReturnMsg* HangupPKByProtoRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.HangupPKByProtoRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void HangupPKByProtoRes::set_allocated_msg(::proto::ReturnMsg* msg) {
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
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_HangupPKByProto_2eproto__INCLUDED
