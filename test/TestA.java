/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class TestA extends JFrame
{
    JComboBox com1, com2;

    public TestA()
    {


        com1 = new JComboBox();
        com2 = new JComboBox();
        com1.setLightWeightPopupEnabled(true);

        com1.addItem("One");
        com1.addItem("two");
        com1.addItem("Three");
        com2.addItem("One");
        com2.addItem("two");
        com2.addItem("Three");

        com1.setRenderer(new MyCellRenderer());


        this.setLayout(new FlowLayout());
        this.add(com1);
        this.add(com2);

        this.pack();
        this.validate();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    

   class MyCellRenderer extends JLabel implements ListCellRenderer<Object> 
   {
     public MyCellRenderer() 
     {
         setOpaque(true);
     }

     public Component getListCellRendererComponent(JList<?> list,
                                                   Object value,
                                                   int index,
                                                   boolean isSelected,
                                                   boolean cellHasFocus) {

         setText(value.toString());

         Color background = Color.white;
         Color foreground = Color.black;

         // check if this cell represents the current DnD drop location
         JList.DropLocation dropLocation = list.getDropLocation();

             background = Color.RED;
             foreground = Color.BLACK;;

         setBackground(background);
         setForeground(foreground);

         return this;
     }
 }


    public static void main(String[]args){
        boolean tambah2=false;
        boolean tambah3=false;
        boolean tambah4=false;
        
        
        if (tambah4) {
            
        }
        else if (tambah3) {
            
        }
        else if (tambah2) {
            
        }
        else if ((tambah4&&tambah3&&tambah2)==false) {
            System.out.println("HAHA");
        }
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            //new Test1();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}