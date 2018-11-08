package JacksonTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.MyDto;
import jackson.sensitive.UserDetailResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * @Author：zeqi
 * @Date: Created in 19:03 7/5/18.
 * @Description:
 */
public class JacksonTest {


    @Test
    public void givenNullsIgnoredOnClass_whenWritingObjectWithNullField_thenIgnored()
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MyDto dtoObject = new MyDto();

        String dtoAsString = mapper.writeValueAsString(dtoObject);

        System.out.println(dtoAsString);
       /* assertThat(dtoAsString, containsString("intValue"));
        assertThat(dtoAsString, not(containsString("stringValue")));*/
    }

    @Test
    public void testSensitiveParameter()
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserDetailResponse response = new UserDetailResponse();
        response.setMobile("15994737373");
        response.setUseName("王小二");

        String dtoAsString = mapper.writeValueAsString(response);

        System.out.println(dtoAsString);
       /* assertThat(dtoAsString, containsString("intValue"));
        assertThat(dtoAsString, not(containsString("stringValue")));*/
    }
}
