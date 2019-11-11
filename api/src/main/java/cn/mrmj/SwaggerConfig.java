package cn.mrmj;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @Description swagger配置文件
 * @Author mrmj
 * @Date 2019/11/8 18:00
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder userPar = new ParameterBuilder();
        //存储生成的头参数
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("token").description("用户标识").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        userPar.name("userId").description("用户id").modelRef(new ModelRef("Long")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());
        pars.add(userPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("mrmj-app-api")
                //通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现
                .select()
                //扫描指定包
                .apis(RequestHandlerSelectors.basePackage("cn.mrmj.api.app"))
                //扫描的路径，扫描所有的路径里的接口，全部暴露出去
                .paths(PathSelectors.any())
                //根据指定的path规则扫描对应的路径里面接口，并暴露在swagger中
                //.paths(paths())
                .build()
                //定义全局的请求参数
                .globalOperationParameters(pars)
                //apiInfo() 增加API相关信息
                .apiInfo(appApiInfo());
    }

    @Bean
    public Docket adminDocument() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder userPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("token").description("用户标识").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        userPar.name("userId").description("用户id").modelRef(new ModelRef("Long")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());
        pars.add(userPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("mrmj-admin-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.mrmj.api.admin"))
                .paths(PathSelectors.any())
                //定义不同的扫描规则
                //.paths(admPaths())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(adminApiInfo());
    }

    private Predicate<String> paths() {
        return or(
                regex("/upload/api.*"),
                regex("/app.*.v1.*")
        );
    }

    private Predicate<String> admPaths() {
        return or(
                regex("/upload/api.*"),
                regex("/admin.*.v1.*")
        );
    }

    //创建该API的基本信息（这些基本信息会展现在文档页面中）
    private ApiInfo appApiInfo() {
        String title = "移动端-系统接口";
        String description = "移动端-系统接口文档";
        String version = "1.0.0-snapshot";
        Contact DEFAULT_CONTACT = new Contact("", "", "");
        ApiInfo apiInfo = new ApiInfo(title, description, version,
                "", DEFAULT_CONTACT, "", "", new ArrayList());
        return apiInfo;
    }
    //两种方式创建
    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("管理端-系统接口")
                .description("后台管理端-系统接口文档")
                .termsOfServiceUrl("服务地址链接")
                .contact("联系人名称")
                .version("1.0.0")
                .build();
    }
}
