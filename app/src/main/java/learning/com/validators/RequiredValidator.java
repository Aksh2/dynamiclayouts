package learning.com.validators;

import android.text.TextUtils;

public class RequiredValidator implements IValidator {

    @Override
    public boolean validate(String data) {
        return !TextUtils.isEmpty(data);
    }
}
