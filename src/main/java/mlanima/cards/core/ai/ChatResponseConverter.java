package mlanima.cards.core.ai;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mlanima.cards.dtos.requests.CardRequest;
import mlanima.cards.exceptions.observed.ChatJsonConvertingException;

import java.util.List;

public class ChatResponseConverter {
    public static List<CardRequest> convertToCardDTOList(String chatResponseJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(chatResponseJson, new TypeReference<List<CardRequest>>() {});
        } catch (Exception e) {
            throw new ChatJsonConvertingException();
        }
    }
}
