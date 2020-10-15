package dingshuangwu.graduation.graduationo2o.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dingshuangwu.graduation.graduationo2o.enums.InfoStatusEnum;
import dingshuangwu.graduation.graduationo2o.enums.JurisdictionEnum;
import dingshuangwu.graduation.graduationo2o.enums.TableEnum;
import dingshuangwu.graduation.graduationo2o.mapper.PublicMapper;
import dingshuangwu.graduation.graduationo2o.mapper.RootManagementMapper;
import dingshuangwu.graduation.graduationo2o.pojo.*;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.*;
import dingshuangwu.graduation.graduationo2o.pojo.UserInformation.*;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import dingshuangwu.graduation.graduationo2o.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RootManagementService {
    @Autowired
    RootManagementMapper mapper;
    @Autowired
    PublicMapper publicMapper;
    final private int PAGESIZE = 10;

    /**
     * 权限管理模块接口
     */
    public PageInfo<JurisdictionVO> getJurisdictionByName(String name, int currentPage) {
        PageInfo<JurisdictionVO> result;
        PageHelper.startPage(currentPage, PAGESIZE);
        if (name == null || "".equals(name) || "null".equals(name)) {
            result = new PageInfo<JurisdictionVO>(mapper.getJurisdictionByName(null, TableEnum.JURISDICTION.getValue()));
            return result;
        } else {
            result = new PageInfo<JurisdictionVO>(mapper.getJurisdictionByName(name, TableEnum.JURISDICTION.getValue()));
            return result;
        }
    }

    public int setJurisdiction(JurisdictionVO jurisdiction) {
        String id = publicMapper.getIdByName(jurisdiction.getName(), TableEnum.USER.getValue());
        jurisdiction.setId(id);
        Integer result = mapper.setJurisdictionById(jurisdiction, TableEnum.JURISDICTION.getValue());
        publicMapper.setFlagById(id, InfoStatusEnum.DEFAULT.getValue(), TableEnum.APPLYFORMANAGEMENT.getValue());
        CacheUserInfoVO cacheUserInfo = DataUtils.getSomeUserCacheUserInfoBy(jurisdiction.getName());
        if (cacheUserInfo != null) {
            cacheUserInfo.getJurisdiction().setManagement(jurisdiction.getManagement());
            cacheUserInfo.getJurisdiction().setMyApply(jurisdiction.getMyApply());
            cacheUserInfo.getJurisdiction().setApply(jurisdiction.getApply());
            cacheUserInfo.getJurisdiction().setMyPublish(jurisdiction.getMyPublish());
            cacheUserInfo.getJurisdiction().setLogin(jurisdiction.getLogin());
        }
        return result;
    }

    /************************************************************************/
    /**
     * 信息管理模块-用户列表
     */
    public PageInfo<Map<String, String>> getUserList(String name, int currentPage) {
        PageInfo<Map<String, String>> result;
        PageHelper.startPage(currentPage, PAGESIZE);
        if (name == null || "".equals(name) || "null".equals(name)) {
            result = new PageInfo<Map<String, String>>(publicMapper.getUserListHaveManagement(null,
                    TableEnum.USER.getValue()));
            return result;
        } else {
            result = new PageInfo<Map<String, String>>(publicMapper.getUserListHaveManagement(name,
                    TableEnum.USER.getValue()));
            return result;
        }
    }

    /**
     * 信息管理模块-个人资料
     */
    public UserInformationAllVO getUserInfoAll(String id) {
        UserInformationAllVO result = mapper.getUserInfoAll(id, TableEnum.USERINFOMATION.getValue());
        result.setBirthday(DateUtils.tansformDate(result.getBirthday()));
        result.setEducationExperienceFirstStartDate(DateUtils.tansformDate(result.getEducationExperienceFirstStartDate()));
        result.setEducationExperienceFirstEndDate(DateUtils.tansformDate(result.getEducationExperienceFirstEndDate()));
        result.setEducationExperienceSecondStartDate(DateUtils.tansformDate(result.getEducationExperienceSecondStartDate()));
        result.setEducationExperienceSecondEndDate(DateUtils.tansformDate(result.getEducationExperienceSecondEndDate()));
        result.setEducationExperienceThirdStartDate(DateUtils.tansformDate(result.getEducationExperienceThirdStartDate()));
        result.setEducationExperienceThirdEndDate(DateUtils.tansformDate(result.getEducationExperienceThirdEndDate()));
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public UserInfoVO getUserInfo(String id) {
        UserInfoVO result = mapper.getUserInfo(id, TableEnum.USERINFOMATION.getValue());
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        result.setBirthday(DateUtils.tansformDate(result.getBirthday()));
        return result;
    }

    public Integer setUserInfo(String id, UserInfoVO userInfo) {
        userInfo.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setUserInfo(id, userInfo, TableEnum.USERINFOMATION.getValue());
    }

    public UserImageVO getUserImage(String id) {
        UserImageVO result = mapper.getUserImage(id, TableEnum.USERINFOMATION.getValue());
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public Integer setUserImage(String id, UserImageVO userImage) {
        userImage.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setUserImage(id, userImage, TableEnum.USERINFOMATION.getValue());
    }

    public UserSelfIntroductionVO getUserSelfIntroduction(String id) {
        UserSelfIntroductionVO result = mapper.getUserSelfIntroduction(id, TableEnum.USERINFOMATION.getValue());
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public Integer setUserSelfIntroduction(String id, UserSelfIntroductionVO userSelfIntroduction) {
        userSelfIntroduction.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setUserSelfIntroduction(id, userSelfIntroduction, TableEnum.USERINFOMATION.getValue());
    }

    public UserEducationExperienceVO getUserEducationExperience(String id) {
        UserEducationExperienceVO result = mapper.getUserEducationExperience(id, TableEnum.USERINFOMATION.getValue());
        result.setEducationExperienceFirstStartDate(DateUtils.tansformDate(result.getEducationExperienceFirstStartDate()));
        result.setEducationExperienceFirstEndDate(DateUtils.tansformDate(result.getEducationExperienceFirstEndDate()));
        result.setEducationExperienceSecondStartDate(DateUtils.tansformDate(result.getEducationExperienceSecondStartDate()));
        result.setEducationExperienceSecondEndDate(DateUtils.tansformDate(result.getEducationExperienceSecondEndDate()));
        result.setEducationExperienceThirdStartDate(DateUtils.tansformDate(result.getEducationExperienceThirdStartDate()));
        result.setEducationExperienceThirdEndDate(DateUtils.tansformDate(result.getEducationExperienceThirdEndDate()));
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public Integer setUserEducationExperience(String id, UserEducationExperienceVO userEducationExperience) {
        userEducationExperience.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setUserEducationExperience(id, userEducationExperience, TableEnum.USERINFOMATION.getValue());
    }

    public UserSelfSignatureVO getUserSelfSignature(String id) {
        UserSelfSignatureVO result = mapper.getUserSelfSignature(id, TableEnum.USERINFOMATION.getValue());
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public Integer setUserSelfSignature(String id, UserSelfSignatureVO userSelfSignature) {
        userSelfSignature.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setUserSelfSignature(id, userSelfSignature, TableEnum.USERINFOMATION.getValue());
    }

    /**
     * 信息管理模块-简历信息
     */
    public MyResumeAllVO getResumeInfoAll(String id) {
        MyResumeAllVO result = mapper.getResumeInfoAll(id, TableEnum.MYRESUME.getValue());
        result.setImageUrl(publicMapper.getImageUrlById(id, TableEnum.USERINFOMATION.getValue()));
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        result.setBirthday(DateUtils.tansformDate(result.getBirthday()));
        return result;
    }

    public MyResumeInfoVO getResumeInfo(String id) {
        MyResumeInfoVO result = mapper.getResumeInfo(id, TableEnum.MYRESUME.getValue());
        result.setImageUrl(publicMapper.getImageUrlById(id, TableEnum.USERINFOMATION.getValue()));
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        result.setBirthday(DateUtils.tansformDate(result.getBirthday()));
        return result;
    }

    public Integer setResumeInfo(String id, MyResumeInfoVO myResumeInfo) {
        myResumeInfo.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setResumeInfo(id, myResumeInfo, TableEnum.MYRESUME.getValue());
    }

    public MyResumeExpertiseVO getResumeExpertise(String id) {
        MyResumeExpertiseVO result = mapper.getResumeExpertise(id, TableEnum.MYRESUME.getValue());
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public Integer setResumeExpertise(String id, MyResumeExpertiseVO myResumeExpertise) {
        myResumeExpertise.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setResumeExpertise(id, myResumeExpertise, TableEnum.MYRESUME.getValue());
    }

    public MyResumeExpectedWorkVO getResumeExpectedWork(String id) {
        MyResumeExpectedWorkVO result = mapper.getResumeExpectedWork(id, TableEnum.MYRESUME.getValue());
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public Integer setMyResumeExpectedWork(String id, MyResumeExpectedWorkVO myResumeExpectedWork) {
        myResumeExpectedWork.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setResumeExpectedWork(id, myResumeExpectedWork, TableEnum.MYRESUME.getValue());
    }

    public MyResumeWorkExperienceVO getMyResumeWorkExperience(String id) {
        MyResumeWorkExperienceVO result = mapper.getResumeWorkExperience(id, TableEnum.MYRESUME.getValue());
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public Integer setMyResumeWorkExperience(String id, MyResumeWorkExperienceVO myResumeWorkExperience) {
        myResumeWorkExperience.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setResumeWorkExperience(id, myResumeWorkExperience, TableEnum.MYRESUME.getValue());
    }

    /**
     * 信息管理模块-求职信息
     */
    public PageInfo<ApplyVO> getUserApply(String authorId, int currentPage) {
        PageInfo<ApplyVO> result;
        PageHelper.startPage(currentPage, PAGESIZE);
        result = new PageInfo<ApplyVO>(mapper.getUserApply(authorId, TableEnum.APPLY.getValue()));
        return result;
    }

    public Integer deleteUserApply(String id) {
        return mapper.deleteUserApply(id, TableEnum.APPLY.getValue(), InfoStatusEnum.DELETED.getValue());
    }

    /**
     * 信息管理模块-招聘信息
     */
    public PageInfo<PublishVO> getUserPublish(String authorId, int currentPage) {
        PageInfo<PublishVO> result;
        PageHelper.startPage(currentPage, PAGESIZE);
        result = new PageInfo<PublishVO>(mapper.getUserPublish(authorId, TableEnum.PUBLISH.getValue()));
        return result;
    }

    public Integer deleteUserPublish(String id) {
        return mapper.deleteUserPublish(id, TableEnum.PUBLISH.getValue(), InfoStatusEnum.DELETED.getValue());
    }
    /************************************************************************/

    /**
     * 用户申请模块-实名认证
     */

    public PageInfo<RealNameAuthenticationVO> getRealNameAuthentication(String name, int currentPage) {
        PageInfo<RealNameAuthenticationVO> result;
        PageHelper.startPage(currentPage, PAGESIZE);
        if (name == null || "null".equals(name) || "".equals(name)) {
            result = new PageInfo<RealNameAuthenticationVO>(mapper.getRealNameAuthentication(null, InfoStatusEnum.APPLYING.getValue(), TableEnum.REALNAMEAUTHENTICATION.getValue()));
        } else {
            result = new PageInfo<RealNameAuthenticationVO>(mapper.getRealNameAuthentication(name, InfoStatusEnum.APPLYING.getValue(), TableEnum.REALNAMEAUTHENTICATION.getValue()));
        }
        return result;
    }

    public Integer realNameAuthenticationPass(String id) {
        Integer result = mapper.realNameAuthenticationPass(id, InfoStatusEnum.VERIFIED.getValue(),
                TableEnum.REALNAMEAUTHENTICATION.getValue(),
                TableEnum.USER.getValue(),
                TableEnum.JURISDICTION.getValue(),
                JurisdictionEnum.YES.getValue());
        if (result != null && result > 0) {
            String name = publicMapper.getNameById(id, TableEnum.USER.getValue());
            CacheUserInfoVO cacheUserInfo = DataUtils.getSomeUserCacheUserInfoBy(name);
            if (cacheUserInfo != null) {
                cacheUserInfo.getJurisdiction().setApplyForManagement(JurisdictionEnum.YES.getValue());
            }
        }
        return result;
    }

    public Integer realNameAuthenticationReject(String id) {
        Integer result = mapper.realNameAuthenticationReject(id, InfoStatusEnum.REJECT.getValue(),
                TableEnum.REALNAMEAUTHENTICATION.getValue(),
                TableEnum.USER.getValue(),
                TableEnum.JURISDICTION.getValue(),
                JurisdictionEnum.YES.getValue());
        if (result != null && result > 0) {
            String name = publicMapper.getNameById(id, TableEnum.USER.getValue());
            CacheUserInfoVO cacheUserInfo = DataUtils.getSomeUserCacheUserInfoBy(name);
            if (cacheUserInfo != null) {
                cacheUserInfo.getJurisdiction().setRealNameAuthentication(JurisdictionEnum.YES.getValue());
            }
        }
        return result;
    }
    /************************************************************************/
    /**
     * 超级管理员
     */
    public Boolean isRoot() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return cacheUserInfo.getIsRoot();
    }

    public PageInfo<ApplyForManagementVO> getApplyForManagement(String name, int currentPage) {
        PageInfo<ApplyForManagementVO> result;
        PageHelper.startPage(currentPage, PAGESIZE);
        if (name == null || "null".equals(name) || "".equals(name)) {
            result = new PageInfo<ApplyForManagementVO>(mapper.getApplyForManagement(null, InfoStatusEnum.APPLYING.getValue(), TableEnum.APPLYFORMANAGEMENT.getValue()));
        } else {
            result = new PageInfo<ApplyForManagementVO>(mapper.getApplyForManagement(name, InfoStatusEnum.APPLYING.getValue(), TableEnum.APPLYFORMANAGEMENT.getValue()));
        }
        return result;
    }

    public RealNameAuthenticationVO getRealNameAuthenticationById(String id) {
        return mapper.getRealNameAuthenticationById(id, InfoStatusEnum.VERIFIED.getValue(), TableEnum.REALNAMEAUTHENTICATION.getValue());
    }

    public Integer ApplyForManagementPass(String id) {
        Integer result = mapper.ApplyForManagementPass(id,
                InfoStatusEnum.VERIFIED.getValue(),
                TableEnum.APPLYFORMANAGEMENT.getValue(),
                TableEnum.JURISDICTION.getValue(),
                JurisdictionEnum.YES.getValue());
        if (result != null && result > 0) {
            String name = publicMapper.getNameById(id, TableEnum.USER.getValue());
            CacheUserInfoVO cacheUserInfo = DataUtils.getSomeUserCacheUserInfoBy(name);
            if (cacheUserInfo != null) {
                cacheUserInfo.getJurisdiction().setManagement(JurisdictionEnum.YES.getValue());
            }
        }
        return result;
    }

    public Integer ApplyForManagemenReject(String id) {
        Integer result = mapper.ApplyForManagementReject(id,
                InfoStatusEnum.REJECT.getValue(),
                TableEnum.APPLYFORMANAGEMENT.getValue(),
                TableEnum.JURISDICTION.getValue(),
                JurisdictionEnum.YES.getValue());
        if (result != null && result > 0) {
            String name = publicMapper.getNameById(id, TableEnum.USER.getValue());
            CacheUserInfoVO cacheUserInfo = DataUtils.getSomeUserCacheUserInfoBy(name);
            if (cacheUserInfo != null) {
                cacheUserInfo.getJurisdiction().setApplyForManagement(JurisdictionEnum.YES.getValue());
            }
        }
        return result;
    }
    /************************************************************************/
}
