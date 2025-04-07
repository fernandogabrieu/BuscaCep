package br.edu.utfpr.buscacep.service

import br.edu.utfpr.buscacep.data.model.CepResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ViaCepService(
    private val httpClient: HttpClient
) {
    suspend fun buscarCep(cep: String): CepResult {
        val response = httpClient.get("https://viacep.com.br/ws/$cep/json/") {
            contentType(ContentType.Application.Json)
        }

        val dto = response.body<ViaCepResponse>()
        if (dto.erro == true)
            throw Exception("CEP não encontrado")

        return dto.toCepResult()
    }
}

@Serializable
data class ViaCepResponse(
    @SerialName("cep") val cep: String = "",
    @SerialName("logradouro") val logradouro: String = "",
    @SerialName("bairro") val bairro: String = "",
    @SerialName("localidade") val localidade: String = "",
    @SerialName("uf") val uf: String = "",
    val erro: Boolean? = null
) {
    fun toCepResult(): CepResult = CepResult(
        cep = cep,
        street = logradouro,
        neighborhood = bairro,
        location = localidade,
        state = uf
    )
}