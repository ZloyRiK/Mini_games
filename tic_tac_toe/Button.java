package tic_tac_toe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Button extends JButton{
    private int index;
    private Logic logic = new Logic();
    private Window window;

    //Если в цикле индекса кнопок сперва х потом у, то сюда передавать в обратном порядке
    public Button(int index, int x, int y, Logic logic, Window window){ 
        this.index = index;
        this.logic = logic;
        this.window = window;

        this.setBounds(x*100, y*100, 100, 100);
        this.addActionListener(new MyKeyAdapter());
    }

    public void actionLast(String text) {
        JOptionPane.showMessageDialog(window, text);
        logic.startPosition();
        window.update();
    }


    public boolean result(String symbol){
        if (symbol=="X") {
            actionLast("YOU LOST"); return true;
        }
        if (symbol=="O") {
            actionLast("YOU WON"); return true;
        }
        if (symbol.equals("deadlock")){
            actionLast("Nobody won"); return true;
        }
        return false;
    }

    private class MyKeyAdapter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            logic.positionUser(index);
            window.update();
            if(result(logic.result())) return;
            
            logic.positionPC();
            window.update();
            if(result(logic.result())) return;
        }
    }
}
