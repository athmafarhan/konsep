import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class TestU1 extends JFrame{

    private String mensaje;
    private JButton btnHola;
    private JButton btnAdios;

    public TestU1() {
        getContentPane().setLayout(null);

        btnHola = new JButton("Hello");
        btnHola.setBounds(63, 210, 89, 23);
        getContentPane().add(btnHola);
        btnHola.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                mensaje = TestU1.this.btnHola.getText();
                TestU2 frame2 = new TestU2(mensaje);
            }
        });

        btnAdios = new JButton("Bye");
        btnAdios.setBounds(245, 210, 89, 23);
        getContentPane().add(btnAdios);

        btnAdios.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            mensaje = TestU1.this.btnAdios.getText();
            TestU2 frame2 = new TestU2(mensaje);
        }
    });
    }

    public static void main(String[] args) {
        TestU1 frame = new TestU1();
        frame.setVisible(true);
    }
}