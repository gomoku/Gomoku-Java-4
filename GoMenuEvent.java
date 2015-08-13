GoMouseEventimport java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
public class GoMenuEvent implements ActionListener {
    private GoFrame goFrame;
    public GoMenuEvent(GoFrame go) {
        goFrame = go;
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e ActionEvent
     * @todo この java.awt.event.ActionListener メソッドを実装
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("UnDo")){
            goFrame.unDo();
            }
        if(command.equals("Restart")){
            goFrame.panelSet();
        }
        if(command.equals("Version")){
            JOptionPane.showMessageDialog(goFrame,
            new String[]{"五目並べ","Version 1.0","Author :Toru Mano"},
             "MESSAGE",JOptionPane.INFORMATION_MESSAGE);
        }
        goFrame.repaint();
    }
}
