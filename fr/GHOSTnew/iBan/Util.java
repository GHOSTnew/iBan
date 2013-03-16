package fr.GHOSTnew.iBan;


 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.net.URL;
 import javax.net.ssl.HttpsURLConnection;

public class Util {
	public static String excutePost(String targetURL, String urlParameters)
	   {
	     HttpsURLConnection connection = null;
	     try
	     {
	       URL url = new URL(targetURL);
	       connection = (HttpsURLConnection)url.openConnection();
	       connection.setRequestMethod("POST");
	       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	 
	       connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
	       connection.setRequestProperty("Content-Language", "en-US");
	 
	       connection.setUseCaches(false);
	       connection.setDoInput(true);
	       connection.setDoOutput(true);
	 
	       connection.connect();
	       
	 
	       DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	       wr.writeBytes(urlParameters);
	       wr.flush();
	       wr.close();
	 
	       InputStream is = connection.getInputStream();
	       BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	 
	       StringBuffer response = new StringBuffer();
	       String line;
	       while ((line = rd.readLine()) != null)
	       {
	        response.append(line);
	         response.append('\r');
	       }
	       rd.close();
	 
	       String str1 = response.toString();
	       return str1;
	     }
	     catch (Exception e)
	     {
	       e.printStackTrace();
	       return null;
	     }
	     finally
	     {
	       if (connection != null)
	         connection.disconnect();
	     }
	   }

}
