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
        Optional <Account> optionalAccount = accountRepository.findById(message.getPostedBy());
        
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

    public Message getMessageById(int messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        //Message message = null;

        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            return message;
        }
        
        return null;
    }

    public Message deleteMessageById(int messageId) {
        // messageRepository.deleteById(messageId);
        Optional<Message> optionalMessage = messageRepository.findById(messageId);

        if (optionalMessage.isPresent()) {
            Message messageObject = optionalMessage.get();

            messageRepository.delete(messageObject);

            return messageObject;
        }
        
        return null;
    }

    public Message updateMessageById(Message message, int messageId) {
        // if (message == null || message.getMessageText() == null) {
        //     return null;
        // }
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        // Message messageRetrieved = null;
        // need to have the logic conditions for update message.

        if (!message.getMessageText().isEmpty()
            && message.getMessageText().length() <= 255
            && optionalMessage.isPresent()) {
            Message messageRetrieved = optionalMessage.get();
            
            messageRetrieved.setMessageText(message.getMessageText());
            return messageRepository.save(messageRetrieved);

            //return messageRetrieved;
        }

        // optionalMessage = messageRepository.findById(messageId);
        // // ^ to retrieve what is in the database

        // if (optionalMessage.isPresent()) {
        //     messageRetrieved = optionalMessage.get();
        // }
        
        // return messageRetrieved;
        return null;
    }

    public List<Message> getMessageByUser(int id) {
        return messageRepository.findByPostedBy(id);
    }
    
}
