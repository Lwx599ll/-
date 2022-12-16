package cn.edu.sdau.demo2020214464.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;



@Data
public class LoginForm {

    /** 用户名 */
    @NotEmpty(message = "用户名必填")
    private String name;

    /** 用户密码 */
    @NotEmpty(message = "用户密码必填")
    private String password;
}
