package ReflectionAPI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class TestMethodsWithReflection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        // Название класса1 Название класса2 Название метода

        Class Object1 = Class.forName(scanner.next());
        Class Object2 = Class.forName(scanner.next());
        String methodName = scanner.next();

        Method m = Object1.getMethod(methodName, Object2);
        Object o1 = Object1.newInstance();
        Object o2 = Object2.getConstructor(String.class).newInstance("StringValue");

        m.invoke(o1, o2);

        System.out.println(o1);
    }
}
