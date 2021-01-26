// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BannedByProto.proto

#ifndef PROTOBUF_BannedByProto_2eproto__INCLUDED
#define PROTOBUF_BannedByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_BannedByProto_2eproto();
void protobuf_InitDefaults_BannedByProto_2eproto();
void protobuf_AssignDesc_BannedByProto_2eproto();
void protobuf_ShutdownFile_BannedByProto_2eproto();

class BannedByProtoReq;
class BannedByProtoRes;

// ===================================================================

class BannedByProtoReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.BannedByProtoReq) */ {
 public:
  BannedByProtoReq();
  virtual ~BannedByProtoReq();

  BannedByProtoReq(const BannedByProtoReq& from);

  inline BannedByProtoReq& operator=(const BannedByProtoReq& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const BannedByProtoReq& default_instance();

  static const BannedByProtoReq* internal_default_instance();

  void Swap(BannedByProtoReq* other);

  // implements Message ----------------------------------------------

  inline BannedByProtoReq* New() const { return New(NULL); }

  BannedByProtoReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const BannedByProtoReq& from);
  void MergeFrom(const BannedByProtoReq& from);
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
  void InternalSwap(BannedByProtoReq* other);
  void UnsafeMergeFrom(const BannedByProtoReq& from);
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

  // optional string tagId = 1;
  void clear_tagid();
  static const int kTagIdFieldNumber = 1;
  const ::std::string& tagid() const;
  void set_tagid(const ::std::string& value);
  void set_tagid(const char* value);
  void set_tagid(const char* value, size_t size);
  ::std::string* mutable_tagid();
  ::std::string* release_tagid();
  void set_allocated_tagid(::std::string* tagid);

  // optional int32 time = 2;
  void clear_time();
  static const int kTimeFieldNumber = 2;
  ::google::protobuf::int32 time() const;
  void set_time(::google::protobuf::int32 value);

  // optional string roomId = 3;
  void clear_roomid();
  static const int kRoomIdFieldNumber = 3;
  const ::std::string& roomid() const;
  void set_roomid(const ::std::string& value);
  void set_roomid(const char* value);
  void set_roomid(const char* value, size_t size);
  ::std::string* mutable_roomid();
  ::std::string* release_roomid();
  void set_allocated_roomid(::std::string* roomid);

  // @@protoc_insertion_point(class_scope:proto.BannedByProtoReq)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr tagid_;
  ::google::protobuf::internal::ArenaStringPtr roomid_;
  ::google::protobuf::int32 time_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_BannedByProto_2eproto_impl();
  friend void  protobuf_AddDesc_BannedByProto_2eproto_impl();
  friend void protobuf_AssignDesc_BannedByProto_2eproto();
  friend void protobuf_ShutdownFile_BannedByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<BannedByProtoReq> BannedByProtoReq_default_instance_;

// -------------------------------------------------------------------

class BannedByProtoRes : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.BannedByProtoRes) */ {
 public:
  BannedByProtoRes();
  virtual ~BannedByProtoRes();

  BannedByProtoRes(const BannedByProtoRes& from);

  inline BannedByProtoRes& operator=(const BannedByProtoRes& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const BannedByProtoRes& default_instance();

  static const BannedByProtoRes* internal_default_instance();

  void Swap(BannedByProtoRes* other);

  // implements Message ----------------------------------------------

  inline BannedByProtoRes* New() const { return New(NULL); }

  BannedByProtoRes* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const BannedByProtoRes& from);
  void MergeFrom(const BannedByProtoRes& from);
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
  void InternalSwap(BannedByProtoRes* other);
  void UnsafeMergeFrom(const BannedByProtoRes& from);
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

  // @@protoc_insertion_point(class_scope:proto.BannedByProtoRes)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::proto::ReturnMsg* msg_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_BannedByProto_2eproto_impl();
  friend void  protobuf_AddDesc_BannedByProto_2eproto_impl();
  friend void protobuf_AssignDesc_BannedByProto_2eproto();
  friend void protobuf_ShutdownFile_BannedByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<BannedByProtoRes> BannedByProtoRes_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// BannedByProtoReq

// optional string tagId = 1;
inline void BannedByProtoReq::clear_tagid() {
  tagid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& BannedByProtoReq::tagid() const {
  // @@protoc_insertion_point(field_get:proto.BannedByProtoReq.tagId)
  return tagid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void BannedByProtoReq::set_tagid(const ::std::string& value) {
  
  tagid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.BannedByProtoReq.tagId)
}
inline void BannedByProtoReq::set_tagid(const char* value) {
  
  tagid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.BannedByProtoReq.tagId)
}
inline void BannedByProtoReq::set_tagid(const char* value, size_t size) {
  
  tagid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.BannedByProtoReq.tagId)
}
inline ::std::string* BannedByProtoReq::mutable_tagid() {
  
  // @@protoc_insertion_point(field_mutable:proto.BannedByProtoReq.tagId)
  return tagid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* BannedByProtoReq::release_tagid() {
  // @@protoc_insertion_point(field_release:proto.BannedByProtoReq.tagId)
  
  return tagid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void BannedByProtoReq::set_allocated_tagid(::std::string* tagid) {
  if (tagid != NULL) {
    
  } else {
    
  }
  tagid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), tagid);
  // @@protoc_insertion_point(field_set_allocated:proto.BannedByProtoReq.tagId)
}

// optional int32 time = 2;
inline void BannedByProtoReq::clear_time() {
  time_ = 0;
}
inline ::google::protobuf::int32 BannedByProtoReq::time() const {
  // @@protoc_insertion_point(field_get:proto.BannedByProtoReq.time)
  return time_;
}
inline void BannedByProtoReq::set_time(::google::protobuf::int32 value) {
  
  time_ = value;
  // @@protoc_insertion_point(field_set:proto.BannedByProtoReq.time)
}

// optional string roomId = 3;
inline void BannedByProtoReq::clear_roomid() {
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& BannedByProtoReq::roomid() const {
  // @@protoc_insertion_point(field_get:proto.BannedByProtoReq.roomId)
  return roomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void BannedByProtoReq::set_roomid(const ::std::string& value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.BannedByProtoReq.roomId)
}
inline void BannedByProtoReq::set_roomid(const char* value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.BannedByProtoReq.roomId)
}
inline void BannedByProtoReq::set_roomid(const char* value, size_t size) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.BannedByProtoReq.roomId)
}
inline ::std::string* BannedByProtoReq::mutable_roomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.BannedByProtoReq.roomId)
  return roomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* BannedByProtoReq::release_roomid() {
  // @@protoc_insertion_point(field_release:proto.BannedByProtoReq.roomId)
  
  return roomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void BannedByProtoReq::set_allocated_roomid(::std::string* roomid) {
  if (roomid != NULL) {
    
  } else {
    
  }
  roomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), roomid);
  // @@protoc_insertion_point(field_set_allocated:proto.BannedByProtoReq.roomId)
}

inline const BannedByProtoReq* BannedByProtoReq::internal_default_instance() {
  return &BannedByProtoReq_default_instance_.get();
}
// -------------------------------------------------------------------

// BannedByProtoRes

// optional .proto.ReturnMsg msg = 1;
inline bool BannedByProtoRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void BannedByProtoRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::proto::ReturnMsg& BannedByProtoRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.BannedByProtoRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
inline ::proto::ReturnMsg* BannedByProtoRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.BannedByProtoRes.msg)
  return msg_;
}
inline ::proto::ReturnMsg* BannedByProtoRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.BannedByProtoRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void BannedByProtoRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.BannedByProtoRes.msg)
}

inline const BannedByProtoRes* BannedByProtoRes::internal_default_instance() {
  return &BannedByProtoRes_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_BannedByProto_2eproto__INCLUDED
