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
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.uiState.inputCep,
            onValueChange = viewModel::onCepChanged,
            label = null,
            placeholder = { Text("Digite o CEP")},
            isError = viewModel.uiState.hasError,
            supportingText = {
                if (viewModel.uiState.hasError)
                    Text("Entrada inválida")
            },
        )
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = viewModel::searchCep,
            enabled = viewModel.uiState.isButtonEnabled
        ) {
            if (viewModel.uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Buscar")
            }
        }

        viewModel.uiState.result?.let { result ->
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text("CEP: ${result.cep}")
                Text("Rua: ${result.street}")
                Text("Bairro: ${result.neighborhood}")
                Text("Cidade: ${result.location}")
                Text("Estado: ${result.state}")
            }
        }
    }
}