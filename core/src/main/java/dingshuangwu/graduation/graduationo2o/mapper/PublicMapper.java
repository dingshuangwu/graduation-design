package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.JurisdictionVO;
import dingshuangwu.graduation.graduationo2o.pojo.UserVO;
import dingshuangwu.graduation.graduationo2o.pojo.root.RootVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PublicMapper {

    UserVO getUserByName(@Param("name") String name, @Param("tableName") String tableName);

    String getIdByName(@Param("name") String name, @Param("tableName") String tableName);

    String getNameById(@Param("id") String id, @Param("tableName") String tableName);

    String getIsRealNameAuthenticationById(@Param("id") String id, @Param("tableName") String tableName);

    String getApplyForManagementStateById(@Param("id") String id, @Param("tableName") String tableName);

    Boolean getIsManagement(@Param("id") String id, @Param("tableName") String tableName);

    String getImageUrlById(@Param("id") String id, @Param("tableName") String tableName);

    JurisdictionVO getJurisdictionByName(@Param("name") String name, @Param("tableName") String tableName);

    Integer setFlagById(@Param("id") String id, @Param("flag") String flag, @Param("tableName") String tableName);

    String getCreateDateById(@Param("id") String id, @Param("tableName") String tableName);

    String getAuthorIdById(@Param("id") String id, @Param("tableName") String tableName);

    String getPasswordById(@Param("id") String id, @Param("tableName") String tableName);

    List<Map<String, String>> getUserListNoManagement(@Param("name") String name, @Param("tableName") String tableName,
                                                      @Param("jurisdictionTableName") String jurisdictionTableName,
                                                      @Param("jurisdictionNO") Boolean jurisdictionNo);

    List<Map<String, String>> getUserListHaveManagement(@Param("name") String name, @Param("tableName") String tableName);

    RootVO getRoot(@Param("id") String id, @Param("name") String name, @Param("tableName") String tableName);
}
