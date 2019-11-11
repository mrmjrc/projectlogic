package cn.mrmj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by: mrmj
 * description: 过滤 Result中成员变量data中的成员变量 二者选一，同时存在只处理include
 * Target 注解类型
 * Retention 元注解 RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 * create time: 2019/11/11 17:11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Data {

	//返回只包含的数据
	String[] include() default {};

	//返回不包含的数据
	String[] exclude() default {};
}
