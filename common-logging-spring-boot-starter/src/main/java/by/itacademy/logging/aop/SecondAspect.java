package by.itacademy.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
public class SecondAspect {
    @Pointcut("execution(public * by.itacademy.*.service.*Service.findById(*))")
    public void anyServiceFindByIdMethod() {
    }

    @Around("anyServiceFindByIdMethod() " +
            "&& target(service) " +
            "&& args(id)")
    public void addLoggingAfterThrowing(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("Around before - invoke findById method in class {}, with id {}", service, id);
        try {
            Object result = joinPoint.proceed();
            log.info("Around after returning - invoke findById method in class {}, with result {}", service, result);
        } catch (Throwable e) {
            log.info("Around after throwing - invoke findById method in class {}, exception {} : {}",
                    service, e.getClass(), e.getMessage());
            throw e;
        } finally {
            log.info("Around after - invoke findById method in class {}", service);
        }
    }
}
