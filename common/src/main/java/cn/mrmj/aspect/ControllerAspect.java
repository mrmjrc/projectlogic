package cn.mrmj.aspect;


import cn.mrmj.annotation.Data;
import cn.mrmj.annotation.NotEmpty;
import cn.mrmj.annotation.NotNull;
import cn.mrmj.exception.ParaException;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * create by: mrmj
 * description: controller方法切面
 * create time: 2019/11/11 17:42
 */

@Aspect
//因为不清楚这个类是属于哪个层面，所以就用@Component。
@Component
public class ControllerAspect {

	private static final String NULL_MESSAGE = "参数 '%s' 不能为null";
	private static final String EMPTY_MESSAGE = "参数 '%s' 不能为空";
	private static final String LENGHT_MESSAGE = "Required parameter '%s' length limit %s,but now is %s";

	//切入点
	@Pointcut("execution(* cn.mrmj.api..*.*(..))")
	public void controller() {
	}

	//controller方法之前执行，JoinPoint对象封装了SpringAop中切面方法的信息
	@Before("controller()")
	public void before(JoinPoint joinPoint) throws Exception {
		checkPara(joinPoint);
//		getParam((ProceedingJoinPoint) joinPoint);
	}

	@After("controller()")
	public void after(JoinPoint joinPoint) {

	}

	//注解切面@AfterReturning取返回值
	@AfterReturning(value = "controller()", returning = "obj")
	public void afterReturning(JoinPoint joinPoint, Object obj) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		//handleReturnValue(joinPoint, obj);
	}

	@AfterThrowing("controller()")
	public void afterThrowing(JoinPoint joinPoint) {

	}

	/**
	 * create by: mrmj
	 * description: 处理返回值
	 * create time: 2019/11/11 18:20
	 */
	private void handleReturnValue(JoinPoint joinPoint, Object obj) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		if (obj == null) {
			return;
		}
		// 只处理Result
		Class<?> clz = Class.forName("cn.mrmj.response.Result");
		if (clz != obj.getClass()) {
			return;
		}
		//获取结果中的方法
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();

		String[] include = null;
		String[] exclude = null;

		//方法如果存在这样的注释，则返回指定类型的元素的注释，否则为null
		Data dataAnnotation = method.getAnnotation(Data.class);

		//默认不包含的字段
		String[] defaultExclude = new String[] { "num", "row", "start", "sort" };

		//如果注解为空，那么为默认的不包括的
		if (dataAnnotation == null) {
			exclude = defaultExclude;
		} else {
			include = dataAnnotation.include();
			exclude = dataAnnotation.exclude();
			ArrayUtils.addAll(exclude, defaultExclude);
		}

		Field dataField = clz.getDeclaredField("data");
		dataField.setAccessible(true);
		// 获取data对象
		Object data = dataField.get(obj);
		if (data == null) {
			return;
		}

		// data是Collection 类型
		if (data instanceof Collection<?>) {
			Object[] array = ((Collection<?>) data).toArray();
			if (array == null || array.length == 0) {
				return;
			}
			for (Object e : array) {
				handleData(e, include, exclude);
			}

		}
		//
		else {
			handleData(data, include, exclude);
		}

	}

	/**
	 * create by: mrmj
	 * description: 根据Data自定义注解的 include,exclude 处理目标对象
	 * create time: 2019/11/12 10:54
	 */
	private void handleData(Object targetObject, String[] include, String[] exclude)
			throws IllegalArgumentException, IllegalAccessException {
		// 反射获取data 所有成员属性
		Set<Field> fieldSet = getDeclaredFields(targetObject.getClass());
		//include
		if (include != null && include.length != 0) {
			List<String> list = Arrays.asList(include);
			for (Field e : fieldSet) {
				//判断名字是否一样，类型是否为私有
				if (!list.contains(e.getName()) && !e.getType().isPrimitive()) {
					e.setAccessible(true);
					e.set(targetObject, null);
				}
			}
		}
		//exclude
		else if (exclude != null && exclude.length != 0) {
			List<String> list = Arrays.asList(exclude);
			for (Field e : fieldSet) {
				if (list.contains(e.getName()) && !e.getType().isPrimitive()) {
					e.setAccessible(true);
					e.set(targetObject, null);
				}
			}
		}
	}

	/**
	 * create by: mrmj
	 * description: 获取参数
	 * create time: 2019/11/12 10:51
	 */
	private void getParam(ProceedingJoinPoint joinPoint) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Object[] args = joinPoint.getArgs();
		try {
			joinPoint.proceed(args);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	/**
	 * create by: mrmj
	 * description: 检查注解中的参数
	 * create time: 2019/11/11 17:50
	 */
	private void checkPara(JoinPoint joinPoint) throws Exception {
		//获取到request中的参数
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		//获取这个方法的相关信息
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();

		String[] notEmptyValue = null;
		int[] notEmptyMaxLen = null;
		String[] notNullValue = null;

		//isAnnotationPresent用于判断NotEmpty类型的注解是否在传过来的method方法上，
		//在的话获取到添加在注解中的对应的数据
		if (method.isAnnotationPresent(NotEmpty.class)) {
			NotEmpty notEmpty = method.getAnnotation(NotEmpty.class);
			notEmptyValue = notEmpty.value();
			notEmptyMaxLen = notEmpty.maxLen();
		}

		//NotNull类型的注解是否在传过来的method方法上，
		if (method.isAnnotationPresent(NotNull.class)) {
			NotNull notNull = method.getAnnotation(NotNull.class);
			notNullValue = notNull.value();
		}

		//获取被requestBody注释的参数对象
		Class<?> beRequestBodyClazz = getClassByAnnotaion(method, RequestBody.class);
		Object beRequestBodyObject = null;
		if (beRequestBodyClazz != null) {
			//获取里面的对象
			Object[] args = joinPoint.getArgs();
			for (Object arg : args) {
				if (arg != null && arg.getClass().equals(beRequestBodyClazz)) {
					beRequestBodyObject = arg;
					break;
				}
			}
		}

		// NotNull校验
		if (notNullValue != null) {
			for (int i = 0; i < notNullValue.length; i++) {
				//获取里面的值
				String paraName = notNullValue[i];
				Object paraValue = null;
				// 获取值
				if (beRequestBodyObject != null) {
					paraValue = getValueByField(beRequestBodyObject, paraName);
				} else {
					paraValue = request.getParameter(paraName);
				}
				if (paraValue == null) {
					throw new ParaException(String.format(NULL_MESSAGE, paraName));
				}
			}
		}

		// NotEmpty校验
		if (notEmptyValue != null) {
			for (int i = 0; i < notEmptyValue.length; i++) {
				String paraName = notEmptyValue[i];
				Object paraValue = null;
				// 获取值
				if (beRequestBodyObject != null) {
					paraValue = getValueByField(beRequestBodyObject, paraName);
				} else {
					paraValue = request.getParameter(paraName);
				}

				int maxLen = Integer.MAX_VALUE;
				if (notEmptyMaxLen != null && i < notEmptyMaxLen.length) {
					maxLen = notEmptyMaxLen[i];
				}
				checkNotEmpty(paraName, paraValue, maxLen);
			}
		}

	}

	//执行前置通知2
	 @Before(value = "execution(* cn.mrmj.*.api..*.*(..))")
	public void before2(JoinPoint joinPoint) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();

		String[] notEmptyValue = null;
		int[] notEmptyMaxLen = null;
		String[] notNullValue = null;

		//是否为 NotEmpty 注解
		if (method.isAnnotationPresent(NotEmpty.class)) {
			NotEmpty notEmpty = method.getAnnotation(NotEmpty.class);
			notEmptyValue = notEmpty.value();
			notEmptyMaxLen = notEmpty.maxLen();
		}

		if (method.isAnnotationPresent(NotNull.class)) {
			NotNull notNull = method.getAnnotation(NotNull.class);
			notNullValue = notNull.value();
		}

		// 被requestBody注释的参数对象
		Class<?> beRequestBodyClazz = getClassByAnnotaion(method, RequestBody.class);
		Object beRequestBodyObject = null;
		if (beRequestBodyClazz != null) {
			Object[] args = joinPoint.getArgs();
			for (Object arg : args) {
				if (arg != null && arg.getClass().equals(beRequestBodyClazz)) {
					beRequestBodyObject = arg;
					break;
				}
			}
		}

		// NotNull校验
		if (notNullValue != null) {
			for (int i = 0; i < notNullValue.length; i++) {
				String paraName = notNullValue[i];
				Object paraValue = null;
				// 获取值
				if (beRequestBodyObject != null) {
					paraValue = getValueByField(beRequestBodyObject, paraName);
				} else {
					paraValue = request.getParameter(paraName);
				}
				if (paraValue == null) {
					throw new ParaException(String.format(NULL_MESSAGE, paraName));
				}
			}
		}

		// NotEmpty校验
		if (notEmptyValue != null) {
			for (int i = 0; i < notEmptyValue.length; i++) {
				String paraName = notEmptyValue[i];
				Object paraValue = null;
				// 获取值
				if (beRequestBodyObject != null) {
					paraValue = getValueByField(beRequestBodyObject, paraName);
				} else {
					paraValue = request.getParameter(paraName);
				}

				int maxLen = Integer.MAX_VALUE;
				if (notEmptyMaxLen != null && i < notEmptyMaxLen.length) {
					maxLen = notEmptyMaxLen[i];
				}

				checkNotEmpty(paraName, paraValue, maxLen);

			}
		}

	}

	/**
	 * create by: mrmj
	 * description: 判断是否非空及长度限制
	 * create time: 2019/11/11 18:26
	 */
	private void checkNotEmpty(String paraName, Object paraValue, int maxLen) throws Exception {
		if (paraValue == null) {
			throw new ParaException(String.format(EMPTY_MESSAGE, paraName));
		}
		int paraLen = 0;

		//判断 paraValue 是否为一个集合
		if (paraValue instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) paraValue;
			paraLen = collection.size();
		}

		//判断是否是一个map
		else if (paraValue instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) paraValue;
			paraLen = map.size();
		}

		//判断是否为数组
		else if (paraValue.getClass().isArray()) {
			Object[] array = (Object[]) paraValue;
			paraLen = array.length;
		}

		//字符串
		else {
			paraLen = String.valueOf(paraValue).length();
		}

		//说明什么都没有，为空
		if (paraLen == 0) {
			throw new ParaException(String.format(EMPTY_MESSAGE, paraName));
		}

		if (paraLen > maxLen) {
			throw new ParaException(String.format(LENGHT_MESSAGE, paraName, maxLen, paraLen));
		}

	}

	/**
	 * create by: mrmj
	 * description: 获取对象某个属性的值
	 * create time: 2019/11/12 10:35
	 */
	private Object getValueByField(Object object, String field)
			throws IllegalArgumentException, IllegalAccessException {
		//反射获取 clazz 对象
		//getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
		//getDeclaredFields()：获得某个类的所有声明的字段，即包括public、
		// private和proteced，但是不包括父类的声明字段。
		Class<?> clazz = object.getClass();
		Set<Field> fieldSet = getDeclaredFields(clazz);
		for (Field f : fieldSet) {
			//使用了method.setAccessible(true)后 性能有了20倍的提升
			//Accessable属性是继承自AccessibleObject 类. 功能是启用或禁用安全检查
			f.setAccessible(true);
			if (f.getName().equals(field)) {
				return f.get(object);
			}
		}
		return null;
	}

	/**
	 * create by: mrmj
	 * description: 通过重写反射方法，获取class所有成员属性
	 * getClass 返回正在运行的类
	 * getSuperclass 返回正在运行的类的父类
	 * create time: 2019/11/12 10:47
	 */
	private Set<Field> getDeclaredFields(Class<?> clazz) {
		Set<Field> fieldSet = new HashSet<Field>();
		while (clazz != null) {
			fieldSet.addAll(new ArrayList<Field>(Arrays.asList(clazz.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		return fieldSet;
	}

	/**
	 * create by: mrmj
	 * description: 通过反射获取被RequestBody注解的方法参数的class,类继承自接口
	 * create time: 2019/11/11 18:13
	 */
	private Class<?> getClassByAnnotaion(Method method, Class<? extends Annotation> clazz) {
		Parameter[] params = method.getParameters();
		for (Parameter e : params) {
			if (e.isAnnotationPresent(clazz)) {
				return e.getType();
			}
		}
		return null;
	}

}
