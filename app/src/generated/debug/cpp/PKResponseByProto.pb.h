// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PKResponseByProto.proto

#ifndef PROTOBUF_PKResponseByProto_2eproto__INCLUDED
#define PROTOBUF_PKResponseByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_PKResponseByProto_2eproto();
void protobuf_InitDefaults_PKResponseByProto_2eproto();
void protobuf_AssignDesc_PKResponseByProto_2eproto();
void protobuf_ShutdownFile_PKResponseByProto_2eproto();

class PKResponseReq;
class PKResponseRes;

// ===================================================================

class PKResponseReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.PKResponseReq) */ {
 public:
  PKResponseReq();
  virtual ~PKResponseReq();

  PKResponseReq(const PKResponseReq& from);

  inline PKResponseReq& operator=(const PKResponseReq& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const PKResponseReq& default_instance();

  static const PKResponseReq* internal_default_instance();

  void Swap(PKResponseReq* other);

  // implements Message ----------------------------------------------

  inline PKResponseReq* New() const { return New(NULL); }

  PKResponseReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const PKResponseReq& from);
  void MergeFrom(const PKResponseReq& from);
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
  void InternalSwap(PKResponseReq* other);
  void UnsafeMergeFrom(const PKResponseReq& from);
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

  // optional int32 type = 3;
  void clear_type();
  static const int kTypeFieldNumber = 3;
  ::google::protobuf::int32 type() const;
  void set_type(::google::protobuf::int32 value);

  // optional string myRoomId = 4;
  void clear_myroomid();
  static const int kMyRoomIdFieldNumber = 4;
  const ::std::string& myroomid() const;
  void set_myroomid(const ::std::string& value);
  void set_myroomid(const char* value);
  void set_myroomid(const char* value, size_t size);
  ::std::string* mutable_myroomid();
  ::std::string* release_myroomid();
  void set_allocated_myroomid(::std::string* myroomid);

  // optional string userId = 5;
  void clear_userid();
  static const int kUserIdFieldNumber = 5;
  const ::std::string& userid() const;
  void set_userid(const ::std::string& value);
  void set_userid(const char* value);
  void set_userid(const char* value, size_t size);
  ::std::string* mutable_userid();
  ::std::string* release_userid();
  void set_allocated_userid(::std::string* userid);

  // optional string myStreamId = 6;
  void clear_mystreamid();
  static const int kMyStreamIdFieldNumber = 6;
  const ::std::string& mystreamid() const;
  void set_mystreamid(const ::std::string& value);
  void set_mystreamid(const char* value);
  void set_mystreamid(const char* value, size_t size);
  ::std::string* mutable_mystreamid();
  ::std::string* release_mystreamid();
  void set_allocated_mystreamid(::std::string* mystreamid);

  // optional int32 aisle = 7;
  void clear_aisle();
  static const int kAisleFieldNumber = 7;
  ::google::protobuf::int32 aisle() const;
  void set_aisle(::google::protobuf::int32 value);

  // optional string mergeStreamUrl = 8;
  void clear_mergestreamurl();
  static const int kMergeStreamUrlFieldNumber = 8;
  const ::std::string& mergestreamurl() const;
  void set_mergestreamurl(const ::std::string& value);
  void set_mergestreamurl(const char* value);
  void set_mergestreamurl(const char* value, size_t size);
  ::std::string* mutable_mergestreamurl();
  ::std::string* release_mergestreamurl();
  void set_allocated_mergestreamurl(::std::string* mergestreamurl);

  // @@protoc_insertion_point(class_scope:proto.PKResponseReq)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr roomid_;
  ::google::protobuf::internal::ArenaStringPtr streamid_;
  ::google::protobuf::internal::ArenaStringPtr myroomid_;
  ::google::protobuf::internal::ArenaStringPtr userid_;
  ::google::protobuf::internal::ArenaStringPtr mystreamid_;
  ::google::protobuf::internal::ArenaStringPtr mergestreamurl_;
  ::google::protobuf::int32 type_;
  ::google::protobuf::int32 aisle_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_PKResponseByProto_2eproto_impl();
  friend void  protobuf_AddDesc_PKResponseByProto_2eproto_impl();
  friend void protobuf_AssignDesc_PKResponseByProto_2eproto();
  friend void protobuf_ShutdownFile_PKResponseByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<PKResponseReq> PKResponseReq_default_instance_;

// -------------------------------------------------------------------

class PKResponseRes : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.PKResponseRes) */ {
 public:
  PKResponseRes();
  virtual ~PKResponseRes();

  PKResponseRes(const PKResponseRes& from);

  inline PKResponseRes& operator=(const PKResponseRes& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const PKResponseRes& default_instance();

  static const PKResponseRes* internal_default_instance();

  void Swap(PKResponseRes* other);

  // implements Message ----------------------------------------------

  inline PKResponseRes* New() const { return New(NULL); }

  PKResponseRes* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const PKResponseRes& from);
  void MergeFrom(const PKResponseRes& from);
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
  void InternalSwap(PKResponseRes* other);
  void UnsafeMergeFrom(const PKResponseRes& from);
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

  // @@protoc_insertion_point(class_scope:proto.PKResponseRes)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::proto::ReturnMsg* msg_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_PKResponseByProto_2eproto_impl();
  friend void  protobuf_AddDesc_PKResponseByProto_2eproto_impl();
  friend void protobuf_AssignDesc_PKResponseByProto_2eproto();
  friend void protobuf_ShutdownFile_PKResponseByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<PKResponseRes> PKResponseRes_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// PKResponseReq

// optional string roomId = 1;
inline void PKResponseReq::clear_roomid() {
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& PKResponseReq::roomid() const {
  // @@protoc_insertion_point(field_get:proto.PKResponseReq.roomId)
  return roomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_roomid(const ::std::string& value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PKResponseReq.roomId)
}
inline void PKResponseReq::set_roomid(const char* value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PKResponseReq.roomId)
}
inline void PKResponseReq::set_roomid(const char* value, size_t size) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PKResponseReq.roomId)
}
inline ::std::string* PKResponseReq::mutable_roomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.PKResponseReq.roomId)
  return roomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* PKResponseReq::release_roomid() {
  // @@protoc_insertion_point(field_release:proto.PKResponseReq.roomId)
  
  return roomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_allocated_roomid(::std::string* roomid) {
  if (roomid != NULL) {
    
  } else {
    
  }
  roomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), roomid);
  // @@protoc_insertion_point(field_set_allocated:proto.PKResponseReq.roomId)
}

// optional string streamId = 2;
inline void PKResponseReq::clear_streamid() {
  streamid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& PKResponseReq::streamid() const {
  // @@protoc_insertion_point(field_get:proto.PKResponseReq.streamId)
  return streamid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_streamid(const ::std::string& value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PKResponseReq.streamId)
}
inline void PKResponseReq::set_streamid(const char* value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PKResponseReq.streamId)
}
inline void PKResponseReq::set_streamid(const char* value, size_t size) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PKResponseReq.streamId)
}
inline ::std::string* PKResponseReq::mutable_streamid() {
  
  // @@protoc_insertion_point(field_mutable:proto.PKResponseReq.streamId)
  return streamid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* PKResponseReq::release_streamid() {
  // @@protoc_insertion_point(field_release:proto.PKResponseReq.streamId)
  
  return streamid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_allocated_streamid(::std::string* streamid) {
  if (streamid != NULL) {
    
  } else {
    
  }
  streamid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), streamid);
  // @@protoc_insertion_point(field_set_allocated:proto.PKResponseReq.streamId)
}

// optional int32 type = 3;
inline void PKResponseReq::clear_type() {
  type_ = 0;
}
inline ::google::protobuf::int32 PKResponseReq::type() const {
  // @@protoc_insertion_point(field_get:proto.PKResponseReq.type)
  return type_;
}
inline void PKResponseReq::set_type(::google::protobuf::int32 value) {
  
  type_ = value;
  // @@protoc_insertion_point(field_set:proto.PKResponseReq.type)
}

// optional string myRoomId = 4;
inline void PKResponseReq::clear_myroomid() {
  myroomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& PKResponseReq::myroomid() const {
  // @@protoc_insertion_point(field_get:proto.PKResponseReq.myRoomId)
  return myroomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_myroomid(const ::std::string& value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PKResponseReq.myRoomId)
}
inline void PKResponseReq::set_myroomid(const char* value) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PKResponseReq.myRoomId)
}
inline void PKResponseReq::set_myroomid(const char* value, size_t size) {
  
  myroomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PKResponseReq.myRoomId)
}
inline ::std::string* PKResponseReq::mutable_myroomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.PKResponseReq.myRoomId)
  return myroomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* PKResponseReq::release_myroomid() {
  // @@protoc_insertion_point(field_release:proto.PKResponseReq.myRoomId)
  
  return myroomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_allocated_myroomid(::std::string* myroomid) {
  if (myroomid != NULL) {
    
  } else {
    
  }
  myroomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), myroomid);
  // @@protoc_insertion_point(field_set_allocated:proto.PKResponseReq.myRoomId)
}

// optional string userId = 5;
inline void PKResponseReq::clear_userid() {
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& PKResponseReq::userid() const {
  // @@protoc_insertion_point(field_get:proto.PKResponseReq.userId)
  return userid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_userid(const ::std::string& value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PKResponseReq.userId)
}
inline void PKResponseReq::set_userid(const char* value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PKResponseReq.userId)
}
inline void PKResponseReq::set_userid(const char* value, size_t size) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PKResponseReq.userId)
}
inline ::std::string* PKResponseReq::mutable_userid() {
  
  // @@protoc_insertion_point(field_mutable:proto.PKResponseReq.userId)
  return userid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* PKResponseReq::release_userid() {
  // @@protoc_insertion_point(field_release:proto.PKResponseReq.userId)
  
  return userid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_allocated_userid(::std::string* userid) {
  if (userid != NULL) {
    
  } else {
    
  }
  userid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userid);
  // @@protoc_insertion_point(field_set_allocated:proto.PKResponseReq.userId)
}

// optional string myStreamId = 6;
inline void PKResponseReq::clear_mystreamid() {
  mystreamid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& PKResponseReq::mystreamid() const {
  // @@protoc_insertion_point(field_get:proto.PKResponseReq.myStreamId)
  return mystreamid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_mystreamid(const ::std::string& value) {
  
  mystreamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PKResponseReq.myStreamId)
}
inline void PKResponseReq::set_mystreamid(const char* value) {
  
  mystreamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PKResponseReq.myStreamId)
}
inline void PKResponseReq::set_mystreamid(const char* value, size_t size) {
  
  mystreamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PKResponseReq.myStreamId)
}
inline ::std::string* PKResponseReq::mutable_mystreamid() {
  
  // @@protoc_insertion_point(field_mutable:proto.PKResponseReq.myStreamId)
  return mystreamid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* PKResponseReq::release_mystreamid() {
  // @@protoc_insertion_point(field_release:proto.PKResponseReq.myStreamId)
  
  return mystreamid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_allocated_mystreamid(::std::string* mystreamid) {
  if (mystreamid != NULL) {
    
  } else {
    
  }
  mystreamid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), mystreamid);
  // @@protoc_insertion_point(field_set_allocated:proto.PKResponseReq.myStreamId)
}

// optional int32 aisle = 7;
inline void PKResponseReq::clear_aisle() {
  aisle_ = 0;
}
inline ::google::protobuf::int32 PKResponseReq::aisle() const {
  // @@protoc_insertion_point(field_get:proto.PKResponseReq.aisle)
  return aisle_;
}
inline void PKResponseReq::set_aisle(::google::protobuf::int32 value) {
  
  aisle_ = value;
  // @@protoc_insertion_point(field_set:proto.PKResponseReq.aisle)
}

// optional string mergeStreamUrl = 8;
inline void PKResponseReq::clear_mergestreamurl() {
  mergestreamurl_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& PKResponseReq::mergestreamurl() const {
  // @@protoc_insertion_point(field_get:proto.PKResponseReq.mergeStreamUrl)
  return mergestreamurl_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_mergestreamurl(const ::std::string& value) {
  
  mergestreamurl_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PKResponseReq.mergeStreamUrl)
}
inline void PKResponseReq::set_mergestreamurl(const char* value) {
  
  mergestreamurl_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PKResponseReq.mergeStreamUrl)
}
inline void PKResponseReq::set_mergestreamurl(const char* value, size_t size) {
  
  mergestreamurl_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PKResponseReq.mergeStreamUrl)
}
inline ::std::string* PKResponseReq::mutable_mergestreamurl() {
  
  // @@protoc_insertion_point(field_mutable:proto.PKResponseReq.mergeStreamUrl)
  return mergestreamurl_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* PKResponseReq::release_mergestreamurl() {
  // @@protoc_insertion_point(field_release:proto.PKResponseReq.mergeStreamUrl)
  
  return mergestreamurl_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PKResponseReq::set_allocated_mergestreamurl(::std::string* mergestreamurl) {
  if (mergestreamurl != NULL) {
    
  } else {
    
  }
  mergestreamurl_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), mergestreamurl);
  // @@protoc_insertion_point(field_set_allocated:proto.PKResponseReq.mergeStreamUrl)
}

inline const PKResponseReq* PKResponseReq::internal_default_instance() {
  return &PKResponseReq_default_instance_.get();
}
// -------------------------------------------------------------------

// PKResponseRes

// optional .proto.ReturnMsg msg = 1;
inline bool PKResponseRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void PKResponseRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::proto::ReturnMsg& PKResponseRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.PKResponseRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
inline ::proto::ReturnMsg* PKResponseRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.PKResponseRes.msg)
  return msg_;
}
inline ::proto::ReturnMsg* PKResponseRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.PKResponseRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void PKResponseRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.PKResponseRes.msg)
}

inline const PKResponseRes* PKResponseRes::internal_default_instance() {
  return &PKResponseRes_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_PKResponseByProto_2eproto__INCLUDED
