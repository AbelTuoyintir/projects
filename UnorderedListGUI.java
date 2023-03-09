import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UnorderedListGUI extends JFrame implements ActionListener {

    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JTextField textField;
    private JButton addToFrontButton;
    private JButton addToRearButton;
    private JButton addAfterButton;
    private JButton removeButton;

    public UnorderedListGUI() {
        super("Unordered List");

        // Create the list model and list
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the text field
        textField = new JTextField(20);

        // Create the buttons
        addToFrontButton = new JButton("Add to Front");
        addToRearButton = new JButton("Add to Rear");
        addAfterButton = new JButton("Add After");
        removeButton = new JButton("Remove");

        // Add action listeners to the buttons
        addToFrontButton.addActionListener(this);
        addToRearButton.addActionListener(this);
        addAfterButton.addActionListener(this);
        removeButton.addActionListener(this);

        // Create the panel for the buttons and text field
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(textField);
        buttonPanel.add(addToFrontButton);
        buttonPanel.add(addToRearButton);
        buttonPanel.add(addAfterButton);
        buttonPanel.add(removeButton);

        // Add the components to the content pane
        Container contentPane = getContentPane();
        contentPane.add(new JScrollPane(list), BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Set the size and make the frame visible
        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Add to Front")) {
            String newItem = textField.getText();
            listModel.add(0, newItem);
        } else if (command.equals("Add to Rear")) {
            String newItem = textField.getText();
            listModel.addElement(newItem);
        } else if (command.equals("Add After")) {
            String newItem = textField.getText();
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.add(selectedIndex+1, newItem);
            }
        } else if (command.equals("Remove")) {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        }

        // Clear the text field
        textField.setText("");
    }

    public static void main(String[] args) {
        UnorderedListGUI gui = new UnorderedListGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
