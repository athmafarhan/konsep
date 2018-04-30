
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ButtonAddDynamic implements ActionListener{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ButtonAddDynamic().createAndShowGUI();
            }
        });
    }

    private JFrame frame;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    private List fields = new ArrayList();
    private List fieldButton = new ArrayList();
    private List fieldFile = new ArrayList();

    private JButton button1 = new JButton("Add Another TextField and Button");
    private static int countReport = 0;
    String files = null;
    int y=2;
    int j=0;

    protected void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }

    String[] labels = { "Locations of the given input" };
        for (String label : labels)
        {
            addColumn(label);
        }
            addRowBelow();
            constraints.gridx=1;
            constraints.gridy=0;
            panel.add(button1,constraints);

    frame = new JFrame("Add Button Dynamically");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JScrollPane(panel));
    frame.setLocationRelativeTo(null);
    frame.setResizable(true);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    button1.addActionListener(this);

    //Set the default button to button1, so that when return is hit, it will hit the button1

    JRootPane root = frame.getRootPane();
    root.setDefaultButton(button1);
    frame.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent we){
        System.exit(0);
        }
    });
    }


    private void addColumn(String labelText) {
        constraints.gridx = fields.size();
        constraints.gridy = 1;
        panel.add(new JLabel(labelText), constraints);
        constraints.gridy=2;
        final JTextField field=new JTextField(40);
        field.setEditable(true);
        panel.add(field,constraints);
        fields.add(field);

        //constraints.gridy=3;
        constraints.gridx = fields.size() + fieldButton.size();
        JButton button = new JButton("OK");
        panel.add(button,constraints);
        fieldButton.add(button);
        panel.revalidate(); // redo layout for extra column
    }


    private void addRowBelow() {
        y++;
        constraints.gridy=y;
        //System.out.println(fields.size());
        for (int x=0 ; x < fields.size() ; x++) {

            constraints.gridx=x;
            final JTextField field = new JTextField(40);
            field.setEditable(true);
            panel.add(field, constraints);
            constraints.gridx=x+1;
            field.setName("tes"+j);
            System.out.println(field.getName());
            JButton button = new JButton("OK");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    JOptionPane.showMessageDialog(null,"Program to add swing components dynamically","HI",1);
                }
            }
            );
            panel.add(button,constraints);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if("Add Another TextField and Button".equals(ae.getActionCommand())) {
            addRowBelow();
            frame.pack();
            frame.setLocationRelativeTo(null);
            j++;
        }
    }
}
