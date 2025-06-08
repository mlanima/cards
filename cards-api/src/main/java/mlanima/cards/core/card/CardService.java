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

    public List<Card> getCardsByDeck(Long deckName) {
        return cardRepository.findByUserIdAndDeckName(
                userService.getUser().getId(),
                deckName
        );
    }

    public Card getCardByDeckAndId(Long deckName, Long cardId) {
        return cardRepository.findByUserIdAndDeckNameAndId(
                userService.getUser().getId(),
                deckName,
                cardId
        ).orElseThrow(CardNotFoundException::new);
    }

    public Card createCard(Long deckName, CardRequest dto) {
        Deck deck = deckService.getDeckById(deckName);

        Card card = new Card();

        card.setDeck(deck);
        card.setPhrase(dto.getPhrase());
        card.setTranslation(dto.getTranslation());
        card.setDeck(deck);

        return cardRepository.save(card);

    }

    public void deleteCard(Long deckName, Long cardId) {
        Card card = getCardByDeckAndId(deckName, cardId);

        cardRepository.deleteById(cardId);
    }

    public Card updateCard(Long deckName, Long cardId, CardRequest dto) {
        Card card = getCardByDeckAndId(deckName, cardId);

        if (dto.getPhrase().isEmpty()) {
            card.setPhrase(dto.getTranslation());
        }

        if (dto.getTranslation().isEmpty()) {
            card.setTranslation(dto.getPhrase());
        }

        return cardRepository.save(card);
    }

    public List<Card> createCards(Long deckName, List<CardRequest> dtos) {
        return dtos.stream().map(dto -> createCard(deckName, dto)).toList();
    }
}
