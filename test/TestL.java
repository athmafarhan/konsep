
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Athma Farhan
 */
public class TestL {
    public static void main(String[] args) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
String inputString1 = "23 04 1997";
String inputString2 = "27 04 1997";

try {
    Date date1 = myFormat.parse(inputString1);
    Date date2 = myFormat.parse(inputString2);
    long diff = date2.getTime() - date1.getTime();
    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
} catch (ParseException e) {
    e.printStackTrace();
}
    }
}
