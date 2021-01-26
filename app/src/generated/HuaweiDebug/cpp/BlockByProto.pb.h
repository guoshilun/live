// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BlockByProto.proto

#ifndef PROTOBUF_BlockByProto_2eproto__INCLUDED
#define PROTOBUF_BlockByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_BlockByProto_2eproto();
void protobuf_InitDefaults_BlockByProto_2eproto();
void protobuf_AssignDesc_BlockByProto_2eproto();
void protobuf_ShutdownFile_BlockByProto_2eproto();

class BlockByProtoReq;
class BlockByProtoRes;

// ===================================================================

class BlockByProtoReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.BlockByProtoReq) */ {
 public:
  BlockByProtoReq();
  virtual ~BlockByProtoReq();

  BlockByProtoReq(const BlockByProtoReq& from);

  inline BlockByProtoReq& operator=(const BlockByProtoReq& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const BlockByProtoReq& default_instance();

  static const BlockByProtoReq* internal_default_instance();

  void Swap(BlockByProtoReq* other);

  // implements Message ----------------------------------------------

  inline BlockByProtoReq* New() const { return New(NULL); }

  BlockByProtoReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const BlockByProtoReq& from);
  void MergeFrom(const BlockByProtoReq& from);
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
  void InternalSwap(BlockByProtoReq* other);
  void UnsafeMergeFrom(const BlockByProtoReq& from);
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

  // optional string roomId = 2;
  void clear_roomid();
  static const int kRoomIdFieldNumber = 2;
  const ::std::string& roomid() const;
  void set_roomid(const ::std::string& value);
  void set_roomid(const char* value);
  void set_roomid(const char* value, size_t size);
  ::std::string* mutable_roomid();
  ::std::string* release_roomid();
  void set_allocated_roomid(::std::string* roomid);

  // @@protoc_insertion_point(class_scope:proto.BlockByProtoReq)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr tagid_;
  ::google::protobuf::internal::ArenaStringPtr roomid_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_BlockByProto_2eproto_impl();
  friend void  protobuf_AddDesc_BlockByProto_2eproto_impl();
  friend void protobuf_AssignDesc_BlockByProto_2eproto();
  friend void protobuf_ShutdownFile_BlockByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<BlockByProtoReq> BlockByProtoReq_default_instance_;

// -------------------------------------------------------------------

class BlockByProtoRes : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.BlockByProtoRes) */ {
 public:
  BlockByProtoRes();
  virtual ~BlockByProtoRes();

  BlockByProtoRes(const BlockByProtoRes& from);

  inline BlockByProtoRes& operator=(const BlockByProtoRes& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const BlockByProtoRes& default_instance();

  static const BlockByProtoRes* internal_default_instance();

  void Swap(BlockByProtoRes* other);

  // implements Message ----------------------------------------------

  inline BlockByProtoRes* New() const { return New(NULL); }

  BlockByProtoRes* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const BlockByProtoRes& from);
  void MergeFrom(const BlockByProtoRes& from);
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
  void InternalSwap(BlockByProtoRes* other);
  void UnsafeMergeFrom(const BlockByProtoRes& from);
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

  // @@protoc_insertion_point(class_scope:proto.BlockByProtoRes)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::proto::ReturnMsg* msg_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_BlockByProto_2eproto_impl();
  friend void  protobuf_AddDesc_BlockByProto_2eproto_impl();
  friend void protobuf_AssignDesc_BlockByProto_2eproto();
  friend void protobuf_ShutdownFile_BlockByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<BlockByProtoRes> BlockByProtoRes_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// BlockByProtoReq

// optional string tagId = 1;
inline void BlockByProtoReq::clear_tagid() {
  tagid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& BlockByProtoReq::tagid() const {
  // @@protoc_insertion_point(field_get:proto.BlockByProtoReq.tagId)
  return tagid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void BlockByProtoReq::set_tagid(const ::std::string& value) {
  
  tagid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.BlockByProtoReq.tagId)
}
inline void BlockByProtoReq::set_tagid(const char* value) {
  
  tagid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.BlockByProtoReq.tagId)
}
inline void BlockByProtoReq::set_tagid(const char* value, size_t size) {
  
  tagid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.BlockByProtoReq.tagId)
}
inline ::std::string* BlockByProtoReq::mutable_tagid() {
  
  // @@protoc_insertion_point(field_mutable:proto.BlockByProtoReq.tagId)
  return tagid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* BlockByProtoReq::release_tagid() {
  // @@protoc_insertion_point(field_release:proto.BlockByProtoReq.tagId)
  
  return tagid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void BlockByProtoReq::set_allocated_tagid(::std::string* tagid) {
  if (tagid != NULL) {
    
  } else {
    
  }
  tagid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), tagid);
  // @@protoc_insertion_point(field_set_allocated:proto.BlockByProtoReq.tagId)
}

// optional string roomId = 2;
inline void BlockByProtoReq::clear_roomid() {
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& BlockByProtoReq::roomid() const {
  // @@protoc_insertion_point(field_get:proto.BlockByProtoReq.roomId)
  return roomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void BlockByProtoReq::set_roomid(const ::std::string& value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.BlockByProtoReq.roomId)
}
inline void BlockByProtoReq::set_roomid(const char* value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.BlockByProtoReq.roomId)
}
inline void BlockByProtoReq::set_roomid(const char* value, size_t size) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.BlockByProtoReq.roomId)
}
inline ::std::string* BlockByProtoReq::mutable_roomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.BlockByProtoReq.roomId)
  return roomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* BlockByProtoReq::release_roomid() {
  // @@protoc_insertion_point(field_release:proto.BlockByProtoReq.roomId)
  
  return roomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void BlockByProtoReq::set_allocated_roomid(::std::string* roomid) {
  if (roomid != NULL) {
    
  } else {
    
  }
  roomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), roomid);
  // @@protoc_insertion_point(field_set_allocated:proto.BlockByProtoReq.roomId)
}

inline const BlockByProtoReq* BlockByProtoReq::internal_default_instance() {
  return &BlockByProtoReq_default_instance_.get();
}
// -------------------------------------------------------------------

// BlockByProtoRes

// optional .proto.ReturnMsg msg = 1;
inline bool BlockByProtoRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void BlockByProtoRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::proto::ReturnMsg& BlockByProtoRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.BlockByProtoRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
inline ::proto::ReturnMsg* BlockByProtoRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.BlockByProtoRes.msg)
  return msg_;
}
inline ::proto::ReturnMsg* BlockByProtoRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.BlockByProtoRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void BlockByProtoRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.BlockByProtoRes.msg)
}

inline const BlockByProtoRes* BlockByProtoRes::internal_default_instance() {
  return &BlockByProtoRes_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_BlockByProto_2eproto__INCLUDED
