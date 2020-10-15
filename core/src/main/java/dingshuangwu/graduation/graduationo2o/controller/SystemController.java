package dingshuangwu.graduation.graduationo2o.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/system")
public class SystemController {
    @RequestMapping("get-date")
    public String getSystemDate() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(new Date());
        return now;
    }
}
