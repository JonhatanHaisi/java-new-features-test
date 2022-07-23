package com.haisi.java.testfeatures.app.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
    "com.haisi.java.testfeatures.app",
    "com.haisi.java.testfeatures.*.configuration",
})
public class AppSpringConfiguration {
}
