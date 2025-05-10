package mlanima.cards.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mlanima.cards.core.card.Card;
import mlanima.cards.core.card.CardRepository;
import mlanima.cards.core.deck.Deck;
import mlanima.cards.core.deck.DeckRepository;
import mlanima.cards.core.deck.PublicDeck;
import mlanima.cards.core.deck.PublicDeckRepository;
import mlanima.cards.core.user.User;
import mlanima.cards.core.user.UserRepository;
import mlanima.cards.core.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class GuestDataInitializer {

    @Bean
    public CommandLineRunner initGuestData(
            ObjectMapper mapper,
            UserRepository userRepository,
            DeckRepository deckRepository,
            CardRepository cardRepository,
            PublicDeckRepository publicDeckRepository
    ) {
        return args -> {
            User user = new User( "GUEST@GUEST.com", "GUEST");
            userRepository.save(user);

            Deck deck = new Deck("School", user);
            Deck saved = deckRepository.save(deck);

            InputStream input = getClass().getClassLoader().getResourceAsStream("deck.json");
            List<Card> cards = mapper.readValue(input, new TypeReference<List<Card>>() {});
            cards.forEach(card -> card.setDeck(saved));

            cardRepository.saveAll(cards);
            publicDeckRepository.save(
                    PublicDeck.fromDeck(saved)
            );
        };
    }
}
