package mlanima.cards.core.card;

import mlanima.cards.dtos.requests.CardRequest;
import mlanima.cards.dtos.responses.CardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/decks/{deckName}/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<CardResponse> getCardsInDeck(@PathVariable String deckName) {
        return cardService.getCardsByDeck(deckName).stream().map(CardResponse::build).toList();

    }

    @GetMapping("/{cardId}")
    public CardResponse getCardInDeck(@PathVariable String deckName, @PathVariable Long cardId) {
        return CardResponse.build(cardService.getCardByDeckAndId(deckName, cardId));
    }

    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardInDeck(@PathVariable String deckName, @PathVariable Long cardId) {
        cardService.deleteCard(deckName, cardId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CardResponse> createCardInDeck(@PathVariable String deckName, @RequestBody CardRequest cardRequest) {
        CardResponse cardResponse = CardResponse.build(cardService.createCard(deckName, cardRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cardResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(cardResponse);
    }

    @PutMapping("/{cardId}")
    public CardResponse updateCardInDeck(@PathVariable String deckName,@PathVariable Long cardId, @RequestBody CardRequest cardRequest) {
        return CardResponse.build(cardService.updateCard(deckName, cardId, cardRequest));
    }



}
