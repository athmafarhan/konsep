
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Athma Farhan
 */
public class TestH {
    public static void main(String[] args) {
        LocalDateTime a = LocalDateTime.of(2018, 1, 30, 15, 30);
        System.out.println(a);
        LocalDateTime t = a.plusDays(2);
        System.out.println(t);
        
    }
}
