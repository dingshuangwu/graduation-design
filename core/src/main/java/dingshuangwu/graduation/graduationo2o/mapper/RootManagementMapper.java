package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.*;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.*;
import dingshuangwu.graduation.graduationo2o.pojo.UserInformation.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RootManagementMapper {

    /**
     * 权限管理模块接口
     */
    List<JurisdictionVO> getJurisdictionByName(@Param("name") String name, @Param("tableName") String tableName);

    Integer setJurisdictionById(@Param("map") JurisdictionVO jurisdiction, @Param("tableName") String tableName);
    /************************************************************************/

    /**
     * 信息管理模块-个人资料
     */
    UserInformationAllVO getUserInfoAll(@Param("id") String id, @Param("tableName") String tableName);

    UserInfoVO getUserInfo(@Param("id") String id, @Param("tableName") String tableName);

    Integer setUserInfo(@Param("id") String id, @Param("map") UserInfoVO userInfo, @Param("tableName") String tableName);

    UserImageVO getUserImage(@Param("id") String id, @Param("tableName") String tableName);

    Integer setUserImage(@Param("id") String id, @Param("map") UserImageVO userImage, @Param("tableName") String tableName);

    UserSelfIntroductionVO getUserSelfIntroduction(@Param("id") String id, @Param("tableName") String tableName);

    Integer setUserSelfIntroduction(@Param("id") String id, @Param("map") UserSelfIntroductionVO userSelfIntroduction,
                                    @Param("tableName") String tableName);

    UserEducationExperienceVO getUserEducationExperience(@Param("id") String id, @Param("tableName") String tableName);

    Integer setUserEducationExperience(@Param("id") String id,
                                       @Param("map") UserEducationExperienceVO userEducationExperience,
                                       @Param("tableName") String tableName);

    UserSelfSignatureVO getUserSelfSignature(@Param("id") String id,
                                             @Param("tableName") String tableName);

    Integer setUserSelfSignature(@Param("id") String id,
                                 @Param("map") UserSelfSignatureVO userSelfSignature,
                                 @Param("tableName") String tableName);

    /**
     * 信息管理模块-简历信息
     */
    MyResumeAllVO getResumeInfoAll(@Param("id") String id, @Param("tableName") String tableName);

    MyResumeInfoVO getResumeInfo(@Param("id") String id, @Param("tableName") String tableName);

    Integer setResumeInfo(@Param("id") String id, @Param("map") MyResumeInfoVO myResumeInfo, @Param("tableName") String tableName);

    MyResumeExpertiseVO getResumeExpertise(@Param("id") String id, @Param("tableName") String tableName);

    Integer setResumeExpertise(@Param("id") String id, @Param("map") MyResumeExpertiseVO myResumeExpertise, @Param("tableName") String tableName);

    MyResumeExpectedWorkVO getResumeExpectedWork(@Param("id") String id, @Param("tableName") String tableName);

    Integer setResumeExpectedWork(@Param("id") String id, @Param("map") MyResumeExpectedWorkVO myResumeExpectedWork, @Param("tableName") String tableName);

    MyResumeWorkExperienceVO getResumeWorkExperience(@Param("id") String id, @Param("tableName") String tableName);

    Integer setResumeWorkExperience(@Param("id") String id, @Param("map") MyResumeWorkExperienceVO myResumeWorkExperience, @Param("tableName") String tableName);

    /**
     * 信息管理模块-求职信息
     */
    List<ApplyVO> getUserApply(@Param("authorId") String authorId, @Param("tableName") String tableName);

    Integer deleteUserApply(@Param("id") String id,
                            @Param("tableName") String tableName,
                            @Param("tableNameFlag") String tableNameFlag);

    /**
     * 信息管理模块-招聘信息
     */
    List<PublishVO> getUserPublish(@Param("authorId") String authorId, @Param("tableName") String tableName);

    Integer deleteUserPublish(@Param("id") String id,
                              @Param("tableName") String tableName,
                              @Param("tableNameFlag") String tableNameFlag);
    /************************************************************************/
    /**
     * 用户申请模块-实名认证
     */

    List<RealNameAuthenticationVO> getRealNameAuthentication(@Param("name") String name,
                                                             @Param("flag") String flag,
                                                             @Param("tableName") String tableName);

    Integer realNameAuthenticationPass(@Param("id") String id,
                                       @Param("realNameAuthenticationFlag") String realNameAuthenticationFlag,
                                       @Param("realNameAuthenticationTableName") String realNameAuthenticationTableName,
                                       @Param("userTableName") String userTableName,
                                       @Param("jurisdictionTableName") String jurisdictionTableName,
                                       @Param("jurisdictionYES") Boolean jurisdictionYES);

    Integer realNameAuthenticationReject(@Param("id") String id,
                                         @Param("realNameAuthenticationFlag") String realNameAuthenticationFlag,
                                         @Param("realNameAuthenticationTableName") String realNameAuthenticationTableName,
                                         @Param("userTableName") String userTableName,
                                         @Param("jurisdictionTableName") String jurisdictionTableName,
                                         @Param("jurisdictionYES") Boolean jurisdictionYES);
    /************************************************************************/
    /**
     * 超级管理员
     */

    List<ApplyForManagementVO> getApplyForManagement(@Param("name") String name, @Param("flag") String flag, @Param("tableName") String tableName);

    RealNameAuthenticationVO getRealNameAuthenticationById(@Param("id") String id, @Param("flag") String flag, @Param("tableName") String tableName);

    Integer ApplyForManagementPass(@Param("id") String id,
                                   @Param("applyForManagementFlag") String applyForManagementFlag,
                                   @Param("applyForManagementTable") String applyForManagementTable,
                                   @Param("jurisdictionTableName") String jurisdictionTableName,
                                   @Param("jurisdictionYES") Boolean jurisdictionYES);

    Integer ApplyForManagementReject(@Param("id") String id,
                                     @Param("applyForManagementFlag") String applyForManagementFlag,
                                     @Param("applyForManagementTable") String applyForManagementTable,
                                     @Param("jurisdictionTableName") String jurisdictionTableName,
                                     @Param("jurisdictionYES") Boolean jurisdictionYES);
    /************************************************************************/
}
