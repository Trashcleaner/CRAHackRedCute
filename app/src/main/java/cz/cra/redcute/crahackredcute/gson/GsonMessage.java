package cz.cra.redcute.crahackredcute.gson;



import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by obrusvit on 30.8.16.
 */
public class GsonMessage implements Parcelable {

    String createdAt; //standard ISO-8601, UTC
    String devEUI;
    int fPort;
    int fCntUp;
    int aDRBit;
    int fCntDn;
    String payloadHex;
    String micHex;
    float IrrRSSI;
    int spFast;
    String subBand;
    String channel;
    int devLrrCnt;
    String Irrid;
    float lrrLAT;
    float lrrLON;

    float[] Irrs;


    protected GsonMessage(Parcel in) {
        createdAt = in.readString();
        devEUI = in.readString();
        fPort = in.readInt();
        fCntUp = in.readInt();
        aDRBit = in.readInt();
        fCntDn = in.readInt();
        payloadHex = in.readString();
        micHex = in.readString();
        IrrRSSI = in.readFloat();
        spFast = in.readInt();
        subBand = in.readString();
        channel = in.readString();
        devLrrCnt = in.readInt();
        Irrid = in.readString();
        lrrLAT = in.readFloat();
        lrrLON = in.readFloat();
        Irrs = in.createFloatArray();
    }

    public static final Creator<GsonMessage> CREATOR = new Creator<GsonMessage>() {
        @Override
        public GsonMessage createFromParcel(Parcel in) {
            return new GsonMessage(in);
        }

        @Override
        public GsonMessage[] newArray(int size) {
            return new GsonMessage[size];
        }
    };

    public float[] getIrrs() {
        return Irrs;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDevEUI() {
        return devEUI;
    }

    public int getfPort() {
        return fPort;
    }

    public int getfCntUp() {
        return fCntUp;
    }

    public int getaDRBit() {
        return aDRBit;
    }

    public int getfCntDn() {
        return fCntDn;
    }

    public String getPayloadHex() {
        return payloadHex;
    }

    public String getMicHex() {
        return micHex;
    }

    public float getIrrRSSI() {
        return IrrRSSI;
    }

    public int getSpFast() {
        return spFast;
    }

    public String getSubBand() {
        return subBand;
    }

    public String getChannel() {
        return channel;
    }

    public int getDevLrrCnt() {
        return devLrrCnt;
    }

    public String getIrrid() {
        return Irrid;
    }

    public float getLrrLAT() {
        return lrrLAT;
    }

    public float getLrrLON() {
        return lrrLON;
    }

    public String getLonString(){
        return Float.toString(lrrLON);
    }
    public String getLatString(){
        return Float.toString(lrrLAT);

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdAt);
        dest.writeString(devEUI);
        dest.writeInt(fPort);
        dest.writeInt(fCntUp);
        dest.writeInt(aDRBit);
        dest.writeInt(fCntDn);
        dest.writeString(payloadHex);
        dest.writeString(micHex);
        dest.writeFloat(IrrRSSI);
        dest.writeInt(spFast);
        dest.writeString(subBand);
        dest.writeString(channel);
        dest.writeInt(devLrrCnt);
        dest.writeString(Irrid);
        dest.writeFloat(lrrLAT);
        dest.writeFloat(lrrLON);
        dest.writeFloatArray(Irrs);
    }
}

