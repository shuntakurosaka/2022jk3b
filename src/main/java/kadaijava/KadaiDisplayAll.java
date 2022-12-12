package kadaijava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.KadaiDataBean;
import dao.KadaiDAO;

@WebServlet("/displayAll")
public class KadaiDisplayAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KadaiDisplayAll() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String keyword = "";
		
		List<KadaiDataBean> list = new ArrayList<KadaiDataBean>();
		KadaiDAO dao = new KadaiDAO();
		list = dao.getAllData();
		
		
		request.setAttribute("data", list);
		request.setAttribute("allpage", dao.getMaxPage(keyword));
		request.getRequestDispatcher("kadaiDisplayAll.jsp").forward(request, response);
	}



}
