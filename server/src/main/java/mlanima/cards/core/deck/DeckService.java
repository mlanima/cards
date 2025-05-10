package mlanima.cards.core.deck;

import mlanima.cards.core.user.UserService;
import mlanima.cards.dtos.requests.DeckRequest;
import mlanima.cards.exceptions.observed.DeckNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    private final DeckRepository deckRepository;
    private final UserService userService;

    @Autowired
    public DeckService(DeckRepository deckRepository, UserService userService) {
        this.deckRepository = deckRepository;
        this.userService = userService;
    }

    public List<Deck> getDecks() {
        return deckRepository.findByUserId(userService.getUser().getId());
    }

    public Deck getDeckById(Long id) {
        return deckRepository.findByUserIdAndId(
                userService.getUser().getId(),
                id
        ).orElseThrow(DeckNotFoundException::new);
    }

    public Deck createDeck(DeckRequest deckRequest) {
        Deck deck = new Deck();
        deck.setName(deckRequest.getName());
        deck.setUser(userService.getUser());

        return deckRepository.save(deck);
    }

    public void deleteDeck(Long id) {
        Deck deck = deckRepository.findByUserIdAndId(
                userService.getUser().getId(),
                id
        ).orElseThrow(DeckNotFoundException::new);

        deckRepository.delete(deck);
    }

    public Deck updateDeck(Long id, DeckRequest d) {
        Deck deck = deckRepository.findByUserIdAndId(userService.getUser().getId(), id)
                .orElseThrow(DeckNotFoundException::new);

        deck.setName(d.getName());

        return deckRepository.save(deck);
    }
}
