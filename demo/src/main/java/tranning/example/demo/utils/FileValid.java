package tranning.example.demo.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = FileValidate.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileValid {
    String message() default "Image must png,jpg,jpeg";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
