package mlanima.cards.core.ai;

import mlanima.cards.core.card.Card;
import mlanima.cards.dtos.requests.AiCardsGenerationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final AIService aiService;

    @Autowired
    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PutMapping("/decks/{deckId}/cards")
    public List<Card> generateCards(@PathVariable Long deckId, @RequestBody AiCardsGenerationRequest input) {
        return aiService.generateCardsFromPrompt(deckId, input);
    }
}
