package cn.mrmj.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * create by: mrmj
 * description: 添加拦截器与放行的接口
 * create time: 2019/11/26 16:07
 */
@Configuration
public class PillarHandler extends WebMvcConfigurerAdapter {
    @Bean
    public HandlerInterceptor getTokenHandler() {
        return new TokenHandler();
    }

    @Bean
    public HandlerInterceptor getAuthHandler() {
        return new AuthHandler();
    }

    @Bean
    public HandlerInterceptor getOnlyLoginHandler() {
        return new OnlyLoginHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTokenHandler()).addPathPatterns("/**")
                .excludePathPatterns("/admin/admin/adminLogin")
//                .excludePathPatterns("/admin/login/api/v1/logOut")
                .excludePathPatterns("/login/api/v1/login")
                .excludePathPatterns("/login/api/v1/logOut")
                .excludePathPatterns("/login/api/v1/toIndexInfo")
//                .excludePathPatterns("/shop/api/v1/getCategoryList")
//                .excludePathPatterns("/user/api/v1/pub/selectShipper")
//                .excludePathPatterns("/user/api/v1/pub/selectUserInfo")
                .excludePathPatterns("/swagger_ui.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/**/error")
//                .excludePathPatterns("/user/api/v1/phoneRegister")
//                .excludePathPatterns("/user/api/v1/emailRegister")
//                .excludePathPatterns("/user/api/v1/emailPush")
//                .excludePathPatterns("/user/api/v1/smsPush")
//                .excludePathPatterns("/ueditor")
//                .excludePathPatterns("/acc_pay/api/aliPayReturn")
                .excludePathPatterns("/userWeiXin/api/**")
//                .excludePathPatterns("/acc_pay/api/notify")
//                .excludePathPatterns("/user/api/v1/emailGetCode")
//                .excludePathPatterns("/user/api/v1/smsPush")
//                .excludePathPatterns("/admin/sys_data/api/v1/exportQueryOrdByArea")
//                .excludePathPatterns("/admin/rec_cash/api/v1/exportCashApplyList")
//                .excludePathPatterns("/admin/rec_sys_profit/api/v1/exportProfitList")
//                .excludePathPatterns("/admin/test/api/v1/getAllDepositeTime")
//                .excludePathPatterns("/admin/test/api/v1/mergeDepositeTime")
//                .excludePathPatterns("/admin/test/api/v1/exmineDeposite")
        ;
//
//
        registry.addInterceptor(getOnlyLoginHandler()).addPathPatterns("/**")
                .excludePathPatterns("/admin/admin/adminLogin")
                .excludePathPatterns("/admin/login/api/v1/logOut")
                .excludePathPatterns("/login/api/v1/login")
                .excludePathPatterns("/login/api/v1/logOut")
                .excludePathPatterns("/login/api/v1/toIndexInfo")
//                .excludePathPatterns("/shop/api/v1/getCategoryList")
//                .excludePathPatterns("/user/api/v1/selByWeixinId")
//                .excludePathPatterns("/user/api/v1/pub/selectShipper")
//                .excludePathPatterns("/user/api/v1/pub/selectUserInfo")
                .excludePathPatterns("/swagger_ui.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/**/error")
//                .excludePathPatterns("/admin/**")
//                .excludePathPatterns("/user/api/v1/phoneRegister")
//                .excludePathPatterns("/user/api/v1/emailRegister")
//                .excludePathPatterns("/user/api/v1/emailPush")
//                .excludePathPatterns("/user/api/v1/smsPush")
//                .excludePathPatterns("/ueditor")
//                .excludePathPatterns("/acc_pay/api/aliPayReturn")
//                .excludePathPatterns("/acc_pay/api/notify")
//                .excludePathPatterns("/userWeiXin/api/**")
//                .excludePathPatterns("/user/api/v1/emailGetCode")
//                .excludePathPatterns("/user/api/v1/smsPush")
//                .excludePathPatterns("/admin/sys_data/api/v1/exportQueryOrdByArea")
//                .excludePathPatterns("/admin/rec_cash/api/v1/exportCashApplyList")
//                .excludePathPatterns("/admin/rec_sys_profit/api/v1/exportProfitList")
//                .excludePathPatterns("/admin/test/api/v1/getAllDepositeTime")
//                .excludePathPatterns("/admin/test/api/v1/mergeDepositeTime")
//                .excludePathPatterns("/admin/test/api/v1/exmineDeposite")
        ;
        registry.addInterceptor(getAuthHandler()).addPathPatterns("/admin/**")
                .excludePathPatterns("/login/api/v1/login")
                .excludePathPatterns("/login/api/v1/toIndexInfo")
                .excludePathPatterns("/admin/admin/adminLogin")
                .excludePathPatterns("/admin/login/api/v1/logOut")
//                .excludePathPatterns("/shop/api/v1/getCategoryList")
//                .excludePathPatterns("/admin/adm/api/v1/getAuthOfUser")
//                .excludePathPatterns("/ueditor")
//                .excludePathPatterns("/acc_pay/api/aliPayReturn")
//                .excludePathPatterns("/app/sys_announce_info/api/v1/selAnnounceInfo")
//                .excludePathPatterns("/acc_pay/api/notify")
//                .excludePathPatterns("/user/api/v1/emailGetCode")
//                .excludePathPatterns("/user/api/v1/smsPush")
//                .excludePathPatterns("/admin/sys_data/api/v1/exportQueryOrdByArea")
//                .excludePathPatterns("/admin/rec_cash/api/v1/exportCashApplyList")
//                .excludePathPatterns("/admin/rec_sys_profit/api/v1/exportProfitList")
//                .excludePathPatterns("/admin/test/api/v1/getAllDepositeTime")
//                .excludePathPatterns("/admin/test/api/v1/mergeDepositeTime")
//                .excludePathPatterns("/admin/test/api/v1/exmineDeposite")
        ;
        super.addInterceptors(registry);
    }
}