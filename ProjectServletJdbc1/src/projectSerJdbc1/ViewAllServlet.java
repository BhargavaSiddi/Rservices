package projectSerJdbc1;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
public class ViewAllServlet extends GenericServlet{
	public Connection con;
	@Override
	public void init() throws ServletException{
		con=DBConnection.getcon();
	}
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from train");
			while(rs.next()) {
			pw.println("<br>"+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5)+"<br>");
			pw.println();
			}
			RequestDispatcher rd = req.getRequestDispatcher("link.html");
			rd.include(req, res);
		}
		catch(Exception e) {e.printStackTrace();}
	}
}
