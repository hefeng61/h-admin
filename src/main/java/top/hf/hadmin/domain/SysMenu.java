package top.hf.hadmin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author hefeng
 * @Description //TODO
 * @Date 2021/11/15 15:21
 */

@TableName(value = "sys_menu")
public class SysMenu implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private String name;
    @TableField
    private Integer pid;
    @TableField
    private Integer type;
    @TableField
    private String path;
    @TableField
    private String icon;
    @TableField
    private Integer sort;
    @TableField
    private Integer visible;
    @TableField
    private String permission;


}
