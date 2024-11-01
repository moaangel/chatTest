package com.example.demo.controller;


import com.example.demo.domain.Chat;
import com.example.demo.domain.ChatRoom;
import com.example.demo.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping("")
    public String mainPage(Model model) {
        model.addAttribute("chatRooms", chatRoomService.findAll());
        return "main";
    }

    @GetMapping("/chat/create")
    public String createChatRoomForm(Model model) {
        model.addAttribute("chatRoom", new ChatRoom());
        return "create-chat-room";
    }

    @PostMapping("/chat/create")
    public String createChatRoom(@ModelAttribute ChatRoom chatRoom) {
        Long roomId = chatRoomService.save(chatRoom);
        return "redirect:/chat/" + roomId;
    }

    @GetMapping("/chat/{roomId}")
    public String roomPage(@PathVariable Long roomId, Model model) {
        ChatRoom chatRoom = chatRoomService.findById(roomId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("roomName", chatRoom.getRoomName());
        return "chat-room";
    }

    @MessageMapping("/chat/{roomId}")//client to server
    @SendTo("/sub/chat/{roomId}")//server to client
    public Chat processChat(@RequestBody Chat chat) {
        return chat;
    }
}