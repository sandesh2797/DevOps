ANNEXURE 1 : USER INTERFACE SCREENS

ANNEXURE 2 : OUTPUT REPORTS WITH DATA ( if any )

ANNEXURE 3 : SAMPLE PROGRAM CODE / Project Demo( which will prove sufficient development is done by the student ) Page 5

package com.project.Controller;



import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;



import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;



import com.project.Bean.ManagerBean;

import com.project.Bean.TLBean;

import com.project.Bean.TMBean;

import com.project.DAO.CustomerDao;

import com.project.DAO.ManagerDao;

import com.project.DAO.TeamLeaderDao;

import com.project.DAO.TeamMemberDao;

import com.project.DB.MainCon;



/**

 * Servlet implementation class LoginServlet

 */

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {

private static final long serialVersionUID = 1L;



    /**

     * @see HttpServlet#HttpServlet()

     */

    public LoginServlet() {

        super();

        // TODO Auto-generated constructor stub

    }



/**

 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)

 */

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// TODO Auto-generated method stub



}



/**

 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)

 */

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// TODO Auto-generated method stub



ManagerBean bean=new ManagerBean();

ManagerDao dao=new ManagerDao();

if(request.getParameter("action").equalsIgnoreCase("managerLogin"))

{

bean.setUsername(request.getParameter("username"));

bean.setPassword(request.getParameter("password"));



if(dao.checkManagerLogin(bean)==true)

{

try

{

dao.update();

} catch (SQLException e)

{

// TODO Auto-generated catch block

e.printStackTrace();

}

HttpSession session=request.getSession();

session.setAttribute("mgrUsername", request.getParameter("username"));

PrintWriter out = response.getWriter();

response.setContentType("text/html");

out.println("<script type=\"text/javascript\">");

out.println("alert('Login sucessfully...');");

out.println("location='Home.jsp';");

out.println("</script>");

}

else

{

PrintWriter out = response.getWriter();

response.setContentType("text/html");

out.println("<script type=\"text/javascript\">");

out.println("alert('Login error! Please retry...');");

out.println("location='managerLogin.jsp';");

out.println("</script>");

}



}



if(request.getParameter("action").equalsIgnoreCase("TeamLeaderLogin"))

{

TeamLeaderDao tldao=new TeamLeaderDao();

TLBean tlbean=new TLBean();

tlbean.setTlusername(request.getParameter("tlusername"));

tlbean.setTlpassword(request.getParameter("tlpassword"));

tlbean.setTeam(request.getParameter("team_name"));



if(tldao.checkTL_Login(tlbean)==true)

{

try

{

dao.update();

} catch (SQLException e)

{

// TODO Auto-generated catch block

e.printStackTrace();

}

HttpSession tlsession=request.getSession();

tlsession.setAttribute("team_name",tlbean.getTeam());

tlsession.setAttribute("tlname",request.getParameter("tlusername"));

PrintWriter out = response.getWriter();

response.setContentType("text/html");

out.println("<script type=\"text/javascript\">");

out.println("alert('Login sucessfully...');");

out.println("location='TL_Home.jsp';");

out.println("</script>");

}

else

{

PrintWriter out = response.getWriter();

response.setContentType("text/html");

out.println("<script type=\"text/javascript\">");

out.println("alert('Login error! Please retry...');");

out.println("location='TL_Login.jsp';");

out.println("</script>");

}



}

if(request.getParameter("action").equalsIgnoreCase("TeamMemberLogin"))

{

try

{

dao.update();

} catch (SQLException e)

{

// TODO Auto-generated catch block

e.printStackTrace();

}

TeamMemberDao tmdao=new TeamMemberDao();

TMBean tmbean=new TMBean();

tmbean.setTlusername(request.getParameter("tmusername"));

tmbean.setTlpassword(request.getParameter("tmpassword"));

tmbean.setTeam(request.getParameter("team_name"));



if(tmdao.checkTM_Login(tmbean)==true)

{





try

{

String empName=null;

Connection con=MainCon.getConnection();

Statement smt2=con.createStatement();

ResultSet rs2=smt2.executeQuery("select empname from employee where email='"+request.getParameter("tmusername")+"'");

while(rs2.next())

{

empName=rs2.getString(1);

}

HttpSession tlsession=request.getSession();

tlsession.setAttribute("team_name",tmbean.getTeam());

String memberName=tmdao.getMemberName(tmbean);

tlsession.setAttribute("memberName",memberName);

tlsession.setAttribute("empName",empName);

tlsession.setAttribute("tmusername",request.getParameter("tmusername"));

PrintWriter out = response.getWriter();

response.setContentType("text/html");

out.println("<script type=\"text/javascript\">");

out.println("alert('Login sucessfully...');");

out.println("location='Home.jsp';");

out.println("</script>");

} catch (SQLException e)



{

// TODO Auto-generated catch block

e.printStackTrace();

}



}

else

{

PrintWriter out = response.getWriter();

response.setContentType("text/html");

out.println("<script type=\"text/javascript\">");

out.println("alert('Login error! Please retry...');");

out.println("location='Login.jsp';");

out.println("</script>");

}



}



if(request.getParameter("action").equalsIgnoreCase("Login"))

{

CustomerDao cd=new CustomerDao();



try

{

if(cd.checkLogin(request.getParameter("username"),request.getParameter("password"))==true)

{



HttpSession tlsession=request.getSession();

tlsession.setAttribute("custUsername",request.getParameter("username"));

PrintWriter out = response.getWriter();

response.setContentType("text/html");

out.println("<script type=\"text/javascript\">");

out.println("alert('Login sucessfully...');");

out.println("location='Home.jsp';");

out.println("</script>");

}

else

{

PrintWriter out = response.getWriter();

response.setContentType("text/html");

out.println("<script type=\"text/javascript\">");

out.println("alert('Login error! Please retry...');");

out.println("location='Login.jsp';");

out.println("</script>");

}

} catch (SQLException e) {

// TODO Auto-generated catch block

e.printStackTrace();
