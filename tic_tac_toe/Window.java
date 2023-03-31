package tic_tac_toe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Window extends JFrame{
    public Logic logic = new Logic();
    public Button[] buttons = new Button[logic.length];
    
    public Window(){
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(316, 339);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);

        int index = 0;
        // Сперва y потом х чтобы кнопки нумеровались в нужном порядке. 
        // Иначе нужно передавать координаты в обратном порядке.
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                panel.add(buttons[index]=new Button(index, x, y,logic,this));
                index++;
            }
        }
        logic.startPosition();
        update();
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new Window();
            }
        });
    }


    public void update() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(logic.array[i]);
        }
    }
}
