package mlanima.cards.core.card;

import mlanima.cards.dtos.requests.CardRequest;
import mlanima.cards.dtos.responses.CardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/decks/{deckId}/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<CardResponse> getCardsInDeck(@PathVariable Long deckId) {
        return cardService.getCardsByDeck(deckId).stream().map(CardResponse::build).toList();

    }

    @GetMapping("/{cardId}")
    public CardResponse getCardInDeck(@PathVariable Long deckId, @PathVariable Long cardId) {
        return CardResponse.build(cardService.getCardByDeckAndId(deckId, cardId));
    }

    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardInDeck(@PathVariable Long deckId, @PathVariable Long cardId) {
        cardService.deleteCard(deckId, cardId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CardResponse> createCardInDeck(@PathVariable Long deckId, @RequestBody CardRequest cardRequest) {
        CardResponse cardResponse = CardResponse.build(cardService.createCard(deckId, cardRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cardResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(cardResponse);
    }

    @PutMapping("/{cardId}")
    public CardResponse updateCardInDeck(@PathVariable Long deckId, @PathVariable Long cardId, @RequestBody CardRequest cardRequest) {
        return CardResponse.build(cardService.updateCard(deckId, cardId, cardRequest));
    }



}
