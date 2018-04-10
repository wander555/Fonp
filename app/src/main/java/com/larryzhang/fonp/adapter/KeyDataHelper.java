package com.larryzhang.fonp.adapter;

import android.content.Context;
import android.widget.Filter;

import com.larryzhang.fonp.bean.PicListBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 关键词工作类
 *
 * @author zhangqiang
 * @date 2018/4/10
 */

public class KeyDataHelper {

    //给出固定的搜索列表
    private static List<KeySuggestion> keySuggestions =
            new ArrayList<>(Arrays.asList(
                    new KeySuggestion("anime"),
                    new KeySuggestion("girls"),
                    new KeySuggestion("food"),
                    new KeySuggestion("landscapes"),
                    new KeySuggestion("city"),
                    new KeySuggestion("gamne"),
                    new KeySuggestion("nature")));


    //查询关键字
    public interface OnFindSuggestionsListener {
        void onResults(List<KeySuggestion> results);
    }


    //根据关键字查询结果
    public interface OnFindResultsListener {
        void onResults(List<PicListBean> results);
    }


    public static void resetSuggestionsHistory() {
        for (KeySuggestion colorSuggestion : keySuggestions) {
            colorSuggestion.setIsHistory(false);
        }
    }


    //初次点击显示的个数
    public static List<KeySuggestion> getHistory(Context context, int count) {
        List<KeySuggestion> suggestionList = new ArrayList<>();
        KeySuggestion keySuggestion;
        for (int i = 0; i < keySuggestions.size(); i++) {
            keySuggestion = keySuggestions.get(i);
            keySuggestion.setIsHistory(true);
            suggestionList.add(keySuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }



    public static void findSuggestions(Context context, String query, final int limit, final long simulatedDelay,
                                       final OnFindSuggestionsListener listener) {
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                KeyDataHelper.resetSuggestionsHistory();
                List<KeySuggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (KeySuggestion suggestion : keySuggestions) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, new Comparator<KeySuggestion>() {
                    @Override
                    public int compare(KeySuggestion lhs, KeySuggestion rhs) {
                        return lhs.getIsHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<KeySuggestion>) results.values);
                }
            }
        }.filter(query);

    }



    public static void findResult(Context context, String query, final OnFindResultsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                List<PicListBean> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    //TODO

                    PicListBean pic = new PicListBean(1,"111",constraint.toString());


//                    for (ColorWrapper color : sColorWrappers) {
//                        if (color.getName().toUpperCase()
//                                .startsWith(constraint.toString().toUpperCase())) {
//
                            suggestionList.add(pic);
//                        }
//                    }

                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<PicListBean>) results.values);
                }
            }
        }.filter(query);

    }

}
