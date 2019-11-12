package cn.mrmj.aspect;

import cn.mrmj.utils.DateUtils;
import cn.mrmj.utils.RequestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * create by: mrmj
 * description: 日志过滤器
 * create time: 2019/11/12 13:57
 */
@Slf4j
@Aspect
@Component
public class LogFilter {

    //ObjectMapper是 model 对象（类和结构体）和 JSON 之间转换的框架
    private static final ObjectMapper mapper = new ObjectMapper();

    private long startTimeMillis;

    private long endTimeMillis;

    @Pointcut("execution(* cn.mrmj.api..*.*(..))")
    public void controller() {
    }

    //requestMapping 注解的处理
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Before("controller()")
    public void doBefore(JoinPoint joinPoint) {
        //获取当前时间为开始时间
        startTimeMillis = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        log.info("===================== request start =====================", new Object[0]);
        log.info("URL:[ {} ]", new Object[] { request.getRequestURL() });
        log.info("IP:[ {} ]", new Object[] {RequestUtils.getIpAddr(request)});
        log.info("Headers=[ {} ]", new Object[] { request.getHeader("token"),request.getHeader("userId") });
        printMap("Request Params", request.getParameterMap());
        log.info("===================== request end =====================\n", new Object[0]);
    }

    @After("controller()")
    public void doAfter(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis();
    }


    @AfterReturning(pointcut = "controller()", returning = "returnValue")
    public JoinPoint afterReturning(JoinPoint joinPoint, Object returnValue) {
        log.info("===================== return start =====================", new Object[0]);

        //随时都能取到当前请求的request对象，可以通过RequestContextHolder的静态方法getRequestAttributes()获取Request相关的变量，如request, response等。
        org.springframework.web.context.request.RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        log.info("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                new SimpleDateFormat("hh:mm:ss.SSS").format(endTimeMillis), DateUtils.formatDateTime(endTimeMillis - startTimeMillis),
                request.getRequestURI(), Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
                (Runtime.getRuntime().maxMemory()- Runtime.getRuntime().totalMemory()+ Runtime.getRuntime().freeMemory())/1024/1024);

        log.info("===================== return end =====================\n", new Object[0]);
        return joinPoint;
    }

    @AfterThrowing(pointcut = "controller()", throwing = "e")
    public void afterThrowing(Throwable e) {
        log.error("ERROR:", new Object[] { e });
    }

    //将日志中封住的信息读取到并处理
    private void printMap(String prefix, Map map) {
        StringBuilder buf = new StringBuilder();
        //遍历获取map中的键和值
        for (Object o : map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            buf.append("  ");
            buf.append((String) entry.getKey());
            buf.append(" = ");
            try {
                //使用jackon的ObjectMapper的writeValueAsString方法可以把java对象转化成json字符串
                buf.append(mapper.writeValueAsString(entry.getValue()));
                buf.append("\n");
            }
            catch (IOException e) {
                log.error("print map error", new Object[] { e.getMessage() });
            }
        }

        if (buf.length() > 0) {
            log.info("{}:\n{}", new Object[] { prefix, buf.substring(0, buf.length() - 1) });
        }
    }
}
