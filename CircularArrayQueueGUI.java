import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CircularArrayQueueGUI extends JFrame implements ActionListener {

    private CircularArrayQueue<Integer> queue;
    private JTextField inputField, outputField;

    public CircularArrayQueueGUI() {
        super("Circular Array Queue GUI");

        // Initialize the queue with a capacity of 10
        queue = new CircularArrayQueue<>(10);

        // Create the input and output fields
        inputField = new JTextField(10);
        outputField = new JTextField(10);
        outputField.setEditable(false);

        // Create the buttons
        JButton enqueueButton = new JButton("Enqueue");
        enqueueButton.addActionListener(this);

        JButton dequeueButton = new JButton("Dequeue");
        dequeueButton.addActionListener(this);

        // Create the panel and add the components to it
        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(enqueueButton);
        panel.add(dequeueButton);
        panel.add(outputField);

        // Add the panel to the frame
        getContentPane().add(panel, BorderLayout.CENTER);

        // Set the size and show the frame
        setSize(300, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CircularArrayQueueGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals("Enqueue")) {
            try {
                int value = Integer.parseInt(inputField.getText());
                queue.enqueue(value);
                outputField.setText("Enqueued " + value);
            } catch (NumberFormatException ex) {
                outputField.setText("Invalid input");
            }
        } else if (action.equals("Dequeue")) {
            Integer value = queue.dequeue();
            if (value != null) {
                outputField.setText("Dequeued " + value);
            } else {
                outputField.setText("Queue is empty");
            }
        }
    }

    // CircularArrayQueue implementation
    private static class CircularArrayQueue<E> {
        private E[] elements;
        private int head;
        private int tail;
        private int size;

        public CircularArrayQueue(int capacity) {
            elements = (E[]) new Object[capacity];
            head = 0;
            tail = 0;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == elements.length;
        }

        public void enqueue(E element) {
            if (isFull()) {
                resize();
            }
            elements[tail] = element;
            tail = (tail - 1 + elements.length) % elements.length;
            size++;
        }

        public E dequeue() {
            if (isEmpty()) {
                return null;
            }
            E element = elements[head];
            head = (head - 1 + elements.length) % elements.length;
            size--;
            return element;
        }

        private void resize() {
            int newCapacity = elements.length * 2;
            E[] newElements = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(head + i) % elements.length];
            }
            elements = newElements;
            head = 0;
            tail = size - 1;
        }
    }
}