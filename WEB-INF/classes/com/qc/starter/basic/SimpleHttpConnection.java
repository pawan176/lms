package com.qc.starter.basic;

/**
 * Created by Anupam on 20/10/2015.
 * Purpose: 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

/*import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;*/


public class SimpleHttpConnection {

	private static final String TAG = "SimpleHTTPConnection";
	private static final String ERROR = "Simple HTTP Connection Error";

	public static String sendByGET(String url) {
		InputStream is;
		StringBuilder sb;
		String result = ERROR;

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 20);
			sb = new StringBuilder();
			sb.append(reader.readLine());
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);

			}
			is.close();
			result = sb.toString();
		} catch (UnsupportedEncodingException e) {		
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	public static String hitHEAD(String url) {
		InputStream is;
		StringBuilder sb;
		String result = ERROR;
              
		try {
			HttpClient httpclient = new DefaultHttpClient();
			//HttpGet httpget = new HttpGet(url);
			HttpHead httpHead=new HttpHead(url);
			HttpResponse response = httpclient.execute(httpHead);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 20);
			sb = new StringBuilder();
			sb.append(reader.readLine());
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);

			}
			is.close();
			result = sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	
	// /////// Send By Post With Image And Data //////////
	public static String sendByPOST(HttpPost httppost) {
		InputStream is;
		StringBuilder sb;
		String result = ERROR;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			// HttpPost httppost = new HttpPost (url);
			// httppost.setEntity (new UrlEncodedFormEntity(data));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			is = entity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 20);
			sb = new StringBuilder();
			sb.append(reader.readLine());
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			result = sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	// With image upload using put
	public static String sendByPUT(HttpPut httpput)// (String url,
													// ArrayList<NameValuePair>
													// data)
	{
		InputStream is;
		StringBuilder sb;
		String result = ERROR;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpput);
			HttpEntity entity = response.getEntity();

			is = entity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 20);
			sb = new StringBuilder();
			sb.append(reader.readLine());
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			result = sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String sendByPOST(String url, ArrayList<NameValuePair> data) {
		InputStream is;
		StringBuilder sb;
		String result = ERROR;
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(data));
			
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			is = entity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 20);
			sb = new StringBuilder();
			sb.append(reader.readLine());
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);

			}
			is.close();
			result = sb.toString();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return null;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	public static String sendByPUT(String url, ArrayList<NameValuePair> data) {
		InputStream is;
		StringBuilder sb;
		String result = ERROR;

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPut httpput = new HttpPut(url);
			httpput.setEntity(new UrlEncodedFormEntity(data));
			HttpResponse response = httpclient.execute(httpput);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 20);
			sb = new StringBuilder();
			sb.append(reader.readLine());
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			result = sb.toString();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

	public static String sendJsonByPost(String url, final JSONObject data) {
		InputStream inputStream = null;
		String result = "";
		try {
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, 10000);
			HttpConnectionParams.setSoTimeout(params, 15000);
			HttpClient httpclient = new DefaultHttpClient();
			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);
			String json = "";
			// 4. convert JSONObject to JSON to String
			json = data.toString();
			// ** Alternative way to convert Person object to JSON string usin
			// Jackson Lib
			// ObjectMapper mapper = new ObjectMapper();
			// json = mapper.writeValueAsString(person);
			// 5. set json to StringEntity
			StringEntity se = new StringEntity(json.toString());
			// 6. set httpPost Entity
			httpPost.setEntity(se);
			// 7. Set some headers to inform server about the type of the
			// content
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			// 8. Execute POST request to the given URL
			HttpResponse httpResponse = httpclient.execute(httpPost);
			// 9. receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// 10. convert inputstream to string
			if (inputStream != null) {
				result = convertInputStreamToString(inputStream);

			} else
				result = "Did not work!";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}


	public static String sendJsonByPost2(String url, final JSONObject data) {
		InputStream inputStreamm = null;
		String result = "";
		try {
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, 10000);
			HttpConnectionParams.setSoTimeout(params, 150);
			HttpClient httpclient = new DefaultHttpClient();
			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);
			String json = "";
			// 4. convert JSONObject to JSON to String
			json = data.toString();
			// ** Alternative way to convert Person object to JSON string usin
			// Jackson Lib
			// ObjectMapper mapper = new ObjectMapper();
			// json = mapper.writeValueAsString(person);
			// 5. set json to StringEntity
			StringEntity se = new StringEntity(json.toString());
			// 6. set httpPost Entity
			httpPost.setEntity(se);
			// 7. Set some headers to inform server about the type of the
			// content
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			// 8. Execute POST request to the given URL
			HttpResponse httpResponsee = httpclient.execute(httpPost);
			// 9. receive response as inputStream
			inputStreamm = httpResponsee.getEntity().getContent();

			// 10. convert inputstream to string
			if (inputStreamm != null) {
				result = convertInputStreamToString(inputStreamm);

			} else
				result = "Did not work!";

		} catch (Exception e) {
		}

		return result;

	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = new String();
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	public static ArrayList<NameValuePair> generateParams(String[] keys,
			String[] values) {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		for (int i = 0; i < keys.length; i++) {
			params.add(new BasicNameValuePair(keys[i], values[i]));
		}

		return params;
	}

	public static String buildGetUrl(String url, String[] keys, String[] values) {
		if (!url.endsWith("?"))
			url += "?";
		url += URLEncodedUtils.format(generateParams(keys, values), "utf-8");

		return url;
	}

	
	
}
