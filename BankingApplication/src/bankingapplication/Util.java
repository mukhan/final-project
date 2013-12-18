package bankingapplication;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Util {

    public static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    public static String formatMoney(double value) {
        double roundedValue = round(value, 2);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(roundedValue);
    }
}
