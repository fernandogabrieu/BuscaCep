package br.edu.utfpr.buscacep.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val isLoading = false
    val hasError = false

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "oi", // TODO uiState.result
            onValueChange = {}, // TODO onCepChanged
            label = null,
            isError = false, // TODO uiState.hasError
            supportingText = {
                if (hasError) Text("Entrada inválida")
            },
        )
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}, //TODO onSearchClick
            enabled = true // TODO uiState.isButtonEnabled
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Buscar")
            }
        }

        Text("CEP: ") // TODO  $uiState.cep")
        Text("Logradouro: ") // TODO  $uiState.logradouro")
        Text("Bairro: ") // TODO  $uiState.bairro")
        Text("Localidade: ") // TODO  $uiState.localidade")
        Text("UF: ") // TODO  $uiState.uf")
    }
}