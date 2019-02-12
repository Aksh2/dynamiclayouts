package learning.com.dynamiclayouts;

import android.graphics.Color;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import learning.com.dynamiclayouts.adapters.ReportListAdapter;
import learning.com.dynamiclayouts.model.ReportModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.reportsRv)
    RecyclerView recyclerView;

    @BindView(R.id.addBt)
    Button addBt;

    private List<ReportModel> reportModelList;
    ReportListAdapter reportListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        reportModelList=new ArrayList<>();

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
                ReportModel reportModel = new ReportModel();
                reportModel.setName("a");
                reportModel.setAge(18);
                reportModelList.add(reportModel);
                Log.d("reportModelList:","list: " + reportModelList);
                reportListAdapter.setData(reportModelList);
                break;
        }
    }

    private void initialiseRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportListAdapter = new ReportListAdapter(this);
        recyclerView.setAdapter(reportListAdapter);

    }
}
