package io.innofang.aop;



import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import java.util.Arrays;
import java.lang.reflect.Method;

/**
 * Created by Inno Fang on 2018/4/2.
 */
public class HijackAroundMethod implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Method name : "
                + methodInvocation.getMethod().getName());
        System.out.println("Method arguments : "
                + Arrays.toString(methodInvocation.getArguments()));

        // 相当于  MethodBeforeAdvice
        System.out.println("HijackAroundMethod : Before method hijacked!");

       try{
           // 调用原方法，即调用CustomerService中的方法
           Object result = methodInvocation.proceed();

           // 相当于 AfterReturningAdvice
           System.out.println("HijackAroundMethod : After method hijacked!");

           return result;
       }catch (IllegalArgumentException e) {
           // 相当于 ThrowsAdvice
           System.out.println("HijackAroundMethod : Throw exception hijacked!");
           throw e;
       }

    }
}
