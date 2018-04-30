import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
/*from w  ww. j ava2s  .  c om*/
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class TestG {
  public TestG() {
    Map<Integer, Student> map = createMap();
    JComboBox cbox = createComboBox(map);

    JFrame frame = new JFrame();
    frame.add(cbox);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private Map<Integer, Student> createMap() {
    Map<Integer, Student> map = new HashMap<>();
    Student s1 = new Student(23, "M");
    Student s2 = new Student(6, "L");
    Student s3 = new Student(3, "C");
    Student s4 = new Student(8, "K");
    Student s5 = new Student(21, "T");

    map.put(s1.getId(), s1);
    map.put(s2.getId(), s2);
    map.put(s3.getId(), s3);
    map.put(s4.getId(), s4);
    map.put(s5.getId(), s5);

    return map;
  }

  private JComboBox createComboBox(final Map<Integer, Student> map) {
    final JComboBox cbox = new JComboBox();
    for (Integer id : map.keySet()) {
      cbox.addItem(id);
    }

    cbox.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        Integer id = (Integer) cbox.getSelectedItem();
        System.out.println(map.get(id));
      }
    });

    return cbox;
  }

  public static void main(String[] args) {

    new TestG();

  }
}

class Student {

  String name;
  Integer id;

  public Student(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Name: " + name + " - Stud ID: " + id;
  }
}