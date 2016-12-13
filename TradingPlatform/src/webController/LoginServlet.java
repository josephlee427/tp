package webController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.User;
import com.fdmgroup.UserJpaDao;
import com.fdmgroup.Company;
import com.fdmgroup.CompanyJpaDao;
import com.fdmgroup.Req;
import com.fdmgroup.RequestJpaDao;
import com.fdmgroup.Role;
import com.fdmgroup.Trade;
import com.fdmgroup.TradeJpaDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("thing");
		
//		doGet(request, response);
		
		String username=request.getParameter("uname");
		String password=request.getParameter("pass");
		
		//TODO: add password authentication
		
		User user = UserJpaDao.getUser(new User(username, password));
		
		if(!user.getPassword().equals(password)){
			request.getRequestDispatcher("login.html").forward(request, response);
		}

		HttpSession thing = request.getSession();
		
		thing.setAttribute("uid", user.getUserID());
		thing.setAttribute("role", UserJpaDao.getRoles(user));
		
		String profile = user.toString();
		
		List<Req> reqHist = RequestJpaDao.matchRequests(user);
		List<Trade> tradeHist = TradeJpaDao.matchTrades(user);
		List<Company> allComps = CompanyJpaDao.getAllCompanies();
		
		request.setAttribute("reqHist", reqHist);
		request.setAttribute("tradeHist", tradeHist);
		request.setAttribute("profile", profile);
		request.setAttribute("allComps", allComps);
		
		switch(UserJpaDao.getRoles(user)){
			case SHAREHOLDER:
				request.getRequestDispatcher("shareholder.jsp").forward(request, response);
				break;
			case ADMIN:
				request.setAttribute("allusers", UserJpaDao.getAllUsers());
				request.setAttribute("allreqhist", RequestJpaDao.getAllRequests());
				request.setAttribute("alltradehist", TradeJpaDao.getAllTrades());
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				break;
			case COMPANY:
				request.getRequestDispatcher("company.jsp").forward(request, response);
				break;
			case BROKER:
				request.getRequestDispatcher("company.jsp").forward(request, response);
				break;
			case HYBRID:
				request.getRequestDispatcher("hybrid.jsp").forward(request, response);
				break;
			default:
				request.getRequestDispatcher("login.html").forward(request, response);
				break;
		}
		
/*		PrintWriter writer = response.getWriter();
		
		System.out.println(username + " " + password);*/
		
	}

}
