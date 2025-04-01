package mlanima.cards.core.deck;

import mlanima.cards.core.card.CardService;
import mlanima.cards.core.user.User;
import mlanima.cards.core.user.UserRepository;
import mlanima.cards.dtos.requests.DeckDTO;
import mlanima.cards.exceptions.observed.DeckNotFoundException;
import mlanima.cards.exceptions.observed.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    private final DeckRepository deckRepository;
    private final UserRepository userRepository;

    @Autowired
    public DeckService(DeckRepository deckRepository, CardService cardService, UserRepository userRepository) {
        this.deckRepository = deckRepository;
        this.userRepository = userRepository;
    }

    public List<Deck> findDecksByUser(Long userId) {
        return deckRepository.findByUserId(userId);
    }

    public Deck addDeckAtUser(Long userId, DeckDTO dto) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Deck deck = new Deck(dto.getName(), user);
        return deckRepository.save(deck);
    }


    public void deleteDeckAtUser(Long deckId) {
        deckRepository.deleteById(deckId);
    }

    public Deck updateDeck(Long deckId, DeckDTO deck) {
        Deck d = deckRepository.findById(deckId).orElseThrow(DeckNotFoundException::new);
        d.setName(deck.getName());
        return deckRepository.save(d);
    }

    public Deck findDeck(Long userId, Long deckId) {
        Deck deck = deckRepository.findById(deckId).orElseThrow(DeckNotFoundException::new);
        if (!deck.getUser().getId().equals(userId)) {
            throw new DeckNotFoundException();
        }

        return deck;
    }
}
