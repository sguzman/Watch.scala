// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.sguzman.watch.protoc.store

object StoreProto extends _root_.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.scalapb.GeneratedFileObject] = Seq(
  )
  lazy val messagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_]] = Seq(
    com.github.sguzman.watch.protoc.store.AnimeSummary,
    com.github.sguzman.watch.protoc.store.Anime,
    com.github.sguzman.watch.protoc.store.UserStats,
    com.github.sguzman.watch.protoc.store.AnimeUser,
    com.github.sguzman.watch.protoc.store.StoreCache
  )
  private lazy val ProtoBytes: Array[Byte] =
      scalapb.Encoding.fromBase64(scala.collection.Seq(
  """Citjb20vZ2l0aHViL3NndXptYW4vd2F0Y2gvcHJvdG9jL3N0b3JlLnByb3RvEh9jb20uZ2l0aHViLnNndXptYW4ud2F0Y2guc
  HJvdG9jItYBCgxBbmltZVN1bW1hcnkSFAoFdGl0bGUYASABKAlSBXRpdGxlEhAKA2ltZxgCIAEoCVIDaW1nEhIKBGxpbmsYAyABK
  AlSBGxpbmsSEgoEZGVzYxgEIAEoCVIEZGVzYxIWCgZzdHVkaW8YBSABKAlSBnN0dWRpbxISCgR5ZWFyGAYgASgJUgR5ZWFyEhYKB
  nJhdGluZxgHIAEoAVIGcmF0aW5nEhoKCHNob3dUeXBlGAggASgJUghzaG93VHlwZRIWCgZnZW5yZXMYCSADKAlSBmdlbnJlcyKYA
  QoFQW5pbWUSRwoHc3VtbWFyeRgBIAEoCzItLmNvbS5naXRodWIuc2d1em1hbi53YXRjaC5wcm90b2MuQW5pbWVTdW1tYXJ5Ugdzd
  W1tYXJ5EhAKA2FsdBgCIAEoCVIDYWx0EhIKBHJhbmsYAyABKA1SBHJhbmsSDgoCaWQYBCABKARSAmlkEhAKA3VybBgFIAEoCVIDd
  XJsIrUBCglVc2VyU3RhdHMSGAoHd2F0Y2hlZBgBIAEoDVIHd2F0Y2hlZBIaCgh3YXRjaGluZxgCIAEoDVIId2F0Y2hpbmcSIAoLd
  2FudFRvV2F0Y2gYAyABKA1SC3dhbnRUb1dhdGNoEhgKB3N0YWxsZWQYBCABKA1SB3N0YWxsZWQSGAoHZHJvcHBlZBgFIAEoDVIHZ
  HJvcHBlZBIcCgl3b250V2F0Y2gYBiABKA1SCXdvbnRXYXRjaCKJAQoJQW5pbWVVc2VyEjwKBWFuaW1lGAEgASgLMiYuY29tLmdpd
  Gh1Yi5zZ3V6bWFuLndhdGNoLnByb3RvYy5BbmltZVIFYW5pbWUSPgoEdXNlchgCIAEoCzIqLmNvbS5naXRodWIuc2d1em1hbi53Y
  XRjaC5wcm90b2MuVXNlclN0YXRzUgR1c2VyIsABCgpTdG9yZUNhY2hlEkwKBWNhY2hlGAEgAygLMjYuY29tLmdpdGh1Yi5zZ3V6b
  WFuLndhdGNoLnByb3RvYy5TdG9yZUNhY2hlLkNhY2hlRW50cnlSBWNhY2hlGmQKCkNhY2hlRW50cnkSEAoDa2V5GAEgASgJUgNrZ
  XkSQAoFdmFsdWUYAiABKAsyKi5jb20uZ2l0aHViLnNndXptYW4ud2F0Y2gucHJvdG9jLkFuaW1lVXNlclIFdmFsdWU6AjgBYgZwc
  m90bzM="""
      ).mkString)
  lazy val scalaDescriptor: _root_.scalapb.descriptors.FileDescriptor = {
    val scalaProto = com.google.protobuf.descriptor.FileDescriptorProto.parseFrom(ProtoBytes)
    _root_.scalapb.descriptors.FileDescriptor.buildFrom(scalaProto, dependencies.map(_.scalaDescriptor))
  }
  lazy val javaDescriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val javaProto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(ProtoBytes)
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(javaProto, Array(
    ))
  }
  @deprecated("Use javaDescriptor instead. In a future version this will refer to scalaDescriptor.", "ScalaPB 0.5.47")
  def descriptor: com.google.protobuf.Descriptors.FileDescriptor = javaDescriptor
}