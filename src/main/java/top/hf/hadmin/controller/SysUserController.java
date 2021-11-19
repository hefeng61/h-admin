package top.hf.hadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.hf.hadmin.domain.SysUser;
import top.hf.hadmin.service.SysUserService;

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

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody SysUser user) {
        return ResponseEntity.ok("");
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody SysUser user) {
        userService.create(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout() {
        return ResponseEntity.ok().build();
    }

}
