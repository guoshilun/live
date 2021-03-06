// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushAnchorRoomMessageByProto.proto

#ifndef PROTOBUF_PushAnchorRoomMessageByProto_2eproto__INCLUDED
#define PROTOBUF_PushAnchorRoomMessageByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_PushAnchorRoomMessageByProto_2eproto();
void protobuf_InitDefaults_PushAnchorRoomMessageByProto_2eproto();
void protobuf_AssignDesc_PushAnchorRoomMessageByProto_2eproto();
void protobuf_ShutdownFile_PushAnchorRoomMessageByProto_2eproto();

class pushAnchorRoomMessage;

// ===================================================================

class pushAnchorRoomMessage : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.pushAnchorRoomMessage) */ {
 public:
  pushAnchorRoomMessage();
  virtual ~pushAnchorRoomMessage();

  pushAnchorRoomMessage(const pushAnchorRoomMessage& from);

  inline pushAnchorRoomMessage& operator=(const pushAnchorRoomMessage& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const pushAnchorRoomMessage& default_instance();

  static const pushAnchorRoomMessage* internal_default_instance();

  void Swap(pushAnchorRoomMessage* other);

  // implements Message ----------------------------------------------

  inline pushAnchorRoomMessage* New() const { return New(NULL); }

  pushAnchorRoomMessage* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const pushAnchorRoomMessage& from);
  void MergeFrom(const pushAnchorRoomMessage& from);
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
  void InternalSwap(pushAnchorRoomMessage* other);
  void UnsafeMergeFrom(const pushAnchorRoomMessage& from);
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

  // optional int32 grade = 1;
  void clear_grade();
  static const int kGradeFieldNumber = 1;
  ::google::protobuf::int32 grade() const;
  void set_grade(::google::protobuf::int32 value);

  // optional uint64 experience = 2;
  void clear_experience();
  static const int kExperienceFieldNumber = 2;
  ::google::protobuf::uint64 experience() const;
  void set_experience(::google::protobuf::uint64 value);

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

  // @@protoc_insertion_point(class_scope:proto.pushAnchorRoomMessage)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr roomid_;
  ::google::protobuf::uint64 experience_;
  ::google::protobuf::int32 grade_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_PushAnchorRoomMessageByProto_2eproto_impl();
  friend void  protobuf_AddDesc_PushAnchorRoomMessageByProto_2eproto_impl();
  friend void protobuf_AssignDesc_PushAnchorRoomMessageByProto_2eproto();
  friend void protobuf_ShutdownFile_PushAnchorRoomMessageByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<pushAnchorRoomMessage> pushAnchorRoomMessage_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// pushAnchorRoomMessage

// optional int32 grade = 1;
inline void pushAnchorRoomMessage::clear_grade() {
  grade_ = 0;
}
inline ::google::protobuf::int32 pushAnchorRoomMessage::grade() const {
  // @@protoc_insertion_point(field_get:proto.pushAnchorRoomMessage.grade)
  return grade_;
}
inline void pushAnchorRoomMessage::set_grade(::google::protobuf::int32 value) {
  
  grade_ = value;
  // @@protoc_insertion_point(field_set:proto.pushAnchorRoomMessage.grade)
}

// optional uint64 experience = 2;
inline void pushAnchorRoomMessage::clear_experience() {
  experience_ = GOOGLE_ULONGLONG(0);
}
inline ::google::protobuf::uint64 pushAnchorRoomMessage::experience() const {
  // @@protoc_insertion_point(field_get:proto.pushAnchorRoomMessage.experience)
  return experience_;
}
inline void pushAnchorRoomMessage::set_experience(::google::protobuf::uint64 value) {
  
  experience_ = value;
  // @@protoc_insertion_point(field_set:proto.pushAnchorRoomMessage.experience)
}

// optional string roomId = 3;
inline void pushAnchorRoomMessage::clear_roomid() {
  roomid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& pushAnchorRoomMessage::roomid() const {
  // @@protoc_insertion_point(field_get:proto.pushAnchorRoomMessage.roomId)
  return roomid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushAnchorRoomMessage::set_roomid(const ::std::string& value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.pushAnchorRoomMessage.roomId)
}
inline void pushAnchorRoomMessage::set_roomid(const char* value) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.pushAnchorRoomMessage.roomId)
}
inline void pushAnchorRoomMessage::set_roomid(const char* value, size_t size) {
  
  roomid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.pushAnchorRoomMessage.roomId)
}
inline ::std::string* pushAnchorRoomMessage::mutable_roomid() {
  
  // @@protoc_insertion_point(field_mutable:proto.pushAnchorRoomMessage.roomId)
  return roomid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* pushAnchorRoomMessage::release_roomid() {
  // @@protoc_insertion_point(field_release:proto.pushAnchorRoomMessage.roomId)
  
  return roomid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushAnchorRoomMessage::set_allocated_roomid(::std::string* roomid) {
  if (roomid != NULL) {
    
  } else {
    
  }
  roomid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), roomid);
  // @@protoc_insertion_point(field_set_allocated:proto.pushAnchorRoomMessage.roomId)
}

inline const pushAnchorRoomMessage* pushAnchorRoomMessage::internal_default_instance() {
  return &pushAnchorRoomMessage_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_PushAnchorRoomMessageByProto_2eproto__INCLUDED
