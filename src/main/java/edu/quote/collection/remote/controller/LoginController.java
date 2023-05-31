package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.AuthorizationInfoVO;
import edu.quote.collection.remote.vo.RegistrationInfoVO;
import edu.quote.collection.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping("/login")
@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/signUp")
    public Long signUp(@RequestBody RegistrationInfoVO registrationInfo) {
        return loginService.signUp(registrationInfo);      
    }

    @PostMapping("/signIn")
    public Long signIn(@RequestBody AuthorizationInfoVO authorizationInfo) {
        return loginService.signIn(authorizationInfo);
    }
}
