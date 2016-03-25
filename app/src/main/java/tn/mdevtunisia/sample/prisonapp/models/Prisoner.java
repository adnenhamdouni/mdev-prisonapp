package tn.mdevtunisia.sample.prisonapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adnenhamdouni on 23/03/2016.
 */

public class Prisoner implements Parcelable{

    private String mName;
    private String mMatricule;
    private String mDuration;
    private int mImageRes;

    public Prisoner(String name, String matricule, String duration, int imageRes) {
        this.mName = name;
        this.mMatricule = matricule;
        this.mDuration = duration;
        this.mImageRes = imageRes;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getMatricule() {
        return mMatricule;
    }

    public void setMatricule(String matricule) {
        this.mMatricule = matricule;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        this.mDuration = duration;
    }

    public int getImageRes() {
        return mImageRes;
    }

    public void setImageRes(int imageRes) {
        this.mImageRes = imageRes;
    }

    @Override
    public String toString() {
        return "Prisoner{" +
                "mName='" + mName + '\'' +
                ", mMatricule='" + mMatricule + '\'' +
                ", mDuration='" + mDuration + '\'' +
                ", mImageRes=" + mImageRes +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mMatricule);
        dest.writeString(mDuration);
        dest.writeInt(mImageRes);

    }

    private Prisoner(Parcel in){
        mName = in.readString();
        mMatricule = in.readString();
        mDuration = in.readString();
        mImageRes = in.readInt();
    }

    public static final Parcelable.Creator<Prisoner> CREATOR = new Prisoner.Creator<Prisoner>() {

        @Override
        public Prisoner createFromParcel(Parcel source) {
            return new Prisoner(source);
        }

        @Override
        public Prisoner[] newArray(int size) {
            return new Prisoner[0];
        }
    };
}
