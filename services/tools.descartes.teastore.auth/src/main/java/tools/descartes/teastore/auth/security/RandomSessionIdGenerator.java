package tools.descartes.teastore.auth.security;

import java.util.Random;
public class RandomSessionIdGenerator implements ISessionIdGenerator {
private static Random random = new  Random();

@Override
public  String getSessionId() {
return "" + random.nextInt();
}

}
