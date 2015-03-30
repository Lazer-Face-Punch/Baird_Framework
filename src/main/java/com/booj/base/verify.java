package com.booj.base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

//import org.jboss.netty.util.TimerTask;

public class verify extends TimerTask {
	


	public static void main(String[] args) {
		TimerTask tasknew = new verify();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate( tasknew, 0, 8000);
	
	/*TimerTask tasknew = new TimerScheduleFixedRate();
	Timer timer = new Timer();
	
	timer.scheduleAtFixedRate(tasknew, new Date(), 1000);*/
	
//System.out.println("Online:" + (testlnet("http://google.com")));
	}
	
	public static boolean testlnet(String site){
		 try {                                                                                                                                                                                                                                 
		        final URL url = new URL(site);                                                                                                                                                                                 
		        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
		        conn.connect();                                                                                                                                                                                                                   
		        return true;                                                                                                                                                                                                                      
		    } catch (MalformedURLException e) {                                                                                                                                                                                                   
		        throw new RuntimeException(e);                                                                                                                                                                                                    
		    } catch (IOException e) {                                                                                                                                                                                                             
		        return false;                                                                                                                                                                                                                     
		    }                             
		/*Socket sock = new Socket(Proxy.NO_PROXY);
		InetSocketAddress addr = new InetSocketAddress(site,80);
		try{
			sock.connect(addr);
			return true;
		}catch(IOException e){
			return false;
		}finally{
			try{sock.close();}
			catch(IOException e){}
		}*/
	}

	@Override
	public void run() {
		System.out.println("Online:" + (testlnet("http://google.com")));
		
	}

}
	
	


