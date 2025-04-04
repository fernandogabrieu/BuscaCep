package br.edu.utfpr.buscacep.ui.main

import br.edu.utfpr.buscacep.data.CepResult

data class MainUiState(
    val inputCep: String = "",
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val result: CepResult? = null
) {
    val success get(): Boolean = !isLoading && !hasError
}
