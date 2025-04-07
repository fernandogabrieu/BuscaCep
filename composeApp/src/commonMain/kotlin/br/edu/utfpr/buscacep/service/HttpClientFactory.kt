package br.edu.utfpr.buscacep.service

import io.ktor.client.*

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class HttpClientFactory() {
    fun create(): HttpClient
}
