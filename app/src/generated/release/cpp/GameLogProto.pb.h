// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GameLogProto.proto

#ifndef PROTOBUF_GameLogProto_2eproto__INCLUDED
#define PROTOBUF_GameLogProto_2eproto__INCLUDED

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
void protobuf_AddDesc_GameLogProto_2eproto();
void protobuf_InitDefaults_GameLogProto_2eproto();
void protobuf_AssignDesc_GameLogProto_2eproto();
void protobuf_ShutdownFile_GameLogProto_2eproto();

class GameLogReq;
class GameLogRes;
class GameLogResult;

// ===================================================================

class GameLogReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.GameLogReq) */ {
 public:
  GameLogReq();
  virtual ~GameLogReq();

  GameLogReq(const GameLogReq& from);

  inline GameLogReq& operator=(const GameLogReq& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const GameLogReq& default_instance();

  static const GameLogReq* internal_default_instance();

  void Swap(GameLogReq* other);

  // implements Message ----------------------------------------------

  inline GameLogReq* New() const { return New(NULL); }

  GameLogReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const GameLogReq& from);
  void MergeFrom(const GameLogReq& from);
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
  void InternalSwap(GameLogReq* other);
  void UnsafeMergeFrom(const GameLogReq& from);
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

  // @@protoc_insertion_point(class_scope:proto.GameLogReq)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr userid_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_GameLogProto_2eproto_impl();
  friend void  protobuf_AddDesc_GameLogProto_2eproto_impl();
  friend void protobuf_AssignDesc_GameLogProto_2eproto();
  friend void protobuf_ShutdownFile_GameLogProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<GameLogReq> GameLogReq_default_instance_;

// -------------------------------------------------------------------

class GameLogRes : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.GameLogRes) */ {
 public:
  GameLogRes();
  virtual ~GameLogRes();

  GameLogRes(const GameLogRes& from);

  inline GameLogRes& operator=(const GameLogRes& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const GameLogRes& default_instance();

  static const GameLogRes* internal_default_instance();

  void Swap(GameLogRes* other);

  // implements Message ----------------------------------------------

  inline GameLogRes* New() const { return New(NULL); }

  GameLogRes* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const GameLogRes& from);
  void MergeFrom(const GameLogRes& from);
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
  void InternalSwap(GameLogRes* other);
  void UnsafeMergeFrom(const GameLogRes& from);
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

  // repeated .proto.GameLogResult gameLog = 2;
  int gamelog_size() const;
  void clear_gamelog();
  static const int kGameLogFieldNumber = 2;
  const ::proto::GameLogResult& gamelog(int index) const;
  ::proto::GameLogResult* mutable_gamelog(int index);
  ::proto::GameLogResult* add_gamelog();
  ::google::protobuf::RepeatedPtrField< ::proto::GameLogResult >*
      mutable_gamelog();
  const ::google::protobuf::RepeatedPtrField< ::proto::GameLogResult >&
      gamelog() const;

  // @@protoc_insertion_point(class_scope:proto.GameLogRes)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::RepeatedPtrField< ::proto::GameLogResult > gamelog_;
  ::proto::ReturnMsg* msg_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_GameLogProto_2eproto_impl();
  friend void  protobuf_AddDesc_GameLogProto_2eproto_impl();
  friend void protobuf_AssignDesc_GameLogProto_2eproto();
  friend void protobuf_ShutdownFile_GameLogProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<GameLogRes> GameLogRes_default_instance_;

// -------------------------------------------------------------------

class GameLogResult : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:proto.GameLogResult) */ {
 public:
  GameLogResult();
  virtual ~GameLogResult();

  GameLogResult(const GameLogResult& from);

  inline GameLogResult& operator=(const GameLogResult& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const GameLogResult& default_instance();

  static const GameLogResult* internal_default_instance();

  void Swap(GameLogResult* other);

  // implements Message ----------------------------------------------

  inline GameLogResult* New() const { return New(NULL); }

  GameLogResult* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const GameLogResult& from);
  void MergeFrom(const GameLogResult& from);
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
  void InternalSwap(GameLogResult* other);
  void UnsafeMergeFrom(const GameLogResult& from);
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

  // optional int32 num = 1;
  void clear_num();
  static const int kNumFieldNumber = 1;
  ::google::protobuf::int32 num() const;
  void set_num(::google::protobuf::int32 value);

  // optional int32 type = 2;
  void clear_type();
  static const int kTypeFieldNumber = 2;
  ::google::protobuf::int32 type() const;
  void set_type(::google::protobuf::int32 value);

  // optional int32 money = 3;
  void clear_money();
  static const int kMoneyFieldNumber = 3;
  ::google::protobuf::int32 money() const;
  void set_money(::google::protobuf::int32 value);

  // @@protoc_insertion_point(class_scope:proto.GameLogResult)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::int32 num_;
  ::google::protobuf::int32 type_;
  ::google::protobuf::int32 money_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_GameLogProto_2eproto_impl();
  friend void  protobuf_AddDesc_GameLogProto_2eproto_impl();
  friend void protobuf_AssignDesc_GameLogProto_2eproto();
  friend void protobuf_ShutdownFile_GameLogProto_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<GameLogResult> GameLogResult_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// GameLogReq

// optional string userId = 1;
inline void GameLogReq::clear_userid() {
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& GameLogReq::userid() const {
  // @@protoc_insertion_point(field_get:proto.GameLogReq.userId)
  return userid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void GameLogReq::set_userid(const ::std::string& value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:proto.GameLogReq.userId)
}
inline void GameLogReq::set_userid(const char* value) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:proto.GameLogReq.userId)
}
inline void GameLogReq::set_userid(const char* value, size_t size) {
  
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:proto.GameLogReq.userId)
}
inline ::std::string* GameLogReq::mutable_userid() {
  
  // @@protoc_insertion_point(field_mutable:proto.GameLogReq.userId)
  return userid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* GameLogReq::release_userid() {
  // @@protoc_insertion_point(field_release:proto.GameLogReq.userId)
  
  return userid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void GameLogReq::set_allocated_userid(::std::string* userid) {
  if (userid != NULL) {
    
  } else {
    
  }
  userid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userid);
  // @@protoc_insertion_point(field_set_allocated:proto.GameLogReq.userId)
}

inline const GameLogReq* GameLogReq::internal_default_instance() {
  return &GameLogReq_default_instance_.get();
}
// -------------------------------------------------------------------

// GameLogRes

// optional .proto.ReturnMsg msg = 1;
inline bool GameLogRes::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void GameLogRes::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::proto::ReturnMsg& GameLogRes::msg() const {
  // @@protoc_insertion_point(field_get:proto.GameLogRes.msg)
  return msg_ != NULL ? *msg_
                         : *::proto::ReturnMsg::internal_default_instance();
}
inline ::proto::ReturnMsg* GameLogRes::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::proto::ReturnMsg;
  }
  // @@protoc_insertion_point(field_mutable:proto.GameLogRes.msg)
  return msg_;
}
inline ::proto::ReturnMsg* GameLogRes::release_msg() {
  // @@protoc_insertion_point(field_release:proto.GameLogRes.msg)
  
  ::proto::ReturnMsg* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void GameLogRes::set_allocated_msg(::proto::ReturnMsg* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:proto.GameLogRes.msg)
}

// repeated .proto.GameLogResult gameLog = 2;
inline int GameLogRes::gamelog_size() const {
  return gamelog_.size();
}
inline void GameLogRes::clear_gamelog() {
  gamelog_.Clear();
}
inline const ::proto::GameLogResult& GameLogRes::gamelog(int index) const {
  // @@protoc_insertion_point(field_get:proto.GameLogRes.gameLog)
  return gamelog_.Get(index);
}
inline ::proto::GameLogResult* GameLogRes::mutable_gamelog(int index) {
  // @@protoc_insertion_point(field_mutable:proto.GameLogRes.gameLog)
  return gamelog_.Mutable(index);
}
inline ::proto::GameLogResult* GameLogRes::add_gamelog() {
  // @@protoc_insertion_point(field_add:proto.GameLogRes.gameLog)
  return gamelog_.Add();
}
inline ::google::protobuf::RepeatedPtrField< ::proto::GameLogResult >*
GameLogRes::mutable_gamelog() {
  // @@protoc_insertion_point(field_mutable_list:proto.GameLogRes.gameLog)
  return &gamelog_;
}
inline const ::google::protobuf::RepeatedPtrField< ::proto::GameLogResult >&
GameLogRes::gamelog() const {
  // @@protoc_insertion_point(field_list:proto.GameLogRes.gameLog)
  return gamelog_;
}

inline const GameLogRes* GameLogRes::internal_default_instance() {
  return &GameLogRes_default_instance_.get();
}
// -------------------------------------------------------------------

// GameLogResult

// optional int32 num = 1;
inline void GameLogResult::clear_num() {
  num_ = 0;
}
inline ::google::protobuf::int32 GameLogResult::num() const {
  // @@protoc_insertion_point(field_get:proto.GameLogResult.num)
  return num_;
}
inline void GameLogResult::set_num(::google::protobuf::int32 value) {
  
  num_ = value;
  // @@protoc_insertion_point(field_set:proto.GameLogResult.num)
}

// optional int32 type = 2;
inline void GameLogResult::clear_type() {
  type_ = 0;
}
inline ::google::protobuf::int32 GameLogResult::type() const {
  // @@protoc_insertion_point(field_get:proto.GameLogResult.type)
  return type_;
}
inline void GameLogResult::set_type(::google::protobuf::int32 value) {
  
  type_ = value;
  // @@protoc_insertion_point(field_set:proto.GameLogResult.type)
}

// optional int32 money = 3;
inline void GameLogResult::clear_money() {
  money_ = 0;
}
inline ::google::protobuf::int32 GameLogResult::money() const {
  // @@protoc_insertion_point(field_get:proto.GameLogResult.money)
  return money_;
}
inline void GameLogResult::set_money(::google::protobuf::int32 value) {
  
  money_ = value;
  // @@protoc_insertion_point(field_set:proto.GameLogResult.money)
}

inline const GameLogResult* GameLogResult::internal_default_instance() {
  return &GameLogResult_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------

// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_GameLogProto_2eproto__INCLUDED
