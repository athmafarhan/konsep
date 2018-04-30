/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Athma Farhan
 */
public class TestE extends Thread {
    int num;
    public TestE(int ber){
        num = ber;
    }
    public void run(){
        for (int i = num-1; i < 10; i+=1) {
            try {
                //Test5.sleep(1000);
                TestE.sleep(1000);
            } catch (Exception e) {
            }
            System.out.println(i);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        TestE t1 = new TestE(1);
        TestE t2 = new TestE(2);
        t1.start();
        
        System.out.println("kepo");
        t2.start();
    }
}
