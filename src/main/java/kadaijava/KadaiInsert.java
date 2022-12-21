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

@WebServlet("/insert")
public class KadaiInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KadaiInsert() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		String strId = (String)request.getParameter("id");
		String strSimei = (String)request.getParameter("simei");

		List<String> message = new ArrayList<String>();
		
		boolean errSw = false;
		int id = -1;
		if(strId == null || strId == "") {
			message.add("IDが入力されていません。");
			errSw = true;
		} else {
			try {
				id = Integer.parseInt(strId);
			} catch(Exception e ) {
				message.add("IDが数値ではありません。");
				errSw = true;
			}
		}
		if(strSimei == null || strSimei == "") {
			message.add("氏名が入力されていません。");
			errSw = true;
		}
		
		if(!errSw) {
			KadaiDAO dao = new KadaiDAO();
			KadaiDataBean bean = dao.getOneRec(strId);
			if(bean == null) {
				bean = new KadaiDataBean();
				bean.setId(id);
				bean.setName(strSimei);
				int result = dao.insertData(bean);
				if(result == 0) {
					message.add("登録できませんでした。");
				} else {
					message.add("登録完了しました。");
				}
			} else {
				message.add("IDが重複しています。");
			}
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("sampleInsert.jsp").forward(request, response);
		}
	}
}