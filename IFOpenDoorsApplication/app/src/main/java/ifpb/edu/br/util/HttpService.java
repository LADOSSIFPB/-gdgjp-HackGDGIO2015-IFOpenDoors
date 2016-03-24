package ifpb.edu.br.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.util.Log;
import ifpb.edu.br.util.Constantes;

public class HttpService {

	// URL to get JSON Array
	private static String url = Constantes.URL_WEB_SERVICE;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		HttpService.url = url;
	}

	// constructor
	public HttpService() {
	}

	private String addPathToUrl(String service, String pathValue) {

		if (!service.endsWith("/")) {
			service += "/";
		}

		service += pathValue;

		return service;
	}

	private String addParamsToUrl(String service, Map<String, String> parametros) {

		List<NameValuePair> params = new LinkedList<NameValuePair>();

		if (!service.endsWith("?")) {
			service += "?";
		}

		// Iterar os valores do mapa.
		for (Map.Entry<String, String> entry : parametros.entrySet()) {

			params.add(new BasicNameValuePair(entry.getKey(), String
					.valueOf(entry.getValue())));
		}

		String paramString = URLEncodedUtils.format(params, HTTP.UTF_8);

		service += paramString;

		return service;
	}

	/**
	 * Enviar requisição via HTTP GET com valor de path.
	 * 
	 * @param service
	 * @param pathValue
	 * @return
	 */
	public HttpResponse sendGETRequest(String service, String pathValue) {
		return sendGETRequest(addPathToUrl(service, pathValue));
	}

	/**
	 * Enviar requisicao via HTTP GET com parâmetros.
	 * 
	 * @param service
	 * @param parametros
	 * @return
	 */
	public HttpResponse sendGETRequest(String service,
			Map<String, String> parametros) {

		return sendGETRequest(addParamsToUrl(service, parametros));
	}

	/**
	 * Enviar uma requisicao ao servidor via HTTP GET.
	 * 
	 * @param service
	 * @return
	 */
	public HttpResponse sendGETRequest(String service) {

		HttpResponse response = null;

		HttpGet httpGet = new HttpGet(url + service);

		try {

			HttpClient httpClient = new DefaultHttpClient();

			response = httpClient.execute(httpGet);

		} catch (ClientProtocolException e) {
			Log.e("AsyncTaskKJson", "Error converting result " + e.toString());
		} catch (IOException e) {
			Log.e("AsyncTaskKJson", "Error converting result " + e.toString());
		}

		return response;
	}

	public static HttpResponse sendJsonPostRequest(String service,
			JSONObject json) throws IOException {

		// Response
		HttpResponse response = null;

		// Create a new HttpClient and Post Header
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url + service);

		try {

			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			StringEntity se = new StringEntity(json.toString(), HTTP.UTF_8);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=" + HTTP.UTF_8));

			httpPost.setEntity(se);

			response = httpClient.execute(httpPost);

		} catch (UnsupportedEncodingException e) {

			Log.i("AsyncTaskKJson", e.getMessage());

		} catch (ClientProtocolException e) {

			Log.i("AsyncTaskKJson", e.getMessage());
		}

		return response;
	}

	public HttpResponse sendParamPostRequest(String service,
			List<NameValuePair> nameValuePairs) {

		// Response
		HttpResponse response = null;

		// Create a new HttpClient and Post Header
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(url + service);

		try {
			// Add data
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			response = httpClient.execute(httppost);

		} catch (UnsupportedEncodingException e) {

			Log.i("AsyncTaskKJson", e.getMessage());

		} catch (ClientProtocolException e) {

			Log.i("AsyncTaskKJson", e.getMessage());

		} catch (IOException e) {

			Log.i("AsyncTaskKJson", e.getMessage());
		}

		return response;
	}
}
