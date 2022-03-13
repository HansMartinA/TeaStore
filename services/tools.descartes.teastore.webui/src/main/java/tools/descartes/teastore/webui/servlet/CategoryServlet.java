package tools.descartes.teastore.webui.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
import tools.descartes.teastore.registryclient.rest.HttpWrapper;
import tools.descartes.teastore.registryclient.rest.LoadBalancedCRUDOperations;
import tools.descartes.teastore.registryclient.rest.LoadBalancedImageOperations;
import tools.descartes.teastore.registryclient.rest.LoadBalancedStoreOperations;
import tools.descartes.teastore.registryclient.rest.ResponseWrapper;
import tools.descartes.teastore.entities.Category;
import tools.descartes.teastore.entities.ImageSizePreset;
import tools.descartes.teastore.entities.Product;
@WebServlet("/category")
public class CategoryServlet extends AbstractUIServlet {
private static final long serialVersionUID = 1L;

private static final int INITIAL_PRODUCT_DISPLAY_COUNT = 20;

private static final List<Integer> PRODUCT_DISPLAY_COUNT_OPTIONS = Arrays.asList(5, 10, 20, 30, 50);

public  CategoryServlet(){
super();
}
@Override
protected  void handleGETRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
if (request.getParameter("category") != null)
{
checkforCookie(request, response);
long categoryID = Long.valueOf(request.getParameter("category"));
Category category = LoadBalancedCRUDOperations.getEntity(Service.PERSISTENCE, "categories", Category.class, categoryID);
int products = Integer.valueOf(ServiceLoadBalancer.loadBalanceRESTOperation(Service.PERSISTENCE, "products", Product.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("count").path(String.valueOf(categoryID))).get()).readEntity(String.class)));
int numberProducts = INITIAL_PRODUCT_DISPLAY_COUNT;
if (request.getAttribute("numberProducts") != null)
{
numberProducts = Integer.valueOf(request.getAttribute("numberProducts").toString());
}
int page = 1;
if (request.getParameter("page") != null)
{
int pagenumber = Integer.valueOf(request.getParameter("page"));
int maxpages = (int) Math.ceil(((double) products) / numberProducts);
if (pagenumber <= maxpages)
{
page = pagenumber;
}
}
ArrayList<String> navigation = createNavigation(products, page, numberProducts);
List<Product> productlist = LoadBalancedCRUDOperations.getEntities(Service.PERSISTENCE, "products", Product.class, "category", categoryID, (page - 1) * numberProducts, numberProducts);
request.setAttribute("productImages", LoadBalancedImageOperations.getProductPreviewImages(productlist));
request.setAttribute("storeIcon", LoadBalancedImageOperations.getWebImage("icon", ImageSizePreset.ICON.getSize()));
request.setAttribute("CategoryList", LoadBalancedCRUDOperations.getEntities(Service.PERSISTENCE, "categories", Category.class,  - 1,  - 1));
request.setAttribute("title", "TeaStore Categorie " + category.getName());
request.setAttribute("Productslist", productlist);
request.setAttribute("category", category.getName());
request.setAttribute("login", LoadBalancedStoreOperations.isLoggedIn(getSessionBlob(request)));
request.setAttribute("categoryID", categoryID);
request.setAttribute("currentnumber", numberProducts);
request.setAttribute("pagination", navigation);
request.setAttribute("pagenumber", page);
request.setAttribute("productdisplaycountoptions", PRODUCT_DISPLAY_COUNT_OPTIONS);
request.getRequestDispatcher("WEB-INF/pages/category.jsp").forward(request, response);
}
else
{
redirect("/", response);
}
}

@Override
protected  void handlePOSTRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, LoadBalancerTimeoutException {
if (request.getParameter("number") != null && request.getParameter("page") != null && request.getParameter("category") != null)
{
redirect("/category?category=" + request.getParameter("category") + "&page=" + request.getParameter("page"), response, PRODUCTCOOKIE, request.getParameter("number"));
}
else
{
handleGETRequest(request, response);
}
}

private  ArrayList<String> createNavigation(int products, int page, int numberProducts) {
ArrayList<String> navigation = new  ArrayList<String>();
int numberpagination = 5;
int maxpages = (int) Math.ceil(((double) products) / numberProducts);
if (maxpages < page)
{
return navigation;
}
if (page == 1)
{
if (maxpages == 1)
{
navigation.add("1");
return navigation;
}
int min = Math.min(maxpages, numberpagination + 1);
for (int i = 1 ; i <= min ; i++)
{
navigation.add(String.valueOf(i));
}
}
else
{
navigation.add("previous");
if (page == maxpages)
{
int max = Math.max(maxpages - numberpagination, 1);
for (int i = max ; i <= maxpages ; i++)
{
navigation.add(String.valueOf(i));
}
return navigation;
}
else
{
int lowerbound = (int) Math.ceil(((double) numberpagination - 1.0D) / 2.0D);
int upperbound = (int) Math.floor(((double) numberpagination - 1.0D) / 2.0D);
int up = Math.min(page + upperbound, maxpages);
int down = Math.max(page - lowerbound, 1);
for (int i = down ; i <= up ; i++)
{
navigation.add(String.valueOf(i));
}
}
}
navigation.add("next");
return navigation;
}

}
