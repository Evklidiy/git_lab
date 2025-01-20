import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class RadixSortGUI {

    public RadixSortGUI() {
        // Создание основного окна
        JFrame frame = new JFrame("Radix Sort");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Панель для ввода массива
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel("Введите массив чисел (через запятую):");
        JTextField inputField = new JTextField();
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);

        // Панель для отображения результатов
        JPanel outputPanel = new JPanel(new GridLayout(2, 1));
        JLabel originalArrayLabel = new JLabel("Исходный массив:");
        JTextArea originalArrayArea = new JTextArea();
        originalArrayArea.setEditable(false);
        outputPanel.add(originalArrayLabel);
        outputPanel.add(new JScrollPane(originalArrayArea));

        JLabel sortedArrayLabel = new JLabel("Отсортированный массив:");
        JTextArea sortedArrayArea = new JTextArea();
        sortedArrayArea.setEditable(false);
        outputPanel.add(sortedArrayLabel);
        outputPanel.add(new JScrollPane(sortedArrayArea));

        // Кнопка для сортировки
        JButton sortButton = new JButton("Выполнить сортировку");

        // Добавление компонентов в окно
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);
        frame.add(sortButton, BorderLayout.SOUTH);

        // Действие для кнопки сортировки
        sortButton.addActionListener((ActionEvent e) -> {
            try {
                // Считывание массива из поля ввода
                String inputText = inputField.getText();
                int[] array = Arrays.stream(inputText.split(","))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                // Отображение исходного массива
                originalArrayArea.setText(Arrays.toString(array));

                // Выполнение сортировки
                RadixSort radixSort = new RadixSort();
                radixSort.sort(array);

                // Отображение отсортированного массива
                sortedArrayArea.setText(Arrays.toString(array));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ошибка: Введите корректный массив чисел через запятую.", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Произошла ошибка: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Отображение окна
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RadixSortGUI::new);
    }
}