// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SendPKFriendByProto.proto

#ifndef PROTOBUF_SendPKFriendByProto_2eproto__INCLUDED
#define PROTOBUF_SendPKFriendByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_SendPKFriendByProto_2eproto();
void protobuf_InitDefaults_SendPKFriendByProto_2eproto();
void protobuf_AssignDesc_SendPKFriendByProto_2eproto();
void protobuf_ShutdownFile_SendPKFriendByProto_2eproto();

class SendPKFriendReq;
class SendPKFriendRes;

// ===================================================================

class SendPKFriendReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.SendPKFriendReq) */ {
 public:
  SendPKFriendReq();
  virtual ~SendPKFriendReq();

  SendPKFriendReq(const SendPKFriendReq& from);

  inline SendPKFriendReq& operator=(const SendPKFriendReq& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const SendPKFriendReq& default_instance();

  static const SendPKFriendReq* internal_default_instance();

  void Swap(SendPKFriendReq* other);

  // implements Message ----------------------------------------------

  inline SendPKFriendReq* New() const { return New(NULL); }

  SendPKFriendReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const SendPKFriendReq& from);
  void MergeFrom(const SendPKFriendReq& from);
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
  void InternalSwap(SendPKFriendReq* other);
  void UnsafeMergeFrom(const SendPKFriendReq& from);
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

  // optional string roomId = 1;
  void clear_roomid();
  static const int kRoomIdFieldNumber = 1;
  const ::std::string& roomid() const;
  void set_roomid(const ::std::string& value);
  void set_roomid(const char* value);
  void set_roomid(const char* value, size_t size);
  ::std::string* mutable_roomid();
  ::std::string* release_roomid();
  void set_allocated_roomid(::std::string* roomid);

  // optional string streamId = 2;
  void clear_streamid();
  static const int kStreamIdFieldNumber = 2;
  const ::std::string& streamid() const;
  void set_streamid(const ::std::string& value);
  void set_streamid(const char* value);
  void set_streamid(const char* value, size_t size);
  ::std::string* mutable_streamid();
  ::std::string* release_streamid();
  void set_allocated_streamid(::std::string* streamid);

  // optional string tagUserId = 3;
  void clear_taguserid();
  static const int kTagUserIdFieldNumber = 3;
  const ::std::string& taguserid() const;
  void set_taguserid(const ::std::string& value);
  void set_taguserid(const char* value);
  void set_taguserid(const char* value, size_t size);
  ::std::string* mutable_taguserid();
  ::std::string* release_taguserid();
  void set_allocated_taguserid(::std::string* taguserid);

  // @@protoc_insertion_point(class_scope:proto.SendPKFriendReq)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr roomid_;
  ::google::protobuf::internal::ArenaStringPtr streamid_;
  ::google::protobuf::internal::ArenaStringPtr taguserid_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_SendPKFriendByProto_2eproto_impl();
  friend void  protobuf_AddDesc_SendPKFriendByProto_2eproto_impl();
  friend void protobuf_AssignDesc_SendPKFriendByProto_2eproto();
  friend void protobuf_ShutdownFile_SendPKFriendByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<SendPKFriendReq> SendPKFriendReq_default_instance_;

// -------------------------------------------------------------------

class SendPKFriendRes : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.SendPKFriendRes) */ {
 public:
  SendPKFriendRes();
  virtual ~SendPKFriendRes();

  SendPKFriendRes(const SendPKFriendRes& from);

  inline SendPKFriendRes& operator=(const SendPKFriendRes& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const SendPKFriendRes& default_instance();

  static const SendPKFriendRes* internal_default_instance();

  void Swap(SendPKFriendRes* other);

  // implements Message ----------------------------------------------

  inline SendPKFriendRes* New() const { return New(NULL); }

  SendPKFriendRes* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const SendPKFriendRes& from);
  void MergeFrom(const SendPKFriendRes& from);
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
  void InternalSwap(SendPKFriendRes* other);
  void UnsafeMergeFrom(const SendPKFriendRes& from);
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

  // @@protoc_insertion_point(class_scope:proto.SendPKFriendRes)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::proto::ReturnMsg* msg_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_SendPKFriendByProto_2eproto_impl();
  friend void  protobuf_AddDesc_SendPKFriendByProto_2eproto_impl();
  friend void protobuf_AssignDesc_SendPKFriendByProto_2eproto();
  friend void protobuf_ShutdownFile_SendPKFriendByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<SendPKFriendRes> SendPKFriendRes_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// SendPKFriendReq

// optional string roomId = 1;
inline void SendPKFriendReq::clear_roomid() {
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& SendPKFriendReq::roomid() const {
  // @@protoc_insertion_point(field_get:proto.SendPKFriendReq.roomId)
  return roomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKFriendReq::set_roomid(const ::std::string& value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.SendPKFriendReq.roomId)
}
inline void SendPKFriendReq::set_roomid(const char* value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.SendPKFriendReq.roomId)
}
inline void SendPKFriendReq::set_roomid(const char* value, size_t size) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.SendPKFriendReq.roomId)
}
inline ::std::string* SendPKFriendReq::mutable_roomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.SendPKFriendReq.roomId)
  return roomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* SendPKFriendReq::release_roomid() {
  // @@protoc_insertion_point(field_release:proto.SendPKFriendReq.roomId)
  
  return roomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKFriendReq::set_allocated_roomid(::std::string* roomid) {
  if (roomid != NULL) {
    
  } else {
    
  }
  roomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), roomid);
  // @@protoc_insertion_point(field_set_allocated:proto.SendPKFriendReq.roomId)
}

// optional string streamId = 2;
inline void SendPKFriendReq::clear_streamid() {
  streamid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& SendPKFriendReq::streamid() const {
  // @@protoc_insertion_point(field_get:proto.SendPKFriendReq.streamId)
  return streamid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKFriendReq::set_streamid(const ::std::string& value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.SendPKFriendReq.streamId)
}
inline void SendPKFriendReq::set_streamid(const char* value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.SendPKFriendReq.streamId)
}
inline void SendPKFriendReq::set_streamid(const char* value, size_t size) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.SendPKFriendReq.streamId)
}
inline ::std::string* SendPKFriendReq::mutable_streamid() {
  
  // @@protoc_insertion_point(field_mutable:proto.SendPKFriendReq.streamId)
  return streamid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* SendPKFriendReq::release_streamid() {
  // @@protoc_insertion_point(field_release:proto.SendPKFriendReq.streamId)
  
  return streamid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKFriendReq::set_allocated_streamid(::std::string* streamid) {
  if (streamid != NULL) {
    
  } else {
    
  }
  streamid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), streamid);
  // @@protoc_insertion_point(field_set_allocated:proto.SendPKFriendReq.streamId)
}

// optional string tagUserId = 3;
inline void SendPKFriendReq::clear_taguserid() {
  taguserid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& SendPKFriendReq::taguserid() const {
  // @@protoc_insertion_point(field_get:proto.SendPKFriendReq.tagUserId)
  return taguserid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKFriendReq::set_taguserid(const ::std::string& value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.SendPKFriendReq.tagUserId)
}
inline void SendPKFriendReq::set_taguserid(const char* value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.SendPKFriendReq.tagUserId)
}
inline void SendPKFriendReq::set_taguserid(const char* value, size_t size) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.SendPKFriendReq.tagUserId)
}
inline ::std::string* SendPKFriendReq::mutable_taguserid() {
  
  // @@protoc_insertion_point(field_mutable:proto.SendPKFriendReq.tagUserId)
  return taguserid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* SendPKFriendReq::release_taguserid() {
  // @@protoc_insertion_point(field_release:proto.SendPKFriendReq.tagUserId)
  
  return taguserid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKFriendReq::set_allocated_taguserid(::std::string* taguserid) {
  if (taguserid != NULL) {
    
  } else {
    
  }
  taguserid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), taguserid);
  // @@protoc_insertion_point(field_set_allocated:proto.SendPKFriendReq.tagUserId)
}

inline const SendPKFriendReq* SendPKFriendReq::internal_default_instance() {
  return &SendPKFriendReq_default_instance_.get();
}
// -------------------------------------------------------------------

// SendPKFriendRes

// optional .proto.ReturnMsg msg = 1;
inline bool SendPKFriendRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void SendPKFriendRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::proto::ReturnMsg& SendPKFriendRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.SendPKFriendRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
inline ::proto::ReturnMsg* SendPKFriendRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.SendPKFriendRes.msg)
  return msg_;
}
inline ::proto::ReturnMsg* SendPKFriendRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.SendPKFriendRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void SendPKFriendRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.SendPKFriendRes.msg)
}

inline const SendPKFriendRes* SendPKFriendRes::internal_default_instance() {
  return &SendPKFriendRes_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_SendPKFriendByProto_2eproto__INCLUDED
