package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.*;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.*;
import dingshuangwu.graduation.graduationo2o.pojo.UserInformation.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagementMapper {

    /**
     * 权限管理模块接口
     */
    List<JurisdictionVO> getJurisdictionByName(@Param("name") String name, @Param("tableName") String tableName,
                                               @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setJurisdictionById(@Param("map") JurisdictionVO jurisdiction, @Param("tableName") String tableName,
                                @Param("jurisdictionNo") Boolean jurisdictionNo);
    /************************************************************************/

    /**
     * 信息管理模块-个人资料
     */
    UserInformationAllVO getUserInfoAll(@Param("id") String id, @Param("tableName") String tableName,
                                        @Param("jurisdictionTableName") String jurisdictionTableName,
                                        @Param("jurisdictionNo") Boolean jurisdictionNo);

    UserInfoVO getUserInfo(@Param("id") String id, @Param("tableName") String tableName,
                           @Param("jurisdictionTableName") String jurisdictionTableName,
                           @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setUserInfo(@Param("id") String id, @Param("map") UserInfoVO userInfo, @Param("tableName") String tableName,
                        @Param("jurisdictionTableName") String jurisdictionTableName,
                        @Param("jurisdictionNo") Boolean jurisdictionNo);

    UserImageVO getUserImage(@Param("id") String id, @Param("tableName") String tableName,
                             @Param("jurisdictionTableName") String jurisdictionTableName,
                             @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setUserImage(@Param("id") String id, @Param("map") UserImageVO userImage, @Param("tableName") String tableName,
                         @Param("jurisdictionTableName") String jurisdictionTableName,
                         @Param("jurisdictionNo") Boolean jurisdictionNo);

    UserSelfIntroductionVO getUserSelfIntroduction(@Param("id") String id, @Param("tableName") String tableName,
                                                   @Param("jurisdictionTableName") String jurisdictionTableName,
                                                   @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setUserSelfIntroduction(@Param("id") String id, @Param("map") UserSelfIntroductionVO userSelfIntroduction,
                                    @Param("tableName") String tableName,
                                    @Param("jurisdictionTableName") String jurisdictionTableName,
                                    @Param("jurisdictionNo") Boolean jurisdictionNo);

    UserEducationExperienceVO getUserEducationExperience(@Param("id") String id, @Param("tableName") String tableName,
                                                         @Param("jurisdictionTableName") String jurisdictionTableName,
                                                         @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setUserEducationExperience(@Param("id") String id,
                                       @Param("map") UserEducationExperienceVO userEducationExperience,
                                       @Param("tableName") String tableName,
                                       @Param("jurisdictionTableName") String jurisdictionTableName,
                                       @Param("jurisdictionNo") Boolean jurisdictionNo);

    UserSelfSignatureVO getUserSelfSignature(@Param("id") String id,
                                             @Param("tableName") String tableName,
                                             @Param("jurisdictionTableName") String jurisdictionTableName,
                                             @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setUserSelfSignature(@Param("id") String id,
                                 @Param("map") UserSelfSignatureVO userSelfSignature,
                                 @Param("tableName") String tableName,
                                 @Param("jurisdictionTableName") String jurisdictionTableName,
                                 @Param("jurisdictionNo") Boolean jurisdictionNo);

    /**
     * 信息管理模块-简历信息
     */
    MyResumeAllVO getResumeInfoAll(@Param("id") String id, @Param("tableName") String tableName,
                                   @Param("jurisdictionTableName") String jurisdictionTableName,
                                   @Param("jurisdictionNo") Boolean jurisdictionNo);

    MyResumeInfoVO getResumeInfo(@Param("id") String id, @Param("tableName") String tableName,
                                 @Param("jurisdictionTableName") String jurisdictionTableName,
                                 @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setResumeInfo(@Param("id") String id, @Param("map") MyResumeInfoVO myResumeInfo, @Param("tableName") String tableName,
                          @Param("jurisdictionTableName") String jurisdictionTableName,
                          @Param("jurisdictionNo") Boolean jurisdictionNo);

    MyResumeExpertiseVO getResumeExpertise(@Param("id") String id, @Param("tableName") String tableName,
                                           @Param("jurisdictionTableName") String jurisdictionTableName,
                                           @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setResumeExpertise(@Param("id") String id, @Param("map") MyResumeExpertiseVO myResumeExpertise, @Param("tableName") String tableName,
                               @Param("jurisdictionTableName") String jurisdictionTableName,
                               @Param("jurisdictionNo") Boolean jurisdictionNo);

    MyResumeExpectedWorkVO getResumeExpectedWork(@Param("id") String id, @Param("tableName") String tableName,
                                                 @Param("jurisdictionTableName") String jurisdictionTableName,
                                                 @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setResumeExpectedWork(@Param("id") String id, @Param("map") MyResumeExpectedWorkVO myResumeExpectedWork, @Param("tableName") String tableName,
                                  @Param("jurisdictionTableName") String jurisdictionTableName,
                                  @Param("jurisdictionNo") Boolean jurisdictionNo);

    MyResumeWorkExperienceVO getResumeWorkExperience(@Param("id") String id, @Param("tableName") String tableName,
                                                     @Param("jurisdictionTableName") String jurisdictionTableName,
                                                     @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer setResumeWorkExperience(@Param("id") String id, @Param("map") MyResumeWorkExperienceVO myResumeWorkExperience, @Param("tableName") String tableName,
                                    @Param("jurisdictionTableName") String jurisdictionTableName,
                                    @Param("jurisdictionNo") Boolean jurisdictionNo);

    /**
     * 信息管理模块-求职信息
     */
    List<ApplyVO> getUserApply(@Param("authorId") String authorId, @Param("tableName") String tableName,
                               @Param("jurisdictionTableName") String jurisdictionTableName,
                               @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer deleteUserApply(@Param("id") String id,
                            @Param("tableName") String tableName,
                            @Param("tableNameFlag") String tableNameFlag,
                            @Param("jurisdictionTableName") String jurisdictionTableName,
                            @Param("jurisdictionNo") Boolean jurisdictionNo);

    /**
     * 信息管理模块-招聘信息
     */
    List<PublishVO> getUserPublish(@Param("authorId") String authorId, @Param("tableName") String tableName,
                                   @Param("jurisdictionTableName") String jurisdictionTableName,
                                   @Param("jurisdictionNo") Boolean jurisdictionNo);

    Integer deleteUserPublish(@Param("id") String id,
                              @Param("tableName") String tableName,
                              @Param("tableNameFlag") String tableNameFlag,
                              @Param("jurisdictionTableName") String jurisdictionTableName,
                              @Param("jurisdictionNo") Boolean jurisdictionNo);
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
}
