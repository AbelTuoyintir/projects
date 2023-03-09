import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class PalindromeChecker extends JFrame implements ActionListener {

    private JLabel inputLabel, resultLabel;
    private JTextField inputField;
    private JButton checkButton;

    public PalindromeChecker() {
        // Set up the GUI
        super("Palindrome Checker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));
        inputLabel = new JLabel("Enter a string:");
        inputField = new JTextField();
        resultLabel = new JLabel();
        checkButton = new JButton("Check");
        checkButton.addActionListener(this);
        add(inputLabel);
        add(inputField);
        add(new JLabel());
        add(checkButton);
        add(new JLabel());
        add(resultLabel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            String input = inputField.getText();
            if (isPalindrome(input)) {
                resultLabel.setText(input + " is a palindrome");
            } else {
                resultLabel.setText(input + " is not a palindrome");
            }
        }
    }

    public static boolean isPalindrome(String s) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(Character.toLowerCase(c));
                queue.offer(Character.toLowerCase(c));
            }
        }
        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (stack.pop() != queue.poll()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new PalindromeChecker();
    }
}
