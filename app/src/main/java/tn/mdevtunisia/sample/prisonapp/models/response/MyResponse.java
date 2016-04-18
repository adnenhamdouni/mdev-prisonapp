package tn.mdevtunisia.sample.prisonapp.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyResponse {

    public class Prisoner {

        @SerializedName("success")
        private int mSuccess;

        @SerializedName("message")
        private String mMessage;

        @SerializedName("prisoners")
        private ArrayList<PrisonerResponse> mPrisoners;

        public Prisoner(int success, String message) {
            super();
            this.mSuccess = success;
            this.mMessage = message;
        }

        public int getSuccess() {
            return mSuccess;
        }

        public void setSuccess(int success) {
            this.mSuccess = success;
        }

        public String getMessage() {
            return mMessage;
        }

        public void setMessage(String message) {
            this.mMessage = message;
        }

        public ArrayList<PrisonerResponse> getPrisoners() {
            return mPrisoners;
        }

        public void setPrisoners(ArrayList<PrisonerResponse> prisoners) {
            this.mPrisoners = prisoners;
        }

    }



    public class PrisonerResponse {

        @SerializedName("id")
        private int mId;
        @SerializedName("name")
        private String mName;
        @SerializedName("matricule")
        private String mMatricule;
        @SerializedName("duration")
        private String mDuration;
        @SerializedName("image_path")
        private String mImagePath;

        public PrisonerResponse(int id, String name, String matricule, String duration, String imagePath) {
            this.mId = id;
            this.mName = name;
            this.mMatricule = matricule;
            this.mDuration = duration;
            this.mImagePath = imagePath;
        }

        public int getmId() {
            return mId;
        }

        public void setmId(int mId) {
            this.mId = mId;
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

        public String getImagePath() {
            return mImagePath;
        }

        public void setImagePath(String imagePath) {
            this.mImagePath = imagePath;
        }
    }
}
