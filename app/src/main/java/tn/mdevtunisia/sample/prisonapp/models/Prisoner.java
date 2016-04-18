package tn.mdevtunisia.sample.prisonapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by adnenhamdouni on 23/03/2016.
 */

public class Prisoner implements Serializable {

    private int id;
    private String name;
    private String mat;
    private String duration;
    private String imgPath;


    public Prisoner() {
    }

    public Prisoner(String name, String matricule, String duration, String imageRes) {
        this.name = name;
        this.mat = matricule;
        this.duration = duration;
        this.imgPath = imageRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricule() {
        return mat;
    }

    public void setMatricule(String matricule) {
        this.mat = matricule;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageRes() {
        return imgPath;
    }

    public void setImageRes(String imageRes) {
        this.imgPath = imageRes;
    }

    @Override
    public String toString() {
        return "Prisoner{" +
                "name='" + name + '\'' +
                ", mat='" + mat + '\'' +
                ", duration='" + duration + '\'' +
                ", imgPath=" + imgPath +
                '}';
    }
}
