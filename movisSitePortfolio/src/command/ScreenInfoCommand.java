package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScreenDAO;
import dto.ScreenDTO;

public class ScreenInfoCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	
		ArrayList<ScreenDTO> sDtos = new ArrayList<ScreenDTO>();
		ScreenDAO sDao = ScreenDAO.getInstance();

//		int currentPage=1;//���� ������ ��ȣ
//		int limit=5;//�� ȭ�鿡 ������ ���ڵ� ��

		
		sDtos = sDao.selectScreenInfo();
		
//		int count =sDao.countScreenInfo();
		
		request.setAttribute("sDtos", sDtos);
	
	}
}
