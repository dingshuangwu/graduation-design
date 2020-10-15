package dingshuangwu.graduation.graduationo2o.controller;

import dingshuangwu.graduation.graduationo2o.pojo.ApplyVO;
import dingshuangwu.graduation.graduationo2o.pojo.JurisdictionVO;
import dingshuangwu.graduation.graduationo2o.pojo.RequestApplyAndPublishVO;
import dingshuangwu.graduation.graduationo2o.pojo.Result;
import dingshuangwu.graduation.graduationo2o.service.ApplyService;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/apply")
public class ApplyApi {
    @Autowired
    ApplyService service;

    @GetMapping("all-apply")
    public Result allApply(RequestApplyAndPublishVO condition) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getApply() || userJurisdiction.getManagement()) {
            return Result.success("获取所有申请信息成功", service.getAllApply(condition));
        } else {
            return Result.error("权限不足！");
        }
    }

    @GetMapping("my-apply")
    public Result myApply(RequestApplyAndPublishVO condition) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getMyApply() || userJurisdiction.getManagement()) {
            return Result.success("获取我的申请信息成功", service.getMyApply(condition));
        } else {
            return Result.error("权限不足！");
        }
    }


    @GetMapping("add-my-apply")
    public Result addMyApply(ApplyVO apply) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getMyApply() || userJurisdiction.getManagement()) {
            if (service.addMyApply(apply) > 0) {
                return Result.success("添加成功");
            } else {
                return Result.error("添加失败");
            }
        } else {
            return Result.error("权限不足！");
        }
    }

    @GetMapping("delete-my-apply")
    public Result deleteMyApply(String id) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getMyApply() || userJurisdiction.getManagement()) {
            if (service.deleteMyApply(id) > 0) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } else {
            return Result.error("权限不足！");
        }
    }

    @GetMapping("update-my-apply")
    public Result updateMyApply(ApplyVO apply) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getMyApply() || userJurisdiction.getManagement()) {
            if (service.updateMyApply(apply) > 0) {
                return Result.success("编辑成功");
            } else {
                return Result.error("编辑失败");
            }
        } else {
            return Result.error("权限不足！");
        }

    }
}
