package com.larryzhang.fonp.adapter;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * 图片的种类关键字
 *
 * @author zhangqiang
 * @date 2018/4/10
 */

public class KeySuggestion implements SearchSuggestion {

    private String name;
    private boolean mIsHistory = false;

    public KeySuggestion(String suggestion) {
        this.name = suggestion.toLowerCase();
    }

    public KeySuggestion(Parcel source) {
        this.name = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }


    @Override
    public String getBody() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(mIsHistory ? 1 : 0);
    }


    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

}
