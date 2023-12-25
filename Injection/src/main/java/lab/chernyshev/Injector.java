package lab.chernyshev;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    public <T> T inject(T object, boolean flag) {
        try {
            Properties properties = getProperties(flag);
            Class<?> c = object.getClass();
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    Class<?> fieldType = field.getType();
                    String interfaceName = fieldType.getName();
                    String implementationClassName = properties.getProperty(interfaceName);
                    if (implementationClassName != null) {
                        Object implementationInstance = Class.forName(implementationClassName).newInstance();
                        field.setAccessible(true);
                        field.set(object, implementationInstance);
                    }
                }
            }

            return object;
        } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Properties getProperties(boolean flag) throws IOException {
        FileInputStream input;
        if (!flag) {
            input = new FileInputStream("src/main/resources/configv1.properties");
        } else {
            input = new FileInputStream("src/main/resources/configv2.properties");
        }
        Properties properties = new Properties();
        properties.load(input);
        return properties;
    }
}
