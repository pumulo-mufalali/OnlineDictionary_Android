package com.example.worddictionary;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worddictionary.Adapters.MeaningAdapter;
import com.example.worddictionary.Adapters.PhoneticsAdapter;
import com.example.worddictionary.Models.ApiResponse;

public class MainActivity extends AppCompatActivity {
    public SearchView searchView;
    public TextView textViewWord;
    public RecyclerView recyclerView_phonetics, recyclerView_meanings;
    public ProgressDialog loading;
    public PhoneticsAdapter phoneticsAdapter;
    public MeaningAdapter meaningAdapter;
    public OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchedData(ApiResponse apiResponse, String message) {
            if (apiResponse == null) {
                Toast.makeText(MainActivity.this, "Please sorry!! No data found!", Toast.LENGTH_SHORT).show();
            }
            showData(apiResponse);
            loading.dismiss();
        }

        @Override
        public void onError(String message) {
            loading.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        textViewWord = findViewById(R.id.textWord);
        recyclerView_phonetics = findViewById(R.id.recyclerWord);
        recyclerView_meanings = findViewById(R.id.recyclerMeaning);

        loading = new ProgressDialog(this);
        loading.setTitle("Loading...");
        loading.show();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loading.setTitle("Fetching data for " + query);
                loading.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showData(ApiResponse apiResponse) {
        textViewWord.setText(apiResponse.getWord());

        recyclerView_phonetics.setHasFixedSize(true);
        recyclerView_phonetics.setLayoutManager(new GridLayoutManager(this, 1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());
        recyclerView_phonetics.setAdapter(phoneticsAdapter);

        recyclerView_meanings.setHasFixedSize(true);
        recyclerView_meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this, apiResponse.getMeanings());
        recyclerView_meanings.setAdapter(meaningAdapter);

    }


}
