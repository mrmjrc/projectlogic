package cn.mrmj;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;
import java.util.Properties;

/**
 * ComponentScan做的事情就是告诉Spring从哪里找到bean
 * @Description application 整个项目启动类
 * @Author mrmj
 * @Date 2019/11/8 17:15
 */
@Configuration
@EnableSwagger2
//@SpringBootApplication
@ComponentScan("cn.mrmj")
@MapperScan("cn.mrmj.mapper")
public class ApiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //此处的Application.class为带有@SpringBootApplication注解的启动类
        return builder.sources(ApiApplication.class);
    }

    //启动类的配置
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class);
    }

    /**
     * 配置mybatis的分页插件pageHelper
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //将RowBounds第一个参数offset当成pageNum页码使用
        properties.setProperty("offsetAsPageNum", "true");
        //使用RowBounds分页会进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //分页合理化参数
        properties.setProperty("reasonable", "true");
        //支持通过 Mapper 接口参数来传递分页参数
        properties.setProperty("supportMethodsArguments", "true");
        //自动分页的配置，依据的是入参，如果参数中有pageNum，pageSize分页参数，则会自动分页
        properties.setProperty("params", "count=countSql");
        //配置mysql数据库的方言
        properties.setProperty("dialect", "mysql");
        //总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
        properties.setProperty("returnPageInfo", "check");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    /**
     * SpringBoot文件上传配置
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大//KB,MB
        factory.setMaxFileSize(DataSize.parse(1024 + "MB"));
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse(1024*5 + "MB"));
        //创建文件上传配置
        return factory.createMultipartConfig();
    }


    /**
     * 加载YML格式自定义配置文件
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//      yaml.setResources(new FileSystemResource("sdk.yml"));//File引入
        yaml.setResources(new ClassPathResource("sdk.yml"));//class引入
        configurer.setProperties(yaml.getObject());
        return configurer;
    }



    /**
     * 定义静态资源映射目录
     * WebMvcConfigure接口包含了WebMvcConfigurerAdapter类中所有方法的默认实现，
     * 因此WebMvcConfigurerAdapter这个适配器就被打入冷宫了
     */
    @Bean
    public WebMvcConfigurer addResourceHandlers() {
        return new WebMvcConfigurer() {
            @Override
            //springboot中配置addResourceHandler和addResourceLocations，
            // 使得可以从磁盘中读取图片、视频、音频等,读取swagger页面
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("swagger-ui.html")
                        .addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");
            }
        };
    }


}
