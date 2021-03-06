package j.script;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Nashorn {

   public static void main(String[] args) throws ScriptException {
      {
         ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
         Bindings obj = (Bindings) engine.eval(
            "var obj = { k: 1, v: 2}\n  obj");
         System.out.println(obj.toString());
         Integer key = (Integer) obj.get("k");
         Integer value = (Integer) obj.get("v");
         System.out.println(key); // prints: 1
         System.out.println(value); // prints: 1
      }

      {
         ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
         String result = (String) engine.eval(
            "var obj = { k: 1, v: 2}\n  JSON.stringify(obj)");
         System.out.println(result);
      }
   }

}
