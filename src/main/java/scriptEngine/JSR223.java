package scriptEngine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSR223 {

    public static void main(String[] args) throws ScriptException {
        //add python to classpath
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("python");
        System.out.println(engine);
        engine.eval("import sys");
        engine.eval("print sys");
        engine.put("a", 42);
        engine.eval("print a");
        engine.eval("x = 2 + 2");
        Object x = engine.get("x");
        System.out.println("x: " + x);
    }
}