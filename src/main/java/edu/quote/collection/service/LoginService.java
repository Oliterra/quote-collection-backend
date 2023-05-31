package edu.quote.collection.service;

import edu.quote.collection.dbaccess.entity.UserEntity;
import edu.quote.collection.dbaccess.repository.UserRepository;
import edu.quote.collection.remote.ErrorCode;
import edu.quote.collection.remote.vo.AuthorizationInfoVO;
import edu.quote.collection.remote.vo.RegistrationInfoVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Transactional
@Service
@AllArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public Long signUp(RegistrationInfoVO registrationInfo) {
        if (userRepository.existsByEmail(registrationInfo.getEmail())) {
            throw new RuntimeException(ErrorCode.EMAIL_DUPLICATE_ERROR.toString());
        }
        if (userRepository.existsByUsername(registrationInfo.getUsername())) {
            throw new RuntimeException(ErrorCode.USERNAME_DUPLICATE_ERROR.toString());
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(registrationInfo.getName());
        userEntity.setSurname(registrationInfo.getSurname());
        userEntity.setEmail(registrationInfo.getEmail());
        userEntity.setUsername(registrationInfo.getUsername());
        userEntity.setPassword(BCrypt.hashpw(registrationInfo.getPassword(), BCrypt.gensalt()));
        return userRepository.save(userEntity).getId();
    }
    
    public Long signIn(AuthorizationInfoVO authorizationInfo) {
        UserEntity user = userRepository.findByEmailOrUsername(authorizationInfo.getUsernameOrEmail(), authorizationInfo.getUsernameOrEmail());
        if (user == null || !BCrypt.checkpw(authorizationInfo.getPassword(), user.getPassword())) {
            throw new RuntimeException(ErrorCode.INVALID_CREDENTIALS_ERROR.toString());
        }
        return user.getId();
    }
}
