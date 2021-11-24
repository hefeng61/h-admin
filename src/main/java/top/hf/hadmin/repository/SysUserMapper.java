package top.hf.hadmin.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.hf.hadmin.domain.SysUser;

import java.util.List;

/**
 * @Author hefeng
 * @Description //TODO
 * @Date 2021/11/15 15:24
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findByName(@Param("username") String username);

    List<String> getPermissions(@Param("user") SysUser user);
}
