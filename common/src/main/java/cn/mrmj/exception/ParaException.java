package cn.mrmj.exception;

/**
 * create by: mrmj
 * description: 参数缺省异常
 * create time: 2019/11/11 16:52
 */
public class ParaException extends Exception {

	private static final long serialVersionUID = 1L;

	public ParaException() {

	}

	public ParaException(String message) {
		//父类异常信息
		super(message);
	}

}
