package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
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

    // @Autowired
    // public MessageService(MessageRepository messageRepository){
    //     this.messageRepository = messageRepository;
    // }

    public Message addMessage(Message message) {
        return messageRepository.save(message);
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

    // NOT WORKING
    // public List<Message> getMessageByUser(Iterable posted_by) {
    //     Optional<List> optionalMessage = messageRepository.findAllById(posted_by);
    //     List<Message> messages = null;

    //     if (optionalMessage.isPresent()) {
    //         messageRetrieved = optionalMessage.get();
    //     }
    //     return messages;
    // }
    
}
