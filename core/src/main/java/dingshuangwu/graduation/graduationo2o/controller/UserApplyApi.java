package dingshuangwu.graduation.graduationo2o.controller;

import dingshuangwu.graduation.graduationo2o.pojo.ApplyForManagementVO;
import dingshuangwu.graduation.graduationo2o.pojo.JurisdictionVO;
import dingshuangwu.graduation.graduationo2o.pojo.RealNameAuthenticationVO;
import dingshuangwu.graduation.graduationo2o.pojo.Result;
import dingshuangwu.graduation.graduationo2o.service.UserApplyService;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user-apply")
public class UserApplyApi {
    @Autowired
    UserApplyService service;

    @PostMapping("real-name-authentication")
    public Result realNameAuthentication(RealNameAuthenticationVO realNameAuthentication) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getRealNameAuthentication()) {
            if (service.realNameAuthentication(realNameAuthentication) > 0) {
                return Result.success("实名认证材料提交成功！");
            } else {
                return Result.error("实名认证材料提交失败！");
            }
        } else {
            return Result.error("实名认证材料已提交，请勿重复提交！");
        }
    }

    @PostMapping("apply-for-management")
    public Result applyForManagement(ApplyForManagementVO applyForManagement) {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getApplyForManagement()) {
            return Result.success("申请管理员权限材料提交成功！", service.applyForManagement(applyForManagement));
        } else {
            return Result.error("未进行实名认证，请先提交实名认证材料");
        }
    }

    @GetMapping("get-real-name-authentication")
    public Result getRealNameAuthentication() {
        JurisdictionVO userJurisdiction = DataUtils.getUserJurisdiction();
        if (userJurisdiction.getApplyForManagement()) {
            return Result.success("实名认证信息获取成功！", service.getRealNameAuthentication());
        } else {
            return Result.success("未进行实名认证，请先提交实名认证材料", false);
        }
    }
}
