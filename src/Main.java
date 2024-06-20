import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static String savedPassword = null;
    private static StringBuilder enteredPasscode = new StringBuilder();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Password Locker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);


        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);


        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);


        JLabel instructionLabel = new JLabel("Enter passcode:");
        instructionLabel.setBounds(10, 20, 120, 25);
        panel.add(instructionLabel);


        JPasswordField passcodeField = new JPasswordField(20);
        passcodeField.setBounds(150, 20, 165, 25);
        panel.add(passcodeField);


        int x = 10, y = 50;
        for (int i = 1; i <= 9; i++) {
            JButton numberButton = new JButton(String.valueOf(i));
            numberButton.setBounds(x, y, 50, 50);
            panel.add(numberButton);

            numberButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enteredPasscode.append(numberButton.getText());
                    passcodeField.setText(enteredPasscode.toString());
                }
            });

            x += 60;
            if (i % 3 == 0) {
                x = 10;
                y += 60;
            }
        }

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(10, 230, 150, 25);
        panel.add(enterButton);

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(10, 260, 300, 25);
        panel.add(resultLabel);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPassword = new String(passcodeField.getPassword());

                if (savedPassword == null) {
                    savedPassword = enteredPassword;
                    resultLabel.setText("Password Set");
                } else {
                    if (enteredPassword.equals(savedPassword)) {
                        resultLabel.setText("Correct password");
                    } else {
                        resultLabel.setText("Incorrect password");
                    }
                }

                enteredPasscode.setLength(0);
                passcodeField.setText("");
            }
        });
    }
}
