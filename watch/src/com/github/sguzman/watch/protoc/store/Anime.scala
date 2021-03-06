// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.sguzman.watch.protoc.store

@SerialVersionUID(0L)
final case class Anime(
    summary: scala.Option[com.github.sguzman.watch.protoc.store.AnimeSummary] = None,
    alt: _root_.scala.Predef.String = "",
    rank: _root_.scala.Int = 0,
    id: _root_.scala.Long = 0L,
    url: _root_.scala.Predef.String = ""
    ) extends scalapb.GeneratedMessage with scalapb.Message[Anime] with scalapb.lenses.Updatable[Anime] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      if (summary.isDefined) { __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(summary.get.serializedSize) + summary.get.serializedSize }
      if (alt != "") { __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(2, alt) }
      if (rank != 0) { __size += _root_.com.google.protobuf.CodedOutputStream.computeUInt32Size(3, rank) }
      if (id != 0L) { __size += _root_.com.google.protobuf.CodedOutputStream.computeUInt64Size(4, id) }
      if (url != "") { __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(5, url) }
      __size
    }
    final override def serializedSize: _root_.scala.Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): Unit = {
      summary.foreach { __v =>
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
      {
        val __v = alt
        if (__v != "") {
          _output__.writeString(2, __v)
        }
      };
      {
        val __v = rank
        if (__v != 0) {
          _output__.writeUInt32(3, __v)
        }
      };
      {
        val __v = id
        if (__v != 0L) {
          _output__.writeUInt64(4, __v)
        }
      };
      {
        val __v = url
        if (__v != "") {
          _output__.writeString(5, __v)
        }
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.github.sguzman.watch.protoc.store.Anime = {
      var __summary = this.summary
      var __alt = this.alt
      var __rank = this.rank
      var __id = this.id
      var __url = this.url
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __summary = Option(_root_.scalapb.LiteParser.readMessage(_input__, __summary.getOrElse(com.github.sguzman.watch.protoc.store.AnimeSummary.defaultInstance)))
          case 18 =>
            __alt = _input__.readString()
          case 24 =>
            __rank = _input__.readUInt32()
          case 32 =>
            __id = _input__.readUInt64()
          case 42 =>
            __url = _input__.readString()
          case tag => _input__.skipField(tag)
        }
      }
      com.github.sguzman.watch.protoc.store.Anime(
          summary = __summary,
          alt = __alt,
          rank = __rank,
          id = __id,
          url = __url
      )
    }
    def getSummary: com.github.sguzman.watch.protoc.store.AnimeSummary = summary.getOrElse(com.github.sguzman.watch.protoc.store.AnimeSummary.defaultInstance)
    def clearSummary: Anime = copy(summary = None)
    def withSummary(__v: com.github.sguzman.watch.protoc.store.AnimeSummary): Anime = copy(summary = Option(__v))
    def withAlt(__v: _root_.scala.Predef.String): Anime = copy(alt = __v)
    def withRank(__v: _root_.scala.Int): Anime = copy(rank = __v)
    def withId(__v: _root_.scala.Long): Anime = copy(id = __v)
    def withUrl(__v: _root_.scala.Predef.String): Anime = copy(url = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => summary.orNull
        case 2 => {
          val __t = alt
          if (__t != "") __t else null
        }
        case 3 => {
          val __t = rank
          if (__t != 0) __t else null
        }
        case 4 => {
          val __t = id
          if (__t != 0L) __t else null
        }
        case 5 => {
          val __t = url
          if (__t != "") __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => summary.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => _root_.scalapb.descriptors.PString(alt)
        case 3 => _root_.scalapb.descriptors.PInt(rank)
        case 4 => _root_.scalapb.descriptors.PLong(id)
        case 5 => _root_.scalapb.descriptors.PString(url)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.github.sguzman.watch.protoc.store.Anime
}

object Anime extends scalapb.GeneratedMessageCompanion[com.github.sguzman.watch.protoc.store.Anime] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[com.github.sguzman.watch.protoc.store.Anime] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.github.sguzman.watch.protoc.store.Anime = {
    require(__fieldsMap.keys.forall(_.getContainingType() == javaDescriptor), "FieldDescriptor does not match message type.")
    val __fields = javaDescriptor.getFields
    com.github.sguzman.watch.protoc.store.Anime(
      __fieldsMap.get(__fields.get(0)).asInstanceOf[scala.Option[com.github.sguzman.watch.protoc.store.AnimeSummary]],
      __fieldsMap.getOrElse(__fields.get(1), "").asInstanceOf[_root_.scala.Predef.String],
      __fieldsMap.getOrElse(__fields.get(2), 0).asInstanceOf[_root_.scala.Int],
      __fieldsMap.getOrElse(__fields.get(3), 0L).asInstanceOf[_root_.scala.Long],
      __fieldsMap.getOrElse(__fields.get(4), "").asInstanceOf[_root_.scala.Predef.String]
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[com.github.sguzman.watch.protoc.store.Anime] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      require(__fieldsMap.keys.forall(_.containingMessage == scalaDescriptor), "FieldDescriptor does not match message type.")
      com.github.sguzman.watch.protoc.store.Anime(
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[scala.Option[com.github.sguzman.watch.protoc.store.AnimeSummary]]),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Int]).getOrElse(0),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).map(_.as[_root_.scala.Long]).getOrElse(0L),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(5).get).map(_.as[_root_.scala.Predef.String]).getOrElse("")
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = StoreProto.javaDescriptor.getMessageTypes.get(1)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = StoreProto.scalaDescriptor.messages(1)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = com.github.sguzman.watch.protoc.store.AnimeSummary
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = com.github.sguzman.watch.protoc.store.Anime(
  )
  implicit class AnimeLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, com.github.sguzman.watch.protoc.store.Anime]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, com.github.sguzman.watch.protoc.store.Anime](_l) {
    def summary: _root_.scalapb.lenses.Lens[UpperPB, com.github.sguzman.watch.protoc.store.AnimeSummary] = field(_.getSummary)((c_, f_) => c_.copy(summary = Option(f_)))
    def optionalSummary: _root_.scalapb.lenses.Lens[UpperPB, scala.Option[com.github.sguzman.watch.protoc.store.AnimeSummary]] = field(_.summary)((c_, f_) => c_.copy(summary = f_))
    def alt: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.alt)((c_, f_) => c_.copy(alt = f_))
    def rank: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.rank)((c_, f_) => c_.copy(rank = f_))
    def id: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Long] = field(_.id)((c_, f_) => c_.copy(id = f_))
    def url: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.url)((c_, f_) => c_.copy(url = f_))
  }
  final val SUMMARY_FIELD_NUMBER = 1
  final val ALT_FIELD_NUMBER = 2
  final val RANK_FIELD_NUMBER = 3
  final val ID_FIELD_NUMBER = 4
  final val URL_FIELD_NUMBER = 5
}
