package edu.quote.collection.remote.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationInfoVO {

    private String usernameOrEmail;
    private String password;
}
