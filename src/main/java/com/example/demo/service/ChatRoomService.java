package com.example.demo.service;

import com.example.demo.domain.ChatRoom;
import com.example.demo.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public Long save(ChatRoom chatRoom){
        chatRoomRepository.save(chatRoom);

        return chatRoom.getId();

    }

    public ChatRoom findById(Long id){
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(id);

        return optionalChatRoom.get();
    }

    public List<ChatRoom> findAll(){
        return chatRoomRepository.findAllByOrderByIdDesc();
    }
}
