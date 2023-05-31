package edu.quote.collection.remote.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationInfoVO {

    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String passwordConfirmation;
}
