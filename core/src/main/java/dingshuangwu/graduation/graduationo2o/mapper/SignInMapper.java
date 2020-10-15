package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.ApplyForManagementVO;
import dingshuangwu.graduation.graduationo2o.pojo.JurisdictionVO;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeAllVO;
import dingshuangwu.graduation.graduationo2o.pojo.RealNameAuthenticationVO;
import dingshuangwu.graduation.graduationo2o.pojo.UserInformation.UserInformationAllVO;
import dingshuangwu.graduation.graduationo2o.pojo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Repository
public interface SignInMapper {
    int signIn(@Param("map") UserVO user, @Param("tableName") String tableName);

    int setJurisdiction(@Param("map") JurisdictionVO jurisdiction, @Param("tableName") String tableName);

    int setUserInfo(@Param("map") UserInformationAllVO userInformationAllVO, @Param("tableName") String tableName);

    int setMyResume(@Param("map") MyResumeAllVO myResumeAllVO, @Param("tableName") String tableName);

    int setRealNameAuthentication(@Param("map") RealNameAuthenticationVO realNameAuthentication, @Param("tableName") String tableName);

    int setApplyForManagement(@Param("map") ApplyForManagementVO applyForManagement, @Param("tableName") String tableName);

    int updatePasswordById(@Param("id") String id, @Param("password") String newPassword, @Param("tableName") String tableName);
}
