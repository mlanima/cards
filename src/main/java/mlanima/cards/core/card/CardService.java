package mlanima.cards.core.card;

import mlanima.cards.core.deck.Deck;
import mlanima.cards.core.deck.DeckService;
import mlanima.cards.core.user.UserService;
import mlanima.cards.dtos.requests.CardRequest;
import mlanima.cards.exceptions.observed.CardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final DeckService deckService;
    private final UserService userService;

    @Autowired
    public CardService(CardRepository cardRepository, DeckService deckService, UserService userService) {
        this.cardRepository = cardRepository;
        this.deckService = deckService;
        this.userService = userService;
    }

    public List<Card> getCardsByDeck(Long deckId) {
        return cardRepository.findByUserIdAndDeckId(
                userService.getUser().getId(),
                deckId
        );
    }

    public Card getCardByDeckAndId(Long deckId, Long cardId) {
        return cardRepository.findByUserIdAndDeckIdAndId(
                userService.getUser().getId(),
                deckId,
                cardId
        ).orElseThrow(CardNotFoundException::new);
    }

    public Card createCard(Long deckId, CardRequest dto) {
        Deck deck = deckService.getDeckById(deckId);

        Card card = new Card();

        card.setDeck(deck);
        card.setPhrase(dto.getPhrase());
        card.setTranslation(dto.getTranslation());
        card.setDeck(deck);

        return cardRepository.save(card);

    }

    public void deleteCard(Long deckId, Long cardId) {
        Card card = getCardByDeckAndId(cardId, deckId);

        cardRepository.deleteById(cardId);
    }

    public Card updateCard(Long deckId, Long cardId, CardRequest dto) {
        Card card = getCardByDeckAndId(cardId, deckId);

        if (dto.getPhrase().isEmpty()) {
            card.setPhrase(dto.getTranslation());
        }

        if (dto.getTranslation().isEmpty()) {
            card.setTranslation(dto.getPhrase());
        }

        return cardRepository.save(card);
    }

    public List<Card> createCards(Long deckId, List<CardRequest> dtos) {
        return dtos.stream().map(dto -> createCard(deckId, dto)).toList();
    }
}
