package top.hf.hadmin.config.token;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import top.hf.hadmin.config.properties.SecurityProperties;
import top.hf.hadmin.service.impl.UserDetailServiceImpl;
import top.hf.hadmin.util.RedisUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author hefeng
 * @Date 2021/11/19 14:47
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    SecurityProperties properties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token = token.replaceAll("Bearer ", "");
            String uesrname = (String) redisUtils.get(token);
            if (StringUtils.hasText(uesrname)) {
                UserDetails userDetails = userDetailService.loadUserByUsername(uesrname);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                checkRenewal(token);
            }
        }

        chain.doFilter(request, response);

    }

    public void checkRenewal(String token) {
        // 判断是否续期token,计算token的过期时间
        long time = redisUtils.getExpire(properties.getOnlineKey() + token) * 1000;
        Date expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) time);
        // 判断当前时间与过期时间的时间差
        long differ = expireDate.getTime() - System.currentTimeMillis();
        // 如果在续期检查的范围内，则续期
        if (differ <= properties.getDetect()) {
            long renew = time + properties.getRenew();
            redisUtils.expire(properties.getOnlineKey() + token, renew, TimeUnit.MILLISECONDS);
        }
    }
}
