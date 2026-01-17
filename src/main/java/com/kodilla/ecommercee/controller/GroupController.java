package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CreateUpdateGroupDto;
import com.kodilla.ecommercee.dto.GroupDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
//odblokowac ten komentarz jak ktos doda serwis dla grupy
//@RequiredArgsConstructor
public class GroupController {

    //odblokowac ten komentarz jak ktos doda serwis dla grupy
    //private GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(List.of(new GroupDto(1L, "lista grup produktow")));
    };

    @PostMapping
    public ResponseEntity<GroupDto> addGroup(@RequestBody @Valid CreateUpdateGroupDto createUpdateGroupDto) {
        return ResponseEntity.ok(new GroupDto(1L, createUpdateGroupDto.name()));
    };

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) {
        return ResponseEntity.ok(new GroupDto(id, "istniejaca grupa"));
    };

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long id) {
        return ResponseEntity.ok(new GroupDto(id, "zaktualizowana grupa"));
    };
}
