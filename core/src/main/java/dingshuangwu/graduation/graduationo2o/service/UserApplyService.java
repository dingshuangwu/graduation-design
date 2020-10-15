package dingshuangwu.graduation.graduationo2o.service;

import dingshuangwu.graduation.graduationo2o.enums.InfoStatusEnum;
import dingshuangwu.graduation.graduationo2o.enums.JurisdictionEnum;
import dingshuangwu.graduation.graduationo2o.enums.TableEnum;
import dingshuangwu.graduation.graduationo2o.mapper.PublicMapper;
import dingshuangwu.graduation.graduationo2o.mapper.UserApplyMapper;
import dingshuangwu.graduation.graduationo2o.pojo.ApplyForManagementVO;
import dingshuangwu.graduation.graduationo2o.pojo.CacheUserInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.RealNameAuthenticationVO;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import dingshuangwu.graduation.graduationo2o.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserApplyService {
    @Autowired
    UserApplyMapper mapper;
    @Autowired
    PublicMapper publicMapper;

    public int realNameAuthentication(RealNameAuthenticationVO realNameAuthentication) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        realNameAuthentication.setId(cacheUserInfo.getId());
        realNameAuthentication.setFlag(InfoStatusEnum.APPLYING.getValue());
        realNameAuthentication.setApplyDate(DateUtils.getFormatDate());
        int result = mapper.setRealNameAuthentication(realNameAuthentication, TableEnum.REALNAMEAUTHENTICATION.getValue(),
                TableEnum.USER.getValue(), TableEnum.JURISDICTION.getValue(), JurisdictionEnum.NO.getValue());
        if (result > 0) {
            cacheUserInfo.getJurisdiction().setRealNameAuthentication(JurisdictionEnum.NO.getValue());
        }
        return result;
    }

    public RealNameAuthenticationVO getRealNameAuthentication() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return mapper.getRealNameAuthentication(cacheUserInfo.getId(), TableEnum.REALNAMEAUTHENTICATION.getValue());
    }

    public int applyForManagement(ApplyForManagementVO applyForManagement) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        applyForManagement.setId(cacheUserInfo.getId());
        applyForManagement.setFlag(InfoStatusEnum.APPLYING.getValue());
        applyForManagement.setApplyDate(DateUtils.getFormatDate());
        int result = mapper.setApplyForManagement(applyForManagement, TableEnum.APPLYFORMANAGEMENT.getValue(),
                TableEnum.JURISDICTION.getValue(), JurisdictionEnum.NO.getValue());
        if (result > 0) {
            cacheUserInfo.getJurisdiction().setApplyForManagement(JurisdictionEnum.NO.getValue());
        }
        return result;
    }
}
