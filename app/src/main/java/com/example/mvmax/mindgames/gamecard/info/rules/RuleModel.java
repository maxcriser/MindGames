package com.example.mvmax.mindgames.gamecard.info.rules;

import android.os.Parcel;
import android.os.Parcelable;

public class RuleModel implements Parcelable {

    private String mTitle;
    private String mDescription;

    public RuleModel(final String pTitle, final String pDescription) {
        mTitle = pTitle;
        mDescription = pDescription;
    }

    private RuleModel(final Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
    }

    public static final Creator<RuleModel> CREATOR = new Creator<RuleModel>() {

        @Override
        public RuleModel createFromParcel(final Parcel in) {
            return new RuleModel(in);
        }

        @Override
        public RuleModel[] newArray(final int size) {
            return new RuleModel[size];
        }
    };

    public void setTitle(final String pTitle) {
        mTitle = pTitle;
    }

    public void setDescription(final String pDescription) {
        mDescription = pDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel pParcel, final int pI) {
        pParcel.writeString(mTitle);
        pParcel.writeString(mDescription);
    }
}
