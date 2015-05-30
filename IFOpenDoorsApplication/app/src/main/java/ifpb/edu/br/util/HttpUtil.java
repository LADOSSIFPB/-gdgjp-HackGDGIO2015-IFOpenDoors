package ifpb.edu.br.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class HttpUtil {

	public static String entityToString(HttpResponse response) {

		HttpEntity httpEntity = response.getEntity();

		StringBuilder builder = new StringBuilder();

		try {
			InputStream content = httpEntity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					content, HTTP.UTF_8), 8);

			String line;

			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

			content.close();

		} catch (IllegalStateException e) {
			Log.e("AsyncTaskKJson", "Error converting result " + e.toString());
		} catch (IOException e) {
			Log.e("AsyncTaskKJson", "Error converting result " + e.toString());
		}
		
		return builder.toString();
	}

	public static boolean isConnect(Context context) {

		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo info = connMgr.getActiveNetworkInfo();

		return (info != null && info.isConnected());
	}
}
