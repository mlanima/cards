package mlanima.cards.core.deck;

import mlanima.cards.dtos.requests.DeckRequest;
import mlanima.cards.dtos.responses.DeckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public List<DeckResponse> getDecks() {
        return deckService.getDecks().stream().map(DeckResponse::build).toList();
    }

    @PostMapping
    public ResponseEntity<DeckResponse> createDeck(@RequestBody DeckRequest deckRequest) {
        DeckResponse response = DeckResponse.build(deckService.createDeck(deckRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{deckName}")
                .buildAndExpand(response.getName())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{deckName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeck(@PathVariable Long deckName) {
        deckService.deleteDeck(deckName);
    }

    @PutMapping("/{deckName}")
    public DeckResponse updateDeck(@PathVariable Long deckName, @RequestBody DeckRequest deckRequest) {
        return DeckResponse.build(deckService.updateDeck(deckName, deckRequest)) ;
    }
}
