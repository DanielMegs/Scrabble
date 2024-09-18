package daniel;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

/** Password Strength App
 * @author danny
 */
public class Scrabble {

    private JFrame frame;
    private JTextField textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7;
    private JButton btnGenerate;
    private JTextArea textArea;

	/**
	 * Initializes and displays the main application window.
	 * 
	 * @param args command line arguments
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Scrabble window = new Scrabble();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Scrabble() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 632, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // 7 text fields for user input
        textField_1 = new JTextField();
        textField_1.setBounds(25, 50, 58, 54);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setBounds(111, 50, 58, 54);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setBounds(193, 50, 58, 54);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setBounds(276, 50, 58, 54);
        frame.getContentPane().add(textField_4);

        textField_5 = new JTextField();
        textField_5.setBounds(360, 50, 58, 54);
        frame.getContentPane().add(textField_5);

        textField_6 = new JTextField();
        textField_6.setBounds(443, 50, 58, 54);
        frame.getContentPane().add(textField_6);

        textField_7 = new JTextField();
        textField_7.setBounds(528, 50, 58, 54);
        frame.getContentPane().add(textField_7);

        // Button to generate permutations
        btnGenerate = new JButton("Generate Scrabble Tiles");
        btnGenerate.setBounds(207, 163, 191, 49);
        frame.getContentPane().add(btnGenerate);

        // Label for results
        JLabel lblNewLabel = new JLabel("Results");
        lblNewLabel.setBounds(25, 250, 76, 25);
        frame.getContentPane().add(lblNewLabel);

        // Text area to display results
        textArea = new JTextArea();
        textArea.setBounds(25, 286, 561, 164);
        frame.getContentPane().add(textArea);

        // Button click event to generate permutations
        btnGenerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGenerate();
            }
        });
    }

    /**
     * Handle the button click to generate permutations.
     */
    private void handleGenerate() {
        // Collect input from the text fields
        String input = textField_1.getText() + textField_2.getText() + textField_3.getText() +
                       textField_4.getText() + textField_5.getText() + textField_6.getText() +
                       textField_7.getText();
        input = input.trim();  // Trim to remove any extra spaces

        // Validate input (ensure 7 letters and no other characters)
        if (input.length() != 7 || !input.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(frame, "Please enter exactly 7 letters (A-Z only).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate permutations and display them
        List<String> permutations = generatePermutations(input);
        displayResults(permutations);
    }

    /**
     * Generates all permutations of the given string.
     */
    private List<String> generatePermutations(String input) {
        List<String> result = new ArrayList<>();
        transform("", input, result);
        return result;
    }

    /**
     * Helper method to recursively generate permutations.
     */
    private void transform(String prefix, String str, List<String> result) {
        if (str.isEmpty()) {
            result.add(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
            	transform(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1), result);
            }
        }
    }

    /**
     * Displays the results (permutations) in the text area.
     */
    private void displayResults(List<String> results) {
        textArea.setText("");  // Clear previous results
        for (String perm : results) {
            textArea.append(perm + "\n");
        }
    }
}
