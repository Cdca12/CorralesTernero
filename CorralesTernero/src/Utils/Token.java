package Utils;

/**
 *
 * @author Carlos Contreras
 */
public class Token {

    private String user, pass;
    private static boolean isValid;

    public Token(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
    public static void expireToken() {
        isValid = false;
    }
    
    public static void refreshToken() {
        isValid = true;
    }

    public static boolean checkValidation() {
        return isValid;
    }
    
    
    
    
}
