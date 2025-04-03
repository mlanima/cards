package mlanima.cards.core.deck;


import mlanima.cards.dtos.DeckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/decks")
public class GroupDeckController {

    private final GroupDeckService groupDeckService;

    @Autowired
    public GroupDeckController(GroupDeckService groupDeckService) {
        this.groupDeckService = groupDeckService;
    }

    @GetMapping
    public List<Deck> getDecksInGroup(@PathVariable Long groupId) {
        return groupDeckService.getDecksByGroup(groupId);
    }

    @GetMapping("/{deckId}")
    public Deck getDeckInGroup(@PathVariable Long groupId, @PathVariable Long deckId) {
        return groupDeckService.getDeckByGroupAndId(groupId, deckId);
    }

    @PostMapping
    public Deck createDeck(@PathVariable Long groupId, @RequestBody DeckDTO dto) {
        return groupDeckService.createDeckInGroup(groupId, dto);
    }

    @DeleteMapping("/{deckId}")
    public void deleteDeckInGroup(@PathVariable Long groupId, @PathVariable Long deckId) {
        this.groupDeckService.deleteDeckInGroup(groupId, deckId);
    }

    @PutMapping("{deckId}")
    public Deck updateDeck(@PathVariable Long groupId,@PathVariable Long deckId, @RequestBody DeckDTO dto) {
        return groupDeckService.updateDeckInGroup(groupId, deckId, dto);
    }
}
