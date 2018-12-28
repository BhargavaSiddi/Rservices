package projectSerJdbc1;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
public class Booking extends GenericServlet{
	public Connection con;
	@Override
	public void init() throws ServletException{
		con = DBConnection.getcon();
	}
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException{
		long PNR = Long.parseLong("0000000000");
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		int Tnum = Integer.parseInt(req.getParameter("Tnum"));
		String Tname = req.getParameter("Tname");
		String From = req.getParameter("From");
		String To = req.getParameter("To");
		String DoJ = req.getParameter("DoJ");
		int Pnum = Integer.parseInt(req.getParameter("Pnum"));
		int Tf = Integer.parseInt(req.getParameter("Tf"));
		try {
			PreparedStatement ps = con.prepareStatement("insert into bookings values(?,?,?,?,?,?,?,?)");
			ps.setLong(1, PNR+1);
			ps.setInt(2, Tnum);
			ps.setString(3, Tname);
			ps.setString(4, From);
			ps.setString(5, To);
			ps.setString(6, DoJ);
			ps.setInt(7, Pnum);
			ps.setInt(8, Tf);
			int k =ps.executeUpdate();
			if(k>0) {
				pw.println("Ticket Conformed with PNR :"+(PNR+1));
				RequestDispatcher rd = req.getRequestDispatcher("link.html");
				rd.include(req, res);
			}
			else {
				pw.println("Ticket Conformation failed");
				RequestDispatcher rd = req.getRequestDispatcher("link.html");
				rd.include(req, res);
			}
		}catch(Exception e) {e.printStackTrace();}
	
	}
}
