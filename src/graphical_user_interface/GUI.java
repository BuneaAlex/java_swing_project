package graphical_user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private int ct=0;
    private JLabel label;
    private JPanel panel;
    private JFrame frame;
    private JList<String> list;
    private JTextField text1;
    private JScrollPane scroll;
    final DefaultListModel<String> l1 = new DefaultListModel<>();
    private Box box;
    public GUI() {

        frame = new JFrame();
        panel = new JPanel();
        list = new JList<>(l1);
        text1 = new JTextField();
        scroll = new JScrollPane();
        frame.setTitle("Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.add(panel);

        label = new JLabel("Number of clicks: 0");
        label.setBounds(10,10,160,25);
        JButton button = new JButton("Click");
        button.setBounds(10,50,80,25);
        button.addActionListener(this);

        list.setBounds(new Rectangle(200,50,80,80));
        text1.setBounds(50,300,100,25);

        JScrollPane scrollableTextArea = new JScrollPane(list);

        scrollableTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        panel.setLayout(null);
        panel.add(button);
        panel.add(label);
        panel.add(list);
        panel.add(text1);
        panel.add(scroll);


        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ct++;
        label.setText("Number of clicks:"+ct);
        l1.addElement(text1.getText());
    }
}
