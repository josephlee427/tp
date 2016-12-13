package webController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.Company;
import com.fdmgroup.CompanyJpaDao;
import com.fdmgroup.Role;

/**
 * Servlet implementation class ModCompanyServlet
 */
@WebServlet("/modcomp")
public class ModCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModCompanyServlet() {
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
//		doGet(request, response);
		
		Company company = new Company(request.getParameter("modcompany"),1);
		
		Company newcomp = new Company(request.getParameter("newCompanyName"),1);
		
		CompanyJpaDao.modCompany(company, newcomp);
		
		HttpSession ses = request.getSession();
		
		//TODO: make passed vars persist betw pages
		
		switch((Role) ses.getAttribute("role")){
		case BROKER:
		case COMPANY:
			request.getRequestDispatcher("company.jsp").forward(request, response);
			break;
		case HYBRID:
			request.getRequestDispatcher("hybrid.jsp").forward(request, response);
			break;
		}
		
	}
}
