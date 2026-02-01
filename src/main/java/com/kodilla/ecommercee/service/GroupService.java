package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(final Long id) {
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(final Long id, final Group group) {
        Group oldGroup = groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
        oldGroup.setName(group.getName());
        return groupRepository.save(oldGroup);

    }
}
