package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;;

public class TimeTest {

	public TimeTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssz").format(Calendar.getInstance().getTime());
		Date date = new SimpleDateFormat("yyyyMMddHHmmssz", Locale.ENGLISH).parse(timeStamp);
		String temp = "haha:sdsd";
		String[] tt = temp.split(":");
		System.out.println(tt[1]);
	}

}
