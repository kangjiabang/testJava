package messageformat;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

public class MessageFormatterTest {

    public static void main(String[] args) {
        String messagePattern = "I am {},I am in {}";
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(messagePattern,new Object[] {"xiaokang","beijing",new RuntimeException("error happens")});
        System.out.println(formattingTuple.getMessage());
        System.out.println(formattingTuple.getThrowable());
    }
}
