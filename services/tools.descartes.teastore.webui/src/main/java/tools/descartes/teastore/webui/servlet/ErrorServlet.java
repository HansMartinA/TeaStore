package tools.descartes.teastore.webui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.rest.LoadBalancedCRUDOperations;
import tools.descartes.teastore.registryclient.rest.LoadBalancedImageOperations;
import tools.descartes.teastore.registryclient.rest.LoadBalancedStoreOperations;
import tools.descartes.teastore.entities.Category;
import tools.descartes.teastore.entities.ImageSizePreset;
@WebServlet("/error")
public class ErrorServlet extends AbstractUIServlet {
private static final long serialVersionUID = 1L;

public  ErrorServlet(){
super();
}
@Override
protected  void handleGETRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
if (statusCode == null)
{
redirect("/", response);
}
else
{
request.setAttribute("CategoryList", LoadBalancedCRUDOperations.getEntities(Service.PERSISTENCE, "categories", Category.class,  - 1,  - 1));
request.setAttribute("storeIcon", LoadBalancedImageOperations.getWebImage("icon", ImageSizePreset.ICON.getSize()));
request.setAttribute("errorImage", LoadBalancedImageOperations.getWebImage("error", ImageSizePreset.ERROR.getSize()));
request.setAttribute("title", "TeaStore Error ");
request.setAttribute("login", LoadBalancedStoreOperations.isLoggedIn(getSessionBlob(request)));
request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request, response);
}
}

}
