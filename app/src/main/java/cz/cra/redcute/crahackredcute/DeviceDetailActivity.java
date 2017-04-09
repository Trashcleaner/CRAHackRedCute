package cz.cra.redcute.crahackredcute;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cz.cra.redcute.crahackredcute.gson.GsonMessage;
import cz.cra.redcute.crahackredcute.gson.GsonRecords;
import cz.cra.redcute.crahackredcute.model.Device;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DeviceDetailActivity extends AppCompatActivity {
    final private static String TAG = "DeviceDetailAct";
    public static final String LOCATION_LAT = "location latitude";
    public static final String LOCATION_LON = "location longitude";

    private TextView textView;

    private Button buttonUpdate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);

        textView = (TextView)findViewById(R.id.detail_text);
        buttonUpdate = (Button)findViewById(R.id.button_update);

        useApiToGet();
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useApiToGet();
            }
        });



    }


    private void useApiToGet(){
        String deveui = getIntent().getStringExtra(Device.DEVEUI);
        CraApiEndpoints craApiEndpoints = CraApiEndpoints.retrofit.create(CraApiEndpoints.class);
        Call<String> call = craApiEndpoints.getResponse(deveui, CraApiEndpoints.TOKEN);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.message().equals("OK")){
                    GsonRecords records = new Gson().fromJson(response.body(), GsonRecords.class);
                    ArrayList<GsonMessage> messages = records.getRecords();
                    textView.setText(messages.get(0).getPayloadHex());
                    ParsityParser parsityParser = new ParsityParser(messages.get(0).getPayloadHex());
                    textView.setText(parsityParser.getParsedString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                textView.setText("Failure");
            }
        });
    }


}
