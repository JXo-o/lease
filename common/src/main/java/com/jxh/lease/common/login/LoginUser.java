package com.jxh.lease.common.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: LoginUser
 * Package: com.jxh.lease.common.login
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/9 21:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    private Long userId;
    private String username;

}
