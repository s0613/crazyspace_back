//package org.crazyspace_edu.api.service;
//
//import lombok.RequiredArgsConstructor;
//import org.crazyspace_edu.api.exception.AlreadyExistsEmailException;
//import org.crazyspace_edu.api.repository.UserRepository;
//import org.crazyspace_edu.api.request.SignUpRequest;
//import org.crazyspace_edu.api.domain.user.AgreeYN;
//import org.crazyspace_edu.api.domain.user.User;
//import org.crazyspace_edu.api.domain.user.UserStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class SignUpService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public void signup(SignUpRequest signUpRequest) {
//
//        Optional<User> userOptional = userRepository.findByEmail(signUpRequest.getEmail());
//
//        if (userOptional.isPresent()) {
//            throw new AlreadyExistsEmailException();
//        }
//
//        // 이메일 인증 토큰 생성
//        String token = UUID.randomUUID().toString();
//
//        // 인증 이메일 발송
//        sendVerificationEmail(user.getEmail(), token);
//
//        // 비밀번호 암호화
//        String encryptedPassword = passwordEncoder.encode(signUpRequest.getPassword());
//
//
//        var user = User.builder()
//                .user_email(signUpRequest.getEmail())
//                .user_password(encryptedPassword)
//                .user_phone(signUpRequest.getPhone())
//                .username(signUpRequest.getUsername())
//                .user_birth(signUpRequest.getBirth())
//                .userLocBaseSvcAgmtYN(AgreeYN.Y)
//                .userMktInfoRecvAgmtYN(AgreeYN.Y)
//                .userPushYN(AgreeYN.Y)
//                .userPsInfoProcAgmtYN(AgreeYN.Y)
//                .userStatus(UserStatus.ACTIVE)
//                .build();
//
//        userRepository.save(user);
//    }
//
//    private void sendVerificationEmail(String email, String token) throws MessagingException {
//        String subject = "Email Verification";
//        String senderName = "Your Company Name";
//        String mailContent = "<p>Please click the link below to verify your email:</p>";
//        mailContent += "<a href=\"http://localhost:8080/api/auth/verify?token=" + token + "\">Verify</a>";
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom("your-email@example.com", senderName);
//        helper.setTo(email);
//        helper.setSubject(subject);
//        helper.setText(mailContent, true);
//
//        mailSender.send(message);
//    }
//}
