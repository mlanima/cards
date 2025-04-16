package mlanima.cards.core.ai;

import mlanima.cards.core.card.Card;
import mlanima.cards.dtos.requests.AiCardsGenerationRequest;
import mlanima.cards.dtos.responses.CardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<CardResponse>>  generateCards(@PathVariable String deckName, @RequestBody AiCardsGenerationRequest input) {
        List<CardResponse> responseList = aiService.generateCardsFromPrompt(deckName, input).stream()
                .map(CardResponse::build)
                .toList();

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath() // excludes controller's @RequestMapping prefix
                .path("/decks/{deckId}/cards")
                .buildAndExpand(deckName)
                .toUri();

        return ResponseEntity.created(location).body(responseList);
    }
}
