package com.tuanmanh.inmo.core.network.utils

import com.tuanmanh.inmo.core.model.HabitFrequency
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class HabitFrequencySerializer : KSerializer<HabitFrequency> {
    override val descriptor = PrimitiveSerialDescriptor("HabitFrequency", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): HabitFrequency {
        return HabitFrequency.valueOf(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: HabitFrequency) {
        encoder.encodeString(value.name)
    }
}