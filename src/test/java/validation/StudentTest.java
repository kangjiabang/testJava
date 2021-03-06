package validation;


import org.apache.dubbo.common.extension.DisableInject;
import org.junit.Ignore;
import org.junit.Test;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author：zeqi
 * @Date: Created in 10:56 9/5/18.
 * @Description:
 */
public class StudentTest  {

    @Test
    @Ignore
    public void testValidate() {
        /*Student student = getBean();
        List<String> validate = validate(student);
        validate.forEach(row -> {
            System.out.println(row.toString());
        });
*/
    }

    private static Student getBean() {
        Student bean = new Student();
        bean.setName(null);

        return bean;
    }

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> List<String> validate(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }
}