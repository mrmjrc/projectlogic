package cn.mrmj.advice;

import cn.mrmj.constant.enums.ErrorCodeEnum;
import cn.mrmj.exception.BusException;
import cn.mrmj.exception.ParaException;
import cn.mrmj.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;


/**
 * @Description controller异常处理切面
 *
 * RestControllerAdvice 作为特化@Component，允许通过类路径扫描自动检测实现类。
 * 它通常用于定义@ExceptionHandler， @InitBinder 和 @ModelAttribute 适用于
 * 所有@RequestMapping方法的方法。
 *
 * @Author mrmj
 * @Date 2019/11/11 15:10
 */
@RestControllerAdvice
public class ControllerExceptionHandlerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandlerAdvice.class);

    //Result 返回结果异常处理
    public Result<Object> handleException(HttpServletRequest request, Exception e)throws Exception {

        // 如果是feign调用，则抛出异常，tx-group 是SprindCloud的返回一个字段
        String txGroup = request.getHeader("tx-group");
        if (txGroup != null) {
            throw e;
        }

        //定义错误信息
        String error = "[%s] [%s]";
        String uri = request.getRequestURI();
        String errorMessage = null;
        ErrorCodeEnum erorCodeEnum = null;

        // instanceof 比较对象是否相等，是否属于同一个接口
        //RPC接口调用场景或者使用动态代理的场景中，偶尔会出现UndeclaredThrowableException
        if (e instanceof UndeclaredThrowableException) {
            Throwable targetEx = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
            if (targetEx != null) {
                targetEx.printStackTrace();
                errorMessage = targetEx.getMessage();
            }
        }

        // 自定义业务异常
        else if (e instanceof BusException) {
            BusException targetEx = (BusException) e;
            erorCodeEnum = targetEx.getErrorCodeEnum();
            errorMessage = targetEx.getMessage();
        }

        //参数缺省异常，返回错误信息
        else if (e instanceof ParaException) {
            ParaException targetEx = (ParaException) e;
            errorMessage = targetEx.getMessage();
        }

        else {
            e.printStackTrace();
            errorMessage = e.getMessage();
        }

        error = String.format(error, uri, errorMessage);

        //将错误代码添加进日志中
        logger.error(error,e);

        //异常没找到，返回系统繁忙
        if (errorMessage == null){
            errorMessage = "系统繁忙，请稍后再试";
        }
        //异常代码不为空返回异常代码（自定义），否则返回系统异常信息
        if (erorCodeEnum != null) {
            return Result.fail(erorCodeEnum);
        } else {
            return Result.fail(errorMessage);
        }
    }
}
