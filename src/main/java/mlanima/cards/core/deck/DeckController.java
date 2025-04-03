package mlanima.cards.core.deck;

import mlanima.cards.dtos.DeckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/decks")
public class DeckController {

    private final DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping
    public List<Deck> getDecks() {
        return deckService.getDecks();
    }

    @PostMapping
    public Deck createDeck(@RequestBody DeckDTO deckDTO) {
        return deckService.createDeck(deckDTO);
    }

    @DeleteMapping("/{deckId}")
    public void deleteDeck(@PathVariable Long deckId) {
        deckService.deleteDeck(deckId);
    }

    @PutMapping("/{deckId}")
    public Deck updateDeck(@PathVariable Long deckId, @RequestBody DeckDTO deckDTO) {
        return null;
    }
}
