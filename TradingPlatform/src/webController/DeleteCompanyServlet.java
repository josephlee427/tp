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
 * Servlet implementation class DeleteCompanyServlet
 */
@WebServlet("/delcomp")
public class DeleteCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCompanyServlet() {
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
		
		Company company = new Company(
				request.getParameter("companyName"),
				1
				);
		
		CompanyJpaDao.deleteCompany(company);
		
		HttpSession ses = request.getSession();
		
		switch((Role) ses.getAttribute("role")){
		case BROKER:
		case COMPANY:
			request.getRequestDispatcher("company.jsp").forward(request, response);
			break;
		case HYBRID:
			request.getRequestDispatcher("hybrid.jsp").forward(request, response);
			break;
		}
		
//		doGet(request, response);
	}

}
