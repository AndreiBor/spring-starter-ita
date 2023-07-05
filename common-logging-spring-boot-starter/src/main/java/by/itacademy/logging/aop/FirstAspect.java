package by.itacademy.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Aspect
public class FirstAspect {


    @Pointcut("execution(public * by.itacademy.*.service.*Service.findById(*))")
    public void anyServiceFindByIdMethod() {
    }

    @Pointcut("execution(public * findById(*))")
    public void anyFindBuIdMethod() {
    }

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

    @AfterReturning(value = "anyServiceFindByIdMethod() " +
            "&& target(service) ",
            returning = "result")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("AfterReturning invoke findById method in class {}, with result {}", service, result);
    }

    @AfterThrowing(value = "anyServiceFindByIdMethod() " +
            "&& target(service) ",
            throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("AfterThrowing invoke findById method in class {}, with throwing {}", service, ex);
    }

    @After("anyServiceFindByIdMethod() " +
            "&& target(service) ")
    public void addLoggingAfterThrowing(Object service) {
        log.info("After invoke findById method in class {}", service);
    }


}
