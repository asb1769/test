package Test;
import Security.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class OldosTest extends JFrame {

    Oldos simulation = new Oldos();

    private JButton addResource;
    private JButton addUser;

    private JEditorPane fldResName;
    private JEditorPane fldUserName;

    private JButton getResource;

    public OldosTest() {
        super("Oldos Window");
        setBounds(200, 200, 400, 100);

        addResource = new JButton("Добавить ресурс");
        fldResName = new JEditorPane();
        addUser = new JButton("Добавить пользователя");
        fldUserName = new JEditorPane();

        getResource = new JButton("Взять ресурс");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(addResource);
        buttonsPanel.add(fldResName);
        buttonsPanel.add(addUser);
        buttonsPanel.add(fldUserName);
        buttonsPanel.add(getResource);
        add(buttonsPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initListeners();
    }

    private void initListeners() {
        addResource.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulation.newResource(fldResName.getText());
            }
        });
        addUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulation.newUser(fldUserName.getText());
            }
        });
        getResource.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Oldos.User u = simulation.getUserByName(fldUserName.getText());
                simulation.getUserByName(fldUserName.getText()).getResource(simulation.getResourceByName(fldResName.getText()));
            }
        });

    }

    public static void main(String args[]) {
        System.out.println("--- Понеслась ---");
        OldosTest app = new OldosTest();
        app.setVisible(true);
        app.pack();
    }

}
