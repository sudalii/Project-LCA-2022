package controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;	// time.Date 대신 LocalDate를 권장함, Date format은 String으로 처리하는 것이 편함

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDAO;
import model.UserVO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/insert")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("project-info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			UserVO vo = new UserVO();
			
			//vo.setId(Integer.parseInt(request.getParameter("id")));
			vo.setProjectName(request.getParameter("project_name"));
			vo.setProductName(request.getParameter("product_name"));
			vo.setCreateDate(LocalDateTime.now().format(formatter));
			vo.setFu(request.getParameter("fu"));
			vo.setProductAmount(Float.parseFloat(request.getParameter("product_amount")));
			
			UserDAO dao = new UserDAO();
			dao.userInfoInsert(vo);
			request.setAttribute("vo", vo);	// 데이터 담기
			
		}catch(Exception e) {
			// log.error(e.getMessage()); // log4j 사용 시 가능
			System.out.println("In doPost()\n");
			System.out.println(e.getMessage());
		}
		response.sendRedirect("data-make-process.jsp");
	}

}
