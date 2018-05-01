package edu.illinois.cs.cs125.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph_view extends MainActivity1_1 {

    private Button button;
    private TextView textview;
    private RequestQueue mQueue;
    private GraphView graph;
    private double y;
    private int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

        Intent intent = getIntent();
        final String symbol = intent.getStringExtra("stock");

        //The Button Click and API call.

        button = (Button) findViewById(R.id.button3);
        mQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoYear(symbol);
            }
        });
        button = (Button) findViewById(R.id.button2);
        mQueue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sixMonth(symbol);
            }
        });

        button = (Button) findViewById(R.id.button);
        mQueue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daily(symbol);
            }
        });

        button = (Button) findViewById(R.id.lastbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getback();
            }
        });
    }
    public void getback() {
        Intent intent = new Intent(this, MainActivity1_1.class);
        startActivity(intent);
    }

    private void twoYear(final String stocksymbol) {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol="+stocksymbol+"&apikey=apikey=AXJVY79KCG2AIM2P";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<String> time_series = new ArrayList<String>();
                        try {
                            JSONObject obj = response.getJSONObject("Monthly Time Series");
                            Iterator iteratorObj = obj.keys();
                            while (iteratorObj.hasNext()) {
                                String getJsonObj = (String)iteratorObj.next();
                                System.out.println("Book No.: "   + "------>" + getJsonObj);

                                JSONObject jo_inside = obj.getJSONObject(getJsonObj);

                                Iterator<String> keys = jo_inside.keys();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    String value = jo_inside.getString(key);
                                    time_series.add(value);
                                }
                            }
                            graph = (GraphView) findViewById(R.id.stock_market_plot);
                            LineGraphSeries<DataPoint> series = new  LineGraphSeries<DataPoint>();
                            for (int i = 25; i > 0; i--) {
                                x++;
                                y = Double.parseDouble(time_series.get(128 - 5*(i - 1)));
                                series.appendData(new DataPoint(x,y), true,26);
                            }
                            graph.removeAllSeries();
                            graph.addSeries(series);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Graph_view.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
    private void sixMonth(final String stocksymbol) {

        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol="+stocksymbol+"&apikey=AXJVY79KCG2AIM2P";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<String> time_series = new ArrayList<String>();
                        try {
                            JSONObject obj = response.getJSONObject("Weekly Time Series");
                            Iterator iteratorObj = obj.keys();
                            while (iteratorObj.hasNext()) {
                                String getJsonObj = (String)iteratorObj.next();
                                System.out.println("Book No.: "   + "------>" + getJsonObj);

                                JSONObject jo_inside = obj.getJSONObject(getJsonObj);

                                Iterator<String> keys = jo_inside.keys();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    String value = jo_inside.getString(key);
                                    time_series.add(value);
                                }
                            }

                            //    textview.setText(Integer.toString(time_series.size()));
                            graph = (GraphView) findViewById(R.id.stock_market_plot);
                            LineGraphSeries<DataPoint> series = new  LineGraphSeries<DataPoint>();
                            for (int i = 25; i > 0; i--) {
                                x++;
                                y = Double.parseDouble(time_series.get(128 - 5*(i - 1)));
                                series.appendData(new DataPoint(x,y), true,26);
                            }
                            graph.removeAllSeries();
                            graph.addSeries(series);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Graph_view.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }
    private void daily(final String stocksymbol) {

        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+stocksymbol+"&outputsize=full&apikey=AXJVY79KCG2AIM2P";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<String> time_series = new ArrayList<String>();
                        try {
                            JSONObject obj = response.getJSONObject("Time Series (Daily)");
                            Iterator iteratorObj = obj.keys();
                            while (iteratorObj.hasNext()) {
                                String getJsonObj = (String)iteratorObj.next();
                                System.out.println("Book No.: "   + "------>" + getJsonObj);

                                JSONObject jo_inside = obj.getJSONObject(getJsonObj);

                                Iterator<String> keys = jo_inside.keys();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    String value = jo_inside.getString(key);
                                    time_series.add(value);
                                }
                            }

                            //    textview.setText(Integer.toString(time_series.size()));
                            graph = (GraphView) findViewById(R.id.stock_market_plot);
                            LineGraphSeries<DataPoint> series = new  LineGraphSeries<DataPoint>();
                            for (int i = 30; i > 0; i--) {
                                x++;
                                y = Double.parseDouble(time_series.get(153 - 5*(i - 1)));
                                series.appendData(new DataPoint(x,y), true,31);
                            }
                            graph.removeAllSeries();
                            graph.addSeries(series);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Graph_view.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }
}
