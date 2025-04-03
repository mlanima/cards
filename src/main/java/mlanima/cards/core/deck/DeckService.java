package mlanima.cards.core.deck;

import mlanima.cards.core.user.UserService;
import mlanima.cards.dtos.DeckDTO;
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

    public Deck getDeckById(Long deckId) {
        return deckRepository.findByUserIdAndId(
                userService.getUser().getId(),
                deckId
        ).orElseThrow(DeckNotFoundException::new);
    }

    public Deck createDeck(DeckDTO deckDTO) {
        Deck deck = new Deck();
        deck.setName(deckDTO.getName());
        deck.setUser(userService.getUser());

        return deckRepository.save(deck);
    }

    public void deleteDeck(Long deckId) {
        Deck deck = deckRepository.findByUserIdAndId(
                userService.getUser().getId(),
                deckId
        ).orElseThrow(DeckNotFoundException::new);

        deckRepository.delete(deck);
    }

}
