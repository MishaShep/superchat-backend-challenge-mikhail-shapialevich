package com.example.demo.utils

import com.example.demo.enums.Placeholder
import com.example.demo.models.dto.SendMessageDto
import com.example.demo.services.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


@Component
class PlaceholdersProcessor(
    private val userService: UserService,
    private val bitcoinPriceUtil: BitcoinPriceUtil
) {

    @Value("\${punctuation.marks}")
    lateinit var punctuationMarks: String

    fun processPlaceholders(request: SendMessageDto): String {
        val placeholders = getPlaceholdersSet(request.text)
        val placeholdersValues = getPlaceholdersValue(placeholders, request)
        var replacedText = request.text
        placeholdersValues.forEach {
            replacedText = replacedText.replace(it.key, it.value)
        }
        return replacedText
    }

    private fun getPlaceholdersSet(text: String): Set<Placeholder> {
        return text.split(" ")
            .filter { it.startsWith("@") }
            .map { removePunctuationMarks(it) }
            .map { Placeholder.from(it)!! }.toSet()
    }

    private fun getPlaceholdersValue(placeholders: Set<Placeholder>, request: SendMessageDto): Map<String, String> {
        return placeholders.associate { it.value to processPlaceholder(it, request) }
    }

    private fun processPlaceholder(placeholder: Placeholder, request: SendMessageDto): String {
        return when (placeholder) {
            Placeholder.FULL_NAME -> {
                userService.getUserByNickname(SecurityContextHolder.getContext().authentication.name).getFullName()
            }

            Placeholder.BITCOIN -> bitcoinPriceUtil.getBitcoinPrice()
            Placeholder.CONTACT_NAME -> userService.getFullNameById(request.messageTo)
        }
    }

    private fun removePunctuationMarks(placeholder: String): String {
        var updatedPlaceholder = placeholder
        for (mark in punctuationMarks) {
            updatedPlaceholder = updatedPlaceholder.replace(mark.toString(), "")
        }
        return updatedPlaceholder
    }
}
