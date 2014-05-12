package entity;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/27/14
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Status extends QueryString {

    String profile_id, content,time;

    public Status(String s1, String s2) {
        profile_id = s1;
        content = s2;
        time="2014-04-30 09:42:37";
    }

}
