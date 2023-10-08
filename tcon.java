import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TemperatureConverterApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature Converter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);
            frame.setLayout(new GridLayout(3, 2));
            
            JLabel inputLabel = new JLabel("Enter Temperature:");
            JTextField inputField = new JTextField();
            JComboBox<String> fromUnit = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
            JComboBox<String> toUnit = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
            JLabel resultLabel = new JLabel("Result:");
            JLabel resultText = new JLabel();

            JButton convertButton = new JButton("Convert");
            convertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double inputTemperature = Double.parseDouble(inputField.getText());
                        String from = fromUnit.getSelectedItem().toString();
                        String to = toUnit.getSelectedItem().toString();
                        double convertedTemperature = convertTemperature(inputTemperature, from, to);
                        resultText.setText(String.format("%.2f %s = %.2f %s", inputTemperature, from, convertedTemperature, to));
                    } catch (NumberFormatException ex) {
                        resultText.setText("Invalid input. Enter a valid number.");
                    }
                }
            });

            frame.add(inputLabel);
            frame.add(inputField);
            frame.add(fromUnit);
            frame.add(toUnit);
            frame.add(convertButton);
            frame.add(resultLabel);
            frame.add(resultText);

            frame.setVisible(true);
        });
    }

    private static double convertTemperature(double temperature, String from, String to) {
        if (from.equals("Celsius") && to.equals("Fahrenheit")) {
            return (temperature * 9 / 5) + 32;
        } else if (from.equals("Fahrenheit") && to.equals("Celsius")) {
            return (temperature - 32) * 5 / 9;
        } else {
            return temperature; // If units are the same, no conversion needed
        }
    }
}