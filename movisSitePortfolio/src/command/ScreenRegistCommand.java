package command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScreenDAO;


public class ScreenRegistCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String msg = "", url="";
		
		String movieCdTm = request.getParameter("movieCdTm");//<%=movieNm %>,<%=movieCd %>,<%=movieTime %>
		
		String[] str= movieCdTm.split(",");
		
		int movieCd=Integer.parseInt(str[0]);//��ȭ�ڵ� ex)20199842
		int movieTime=Integer.parseInt(str[1]);//��ȭ�ð�(��) ex)120, 128
		String theaterCd = request.getParameter("theater");//��ȭ�� t1,t2,t3
		String screenDate = request.getParameter("reservDay");//��¥ex)2020-1-1		
		String roomCd = request.getParameter("room");//�󿵰�r1,r2,r3,r4,r5		
		String screenStart = request.getParameter("startTime");//���۽ð� ex)HH:mm
		String screenEnd = null;
		
		System.out.println(movieCd);//��ȭ�ڵ�
		System.out.println(movieTime);//�󿵽ð�
		System.out.println(screenDate);//������
		System.out.println(roomCd);//�󿵰���ȣ
		System.out.println(screenStart);//���۽ð�
		
		SimpleDateFormat sdt = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		ScreenDAO sDao = ScreenDAO.getInstance();
		
			try {
				Date dscreenStart = sdt.parse(screenStart);
				cal.setTime(dscreenStart);
				cal.add(Calendar.MINUTE, movieTime);
				screenEnd = sdt.format(cal.getTime());
				System.out.println(screenEnd);//����ð�
			
				int n =sDao.registScreen(theaterCd, roomCd, movieCd, screenDate, screenStart, screenEnd);
				
				if(n==1) {
					msg="�������� ��ϵǾ����ϴ�.";
					url="adminRegistScreen.do";
				}else {
					msg="�������� ��� ����.";
					url="adminRegistScreen.do";
				}
			} catch (ParseException e) {
				msg="������ ��� ����.";
				url="adminRegistScreen.do";

			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
	}

}
