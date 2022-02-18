package io.lojong.com.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fact(
    @NonNull
    @PrimaryKey
    val id: Int,
    val text: String?
)