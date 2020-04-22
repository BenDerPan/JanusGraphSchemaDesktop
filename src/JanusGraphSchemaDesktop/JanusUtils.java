package JanusGraphSchemaDesktop;


import org.janusgraph.core.attribute.Geoshape;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class JanusUtils {
    public static final HashMap<String,Class<?>> SupportDataTypes=new HashMap<String, Class<?>>(){
        {
            put("String",String.class);
            put("Character",Character.class);
            put("Boolean",Boolean.class);
            put("Byte",Byte.class);
            put("Short",Short.class);
            put("Integer",Integer.class);
            put("Long",Long.class);
            put("Float",Float.class);
            put("Double",Double.class);
            put("Date", Date.class);
            put("Geoshape", Geoshape.class);
            put("UUID", UUID.class);
        }
    };

    public static Class<?> getDataTypeByName(String name){
        if (SupportDataTypes.containsKey(name)){
            return SupportDataTypes.get(name);
        }
        return null;
    }

}
