package entity;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/28/14
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class QueryString {

    public String getInsertQuery(String table){

        String columnString="",whats="";

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {

            field.setAccessible(true);

            if (!columnString.isEmpty()) {
                columnString = columnString + ", ";
                whats  = whats + ", ";
            }
            columnString = columnString + field.getName();
            whats = whats + "?";

        }

        String retString="insert into  " + table + "( "+ columnString + " ) values( " + whats + ")";

        return retString;

    }

    public HashMap getHashMap(){

        HashMap ret=new HashMap();

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                ret.put(field.getName(),field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return ret;
    }


}
