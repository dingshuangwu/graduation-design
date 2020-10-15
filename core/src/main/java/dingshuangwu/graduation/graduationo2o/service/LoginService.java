package dingshuangwu.graduation.graduationo2o.service;

import dingshuangwu.graduation.graduationo2o.enums.TableEnum;
import dingshuangwu.graduation.graduationo2o.mapper.LoginMapper;
import dingshuangwu.graduation.graduationo2o.mapper.PublicMapper;
import dingshuangwu.graduation.graduationo2o.pojo.*;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import dingshuangwu.graduation.graduationo2o.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Service
public class LoginService {
    @Autowired
    private LoginMapper mapper;
    @Autowired
    private PublicMapper publicMapper;

    final private String TABLENAME = TableEnum.USER.getValue();

    /**
     * 登录验证
     *
     * @param name
     * @param password
     * @return
     */
    public Result login(String name, String password) {
        LoginVO loginVo = new LoginVO();
        UserVO user = mapper.login(name, LoginUtil.StringInMd5(password), TABLENAME);
        //如果loginMap为空，则数据库无匹配的账号密码，返回"用户名或密码错误"
        if (user == null) {
            return Result.error("用户名或密码错误！");
        }
        JurisdictionVO userJurisdiction = publicMapper.getJurisdictionByName(name, TableEnum.JURISDICTION.getValue());
        if (!userJurisdiction.getLogin()) {
            if (!userJurisdiction.getManagement()) {
                return Result.error("您的账号已被封禁，请联系管理员处理，e-mail:dingshuangwu@vip.qq.com！");
            }
        }
        CacheUserInfoVO userInfo = DataUtils.getSomeUserCacheUserInfoBy(name);
        if (userInfo != null) {
            DataUtils.removeCacheUserInfoByName(name);
        }
        //根据用户名，密码，当前时间组成的字符串进行MD5加密，结果作为token返回
        String token = LoginUtil.StringInMd5(name + password + System.currentTimeMillis());
        CacheUserInfoVO cacheUserInfo = new CacheUserInfoVO();
        cacheUserInfo.setToken(token);
        cacheUserInfo.setName(name);
        cacheUserInfo.setId(user.getId());
        cacheUserInfo.setJurisdiction(userJurisdiction);
        cacheUserInfo.setIsRoot(false);
        if (userJurisdiction.getManagement()) {
            if (publicMapper.getRoot(user.getId(), name, TableEnum.ROOT.getValue()) != null) {
                cacheUserInfo.setIsRoot(true);
            }
        }
        loginVo.setJurisdiction(userJurisdiction);
        loginVo.setName(name);
        loginVo.setToken(token);
        loginVo.setImageUrl(publicMapper.getImageUrlById(user.getId(), TableEnum.USERINFOMATION.getValue()));
        //将userInfo放进缓存中
        DataUtils.setCacheUserInfoByName(name, cacheUserInfo);
        return Result.success("登录成功！", loginVo);
    }

    /**
     * 退出登录
     *
     * @return
     */
    public Result loginOut() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (cacheUserInfo != null && cacheUserInfo.getToken().equals(token)) {
            //移除缓存中的userInfo
            DataUtils.removeCacheUserInfo();
            return Result.success("已成功退出登录！");
        } else {
            return Result.error("退出错误或者权限不足！");
        }
    }
}
