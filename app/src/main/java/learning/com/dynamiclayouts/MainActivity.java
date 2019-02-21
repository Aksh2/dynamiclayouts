package learning.com.dynamiclayouts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import learning.com.dynamiclayouts.adapters.ReportListAdapter;
import learning.com.dynamiclayouts.model.ReportModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int REQUEST_CODE=1;

    @BindView(R.id.reportsRv)
    RecyclerView recyclerView;

    @BindView(R.id.addBt)
    Button addBt;

    @BindView(R.id.reportNumber)
    TextView noOfReportsTv;

    private List<ReportModel> reportModelList;
    ReportListAdapter reportListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialiseListeners();
        initialiseRecyclerView();
    }

    private void initialiseListeners(){
        addBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBt:
                Intent dynamicFormIntent = new Intent(this,FormActivity.class);
                startActivityForResult(dynamicFormIntent,REQUEST_CODE);
                break;
        }
    }

    private void initialiseRecyclerView(){
        reportModelList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportListAdapter = new ReportListAdapter(this);
        recyclerView.setAdapter(reportListAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode==Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    Iterator<String> itr = jsonObject.keys();
                    int count=0;
                    ReportModel reportModel = new ReportModel();
                    while (itr.hasNext()&&count<2){
                        String key=itr.next();
                        String value=jsonObject.getString(key);
                        Log.d("json","key:"+key+"value: "+value);
                        reportModel.setField1(key+" "+value);
                        if(itr.hasNext()){
                            String key2=itr.next();
                            String value2=jsonObject.getString(key2);
                            Log.d("json","key2:"+key2+"value2: "+value2);
                            reportModel.setField2(key2+" "+value2);

                        }
                        break;

                    }
                    reportModelList.add(reportModel);
                    reportListAdapter.setData(reportModelList);
                    noOfReportsTv.setText(String.valueOf(reportModelList.size()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
