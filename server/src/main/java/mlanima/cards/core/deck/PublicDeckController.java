package mlanima.cards.core.deck;

import mlanima.cards.core.user.UserService;
import mlanima.cards.dtos.requests.PublicDeckRequest;
import mlanima.cards.exceptions.observed.DeckNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public/decks")
public class PublicDeckController {

    private final PublicDeckService publicDeckService;

    @Autowired
    public PublicDeckController(PublicDeckService publicDeckService) {
        this.publicDeckService = publicDeckService;
    }

    @GetMapping
    List<Deck> getPublicDecks() {
        return publicDeckService.getPublicDecks();
    }

    @GetMapping("{id}")
    Deck getPublicDeck(@PathVariable Long id) {
        return publicDeckService.getDeck(id).orElseThrow(DeckNotFoundException::new);
    }

    @PostMapping
    PublicDeck postDeck(@RequestBody PublicDeckRequest publicDeckRequest) {
        return publicDeckService.postDeck(publicDeckRequest);
    }

    @DeleteMapping("/{id}")
    void deleteDeck(@PathVariable Long id) {
        publicDeckService.deletePublicDeck(id);
    }
}
