package com.example.controller;

import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.Account;
import com.example.entity.Message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;
    
    public SocialMediaController( MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        Account accountObject = accountService.addAccount(account);
        
        if (accountObject != null) {
            return ResponseEntity.status(200).body(account);
        }

        if (accountService.findByUsername(account.getUsername()) != null) {
            return ResponseEntity.status(409).body(null);
        }

        return ResponseEntity.status(400).body(null);

        //return account;
        //success is 200 JSON of the acct
        //if unsuccessful, duplicate - response status is 409
        //if unsuccessful, others - response status is 400
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account accountObject = accountService.loginAccount(account);

        if (accountObject != null) {
            return ResponseEntity.status(200).body(account);
        }

        return ResponseEntity.status(401).body(null);
        //success is 200 JSON of the acct
        //if unsuccessful, unauthorized - response status is 400
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> addMessages(@RequestBody Message message) {
        Message messageObject = messageService.addMessage(message);

        if (messageObject != null) {
            return ResponseEntity.status(200).body(messageObject);
        }

        return ResponseEntity.status(400).body(null);
        //success is 200 JSON of the message
        //if unsuccessful, others - response status is 400
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(@RequestBody List<Message> message) {
        List<Message> messageObject = messageService.getAllMessages();

        return ResponseEntity.status(200).body(messageObject);
        //status will always be 200;
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable long messageId, @RequestBody Message message) {
        Message messageObject = messageService.getMessageById(messageId);
        
        return ResponseEntity.status(200).body(messageObject);
        //status will always be 200;
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Message> deleteMessageById(@PathVariable long messageId, @RequestBody Message message) {
        Message messageObject = messageService.getMessageById(messageId);
        
        return ResponseEntity.status(200).body(messageObject);
        //status will always be 200;
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Message> updateMessageById(@PathVariable long messageId, @RequestBody Message message) {
        Message messageObject = messageService.updateMessageById(message, messageId);

        if (messageObject != null) {
            return ResponseEntity.status(200).body(messageObject);
        }

        return ResponseEntity.status(400).body(null);
        //success is 200 JSON of the message
        //if unsuccessful, not found/updated - response status is 400
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable Iterable<Long> accountId, @RequestBody List<Message> message) {
        List<Message> messageObject = messageService.getMessageByUser(accountId);

        return ResponseEntity.status(200).body(messageObject);
       //status will always be 200;
    }

}
