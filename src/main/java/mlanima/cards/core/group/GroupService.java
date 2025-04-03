package mlanima.cards.core.group;

import mlanima.cards.core.user.UserService;
import mlanima.cards.dtos.GroupDTO;
import mlanima.cards.exceptions.observed.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public List<Group> getGroups() {
        return groupRepository.findByUserId(userService.getUser().getId());
    }

    public Group getGroup(Long groupId) {
        return groupRepository
                .findByUserIdAndId(
                    userService.getUser().getId(),
                    groupId
                )

                .orElseThrow(GroupNotFoundException::new);
    }

    public Group createGroup(GroupDTO dto) {
        Group group = new Group();
        group.setName(dto.getName());
        group.setUser(userService.getUser());

        return groupRepository.save(group);
    }

    public Group updateGroup(Long groupId, GroupDTO dto) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        group.setName(dto.getName());
        return groupRepository.save(group);
    }

    public void deleteGroup(Long groupId) {
        Group group = groupRepository.findByUserIdAndId(
                userService.getUser().getId(),
                groupId
        ).orElseThrow(GroupNotFoundException::new);
    }
}
