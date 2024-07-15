package org.crazyspace_edu.api.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.crazyspace_edu.api.domain.VerificationToken;
import org.crazyspace_edu.api.domain.user.AgreeYN;
import org.crazyspace_edu.api.domain.user.User;
import org.crazyspace_edu.api.domain.user.UserStatus;
import org.crazyspace_edu.api.exception.AlreadyExistsEmailException;
import org.crazyspace_edu.api.repository.UserRepository;
import org.crazyspace_edu.api.repository.VerificationTokenRepository;
import org.crazyspace_edu.api.request.SignUpRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final VerificationTokenRepository tokenRepository;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authenticationManager.authenticate(authentication);
    }

    public void signup(SignUpRequest signUpRequest) {
        Optional<User> userOptional = userRepository.findByEmail(signUpRequest.getEmail());

        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        var user = User.builder()
                .email(signUpRequest.getEmail())
                .password(encryptedPassword)
                .phone(signUpRequest.getPhone())
                .name(signUpRequest.getName())
                .birth(signUpRequest.getBirth())
                .userLocBaseSvcAgmtYN(AgreeYN.Y)
                .userMktInfoRecvAgmtYN(AgreeYN.Y)
                .userPushYN(AgreeYN.Y)
                .userPsInfoProcAgmtYN(AgreeYN.Y)
                .userSvcUsePcyAgmtYN(AgreeYN.Y)
                .userStatus(UserStatus.ACTIVE)
                .enabled(false)
                .build();

        userRepository.save(user);

        // 이메일 인증 토큰 생성
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepository.save(verificationToken);

        // 인증 이메일 발송
        try {
            sendVerificationEmail(user.getEmail(), token);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to send verification email", e);
        }
    }

    public boolean verifyUser(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expired");
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        tokenRepository.delete(verificationToken);
        return true;
    }

    private void sendVerificationEmail(String email, String token) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "CrazySpace '동반' ";
        String mailContent = "<p>Please click the link below to verify your email:</p>";
        mailContent += "<a href=\"http://localhost:8080/api/auth/verify?token=" + token + "\">Verify</a>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("crazyspacegy@gmail.com", senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }
}
