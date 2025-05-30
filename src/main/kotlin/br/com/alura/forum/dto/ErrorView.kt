package br.com.alura.forum.dto

import java.time.LocalDateTime

data class ErrorView (
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val erro: String,
    val menssagem: String?,
    val path: String
)
