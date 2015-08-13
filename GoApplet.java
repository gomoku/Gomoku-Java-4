import java.awt.*;
import javax.swing.*;
public class GoApplet extends JApplet {
    GoFrame goFrame;
    //アプレットの初期化
    public void init() {
        goFrame=new GoFrame();
        Container cont=this.getContentPane();
        cont.setBackground(Color.white);
        setJMenuBar(goFrame.menuBar);
        cont.add(goFrame, BorderLayout.CENTER);
        cont.add(goFrame.status, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
