package com.tuanmanh.inmo.core.network.utils

import com.tuanmanh.inmo.core.model.HabitStatus
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class HabitStatusSerializer: KSerializer<HabitStatus> {
    override val descriptor = PrimitiveSerialDescriptor("HabitStatus", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): HabitStatus {
        return HabitStatus.valueOf(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: HabitStatus) {
        encoder.encodeString(value.name)
    }
}