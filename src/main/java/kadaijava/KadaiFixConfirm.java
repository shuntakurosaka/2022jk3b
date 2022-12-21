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

@WebServlet("/kadaiFixConfirm")
public class KadaiFixConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KadaiFixConfirm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String submit = (String)request.getParameter("submit");
		if(submit == null || !submit.equals("1")) {
			response.sendRedirect("displayAll");
			return;
		}
		
		List<String> list = new ArrayList();
		KadaiDataBean bean = new KadaiDataBean();
		String strId = request.getParameter("id");
		String strSimei = request.getParameter("simei");
		
		try {
			bean.setId(Integer.parseInt(strId));
		} catch(Exception e) {
			list.add("IDが数値ではありません。");
		}
		
		if(strSimei.isEmpty()) {
			list.add("氏名の値が未設定になっています。");
		} else {
			bean.setName(strSimei);
		}
		
		if(list.size() == 0) {
			KadaiDAO dao = new KadaiDAO();
			int result = dao.updateData(bean);
			if(result == 1) {
				list.add("修正完了しました。");
			} else {
				list.add("修正できませんでした。");
			}
		}
		request.setAttribute("message", list);
		request.getRequestDispatcher("sampleUpdateGo.jsp").forward(request, response);
	}
}
