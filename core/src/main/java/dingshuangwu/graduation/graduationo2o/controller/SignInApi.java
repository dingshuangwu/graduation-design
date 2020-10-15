package dingshuangwu.graduation.graduationo2o.controller;

import dingshuangwu.graduation.graduationo2o.pojo.Result;
import dingshuangwu.graduation.graduationo2o.pojo.UserVO;
import dingshuangwu.graduation.graduationo2o.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@RestController
@RequestMapping("api/sign")
public class SignInApi {

    @Autowired
    SignInService service;

    @PostMapping("sign-in")
    public Result signIn(UserVO user) {
        if ("".equals(user.getName()) || "".equals(user.getPassword())) {
            return Result.error("用户名或密码不能为空");
        } else if (user.getName().contains(" ") || user.getPassword().contains(" ")) {
            return Result.error("用户名或密码不能有空格");
        } else if (user.getName() == null || user.getPassword() == null) {
            return Result.error("用户名或密码不能为空");
        } else if (user.getName().length() > 15 || user.getName().length() < 4) {
            return Result.error("用户名长度应在4~15之间");
        } else if (user.getPassword().length() > 20 || user.getPassword().length() < 6) {
            return Result.error("密码长度应在6~20之间");
        } else {
            if (service.signIn(user) > 0) {
                return Result.success("注册成功");
            } else {
                return Result.error("用户名已存在");
            }
        }
    }

    @PostMapping("update-password")
    public Result updatePassword(String name, String oldPassword, String newPassword) {
        if ("".equals(oldPassword) || oldPassword == null || oldPassword.contains(" ")) {
            return Result.error("原始密码格式不符合");
        } else if ("".equals(newPassword) || newPassword == null || newPassword.contains(" ")) {
            return Result.error("新密码格式不符合");
        } else if (oldPassword.equals(newPassword)) {
            return Result.error("新密码与旧密码相同！");
        } else {
            if (service.updatePassword(name, oldPassword, newPassword) > 0) {
                return Result.success("修改成功,请重新登录");
            } else {
                return Result.error("原始密码错误! 请重试!");
            }
        }
    }
}
