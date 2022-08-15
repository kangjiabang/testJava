package databinder;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.validation.DataBinder;

public class DataBinderExample {

    public static void main(String[] args) {

        MutablePropertyValues mpv = new MutablePropertyValues();

        mpv.addPropertyValue("anInt","10");

        TestBean testBean = new TestBean();
        DataBinder db = new DataBinder(testBean);

        db.bind(mpv);

        System.out.println(testBean);
    }
}
