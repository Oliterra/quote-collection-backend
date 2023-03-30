package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.GroupVO;
import edu.quote.collection.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/groups")
@RestController
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping()
    public List<GroupVO> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/user/{userId}")
    public List<GroupVO> getUserGroups(@PathVariable Long userId) {
        return groupService.getUserGroups(userId);
    }
}
