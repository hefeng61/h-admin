package top.hf.hadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hf.hadmin.domain.SysUser;
import top.hf.hadmin.service.SysUserService;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Author hefeng
 * @Description 登录模块
 * @Date 2021/11/15 15:12
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SysUserLoginController {

    private final SysUserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody SysUser user) {
        Map<String, Object> map = userService.doLogin(user);
        return ResponseEntity.ok(map);
    }

}
