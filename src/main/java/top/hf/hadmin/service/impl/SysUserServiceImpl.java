package top.hf.hadmin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.hf.hadmin.domain.SysUser;
import top.hf.hadmin.repository.SysUserMapper;
import top.hf.hadmin.service.SysUserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hefeng
 * @Description //TODO
 * @Date 2021/11/15 16:04
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper userMapper;

    @Override
    public Map<String, Object> doLogin(SysUser user) {
//        SysUser sysUser = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username", user.getUsername()));
//        if (sysUser == null) {
//            throw new BadRequestException("用户不存在");
//        }
//        if (!sysUser.getPassword().equals(SaSecureUtil.md5(user.getPassword()))) {
//            throw new BadRequestException("密码错误");
//        }
//        if (sysUser.getEnable() == 0) {
//            throw new BadRequestException("用户已被禁用");
//        }
//
//        StpUtil.login(sysUser.getId());
//        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Map<String, Object> map = new HashMap<>();
//        map.put("user", sysUser);
//        map.put("token", tokenInfo.getTokenValue());
        return map;
    }

    @Override
    public void create(SysUser user) {
//        user.setPassword(SaSecureUtil.md5(user.getPassword()));
        userMapper.insert(user);
    }
}
