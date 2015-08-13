import java.awt.event.MouseAdapter;
import java.awt.event.*;
import javax.swing.*;
public class GoMouseEvent extends MouseAdapter {
    /**フィールド*/
    private GoPanel goPanel;
    GoCPU cpu;
    EvaluationWin ew;
    /**コンストラクタ
     * @param g GoMouseEventが参照するGoPanel
     */
    public GoMouseEvent(GoPanel g) {
        this.goPanel = g;
        ew = new EvaluationWin(goPanel);
    }
    /**メソッド*/
    /**マウスがクリックされたら
     *@param me MouseEvent
     */
    public void mouseClicked(MouseEvent me) {
        int x = analyzeX(me.getX());
        int y = analyzeY(me.getY());
        if (goPanel.canPlace(x, y)) { //石置けるか
            goPanel.putDown(x, y);
            goPanel.repaint();
            winCheck();
            goPanel.isBlackTurn = !goPanel.isBlackTurn;
        } else { //石置けないとき
            dialog(new String[] {"You Can NOT ", "Put Down Stone There."});
            goPanel.repaint();
        }
    }
    /**勝敗、引き分けチェック*/
    void winCheck() {
        if (ew.evaluateBlack() != 0) {
            dialog(new String[] {"BLACK WIN!!", "Congratulations!"});
            goPanel.panelSet();
            goPanel.isBlackTurn = false;
            goPanel.repaint();
        }
        if (ew.evaluateWhite() != 0) {
            dialog(new String[] {"WHITE WIN!!", "Congratulations!"});
            goPanel.panelSet();
            goPanel.isBlackTurn = false;
            goPanel.repaint();
        }
        if (goPanel.noBlank()) {
            dialog(new String[] {"DRAW."});
            goPanel.panelSet();
            goPanel.isBlackTurn = false;
            goPanel.repaint();
        }
    }
    void dialog(String[] message) {
        JOptionPane.showMessageDialog(goPanel, message, "MESSAGE",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    /**座標をマス目にして返す(X)
     *@param x ウィンドウ上でのX座標
     *@return 盤での列(row)の番号
     */
    int analyzeX(int x) {
        return x / goPanel.gW;
    }
    /**座標をマス目にして返す(Y)
     *@param y  ウィンドウ上でのｙ座標
     *@return 盤での行(column)の番号
     */
    int analyzeY(int y) {
        return y / goPanel.gH;
    }
}
