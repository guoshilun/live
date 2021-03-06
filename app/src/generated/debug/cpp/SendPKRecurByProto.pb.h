// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SendPKRecurByProto.proto

#ifndef PROTOBUF_SendPKRecurByProto_2eproto__INCLUDED
#define PROTOBUF_SendPKRecurByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_SendPKRecurByProto_2eproto();
void protobuf_InitDefaults_SendPKRecurByProto_2eproto();
void protobuf_AssignDesc_SendPKRecurByProto_2eproto();
void protobuf_ShutdownFile_SendPKRecurByProto_2eproto();

class SendPKRecurReq;
class SendPKRecurRes;

// ===================================================================

class SendPKRecurReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.SendPKRecurReq) */ {
 public:
  SendPKRecurReq();
  virtual ~SendPKRecurReq();

  SendPKRecurReq(const SendPKRecurReq& from);

  inline SendPKRecurReq& operator=(const SendPKRecurReq& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const SendPKRecurReq& default_instance();

  static const SendPKRecurReq* internal_default_instance();

  void Swap(SendPKRecurReq* other);

  // implements Message ----------------------------------------------

  inline SendPKRecurReq* New() const { return New(NULL); }

  SendPKRecurReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const SendPKRecurReq& from);
  void MergeFrom(const SendPKRecurReq& from);
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
  void InternalSwap(SendPKRecurReq* other);
  void UnsafeMergeFrom(const SendPKRecurReq& from);
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

  // @@protoc_insertion_point(class_scope:proto.SendPKRecurReq)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr roomid_;
  ::google::protobuf::internal::ArenaStringPtr streamid_;
  ::google::protobuf::internal::ArenaStringPtr taguserid_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_SendPKRecurByProto_2eproto_impl();
  friend void  protobuf_AddDesc_SendPKRecurByProto_2eproto_impl();
  friend void protobuf_AssignDesc_SendPKRecurByProto_2eproto();
  friend void protobuf_ShutdownFile_SendPKRecurByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<SendPKRecurReq> SendPKRecurReq_default_instance_;

// -------------------------------------------------------------------

class SendPKRecurRes : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.SendPKRecurRes) */ {
 public:
  SendPKRecurRes();
  virtual ~SendPKRecurRes();

  SendPKRecurRes(const SendPKRecurRes& from);

  inline SendPKRecurRes& operator=(const SendPKRecurRes& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const SendPKRecurRes& default_instance();

  static const SendPKRecurRes* internal_default_instance();

  void Swap(SendPKRecurRes* other);

  // implements Message ----------------------------------------------

  inline SendPKRecurRes* New() const { return New(NULL); }

  SendPKRecurRes* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const SendPKRecurRes& from);
  void MergeFrom(const SendPKRecurRes& from);
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
  void InternalSwap(SendPKRecurRes* other);
  void UnsafeMergeFrom(const SendPKRecurRes& from);
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

  // @@protoc_insertion_point(class_scope:proto.SendPKRecurRes)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::proto::ReturnMsg* msg_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_SendPKRecurByProto_2eproto_impl();
  friend void  protobuf_AddDesc_SendPKRecurByProto_2eproto_impl();
  friend void protobuf_AssignDesc_SendPKRecurByProto_2eproto();
  friend void protobuf_ShutdownFile_SendPKRecurByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<SendPKRecurRes> SendPKRecurRes_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// SendPKRecurReq

// optional string roomId = 1;
inline void SendPKRecurReq::clear_roomid() {
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& SendPKRecurReq::roomid() const {
  // @@protoc_insertion_point(field_get:proto.SendPKRecurReq.roomId)
  return roomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKRecurReq::set_roomid(const ::std::string& value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.SendPKRecurReq.roomId)
}
inline void SendPKRecurReq::set_roomid(const char* value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.SendPKRecurReq.roomId)
}
inline void SendPKRecurReq::set_roomid(const char* value, size_t size) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.SendPKRecurReq.roomId)
}
inline ::std::string* SendPKRecurReq::mutable_roomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.SendPKRecurReq.roomId)
  return roomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* SendPKRecurReq::release_roomid() {
  // @@protoc_insertion_point(field_release:proto.SendPKRecurReq.roomId)
  
  return roomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKRecurReq::set_allocated_roomid(::std::string* roomid) {
  if (roomid != NULL) {
    
  } else {
    
  }
  roomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), roomid);
  // @@protoc_insertion_point(field_set_allocated:proto.SendPKRecurReq.roomId)
}

// optional string streamId = 2;
inline void SendPKRecurReq::clear_streamid() {
  streamid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& SendPKRecurReq::streamid() const {
  // @@protoc_insertion_point(field_get:proto.SendPKRecurReq.streamId)
  return streamid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKRecurReq::set_streamid(const ::std::string& value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.SendPKRecurReq.streamId)
}
inline void SendPKRecurReq::set_streamid(const char* value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.SendPKRecurReq.streamId)
}
inline void SendPKRecurReq::set_streamid(const char* value, size_t size) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.SendPKRecurReq.streamId)
}
inline ::std::string* SendPKRecurReq::mutable_streamid() {
  
  // @@protoc_insertion_point(field_mutable:proto.SendPKRecurReq.streamId)
  return streamid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* SendPKRecurReq::release_streamid() {
  // @@protoc_insertion_point(field_release:proto.SendPKRecurReq.streamId)
  
  return streamid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKRecurReq::set_allocated_streamid(::std::string* streamid) {
  if (streamid != NULL) {
    
  } else {
    
  }
  streamid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), streamid);
  // @@protoc_insertion_point(field_set_allocated:proto.SendPKRecurReq.streamId)
}

// optional string tagUserId = 3;
inline void SendPKRecurReq::clear_taguserid() {
  taguserid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& SendPKRecurReq::taguserid() const {
  // @@protoc_insertion_point(field_get:proto.SendPKRecurReq.tagUserId)
  return taguserid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKRecurReq::set_taguserid(const ::std::string& value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.SendPKRecurReq.tagUserId)
}
inline void SendPKRecurReq::set_taguserid(const char* value) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.SendPKRecurReq.tagUserId)
}
inline void SendPKRecurReq::set_taguserid(const char* value, size_t size) {
  
  taguserid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.SendPKRecurReq.tagUserId)
}
inline ::std::string* SendPKRecurReq::mutable_taguserid() {
  
  // @@protoc_insertion_point(field_mutable:proto.SendPKRecurReq.tagUserId)
  return taguserid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* SendPKRecurReq::release_taguserid() {
  // @@protoc_insertion_point(field_release:proto.SendPKRecurReq.tagUserId)
  
  return taguserid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void SendPKRecurReq::set_allocated_taguserid(::std::string* taguserid) {
  if (taguserid != NULL) {
    
  } else {
    
  }
  taguserid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), taguserid);
  // @@protoc_insertion_point(field_set_allocated:proto.SendPKRecurReq.tagUserId)
}

inline const SendPKRecurReq* SendPKRecurReq::internal_default_instance() {
  return &SendPKRecurReq_default_instance_.get();
}
// -------------------------------------------------------------------

// SendPKRecurRes

// optional .proto.ReturnMsg msg = 1;
inline bool SendPKRecurRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void SendPKRecurRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::proto::ReturnMsg& SendPKRecurRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.SendPKRecurRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
inline ::proto::ReturnMsg* SendPKRecurRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.SendPKRecurRes.msg)
  return msg_;
}
inline ::proto::ReturnMsg* SendPKRecurRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.SendPKRecurRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void SendPKRecurRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.SendPKRecurRes.msg)
}

inline const SendPKRecurRes* SendPKRecurRes::internal_default_instance() {
  return &SendPKRecurRes_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_SendPKRecurByProto_2eproto__INCLUDED
