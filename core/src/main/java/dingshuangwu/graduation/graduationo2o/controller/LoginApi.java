package dingshuangwu.graduation.graduationo2o.controller;

import dingshuangwu.graduation.graduationo2o.pojo.Result;
import dingshuangwu.graduation.graduationo2o.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@RestController
@RequestMapping("api/login")
public class LoginApi {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public Result login(String name, String password) {
        if (name == null || password == null) {
            return Result.error("账号或密码不能为空");
        } else if (name.contains(" ") || password.contains(" ")) {
            return Result.error("账号或密码不能有空格");
        } else {
            return service.login(name, password);
        }
    }

    @PostMapping("/loginOut")
    public Result loginOut() {
        return service.loginOut();
    }

}
