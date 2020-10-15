package dingshuangwu.graduation.graduationo2o.service;

import dingshuangwu.graduation.graduationo2o.enums.InfoStatusEnum;
import dingshuangwu.graduation.graduationo2o.enums.TableEnum;
import dingshuangwu.graduation.graduationo2o.mapper.PublicMapper;
import dingshuangwu.graduation.graduationo2o.mapper.SignInMapper;
import dingshuangwu.graduation.graduationo2o.pojo.*;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeAllVO;
import dingshuangwu.graduation.graduationo2o.pojo.UserInformation.UserInformationAllVO;
import dingshuangwu.graduation.graduationo2o.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Service
public class SignInService {

    @Autowired
    private SignInMapper mapper;
    @Autowired
    private PublicMapper publicMapper;
    final private String TABLENAME = TableEnum.USER.getValue();

    @Transactional(rollbackFor = Exception.class)
    public int signIn(UserVO user) {
        user.setPassword(LoginUtil.StringInMd5(user.getPassword()));
        user.setFlag(InfoStatusEnum.NORMAL.getValue());
        user.setRealNameAuthentication(InfoStatusEnum.DEFAULT.getValue());
        user.setCreateDate(DateUtils.getFormatDate());
        UserVO user_result = publicMapper.getUserByName(user.getName(), TABLENAME);
        if (user_result == null) {
            mapper.signIn(user, TABLENAME);
            //创建默认权限
            JurisdictionVO jurisDiction = UserUtils.createdDefaultJurisDiction(user.getId(), user.getName());
            mapper.setJurisdiction(jurisDiction, TableEnum.JURISDICTION.getValue());
            //创建默认个人信息
            UserInformationAllVO userInformationAll = UserUtils.createdDefaultUserInfo(user.getId());
            mapper.setUserInfo(userInformationAll, TableEnum.USERINFOMATION.getValue());
            //创建默认简历信息
            MyResumeAllVO myResumeAll = UserUtils.createdDefaultMyResume(user.getId());
            mapper.setMyResume(myResumeAll, TableEnum.MYRESUME.getValue());
            //创建实名认证默认信息
            RealNameAuthenticationVO realNameAuthentication = UserUtils.createDefaultRealNameAuthentication(user.getId(), user.getName());
            mapper.setRealNameAuthentication(realNameAuthentication, TableEnum.REALNAMEAUTHENTICATION.getValue());
            //创建申请管理员权限默认信息
            ApplyForManagementVO applyForManagement = UserUtils.createDefaultApplyForManagement(user.getId(), user.getName());
            mapper.setApplyForManagement(applyForManagement, TableEnum.APPLYFORMANAGEMENT.getValue());
            return 1;
        } else {
            return -1;
        }
    }

    public int updatePassword(String name, String oldPassword, String newPassword) {
        String id = publicMapper.getIdByName(name, TABLENAME);
        oldPassword = LoginUtil.StringInMd5(oldPassword);
        if (oldPassword.equals(publicMapper.getPasswordById(id, TABLENAME))) {
            newPassword = LoginUtil.StringInMd5(newPassword);
            int result = mapper.updatePasswordById(id, newPassword, TABLENAME);
            CacheUserInfoVO cacheUserInfo = DataUtils.getSomeUserCacheUserInfoBy(name);
            if (cacheUserInfo != null) {
                //移除缓存中的userInfo
                DataUtils.removeCacheUserInfoByName(name);
            }
            return result;
        } else {
            return -1;
        }
    }
}
