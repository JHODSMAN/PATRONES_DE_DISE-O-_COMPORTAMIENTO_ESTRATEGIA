import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JTextField num1Field;
    private JTextField num2Field;
    private JComboBox<String> operationBox;
    private JButton calculateButton;
    private JLabel resultLabel;
    private CalculatorContext context;

    public CalculatorGUI() {
        context = new CalculatorContext();
        
        setTitle("Calculadora - Patrón de estrategia");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label1 = new JLabel("Numero 1:");
        label1.setBounds(20, 20, 80, 25);
        add(label1);

        num1Field = new JTextField();
        num1Field.setBounds(100, 20, 150, 25);
        add(num1Field);

        JLabel label2 = new JLabel("Numero 2:");
        label2.setBounds(20, 60, 80, 25);
        add(label2);

        num2Field = new JTextField();
        num2Field.setBounds(100, 60, 150, 25);
        add(num2Field);

        operationBox = new JComboBox<>(new String[] { "Agregar", "Sustraer", "Multiplicar", "Dividir" });
        operationBox.setBounds(260, 20, 100, 25);
        add(operationBox);

        calculateButton = new JButton("Calcular");
        calculateButton.setBounds(260, 60, 100, 25);
        add(calculateButton);

        resultLabel = new JLabel("Resultados: ");
        resultLabel.setBounds(20, 100, 300, 25);
        add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            String operation = (String) operationBox.getSelectedItem();
            
            switch (operation) {
                case "Agregar":
                    context.setStrategy(new AdditionStrategy());
                    break;
                case "Sustraer":
                    context.setStrategy(new SubtractionStrategy());
                    break;
                case "Multiplicar":
                    context.setStrategy(new MultiplicationStrategy());
                    break;
                case "Dividir":
                    context.setStrategy(new DivisionStrategy());
                    break;
            }

            double result = context.executeStrategy(num1, num2);
            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
