package mlanima.cards.core.card;

import mlanima.cards.core.deck.Deck;
import mlanima.cards.core.deck.DeckRepository;
import mlanima.cards.dtos.requests.CardDTO;
import mlanima.cards.exceptions.observed.CardNotFoundException;
import mlanima.cards.exceptions.observed.DeckNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;

    @Autowired
    public CardService(CardRepository cardRepository, DeckRepository deckRepository) {
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
    }

    public List<Card> getCardsByDeck(Long deckId) {
        return cardRepository.findByDeckId(deckId);
    }

    public Card addCard(Long deckId, Card card) {
        Deck deck = deckRepository.findById(deckId).orElseThrow(DeckNotFoundException::new);
        card.setDeck(deck);
        return cardRepository.save(card);


    }

    public void deleteCard(Long deckId, Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        if (!card.getDeck().getId().equals(deckId)) {
            throw new CardNotFoundException();
        }

        cardRepository.deleteById(cardId);
    }

    public Card updateUserCard(Long deckId,Long cardId, CardDTO card) {
        if (!deckRepository.existsById(deckId)) {
            throw new DeckNotFoundException();
        }

        Card cardToUpdate = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);

        cardToUpdate.setPhrase(card.getPhrase());
        cardToUpdate.setTranslation(card.getTranslation());
        return cardRepository.save(cardToUpdate);
    }

}
