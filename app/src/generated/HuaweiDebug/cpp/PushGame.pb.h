// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushGame.proto

#ifndef PROTOBUF_PushGame_2eproto__INCLUDED
#define PROTOBUF_PushGame_2eproto__INCLUDED

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
void protobuf_AddDesc_PushGame_2eproto();
void protobuf_InitDefaults_PushGame_2eproto();
void protobuf_AssignDesc_PushGame_2eproto();
void protobuf_ShutdownFile_PushGame_2eproto();

class PushGameMessage;

// ===================================================================

class PushGameMessage : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.PushGameMessage) */ {
 public:
  PushGameMessage();
  virtual ~PushGameMessage();

  PushGameMessage(const PushGameMessage& from);

  inline PushGameMessage& operator=(const PushGameMessage& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const PushGameMessage& default_instance();

  static const PushGameMessage* internal_default_instance();

  void Swap(PushGameMessage* other);

  // implements Message ----------------------------------------------

  inline PushGameMessage* New() const { return New(NULL); }

  PushGameMessage* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const PushGameMessage& from);
  void MergeFrom(const PushGameMessage& from);
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
  void InternalSwap(PushGameMessage* other);
  void UnsafeMergeFrom(const PushGameMessage& from);
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

  // optional int32 front = 2;
  void clear_front();
  static const int kFrontFieldNumber = 2;
  ::google::protobuf::int32 front() const;
  void set_front(::google::protobuf::int32 value);

  // optional int32 contrary = 3;
  void clear_contrary();
  static const int kContraryFieldNumber = 3;
  ::google::protobuf::int32 contrary() const;
  void set_contrary(::google::protobuf::int32 value);

  // optional int32 maxBet = 4;
  void clear_maxbet();
  static const int kMaxBetFieldNumber = 4;
  ::google::protobuf::int32 maxbet() const;
  void set_maxbet(::google::protobuf::int32 value);

  // optional int32 time = 8;
  void clear_time();
  static const int kTimeFieldNumber = 8;
  ::google::protobuf::int32 time() const;
  void set_time(::google::protobuf::int32 value);

  // @@protoc_insertion_point(class_scope:proto.PushGameMessage)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::int32 front_;
  ::google::protobuf::int32 contrary_;
  ::google::protobuf::int32 maxbet_;
  ::google::protobuf::int32 time_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_PushGame_2eproto_impl();
  friend void  protobuf_AddDesc_PushGame_2eproto_impl();
  friend void protobuf_AssignDesc_PushGame_2eproto();
  friend void protobuf_ShutdownFile_PushGame_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<PushGameMessage> PushGameMessage_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// PushGameMessage

// optional int32 front = 2;
inline void PushGameMessage::clear_front() {
  front_ = 0;
}
inline ::google::protobuf::int32 PushGameMessage::front() const {
  // @@protoc_insertion_point(field_get:proto.PushGameMessage.front)
  return front_;
}
inline void PushGameMessage::set_front(::google::protobuf::int32 value) {
  
  front_ = value;
  // @@protoc_insertion_point(field_set:proto.PushGameMessage.front)
}

// optional int32 contrary = 3;
inline void PushGameMessage::clear_contrary() {
  contrary_ = 0;
}
inline ::google::protobuf::int32 PushGameMessage::contrary() const {
  // @@protoc_insertion_point(field_get:proto.PushGameMessage.contrary)
  return contrary_;
}
inline void PushGameMessage::set_contrary(::google::protobuf::int32 value) {
  
  contrary_ = value;
  // @@protoc_insertion_point(field_set:proto.PushGameMessage.contrary)
}

// optional int32 maxBet = 4;
inline void PushGameMessage::clear_maxbet() {
  maxbet_ = 0;
}
inline ::google::protobuf::int32 PushGameMessage::maxbet() const {
  // @@protoc_insertion_point(field_get:proto.PushGameMessage.maxBet)
  return maxbet_;
}
inline void PushGameMessage::set_maxbet(::google::protobuf::int32 value) {
  
  maxbet_ = value;
  // @@protoc_insertion_point(field_set:proto.PushGameMessage.maxBet)
}

// optional int32 time = 8;
inline void PushGameMessage::clear_time() {
  time_ = 0;
}
inline ::google::protobuf::int32 PushGameMessage::time() const {
  // @@protoc_insertion_point(field_get:proto.PushGameMessage.time)
  return time_;
}
inline void PushGameMessage::set_time(::google::protobuf::int32 value) {
  
  time_ = value;
  // @@protoc_insertion_point(field_set:proto.PushGameMessage.time)
}

inline const PushGameMessage* PushGameMessage::internal_default_instance() {
  return &PushGameMessage_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_PushGame_2eproto__INCLUDED
