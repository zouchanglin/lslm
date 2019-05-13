package lishan.live.lslm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyMvcConfig
 * @Description
 * @Author tim
 * @Date 2019-04-18 10:15
 * @Version 1.0
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("index");
        registry.addViewController("/admin/index.html").setViewName("index");
        registry.addViewController("/admin/index").setViewName("index");
        registry.addViewController("/admin/company/addHtml").setViewName("company/company-add");
        registry.addViewController("/admin/activity/addHtml").setViewName("activity/activity-add");
    }
}
