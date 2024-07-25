package com.crazyspace_edu.api.request;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String birth;

}
