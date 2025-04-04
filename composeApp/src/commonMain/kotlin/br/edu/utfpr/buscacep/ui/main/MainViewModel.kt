package br.edu.utfpr.buscacep.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.utfpr.buscacep.data.CepResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {
    var uiState: MainUiState by mutableStateOf(MainUiState())
        private set

    fun onCepChanged(cep: String){
        uiState = uiState.copy(
            inputCep = cep,
            isButtonEnabled = cep.length == 8 //ou alguma outra validação aqui
        )
    }

    fun searchCep() {
        if (uiState.isLoading) return

        uiState = uiState.copy(
            isLoading = true,
            hasError = false
        )

        viewModelScope.launch {
            delay(1500)
            val success = Random.nextBoolean() // chamada da api = true por exemplo
            uiState = if (success) {
                uiState.copy(
                    result = CepResult(
                        cep = "cep exemplo",
                        street = "rua exemplo",
                        neighborhood = "bairro exemplo",
                        location = "localidade exemplo",
                        state = "estado exemplo"
                    ),
                    isLoading = false
                )
            } else {
                uiState.copy(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }
}