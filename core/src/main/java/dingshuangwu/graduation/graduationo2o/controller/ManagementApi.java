package dingshuangwu.graduation.graduationo2o.controller;

import dingshuangwu.graduation.graduationo2o.pojo.CacheUserInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.JurisdictionVO;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeExpectedWorkVO;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeExpertiseVO;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeWorkExperienceVO;
import dingshuangwu.graduation.graduationo2o.pojo.Result;
import dingshuangwu.graduation.graduationo2o.pojo.UserInformation.*;
import dingshuangwu.graduation.graduationo2o.service.ManagementService;
import dingshuangwu.graduation.graduationo2o.service.RootManagementService;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import dingshuangwu.graduation.graduationo2o.utils.MemoryCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/management")
public class ManagementApi {
    @Autowired
    ManagementService service;
    @Autowired
    RootManagementService rootService;

    /**
     * 权限管理模块接口
     */
    @PostMapping("get-jurisdiction")
    public Result getJurisdictionByName(String name, int currentPage) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            if (name.contains(" ")) {
                return Result.error("用户名不能包含空格!");
            }
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户权限信息成功", rootService.getJurisdictionByName(name, currentPage));
            } else {
                return Result.success("获取用户权限信息成功", service.getJurisdictionByName(name, currentPage));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-jurisdiction")
    public Result setJurisdictionByName(JurisdictionVO jurisdiction) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setJurisdiction(jurisdiction);
            } else {
                result = service.setJurisdiction(jurisdiction);
            }
            if (result != null && result > 0) {
                if (MemoryCache.get("userInfo" + jurisdiction.getName()) != null) {
                    MemoryCache.remove("userInfo" + jurisdiction.getName());
                }
                return Result.success("设置用户权限信息成功");
            } else {
                return Result.error("用户权限信息未修改");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }
    /************************************************************************/


    /**
     * 信息管理模块-用户列表
     */
    @PostMapping("get-user-list")
    public Result getUserList(String name, int currentPage) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            if (name.contains(" ")) {
                return Result.error("用户名不能包含空格!");
            }
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户列表成功", rootService.getUserList(name, currentPage));
            } else {
                return Result.success("获取用户列表成功", service.getUserList(name, currentPage));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    /**
     * 信息管理模块-个人资料
     */
    @PostMapping("get-user-info-all")
    public Result getUserInfoAll(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户个人资料所有信息成功！", rootService.getUserInfoAll(id));
            } else {
                return Result.success("获取用户个人资料所有信息成功！", service.getUserInfoAll(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("get-user-info")
    public Result getUserInfo(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户个人资料-基础信息成功！", rootService.getUserInfo(id));
            } else {
                return Result.success("获取用户个人资料-基础信息成功！", service.getUserInfo(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-user-info")
    public Result setUserInfo(String id, UserInfoVO userInfo) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setUserInfo(id, userInfo);
            } else {
                result = service.setUserInfo(id, userInfo);
            }
            if (result != null && result > 0) {
                return Result.success("修改用户个人资料-基础信息成功！");
            } else {
                return Result.error("修改用户个人资料-基础信息失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("get-user-image")
    public Result getUserImage(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户个人资料-用户头像信息成功！", rootService.getUserImage(id));
            } else {
                return Result.success("获取用户个人资料-用户头像信息成功！", service.getUserImage(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-user-image")
    public Result setUserImage(String id, UserImageVO userImage) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setUserImage(id, userImage);
            } else {
                result = service.setUserImage(id, userImage);
            }
            if (result != null && result > 0) {
                return Result.success("更新用户个人资料-用户头像成功！");
            } else {
                return Result.error("更新用户个人资料-用户头像失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("get-user-self-introduction")
    public Result getUserSelfIntroduction(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户个人资料-个人介绍信息成功！", rootService.getUserSelfIntroduction(id));
            } else {
                return Result.success("获取用户个人资料-个人介绍信息成功！", service.getUserSelfIntroduction(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-user-self-introduction")
    public Result setUserSelfIntroduction(String id, UserSelfIntroductionVO userSelfIntroduction) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setUserSelfIntroduction(id, userSelfIntroduction);
            } else {
                result = service.setUserSelfIntroduction(id, userSelfIntroduction);
            }
            if (result != null && result > 0) {
                return Result.success("更新用户个人资料-个人介绍信息成功！");
            } else {
                return Result.error("更新用户个人资料-个人介绍信息失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("get-user-education-experience")
    public Result getUserEducationExperience(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户个人资料-教育经历信息成功！", rootService.getUserEducationExperience(id));
            } else {
                return Result.success("获取用户个人资料-教育经历信息成功！", service.getUserEducationExperience(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-user-education-experience")
    public Result setUserEducationExperience(String id, UserEducationExperienceVO userEducationExperience) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setUserEducationExperience(id, userEducationExperience);
            } else {
                result = service.setUserEducationExperience(id, userEducationExperience);
            }
            if (result != null && result > 0) {
                return Result.success("修改用户个人资料-教育经历信息成功！");
            } else {
                return Result.error("修改用户个人资料-教育经历信息失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("get-user-self-signature")
    public Result getSelfSignature(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户个人资料-个性签名信息成功！", rootService.getUserSelfSignature(id));
            } else {
                return Result.success("获取用户个人资料-个性签名信息成功！", service.getUserSelfSignature(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-user-self-signature")
    public Result setUserSelfSignature(String id, UserSelfSignatureVO userSelfSignature) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setUserSelfSignature(id, userSelfSignature);
            } else {
                result = service.setUserSelfSignature(id, userSelfSignature);
            }
            if (result != null && result > 0) {
                return Result.success("更新用户个人资料-个性签名成功！");
            } else {
                return Result.error("更新用户个人资料-个性签名失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    /**
     * 信息管理模块-简历信息
     */
    @PostMapping("get-resume-info-all")
    public Result getResumeInfoAll(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户简历信息所有信息成功！", rootService.getResumeInfoAll(id));
            } else {
                return Result.success("获取用户简历信息所有信息成功！", service.getResumeInfoAll(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("get-resume-info")
    public Result getResumeInfo(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户简历信息-基础信息成功！", rootService.getResumeInfo(id));
            } else {
                return Result.success("获取用户简历信息-基础信息成功！", service.getResumeInfo(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-resume-info")
    public Result setResumeInfo(String id, MyResumeInfoVO myResumeInfo) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setResumeInfo(id, myResumeInfo);
            } else {
                result = service.setResumeInfo(id, myResumeInfo);
            }
            if (result != null && result > 0) {
                return Result.success("更新用户简历信息-基础信息成功！");
            } else {
                return Result.error("更新用户简历信息-基础信息失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("get-resume-expertise")
    public Result getResumeExpertise(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户简历信息-技能专长信息成功！", rootService.getResumeExpertise(id));
            } else {
                return Result.success("获取用户简历信息-技能专长信息成功！", service.getResumeExpertise(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-resume-expertise")
    public Result setResumeExpertise(String id, MyResumeExpertiseVO myResumeExpertise) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setResumeExpertise(id, myResumeExpertise);
            } else {
                result = service.setResumeExpertise(id, myResumeExpertise);
            }
            if (result != null && result > 0) {
                return Result.success("更新用户简历信息-技能专长成功！");
            } else {
                return Result.error("更新用户简历信息-技能专长失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("get-resume-expected-work")
    public Result getResumeExpectedWork(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户简历信息-期望职位成功！", rootService.getResumeExpectedWork(id));
            } else {
                return Result.success("获取用户简历信息-期望职位成功！", service.getResumeExpectedWork(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-resume-expected-work")
    public Result setMyResumeExpectedWork(String id, MyResumeExpectedWorkVO myResumeExpectedWork) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setMyResumeExpectedWork(id, myResumeExpectedWork);
            } else {
                result = service.setMyResumeExpectedWork(id, myResumeExpectedWork);
            }
            if (result != null & result > 0) {
                return Result.success("更新用户简历信息-期望职位成功！");
            } else {
                return Result.error("更新用户简历信息-期望职位失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("get-resume-work-experience")
    public Result getMyResumeWorkExperience(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户简历信息-工作经验信息成功！", rootService.getMyResumeWorkExperience(id));
            } else {
                return Result.success("获取用户简历信息-工作经验信息成功！", service.getMyResumeWorkExperience(id));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("set-resume-work-experience")
    public Result setMyResumeWorkExperience(String id, MyResumeWorkExperienceVO myResumeWorkExperience) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.setMyResumeWorkExperience(id, myResumeWorkExperience);
            } else {
                result = service.setMyResumeWorkExperience(id, myResumeWorkExperience);
            }
            if (result != null && result > 0) {
                return Result.success("更新用户简历信息-工作经验成功！");
            } else {
                return Result.error("更新用户简历信息-工作经验失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    /**
     * 信息管理模块-求职信息
     */
    @PostMapping("get-user-apply")
    public Result getUserApply(String authorId, int currentPage) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户求职信息成功", rootService.getUserApply(authorId, currentPage));
            } else {
                return Result.success("获取用户求职信息成功", service.getUserApply(authorId, currentPage));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("delete-user-apply")
    public Result deleteUserApply(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.deleteUserApply(id);
            } else {
                result = service.deleteUserApply(id);
            }
            if (result != null && result > 0) {
                return Result.success("删除用户求职信息成功！");
            } else {
                return Result.error("删除用户求职信息失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    /**
     * 信息管理模块-招聘信息
     */
    @PostMapping("get-user-publish")
    public Result getUserPublish(String authorId, int currentPage) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取用户招聘信息成功", rootService.getUserPublish(authorId, currentPage));
            } else {
                return Result.success("获取用户招聘信息成功", service.getUserPublish(authorId, currentPage));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("delete-user-publish")
    public Result deleteUserPublish(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            Integer result;
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                result = rootService.deleteUserPublish(id);
            } else {
                result = service.deleteUserPublish(id);
            }
            if (result != null && result > 0) {
                return Result.success("删除用户招聘信息成功！");
            } else {
                return Result.error("删除用户招聘信息失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }
    /************************************************************************/
    /**
     * 用户申请模块-实名认证
     */
    @PostMapping("real-name-authentication")
    public Result realNameAuthentication(String name, int currentPage) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            if (name.contains(" ")) {
                return Result.error("用户名不能包含空格!");
            }
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            if (cacheUserInfo.getIsRoot()) {
                return Result.success("获取实名认证信息成功！", rootService.getRealNameAuthentication(name, currentPage));
            } else {
                return Result.success("获取实名认证信息成功！", service.getRealNameAuthentication(name, currentPage));
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("real-name-authentication-pass")
    public Result realNameAuthenticationPass(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            if (service.realNameAuthenticationPass(id) > 0) {
                return Result.success("操作成功！");
            } else {
                return Result.error("操作失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("real-name-authentication-reject")
    public Result realNameAuthenticationReject(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            if (service.realNameAuthenticationReject(id) > 0) {
                return Result.success("操作成功！");
            } else {
                return Result.error("操作失败！");
            }
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    @PostMapping("become-management")
    public Result BecomeManagement() {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            return null;
        } else {
            return Result.error("管理员权限不足！");
        }
    }

    /************************************************************************/
    /**
     * 超级管理员
     */
    @PostMapping("is-root")
    public Result isRoot() {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getManagement()) {
            return Result.success("获取数据成功！", rootService.isRoot());
        } else {
            return Result.error("权限不足！");
        }
    }

    @PostMapping("get-apply-for-management")
    public Result getApplyForManagement(String name, int currentPage) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (cacheUserInfo.getIsRoot()) {
            if (name.contains(" ")) {
                return Result.error("用户名不能包含空格!");
            }
            return Result.success("获取管理员权限申请信息成功！", rootService.getApplyForManagement(name, currentPage));
        } else {
            return Result.error("权限不足！");
        }
    }

    @PostMapping("get-real-name-authentication-by-id")
    public Result getRealNameAuthenticationById(String id) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (cacheUserInfo.getIsRoot()) {
            return Result.success("获取实名认证信息成功！", rootService.getRealNameAuthenticationById(id));
        } else {
            return Result.error("权限不足！");
        }
    }

    @PostMapping("apply-for-management-pass")
    public Result ApplyForManagementPass(String id) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (cacheUserInfo.getIsRoot()) {
            if (rootService.ApplyForManagementPass(id) > 0) {
                return Result.success("操作成功！");
            } else {
                return Result.error("操作失败！");
            }
        } else {
            return Result.error("权限不足！");
        }
    }

    @PostMapping("apply-for-management-reject")
    public Result ApplyForManagemenReject(String id) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (cacheUserInfo.getIsRoot()) {
            if (rootService.ApplyForManagemenReject(id) > 0) {
                return Result.success("操作成功！");
            } else {
                return Result.error("操作失败！");
            }
        } else {
            return Result.error("权限不足！");
        }
    }
    /************************************************************************/
}
