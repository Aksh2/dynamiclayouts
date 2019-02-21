package learning.com.dynamiclayouts;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import learning.com.validators.IValidator;
import learning.com.validators.MinMaxValidator;
import learning.com.validators.RequiredValidator;
import learning.com.viewobjects.EditTextWidget;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {

    static final String TAG= FormActivity.class.getSimpleName();
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    List<EditTextWidget> editTextWidgets = new ArrayList<>();

    @BindView(R.id.doneBt)
    Button doneBt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
        doneBt.setOnClickListener(this);
             parseJson("[{\"field-name\":\"name\",\"type\":\"text\",\"required\":true},{\"field-name\":\"age\",\"type\":\"number\",\"min\":18,\"max\":65},{\"field-name\":\"address\",\"type\":\"multiline\"}]"
);

    }

    public void createTextView(ViewGroup viewGroup, String label){
        TextView textView = new TextView(viewGroup.getContext());
        textView.setText(label.substring(0,1).toUpperCase()+label.substring(1)+":");
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

    public EditTextWidget createEditText(ViewGroup viewGroup,String label,boolean isMultiline,boolean isNumberInputType,boolean isRequired, int min,int max){
        EditTextWidget editText = new EditTextWidget(this);
        createTextView(viewGroup,label);
        if(viewGroup instanceof LinearLayout){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins((int)getResources().getDimension(R.dimen.editText_left_margin),
                    (int)getResources().getDimension(R.dimen.editText_top_margin),
                    (int)getResources().getDimension(R.dimen.editText_right_margin),
                    (int)getResources().getDimension(R.dimen.editText_bottom_margin)
            );
            editText.setLayoutParams(layoutParams);

        }else if(viewGroup instanceof RelativeLayout){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins((int)getResources().getDimension(R.dimen.editText_left_margin),
                    (int)getResources().getDimension(R.dimen.editText_top_margin),
                    (int)getResources().getDimension(R.dimen.editText_right_margin),
                    (int)getResources().getDimension(R.dimen.editText_bottom_margin)
            );
            editText.setLayoutParams(layoutParams);

        }
        if(!isMultiline)
            editText.setMaxLines(1);
        if(isNumberInputType)
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        editText.setRequired(isRequired);
        editText.setMax(max);
        editText.setMin(min);
        List<IValidator> validators = new ArrayList<>();
        if(isRequired){
            IValidator validator = new RequiredValidator();
            validators.add(validator);
        }

        if(min!=-1 && max!=-1){
            IValidator validator = new MinMaxValidator(min,max);
            validators.add(validator);
        }
        editText.setValidationRules(validators);
        return editText;



    }

        public void createSpinner(ViewGroup viewGroup,String label,String[] options){
        Spinner spinner = new Spinner(this);
        createTextView(viewGroup,label);
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
        JSONArray jsonArray=null;
        String type="",labelName="";
        try {
            jsonArray = new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                type = jsonObject.has("type") ? jsonObject.getString("type") : "";
                labelName = jsonObject.has("field-name") ? jsonObject.getString("field-name") : "";
                Boolean isRequired = jsonObject.has("required")? jsonObject.getBoolean("required"):false;
                int max=jsonObject.has("max")?jsonObject.getInt("max"):-1;
                int min=jsonObject.has("min")?jsonObject.getInt("min"):-1;
                Log.d(TAG, "labelName: " + labelName + " ,type: " + type);
                switch (type) {
                    case "text":
                        editTextWidgets.add(createEditText(linearLayout, labelName, false, false,isRequired,min,max));
                        linearLayout.addView(editTextWidgets.get(editTextWidgets.size()-1));
                        break;
                    case "multiline":
                        editTextWidgets.add(createEditText(linearLayout, labelName, true, false,isRequired,min,max));
                        linearLayout.addView(editTextWidgets.get(editTextWidgets.size()-1));
                        break;
                    case "number":
                        editTextWidgets.add(createEditText(linearLayout, labelName, false, true,isRequired,min,max));
                        linearLayout.addView(editTextWidgets.get(editTextWidgets.size()-1));
                        break;
                    case "dropdown":
                        ArrayList<String> options = (ArrayList<String>) convertJSONArrayIntoArrayList(jsonObject.getJSONArray("options"));
                        Log.d(TAG, "options: " + options);
                        createSpinner(linearLayout, labelName, Arrays.copyOf(options.toArray(), options.toArray().length, String[].class));
                        break;
                    default:
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private List<String> convertJSONArrayIntoArrayList(JSONArray jsonArray){
        ArrayList<String> values = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            try {
                values.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return values;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.doneBt:
                Log.d("validation","all valid: "+validate());
                if(validate()){
                    String result=traverseViewTree(linearLayout);
                    Log.d("validation","json: "+result);
                    Intent intent = new Intent();
                    intent.putExtra("result",result);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                break;
        }
    }


    private String traverseViewTree(ViewGroup rootView){
        JSONObject jsonObject = new JSONObject();
        for(int index=0;index<rootView.getChildCount();index=index+2){
            View view=rootView.getChildAt(index);
            String label="",value="";
                    if(view instanceof TextView){
                        label=((TextView) view).getText().toString();
                        if(index+1<rootView.getChildCount()){
                            View nextView = rootView.getChildAt(index+1);
                            if(nextView instanceof EditText){
                                value = ((EditText)nextView).getText().toString();
                            }else if(nextView instanceof Spinner){
                                value =((Spinner)nextView).getSelectedItem().toString();
                            }
                        }
                        try {
                            jsonObject.put(label,value);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
        }
        return jsonObject.toString();

    }

    private boolean validate(){
        boolean flag=true;
        for(EditTextWidget editTextWidget : editTextWidgets){
            Log.d(TAG,"EditText:"+editTextWidget.toString());
            for(IValidator validator: editTextWidget.getValidationRules()){
                Log.d(TAG,"validation rule:"+validator.toString());

                if(validator.validate(editTextWidget.getText().toString())){
                    flag=true;
                }else{
                    editTextWidget.setError("Not a valid value");
                    return false;
                }
            }
        }
        return flag;
    }
}
