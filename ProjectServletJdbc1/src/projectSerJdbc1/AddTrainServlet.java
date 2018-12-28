package projectSerJdbc1;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
public class AddTrainServlet extends GenericServlet{
	public Connection con;
	@Override
	public void init() throws ServletException{
		con=DBConnection.getcon();
	}
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		int Tnum = Integer.parseInt(req.getParameter("Tnum"));
		String Tname = req.getParameter("Tname");
		String From = req.getParameter("From");
		String To = req.getParameter("To");
		int Avail = Integer.parseInt(req.getParameter("Avail"));
		try {
			PreparedStatement ps = con.prepareStatement("insert into train values(?,?,?,?,?)");
			ps.setInt(1, Tnum);
			ps.setString(2, Tname);
			ps.setString(3, From);
			ps.setString(4, To);
			ps.setInt(5, Avail);
			int k = ps.executeUpdate();
			if(k>0) {
				pw.println("New Train Added with number "+Tnum);
				RequestDispatcher rd = req.getRequestDispatcher("link.html");
				rd.include(req, res);
			}
		}catch(Exception e) {e.printStackTrace();}
	}
}
