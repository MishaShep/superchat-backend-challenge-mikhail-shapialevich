package com.example.demo.utils

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
class BitcoinPriceUtil {

    fun getBitcoinPrice(): String {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI("https://api.coincap.io/v2/assets/bitcoin"))
            .GET()
            .build()
        val response: HttpResponse<String> = client.send(request, HttpResponse.BodyHandlers.ofString())
        val bitcoin = jacksonObjectMapper().readValue(response.body(), Bitcoin::class.java)
        return "\$${"%.2f".format(bitcoin.data.priceUsd)}"
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Bitcoin(
        val data: BitcoinData
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class BitcoinData(
        val priceUsd: Float
    )
}
