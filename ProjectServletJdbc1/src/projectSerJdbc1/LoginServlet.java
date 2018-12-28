package projectSerJdbc1;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
public class LoginServlet extends GenericServlet{
	public Connection con;
	@Override
	public void init() throws ServletException{
		con=DBConnection.getcon();
	}
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String Uname = req.getParameter("Uname");
		String Pword = req.getParameter("Pword");
		try {
			PreparedStatement ps = con.prepareStatement("select * from rservices where User_name = ? and password = ? ");
			ps.setString(1, Uname);
			ps.setString(2, Pword);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				pw.println("WELCOME : <br>");
				RequestDispatcher rd = req.getRequestDispatcher("link.html");
				rd.include(req, res);
			}else {
				pw.println("Invalid EmailID (or) Password--------");
				RequestDispatcher rd = req.getRequestDispatcher("login.html");
				rd.include(req, res);
			}
		}
		catch(Exception e) {
		}
	}
}
