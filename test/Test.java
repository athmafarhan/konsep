import java.awt.Dimension;
    import java.awt.GridBagConstraints;
    import java.awt.GridBagLayout;
    import java.awt.Insets;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.ArrayList;
    import java.util.List;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    import javax.swing.JTextField;
    import javax.swing.border.LineBorder;

    public class Test 
    {
        // Field members
        static JPanel panel = new JPanel();
        static Integer indexer = 1;
        static List<JTextField> listOfTextFields = new ArrayList<JTextField>();

        public static void main(String[] args)
        {       
            // Construct frame
            JFrame frame = new JFrame();
            frame.setLayout(new GridBagLayout());
            frame.setPreferredSize(new Dimension(990, 990));
            frame.setTitle("My Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Frame constraints
            GridBagConstraints frameConstraints = new GridBagConstraints();

            // Construct button
            JButton addButton = new JButton("test");
            addButton.addActionListener(new ButtonListener());

            // Add button to frame
            frameConstraints.gridx = 0;
            frameConstraints.gridy = 0;
            frame.add(addButton, frameConstraints);

            // Construct panel
            panel.setPreferredSize(new Dimension(600, 600));
            panel.setLayout(new GridBagLayout());
            panel.setBorder(LineBorder.createBlackLineBorder());

            // Add panel to frame
            frameConstraints.gridx = 0;
            frameConstraints.gridy = 1;
            frameConstraints.weighty = 1;
            frame.add(panel, frameConstraints);

            // Pack frame
            frame.pack();

            // Make frame visible
            frame.setVisible(true);
        }

        static class ButtonListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {       

                panel.removeAll();
                GridBagConstraints textFieldConstraints = new GridBagConstraints();

                int rowCnt=4,i,j;

                for(i=0;i<rowCnt;i++){
                    for(j=0;j<rowCnt;j++){
                        JTextField g=new JTextField();
                        g.setText("7");
                        textFieldConstraints.gridx = i;
                        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
                        textFieldConstraints.weightx = 0.5;
                        textFieldConstraints.insets = new Insets(10, 10, 10, 10);
                        textFieldConstraints.gridy = j;
                        panel.add(g, textFieldConstraints);
                    }
                }

                panel.updateUI();
            }


        }
    }