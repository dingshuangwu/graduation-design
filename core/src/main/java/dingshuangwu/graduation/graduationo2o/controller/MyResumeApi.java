package dingshuangwu.graduation.graduationo2o.controller;

import dingshuangwu.graduation.graduationo2o.pojo.CacheUserInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeExpectedWorkVO;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeExpertiseVO;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.MyResumeWorkExperienceVO;
import dingshuangwu.graduation.graduationo2o.pojo.Result;
import dingshuangwu.graduation.graduationo2o.service.MyResumeService;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshuangwu
 * @date 2020.02.06
 */
@RestController
@RequestMapping("api/my-resume")
public class MyResumeApi {

    @Autowired
    MyResumeService service;

    @GetMapping("all")
    public Result getMyResumeAll() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得我的简历-所有信息成功", service.getMyResumeAll(cacheUserInfo.getId()));
    }

    @GetMapping("info")
    public Result getMyResumeInfo() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得我的简历-个人信息成功", service.getMyResumeInfo(cacheUserInfo.getId()));
    }

    //信息参数由前端传入，lastModifyTime由系统给出
    @GetMapping("set-info")
    public Result setMyResumeInfo(MyResumeInfoVO myResumeInfo) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (service.setMyResumeInfo(cacheUserInfo.getId(), myResumeInfo) > 0) {
            return Result.success("修改我的简历-个人信息成功");
        } else {
            return Result.error("修改我的简历-个人信息失败");
        }
    }

    @GetMapping("expertise")
    public Result getMyResumeExpertise() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得我的简历-技能专长信息成功", service.getMyResumeExpertise(cacheUserInfo.getId()));
    }

    @GetMapping("set-expertise")
    public Result setMyResumeExpertise(MyResumeExpertiseVO myResumeExpertise) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (service.setMyResumeExpertise(cacheUserInfo.getId(), myResumeExpertise) > 0) {
            return Result.success("修改我的简历-技能专长信息成功");
        } else {
            return Result.error("修改我的简历-技能专长信息失败");
        }
    }

    @GetMapping("expected-work")
    public Result getMyResumeExpectedWork() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得我的简历-期望职位信息成功", service.getMyResumeExpectedWork(cacheUserInfo.getId()));
    }

    @GetMapping("set-expected-work")
    public Result setMyResumeExpectedWork(MyResumeExpectedWorkVO myResumeExpectedWork) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (service.setMyResumeExpectedWork(cacheUserInfo.getId(), myResumeExpectedWork) > 0) {
            return Result.success("修改我的简历-期望职位信息成功");
        } else {
            return Result.error("修改我的简历-期望职位信息失败");
        }
    }

    @GetMapping("work-experience")
    public Result getMyResumeWorkExperience() {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        return Result.success("获得我的简历-工作经验信息成功", service.getMyResumeWorkExperience(cacheUserInfo.getId()));
    }

    @GetMapping("set-work-experience")
    public Result setMyResumeWorkExperience(MyResumeWorkExperienceVO myResumeWorkExperience) {
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (service.setMyResumeWorkExperience(cacheUserInfo.getId(), myResumeWorkExperience) > 0) {
            return Result.success("修改我的简历-工作经验信息成功");
        } else {
            return Result.error("修改我的简历-工作经验信息失败");
        }
    }

    @GetMapping("get-somebody-resume")
    public Result getSomeBodyResume(String name) {
        return Result.success("获取" + name + "的简历成功", service.getSomeBodyResumeByUserName(name));
    }
}
