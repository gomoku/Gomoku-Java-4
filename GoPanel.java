import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GoPanel extends JPanel {
    /**フィールド*/
    static final int MASUME = 10; //マス目の数　10×10
    int gW, gH; //マス目の幅
    private int panel[][] = new int[MASUME][MASUME]; //盤の状態を表す配列
    static final int BLANK = 0, BSTONE = 1, WSTONE = -1; //panel[][]配列に入れる定数
    static final int margin = 4; //石を描くときの余白
    boolean isBlackTurn = true; //黒の番か
    MouseAdapter MouseAd;
    JLabel status;
    Image buf;
    Graphics ct;
    /**コンストラクタ*/
    /**幅width,高さheightのGoPanelを作成
     *@param width ウィンドウの幅
     *@param height ウィンドウの高さ
     */
    public GoPanel(int width, int height) {
        setBackground(new Color(238, 231, 191)); //背景色を設定
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim); // パネルのサイズを設定
        setMinimumSize(dim);
        setMaximumSize(dim);
        panelSet(); //盤上を初期化
        MouseAd = new GoMouseEvent(this); //イベントリスナーを追加
        this.addMouseListener(MouseAd);
        status = new JLabel("  "); //ステイタスバーを追加
    }
    /**幅400,高さ400のGoPanelを作成*/
    public GoPanel() {
        this(400, 400);
    }
    public void paintComponent(Graphics g) {
        buf = createImage(this.getWidth() , this.getHeight());
        ct = buf.getGraphics();
        super.paintComponent(ct);
        drawGrid(ct); //盤上に線を描く
        drawStone(ct); //石を描く
        statusOut(); //ステイタスバーに文字を入れる
        g.drawImage(buf , 0 , 0 ,this);
    }
    /**石が置けるかどうか
     *@param x 盤での列(row)の番号-1
     *@param y 盤での行(column)の番号-1
     *@return 置けるならtrueを返す
     */
    boolean canPlace(int x, int y) {
        return panel[x][y] == BLANK;
    }
    /**黒石を置く
     *@param x 盤での列(row)の番号-1
     *@param y 盤での行(column)の番号-1
     */
    void putDownB(int x, int y) {
        panel[x][y] = BSTONE;
    }
    /**白石を置く
     *@param x 盤での列(row)の番号-1
     *@param y 盤での行(column)の番号-1
     */
    void putDownW(int x, int y) {
        panel[x][y] = WSTONE;
    }
    /**
     * 石を置く
     * @param x int 盤での列(row)の番号-1
     * @param y int 盤での行(column)の番号-1
     */
    void putDown(int x, int y) {
        if (isBlackTurn) {
            putDownB(x, y);
        } else {
            putDownW(x, y);
        }
    }
    /**
     * 石を取り除く
     * @param x int 盤での列(row)の番号-1
     * @param y int 盤での行(column)の番号-1
     */
    void removeStone(int x, int y) {
        if (!canPlace(x, y)) {
            panel[x][y] = BLANK;
        } else {
            System.out.println("(" + x + "," + y +
                               ") から石を取り除こうとしましたがそこには石はありません");//test
        }
    }
    /**
     * パネルの状態を返す
     * @param x int 盤での列(row)の番号-1
     * @param y int 盤での行(column)の番号-1
     * @return int　BLANK = 0, BSTONE = 1, WSTONE = -1
     */
    int getPanelState(int x,int y){
        return panel[x][y];
    }
    /**盤上の初期化*/
    void panelSet() {
        for (int x = 0; x < panel.length; x++) {
            for (int y = 0; y < panel[x].length; y++) {
                panel[x][y] = BLANK;
            }
        }
    }
    /**盤上に石を置けるマスがない？*/
    boolean noBlank(){
        for (int x = 0; x < panel.length; x++) {
            for (int y = 0; y < panel[x].length; y++) {
                if(panel[x][y] == BLANK){
                     return false;
                }
            }
        }
        return true;
    }
    /**ステイタスバーに文字を表示*/
    void statusOut() {
            if (isBlackTurn) { //黒の番のとき
                status.setText("Now is Black turn .");
            } else { //白の番のとき
                status.setText("Now is White turn .");
            }
    }
    /**盤上に線を描く
     *@param g Graphics class
     */
    void drawGrid(Graphics g) {
        gW = getWidth() / MASUME; //マスの幅
        gH = getHeight() / MASUME;
        g.setColor(Color.black);
        for (int i = 0; i <= MASUME; i++) { //横の線を引く
            g.drawLine(0, (int) ((0.5 + i) * gH), getWidth(),
                       (int) ((0.5 + i) * gH));
        }
        for (int i = 0; i <= MASUME; i++) { //縦の線を引く
            g.drawLine((int) ((0.5 + i) * gW), 0, (int) ((0.5 + i) * gW),
                       getHeight());
        }
    }
    /**石を描く
     *@param g Graphics class
     */
    void drawStone(Graphics g) {
        for (int x = 0; x < panel.length; x++) {
            for (int y = 0; y < panel[x].length; y++) {
                if (panel[x][y] == BSTONE) { //該当箇所が黒い石
                    g.setColor(Color.black);
                    g.drawOval(x * gW + margin, y * gH + margin,
                               gW - 2 * margin, gH - 2 * margin);
                    g.drawOval(x * gW + margin + 1, y * gH + margin + 1,
                               gW - 2 * margin - 2, gH - 2 * margin - 2);
                    g.fillOval(x * gW + margin, y * gH + margin,
                               gW - 2 * margin, gH - 2 * margin);
                }
                if (panel[x][y] == WSTONE) { //該当箇所が白い石
                    g.setColor(Color.black);
                    g.drawOval(x * gW + margin, y * gH + margin,
                               gW - 2 * margin, gH - 2 * margin);
                    g.drawOval(x * gW + margin + 1, y * gH + margin + 1,
                               gW - 2 * margin - 2, gH - 2 * margin - 2);
                    g.setColor(Color.white);
                    g.fillOval(x * gW + margin + 1, y * gH + margin + 1,
                               gW - 2 * margin - 2, gH - 2 * margin - 2);
                }
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("GOMOKU");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ウィンドウを閉じたときプログラムを終了
        Container cont = frame.getContentPane();
        cont.setLayout(new BorderLayout());
        GoPanel gop = new GoPanel();
        cont.setBackground(Color.white);
        cont.add(gop, BorderLayout.CENTER);
        cont.add(gop.status, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
