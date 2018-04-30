/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Athma Farhan
 */
public class TestN {
    public static void main(String[] args) {
        String test = "100,000";
        test = test.replace(",", "");
        
        System.out.println(Integer.parseInt(test));
    }
}
