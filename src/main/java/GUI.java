import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GUI extends JFrame implements ActionListener {

    private ArrayList<JButton> buttons;
    private ArrayList<String[]> pairs;
    private Client client;
    private CurrencyConverter converter;
    private JsonFileHandler jsonFileHandler;

    public GUI(Client client) {
        this.buttons = new ArrayList<>();
        this.pairs = new ArrayList<>();
        this.client = client;
        this.converter = new CurrencyConverter();
        this.jsonFileHandler = new JsonFileHandler();
        setTitle("Currency Converter");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pairs.add(new String[]{"USD", "ARS"});
        pairs.add(new String[]{"ARS", "USD"});
        pairs.add(new String[]{"USD", "BRL"});
        pairs.add(new String[]{"BRL", "USD"});
        pairs.add(new String[]{"USD", "COP"});
        pairs.add(new String[]{"COP", "USD"});

        for (int i = 0; i < pairs.size(); i++) {
            JButton button = new JButton(pairs.get(i)[0] + " - " + pairs.get(i)[1]);
            button.addActionListener(this);
            buttons.add(button);
        }
        JButton button = new JButton("Filtrar");
        button.addActionListener(this);
        buttons.add(button);
        button = new JButton("Salir");
        button.addActionListener(this);
        buttons.add(button);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(buttons.size(), 1, 0, 10));
        for (int i = 0; i < buttons.size(); i++) {
            panel.add(buttons.get(i));
        }
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Salir")) {
            System.exit(0);
        }  else if (command.equals("Filtrar")) {
            String currency = JOptionPane.showInputDialog(this, "Ingresa el monto a convertir de la moneda qe desea: ").toUpperCase();
            Map<String, Double> currencyInformation;
            try {
                currencyInformation = client.filterRates(currency);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            StringBuilder message = new StringBuilder("Tasas de cambio para " + currency + ":\n");
            message.append(String.format("%-10s %10s\n", "Moneda", "Tasa"));
            message.append("----------------------------\n");
            for (Map.Entry<String,Double> pair : currencyInformation.entrySet()) {
                message.append(String.format("%-10s %10.4f\n", pair.getKey(), pair.getValue()));
            }
            JTextArea textArea = new JTextArea(message.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(200, 600)); // Tamaño del cuadro de diálogo
            FilterRecord record = new FilterRecord(currency,currencyInformation);
            try {
                jsonFileHandler.saveFilterToFile(record);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null, scrollPane, "Resultados de Conversión", JOptionPane.INFORMATION_MESSAGE);

        } else {
            for (String[] pair : pairs) {
                if (command.equals(pair[0] + " - " + pair[1])) {
                    String amountStr = JOptionPane.showInputDialog(this, "Ingresa el monto a convertir de " + pair[0] + " a " + pair[1]);
                    try {
                        double amount = Double.parseDouble(amountStr);
                        String exchangeRateStr = client.consultExchangeRate(pair[0], pair[1]);
                        double exchangeRate = Double.parseDouble(exchangeRateStr);
                        double result =converter.convert(amount,exchangeRate);
                        String message = "Tipo de cambio " + pair[0] + " a " + pair[1] + ": " + exchangeRate +
                                "\nEl resultado de cambiar " + amountStr + " " + pair[0] +" es " + String.format("%.2f",result) + " " + pair[1];
                        ConversionRecord record = new ConversionRecord(pair[0],pair[1], amount, exchangeRate, result);
                        jsonFileHandler.saveConversionToFile(record);
                        JOptionPane.showMessageDialog(this, message);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al consultar el tipo de cambio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
        }
    }
}
