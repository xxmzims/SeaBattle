package Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Указываем что, мы можем пометить этой аннотацией
@Target(ElementType.METHOD)
// Указываем политику удержания класса(Только в исходном коде, в скомпилированном файле, во время выполнения программы)
@Retention(RetentionPolicy.RUNTIME)
// создание аннотации
public @interface MethodInfo {
    // можем использовать параметры для аннотации
    String author() default "Maks";

    int dateOfCreation() default 2019;

    // default указывает стандартный тип параметров

    String purpose();
}
