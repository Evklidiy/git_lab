import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SneakersGUI {
    private final List<Sneakers> sneakersList = new ArrayList<>();
    private final DefaultTableModel tableModel;

    public SneakersGUI() {
        // Инициализация списка кроссовок
        initializeSneakers();

        // Основное окно
        JFrame frame = new JFrame("Sneakers Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Таблица для отображения данных
        String[] columnNames = {"Name", "Cost", "Producer", "Country", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Заполнение таблицы существующими данными
        loadSneakersIntoTable();

        // Панель ввода данных
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        JTextField nameField = new JTextField();
        JTextField costField = new JTextField();
        JTextField producerField = new JTextField();
        JTextField countryField = new JTextField();
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Football", "Basketball", "Running"});

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Cost:"));
        inputPanel.add(costField);
        inputPanel.add(new JLabel("Producer:"));
        inputPanel.add(producerField);
        inputPanel.add(new JLabel("Country:"));
        inputPanel.add(countryField);
        inputPanel.add(new JLabel("Type:"));
        inputPanel.add(typeComboBox);

        // Кнопки
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton calculateButton = new JButton("Calculate");

        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Панель для отображения результатов расчетов
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Добавление компонентов в окно
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(calculateButton, BorderLayout.SOUTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.EAST);

        // Действия для кнопок
        addButton.addActionListener((ActionEvent e) -> {
            try {
                String name = nameField.getText();
                double cost = Double.parseDouble(costField.getText());
                String producer = producerField.getText();
                String country = countryField.getText();
                String type = (String) typeComboBox.getSelectedItem();

                Producer producerObj = new Producer(producer, country);
                Sneakers sneaker = switch (type) {
                    case "Football" -> new FootballSneakers(name, cost, producerObj);
                    case "Basketball" -> new BasketballSneakers(name, cost, producerObj);
                    case "Running" -> new RunningSneakers(name, cost, producerObj);
                    default -> throw new IllegalArgumentException("Invalid type");
                };

                sneakersList.add(sneaker);
                tableModel.addRow(new Object[]{name, cost, producer, country, type});
                clearFields(nameField, costField, producerField, countryField);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input: " + ex.getMessage());
            }
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                sneakersList.remove(selectedRow);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Select a row to delete.");
            }
        });

        calculateButton.addActionListener((ActionEvent e) -> {
            StringBuilder result = new StringBuilder();
            result.append("Unique Producers: ").append(CountUniqueProducers.count(sneakersList)).append("\n\n");

            Map<String, Double> avgCostByType = CalculateAverageCostByType.calculate(sneakersList);
            result.append("Average Cost by Type:\n").append(avgCostByType).append("\n\n");

            Map<String, Double> avgCostByProducer = CalculateAverageCostByProducer.calculate(sneakersList);
            result.append("Average Cost by Producer:\n").append(avgCostByProducer).append("\n");

            resultArea.setText(result.toString());
        });

        frame.setVisible(true);
    }

    // Метод для инициализации изначального списка кроссовок
    private void initializeSneakers() {
        sneakersList.add(new FootballSneakers("Nike Mercurial", 100.0, new Producer("Nike", "США")));
        sneakersList.add(new BasketballSneakers("Adidas HardCourt", 120.0, new Producer("Adidas", "Германия")));
        sneakersList.add(new FootballSneakers("Puma Future", 90.0, new Producer("Puma", "Германия")));
        sneakersList.add(new BasketballSneakers("Under Armour Curry", 130.0, new Producer("Under Armour", "США")));
        sneakersList.add(new RunningSneakers("Adidas UltraBoost", 150.0, new Producer("Adidas", "Германия")));
        sneakersList.add(new RunningSneakers("Nike Air Zoom", 140.0, new Producer("Nike", "США")));
        sneakersList.add(new BasketballSneakers("Reebok Pump", 110.0, new Producer("Reebok", "США")));
        sneakersList.add(new FootballSneakers("New Balance Furon", 95.0, new Producer("New Balance", "США")));
        sneakersList.add(new RunningSneakers("ASICS Gel-Nimbus", 160.0, new Producer("ASICS", "Япония")));
        sneakersList.add(new BasketballSneakers("Jordan Retro", 200.0, new Producer("Jordan", "США")));
    }

    // Метод для загрузки данных в таблицу
    private void loadSneakersIntoTable() {
        for (Sneakers sneaker : sneakersList) {
            tableModel.addRow(new Object[]{
                    sneaker.name(),
                    sneaker.cost(),
                    sneaker.producer().name(),
                    sneaker.producer().country(),
                    sneaker.type()
            });
        }
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SneakersGUI::new);
    }
}