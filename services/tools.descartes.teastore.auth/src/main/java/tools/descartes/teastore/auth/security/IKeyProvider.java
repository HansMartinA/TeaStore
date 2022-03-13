package tools.descartes.teastore.auth.security;

import tools.descartes.teastore.entities.message.SessionBlob;
public interface IKeyProvider {
public  String getKey(SessionBlob blob) ;

}
