package backEnd.config;

import backEnd.filter.RequestFilter;
import backEnd.handler.admin.AdminService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean tokenFilter(AdminService adminService) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestFilter(adminService));
        registration.addUrlPatterns(
                "/collect/*", "/news/*", "/order/*", "/patent/*", "/admin/group/*", "/admin/right/*",
                "/user/*", "/pay/*", "/need/*"
        );
        registration.setName("tokenFilter");
        registration.setOrder(1);
        return registration;
    }
}