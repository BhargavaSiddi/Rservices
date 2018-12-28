package projectSerJdbc1;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
public class ViewUserInfo extends GenericServlet {
	public Connection con;
	@Override
	public void init() throws ServletException{
		con=DBConnection.getcon();
	}
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String Tnum = req.getParameter("Tnum");
		try {
			PreparedStatement ps = con.prepareStatement("select * from train where Tnum = ?");
			ps.setString(1, Tnum);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd = req.getRequestDispatcher("link.html");
				rd.include(req, res);
				pw.println("<br>Tnum : "+rs.getString(1));
				pw.println("<br>Tname : "+rs.getString(2));
				pw.println("<br>From : "+rs.getString(3));
				pw.println("<br>To : "+rs.getString(4));
				pw.println("<br>Seat Availability : "+rs.getInt(5));
			}
			else {
				pw.println("Invalid Tnum");
				RequestDispatcher rd = req.getRequestDispatcher("view_info.html");
				rd.include(req, res);
			}
		}catch(Exception e) {}
	}
}
