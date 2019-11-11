package cn.mrmj.exception;


import cn.mrmj.constant.enums.ErrorCodeEnum;

/**
 * create by: mrmj
 * description: 业务异常处理,返回的异常全为错误枚举中定义的异常
 * create time: 2019/11/11 16:49
 */
public class BusException extends Exception {

	private static final long serialVersionUID = 5827674159912277791L;

	private ErrorCodeEnum errorCodeEnum;

	public BusException() {

	}

	public BusException(String message) {
		super(message);
	}

	public BusException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum.getMessage());
		this.setErrorCodeEnum(errorCodeEnum);
	}

	public ErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}

	public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
		this.errorCodeEnum = errorCodeEnum;
	}

}
