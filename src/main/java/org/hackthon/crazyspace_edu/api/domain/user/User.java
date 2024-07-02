package org.hackthon.crazyspace_edu.api.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String user_password;

    @Column(nullable = false, unique = true)
    private String user_email;

    @Column(nullable = false, unique = true)
    private String user_phone;

    @Column(nullable = false)
    private String user_birth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType; // 유형

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus; // 상태

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgreeYN userSvcUsePcyAgmtYN; // 서비스 약간 동의

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgreeYN userPsInfoProcAgmtYN; // 개인정보처리방침동의여부

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgreeYN userLocBaseSvcAgmtYN; // 위치기반서비스동의여부

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgreeYN userMktInfoRecvAgmtYN; // 마케팅정보수신동의여부

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgreeYN userPushYN; // 알림수신동의여부

    @Column(nullable = false)
    private LocalDateTime reg_dt; // 등록일자

    @Column(nullable = false)
    private LocalDateTime chg_dt; // 변경일자

    private LocalDateTime createAt;

    @Builder
    public User(Long id, String username, String user_password, String user_email, String user_phone, String user_birth, UserType userType, UserStatus userStatus, AgreeYN userSvcUsePcyAgmtYN, AgreeYN userPsInfoProcAgmtYN, AgreeYN userLocBaseSvcAgmtYN, AgreeYN userMktInfoRecvAgmtYN, AgreeYN userPushYN, int reg_dt, int chg_dt) {
        this.id = id;
        this.username = username;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_birth = user_birth;
        this.userType = userType;
        this.userStatus = userStatus;
        this.userSvcUsePcyAgmtYN = userSvcUsePcyAgmtYN;
        this.userPsInfoProcAgmtYN = userPsInfoProcAgmtYN;
        this.userLocBaseSvcAgmtYN = userLocBaseSvcAgmtYN;
        this.userMktInfoRecvAgmtYN = userMktInfoRecvAgmtYN;
        this.userPushYN = userPushYN;
        this.reg_dt = createAt.now();
        this.chg_dt = createAt.now();
    }


}
