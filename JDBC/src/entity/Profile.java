package entity;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/27/14
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Profile extends QueryString {

    private String username, email, password;

    public Profile(String username, String email, String password, int age) {

        this.username = username;
        this.email = email;
        this.password = password;

    }

    public String toString() {
        return "username: " + username + "   email: " + email;
    }

    public String columnList() {
        Field[] fields = this.getClass().getDeclaredFields();
        String retString = "";
        for (Field field : fields) {
            if (!retString.isEmpty()) {
                retString = retString + ", ";
            }
            retString = retString + field.getName();
        }
        return retString;
    }

    public String valueList() {
        Field[] fields = this.getClass().getDeclaredFields();
        String retString = "";
        for (Field field : fields) {
            try {
                if (!retString.isEmpty()) {
                    retString = retString + ", ";
                }
                retString = retString + "'" + field.get(this) + "'";

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return retString;
    }


}
