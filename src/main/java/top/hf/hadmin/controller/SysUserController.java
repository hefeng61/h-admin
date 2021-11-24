package top.hf.hadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import top.hf.hadmin.config.properties.SecurityProperties;
import top.hf.hadmin.domain.SysUser;
import top.hf.hadmin.service.SysUserService;
import top.hf.hadmin.service.impl.UserDetailServiceImpl;
import top.hf.hadmin.util.RedisUtils;
import top.hf.hadmin.util.TokenUtil;

import javax.validation.Valid;

/**
 * @Author hefeng
 * @Description 登录模块
 * @Date 2021/11/15 15:12
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SysUserController {

    private final SysUserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtils redisUtils;
    private final UserDetailServiceImpl userDetailsService;
    private final SecurityProperties properties;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody SysUser user) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenUtil.createToken(user.getUsername());
        redisUtils.set(token,user.getUsername(),properties.getTokenValidityInSeconds());
        return ResponseEntity.ok("Bearer "+token);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout() {
        return ResponseEntity.ok().build();
    }

//    @PreAuthorize("hasAuthority('user:list')")
    @PreAuthorize("hasRole('guest')")
    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok("hello world");
    }

}
