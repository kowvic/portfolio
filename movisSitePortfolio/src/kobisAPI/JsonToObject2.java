package kobisAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.databind.ObjectMapper;





public class JsonToObject2 {
	
	public static void main(String[] args) {
	String urlStr = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"
			+ "?key=b528a0481070181cd539d38954517c81&itemPerPage=10&targetDt=";
	
	
	BoxOffice boxOffice = null;
	BufferedReader br = null;

	//���Ϲڽ����ǽ� ���� ��¥ ���ϱ�(������¥)
	Calendar calendar = new GregorianCalendar();
	calendar.add(Calendar.DATE, -1); // ���ó�¥�κ��� -1
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // ��¥ ���� 
	String yesterday = sdf.format(calendar.getTime()); // String���� ����
	
	System.out.println(yesterday);
	//���Ϲڽ����ǽ� ���� ��¥ ���ϱ�(������¥)END
	
	//�ڽ����ǽ� api ����, json �� �޾ƿ���
	try {
		URL url = new URL(urlStr+yesterday);
		HttpURLConnection urlconnection =(HttpURLConnection) url.openConnection();
		urlconnection.setRequestMethod("GET");
		
		
		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"utf-8"));
		String result="";
		String line;
		while((line = br.readLine())!= null) {
			result +=line+"\n";
		}
		
		//json�� �Ľ��Ͽ� javaObject�� �����ϱ�
		ObjectMapper mapper = new ObjectMapper();
		
		boxOffice =mapper.readValue(result, BoxOffice.class);
		DailyBoxOfficeList[] list = boxOffice.getBoxOfficeResult().getDailyBoxOfficeList();
		System.out.println(list[0]);
		  for(DailyBoxOfficeList dailyBoxOfficeList : list) {
//			  System.out.println(dailyBoxOfficeList);
			  System.out.println("��ȭ��� :"+dailyBoxOfficeList.getMovieNm());
			  System.out.println("������ :"+dailyBoxOfficeList.getOpenDt());
			  System.out.println("��ȭ�ڵ� :"+dailyBoxOfficeList.getMovieCd());			  
			 
		  }
	//�ڽ����ǽ� api ����, json �� �޾ƿ���END	  	  
		  
 
		  

	 
	} catch (Exception e) {
		System.out.println(e);
	}

	}
}

