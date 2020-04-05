package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDAO;

public class AdLoginCommand implements Command{

	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		AdminDAO aDao = AdminDAO.getInstance();
		int result = aDao.CheckAdmin(id, pw);
		if(result!=AdminDAO.adLoginOk) {
			System.out.println("db�� ���� �������Դϴ�.");
		}else {
			System.out.println("�����ھ��̵�� �α����մϴ�.");
			request.setAttribute("adId", id);
		}
	}
}
