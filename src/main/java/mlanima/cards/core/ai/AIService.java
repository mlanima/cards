package mlanima.cards.core.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import mlanima.cards.core.card.Card;
import mlanima.cards.core.card.CardRepository;
import mlanima.cards.core.card.CardService;
import mlanima.cards.dtos.CardDTO;
import mlanima.cards.dtos.requests.AiCardsGenerationRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIService {

    private final ChatClient client;
    private final CardService cardService;

    @Autowired
    public AIService(ChatClient.Builder builder, CardService cardService) {
        this.client = builder.build();
        this.cardService = cardService;
    }

    public List<Card> generateCardsFromPrompt(Long deckId, AiCardsGenerationRequest input) {
        ChatResponse response = this.client.prompt(
                new Prompt(List.of(
                        PromptMessages.generateCardsMesssage,
                        new UserMessage(input.getInput())
                    )
                )
        ).call().chatResponse();

        System.out.println(response.getResult().getOutput().getText());

        List<CardDTO> cards= ChatResponseConverter.convertToCardDTOList(response.getResult().getOutput().getText());

        return cardService.createCards(deckId, cards);
    }

}
