import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LibraryUI extends JFrame {

    private JTextField titleField, authorField;
    private JTextArea bookListArea;

    public LibraryUI() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set a professional background color
        getContentPane().setBackground(new Color(245, 245, 245)); // Light gray background

        // Top Panel - Book Input
        JPanel inputPanel = createPanel(new GridLayout(3, 2, 10, 10), "Add New Book");
        inputPanel.add(createLabel("Book Title:"));
        titleField = createTextField();
        inputPanel.add(titleField);

        inputPanel.add(createLabel("Author:"));
        authorField = createTextField();
        inputPanel.add(authorField);

        JButton addButton = createButton("Add Book");
        addButton.addActionListener(e -> addBook());
        inputPanel.add(addButton);

        JButton clearButton = createButton("Clear Fields");
        clearButton.addActionListener(e -> clearFields());
        inputPanel.add(clearButton);

        // Center Panel - Book List Display
        JPanel listPanel = createPanel(new BorderLayout(), "Books in Library");
        bookListArea = new JTextArea();
        bookListArea.setEditable(false);
        bookListArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        bookListArea.setBackground(new Color(255, 255, 255)); // White background
        bookListArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(bookListArea);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel - Actions
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.setBackground(new Color(230, 230, 230)); // Slightly darker gray

        JButton listButton = createButton("List All Books");
        listButton.addActionListener(e -> listBooks());
        actionPanel.add(listButton);

        JButton exitButton = createButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        actionPanel.add(exitButton);

        // Add Panels to Frame
        add(inputPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(Color.WHITE);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return button;
    }

    private JPanel createPanel(LayoutManager layout, String title) {
        JPanel panel = new JPanel(layout);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                title,
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                Color.DARK_GRAY)
        );
        panel.setBackground(new Color(245, 245, 245));
        return panel;
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        if (!title.isEmpty() && !author.isEmpty()) {
            BookDAO.addBook(title, author);
            JOptionPane.showMessageDialog(this, "Book added successfully.");
            clearFields();
            listBooks();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
    }

    private void listBooks() {
        var books = BookDAO.listBooks();
        bookListArea.setText(String.join("\n", books));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryUI::new);
    }
}
