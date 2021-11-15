package top.hf.hadmin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author hefeng
 * @Description //TODO
 * @Date 2021/11/15 15:19
 */
@TableName(value = "sys_role")
public class SysRole implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private String name;
    @TableField
    private String dataScope;


}
