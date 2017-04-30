package com.example.luis.testezap.Utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by luis on 31/10/16.
 */

public class Money {

    public static String  formatMoney(int val){
        BigDecimal valor = new BigDecimal (val);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return  nf.format (valor);
    }

}
