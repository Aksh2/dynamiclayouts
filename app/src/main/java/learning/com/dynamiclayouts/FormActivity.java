package learning.com.dynamiclayouts;

import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class FormActivity extends AppCompatActivity {


    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);

        for(int i=1;i<=2;i++){
            createTextInputLayout(linearLayout,"label "+i);
            createTextView(linearLayout,"Gender:");
            createSpinner(linearLayout,new String[]{"Male","Female"});
        }
    }




    public void createTextView(ViewGroup viewGroup, String label){
        TextView textView = new TextView(viewGroup.getContext());
        textView.setText(label);
        if(viewGroup instanceof LinearLayout){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins((int)getResources().getDimension(R.dimen.textView_left_margin),
                    (int)getResources().getDimension(R.dimen.textView_top_margin),
                    (int)getResources().getDimension(R.dimen.textView_right_margin),
                    (int)getResources().getDimension(R.dimen.textView_bottom_margin)
            );
            textView.setLayoutParams(layoutParams);
        }else if(viewGroup instanceof RelativeLayout){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins((int)getResources().getDimension(R.dimen.textView_left_margin),
                    (int)getResources().getDimension(R.dimen.textView_top_margin),
                    (int)getResources().getDimension(R.dimen.textView_right_margin),
                    (int)getResources().getDimension(R.dimen.textView_bottom_margin)
            );
            textView.setLayoutParams(layoutParams);
        }
        textView.setTextColor(Color.BLACK);
        viewGroup.addView(textView);
    }

    public void createTextInputLayout(ViewGroup viewGroup,String label){
        EditText editText = new EditText(viewGroup.getContext());
        TextInputLayout textInputLayout = new TextInputLayout(viewGroup.getContext());
        textInputLayout.setHint(label);
        if(viewGroup instanceof LinearLayout){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins((int)getResources().getDimension(R.dimen.editText_left_margin),
                    (int)getResources().getDimension(R.dimen.editText_top_margin),
                    (int)getResources().getDimension(R.dimen.editText_right_margin),
                    (int)getResources().getDimension(R.dimen.editText_bottom_margin)
            );
            editText.setLayoutParams(layoutParams);
            textInputLayout.setLayoutParams(layoutParams);
            textInputLayout.addView(editText,layoutParams);
        }else if(viewGroup instanceof RelativeLayout){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins((int)getResources().getDimension(R.dimen.editText_left_margin),
                    (int)getResources().getDimension(R.dimen.editText_top_margin),
                    (int)getResources().getDimension(R.dimen.editText_right_margin),
                    (int)getResources().getDimension(R.dimen.editText_bottom_margin)
            );
            editText.setLayoutParams(layoutParams);
            textInputLayout.setLayoutParams(layoutParams);
            textInputLayout.addView(editText,layoutParams);
        }
        viewGroup.addView(textInputLayout);
    }

    public void createSpinner(ViewGroup viewGroup,String[] options){
        Spinner spinner = new Spinner(this);
        if(viewGroup instanceof LinearLayout){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins((int)getResources().getDimension(R.dimen.spinner_left_margin),
                    (int)getResources().getDimension(R.dimen.spinner_top_margin),
                    (int)getResources().getDimension(R.dimen.spinner_right_margin),
                    (int)getResources().getDimension(R.dimen.spinner_bottom_margin)
            );
            spinner.setLayoutParams(layoutParams);
        }else if(viewGroup instanceof RelativeLayout){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins((int)getResources().getDimension(R.dimen.spinner_left_margin),
                    (int)getResources().getDimension(R.dimen.spinner_top_margin),
                    (int)getResources().getDimension(R.dimen.spinner_right_margin),
                    (int)getResources().getDimension(R.dimen.spinner_bottom_margin)
            );
            spinner.setLayoutParams(layoutParams);
        }
        spinner.setGravity(Gravity.CENTER_HORIZONTAL);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(viewGroup.getContext(),android.R.layout.simple_spinner_dropdown_item,options);
        spinner.setAdapter(adapter);
        viewGroup.addView(spinner);
    }

    private void parseJson(String data){
        JSONObject jsonObject=null;
        String type="",labelName="";
        try {
            jsonObject = new JSONObject(data);
            type=jsonObject.has("type")?jsonObject.getString("type"):"";
            labelName=jsonObject.has("field-name")?jsonObject.getString("field-name"):"";
        } catch (JSONException e) {
            e.printStackTrace();
        }

        switch (type){
            case "text":
                break;
            case "multiline":
                break;
            case "number":
                break;
            case "dropdown":
                break;
            default:
        }

    }


}
