package dingshuangwu.graduation.graduationo2o.aspect;

import dingshuangwu.graduation.graduationo2o.pojo.CacheUserInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.Result;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Component
@Aspect
public class loginAspect {

    @Pointcut("within(dingshuangwu.graduation.graduationo2o.controller..*) && !within(dingshuangwu.graduation.graduationo2o.controller.LoginApi) && !within(dingshuangwu.graduation.graduationo2o.controller.SignInApi)&& !execution(public * dingshuangwu.graduation.graduationo2o.controller.PublishApi.allPublish(..))")
    public void login() {
    }

    @Around("login()")
    public Object auth(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取session中的用户信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String name = request.getHeader("name");
        CacheUserInfoVO cacheUserInfo = DataUtils.getSomeUserCacheUserInfoBy(name);
        if (cacheUserInfo != null && cacheUserInfo.getToken().equals(token)) {
            DataUtils.refreshCacheUserInfoAliveDateByName(name);
            return joinPoint.proceed();
        } else {
            return new Result(-1, "登录信息错误或已过期");
        }
    }
}
