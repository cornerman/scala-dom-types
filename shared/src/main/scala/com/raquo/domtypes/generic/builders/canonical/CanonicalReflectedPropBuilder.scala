package com.raquo.domtypes.generic.builders.canonical

import com.raquo.domtypes.generic.builders.ReflectedAttrBuilder
import com.raquo.domtypes.generic.codecs.Codec
import com.raquo.domtypes.generic.keys.Prop

/** Canonical implementation of [[ReflectedAttrBuilder]], using our own [[Prop]] class.
  *
  * If you are using this implementation, create an implicit value class
  * around [[Prop]] – there you can e.g. implement the `:=` method.
  *
  * Alternatively, you can use [[CanonicalReflectedAttrBuilder]], or implement your own
  * [[ReflectedAttrBuilder]] that uses either a subclass of either [[Prop]] or
  * [[com.raquo.domtypes.generic.keys.Attr]], or a completely unrelated type of your own.
  */
trait CanonicalReflectedPropBuilder extends ReflectedAttrBuilder[Prop] {

  override def reflectedAttr[V, DomPropV](
    attrKey: String,
    propKey: String,
    attrCodec: Codec[V, String],
    propCodec: Codec[V, DomPropV]
  ): Prop[V, DomPropV] = {
    // Note that we use `propKey` and `propCodec` params to build Prop, because it
    // represents a DOM property, not an HTML attribute. If you wanted a representation
    // of an HTML attribute instead, you would use `attrKey` and `attrCodec`.
    new Prop(propKey, propCodec)
  }
}
