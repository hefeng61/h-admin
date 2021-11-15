package top.hf.hadmin.config.token;

import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaFoxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Author hefeng
 * @Description token设置
 * @Date 2021/11/15 16:57
 */
@Configuration
public class SaTokenConfigure {

    @Autowired
    public void rewriteSaStrategy() {
        // 重写 Token 生成策略
        SaStrategy.me.createToken = (loginId, loginType) -> {
            return "Bearer " + SaFoxUtil.getRandomString(60);    // 随机60位长度字符串
        };
    }

}
