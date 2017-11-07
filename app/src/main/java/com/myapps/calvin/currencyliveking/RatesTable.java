package com.myapps.calvin.currencyliveking;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RatesTable extends Fragment {
    MyAdapter adapter;
    DownloadTask myTask = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.table_rates,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.myRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyAdapter(getContext());
        rv.setAdapter(adapter);
        myTask = new DownloadTask(getContext(),adapter);
        myTask.execute("https://openexchangerates.org/api/latest.json?app_id=3f751bad3cfb41c7b422c5c105c85742");

    }

    @Override
    public void onStop() {
        super.onStop();
        myTask.cancel(true);
    }
}
