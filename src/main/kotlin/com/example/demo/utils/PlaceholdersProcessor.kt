package com.example.demo.utils

import com.example.demo.enums.Placeholder
import com.example.demo.models.dto.SendMessageDto
import com.example.demo.services.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


@Component
class PlaceholdersProcessor(
    private val userService: UserService,
    private val bitcoinPriceUtil: BitcoinPriceUtil
) {

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
            Placeholder.FULL_NAME -> getUserFullName()
            Placeholder.BITCOIN -> getBictoinExchangeRate()
            Placeholder.CONTACT_NAME -> getContactFullName(request.messageTo)
        }
    }

    private fun getUserFullName(): String {
        val user = userService.getUserByNickname(SecurityContextHolder.getContext().authentication.name)
        return "${user.firstName} ${user.lastName}"
    }

    private fun getBictoinExchangeRate(): String {
        return bitcoinPriceUtil.getBitcoinPrice()
    }

    private fun getContactFullName(id: Long): String {
        return userService.getFullNameById(id)
    }

    private fun removePunctuationMarks(placeholder: String): String {
        return placeholder.replace(".", "")
            .replace(":", "")
            .replace(",", "")
            .replace("?", "")
            .replace("!", "")
            .replace(";", "")
            .replace("\"", "")
            .replace("\'", "")
            .replace(")", "")
            .replace("(", "")
    }
}
