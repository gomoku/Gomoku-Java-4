import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Container;
import java.util.ArrayList;
public class GoFrame extends GoPanel {
    /**フィールド*/
    JMenuBar menuBar;
    JMenu menu;
    JMenu help;
    JMenuItem item1, item2, item3, item4;
    GoMenuEvent gme;
    ArrayList history;
    /**
     * コンストラクタ
     * @param width int ウィンドウの幅
     * @param height int　ウィンドウの高さ
     */
    public GoFrame(int width, int height) {
        super(width, height);
        gme=new GoMenuEvent(this);
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        help = new JMenu("Help");
        item1 = new JMenuItem("UnDo");
        item1.setActionCommand("UnDo");
        item1.addActionListener(gme);
        item2 = new JMenuItem("Restart");
        item2.setActionCommand("Restart");
        item2.addActionListener(gme);
        item3 = new JMenuItem("Version");
        item3.setActionCommand("Version");
        item3.addActionListener(gme);
        menu.add(item1);
        menu.add(item2);
        help.add(item3);
        menuBar.add(menu);
        menuBar.add(help);
        history = new ArrayList();
    }
    public GoFrame() {
        this(400, 400);
    }
    void putDownB(int x, int y) {
        super.putDownB(x, y);
        history.add(new int[] {x, y});
    }
    void putDownW(int x, int y) {
        super.putDownW(x, y);
        history.add(new int[] {x, y});
    }
    void putDown(int x, int y) {
        super.putDown(x, y);
        history.add(new int[] {x, y});
    }
    void panelSet() {
        super.panelSet();
        if(history!=null){
            history.clear();
        }
        super.isBlackTurn=true;
    }
 void unDo(){
     if(history.size()!=0){
         int point[] = (int[]) history.get(history.size()-1);
         super.removeStone(point[0], point[1]);
         history.remove(history.size() - 1);
         history.remove(history.size() - 1);
         super.isBlackTurn=!super.isBlackTurn;
     }
 }
    public static void main(String[] args) {
        GoFrame gop = new GoFrame();
        JFrame frame = new JFrame("GOMOKU");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //ウィンドウを閉じたときプログラムを終了
        Container cont = frame.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.setBackground(Color.white);
        cont.add(gop, BorderLayout.CENTER);
        cont.add(gop.status, BorderLayout.SOUTH);
        frame.setJMenuBar(gop.menuBar);
        frame.pack();
        frame.setVisible(true);
    }
}
