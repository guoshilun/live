// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CancelPKByProto.proto

#ifndef PROTOBUF_CancelPKByProto_2eproto__INCLUDED
#define PROTOBUF_CancelPKByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_CancelPKByProto_2eproto();
void protobuf_InitDefaults_CancelPKByProto_2eproto();
void protobuf_AssignDesc_CancelPKByProto_2eproto();
void protobuf_ShutdownFile_CancelPKByProto_2eproto();

class CancelPKByProtoReq;
class CancelPKByProtoRes;

// ===================================================================

class CancelPKByProtoReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.CancelPKByProtoReq) */ {
 public:
  CancelPKByProtoReq();
  virtual ~CancelPKByProtoReq();

  CancelPKByProtoReq(const CancelPKByProtoReq& from);

  inline CancelPKByProtoReq& operator=(const CancelPKByProtoReq& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const CancelPKByProtoReq& default_instance();

  static const CancelPKByProtoReq* internal_default_instance();

  void Swap(CancelPKByProtoReq* other);

  // implements Message ----------------------------------------------

  inline CancelPKByProtoReq* New() const { return New(NULL); }

  CancelPKByProtoReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const CancelPKByProtoReq& from);
  void MergeFrom(const CancelPKByProtoReq& from);
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
  void InternalSwap(CancelPKByProtoReq* other);
  void UnsafeMergeFrom(const CancelPKByProtoReq& from);
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

  // @@protoc_insertion_point(class_scope:proto.CancelPKByProtoReq)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr pkid_;
  ::google::protobuf::internal::ArenaStringPtr tagroomid_;
  ::google::protobuf::internal::ArenaStringPtr myroomid_;
  ::google::protobuf::internal::ArenaStringPtr taguserid_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_CancelPKByProto_2eproto_impl();
  friend void  protobuf_AddDesc_CancelPKByProto_2eproto_impl();
  friend void protobuf_AssignDesc_CancelPKByProto_2eproto();
  friend void protobuf_ShutdownFile_CancelPKByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<CancelPKByProtoReq> CancelPKByProtoReq_default_instance_;

// -------------------------------------------------------------------

class CancelPKByProtoRes : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.CancelPKByProtoRes) */ {
 public:
  CancelPKByProtoRes();
  virtual ~CancelPKByProtoRes();

  CancelPKByProtoRes(const CancelPKByProtoRes& from);

  inline CancelPKByProtoRes& operator=(const CancelPKByProtoRes& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const CancelPKByProtoRes& default_instance();

  static const CancelPKByProtoRes* internal_default_instance();

  void Swap(CancelPKByProtoRes* other);

  // implements Message ----------------------------------------------

  inline CancelPKByProtoRes* New() const { return New(NULL); }

  CancelPKByProtoRes* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const CancelPKByProtoRes& from);
  void MergeFrom(const CancelPKByProtoRes& from);
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
  void InternalSwap(CancelPKByProtoRes* other);
  void UnsafeMergeFrom(const CancelPKByProtoRes& from);
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

  // @@protoc_insertion_point(class_scope:proto.CancelPKByProtoRes)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::proto::ReturnMsg* msg_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_CancelPKByProto_2eproto_impl();
  friend void  protobuf_AddDesc_CancelPKByProto_2eproto_impl();
  friend void protobuf_AssignDesc_CancelPKByProto_2eproto();
  friend void protobuf_ShutdownFile_CancelPKByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<CancelPKByProtoRes> CancelPKByProtoRes_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// CancelPKByProtoReq

// optional string pkId = 1;
inline void CancelPKByProtoReq::clear_pkid() {
  pkid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& CancelPKByProtoReq::pkid() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoReq.pkId)
  return pkid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void CancelPKByProtoReq::set_pkid(const ::std::string& value) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.CancelPKByProtoReq.pkId)
}
inline void CancelPKByProtoReq::set_pkid(const char* value) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.CancelPKByProtoReq.pkId)
}
inline void CancelPKByProtoReq::set_pkid(const char* value, size_t size) {
  
  pkid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.CancelPKByProtoReq.pkId)
}
inline ::std::string* CancelPKByProtoReq::mutable_pkid() {
  
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoReq.pkId)
  return pkid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* CancelPKByProtoReq::release_pkid() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoReq.pkId)
  
  return pkid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void CancelPKByProtoReq::set_allocated_pkid(::std::string* pkid) {
  if (pkid != NULL) {
    
  } else {
    
  }
  pkid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), pkid);
  // @@protoc_insertion_point(field_set_allocated:proto.CancelPKByProtoReq.pkId)
}

// optional string tagRoomId = 2;
inline void CancelPKByProtoReq::clear_tagroomid() {
  tagroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& CancelPKByProtoReq::tagroomid() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoReq.tagRoomId)
  return tagroomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void CancelPKByProtoReq::set_tagroomid(const ::std::string& value) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.CancelPKByProtoReq.tagRoomId)
}
inline void CancelPKByProtoReq::set_tagroomid(const char* value) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.CancelPKByProtoReq.tagRoomId)
}
inline void CancelPKByProtoReq::set_tagroomid(const char* value, size_t size) {
  
  tagroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.CancelPKByProtoReq.tagRoomId)
}
inline ::std::string* CancelPKByProtoReq::mutable_tagroomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoReq.tagRoomId)
  return tagroomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* CancelPKByProtoReq::release_tagroomid() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoReq.tagRoomId)
  
  return tagroomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void CancelPKByProtoReq::set_allocated_tagroomid(::std::string* tagroomid) {
  if (tagroomid != NULL) {
    
  } else {
    
  }
  tagroomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), tagroomid);
  // @@protoc_insertion_point(field_set_allocated:proto.CancelPKByProtoReq.tagRoomId)
}

// optional string myRoomId = 3;
inline void CancelPKByProtoReq::clear_myroomid() {
  myroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& CancelPKByProtoReq::myroomid() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoReq.myRoomId)
  return myroomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void CancelPKByProtoReq::set_myroomid(const ::std::string& value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.CancelPKByProtoReq.myRoomId)
}
inline void CancelPKByProtoReq::set_myroomid(const char* value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.CancelPKByProtoReq.myRoomId)
}
inline void CancelPKByProtoReq::set_myroomid(const char* value, size_t size) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.CancelPKByProtoReq.myRoomId)
}
inline ::std::string* CancelPKByProtoReq::mutable_myroomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoReq.myRoomId)
  return myroomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* CancelPKByProtoReq::release_myroomid() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoReq.myRoomId)
  
  return myroomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void CancelPKByProtoReq::set_allocated_myroomid(::std::string* myroomid) {
  if (myroomid != NULL) {
    
  } else {
    
  }
  myroomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), myroomid);
  // @@protoc_insertion_point(field_set_allocated:proto.CancelPKByProtoReq.myRoomId)
}

// optional string tagUserId = 4;
inline void CancelPKByProtoReq::clear_taguserid() {
  taguserid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& CancelPKByProtoReq::taguserid() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoReq.tagUserId)
  return taguserid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void CancelPKByProtoReq::set_taguserid(const ::std::string& value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.CancelPKByProtoReq.tagUserId)
}
inline void CancelPKByProtoReq::set_taguserid(const char* value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.CancelPKByProtoReq.tagUserId)
}
inline void CancelPKByProtoReq::set_taguserid(const char* value, size_t size) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.CancelPKByProtoReq.tagUserId)
}
inline ::std::string* CancelPKByProtoReq::mutable_taguserid() {
  
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoReq.tagUserId)
  return taguserid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* CancelPKByProtoReq::release_taguserid() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoReq.tagUserId)
  
  return taguserid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void CancelPKByProtoReq::set_allocated_taguserid(::std::string* taguserid) {
  if (taguserid != NULL) {
    
  } else {
    
  }
  taguserid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), taguserid);
  // @@protoc_insertion_point(field_set_allocated:proto.CancelPKByProtoReq.tagUserId)
}

inline const CancelPKByProtoReq* CancelPKByProtoReq::internal_default_instance() {
  return &CancelPKByProtoReq_default_instance_.get();
}
// -------------------------------------------------------------------

// CancelPKByProtoRes

// optional .proto.ReturnMsg msg = 1;
inline bool CancelPKByProtoRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void CancelPKByProtoRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::proto::ReturnMsg& CancelPKByProtoRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.CancelPKByProtoRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
inline ::proto::ReturnMsg* CancelPKByProtoRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.CancelPKByProtoRes.msg)
  return msg_;
}
inline ::proto::ReturnMsg* CancelPKByProtoRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.CancelPKByProtoRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void CancelPKByProtoRes::set_allocated_msg(::proto::ReturnMsg* msg) {
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
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_CancelPKByProto_2eproto__INCLUDED