package br.edu.utfpr.buscacep.data.model

data class CepResult(
    val cep: String = "",
    val street: String = "",
    val neighborhood: String = "",
    val location: String = "",
    val state: String = ""
)
