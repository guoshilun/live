// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AnswerByProto.proto

#ifndef PROTOBUF_AnswerByProto_2eproto__INCLUDED
#define PROTOBUF_AnswerByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_AnswerByProto_2eproto();
void protobuf_InitDefaults_AnswerByProto_2eproto();
void protobuf_AssignDesc_AnswerByProto_2eproto();
void protobuf_ShutdownFile_AnswerByProto_2eproto();

class AnswerByProtoReq;
class AnswerByProtoRes;

// ===================================================================

class AnswerByProtoReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.AnswerByProtoReq) */ {
 public:
  AnswerByProtoReq();
  virtual ~AnswerByProtoReq();

  AnswerByProtoReq(const AnswerByProtoReq& from);

  inline AnswerByProtoReq& operator=(const AnswerByProtoReq& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const AnswerByProtoReq& default_instance();

  static const AnswerByProtoReq* internal_default_instance();

  void Swap(AnswerByProtoReq* other);

  // implements Message ----------------------------------------------

  inline AnswerByProtoReq* New() const { return New(NULL); }

  AnswerByProtoReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const AnswerByProtoReq& from);
  void MergeFrom(const AnswerByProtoReq& from);
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
  void InternalSwap(AnswerByProtoReq* other);
  void UnsafeMergeFrom(const AnswerByProtoReq& from);
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

  // optional string userId = 1;
  void clear_userid();
  static const int kUserIdFieldNumber = 1;
  const ::std::string& userid() const;
  void set_userid(const ::std::string& value);
  void set_userid(const char* value);
  void set_userid(const char* value, size_t size);
  ::std::string* mutable_userid();
  ::std::string* release_userid();
  void set_allocated_userid(::std::string* userid);

  // optional int32 type = 2;
  void clear_type();
  static const int kTypeFieldNumber = 2;
  ::google::protobuf::int32 type() const;
  void set_type(::google::protobuf::int32 value);

  // optional string streamId = 3;
  void clear_streamid();
  static const int kStreamIdFieldNumber = 3;
  const ::std::string& streamid() const;
  void set_streamid(const ::std::string& value);
  void set_streamid(const char* value);
  void set_streamid(const char* value, size_t size);
  ::std::string* mutable_streamid();
  ::std::string* release_streamid();
  void set_allocated_streamid(::std::string* streamid);

  // optional string roomId = 4;
  void clear_roomid();
  static const int kRoomIdFieldNumber = 4;
  const ::std::string& roomid() const;
  void set_roomid(const ::std::string& value);
  void set_roomid(const char* value);
  void set_roomid(const char* value, size_t size);
  ::std::string* mutable_roomid();
  ::std::string* release_roomid();
  void set_allocated_roomid(::std::string* roomid);

  // @@protoc_insertion_point(class_scope:proto.AnswerByProtoReq)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr userid_;
  ::google::protobuf::internal::ArenaStringPtr streamid_;
  ::google::protobuf::internal::ArenaStringPtr roomid_;
  ::google::protobuf::int32 type_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_AnswerByProto_2eproto_impl();
  friend void  protobuf_AddDesc_AnswerByProto_2eproto_impl();
  friend void protobuf_AssignDesc_AnswerByProto_2eproto();
  friend void protobuf_ShutdownFile_AnswerByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<AnswerByProtoReq> AnswerByProtoReq_default_instance_;

// -------------------------------------------------------------------

class AnswerByProtoRes : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.AnswerByProtoRes) */ {
 public:
  AnswerByProtoRes();
  virtual ~AnswerByProtoRes();

  AnswerByProtoRes(const AnswerByProtoRes& from);

  inline AnswerByProtoRes& operator=(const AnswerByProtoRes& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const AnswerByProtoRes& default_instance();

  static const AnswerByProtoRes* internal_default_instance();

  void Swap(AnswerByProtoRes* other);

  // implements Message ----------------------------------------------

  inline AnswerByProtoRes* New() const { return New(NULL); }

  AnswerByProtoRes* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const AnswerByProtoRes& from);
  void MergeFrom(const AnswerByProtoRes& from);
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
  void InternalSwap(AnswerByProtoRes* other);
  void UnsafeMergeFrom(const AnswerByProtoRes& from);
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

  // optional string userId = 2;
  void clear_userid();
  static const int kUserIdFieldNumber = 2;
  const ::std::string& userid() const;
  void set_userid(const ::std::string& value);
  void set_userid(const char* value);
  void set_userid(const char* value, size_t size);
  ::std::string* mutable_userid();
  ::std::string* release_userid();
  void set_allocated_userid(::std::string* userid);

  // @@protoc_insertion_point(class_scope:proto.AnswerByProtoRes)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr userid_;
  ::proto::ReturnMsg* msg_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_AnswerByProto_2eproto_impl();
  friend void  protobuf_AddDesc_AnswerByProto_2eproto_impl();
  friend void protobuf_AssignDesc_AnswerByProto_2eproto();
  friend void protobuf_ShutdownFile_AnswerByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<AnswerByProtoRes> AnswerByProtoRes_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// AnswerByProtoReq

// optional string userId = 1;
inline void AnswerByProtoReq::clear_userid() {
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& AnswerByProtoReq::userid() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoReq.userId)
  return userid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void AnswerByProtoReq::set_userid(const ::std::string& value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoReq.userId)
}
inline void AnswerByProtoReq::set_userid(const char* value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.AnswerByProtoReq.userId)
}
inline void AnswerByProtoReq::set_userid(const char* value, size_t size) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.AnswerByProtoReq.userId)
}
inline ::std::string* AnswerByProtoReq::mutable_userid() {
  
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoReq.userId)
  return userid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* AnswerByProtoReq::release_userid() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoReq.userId)
  
  return userid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void AnswerByProtoReq::set_allocated_userid(::std::string* userid) {
  if (userid != NULL) {
    
  } else {
    
  }
  userid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userid);
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoReq.userId)
}

// optional int32 type = 2;
inline void AnswerByProtoReq::clear_type() {
  type_ = 0;
}
inline ::google::protobuf::int32 AnswerByProtoReq::type() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoReq.type)
  return type_;
}
inline void AnswerByProtoReq::set_type(::google::protobuf::int32 value) {
  
  type_ = value;
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoReq.type)
}

// optional string streamId = 3;
inline void AnswerByProtoReq::clear_streamid() {
  streamid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& AnswerByProtoReq::streamid() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoReq.streamId)
  return streamid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void AnswerByProtoReq::set_streamid(const ::std::string& value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoReq.streamId)
}
inline void AnswerByProtoReq::set_streamid(const char* value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.AnswerByProtoReq.streamId)
}
inline void AnswerByProtoReq::set_streamid(const char* value, size_t size) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.AnswerByProtoReq.streamId)
}
inline ::std::string* AnswerByProtoReq::mutable_streamid() {
  
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoReq.streamId)
  return streamid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* AnswerByProtoReq::release_streamid() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoReq.streamId)
  
  return streamid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void AnswerByProtoReq::set_allocated_streamid(::std::string* streamid) {
  if (streamid != NULL) {
    
  } else {
    
  }
  streamid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), streamid);
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoReq.streamId)
}

// optional string roomId = 4;
inline void AnswerByProtoReq::clear_roomid() {
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& AnswerByProtoReq::roomid() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoReq.roomId)
  return roomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void AnswerByProtoReq::set_roomid(const ::std::string& value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoReq.roomId)
}
inline void AnswerByProtoReq::set_roomid(const char* value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.AnswerByProtoReq.roomId)
}
inline void AnswerByProtoReq::set_roomid(const char* value, size_t size) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.AnswerByProtoReq.roomId)
}
inline ::std::string* AnswerByProtoReq::mutable_roomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoReq.roomId)
  return roomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* AnswerByProtoReq::release_roomid() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoReq.roomId)
  
  return roomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void AnswerByProtoReq::set_allocated_roomid(::std::string* roomid) {
  if (roomid != NULL) {
    
  } else {
    
  }
  roomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), roomid);
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoReq.roomId)
}

inline const AnswerByProtoReq* AnswerByProtoReq::internal_default_instance() {
  return &AnswerByProtoReq_default_instance_.get();
}
// -------------------------------------------------------------------

// AnswerByProtoRes

// optional .proto.ReturnMsg msg = 1;
inline bool AnswerByProtoRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void AnswerByProtoRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::proto::ReturnMsg& AnswerByProtoRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
inline ::proto::ReturnMsg* AnswerByProtoRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoRes.msg)
  return msg_;
}
inline ::proto::ReturnMsg* AnswerByProtoRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void AnswerByProtoRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoRes.msg)
}

// optional string userId = 2;
inline void AnswerByProtoRes::clear_userid() {
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& AnswerByProtoRes::userid() const {
  // @@protoc_insertion_point(field_get:proto.AnswerByProtoRes.userId)
  return userid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void AnswerByProtoRes::set_userid(const ::std::string& value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.AnswerByProtoRes.userId)
}
inline void AnswerByProtoRes::set_userid(const char* value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.AnswerByProtoRes.userId)
}
inline void AnswerByProtoRes::set_userid(const char* value, size_t size) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.AnswerByProtoRes.userId)
}
inline ::std::string* AnswerByProtoRes::mutable_userid() {
  
  // @@protoc_insertion_point(field_mutable:proto.AnswerByProtoRes.userId)
  return userid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* AnswerByProtoRes::release_userid() {
  // @@protoc_insertion_point(field_release:proto.AnswerByProtoRes.userId)
  
  return userid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void AnswerByProtoRes::set_allocated_userid(::std::string* userid) {
  if (userid != NULL) {
    
  } else {
    
  }
  userid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userid);
  // @@protoc_insertion_point(field_set_allocated:proto.AnswerByProtoRes.userId)
}

inline const AnswerByProtoRes* AnswerByProtoRes::internal_default_instance() {
  return &AnswerByProtoRes_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_AnswerByProto_2eproto__INCLUDED
