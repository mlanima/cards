package mlanima.cards.core.deck;

import mlanima.cards.core.user.UserService;
import mlanima.cards.dtos.requests.PublicDeckRequest;
import mlanima.cards.exceptions.observed.DeckNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicDeckService {

    private final PublicDeckRepository publicDeckRepository;
    private DeckService deckService;
    private UserService userService;

    @Autowired
    public void setDeckService(DeckService deckService) {
        this.deckService = deckService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public PublicDeckService(PublicDeckRepository publicDeckRepository) {
        this.publicDeckRepository = publicDeckRepository;
    }

    public List<Deck> getPublicDecks() {
        return publicDeckRepository.findAll().stream().map(PublicDeck::getDeck).toList();
    }

    public Optional<Deck> getDeck(Long deckId) {
        return publicDeckRepository.findById(deckId).map(PublicDeck::getDeck);
    }

    public PublicDeck postDeck(PublicDeckRequest publicDeckRequest) {
        Deck deck = deckService.getDeckById(publicDeckRequest.getDeckId());

        if (deck.getUser() != userService.getUser()) {
            throw new DeckNotFoundException();
        }

        return publicDeckRepository.save(PublicDeck.fromDeck(deck));
    }

    public void deletePublicDeck(Long deckId) {
        PublicDeck publicDeck = publicDeckRepository.findById(deckId).orElseThrow(DeckNotFoundException::new);
        publicDeckRepository.delete(publicDeck);
    }
}
