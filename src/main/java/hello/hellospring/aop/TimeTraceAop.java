package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// @Component를 통해 컴포넌트 스캔을 해도 되지만 AOP는 @Bean에 등록하는 것을 더 선호
@Aspect
@Component
public class TimeTraceAop {

    // @Around를 통해 타겟팅
    // hello.hellospring..*(..)는 hellospring 하위에 다 적용
    // hello.hellospring.service..*(..)는 hellospring.service 하위에 다 적용
    @Around("execution(* hello.hellospring..*(..))")
    public  Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            // joinPoint.proceed()를 통해 실제 memberService 호출
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
