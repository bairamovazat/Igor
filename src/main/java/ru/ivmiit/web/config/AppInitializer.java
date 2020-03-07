package ru.ivmiit.web.config;


import ru.ivmiit.web.security.config.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@ComponentScan("ru.ivmiit.web")
@Slf4j
@Import({AppConfiguration.class, JpaConfiguration.class, SecurityConfig.class})
public class AppInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            log.error(e.getMessage(), e);
        });
        configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(AppInitializer.class);
    }

    //Чтобым можно было запускать через war
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

}