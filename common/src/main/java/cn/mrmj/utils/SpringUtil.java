package cn.mrmj.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * create by: mrmj
 * description: 普通类调用Spring bean对象：
 *  说明：
 *  此类需要放到App.java同包或者子包下才能被扫描，否则失效。
 * create time: 2019/11/21 18:45
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext  = applicationContext;
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("----------------cn.mrmj.utils.SpringUtil------------------------------------------------------");
        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="+SpringUtil.applicationContext+"========");
        System.out.println("---------------------------------------------------------------------");

    }

    /**
     * create by: mrmj
     * description: 获取applicationContext
     * create time: 2019/11/21 18:46
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * create by: mrmj
     * description: 通过name获取 Bean.
     * create time: 2019/11/21 18:46
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);

    }

    /**
     * create by: mrmj
     * description: 通过class获取Bean.
     * create time: 2019/11/21 18:46
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * create by: mrmj
     * description: 通过name,以及Clazz返回指定的Bean
     * create time: 2019/11/21 18:46
     */
    public static <T> T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}