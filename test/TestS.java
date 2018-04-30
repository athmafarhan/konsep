import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TestS extends JFrame
{
    private JButton btnAdd;
    private JPanel centerPanel;
    private Box vBox;

    public TestS()
    {
        super("The Title");
        btnAdd = new JButton("Add new JTextField!");
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e)
            {
                vBox.add(new JTextField(20));
                pack();
            }

        });
        vBox = Box.createVerticalBox();
        centerPanel = new JPanel();
        JPanel contentPanel = (JPanel) getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(btnAdd, "South");
        contentPanel.add(centerPanel, "Center");
        centerPanel.add(vBox);
        pack();
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TestS().setVisible(true);
            }
        });
    }
}