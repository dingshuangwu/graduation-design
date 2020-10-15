package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.UserInformation.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    UserInformationAllVO getUserAllInfo(@Param("id") String id, @Param("tableName") String tableName);

    UserInfoVO getUserInfo(@Param("id") String id, @Param("tableName") String tableName);

    int setUserInfo(@Param("id") String id, @Param("map") UserInfoVO userInfo, @Param("tableName") String tableName);

    UserImageVO getUserImage(@Param("id") String id, @Param("tableName") String tableName);

    int setUserImage(@Param("id") String id, @Param("map") UserImageVO userImage, @Param("tableName") String tableName);

    UserSelfIntroductionVO getSelfIntroduction(@Param("id") String id, @Param("tableName") String tableName);

    int setSelfIntroduction(@Param("id") String id, @Param("map") UserSelfIntroductionVO userSelfIntroduction, @Param("tableName") String tableName);

    UserEducationExperienceVO getEducationExperience(@Param("id") String id, @Param("tableName") String tableName);

    int setEducationExperience(@Param("id") String id, @Param("map") UserEducationExperienceVO userEducationExperience, @Param("tableName") String tableName);

    UserSelfSignatureVO getSelfSignature(@Param("id") String id, @Param("tableName") String tableName);

    int setSelfSignature(@Param("id") String id, @Param("map") UserSelfSignatureVO userSelfSignature, @Param("tableName") String tableName);

    UserInformationAllVO getSomeBodyInfoById(@Param("id") String id, @Param("tableName") String tableName);
}
