package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.KadaiDataBean;
import conn.KadaiConn;

public class KadaiDAO extends KadaiConn implements Serializable{
	private static final long serialVesionUID = 1L;
		
	Connection con = null;
	Statement st = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String sql = null;
	private static final int MAXROW = 10;
		
	public KadaiDAO() {
		con = conn();
	}
	
	//----ページ数カウント
	public int getMaxPage(String keyword) {		//nullの場合全件表示したい
		if(keyword == null || keyword == "") {
			keyword = "";
		}
		int allPage = -1;
		try {
			if(keyword == "") {
				sql ="select count(*) as cnt from gakusei_master";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				rs.next();
			} else {
				sql = "select count(*) as cnt from gakusei_master where Name like ? or Furigana like ? or Student_ID = ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, "%" + keyword + "%");
				pst.setString(2,"%" + keyword + "%");
				pst.setString(3, keyword);
				rs = pst.executeQuery();
				rs.next();
			}
			int records = rs.getInt("cnt");
			allPage = (records - 1) / MAXROW + 1;
		} catch (Exception e) {
		    e.printStackTrace();
		    allPage = 0;
		}
		return allPage;
	}
	
	//----最初に表示するデータ（ID,名前,ふりがな）
	public List<KadaiDataBean> getAllData(int page , String keyword) {
		List<KadaiDataBean> data = new ArrayList<KadaiDataBean>();
		try {
			if(keyword == null || keyword == "") {
				keyword = "";
			}
			sql = "select * from gakusei_master where Name like ? or Furigana like ? or Student_ID = ? limit ? , ?";
			pst = con.prepareStatement(sql);
			int baseRow = (page - 1) * MAXROW;
			pst.setString(1,"%" + keyword + "%");
			pst.setString(2,"%" + keyword + "%");
			pst.setString(3, keyword);
			pst.setInt(4, baseRow);
			pst.setInt(5, MAXROW);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("Student_ID");
				String name = rs.getString("Name");
				String furigana = rs.getString("Furigana");
				KadaiDataBean b = new KadaiDataBean();
				b.setId(id);
				b.setName(name);
				b.setFurigana(furigana);
				data.add(b);
			}
		} catch(Exception e) {
			data = null;
		}
		return data;
	}
	
	public KadaiDataBean getOneRec(String id) {
		KadaiDataBean data = new KadaiDataBean();
		try {
			sql = "select * from gakusei_master where Student_ID =?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(id));
			rs = pst.executeQuery();
			rs.next();
			KadaiDataBean b = new KadaiDataBean();
			b.setId(rs.getInt("Student_ID"));
			b.setStatus(rs.getInt("Status"));
			b.setStatusEnterDate(rs.getString("Status_EnterDate"));
			b.setName(rs.getString("Name"));
			b.setFurigana(rs.getString("Furigana"));
			b.setBirth(rs.getString("Birth"));
			b.setPostNumber(rs.getString("PostNumber"));
			b.setAddress(rs.getString("Address"));
			b.setTellNumber(rs.getString("TellNumber"));
			if(rs.getString("Mail") != null) {
				b.setMail(rs.getString("Mail"));
			} else {
				b.setMail("");
			}
			b.setParentName(rs.getString("Parent_Name"));
			b.setParentFurigana(rs.getString("Parent_Furigana"));
			b.setParentPostNumber(rs.getString("Parent_PostNumber"));
			b.setParentAddress(rs.getString("Parent_Address"));
			b.setParentTellNumber(rs.getString("Parent_TellNumber"));
			if(rs.getString("Mail") != null) {
				b.setParentMail(rs.getString("Parent_Mail"));
			} else {
				b.setParentMail("");
			}
		} catch(Exception e) {
			e.printStackTrace();
			data = null;
		}
		return data;
	}
	
	//----詳細表示用のひとりの全データ
	public List<KadaiDataBean> getDetailData(int comeid) {
		List<KadaiDataBean> data = new ArrayList<KadaiDataBean>();
		try {
			sql = "select * from gakusei_master where Student_ID = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1,comeid);
			rs = pst.executeQuery();
			
			rs.next();
			KadaiDataBean b = new KadaiDataBean();
			b.setId(rs.getInt("Student_ID"));
			b.setStatus(rs.getInt("Status"));
			b.setStatusEnterDate(rs.getString("Status_EnterDate"));
			b.setName(rs.getString("Name"));
			b.setFurigana(rs.getString("Furigana"));
			b.setBirth(rs.getString("Birth"));
			b.setPostNumber(rs.getString("PostNumber"));
			b.setAddress(rs.getString("Address"));
			b.setTellNumber(rs.getString("TellNumber"));
			if(rs.getString("Mail") != null) {
				b.setMail(rs.getString("Mail"));
			} else {
				b.setMail("");
			}
			b.setParentName(rs.getString("Parent_Name"));
			b.setParentFurigana(rs.getString("Parent_Furigana"));
			b.setParentPostNumber(rs.getString("Parent_PostNumber"));
			b.setParentAddress(rs.getString("Parent_Address"));
			b.setParentTellNumber(rs.getString("Parent_TellNumber"));
			if(rs.getString("Mail") != null) {
				b.setParentMail(rs.getString("Parent_Mail"));
			} else {
				b.setParentMail("");
			}
			data.add(b);
		} catch(Exception e) {
			e.printStackTrace();
			data = null;
		}
		return data;
	}
}
