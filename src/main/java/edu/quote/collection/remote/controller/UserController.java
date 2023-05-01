package edu.quote.collection.remote.controller;

import edu.quote.collection.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/quoteRating/{userId}")
    public Byte getRatingByUserId(@PathVariable Long userId, @RequestParam Long quoteId) {
        return userService.getRatingByUserId(userId, quoteId);
    }
}
