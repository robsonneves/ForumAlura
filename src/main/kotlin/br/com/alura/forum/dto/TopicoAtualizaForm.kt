package br.com.alura.forum.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicoAtualizaForm (

    @field:NotNull
    val id: Long,
    @field:NotNull @field:Size(min = 5, max = 100)
    val titulo: String,
    @field:NotNull
    val mensagem: String
)
