public class EvaluationWin {
    GoPanel goPanel;
    static final int WIN = 1, NOT_WIN = 0;
    int stone;
    public EvaluationWin(GoPanel g) {
        this.goPanel = g;
    }
    void setForBlack() { //黒石評価の準備
        stone = GoPanel.BSTONE;
    }
    void setForWhite() { //白石評価の準備
        stone = GoPanel.WSTONE;
    }
    /**
     *
     * @param startX int　開始位置（X）
     * @param startY int　開始位置（Y）
     * @param dX int　変化量X
     * @param dY int　変化量Y
     * @param n int　評価するマスの数
     * @return int 勝なら１、非勝なら０
     */
    int evaluateLine(int startX, int startY, int dX, int dY, int n) {
        int stoneNumber = 0;
        for (int i = 0; i < n; i++) {
            if (goPanel.getPanelState(startX-1 + i * dX,startY-1 + i * dY) == stone) {//石なら
                stoneNumber++;
                if(stoneNumber>=5){//5個以上連続なら
                    return WIN;
                }
            }
            else{//石以外
                stoneNumber=0;
            }
        }
        return NOT_WIN;
    }
    /**
     * 縦の列を評価
     * @return int　勝なら1,2,3...  , 非勝なら０
     */
    int evaluateRow(){
        int sum=0;
        for(int row=1;row<=goPanel.MASUME;row++){
            sum=sum+evaluateLine(row,1,0,1,goPanel.MASUME);
        }
        return sum;
    }
    /**
     * 横の行を評価
     * @return int　勝なら1,2,3...  , 非勝なら０
     */
    int evaluateColumn(){
        int sum=0;
        for(int col=1;col<=goPanel.MASUME;col++){
            sum=sum+evaluateLine(1,col,1,0,goPanel.MASUME);
        }
        return sum;
    }
    /**
     * 斜めの列を評価
     * @return int　勝なら1,2,3...  , 非勝なら０
     */
    int evaluateTilt(){
        int sum=0;
        sum=sum+evaluateLine(1,1,1,1,GoPanel.MASUME);
        sum=sum+evaluateLine(GoPanel.MASUME,1,-1,1,GoPanel.MASUME);
        for(int k=2;k<=GoPanel.MASUME-4;k++){
            sum=sum+evaluateLine(1,k,1,1,GoPanel.MASUME+1-k);
            sum=sum+evaluateLine(k,1,1,1,GoPanel.MASUME+1-k);
            sum=sum+evaluateLine(GoPanel.MASUME+1-k,1,-1,1,GoPanel.MASUME+1-k);
            sum=sum+evaluateLine(GoPanel.MASUME,k,-1,1,GoPanel.MASUME+1-k);
        }
        return sum;
    }
    /**
     * 黒石の勝ち、非勝を評価
     * @return int　勝ちなら1,2,3,4... ,非勝なら０
     */
    int evaluateBlack(){
        setForBlack();
        return evaluateRow()+evaluateColumn()+evaluateTilt();
    }
    /**
     * 白石の勝ち、非勝を評価
     * @return int　勝ちなら1,2,3,4... ,非勝なら０
     */
    int evaluateWhite(){
        setForWhite();
        return evaluateRow()+evaluateColumn()+evaluateTilt();
    }
    /**人の勝、非勝
     *@return int 勝ちなら1,2,3,4... ,非勝なら０
     */
    int evaluateOnMan(){
        return evaluateBlack();
    }
    /**CPUの勝、非勝
     *@return int 勝ちなら1,2,3,4... ,非勝なら０
     */
    int evaluateOnCPU(){
        return evaluateWhite();
    }
}
