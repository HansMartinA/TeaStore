package tools.descartes.teastore.webui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.rest.LoadBalancedImageOperations;
import tools.descartes.teastore.entities.ImageSizePreset;
@WebServlet("/database")
public class DataBaseServlet extends AbstractUIServlet {
private static final long serialVersionUID = 1L;

public  DataBaseServlet(){
super();
}
@Override
protected  void handleGETRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
checkforCookie(request, response);
request.setAttribute("storeIcon", LoadBalancedImageOperations.getWebImage("icon", ImageSizePreset.ICON.getSize()));
request.setAttribute("title", "TeaStore Database");
request.getRequestDispatcher("WEB-INF/pages/database.jsp").forward(request, response);
}

}
