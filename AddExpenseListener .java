import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

 class ExpenseTracker {
    private JFrame frame;
    private JTextField descriptionField;
    private JTextField amountField;
    private JComboBox<String> categoryComboBox;
    private DefaultListModel<String> expensesListModel;
    private JList<String> expensesList;
    private ArrayList<Expense> expenses;

    public ExpenseTracker() {
        frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        expenses = new ArrayList<>();

        // Create expense input fields
        JPanel expenseInputPanel = new JPanel();
        descriptionField = new JTextField(20);
        amountField = new JTextField(10);
        String[] categories = {"Groceries", "Transportation", "Entertainment", "Other"};
        categoryComboBox = new JComboBox<>(categories);
        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(new AddExpenseListener());

        expenseInputPanel.add(new JLabel("Description:"));
        expenseInputPanel.add(descriptionField);
        expenseInputPanel.add(new JLabel("Amount (₹):"));
        expenseInputPanel.add(amountField);
        expenseInputPanel.add(new JLabel("Category:"));
        expenseInputPanel.add(categoryComboBox);
        expenseInputPanel.add(addButton);

        // Create the list to display expenses
        expensesListModel = new DefaultListModel<>();
        expensesList = new JList<>(expensesListModel);
        JScrollPane listScrollPane = new JScrollPane(expensesList);

        frame.setLayout(new BorderLayout());
        frame.add(expenseInputPanel, BorderLayout.NORTH);
        frame.add(listScrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Listener for the "Add Expense" button
    private class AddExpenseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String description = descriptionField.getText();
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
                String category = (String) categoryComboBox.getSelectedItem();
                expenses.add(new Expense(description, amount, category));
                updateExpensesList();
                descriptionField.setText("");
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount.");
            }
        }
    }

    // Update the list of expenses
    private void updateExpensesList() {
        expensesListModel.clear();
        for (Expense expense : expenses) {
            expensesListModel.addElement(expense.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTracker());
    }
}

class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return description + " - $" + df.format(amount) + " (" + category + ")";
    }
}