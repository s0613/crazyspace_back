package com.crazyspace_edu.api.service;

import com.crazyspace_edu.api.domain.user.FindToken;
import com.crazyspace_edu.api.domain.user.User;
import com.crazyspace_edu.api.domain.user.VerificationToken;
import com.crazyspace_edu.api.repository.FindTokenRepository;
import com.crazyspace_edu.api.repository.UserRepository;
import com.crazyspace_edu.api.repository.VerificationTokenRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindPasswordService {
    private final JavaMailSender mailSender;
    private final FindTokenRepository findTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void sendTokenForFindPassword(String email) throws MessagingException, UnsupportedEncodingException {
        String token = UUID.randomUUID().toString();
        FindToken findToken = new FindToken(token);
        findToken.setToken(token);
        findTokenRepository.save(findToken);
        sendVerificationEmail(email, token);
    }


    private void sendVerificationEmail(String email, String token) throws MessagingException, UnsupportedEncodingException {

        Optional<User> userOptional = userRepository.findByEmail(email);
        String password = passwordEncoder.encode(userOptional.get().getPassword());

        String subject = "Email Verification";
        String senderName = "CrazySpace '동반' ";

        String mailContent = "is your password : " + password +" </p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("crazyspacegy@gmail.com", senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }
}
