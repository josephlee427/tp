package webController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.BuyOrSell;
import com.fdmgroup.Role;
import com.fdmgroup.User;
import com.fdmgroup.UserJpaDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User(
				(String) request.getParameter("newuser"),
				(String) request.getParameter("newpass"),
				Role.valueOf((String) request.getParameter("type")));
		
		UserJpaDao.newUser(user);
		
		request.getRequestDispatcher("admin.jsp").forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String username=request.getParameter("uname");
		String password=request.getParameter("pass");
		
		UserJpaDao.newUser(new User(username, password));
		
		request.getRequestDispatcher("login.html").forward(request, response);
/*		PrintWriter writer = response.getWriter();
		
		System.out.println(username + " " + password);*/
		
	}

}
