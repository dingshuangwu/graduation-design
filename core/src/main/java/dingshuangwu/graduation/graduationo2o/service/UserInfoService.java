package dingshuangwu.graduation.graduationo2o.service;

import dingshuangwu.graduation.graduationo2o.enums.TableEnum;
import dingshuangwu.graduation.graduationo2o.mapper.PublicMapper;
import dingshuangwu.graduation.graduationo2o.mapper.UserInfoMapper;
import dingshuangwu.graduation.graduationo2o.pojo.CacheUserInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.UserInformation.*;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import dingshuangwu.graduation.graduationo2o.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper mapper;
    @Autowired
    private PublicMapper publicMapper;

    private final String TABLENAME = TableEnum.USERINFOMATION.getValue();

    public UserInformationAllVO getUserAllInfo(String id) {
        UserInformationAllVO result = mapper.getUserAllInfo(id, TABLENAME);
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
        UserInfoVO result = mapper.getUserInfo(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        result.setBirthday(DateUtils.tansformDate(result.getBirthday()));
        return result;
    }

    public int setUserInfo(String id, UserInfoVO userInfo) {
        userInfo.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setUserInfo(id, userInfo, TABLENAME);
    }

    public UserImageVO getUserImage(String id) {
        UserImageVO result = mapper.getUserImage(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public int setUserImage(String id, UserImageVO userImage) {
        userImage.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setUserImage(id, userImage, TABLENAME);
    }

    public UserSelfIntroductionVO getSelfIntroduction(String id) {
        UserSelfIntroductionVO result = mapper.getSelfIntroduction(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public int setSelfIntroduction(String id, UserSelfIntroductionVO userSelfIntroductionVO) {
        userSelfIntroductionVO.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setSelfIntroduction(id, userSelfIntroductionVO, TABLENAME);
    }

    public UserEducationExperienceVO getEducationExperience(String id) {
        UserEducationExperienceVO result = mapper.getEducationExperience(id, TABLENAME);
        result.setEducationExperienceFirstStartDate(DateUtils.tansformDate(result.getEducationExperienceFirstStartDate()));
        result.setEducationExperienceFirstEndDate(DateUtils.tansformDate(result.getEducationExperienceFirstEndDate()));
        result.setEducationExperienceSecondStartDate(DateUtils.tansformDate(result.getEducationExperienceSecondStartDate()));
        result.setEducationExperienceSecondEndDate(DateUtils.tansformDate(result.getEducationExperienceSecondEndDate()));
        result.setEducationExperienceThirdStartDate(DateUtils.tansformDate(result.getEducationExperienceThirdStartDate()));
        result.setEducationExperienceThirdEndDate(DateUtils.tansformDate(result.getEducationExperienceThirdEndDate()));
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public int setEducationExperience(String id, UserEducationExperienceVO userEducationExperience) {
        userEducationExperience.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setEducationExperience(id, userEducationExperience, TABLENAME);
    }

    public UserSelfSignatureVO getSelfSignature(String id) {
        UserSelfSignatureVO result = mapper.getSelfSignature(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public int setSelfSignature(String id, UserSelfSignatureVO userSelfSignature) {
        userSelfSignature.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setSelfSignature(id, userSelfSignature, TABLENAME);
    }

    public UserInformationAllVO getSomeBodyInfoByName(String name) {
        String id = publicMapper.getIdByName(name, TableEnum.USER.getValue());
        UserInformationAllVO result = mapper.getSomeBodyInfoById(id, TABLENAME);
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

    public String getRealNameAuthenticationState() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return publicMapper.getIsRealNameAuthenticationById(cacheUserInfo.getId(), TableEnum.USER.getValue());
    }

    public String getApplyForManagementState() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return publicMapper.getApplyForManagementStateById(cacheUserInfo.getId(), TableEnum.APPLYFORMANAGEMENT.getValue());
    }

}
