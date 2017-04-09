package cz.cra.redcute.crahackredcute;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cz.cra.redcute.crahackredcute.database.DeviceDbAdapter;


public class AddingActivity extends AppCompatActivity {

    EditText devEUI;
    EditText place;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        devEUI = (EditText) findViewById(R.id.editText1);
        place = (EditText) findViewById(R.id.editText2);
        save = (Button) findViewById(R.id.buttonSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDevice();
                finish();
            }
        });

    }

    private void saveDevice() {
        String devEui = devEUI.getText().toString();
        if(devEui.length()!=16){
            Toast.makeText(getApplicationContext(), R.string.error_deveui_length, Toast.LENGTH_LONG).show();
            return;
        }
        String pla = place.getText().toString();

        DeviceDbAdapter boxesDbAdapter = new DeviceDbAdapter(getBaseContext());
        boxesDbAdapter.open();
        boxesDbAdapter.createDevice(devEui, pla);
        boxesDbAdapter.close();
    }
}
