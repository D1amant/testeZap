package com.example.luis.testeIlegra.Utils;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by luis on 05/08/15.
 */
public class ValidatorForm {


    public static boolean validateNotNull(View pView, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    return true;
                }
            }
            // em qualquer outra condição é gerado um erro
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    public static boolean validateConfirmPassword(View pView,View pView2, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            EditText edText2 = (EditText) pView2;
            Editable text = edText.getText();
            Editable text2 = edText2.getText();
            if (text != null && text2 != null) {
                String strText = text.toString();
                String strText2 = text2.toString();
                if (strText.equals(strText2)) {
                    return true;
                }
            }
            // em qualquer outra condição é gerado um erro
            edText.setError(pMessage);
            edText2.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    public static boolean validateNotNull(List<AutoCompleteTextView> pView, String pMessage) {
        boolean validacao = true;
        for (int i = 0; i < pView.size(); i++ ){
            if (pView.get(i) instanceof AutoCompleteTextView) {
                AutoCompleteTextView edText = (AutoCompleteTextView) pView.get(i);
                Editable text = edText.getText();
                if (text != null) {
                    String strText = text.toString();
                    if (!TextUtils.isEmpty(strText)) {
                        // return true;
                    }else{
                        // em qualquer outra condição é gerado um erro
                        edText.setError(pMessage);
                        edText.setFocusable(true);
                        edText.requestFocus();
                        validacao = false;
                    }
                }


            }
            // return false;
        }
        return validacao;
    }



    public static boolean validateEditTextNotNull(List<EditText> pView, String pMessage) {
        boolean validacao = true;
        for (int i = 0; i < pView.size(); i++ ){
            if (pView.get(i) instanceof EditText) {
                EditText edText = (EditText) pView.get(i);
                Editable text = edText.getText();
                if (text != null) {
                    String strText = text.toString();
                    if (!TextUtils.isEmpty(strText)) {
                        // return true;
                    }else{
                        // em qualquer outra condição é gerado um erro
                        edText.setError(pMessage);
                        edText.setFocusable(true);
                        edText.requestFocus();
                        validacao = false;
                    }
                }


            }
            // return false;
        }
        return validacao;
    }





    public static boolean validateSpinnerNotNull(List<View> pView, String pMessage) {
        boolean validacao = true;
        for (int i = 0; i < pView.size(); i++ ){
            if (pView.get(i) instanceof Spinner) {
                Spinner spinner = (Spinner) pView.get(i);

                if (spinner.getSelectedItem() != null) {

                    if (!TextUtils.isEmpty(spinner.getSelectedItem().toString())) {
                        // return true;
                    }else{
                        // em qualquer outra condição é gerado um erro
                        ((TextView)spinner.getSelectedView()).setError(pMessage);
                        //spinner.setError(pMessage);
                        spinner.setFocusable(true);
                        spinner.requestFocus();
                        validacao = false;
                    }
                }


            }
            // return false;
        }
        return validacao;
    }
    public static boolean validateDateFormat(View pView, String pDateFormat,
                                             String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    SimpleDateFormat format = new SimpleDateFormat(pDateFormat);
                    try {
                        format.parse(strText);
                        return true;
                    } catch (ParseException pe) {

                    }
                }
            }
            // em qualquer outra condição é gerado um erro
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }


    public final static boolean isValidEmail(View pView , String pMessage) {

        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            CharSequence target = edText.getText().toString();

            if (TextUtils.isEmpty(target)) {
                edText.setError(pMessage);
                return false;
            } else {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()) {
                    return true;
                } else {
                    edText.setError(pMessage);
                    return false;
                }

            }

        }
        return false;
    }
}