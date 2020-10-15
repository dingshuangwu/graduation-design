package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.ApplyForManagementVO;
import dingshuangwu.graduation.graduationo2o.pojo.RealNameAuthenticationVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApplyMapper {
    int setRealNameAuthentication(@Param("map") RealNameAuthenticationVO realNameAuthentication,
                                  @Param("realNameAuthenticationTable") String realNameAuthenticationTable,
                                  @Param("userTableName") String userTableName,
                                  @Param("jurisdictionTableName") String jurisdictionTableName,
                                  @Param("jurisdictionNO") Boolean jurisdictionNO);

    int setApplyForManagement(@Param("map") ApplyForManagementVO applyForManagement,
                              @Param("applyForManagementTable") String applyForManagementTable,
                              @Param("jurisdictionTableName") String jurisdictionTableName,
                              @Param("jurisdictionNO") Boolean jurisdictionNO);

    RealNameAuthenticationVO getRealNameAuthentication(@Param("id") String id, @Param("tableName") String tableName);
}
