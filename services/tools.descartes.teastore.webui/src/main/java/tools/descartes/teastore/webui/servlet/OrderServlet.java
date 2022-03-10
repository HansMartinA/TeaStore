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
@WebServlet("/order")
public class OrderServlet extends AbstractUIServlet {
private static final long serialVersionUID = 1L;

public  OrderServlet(){
super();
}
@Override
protected  void handleGETRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
checkforCookie(request, response);
if (getSessionBlob(request).getOrderItems().size() == 0)
{
redirect("/", response);
}
else
{
doPost(request, response);
}
}

@Override
protected  void handlePOSTRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
request.setAttribute("CategoryList", LoadBalancedCRUDOperations.getEntities(Service.PERSISTENCE, "categories", Category.class,  - 1,  - 1));
request.setAttribute("storeIcon", LoadBalancedImageOperations.getWebImage("icon", ImageSizePreset.ICON.getSize()));
request.setAttribute("title", "TeaStore Order");
request.setAttribute("login", LoadBalancedStoreOperations.isLoggedIn(getSessionBlob(request)));
request.getRequestDispatcher("WEB-INF/pages/order.jsp").forward(request, response);
}

}
