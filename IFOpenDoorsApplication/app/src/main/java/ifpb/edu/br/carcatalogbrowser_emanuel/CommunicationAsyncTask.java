package ifpb.edu.br.carcatalogbrowser_emanuel;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import ifpb.edu.br.classes.Door;
import ifpb.edu.br.util.HttpService;
import ifpb.edu.br.util.HttpUtil;

import com.google.gson.Gson;

import java.io.IOException;

public class CommunicationAsyncTask extends AsyncTask<Void, Integer, String> {

	private Activity activity;
	private String path;
	private int numberDoor;

	public CommunicationAsyncTask(Activity activity, String path, int numberDoor) {
		this.activity = activity;
		this.path = path;
		this.numberDoor = numberDoor;
	}

	@Override
	protected String doInBackground(Void... arg0) {
		String json = null;
		Door door = new Door();
        door.setNumber(numberDoor);
        JSONObject jsonObject;
        HttpService httpService;
        HttpResponse response = null;

		Gson gson = new Gson();
        json = gson.toJson(door);

        try {
            jsonObject = new JSONObject(json);

            // Enviar a requisicao HTTP via POST.
            httpService = new HttpService();
            response = httpService.sendJsonPostRequest(
                    this.path, jsonObject);

        } catch (JSONException e) {

        } catch (IOException e) {

        }

        json = HttpUtil.entityToString(response);

		door = gson.fromJson(json, Door.class);

		return door.getMensage();
	}
}
