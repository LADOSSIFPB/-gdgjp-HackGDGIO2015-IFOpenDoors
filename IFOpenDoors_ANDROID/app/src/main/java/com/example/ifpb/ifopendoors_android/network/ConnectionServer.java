package com.example.ifpb.ifopendoors_android.network;

/**
 * Created by IFPB on 27/09/2016.
 */
import java.io.IOException;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConnectionServer {

    private static final String[] URL_BASES =
            {
                    "http://ladoss.com.br:8773/NutrIF_Service_homologacao/",
                    "http://192.168.1.245:8773/NutrIF_Service/"
            };

    private static APIService service;
    private static ConnectionServer ourInstance = new ConnectionServer();

    public static ConnectionServer getInstance() {
        return ourInstance;
    }

    public APIService getService() {
        return service;
    }

    private ConnectionServer() {
        updateServiceAdress();
    }

    public void updateServiceAdress() {

        for (String endereco : URL_BASES) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(endereco)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(APIService.class);

            try {
                Response<Void> response = service.status().execute();
                if (response.isSuccessful())
                    break;
            } catch (IOException e) {
                continue;
            }
        }
    }

}
