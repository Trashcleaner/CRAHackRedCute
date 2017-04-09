package cz.cra.redcute.crahackredcute;

import android.util.Log;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by obrusvit on 7.4.17.
 */

public class ParsityParser {
    String niceString;
    String parsedString;

    private static HashMap<Character, String> sensorCodes;

    static {
        sensorCodes = new HashMap<>();
        sensorCodes.put('1', "Temperature [°C]");
        sensorCodes.put('2', "Humidity [%]");
        sensorCodes.put('3', "Tilt [°]");
        sensorCodes.put('4', "GPS [lat/lon]");
        sensorCodes.put('5', "Laser [On/Off]");
        sensorCodes.put('6', "Distance [cm]");
        sensorCodes.put('7', "Pitch [°]");
        sensorCodes.put('8', "Roll [°]");
    }

    HashMap<String, Float> hashMap;


    public ParsityParser(String stringToParse) {

        niceString = stringToParse.toLowerCase();
        String[] myKids = niceString.split("a");

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < myKids.length; i++) {
            //Log.d("DFADF", myKids[i]);
            if(!myKids[i].equals("")){
                char s = myKids[i].charAt(0);
                if (sensorCodes.get(s) != null) {
                    builder.append(sensorCodes.get(s) + ":  " + parseValueOfPayload(myKids[i]) + "\n");
                }

                parsedString = builder.toString();
            }


        }

    }

    private String parseValueOfPayload(String myKid) {
        String ret;
        ret = myKid.substring(1).replace("ff", "");
        return ret.replace("b", ",");


    }

    public String getParsedString() {
        return parsedString;
    }
}





