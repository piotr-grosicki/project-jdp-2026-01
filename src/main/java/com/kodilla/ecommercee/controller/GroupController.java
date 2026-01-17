package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CreateUpdateGroupDto;
import com.kodilla.ecommercee.dto.GroupDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
//uncomment when GroupService added
//@RequiredArgsConstructor
public class GroupController {

    //uncomment when GroupService added
    //private GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(List.of(new GroupDto(1L, "groups list")));
    };

    @PostMapping
    public ResponseEntity<GroupDto> addGroup(@RequestBody @Valid CreateUpdateGroupDto createUpdateGroupDto) {
        return ResponseEntity.ok(new GroupDto(1L, createUpdateGroupDto.name()));
    };

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) {
        return ResponseEntity.ok(new GroupDto(id, "existing group"));
    };

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long id) {
        return ResponseEntity.ok(new GroupDto(id, "updated group"));
    };
}
