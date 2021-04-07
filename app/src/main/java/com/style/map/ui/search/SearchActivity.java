package com.style.map.ui.search;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.style.map.R;
import com.style.map.core.ui.BaseActivity;
import com.style.map.databinding.ActivitySearchBinding;

import java.util.List;

/**
 * 搜索
 */
public class SearchActivity extends BaseActivity<ActivitySearchBinding> {
    //检索示例
    SuggestionSearch suggestionSearch = SuggestionSearch.newInstance();

    @Override
    public int layout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {

    }

    @Override
    public void listener() {
        OnGetSuggestionResultListener listener = suggestionResult -> {
            for (int i = 0; i < suggestionResult.getAllSuggestions().size(); i++) {

                List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
            }
        };
        suggestionSearch.setOnGetSuggestionResultListener(listener);

        suggestionSearch.requestSuggestion(new SuggestionSearchOption().city("北京").keyword("北京"));
    }
}
