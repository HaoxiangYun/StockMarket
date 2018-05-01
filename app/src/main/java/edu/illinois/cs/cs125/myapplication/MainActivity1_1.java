package edu.illinois.cs.cs125.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity1_1 extends MainActivity {
    private List<String> double_trend = new ArrayList<String>();

    String[] topStocks = new String[]{"AAPL", "GOOGL", "MSFT", "AMZN", "FB", "BRK.B", "XOM", "JNJ", "JPM", "WFC", "GE", "BAC", "T",
            "WMT", "PG", "V", "CVX", "PFE", "HD", "VZ"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity1_1);
        ListView listView = (ListView) findViewById(R.id.listNews);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        Intent intent = getIntent();
        double_trend = intent.getStringArrayListExtra("trend");

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getback();
            }
        });
    }
    public void getback() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openGraph_view(final String symbol) {
        Intent intent = new Intent(this, Graph_view.class);
        intent.putExtra("stock",symbol);
        startActivity(intent);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return topStocks.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.example_list, null);
            TextView textView_name = (TextView) view.findViewById(R.id.symbol);
        //    TextView textView_trend = (TextView) view.findViewById(R.id.trend);
            Button button = (Button) view.findViewById(R.id.viewbutton);

            final String symbol = topStocks[i];

            textView_name.setText(symbol);
            //    showtrend((topStocks[i]), textView_trend, stocksymbol);
            //   textView_trend.setText(double_trend.get(i));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openGraph_view(symbol);
                }
            });

            return view;
        }
    }

}
