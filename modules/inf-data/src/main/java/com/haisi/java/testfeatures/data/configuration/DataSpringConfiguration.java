package com.haisi.java.testfeatures.data.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.haisi.java.testfeatures.data.entity")
@EnableJpaRepositories("com.haisi.java.testfeatures.data.repository")
public class DataSpringConfiguration {
}
