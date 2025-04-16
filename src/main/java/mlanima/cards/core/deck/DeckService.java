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

    public Deck getDeckByName(String deckName) {
        return deckRepository.findByUserIdAndName(
                userService.getUser().getId(),
                deckName
        ).orElseThrow(DeckNotFoundException::new);
    }

    public Deck createDeck(DeckRequest deckRequest) {
        Deck deck = new Deck();
        deck.setName(deckRequest.getName());
        deck.setUser(userService.getUser());

        return deckRepository.save(deck);
    }

    public void deleteDeck(String deckName) {
        Deck deck = deckRepository.findByUserIdAndName(
                userService.getUser().getId(),
                deckName
        ).orElseThrow(DeckNotFoundException::new);

        deckRepository.delete(deck);
    }

    public Deck updateDeck(String deckName, DeckRequest d) {
        Deck deck = deckRepository.findByUserIdAndName(userService.getUser().getId(), deckName)
                .orElseThrow(DeckNotFoundException::new);

        deck.setName(d.getName());

        return deckRepository.save(deck);
    }

}
