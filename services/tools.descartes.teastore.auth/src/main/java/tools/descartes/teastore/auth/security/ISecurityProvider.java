package tools.descartes.teastore.auth.security;

import tools.descartes.teastore.entities.message.SessionBlob;
public interface ISecurityProvider {
public  IKeyProvider getKeyProvider() ;

public  SessionBlob secure(SessionBlob blob) ;

public  SessionBlob validate(SessionBlob blob) ;

}
