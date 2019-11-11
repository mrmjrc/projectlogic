package cn.mrmj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by: mrmj
 * description: 参数不能为空
 * create time: 2019/11/11 17:26
 */

//     ElementType取值
//	   1.CONSTRUCTOR:用于描述构造器
//　　　　2.FIELD:用于描述域
//　　　　3.LOCAL_VARIABLE:用于描述局部变量
//　　　　4.METHOD:用于描述方法
//　　　　5.PACKAGE:用于描述包
//　　　　6.PARAMETER:用于描述参数
//　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {
	//不为空的参数
	String[] value() default "";

	//参数最大长度,对应value字段的顺序，不填默认不限制
	int[] maxLen() default {};
}
