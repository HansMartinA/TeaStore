package tools.descartes.teastore.kieker.rabbitmq;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kieker.common.record.IMonitoringRecord;
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

@Override
protected  void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
PrintWriter writer = response.getWriter();
response.setCharacterEncoding("utf8");
writer.println("<h1>Kieker logs</h1>");
writer.println("<a href=\"/logs/\">Get log files</a>");
writer.println("<a href=\"/logs//displaylogs\">Display logs currently stored in memory</a>");
writer.println("<a href=\"/logs/reset\">Reset logs</a>");
}

}
