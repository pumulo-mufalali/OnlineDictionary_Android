package com.example.worddictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worddictionary.Models.Meaning;
import com.example.worddictionary.R;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder> {
    private Context context;
    private List<Meaning> meaningList;

    public MeaningAdapter(Context context, List<Meaning> meaningList) {
        this.context = context;
        this.meaningList = meaningList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.meaning_lists, parent, false);
       return new MeaningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
        holder.textView_partOfSpeech.setText("Part of Speech: "+meaningList.get(position).getPartOfSpeech());
        holder.recyclerView_definitions.setHasFixedSize(true);
        holder.recyclerView_definitions.setLayoutManager(new GridLayoutManager(context, 1));

        DefinitionsAdapter definitionsAdapter = new DefinitionsAdapter(context, meaningList.get(position).getDefinitions());
        holder.recyclerView_definitions.setAdapter(definitionsAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningList.size();
    }

    public class MeaningViewHolder extends RecyclerView.ViewHolder {
        TextView textView_partOfSpeech;
        RecyclerView recyclerView_definitions;

        public MeaningViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_partOfSpeech = itemView.findViewById(R.id.partOfSpeech);
            recyclerView_definitions = itemView.findViewById(R.id.recyclerDefinition);
        }
    }
}
