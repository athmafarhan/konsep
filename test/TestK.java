import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TestK extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;

    public TestK() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setVisible(true);

    }

    public static void main(String args[]) {
        new TestK();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        final JComboBox<String> comboBox = new JComboBox<>(new String[]{"","1","2","3"});
        JButton button = new JButton("filter");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RowFilter<DefaultTableModel, Object> rf  = RowFilter.regexFilter(comboBox.getSelectedItem().toString(), 0);
                sorter.setRowFilter(rf);
            }
        });
        panel.add(comboBox);
        panel.add(button);

        table = new JTable(model = new DefaultTableModel(3,3));
        sorter = new TableRowSorter<DefaultTableModel>(model);
        table.setRowSorter(sorter);

        add(panel,BorderLayout.SOUTH);
        add(new JScrollPane(table));
    }
}