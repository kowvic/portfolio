package kobisAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;




public class JsonToObject {
	
	public JsonToObject() {
		
	}
	
	public String getYesterday() {
		//���Ϲڽ����ǽ� ���� ��¥ ���ϱ�(������¥)
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -1); // ���ó�¥�κ��� -1
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // ��¥ ���� 
		String yesterday = sdf.format(calendar.getTime()); // String���� ����
		
		return yesterday;
	}
		//uri: ���� �ڿ� �ĺ���(identifier), ���ͳ� ���� �ڿ��� �ĺ��ϱ� ���� ���ڿ�
		//url: ���� �ڿ� ��ġ, ��Ʈ��ũ �󿡼� �ڿ��� ����ִ��� �˷��ֱ� ���� �Ծ�. ���ͳݻ��� �ڿ� ��ġ
	/*
	 * http://tstory.com = �� �ּҴ� http://tstory.com��� ������ ��Ÿ���� ������ url�̸鼭 uri
	 * http://tstory.com/skin =������ http://tstory.com, skin�� ���ͳݻ��� �ڿ� ��ġ, uri�̸鼭 url
	 * http://tstory.com/?q=uri = q=uri�� �ĺ���(identifier), �ּҴ� uri������ url�� �ƴϰ� �ȴ�.
	 * 
	 * �ĺ��ڿ� ���� ���� ���Ϸ� �������� �ʱ� ������ url�� �ƴϴ�. ������ �� �ּҰ� html ������ ����Ű�� ������ uri�� �ȴ�.
	 * */
	public ArrayList<DailyBoxOfficeList> parsingObject(String yesterday) {
		
		ArrayList<DailyBoxOfficeList> dlists= new ArrayList<DailyBoxOfficeList>();
		String uriStr = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"
				+ "?key=b528a0481070181cd539d38954517c81&itemPerPage=10&targetDt=";

		BoxOffice boxOffice = null;
		BufferedReader br = null;
		
		//�ڽ����ǽ� api ����, json �� �޾ƿ���
		try {
			URL url = new URL(uriStr+yesterday);
			HttpURLConnection urlconnection =(HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			
			
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"utf-8"));
			String result="";
			String line;
			while((line = br.readLine())!= null) {
				result +=line+"\n";
			}
			
			//json�� �Ľ��Ͽ� javaObject�� �����ϱ�
			ObjectMapper  mapper = new ObjectMapper();
			
			boxOffice =mapper.readValue(result, BoxOffice.class);
			DailyBoxOfficeList[] list = boxOffice.getBoxOfficeResult().getDailyBoxOfficeList();
		
	
			for(DailyBoxOfficeList dlist : list) {
//			  System.out.println(dailyBoxOfficeList);
				
				String movieNm = dlist.getMovieNm();//��ȭ�̸�
				String openDt = dlist.getOpenDt();//������
				String movieCd = dlist.getMovieCd();//��ȭ�ڵ�
				String rank = dlist.getRank();//��ȭ����
				String salesAmt = dlist.getSalesAmt();//���ϸ���
				String salesAcc = dlist.getSalesAcc();//��������
				String audiAcc = dlist.getAudiAcc();//��������
				
				dlist = new DailyBoxOfficeList(salesAcc, movieNm, salesAmt, movieCd, rank, openDt, audiAcc);			
				dlists.add(dlist);
			
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dlists;
		
		
	}

	
}

