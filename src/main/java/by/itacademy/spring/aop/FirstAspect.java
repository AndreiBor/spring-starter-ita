package by.itacademy.spring.aop;

import by.itacademy.spring.validator.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Aspect
@Component
public class FirstAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }


    @Pointcut("within(by.itacademy.spring.service.*Service)")
    public void isServiceLayer() {
    }

//    @Pointcut("this(org.springframework.stereotype.Repository)")
    @Pointcut("target(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer(){
    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping(){}

    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelArg(){}

    @Pointcut("isControllerLayer() && @args(by.itacademy.spring.validator.UserInfo,..)")
    public void hasUserInfoAnnotation(){}

    @Pointcut("bean(*Service)")
    public void isServiceBean(){}

    @Pointcut("execution(public * by.itacademy.spring.service.*Service.findById(*))")
    public void anyServiceFindByIdMethod(){}

    @Pointcut("execution(public * findById(*))")
    public void anyFindBuIdMethod(){}

    @Before("anyServiceFindByIdMethod() " +
            "&& args(id) " +
            "&& target(service) " +
            "&& this(serviceProxy) " +
            "&& @within(transactional)")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional) {
        log.info("Before invoke findById method in class {}, with id {}", service, id);
    }


}
