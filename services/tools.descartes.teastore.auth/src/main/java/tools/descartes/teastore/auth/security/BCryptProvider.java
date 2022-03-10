package tools.descartes.teastore.auth.security;

import org.mindrot.jbcrypt.BCrypt;
public final class BCryptProvider {
private  BCryptProvider(){
}
public static  boolean checkPassword(String password, String password2) {
return BCrypt.checkpw(password, password2);
}

}
