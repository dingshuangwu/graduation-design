package dingshuangwu.graduation.graduationo2o.controller;

import dingshuangwu.graduation.graduationo2o.pojo.CacheUserInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.Result;
import dingshuangwu.graduation.graduationo2o.pojo.UserInformation.*;
import dingshuangwu.graduation.graduationo2o.service.UserInfoService;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshuangwu
 * @date 2020.02.06
 */
@RestController
@RequestMapping("api/user-info")
public class UserInfoApi {
    @Autowired
    private UserInfoService service;

    @GetMapping("all")
    public Result getUserAllInfo() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得用户信息-所有信息成功", service.getUserAllInfo(cacheUserInfo.getId()));
    }

    @GetMapping("info")
    public Result getUserInfo() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得用户信息-个人资料信息成功", service.getUserInfo(cacheUserInfo.getId()));
    }

    //信息参数由前端传入，lastModifyTime由系统给出
    @GetMapping("set-info")
    public Result setUserInfo(UserInfoVO userInfo) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (service.setUserInfo(cacheUserInfo.getId(), userInfo) > 0) {
            return Result.success("修改用户信息-个人资料信息成功");
        } else {
            return Result.error("修改用户信息-个人资料信息失败");
        }
    }

    @GetMapping("user-image")
    public Result getUserImage() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得用户信息-个人资料信息成功", service.getUserImage(cacheUserInfo.getId()));
    }

    @PostMapping("set-user-image")
    public Result setUserImage(UserImageVO userImage) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (service.setUserImage(cacheUserInfo.getId(), userImage) > 0) {
            return Result.success("修改用户信息-用户头像成功");
        } else {
            return Result.error("修改用户信息-用户头像失败");
        }
    }

    @GetMapping("self-introduction")
    public Result getSelfIntroduction() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得用户信息-个人介绍成功", service.getSelfIntroduction(cacheUserInfo.getId()));
    }

    @GetMapping("set-self-introduction")
    public Result setSelfIntroduction(UserSelfIntroductionVO userSelfIntroduction) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (service.setSelfIntroduction(cacheUserInfo.getId(), userSelfIntroduction) > 0) {
            return Result.success("修改用户信息-个人介绍成功");
        } else {
            return Result.error("修改用户信息-个人介绍失败");
        }
    }

    @GetMapping("education-experience")
    public Result getEducationExperience() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得用户信息-教育经历成功", service.getEducationExperience(cacheUserInfo.getId()));
    }

    @GetMapping("set-education-experience")
    public Result setEducationExperience(UserEducationExperienceVO userEducationExperience) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (service.setEducationExperience(cacheUserInfo.getId(), userEducationExperience) > 0) {
            return Result.success("修改用户信息-教育经历成功");
        } else {
            return Result.error("修改用户信息-教育经历失败");
        }
    }

    @GetMapping("self-signature")
    public Result getSelfSignature() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得用户信息-个人签名成功", service.getSelfSignature(cacheUserInfo.getId()));
    }

    @GetMapping("set-self-signature")
    public Result setSelfSignature(UserSelfSignatureVO userSelfSignature) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (service.setSelfSignature(cacheUserInfo.getId(), userSelfSignature) > 0) {
            return Result.success("修改用户信息-个人签名成功");
        } else {
            return Result.error("修改用户信息-个人签名失败");
        }
    }

    @GetMapping("get-somebody-info")
    public Result getSomeBodyInfoByName(String name) {
        return Result.success("获取" + name + "的信息成功", service.getSomeBodyInfoByName(name));
    }

    @GetMapping("get-real-name-authentication-state")
    public Result getRealNameAuthenticationState() {
        return Result.success("获取实名认证状态成功！", service.getRealNameAuthenticationState());
    }

    @GetMapping("get-apply-for-management-state")
    public Result getApplyForManagementState() {
        return Result.success("获取管理员权限申请状态成功！", service.getApplyForManagementState());
    }
}
