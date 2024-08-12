package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    AccountRepository accountRepository;

    public Message addMessage(Message message) {
        Optional <Account> optionalAccount = accountRepository.findById(Long.valueOf(message.getPostedBy()));
        if (!message.getMessageText().isEmpty()
            && message.getMessageText().length() < 256
            && optionalAccount.isPresent()) {
                return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(long messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        Message message = null;

        if (optionalMessage.isPresent()) {
            message = optionalMessage.get();
        }
        
        return message;
    }

    public void deleteMessageById(long messageId) {
        messageRepository.deleteById(messageId);
    }

    public Message updateMessageById(Message message, long messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        Message messageRetrieved = null;

        if (optionalMessage.isPresent()) {
            messageRetrieved = optionalMessage.get();
            messageRetrieved.setMessageText(message.getMessageText());
            messageRepository.save(messageRetrieved);
        }

        optionalMessage = messageRepository.findById(messageId);
        // ^ to retrieve what is in the database

        if (optionalMessage.isPresent()) {
            messageRetrieved = optionalMessage.get();
        }
        
        return messageRetrieved;
    }

    public List<Message> getMessageByUser(Iterable <Long> posted_by) {
        return messageRepository.findAllById(posted_by);
    }
    
}
