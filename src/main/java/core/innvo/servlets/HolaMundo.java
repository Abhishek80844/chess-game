package core.sebas.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Test class
 * @author srevuelta
 * @date June 2019   
 fhshdf
 */
public class HolaMundo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(HolaMundo.class);

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Hola mundo</title>");
			out.println("</head>");out.println("<body>");
			out.println("<h1>Hola mundo!</h1>");
			out.println("</body>");
			out.println("</html>");
		} 
		catch(Exception e) {
			log.error("Error to write servlet" + this.getClass().getName());
		}
		finally {out.close();}
	}
}
