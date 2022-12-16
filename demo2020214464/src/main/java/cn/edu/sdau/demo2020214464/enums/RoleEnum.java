package cn.edu.sdau.demo2020214464.enums;


import lombok.Getter;

/**
 * 用户权限
 */

@Getter
public enum RoleEnum {

    /**
     * 0 普通用户
     * 1 管理员
     */
    NORMAL(0, "普通用户"),
    ADMINISTRATOR(1, "管理员")
    ;

    private Integer code;

    private String msg;

    RoleEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

