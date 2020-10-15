package dingshuangwu.graduation.graduationo2o.config;

import dingshuangwu.graduation.graduationo2o.pojo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        Result result = new Result();
        result.setCode(500);
        result.setMessage("服务器内部出错");
        result.setData(e.getMessage());
        return result;
    }

}
