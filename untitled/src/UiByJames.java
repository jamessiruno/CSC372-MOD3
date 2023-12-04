import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UiByJames extends JFrame {
    private JTextArea textArea;

    public UiByJames() {
        // Set up the main frame
        setTitle("Simple UI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create the File menu
        JMenu fileMenu = new JMenu("Menu");
        menuBar.add(fileMenu);

        // Create menu items
        JMenuItem showDateTimeItem = new JMenuItem("What's the date today?");
        JMenuItem writeToLogFileItem = new JMenuItem("Show me the logs");
        JMenuItem changeBackgroundColorItem = new JMenuItem("I'm feeling orange-y");
        JMenuItem exitItem = new JMenuItem("Time to roll out");

        // Add menu items to the File menu
        fileMenu.add(showDateTimeItem);
        fileMenu.add(writeToLogFileItem);
        fileMenu.add(changeBackgroundColorItem);
        fileMenu.add(exitItem);

        // Add action listeners to menu items
        showDateTimeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDateTime();
            }
        });

        writeToLogFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeToLogFile();
            }
        });

        changeBackgroundColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBackgroundColor();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Create a text area
        textArea = new JTextArea();
        textArea.setEditable(false);

        // Add text area to the frame
        getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void showDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(new Date());
        textArea.append("Date and Time: " + dateTime + "\n");
    }

    private void writeToLogFile() {
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(this, "Content written to log.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to log.txt: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeBackgroundColor() {
        float orangeHue = 30 / 360.0f; // Orange hue in HSB color model
        float randomHue = orangeHue + (new Random().nextFloat() * 0.1f); // Random hue near orange

        if (randomHue > 1.0f) {
            randomHue -= 1.0f;
        }

        Color color = Color.getHSBColor(randomHue, 1, 1);

        textArea.setBackground(color);
        getContentPane().setBackground(color);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UiByJames().setVisible(true);
            }
        });
    }
}
