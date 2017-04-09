package cz.cra.redcute.crahackredcute.model;

/**
 * Created by obrusvit on 15.3.17.
 */

public class Device {

    public static final String NAME = "NameOfDevice";
    public static final String DEVEUI = "DevEUIOfDevice";


    private String devEUI;
    private String name;

    /**
     * SQL dependent variable
     */
    private long deviceId;

    public Device(String devEUI, String name, long deviceId) {
        this.devEUI = devEUI;
        this.name = name;
        this.deviceId = deviceId;
    }

    public String getDevEUI() {
        return devEUI;
    }

    public String getName() {
        return name;
    }

    public long getDeviceId() {
        return deviceId;
    }
}
