package webController;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.BuyOrSell;
import com.fdmgroup.Company;
import com.fdmgroup.CompanyJpaDao;
import com.fdmgroup.Req;
import com.fdmgroup.RequestJpaDao;
import com.fdmgroup.Role;
import com.fdmgroup.UserJpaDao;

/**
 * Servlet implementation class NewRequestServlet
 */
@WebServlet("/newRequest")
public class NewRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRequestServlet() {
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

		String compname = (String) request.getParameter("company");
		
		int param1 = (Integer) request.getSession().getAttribute("uid");
		int company_ID = CompanyJpaDao.getCompany(new Company(compname, 1.0)).getCompany_ID(); 
		int param3 = Integer.valueOf(request.getParameter("howmany"));
		BuyOrSell bos = BuyOrSell.valueOf((String) request.getParameter("bs"));
		
		Req newReq = new Req(param1, company_ID, param3, new Date().toString(), bos);
		
		RequestJpaDao.newRequest(newReq);
		
		HttpSession ses = request.getSession();
		
		switch((Role) ses.getAttribute("role")){
		case SHAREHOLDER:
			request.getRequestDispatcher("sharehodler.jsp").forward(request, response);
			break;
		case HYBRID:
			request.getRequestDispatcher("hybrid.jsp").forward(request, response);
			break;
		}
	}

}
