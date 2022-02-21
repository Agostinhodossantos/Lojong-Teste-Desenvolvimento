package io.lojong.com.model

import androidx.annotation.NonNull
import androidx.core.widget.TextViewCompat
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fact(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String?
)