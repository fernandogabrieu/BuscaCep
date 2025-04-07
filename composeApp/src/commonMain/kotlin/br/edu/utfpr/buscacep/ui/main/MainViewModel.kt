package br.edu.utfpr.buscacep.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.utfpr.buscacep.service.HttpClientFactory
import br.edu.utfpr.buscacep.service.ViaCepService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val viaCepService = ViaCepService(HttpClientFactory().create())

    var uiState: MainUiState by mutableStateOf(MainUiState())
        private set

    fun onCepChanged(cep: String) {
        uiState = uiState.copy(
            inputCep = cep,
            isValidCep = cep.length == 8 //ou alguma outra validação aqui
        )
    }

    fun searchCep() {
        if (uiState.isLoading) return

        uiState = uiState.copy(
            isLoading = true,
            hasError = false,
            result = null
        )

        viewModelScope.launch {
            try{
                val result = viaCepService.buscarCep(uiState.inputCep)
                uiState = uiState.copy(
                    isLoading = false,
                    result = result
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }
}