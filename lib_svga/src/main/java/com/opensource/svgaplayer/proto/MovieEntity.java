// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: svga.proto at 123:1
package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.List;
import java.util.Map;
import okio.ByteString;

public final class MovieEntity extends Message<MovieEntity, MovieEntity.Builder> {
  public static final ProtoAdapter<MovieEntity> ADAPTER = new ProtoAdapter_MovieEntity();
  public static final String DEFAULT_VERSION = "";
  private static final long serialVersionUID = 0L;
  /**
   * SVGA 格式版本号
   */
  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String version;

  /**
   * 动画参数
   */
  @WireField(
      tag = 2,
      adapter = "com.opensource.svgaplayer.proto.MovieParams#ADAPTER"
  )
  public final MovieParams params;

  /**
   * Key 是位图键名，Value 是位图文件名或二进制 PNG 数据。
   */
  @WireField(
      tag = 3,
      keyAdapter = "com.squareup.wire.ProtoAdapter#STRING",
      adapter = "com.squareup.wire.ProtoAdapter#BYTES"
  )
  public final Map<String, ByteString> images;

  /**
   * 元素列表
   */
  @WireField(
      tag = 4,
      adapter = "com.opensource.svgaplayer.proto.SpriteEntity#ADAPTER",
      label = WireField.Label.REPEATED
  )
  public final List<SpriteEntity> sprites;

  /**
   * 音频列表
   */
  @WireField(
      tag = 5,
      adapter = "com.opensource.svgaplayer.proto.AudioEntity#ADAPTER",
      label = WireField.Label.REPEATED
  )
  public final List<AudioEntity> audios;

  public MovieEntity(String version, MovieParams params, Map<String, ByteString> images, List<SpriteEntity> sprites, List<AudioEntity> audios) {
    this(version, params, images, sprites, audios, ByteString.EMPTY);
  }

  public MovieEntity(String version, MovieParams params, Map<String, ByteString> images, List<SpriteEntity> sprites, List<AudioEntity> audios, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.version = version;
    this.params = params;
    this.images = Internal.immutableCopyOf("images", images);
    this.sprites = Internal.immutableCopyOf("sprites", sprites);
    this.audios = Internal.immutableCopyOf("audios", audios);
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.version = version;
    builder.params = params;
    builder.images = Internal.copyOf("images", images);
    builder.sprites = Internal.copyOf("sprites", sprites);
    builder.audios = Internal.copyOf("audios", audios);
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof MovieEntity)) return false;
    MovieEntity o = (MovieEntity) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(version, o.version)
        && Internal.equals(params, o.params)
        && images.equals(o.images)
        && sprites.equals(o.sprites)
        && audios.equals(o.audios);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (version != null ? version.hashCode() : 0);
      result = result * 37 + (params != null ? params.hashCode() : 0);
      result = result * 37 + images.hashCode();
      result = result * 37 + sprites.hashCode();
      result = result * 37 + audios.hashCode();
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (version != null) builder.append(", version=").append(version);
    if (params != null) builder.append(", params=").append(params);
    if (!images.isEmpty()) builder.append(", images=").append(images);
    if (!sprites.isEmpty()) builder.append(", sprites=").append(sprites);
    if (!audios.isEmpty()) builder.append(", audios=").append(audios);
    return builder.replace(0, 2, "MovieEntity{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<MovieEntity, Builder> {
    public String version;

    public MovieParams params;

    public Map<String, ByteString> images;

    public List<SpriteEntity> sprites;

    public List<AudioEntity> audios;

    public Builder() {
      images = Internal.newMutableMap();
      sprites = Internal.newMutableList();
      audios = Internal.newMutableList();
    }

    /**
     * SVGA 格式版本号
     */
    public Builder version(String version) {
      this.version = version;
      return this;
    }

    /**
     * 动画参数
     */
    public Builder params(MovieParams params) {
      this.params = params;
      return this;
    }

    /**
     * Key 是位图键名，Value 是位图文件名或二进制 PNG 数据。
     */
    public Builder images(Map<String, ByteString> images) {
      Internal.checkElementsNotNull(images);
      this.images = images;
      return this;
    }

    /**
     * 元素列表
     */
    public Builder sprites(List<SpriteEntity> sprites) {
      Internal.checkElementsNotNull(sprites);
      this.sprites = sprites;
      return this;
    }

    /**
     * 音频列表
     */
    public Builder audios(List<AudioEntity> audios) {
      Internal.checkElementsNotNull(audios);
      this.audios = audios;
      return this;
    }

    @Override
    public MovieEntity build() {
      return new MovieEntity(version, params, images, sprites, audios, super.buildUnknownFields());
    }
  }

  private static final class ProtoAdapter_MovieEntity extends ProtoAdapter<MovieEntity> {
    private final ProtoAdapter<Map<String, ByteString>> images = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.BYTES);

    ProtoAdapter_MovieEntity() {
      super(FieldEncoding.LENGTH_DELIMITED, MovieEntity.class);
    }

    @Override
    public int encodedSize(MovieEntity value) {
      return (value.version != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value.version) : 0)
          + (value.params != null ? MovieParams.ADAPTER.encodedSizeWithTag(2, value.params) : 0)
          + images.encodedSizeWithTag(3, value.images)
          + SpriteEntity.ADAPTER.asRepeated().encodedSizeWithTag(4, value.sprites)
          + AudioEntity.ADAPTER.asRepeated().encodedSizeWithTag(5, value.audios)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, MovieEntity value) throws IOException {
      if (value.version != null) ProtoAdapter.STRING.encodeWithTag(writer, 1, value.version);
      if (value.params != null) MovieParams.ADAPTER.encodeWithTag(writer, 2, value.params);
      images.encodeWithTag(writer, 3, value.images);
      SpriteEntity.ADAPTER.asRepeated().encodeWithTag(writer, 4, value.sprites);
      AudioEntity.ADAPTER.asRepeated().encodeWithTag(writer, 5, value.audios);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public MovieEntity decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.version(ProtoAdapter.STRING.decode(reader)); break;
          case 2: builder.params(MovieParams.ADAPTER.decode(reader)); break;
          case 3: builder.images.putAll(images.decode(reader)); break;
          case 4: builder.sprites.add(SpriteEntity.ADAPTER.decode(reader)); break;
          case 5: builder.audios.add(AudioEntity.ADAPTER.decode(reader)); break;
          default: {
            FieldEncoding fieldEncoding = reader.peekFieldEncoding();
            Object value = fieldEncoding.rawProtoAdapter().decode(reader);
            builder.addUnknownField(tag, fieldEncoding, value);
          }
        }
      }
      reader.endMessage(token);
      return builder.build();
    }

    @Override
    public MovieEntity redact(MovieEntity value) {
      Builder builder = value.newBuilder();
      if (builder.params != null) builder.params = MovieParams.ADAPTER.redact(builder.params);
      Internal.redactElements(builder.sprites, SpriteEntity.ADAPTER);
      Internal.redactElements(builder.audios, AudioEntity.ADAPTER);
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
