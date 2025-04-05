package mlanima.cards.core.deck;

import mlanima.cards.dtos.requests.DeckRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Deck createDeck(@RequestBody DeckRequest deckRequest) {
        return deckService.createDeck(deckRequest);
    }

    @DeleteMapping("/{deckId}")
    public void deleteDeck(@PathVariable Long deckId) {
        deckService.deleteDeck(deckId);
    }

    @PutMapping("/{deckId}")
    public Deck updateDeck(@PathVariable Long deckId, @RequestBody DeckRequest deckRequest) {
        return null;
    }
}
