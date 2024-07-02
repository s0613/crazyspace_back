package org.hackthon.crazyspace_edu.api.service;

import lombok.RequiredArgsConstructor;
import org.hackthon.crazyspace_edu.api.domain.user.AgreeYN;
import org.hackthon.crazyspace_edu.api.domain.user.User;
import org.hackthon.crazyspace_edu.api.domain.user.UserStatus;
import org.hackthon.crazyspace_edu.api.exception.AlreadyExistsEmailException;
import org.hackthon.crazyspace_edu.api.repository.UserRepository;
import org.hackthon.crazyspace_edu.api.request.SignUpRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;

    public void signup(SignUpRequest signUpRequest) {

        Optional<User> userOptional = userRepository.findByEmail(signUpRequest.getEmail());

        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        var user = User.builder()
                .user_email(signUpRequest.getEmail())
                .user_password(signUpRequest.getPassword())
                .user_phone(signUpRequest.getPhone())
                .username(signUpRequest.getUsername())
                .user_birth(signUpRequest.getBirth())
                .userLocBaseSvcAgmtYN(AgreeYN.Y)
                .userMktInfoRecvAgmtYN(AgreeYN.Y)
                .userPushYN(AgreeYN.Y)
                .userPsInfoProcAgmtYN(AgreeYN.Y)
                .userStatus(UserStatus.ACTIVE)
                .build();

        userRepository.save(user);
    }
}
