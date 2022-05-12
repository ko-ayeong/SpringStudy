package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    // 공통 관심 사항(cross-cutting concern)

    // 프록시 가짜스프링빈을 세워둠.
    // 이게 끝나면 joinPoint.proceed로 실제 서비스를 실행함

    // 이거를 어디에 타게팅 할거야? > Around
    // hellospring..*(..)) > 다 적용시킨거임. 설정할 수 있음 / 보통 패키지 레벨로 많이 한다
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            // 다음 메소드로 진행
            // 이거 지금 Object result = joinPoint.proceed();
            // return result 인데 ctrl alt n 으로 인라인 해준거임
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
