package top.hf.hadmin.service;

import top.hf.hadmin.domain.SysUser;

import java.util.Map;

/**
 * @Author hefeng
 * @Description 用户登录
 * @Date 2021/11/15 16:02
 */
public interface SysUserService {

    /**
     * @Description: 登录验证
     * @Author: hefeng
     * @Date: 2021/11/15 16:03
     * @Param:
     * @Return:
     **/

    Map<String,Object> doLogin(SysUser user);

    /**
     * @Description: 新增用户
     * @Author: hefeng
     * @Date: 2021/11/16 9:18
     * @Param:
     * @Return:
     **/

    void create(SysUser user);
}
