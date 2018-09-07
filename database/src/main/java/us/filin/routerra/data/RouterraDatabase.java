package us.filin.routerra.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@EnableJpaRepositories(basePackages = {"us.filin.routerra.data.service"})
@EntityScan("us.filin.routerra.data.jpa")
@ComponentScan(basePackages = {"us.filin.routerra.data.service"})
public @interface RouterraDatabase {
}
