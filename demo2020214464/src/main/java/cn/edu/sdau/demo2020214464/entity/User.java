package cn.edu.sdau.demo2020214464.entity;
import cn.edu.sdau.demo2020214464.enums.RoleEnum;
import lombok.Data;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "姓名不能为空")
    private String name;

    private String password;

    private Integer role = RoleEnum.NORMAL.getCode();

    private String sex;

    private String address;

    private String phone;

    private String email;
}
