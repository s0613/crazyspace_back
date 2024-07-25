package com.crazyspace_edu.api.domain.user;

import com.crazyspace_edu.api.domain.GeneratedAiContent;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String birth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GeneratedAiContent> contents = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus = UserStatus.ACTIVE; // 기본값 설정

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userSvcUsePcyAgmtYN = AgreeYN.Y; // 기본값 설정

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userPsInfoProcAgmtYN = AgreeYN.Y; // 기본값 설정

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userLocBaseSvcAgmtYN = AgreeYN.Y; // 기본값 설정

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userMktInfoRecvAgmtYN = AgreeYN.Y; // 기본값 설정

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userPushYN = AgreeYN.Y; // 기본값 설정

    @Column(nullable = false)
    private boolean enabled = false; // 기본값 설정

    @Column(nullable = false)
    private LocalDateTime reg_dt; // @Column 추가

    @Column(nullable = false)
    private LocalDateTime chg_dt; // @Column 추가

    @Builder
    public User(Long id, String name, String password, String email, String phone, String birth, UserStatus userStatus, AgreeYN userSvcUsePcyAgmtYN, AgreeYN userPsInfoProcAgmtYN, AgreeYN userLocBaseSvcAgmtYN, AgreeYN userMktInfoRecvAgmtYN, AgreeYN userPushYN, boolean enabled) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.userStatus = UserStatus.ACTIVE;
        this.userSvcUsePcyAgmtYN = AgreeYN.Y;
        this.userPsInfoProcAgmtYN = AgreeYN.Y;
        this.userLocBaseSvcAgmtYN = AgreeYN.Y;
        this.userMktInfoRecvAgmtYN = AgreeYN.Y;
        this.userPushYN = AgreeYN.Y;
        this.enabled = enabled;
        this.reg_dt = LocalDateTime.now();
        this.chg_dt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.reg_dt = LocalDateTime.now();
        this.chg_dt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.chg_dt = LocalDateTime.now();
    }
}
