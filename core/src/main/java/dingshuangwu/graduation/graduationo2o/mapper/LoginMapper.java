package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Repository
public interface LoginMapper {
    /**
     * 验证账号密码
     *
     * @param name
     * @param password
     * @return
     */
    UserVO login(@Param(value = "name") String name, @Param(value = "password") String password,
                 @Param("tableName") String tableName);
}
