package social.roo.model.dto;

import social.roo.model.entity.User;

/**
 * @author biezhi
 * @date 2017/7/31
 */
public class Auth {

    public static boolean check(){
        return true;
    }

    public static boolean hasRole(String...roles){
        return true;
    }

    public static User loginUser(){
        return null;
    }
}
