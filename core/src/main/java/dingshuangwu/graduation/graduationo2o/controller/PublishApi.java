package dingshuangwu.graduation.graduationo2o.controller;

import dingshuangwu.graduation.graduationo2o.pojo.JurisdictionVO;
import dingshuangwu.graduation.graduationo2o.pojo.PublishVO;
import dingshuangwu.graduation.graduationo2o.pojo.RequestApplyAndPublishVO;
import dingshuangwu.graduation.graduationo2o.pojo.Result;
import dingshuangwu.graduation.graduationo2o.service.PublishService;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/publish")
public class PublishApi {
    @Autowired
    PublishService service;

    @GetMapping("all-publish")
    public Result allPublish(RequestApplyAndPublishVO condition) {
        return Result.success("获取所有兼职信息成功", service.getAllPublish(condition));
    }

    @GetMapping("my-publish")
    public Result myPublish(RequestApplyAndPublishVO condition) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getMyPublish() || userJurisdiction.getManagement()) {
            return Result.success("获取我发布的兼职信息成功", service.getMyPublish(condition));
        } else {
            return Result.error("权限不足！");
        }
    }

    @GetMapping("add-my-publish")
    public Result addMyPublish(PublishVO publish) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getMyPublish() || userJurisdiction.getManagement()) {
            if (service.addMyPublish(publish) > 0) {
                return Result.success("添加成功");
            } else {
                return Result.error("添加失败");
            }
        } else {
            return Result.error("权限不足！");
        }
    }

    @GetMapping("delete-my-publish")
    public Result deleteMyApply(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getMyPublish() || userJurisdiction.getManagement()) {
            if (service.deleteMyPublish(id) > 0) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } else {
            return Result.error("权限不足！");
        }
    }

    @GetMapping("update-my-publish")
    public Result updateMyPublish(PublishVO publish) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getMyPublish() || userJurisdiction.getManagement()) {
            if (service.updateMyPublish(publish) > 0) {
                return Result.success("编辑成功");
            } else {
                return Result.error("编辑失败");
            }
        } else {
            return Result.error("权限不足！");
        }
    }
}
