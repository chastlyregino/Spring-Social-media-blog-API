package com.example.controller;

import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.Account;
import com.example.entity.Message;

import java.util.List;

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
    // MessageService messageService;
    // AccountService accountService;

    // public SocialMediaController(){
    //     this.messageService = new MessageService();
    //     this.accountService = new AccountService();
    // }

    @PostMapping("/register")
    public @ResponseBody Account register(@RequestBody Account account) {
        return account;
        //success is 200 JSON of the acct
        //if unsuccessful, duplicate - response status is 409
        //if unsuccessful, others - response status is 400
    }

    @PostMapping("/login")
    public @ResponseBody Account login(@RequestBody Account account) {
        return account;
        //success is 200 JSON of the acct
        //if unsuccessful, unauthorized - response status is 400
    }

    @PostMapping("/messages")
    public @ResponseBody Message addMessages(@RequestBody Message message) {
        return message;
        //success is 200 JSON of the message
        //if unsuccessful, others - response status is 400
    }

    @GetMapping("/messages")
    public @ResponseBody List<Message> getAllMessages(@RequestBody List<Message> message) {
        return message;
        //status will always be 200;
    }

    @GetMapping("/messages/{messageId}")
    public @ResponseBody Message getMessageById(@RequestBody Message message) {
        return message;
        //status will always be 200;
    }

    @DeleteMapping("/messages/{messageId}")
    public @ResponseBody Message deleteMessageById(@RequestBody Message message) {
        return message;
        //status will always be 200;
    }

    @PatchMapping("/messages/{messageId}")
    public @ResponseBody Message updateMessageById(@RequestBody Message message) {
        return message;
        //success is 200 JSON of the message
        //if unsuccessful, not found/updated - response status is 400
    }

    @GetMapping("/accounts/{accountId}/messages")
    public @ResponseBody List<Message> getMessagesByAccountId(@RequestBody List<Message> message) {
        return message;
       //status will always be 200;
    }

}
