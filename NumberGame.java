import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame {
    private JFrame frame;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private int numberToGuess;
    private int maxAttempts;
    private int attempts;
    private boolean playAgain;

    public NumberGame(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.playAgain = true;
        generateNumber();
        createGUI();
    }

    private void generateNumber() {
        Random random = new Random();
        this.numberToGuess = random.nextInt(100) + 1;
    }

    private void createGUI() {
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        label = new JLabel("Guess a number between 1 and 100:");
        label.setHorizontalAlignment(JLabel.CENTER);

        textField = new JTextField(10);

        button = new JButton("Guess");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess = Integer.parseInt(textField.getText());
                attempts++;
                if (guess == numberToGuess) {
                    label.setText("Congratulations, you guessed the number in " + attempts + " attempts!");
                    playAgain = false;
                } else if (guess > numberToGuess) {
                    label.setText("Your guess is too high. Try again.");
                } else {
                    label.setText("Your guess is too low. Try again.");
                }
                if (!playAgain) {
                    frame.dispose();
                }
            }
        });

        frame.add(label, BorderLayout.NORTH);
        frame.add(textField, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        NumberGame game = new NumberGame(6);
    }
}
