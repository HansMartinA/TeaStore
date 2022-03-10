package tools.descartes.teastore.webui.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.rest.LoadBalancedImageOperations;
import tools.descartes.teastore.registryclient.rest.LoadBalancedStoreOperations;
import tools.descartes.teastore.entities.ImageSizePreset;
@WebServlet("/about")
public class AboutUsServlet extends AbstractUIServlet {
private static final long serialVersionUID = 1L;

public  AboutUsServlet(){
super();
}
@Override
protected  void handleGETRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
checkforCookie(request, response);
HashMap<String, String> portraits = LoadBalancedImageOperations.getWebImages(Arrays.asList("andreBauer", "johannesGrohmann", "joakimKistowski", "simonEismann", "norbertSchmitt", "samuelKounev"), ImageSizePreset.PORTRAIT.getSize());
request.setAttribute("portraitAndre", portraits.get("andreBauer"));
request.setAttribute("portraitJohannes", portraits.get("johannesGrohmann"));
request.setAttribute("portraitJoakim", portraits.get("joakimKistowski"));
request.setAttribute("portraitSimon", portraits.get("simonEismann"));
request.setAttribute("portraitNorbert", portraits.get("norbertSchmitt"));
request.setAttribute("portraitKounev", portraits.get("samuelKounev"));
request.setAttribute("descartesLogo", LoadBalancedImageOperations.getWebImage("descartesLogo", ImageSizePreset.LOGO.getSize()));
request.setAttribute("storeIcon", LoadBalancedImageOperations.getWebImage("icon", ImageSizePreset.ICON.getSize()));
request.setAttribute("title", "TeaStore About Us");
request.setAttribute("login", LoadBalancedStoreOperations.isLoggedIn(getSessionBlob(request)));
request.getRequestDispatcher("WEB-INF/pages/about.jsp").forward(request, response);
}

}
