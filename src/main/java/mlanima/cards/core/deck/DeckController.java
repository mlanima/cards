package mlanima.cards.core.deck;

import mlanima.cards.dtos.requests.DeckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeckController {

    private final DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping("/users/{userId}/decks")
    @PreAuthorize("userId = authentication.principal.getId()")
    public List<Deck> getDecks(@PathVariable Long userId) {
        return deckService.findDecksByUser(userId);
    }

    @GetMapping("/users/{userId}/decks/{deckId}")
    @PreAuthorize("userId = authentication.principal.getId()")
    public Deck getDeck(@PathVariable Long userId, @PathVariable Long deckId) {
        return deckService.findDeck(userId, deckId);
    }


    @PostMapping("/users/{userId}/decks")
    @PreAuthorize("userId = authentication.principal.getId()")
    @ResponseStatus(HttpStatus.CREATED)
    public Deck addDeck(@PathVariable Long userId, @RequestBody DeckDTO deck) {
        return deckService.addDeckAtUser(userId, deck);
    }


    @DeleteMapping("/users/{userId}/decks/{deckId}")
    @PreAuthorize("userId = authentication.principal.getId()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeck(@PathVariable Long userId, @PathVariable Long deckId) {
        deckService.deleteDeckAtUser(deckId);
    }

    @PutMapping("/users/{userId}/decks/{deckId}")
    @PreAuthorize("userId = authentication.principal.getId()")
    public Deck updateDeck(@PathVariable Long userId, @PathVariable Long deckId, @RequestBody DeckDTO deck) {
        return deckService.updateDeck(deckId, deck);
    }



}
