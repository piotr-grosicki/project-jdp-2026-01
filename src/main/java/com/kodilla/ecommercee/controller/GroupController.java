package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupMapper groupMapper;
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(groupService.getAllGroups()));
    }

    @PostMapping
    public ResponseEntity<GroupDto> addGroup(@RequestBody @Valid GroupDto groupDto) {
        Group group = groupService.saveGroup(groupMapper.mapToGroup(groupDto));
        return ResponseEntity.ok(groupMapper.mapToGroupDto(group));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupService.getGroupById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long id, @RequestBody @Valid GroupDto groupDto) {
        Group group = groupService.updateGroup(id, groupMapper.mapToGroup(groupDto));
        return ResponseEntity.ok(groupMapper.mapToGroupDto(group));
    }
}
