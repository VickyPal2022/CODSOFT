import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame {
    private JTextField numSubjectsField;
    private JTextField[] marksFields;
    private JLabel totalLabel, averageLabel, gradeLabel;
    private JButton submitSubjectsButton, calculateButton;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel numSubjectsLabel = new JLabel("Number of Subjects:");
        numSubjectsLabel.setBounds(50, 30, 150, 25);
        add(numSubjectsLabel);

        numSubjectsField = new JTextField();
        numSubjectsField.setBounds(200, 30, 100, 25);
        add(numSubjectsField);

        submitSubjectsButton = new JButton("Submit");
        submitSubjectsButton.setBounds(110, 70, 100, 25);
        add(submitSubjectsButton);


        submitSubjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMarksFields();
            }
        });

        setVisible(true);
    }

    private void createMarksFields() {
        int numSubjects;
        try {
            numSubjects = Integer.parseInt(numSubjectsField.getText());
            if (numSubjects <= 0) throw new NumberFormatException();

            marksFields = new JTextField[numSubjects];
            int yOffset = 110;
            for (int i = 0; i < numSubjects; i++) {
                JLabel subjectLabel = new JLabel("Subject " + (i + 1) + " Marks:");
                subjectLabel.setBounds(50, yOffset + (i * 30), 100, 25);
                add(subjectLabel);

                marksFields[i] = new JTextField();
                marksFields[i].setBounds(200, yOffset + (i * 30), 100, 25);
                add(marksFields[i]);
            }

            totalLabel = new JLabel("Total Marks: ");
            totalLabel.setBounds(50, yOffset + (numSubjects * 30)+20, 300, 25);
            add(totalLabel);

            averageLabel = new JLabel("Average Percentage: ");
            averageLabel.setBounds(50, yOffset + (numSubjects * 30)+40, 300, 25);
            add(averageLabel);

            gradeLabel = new JLabel("Grade: ");
            gradeLabel.setBounds(50, yOffset + (numSubjects * 30)+60, 300, 25);
            add(gradeLabel);

            calculateButton = new JButton("Calculate");
            calculateButton.setBounds(150, yOffset + (numSubjects * 30), 100, 25);
            add(calculateButton);

            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calculateGrades();
                }
            });

            // Refresh the frame to show the new fields and button
            setSize(400, yOffset + (numSubjects * 30) + 100);
            revalidate();
            repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of subjects.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateGrades() {
        int totalMarks = 0;
        int numSubjects = marksFields.length;

        try {
            for (JTextField marksField : marksFields) {
                int marks = Integer.parseInt(marksField.getText());
                totalMarks += marks;
            }

            double averagePercentage = (double) totalMarks / numSubjects;

            String grade;
            if (averagePercentage >= 90) {
                grade = "A";
            } else if (averagePercentage >= 80) {
                grade = "B";
            } else if (averagePercentage >= 70) {
                grade = "C";
            } else if (averagePercentage >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            totalLabel.setText("Total Marks: " + totalMarks);
            averageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
            gradeLabel.setText("Grade: " + grade);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {

        new StudentGradeCalculator();
    }
}
