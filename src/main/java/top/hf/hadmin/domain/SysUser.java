package top.hf.hadmin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author hefeng
 * @Description 用户类
 * @Date 2021/11/15 15:16
 */
@Getter
@Setter
@TableName(value = "sys_user")
public class SysUser implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    @TableField
    private String username;
    @NotBlank(message = "密码不能为空")
    @TableField
    private String password;
    @TableField
    private Integer deptId;
    @TableField
    private Integer gender;
    @TableField
    private String phone;
    @TableField
    private String avatar;
    @TableField
    private Integer isAdmin;
    @TableField
    private Integer enable;
}
