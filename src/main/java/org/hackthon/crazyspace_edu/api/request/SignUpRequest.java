package org.hackthon.crazyspace_edu.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {
    private String username;

    private String password;

    private String email;

    private String phone;

    private String birth;

}
