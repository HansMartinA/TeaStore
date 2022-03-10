package tools.descartes.teastore.webui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.rest.LoadBalancedStoreOperations;
import tools.descartes.teastore.entities.message.SessionBlob;
@WebServlet("/loginAction")
public class LoginActionServlet extends AbstractUIServlet {
private static final long serialVersionUID = 1L;

public  LoginActionServlet(){
super();
}
@Override
protected  void handleGETRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
redirect("/", response);
}

@Override
protected  void handlePOSTRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
boolean login = false;
if (request.getParameter("username") != null && request.getParameter("password") != null)
{
SessionBlob blob = LoadBalancedStoreOperations.login(getSessionBlob(request), request.getParameter("username"), request.getParameter("password"));
login = (blob != null && blob.getSID() != null);
if (login)
{
saveSessionBlob(blob, response);
if (request.getParameter("referer") != null && request.getParameter("referer").contains("tools.descartes.teastore.webui/cart"))
{
redirect("/cart", response, MESSAGECOOKIE, SUCESSLOGIN);
}
else
{
redirect("/", response, MESSAGECOOKIE, SUCESSLOGIN);
}
}
else
{
redirect("/login", response, ERRORMESSAGECOOKIE, WRONGCREDENTIALS);
}
}
else
if (request.getParameter("logout") != null)
{
SessionBlob blob = LoadBalancedStoreOperations.logout(getSessionBlob(request));
saveSessionBlob(blob, response);
destroySessionBlob(blob, response);
redirect("/", response, MESSAGECOOKIE, SUCESSLOGOUT);
}
else
{
handleGETRequest(request, response);
}
}

}
