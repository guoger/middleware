package stocks;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressWarnings("serial")
public class StockTime implements java.io.Serializable {

	private String stockTime;
	transient private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyyMMddHHmmssz");
	
	public StockTime() {
		this.stockTime =
				this.timeStamp.format(Calendar.getInstance().getTime());
	}
	
	public StockTime(String time) {
		this.stockTime = time;
	}
	
	public void setTime(String time) {
		this.stockTime = time;
	}
	
	public String getTime() {
		return this.stockTime;
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		StockTime time = new StockTime();
		System.out.println(time.stockTime);
		Thread.sleep(2000);
		String anotherTime =
				time.timeStamp.format(Calendar.getInstance().getTime());
		time = new StockTime(anotherTime);
		System.out.println(time.stockTime);
	}


}
