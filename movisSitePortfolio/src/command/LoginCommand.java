package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;

public class LoginCommand implements Command{
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) {
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	MemberDAO mDao = MemberDAO.getInstance();
	
	int result = mDao.CheckMember(id, pw);
	
		if(result!=MemberDAO.loginOk) {
			System.out.println("DB�� ���� ȸ�����̵��Դϴ�.");
		}else {
			System.out.println("�����̵�� �α����մϴ�.");
			request.setAttribute("memId", id);//�����Է��� id�� memId�� �̸����� request�� ������.
		}
	}
}

