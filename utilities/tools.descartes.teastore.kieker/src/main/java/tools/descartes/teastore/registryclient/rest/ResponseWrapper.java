package tools.descartes.teastore.registryclient.rest;

import javax.ws.rs.core.Response;
public final class ResponseWrapper {
private  ResponseWrapper(){
}
public static  Response wrap(Response response) {
return response;
}

}
