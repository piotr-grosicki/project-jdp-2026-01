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
        return groupRepository.findById(id).orElse(GroupNotFoundException::new);
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroupById(final Long id) {
        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException();
        }
        groupRepository.deleteById(id);
    }
}
