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
    private UserStatus userStatus = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userSvcUsePcyAgmtYN = AgreeYN.Y;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userPsInfoProcAgmtYN = AgreeYN.Y;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userLocBaseSvcAgmtYN = AgreeYN.Y;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userMktInfoRecvAgmtYN = AgreeYN.Y;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeYN userPushYN = AgreeYN.Y;

    @Column(nullable = false)
    private boolean enabled = false;

    @Column(nullable = false)
    private LocalDateTime reg_dt;

    @Column(nullable = false)
    private LocalDateTime chg_dt;

    @Builder
    public User(Long id, String name, String password, String email, String phone, String birth, UserStatus userStatus, AgreeYN userSvcUsePcyAgmtYN, AgreeYN userPsInfoProcAgmtYN, AgreeYN userLocBaseSvcAgmtYN, AgreeYN userMktInfoRecvAgmtYN, AgreeYN userPushYN, boolean enabled) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.userStatus = userStatus;
        this.userSvcUsePcyAgmtYN = userSvcUsePcyAgmtYN;
        this.userPsInfoProcAgmtYN = userPsInfoProcAgmtYN;
        this.userLocBaseSvcAgmtYN = userLocBaseSvcAgmtYN;
        this.userMktInfoRecvAgmtYN = userMktInfoRecvAgmtYN;
        this.userPushYN = userPushYN;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birth='" + birth + '\'' +
                ", userStatus=" + userStatus +
                ", enabled=" + enabled +
                ", reg_dt=" + reg_dt +
                ", chg_dt=" + chg_dt +
                '}';
    }
}
