package com.panko.springBootMvc;

import com.panko.springBootMvc.model.Message;
import com.panko.springBootMvc.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Objects;

@Controller
class GreetingController {

    private final MessageRepository messageRepository;

    public GreetingController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("messages", messageRepository.findAll());

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepository.save(message);

        model.put("messages", messageRepository.findAll());

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (!Objects.nonNull(filter) || filter.isEmpty()) {
            messages = messageRepository.findAll();
        } else {
            messages = messageRepository.findAllByTag(filter);
        }

        model.put("messages", messages);

        return "main";
    }
}