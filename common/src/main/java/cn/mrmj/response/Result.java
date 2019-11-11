package cn.mrmj.response;

import cn.mrmj.constant.enums.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description API结果返回类 Result
 * @Author mrmj
 * @Date 2019/11/11 15:19
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    //状态码
    private int code;
    //错误提示
    private String msg;
    //返回数据
    private T data;
    //消息总数
    private Integer total;
    //额外的数据
    private Object attach;

    //返回无参数成功信息，默认添加成功的信息
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCodeEnum.OK.getCode());
        result.setMsg(ErrorCodeEnum.OK.getMessage());
        return result;
    }

    //返回有参数成功数据信息，默认添加成功的代码信息
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCodeEnum.OK.getCode());
        result.setData(data);
        return result;
    }

    //返回有参数成功信息，数据+数据总数，默认添加成功的信息
    public static <T> Result<T> success(T data, int total) {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCodeEnum.OK.getCode());
        result.setData(data);
        result.setTotal(total);
        return result;
    }

    //recordsTotal 编码后的总数？，未知
    public static <T> Result<T> success(T data, Integer total,Long recordsTotal,Integer t) {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCodeEnum.OK.getCode());
        result.setData(data);
        result.setTotal(total);
        return result;
    }

    //返回有参数成功信息，数据+数据总数+额外的信息，默认添加成功的信息
    public static <T> Result<T> success(T data, Integer total,Object attach) {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCodeEnum.OK.getCode());
        result.setData(data);
        result.setTotal(total);
        result.setAttach(attach);
        return result;
    }

    //返回有参数成功信息，数据+数据总数+额外的信息+recordsTotal，默认添加成功的信息
    public static <T> Result<T> success(T data, Integer total,Object attach,Long recordsTotal) {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCodeEnum.OK.getCode());
        result.setData(data);
        result.setTotal(total);
        result.setAttach(attach);
        return result;
    }

    //返回无参的失败信息，自动添加错误代码和信息
    public static <T> Result<T> fail() {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCodeEnum.UNDEFINE_ERROR.getCode());
        result.setMsg(ErrorCodeEnum.UNDEFINE_ERROR.getMessage());
        return result;
    }

    //返回带参的失败信息，错误信息，自动添加错误代码，
    public static <T> Result<T> fail(String errorMessag) {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCodeEnum.UNDEFINE_ERROR.getCode());
        result.setMsg(errorMessag);
        return result;
    }

    //返回带参的失败信息，错误信息，错误代码，
    public static <T> Result<T> fail(Integer errorCode,String errorMessag) {
        Result<T> result = new Result<T>();
        result.setCode(errorCode);
        result.setMsg(errorMessag);
        return result;
    }


    //返回带参的失败信息，返回枚举，自定义已经定义的枚举类错误信息
    public static <T> Result<T> fail(ErrorCodeEnum errorCodeEnum) {
        Result<T> result = new Result<T>();
        result.setCode(errorCodeEnum.getCode());
        result.setMsg(errorCodeEnum.getMessage());
        return result;
    }

    //判断是够成功,根据传入的code参数和枚举类中的比较
    public boolean isSuccess(int code) {
        return ErrorCodeEnum.OK.getCode() == code;
    }




}
