package com.github.willor777.models

import kotlinx.serialization.Serializable


@Serializable
data class LoginRespDTO (val token: String, val expiry: Long)