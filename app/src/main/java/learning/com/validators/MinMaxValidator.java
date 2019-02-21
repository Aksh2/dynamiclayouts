package learning.com.validators;

import android.text.TextUtils;

public class MinMaxValidator implements IValidator {

    private int min,max;

    public MinMaxValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean validate(String data) {
            boolean flag=false;
            if(!TextUtils.isEmpty(data)) {
                int value = Integer.valueOf(data);
                return (value >= min && value <= max);
            }
            return flag;


    }
}
