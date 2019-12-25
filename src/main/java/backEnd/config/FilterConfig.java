package backEnd.config;

import backEnd.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean tokenFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestFilter());
        registration.addUrlPatterns(
                "/Collect/*", "/Comment/*", "/Index/*", "/News/*", "/Order/*", "/Patent/*", "/Picture/*",
                "/Search/*", "/Trade/*", "/User/*", "/Image/*", "/Pay/*", "/WX/*", "/Alipay/*"
        );
        registration.setName("tokenFilter");
        registration.setOrder(1);
        return registration;
    }
}