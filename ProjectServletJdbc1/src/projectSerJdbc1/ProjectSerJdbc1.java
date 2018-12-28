package projectSerJdbc1;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
public class ProjectSerJdbc1 extends GenericServlet{
public Connection con;
@Override
public void init() throws ServletException{
	con=DBConnection.getcon();
}
@Override
public void service(ServletRequest req, ServletResponse res)throws ServletException, IOException{
	long b=Long.parseLong("2018122700");
	PrintWriter pw = res.getWriter();
	res.setContentType("text/html");
	String Uname = req.getParameter("Uname");
	String Fname = req.getParameter("Fname");
	String Lname = req.getParameter("Lname");
	String Mname = req.getParameter("Mname");
	String dob = req.getParameter("dob");
	String Emid1 = req.getParameter("Emid1");
	String Emid2 = req.getParameter("Emid2");
	String Pword = req.getParameter("Pword");
	long ph1 =Long.parseLong(req.getParameter("ph1"));
	String ph2 = req.getParameter("ph2");
	int age =Integer.parseInt(req.getParameter("age"));
	try {
	Statement stm=con.createStatement();
	ResultSet rs1=stm.executeQuery("select user_id from Rservices");
	while (rs1.next()){
		 b=rs1.getInt(1);
	}
	PreparedStatement ps = con.prepareStatement("insert into Rservices values(?,?,?,?,?,?,?,?,?,?,?,?)");
	ps.setLong(1, b+1);
	ps.setString(2, Uname);
	ps.setString(3, Pword);
	ps.setString(4, Fname);
	ps.setString(5, Mname);
	ps.setString(6, Lname);
	ps.setLong(7, ph1);
	ps.setString(8, dob);
	ps.setInt(9, age);
	ps.setString(10, ph2);
	ps.setString(11, Emid1);
	ps.setString(12, Emid2);
	int k= ps.executeUpdate();
	if(k>0) {
		RequestDispatcher rd = req.getRequestDispatcher("login.html");
		rd.include(req, res);
		pw.println("\nRegistration compleed successfully with UserName : "+Uname+" & UserID : "+(b+1));
	}
	
	else {
		pw.println("Rigistration Interrupted");
	}
	}
	catch(Exception e) {e.getStackTrace();}
}
}