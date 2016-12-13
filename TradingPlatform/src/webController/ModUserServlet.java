package webController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.Role;
import com.fdmgroup.User;
import com.fdmgroup.UserJpaDao;

/**
 * Servlet implementation class ModUserServlet
 */
@WebServlet("/moduser")
public class ModUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		User modUser = new User(
				(String) request.getParameter("moduser"),
				(String) request.getParameter("newpass"),
				(String) request.getParameter("newfirstname"),
				(String) request.getParameter("newlastname"),
				Role.valueOf((String) request.getParameter("type"))
				);
		
		UserJpaDao.modUser(modUser);
		
		//TODO: moduser doesnt persist roles properly. Fix that.
		
		request.getRequestDispatcher("admin.jsp").forward(request, response);;
		
		
		
	}
	

}
