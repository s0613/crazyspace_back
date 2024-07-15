package org.crazyspace_edu.api.request;

import lombok.*;
import org.crazyspace_edu.api.domain.user.AgreeYN;


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
