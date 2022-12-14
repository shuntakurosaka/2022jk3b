package kadaijava;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class KadaiSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KadaiSearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String keyword;
		String strpage;
		int page = 1;
		try {
			keyword = (String)request.getParameter("keyword");
			strpage = (String)request.getParameter("page");
			if (strpage != null) {
				try {
					page = Integer.parseInt(strpage);
				} catch (Exception e) {
					page = 1;
				}
			}
			
			
			request.setAttribute("keyword", keyword);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/displayAll").forward(request, response);	
		} catch(Exception e) {
			response.getWriter().print(e.getMessage());
		}
	}


}
