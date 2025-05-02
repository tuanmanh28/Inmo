package com.tuanmanh.inmo.core.network.utils

import com.tuanmanh.inmo.core.model.HabitType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class HabitTypeSerializer : KSerializer<HabitType> {
    override val descriptor = PrimitiveSerialDescriptor("HabitType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): HabitType {
        return HabitType.valueOf(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: HabitType) {
        encoder.encodeString(value.name)
    }
}