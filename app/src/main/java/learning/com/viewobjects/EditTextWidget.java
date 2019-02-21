package learning.com.viewobjects;



import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import java.util.ArrayList;
import java.util.List;

import learning.com.validators.IValidator;

public class EditTextWidget extends AppCompatEditText {
    private boolean isRequired;
    private int min;
    private int max;
    private List<IValidator> validationRules=new ArrayList<>();

    public EditTextWidget(Context context) {
        super(context);
    }


    public void setRequired(boolean required) {
        isRequired = required;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setValidationRules(List<IValidator> validationRules) {
        this.validationRules=validationRules;
    }

    public List<IValidator> getValidationRules() {
        return validationRules;
    }
}
