package com.example.catering;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class sheetsTask extends AsyncTask<String, String, List<List<Object>>> {


    private static final String APPLICATION_NAME = "Invite List";
    private static final String API_KEY = "AIzaSyAz6V7rHfi0Wrem6HI2S52XLreUCtQu9_A";
    private Exception exception;
    Context context;

    sheetsTask(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    protected List<List<Object>> doInBackground(String... params) {
        try {

            // call you method here
            return getValues(params[0], params[1]);

        } catch (Exception e) {
            this.exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<List<Object>> result){
        System.out.print(result);
        Intent intent = new Intent(context, viewPagerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("result_list", (Serializable) result);
        context.startActivity(intent);
        // update the UI with your data
    }


    private Sheets getSheets() {
        NetHttpTransport transport = new NetHttpTransport.Builder().build();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        HttpRequestInitializer httpRequestInitializer = request -> {
            request.setInterceptor(intercepted -> intercepted.getUrl().set("key", API_KEY));
        };

        return new Sheets.Builder(transport, jsonFactory, httpRequestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public List<List<Object>> getValues(String spreadsheetId, String range) throws IOException {
        return getSheets()
                .spreadsheets()
                .values()
                .get(spreadsheetId, range)
                .execute()
                .getValues();
    }
}
