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
import tools.descartes.teastore.webui.servlet.elhelper.ELHelperUtils;
import tools.descartes.teastore.entities.Category;
import tools.descartes.teastore.entities.ImageSizePreset;
import tools.descartes.teastore.entities.Order;
import tools.descartes.teastore.entities.User;
@WebServlet("/profile")
public class ProfileServlet extends AbstractUIServlet {
private static final long serialVersionUID = 1L;

public  ProfileServlet(){
super();
}
@Override
protected  void handleGETRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
checkforCookie(request, response);
if (!LoadBalancedStoreOperations.isLoggedIn(getSessionBlob(request)))
{
redirect("/", response);
}
else
{
request.setAttribute("storeIcon", LoadBalancedImageOperations.getWebImage("icon", ImageSizePreset.ICON.getSize()));
request.setAttribute("CategoryList", LoadBalancedCRUDOperations.getEntities(Service.PERSISTENCE, "categories", Category.class,  - 1,  - 1));
request.setAttribute("title", "TeaStore Home");
request.setAttribute("User", LoadBalancedCRUDOperations.getEntity(Service.PERSISTENCE, "users", User.class, getSessionBlob(request).getUID()));
request.setAttribute("Orders", LoadBalancedCRUDOperations.getEntities(Service.PERSISTENCE, "orders", Order.class, "user", getSessionBlob(request).getUID(),  - 1,  - 1));
request.setAttribute("login", LoadBalancedStoreOperations.isLoggedIn(getSessionBlob(request)));
request.setAttribute("helper", ELHelperUtils.UTILS);
request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
}
}

}
