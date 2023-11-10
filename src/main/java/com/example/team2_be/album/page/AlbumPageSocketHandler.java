package com.example.team2_be.album.page;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumPageSocketHandler {
    @MessageMapping("/subscribe/{pageId}")
    @SendTo("/pages/{pageId}")
    public void subscribePage(@DestinationVariable Long pageId) {

    }
}
