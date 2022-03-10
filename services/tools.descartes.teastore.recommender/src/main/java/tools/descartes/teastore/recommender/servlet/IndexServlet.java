package tools.descartes.teastore.recommender.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

public  IndexServlet(){
super();
}
protected  void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
response.getWriter().println("This is the recommender module running at " + request.getProtocol() + "://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/" + request.getContextPath());
}

protected  void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
doGet(request, response);
}

}
