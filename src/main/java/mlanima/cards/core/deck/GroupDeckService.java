package mlanima.cards.core.deck;

import mlanima.cards.core.group.Group;
import mlanima.cards.core.group.GroupService;
import mlanima.cards.core.user.UserRepository;
import mlanima.cards.core.user.UserService;
import mlanima.cards.dtos.DeckDTO;
import mlanima.cards.exceptions.observed.DeckNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupDeckService {

    private final DeckRepository deckRepository;
    private final UserService userService;
    private final GroupService groupService;

    @Autowired
    public GroupDeckService(DeckRepository deckRepository, UserService userService, GroupService groupService) {
        this.deckRepository = deckRepository;
        this.userService = userService;
        this.groupService = groupService;
    }

    public List<Deck> getDecksByGroup(Long groupId) {
        return deckRepository.findByUserIdAndGroupId(
                userService.getUser().getId(),
                groupId
        );
    }

    public Deck getDeckByGroupAndId(Long groupId, Long deckId) {
        return this.deckRepository.findByUserIdAndGroupIdAndId(
                userService.getUser().getId(),
                groupId,
                deckId
        ).orElseThrow(DeckNotFoundException::new);
    }

    public void deleteDeckInGroup(Long groupId, Long deckId) {
        Deck deck = getDeckByGroupAndId(groupId, deckId);

        this.deckRepository.delete(deck);
    }

    public Deck createDeckInGroup(Long groupId, DeckDTO dto) {
        Group group = groupService.getGroup(groupId);

        Deck deck = new Deck();
        deck.setGroup(group);
        deck.setName(dto.getName());
        deck.setUser(userService.getUser());

        return this.deckRepository.save(deck);
    }

    public Deck updateDeckInGroup(Long groupId, Long deckId, DeckDTO dto) {
        Deck deck = getDeckByGroupAndId(groupId, deckId);
        deck.setName(dto.getName());

        return this.deckRepository.save(deck);
    }
}
