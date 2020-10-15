package dingshuangwu.graduation.graduationo2o.controller;

import dingshuangwu.graduation.graduationo2o.mapper.ApplyMapper;
import dingshuangwu.graduation.graduationo2o.mapper.PublishMapper;
import dingshuangwu.graduation.graduationo2o.mapper.SignInMapper;
import dingshuangwu.graduation.graduationo2o.pojo.RequestApplyAndPublishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestApi {
    @Autowired
    ApplyMapper applyMapper;
    @Autowired
    PublishMapper publishMapper;
    @Autowired
    SignInMapper signInMapper;

    @GetMapping("test")
    public String test(RequestApplyAndPublishVO condition) {
        return "dingshuangwu";
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("apply")
    public void test1() {
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("publish")
    public void test2() {
    }
}