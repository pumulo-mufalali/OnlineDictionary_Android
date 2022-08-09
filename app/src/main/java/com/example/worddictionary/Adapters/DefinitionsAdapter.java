package com.example.worddictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worddictionary.Models.Definition;
import com.example.worddictionary.R;

import java.util.List;

public class DefinitionsAdapter extends RecyclerView.Adapter<DefinitionsAdapter.DefinitionViewHolder> {
    private Context context;
    private List<Definition> definitionList;

    public DefinitionsAdapter(Context context, List<Definition> definitionList) {
        this.context = context;
        this.definitionList = definitionList;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.definitions_lists, parent, false);
        return new DefinitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.textViewDefinition.setText("Definition: "+definitionList.get(position).getDefinition());
        holder.textViewExample.setText("Example: "+definitionList.get(position).getExample());
        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(definitionList.get(position).getSynonyms());
        antonyms.append(definitionList.get(position).getAntonyms());

        holder.textViewSynonyms.setText(synonyms);
        holder.textViewAntonyms.setText(antonyms);

        holder.textViewSynonyms.setSelected(true);
        holder.textViewAntonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return definitionList.size();
    }

    public class DefinitionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDefinition, textViewExample, textViewSynonyms, textViewAntonyms;

        public DefinitionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDefinition = itemView.findViewById(R.id.txtDefinitions);
            textViewExample = itemView.findViewById(R.id.txtExamples);
            textViewSynonyms = itemView.findViewById(R.id.txt_Synonyms);
            textViewAntonyms = itemView.findViewById(R.id.txt_Antonyms);
        }
    }
}
