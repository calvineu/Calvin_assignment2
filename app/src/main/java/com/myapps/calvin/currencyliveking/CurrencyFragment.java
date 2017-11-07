package com.myapps.calvin.currencyliveking;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class CurrencyFragment extends Fragment {
    private ImageView img1,img2;
    private TextView tv1,tv2,tv3;
    private int pos1,pos2;
    private EditText edit;

    public CurrencyFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.currency_fragment,container,false));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner sp1 = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),R.array.currency_symbols,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);
        Spinner sp2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),R.array.currency_symbols,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);
        img1 = (ImageView) view.findViewById(R.id.c_f_flag);
        tv1 = (TextView) view.findViewById(R.id.c_f_text);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] currency_names = getResources().getStringArray(R.array.currency_names);
                String str = currency_names[position];
                img1.setImageResource(MyFlags.getFlags()[position]);
                tv1.setText(str);
                pos1 = position;
                if(tv3.getText() != null){
                    tv3.setText("");
                }
                if(edit.getText() != null){
                    edit.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        img2 = (ImageView) view.findViewById(R.id.c_f_flag2);
        tv2 = (TextView) view.findViewById(R.id.c_f_text2);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] currency_names = getResources().getStringArray(R.array.currency_names);
                String str = currency_names[position];
                img2.setImageResource(MyFlags.getFlags()[position]);
                tv2.setText(str);
                pos2 = position;
                if(tv3.getText() != null){
                    tv3.setText("");
                }
                if(edit.getText() != null){
                    edit.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edit = (EditText) view.findViewById(R.id.edit);
        tv3 = (TextView) view.findViewById(R.id.txt_result);
        Button btn = (Button) view.findViewById(R.id.convertButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DownloadTask.list.size() == 0){
                    Toast toast = Toast.makeText(getContext(),"Please check your internet connection",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else {
                    String amountString = edit.getText().toString();
                    if (amountString.equals("")) {
                        Toast toast = Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        Double x = DownloadTask.list.get(pos1);
                        Double y = DownloadTask.list.get(pos2);
                        Double amount = Double.valueOf(amountString);
                        Double z = (amount / x) * y;
                        String amountDoubleFormated = new DecimalFormat("0.00").format(z);
                        Double price = Double.valueOf(amountDoubleFormated);
                        tv3.setText(String.valueOf(BigDecimal.valueOf(price)));
                    }
                }

            }
        });


    }
}
