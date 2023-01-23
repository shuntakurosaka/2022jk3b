package kadaijava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.KadaiDataBean;
import dao.KadaiDAO;

@WebServlet("/fixEnter")
public class KadaiFixEnter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KadaiFixEnter() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession kadaiSession = request.getSession();
		KadaiDAO dao = new KadaiDAO();
		KadaiDataBean changedBean = (KadaiDataBean)kadaiSession.getAttribute("changedBean");
		List<String> errList = new ArrayList<String>();
		
		int result = dao.fixData(changedBean);
		if(result == 1) {
			response.sendRedirect("kadaiFixComplete.jsp");
		} else {
			errList.add("修正に失敗しました。");
			request.setAttribute("errList", errList);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
