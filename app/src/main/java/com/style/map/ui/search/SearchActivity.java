package com.style.map.ui.search;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.style.map.R;
import com.style.map.core.ui.BaseActivity;
import com.style.map.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 搜索
 */
public class SearchActivity extends BaseActivity<ActivitySearchBinding> {
    //检索示例
    SuggestionSearch suggestionSearch = SuggestionSearch.newInstance();

    private final List<SuggestionResult.SuggestionInfo> list = new ArrayList<>();
    SearchAdapter adapter = new SearchAdapter(list);

    @Override
    public int layout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setAdapter(adapter);
        binding.search.setFocusable(true);
        binding.search.setFocusableInTouchMode(true);
        binding.search.requestFocus();
    }

    @Override
    public void listener() {
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                suggestionSearch.requestSuggestion(new SuggestionSearchOption().city(s.toString()).keyword(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OnGetSuggestionResultListener listener = suggestionResult -> {
            List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
            if (allSuggestions == null || allSuggestions.size() <= 0) {
                return;
            }
            Iterator<SuggestionResult.SuggestionInfo> iterator = allSuggestions.iterator();
            while (iterator.hasNext()) {
                SuggestionResult.SuggestionInfo next = iterator.next();
                if (next.address == null || next.address.isEmpty()) {
                    iterator.remove();
                }
            }
            list.clear();
            list.addAll(allSuggestions);
            adapter.notifyDataSetChanged();
        };
        adapter.listener = info -> {
            Intent intent = new Intent();
            intent.putExtra("key", info);
            setResult(1, intent);
            finish();
        };
        suggestionSearch.setOnGetSuggestionResultListener(listener);
    }
}
