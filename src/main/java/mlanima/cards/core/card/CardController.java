package mlanima.cards.core.card;

import mlanima.cards.dtos.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public List<Card> getCardsInDeck(@PathVariable Long deckId) {
        return cardService.getCardsByDeck(deckId);
    }

    @GetMapping("/{cardId}")
    public Card getCardInDeck(@PathVariable Long deckId, @PathVariable Long cardId) {
        return cardService.getCardByDeckAndId(deckId, cardId);
    }

    @DeleteMapping("/{cardId}")
    public void deleteCardInDeck(@PathVariable Long deckId, @PathVariable Long cardId) {
        cardService.deleteCard(deckId, cardId);
    }

    @PostMapping
    public Card createCardInDeck(@PathVariable Long deckId, @RequestBody CardDTO cardDTO) {
        return cardService.createCard(deckId, cardDTO);
    }

    @PutMapping("/{cardId}")
    public Card updateCardInDeck(@PathVariable Long deckId,@PathVariable Long cardId, @RequestBody CardDTO cardDTO) {
        return cardService.updateCard(deckId, cardId, cardDTO);
    }



}
