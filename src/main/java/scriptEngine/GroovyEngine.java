package scriptEngine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class GroovyEngine {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");

        // basic example
        System.out.println(engine.eval("(1..10).sum()"));

        // example showing scripting variables
        engine.put("first", "HELLO");
        engine.put("second", "world");
        System.out.println(engine.eval("first.toLowerCase() + second.toUpperCase()"));
    }
}