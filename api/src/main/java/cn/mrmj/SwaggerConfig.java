package cn.mrmj;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.mrmj.api.app"))
                .paths(PathSelectors.any())
                .build()
                //定义全局的请求参数
                .globalOperationParameters(pars)
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
                .build()
                .globalOperationParameters(pars)
                .apiInfo(adminApiInfo());
    }

    private Predicate<String> paths() {
        return or(
                regex("/file.*"),
                regex("/upload/api.*"),
                regex("/banner.*"),
                regex("/lt.*"),
                regex("/order.*"),
                regex("/pay.*"),
                regex("/store.*"),
                regex("/person.*"),
                regex("/user.*"),
                regex("/sysInfo.*"),
                regex("/shop.*"),

                regex("/food.*"),

                regex("/website.*")
        );
    }

    private Predicate<String> admPaths() {
        return or(
                regex("/upload/api.*"),
                regex("/oauth.*"),
                regex("/admin/order/api.*"),
                regex("/admin/coupon/api.*"),
                regex("/admin/category/api.*"),
                regex("/admin/spec/api.*"),
                regex("/admin/shop/api.*"),
                regex("/admin/store/api.*"),
                regex("/admin/user.*"),
                regex("/admin/banner.*"),
                regex("/food.*"),
                regex("/admin.*"));
    }

    private ApiInfo appApiInfo() {
        String title = "移动端-系统接口";
        String description = "移动端-系统接口文档";
        String version = "1.0.0-snapshot";
        Contact DEFAULT_CONTACT = new Contact("", "", "");
        ApiInfo apiInfo = new ApiInfo(title, description, version,
                "", DEFAULT_CONTACT, "", "", new ArrayList());
        return apiInfo;
    }
    private ApiInfo adminApiInfo() {
        String title = "后台管理-系统接口";
        String description = "后台管理-系统接口文档";
        String version = "1.0.0-snapshot";
        Contact DEFAULT_CONTACT = new Contact("", "", "");
        ApiInfo apiInfo = new ApiInfo(title, description, version,
                "", DEFAULT_CONTACT, "", "", new ArrayList());
        return apiInfo;
    }
}
