package com.style.map.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.search.sug.SuggestionResult;
import com.style.map.R;
import com.style.map.databinding.ItemSearchBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索結果適配器
 */

interface OnItemClickListener {
    void listener(SuggestionResult.SuggestionInfo info);
}

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<SuggestionResult.SuggestionInfo> list;
    public OnItemClickListener listener;

    public SearchAdapter(List<SuggestionResult.SuggestionInfo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemSearchBinding binding;
        SuggestionResult.SuggestionInfo suggestionInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.listener(suggestionInfo);
                }
            });
        }

        void setData(SuggestionResult.SuggestionInfo suggestionInfo) {
            this.suggestionInfo = suggestionInfo;
            binding.itemText.setText(suggestionInfo.address);
        }
    }
}
