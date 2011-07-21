
import java.util.Vector;

public class AI {

    Othello othello;
    int[][] score = new int[8][8];
    boolean[][] kontrol = new boolean[8][8];

    public AI(Othello othello) {
        this.othello = othello;
        setScore();
        printBoard();
    }

    public void setScore() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (y == 1 || y == 6) {
                    score[x][y] = -6;
                } else {
                    score[x][y] = 0;

                }
                if (x == 1 || x == 6) {
                    score[x][y] -= 6;
                }
            }
        }

        score[0][0] = 60;
        score[0][7] = 60;
        score[7][0] = 60;
        score[7][7] = 60;
    }

    public AI_put getBest(int p) {
        AI_put best = null;
        Vector movs = new Vector();
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[0].length; j++) {
                if (othello.board[i][j].isFresh) {
                    if (othello.check(p, i, j)) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                        }
                        if (best == null) {
                            best = new AI_put(i, j, p, score[i][j]);
                            movs.add(best);
                        } else if (score[i][j] > best.point) {
                            movs.clear();
                            best = new AI_put(i, j, p, score[i][j]);
                            movs.add(best);
                        } else if (score[i][j] == best.point) {
                            movs.add(new AI_put(i, j, p, score[i][j]));
                        }
                    }
                }
            }
        }
//
//        if (movs.size() > 1) {
//            best= (AI_put) movs.elementAt((int) (Math.random() * movs.size()));
//        }

        return best;
    }

    public void printBoard() {
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[0].length; j++) {
                System.out.print(score[j][i] + " ");
            }
            System.out.println("");
        }
    }
}

class movin {

    int x, y, p;
    int point;
}
