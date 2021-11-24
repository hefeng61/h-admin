package top.hf.hadmin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.hf.hadmin.config.exception.BadRequestException;
import top.hf.hadmin.domain.SecurityUser;
import top.hf.hadmin.domain.SysUser;
import top.hf.hadmin.repository.SysUserMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author hefeng
 * @Date 2021/11/19 17:20
 * @Description
 */
@Service(value = "userDetailsService")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.findByName(s);
        if (sysUser == null) {
            throw new UsernameNotFoundException("");
        }
//        if (sysUser.getEnable() == 0) {
//            throw new BadRequestException("用户已被禁用");
//        }
        List<String> permissions = userMapper.getPermissions(sysUser);
        List<GrantedAuthority> collect = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        SecurityUser securityUser = new SecurityUser(sysUser, collect);
        return securityUser;
    }
}
