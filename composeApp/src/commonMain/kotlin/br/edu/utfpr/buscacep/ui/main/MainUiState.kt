package br.edu.utfpr.buscacep.ui.main

import br.edu.utfpr.buscacep.data.model.CepResult

data class MainUiState(
    val inputCep: String = "",
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val isValidCep: Boolean = false,
    val result: CepResult? = null
)
