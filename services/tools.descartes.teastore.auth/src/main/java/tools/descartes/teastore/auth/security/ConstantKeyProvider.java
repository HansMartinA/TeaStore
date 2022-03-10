package tools.descartes.teastore.auth.security;

import tools.descartes.teastore.entities.message.SessionBlob;
public class ConstantKeyProvider implements IKeyProvider {
@Override
public  String getKey(SessionBlob blob) {
return "thebestsecretkey";
}

}
