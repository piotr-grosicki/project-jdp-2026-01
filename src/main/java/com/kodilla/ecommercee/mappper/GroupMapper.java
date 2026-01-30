package com.kodilla.ecommercee.mappper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto) {
        return Group.builder()
                .id(groupDto.id())
                .name(groupDto.name())
                .build();
    }

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(
                group.getId(),
                group.getName()
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groups) {
        return groups.stream()
                .map(this::mapToGroupDto)
                .toList();
    }
}
