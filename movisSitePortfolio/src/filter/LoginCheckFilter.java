package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{
	
	@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			
			//���� ���� ����
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpSession session = httpRequest.getSession();
			String memId = (String)session.getAttribute("memId");
			String adId = (String) session.getAttribute("adId");
			//����
			boolean login=false;
			boolean adLogin = false;
			if(session!=null) {
				System.out.println("���ǿ� ȸ�����̵� ������ֽ��ϴ�.");
				if(memId!=null) {
					System.out.println("����� ȸ�����̵��"+memId+"�Դϴ�.");
					login=true;
				}else if(adId!=null) {
					System.out.println("����� ȸ�����̵�� "+adId+"�Դϴ�.");
					adLogin = true;
				}
			}
			
			System.out.println("���͸� �Ѿ�ϴ�.");
			if(login) {
				chain.doFilter(request, response);
			}else if(adLogin){
				chain.doFilter(request, response);
				
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.do");
				dispatcher.forward(request, response);
			}
		}
	
//@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//	
//		//������ ���ǰ�ü�� ������ �Ѵ�.
//		HttpServletRequest httpRequest = (HttpServletRequest)request;
//		HttpSession session = httpRequest.getSession();
//		
//		boolean login=false;
//
//		//����
//		if(session!=null) {
//			if(session.getAttribute("loginId")!=null) {
//				login = true;
//			}
//		}
//		if(login) {
//			chain.doFilter(request, response);//ü�ΰ� �̵�, �������� �ڿ����� �̵�
//		}else {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginForm.jsp");
//			dispatcher.forward(request, response);
//		}
//	}


}
