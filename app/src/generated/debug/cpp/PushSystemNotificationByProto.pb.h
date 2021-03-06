// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushSystemNotificationByProto.proto

#ifndef PROTOBUF_PushSystemNotificationByProto_2eproto__INCLUDED
#define PROTOBUF_PushSystemNotificationByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_PushSystemNotificationByProto_2eproto();
void protobuf_InitDefaults_PushSystemNotificationByProto_2eproto();
void protobuf_AssignDesc_PushSystemNotificationByProto_2eproto();
void protobuf_ShutdownFile_PushSystemNotificationByProto_2eproto();

class PushSystemNotification;

// ===================================================================

class PushSystemNotification : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.PushSystemNotification) */ {
 public:
  PushSystemNotification();
  virtual ~PushSystemNotification();

  PushSystemNotification(const PushSystemNotification& from);

  inline PushSystemNotification& operator=(const PushSystemNotification& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const PushSystemNotification& default_instance();

  static const PushSystemNotification* internal_default_instance();

  void Swap(PushSystemNotification* other);

  // implements Message ----------------------------------------------

  inline PushSystemNotification* New() const { return New(NULL); }

  PushSystemNotification* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const PushSystemNotification& from);
  void MergeFrom(const PushSystemNotification& from);
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
  void InternalSwap(PushSystemNotification* other);
  void UnsafeMergeFrom(const PushSystemNotification& from);
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

  // optional int32 type = 1;
  void clear_type();
  static const int kTypeFieldNumber = 1;
  ::google::protobuf::int32 type() const;
  void set_type(::google::protobuf::int32 value);

  // optional string message = 2;
  void clear_message();
  static const int kMessageFieldNumber = 2;
  const ::std::string& message() const;
  void set_message(const ::std::string& value);
  void set_message(const char* value);
  void set_message(const char* value, size_t size);
  ::std::string* mutable_message();
  ::std::string* release_message();
  void set_allocated_message(::std::string* message);

  // @@protoc_insertion_point(class_scope:proto.PushSystemNotification)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr message_;
  ::google::protobuf::int32 type_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_PushSystemNotificationByProto_2eproto_impl();
  friend void  protobuf_AddDesc_PushSystemNotificationByProto_2eproto_impl();
  friend void protobuf_AssignDesc_PushSystemNotificationByProto_2eproto();
  friend void protobuf_ShutdownFile_PushSystemNotificationByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<PushSystemNotification> PushSystemNotification_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// PushSystemNotification

// optional int32 type = 1;
inline void PushSystemNotification::clear_type() {
  type_ = 0;
}
inline ::google::protobuf::int32 PushSystemNotification::type() const {
  // @@protoc_insertion_point(field_get:proto.PushSystemNotification.type)
  return type_;
}
inline void PushSystemNotification::set_type(::google::protobuf::int32 value) {
  
  type_ = value;
  // @@protoc_insertion_point(field_set:proto.PushSystemNotification.type)
}

// optional string message = 2;
inline void PushSystemNotification::clear_message() {
  message_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& PushSystemNotification::message() const {
  // @@protoc_insertion_point(field_get:proto.PushSystemNotification.message)
  return message_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PushSystemNotification::set_message(const ::std::string& value) {
  
  message_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.PushSystemNotification.message)
}
inline void PushSystemNotification::set_message(const char* value) {
  
  message_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.PushSystemNotification.message)
}
inline void PushSystemNotification::set_message(const char* value, size_t size) {
  
  message_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.PushSystemNotification.message)
}
inline ::std::string* PushSystemNotification::mutable_message() {
  
  // @@protoc_insertion_point(field_mutable:proto.PushSystemNotification.message)
  return message_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* PushSystemNotification::release_message() {
  // @@protoc_insertion_point(field_release:proto.PushSystemNotification.message)
  
  return message_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void PushSystemNotification::set_allocated_message(::std::string* message) {
  if (message != NULL) {
    
  } else {
    
  }
  message_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), message);
  // @@protoc_insertion_point(field_set_allocated:proto.PushSystemNotification.message)
}

inline const PushSystemNotification* PushSystemNotification::internal_default_instance() {
  return &PushSystemNotification_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_PushSystemNotificationByProto_2eproto__INCLUDED
