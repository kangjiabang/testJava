package basic.enums;

import org.apache.poi.ss.formula.functions.T;

public class EnumTest {

    public static void main(String[] args) {

        System.out.println(Color.class.isEnum());
        printEnumNames(Color.class);

    }

    public static void printEnumNames(Class<?> clazz) {

        if (clazz.isEnum()) {

            Class<? extends Enum<?>> enumCls = (Class<? extends Enum<?>>) clazz;
            Enum<?>[] enumConstants = enumCls.getEnumConstants();
            if (enumConstants == null) {
                return;
            }
            for (Enum<?> enumInstance : enumConstants) {
                System.out.println(enumInstance.name());
            }


        }
    }
}
