package mlanima.cards.core.group;

import mlanima.cards.dtos.GroupDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/{groupId}")
    public Group getGroup(@PathVariable long groupId) {
        return groupService.getGroup(groupId);
    }

    @PostMapping
    public Group createGroup(@RequestBody GroupDTO group) {
        return groupService.createGroup(group);
    }

    @PutMapping("/{groupId}")
    public Group updateGroup(@PathVariable Long groupId,@RequestBody GroupDTO group) {
        return groupService.updateGroup(groupId, group);
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
    }
}
