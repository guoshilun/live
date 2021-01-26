// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushLinkByProto.proto

#ifndef PROTOBUF_PushLinkByProto_2eproto__INCLUDED
#define PROTOBUF_PushLinkByProto_2eproto__INCLUDED

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
void protobuf_AddDesc_PushLinkByProto_2eproto();
void protobuf_InitDefaults_PushLinkByProto_2eproto();
void protobuf_AssignDesc_PushLinkByProto_2eproto();
void protobuf_ShutdownFile_PushLinkByProto_2eproto();

class pushLonk;

// ===================================================================

class pushLonk : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.pushLonk) */ {
 public:
  pushLonk();
  virtual ~pushLonk();

  pushLonk(const pushLonk& from);

  inline pushLonk& operator=(const pushLonk& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const pushLonk& default_instance();

  static const pushLonk* internal_default_instance();

  void Swap(pushLonk* other);

  // implements Message ----------------------------------------------

  inline pushLonk* New() const { return New(NULL); }

  pushLonk* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const pushLonk& from);
  void MergeFrom(const pushLonk& from);
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
  void InternalSwap(pushLonk* other);
  void UnsafeMergeFrom(const pushLonk& from);
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

  // optional string picture = 2;
  void clear_picture();
  static const int kPictureFieldNumber = 2;
  const ::std::string& picture() const;
  void set_picture(const ::std::string& value);
  void set_picture(const char* value);
  void set_picture(const char* value, size_t size);
  ::std::string* mutable_picture();
  ::std::string* release_picture();
  void set_allocated_picture(::std::string* picture);

  // optional string nickname = 3;
  void clear_nickname();
  static const int kNicknameFieldNumber = 3;
  const ::std::string& nickname() const;
  void set_nickname(const ::std::string& value);
  void set_nickname(const char* value);
  void set_nickname(const char* value, size_t size);
  ::std::string* mutable_nickname();
  ::std::string* release_nickname();
  void set_allocated_nickname(::std::string* nickname);

  // optional int32 userGrade = 4;
  void clear_usergrade();
  static const int kUserGradeFieldNumber = 4;
  ::google::protobuf::int32 usergrade() const;
  void set_usergrade(::google::protobuf::int32 value);

  // optional int32 time = 5;
  void clear_time();
  static const int kTimeFieldNumber = 5;
  ::google::protobuf::int32 time() const;
  void set_time(::google::protobuf::int32 value);

  // optional string streamId = 6;
  void clear_streamid();
  static const int kStreamIdFieldNumber = 6;
  const ::std::string& streamid() const;
  void set_streamid(const ::std::string& value);
  void set_streamid(const char* value);
  void set_streamid(const char* value, size_t size);
  ::std::string* mutable_streamid();
  ::std::string* release_streamid();
  void set_allocated_streamid(::std::string* streamid);

  // @@protoc_insertion_point(class_scope:proto.pushLonk)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr userid_;
  ::google::protobuf::internal::ArenaStringPtr picture_;
  ::google::protobuf::internal::ArenaStringPtr nickname_;
  ::google::protobuf::internal::ArenaStringPtr streamid_;
  ::google::protobuf::int32 usergrade_;
  ::google::protobuf::int32 time_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_PushLinkByProto_2eproto_impl();
  friend void  protobuf_AddDesc_PushLinkByProto_2eproto_impl();
  friend void protobuf_AssignDesc_PushLinkByProto_2eproto();
  friend void protobuf_ShutdownFile_PushLinkByProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<pushLonk> pushLonk_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// pushLonk

// optional string userId = 1;
inline void pushLonk::clear_userid() {
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& pushLonk::userid() const {
  // @@protoc_insertion_point(field_get:proto.pushLonk.userId)
  return userid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushLonk::set_userid(const ::std::string& value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.pushLonk.userId)
}
inline void pushLonk::set_userid(const char* value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.pushLonk.userId)
}
inline void pushLonk::set_userid(const char* value, size_t size) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.pushLonk.userId)
}
inline ::std::string* pushLonk::mutable_userid() {
  
  // @@protoc_insertion_point(field_mutable:proto.pushLonk.userId)
  return userid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* pushLonk::release_userid() {
  // @@protoc_insertion_point(field_release:proto.pushLonk.userId)
  
  return userid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushLonk::set_allocated_userid(::std::string* userid) {
  if (userid != NULL) {
    
  } else {
    
  }
  userid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userid);
  // @@protoc_insertion_point(field_set_allocated:proto.pushLonk.userId)
}

// optional string picture = 2;
inline void pushLonk::clear_picture() {
  picture_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& pushLonk::picture() const {
  // @@protoc_insertion_point(field_get:proto.pushLonk.picture)
  return picture_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushLonk::set_picture(const ::std::string& value) {
  
  picture_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.pushLonk.picture)
}
inline void pushLonk::set_picture(const char* value) {
  
  picture_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.pushLonk.picture)
}
inline void pushLonk::set_picture(const char* value, size_t size) {
  
  picture_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.pushLonk.picture)
}
inline ::std::string* pushLonk::mutable_picture() {
  
  // @@protoc_insertion_point(field_mutable:proto.pushLonk.picture)
  return picture_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* pushLonk::release_picture() {
  // @@protoc_insertion_point(field_release:proto.pushLonk.picture)
  
  return picture_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushLonk::set_allocated_picture(::std::string* picture) {
  if (picture != NULL) {
    
  } else {
    
  }
  picture_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), picture);
  // @@protoc_insertion_point(field_set_allocated:proto.pushLonk.picture)
}

// optional string nickname = 3;
inline void pushLonk::clear_nickname() {
  nickname_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& pushLonk::nickname() const {
  // @@protoc_insertion_point(field_get:proto.pushLonk.nickname)
  return nickname_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushLonk::set_nickname(const ::std::string& value) {
  
  nickname_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.pushLonk.nickname)
}
inline void pushLonk::set_nickname(const char* value) {
  
  nickname_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.pushLonk.nickname)
}
inline void pushLonk::set_nickname(const char* value, size_t size) {
  
  nickname_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.pushLonk.nickname)
}
inline ::std::string* pushLonk::mutable_nickname() {
  
  // @@protoc_insertion_point(field_mutable:proto.pushLonk.nickname)
  return nickname_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* pushLonk::release_nickname() {
  // @@protoc_insertion_point(field_release:proto.pushLonk.nickname)
  
  return nickname_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushLonk::set_allocated_nickname(::std::string* nickname) {
  if (nickname != NULL) {
    
  } else {
    
  }
  nickname_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), nickname);
  // @@protoc_insertion_point(field_set_allocated:proto.pushLonk.nickname)
}

// optional int32 userGrade = 4;
inline void pushLonk::clear_usergrade() {
  usergrade_ = 0;
}
inline ::google::protobuf::int32 pushLonk::usergrade() const {
  // @@protoc_insertion_point(field_get:proto.pushLonk.userGrade)
  return usergrade_;
}
inline void pushLonk::set_usergrade(::google::protobuf::int32 value) {
  
  usergrade_ = value;
  // @@protoc_insertion_point(field_set:proto.pushLonk.userGrade)
}

// optional int32 time = 5;
inline void pushLonk::clear_time() {
  time_ = 0;
}
inline ::google::protobuf::int32 pushLonk::time() const {
  // @@protoc_insertion_point(field_get:proto.pushLonk.time)
  return time_;
}
inline void pushLonk::set_time(::google::protobuf::int32 value) {
  
  time_ = value;
  // @@protoc_insertion_point(field_set:proto.pushLonk.time)
}

// optional string streamId = 6;
inline void pushLonk::clear_streamid() {
  streamid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& pushLonk::streamid() const {
  // @@protoc_insertion_point(field_get:proto.pushLonk.streamId)
  return streamid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushLonk::set_streamid(const ::std::string& value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.pushLonk.streamId)
}
inline void pushLonk::set_streamid(const char* value) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.pushLonk.streamId)
}
inline void pushLonk::set_streamid(const char* value, size_t size) {
  
  streamid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.pushLonk.streamId)
}
inline ::std::string* pushLonk::mutable_streamid() {
  
  // @@protoc_insertion_point(field_mutable:proto.pushLonk.streamId)
  return streamid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* pushLonk::release_streamid() {
  // @@protoc_insertion_point(field_release:proto.pushLonk.streamId)
  
  return streamid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void pushLonk::set_allocated_streamid(::std::string* streamid) {
  if (streamid != NULL) {
    
  } else {
    
  }
  streamid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), streamid);
  // @@protoc_insertion_point(field_set_allocated:proto.pushLonk.streamId)
}

inline const pushLonk* pushLonk::internal_default_instance() {
  return &pushLonk_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_PushLinkByProto_2eproto__INCLUDED
