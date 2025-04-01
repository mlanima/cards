package mlanima.cards.core.card;

import mlanima.cards.dtos.requests.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/users/{userId}/decks/{deckId}/cards")
    @PreAuthorize("userId = authentication.principal.getId()")
    public List<Card> getCardsByDeck(@PathVariable Long deckId, @PathVariable Long userId) {
        return cardService.getCardsByDeck(deckId);
    }

    @PostMapping("/users/{userId}/decks/{deckId}/cards")
    @PreAuthorize("userId = authentication.principal.getId()")
    public Card addCard(@PathVariable Long userId, @PathVariable Long deckId, @RequestBody Card card) {
        return cardService.addCard(deckId, card);
    }

    @PutMapping("/users/{userId}/decks/{deckId}/cards/{cardId}")
    @PreAuthorize("userId = authentication.principal.getId()")
    public Card updateCard(
            @PathVariable Long userId,
            @PathVariable Long deckId,
            @PathVariable Long cardId,
            @RequestBody CardDTO card
    ) {
        return cardService.updateUserCard(deckId, cardId, card);
    }

    @DeleteMapping("/users/{userId}/decks/{deckId}/cards/{cardId}")
    @PreAuthorize("userId = authentication.principal.getId()")
    public void deleteCard(@PathVariable Long userId, @PathVariable Long deckId, @PathVariable Long cardId) {
        cardService.deleteCard(deckId, cardId);
    }

}
