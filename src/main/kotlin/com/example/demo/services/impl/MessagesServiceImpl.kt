package com.example.demo.services.impl

import com.example.demo.models.dto.ContactDto
import com.example.demo.models.dto.ConversationDto
import com.example.demo.models.dto.ConversationMessageDto
import com.example.demo.models.dto.ConversationsDto
import com.example.demo.models.dto.ExternalConversationDto
import com.example.demo.models.dto.SendMessageDto
import com.example.demo.models.entity.ExternalMessage
import com.example.demo.models.entity.Message
import com.example.demo.repositories.ExternalMessageRepository
import com.example.demo.repositories.MessagesRepository
import com.example.demo.services.ContactService
import com.example.demo.services.MessagesService
import com.example.demo.utils.PlaceholdersProcessor
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class MessagesServiceImpl(
    private val messagesRepo: MessagesRepository,
    private val externalMessageRepository: ExternalMessageRepository,
    private val placeholderProcessor: PlaceholdersProcessor,
    private val contactService: ContactService
) : MessagesService {

    override fun getAllMessages(): ConversationsDto {
        val principleUser = getPrincipleUser()
        val messages = messagesRepo.findAllByMessageFromOrMessageTo(principleUser.id, principleUser.id)
        val externalMessages = externalMessageRepository.findAllByMessageTo(principleUser.id)

        val conversationDtos = messages
            .groupBy { it.toKey() }
            .map { (contact, messageList) ->
                val conversationMessages = messageList.sortedByDescending { it.createdDate }.map {
                    ConversationMessageDto(
                        messageSender = if (it.messageFrom == principleUser.id) principleUser.fullName else contact.fullName,
                        text = it.text,
                        createdDate = it.createdDate!!
                    )
                }
                ConversationDto(
                    contact = contact,
                    lastMessageDate = conversationMessages.first().createdDate,
                    messages = conversationMessages
                )
            }.sortedByDescending { it.lastMessageDate }

        val externalConversationDtos = externalMessages
            .groupBy { it.messageFrom }
            .map { (msgSender, messageList) ->
                val conversationMessages = messageList.sortedByDescending { it.createdDate }
                    .map {
                        ConversationMessageDto(
                            messageSender = msgSender,
                            text = it.text,
                            createdDate = it.createdDate!!
                        )
                    }

                ExternalConversationDto(
                    sender = msgSender,
                    lastMessageDate = conversationMessages.first().createdDate,
                    messages = conversationMessages
                )
            }
        return ConversationsDto(
            internalConversations = conversationDtos,
            externalConversations = externalConversationDtos
        )
    }

    override fun sendNewMessage(request: SendMessageDto): Message {
        val principleUser = getPrincipleUser()
        return messagesRepo.save(
            Message(
                text = placeholderProcessor.processPlaceholders(request),
                messageFrom = principleUser.id,
                messageTo = request.messageTo,
            )
        )
    }

    override fun sendMessageFromExternalSource(
        request: SendMessageDto, serviceName: String
    ): ExternalMessage {
        return externalMessageRepository.save(
            ExternalMessage(
                text = request.text,
                messageFrom = serviceName,
                messageTo = request.messageTo
            )
        )
    }

    private fun Message.toKey(): ContactDto {
        val principleUser = getPrincipleUser()
        val contactId = if (this.messageFrom == principleUser.id) this.messageTo
        else this.messageFrom
        return contactService.getContactByUserId(contactId)
    }

    private fun getPrincipleUser(): ContactDto {
        return contactService.getContactByUsername(SecurityContextHolder.getContext().authentication.name)
    }
}