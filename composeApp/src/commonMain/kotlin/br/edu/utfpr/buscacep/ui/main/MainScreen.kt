package br.edu.utfpr.buscacep.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
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
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column (
            modifier = Modifier.align(Alignment.TopStart),
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.uiState.inputCep,
                onValueChange = viewModel::onCepChanged,
                label = null,
                placeholder = { Text("Digite o CEP")},
                isError = viewModel.uiState.hasError,
                enabled = !viewModel.uiState.isLoading
            )
            ElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = viewModel::searchCep,
                enabled = !viewModel.uiState.isLoading && viewModel.uiState.isValidCep
            ) {
                if (viewModel.uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                } else {
                    Text("Buscar")
                }
            }
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text("CEP: ${viewModel.uiState.result?.cep ?: ""}")
                Text("Rua: ${viewModel.uiState.result?.street ?: ""}")
                Text("Bairro: ${viewModel.uiState.result?.neighborhood ?: ""}")
                Text("Cidade: ${viewModel.uiState.result?.location ?: ""}")
                Text("Estado: ${viewModel.uiState.result?.state ?: ""}")
            }
        }
        if (viewModel.uiState.hasError) {
            Snackbar(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                content = { Text("Ocorreu um erro ao consultar o CEP. Aguarde um momento e tente novamente.") }
            )
        }
    }
}