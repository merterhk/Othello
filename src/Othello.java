
public class Othello {

    Kare[][] board = new Kare[8][8];
    int p1 = 0, p2 = 0;
    int kareSay = 0;
    AI yz;

    public Othello() {
       
        refreshBoard();
    }

    public void refreshBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Kare();
            }
        }
        board[3][3].set(1);
        board[4][4].set(1);
        board[3][4].set(2);
        board[4][3].set(2);

        p1 = 2;
        p2 = 2;
        kareSay = 4;
        yz=new AI(this);
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[j][i].type + " ");
            }
            System.out.println("");
        }
    }

    public int[][] getBoard() {
        int[][] b = new int[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                b[i][j] = board[i][j].type;
            }
        }
        return b;
    }

    public boolean isAvaliable(int p) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].isFresh && check(p, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean put(int p, int x, int y) {
        if (board[x][y].isFresh) {
            int count = 0;

            count += (controlUp(p, x, y, 0, false) > 0) ? controlUp(p, x, y, 0, true) : 0;
            count += (controlDown(p, x, y, 0, false) > 0) ? controlDown(p, x, y, 0, true) : 0;
            count += (controlLeft(p, x, y, 0, false) > 0) ? controlLeft(p, x, y, 0, true) : 0;
            count += (controlRight(p, x, y, 0, false) > 0) ? controlRight(p, x, y, 0, true) : 0;
            count += (controlUpLeft(p, x, y, 0, false) > 0) ? controlUpLeft(p, x, y, 0, true) : 0;
            count += (controlUpRight(p, x, y, 0, false) > 0) ? controlUpRight(p, x, y, 0, true) : 0;
            count += (controlDownLeft(p, x, y, 0, false) > 0) ? controlDownLeft(p, x, y, 0, true) : 0;
            count += (controlDownRight(p, x, y, 0, false) > 0) ? controlDownRight(p, x, y, 0, true) : 0;


            if (count > 0) {
                board[x][y].set(p);

                p1 += (p == 1) ? count + 1 : -1 * count;
                p2 += (p == 2) ? count + 1 : -1 * count;
                kareSay++;
            }
            System.out.println("Pos p:" + p + " x:" + x + " y:" + y);
            //System.out.println("Put: p1=" + p1 + " p2=" + p2 + " kareSay=" + kareSay);
            return (count > 0) ? true : false;
        } else {
            return false;
        }
    }

    public boolean check(int p, int x, int y) {
        int count = 0;
        count += controlUp(p, x, y, 0, false);
        count += controlDown(p, x, y, 0, false);
        count += controlRight(p, x, y, 0, false);
        count += controlLeft(p, x, y, 0, false);
        count += controlUpRight(p, x, y, 0, false);
        count += controlUpLeft(p, x, y, 0, false);
        count += controlDownLeft(p, x, y, 0, false);
        count += controlDownRight(p, x, y, 0, false);
        return (count > 0) ? true : false;
    }

    //Control Methods... UP,DOWN,LEFT,RIGHT,UPRIGHT,UPLEFT,DOWNRIGHT,DOWNRIGHT
    public int controller(int p, int x, int y, int incX, int incY, int count, boolean reverse) {
        y += incY;
        x += incX;
        if (y < 0 || y > 7 || x < 0 || x > 7 || board[x][y].isFresh) { // işe yaramaz
            return 0;
        }

        if (board[x][y].type == p) { // Dışarı çıktıysa veya boş ise dur
            return count;
        }

        if (board[x][y].type != p) { // Farklıysa devam et
            count += 1;
            int a = controller(p, x, y, incX, incY, count, reverse);
            if (count > 0 && reverse) {
                //board[x][y].reverse();
                board[x][y].type = (board[x][y].type == 1) ? 2 : 1;
            }
            return a;
        }
        return -1; // Hatalı durum
    }

    public int controlUp(int p, int x, int y, int count, boolean reverse) {
        return controller(p, x, y, 0, -1, count, reverse);
    }

    public int controlDown(int p, int x, int y, int count, boolean reverse) {
        return controller(p, x, y, 0, 1, count, reverse);
    }

    public int controlLeft(int p, int x, int y, int count, boolean reverse) {
        return controller(p, x, y, -1, 0, count, reverse);
    }

    public int controlRight(int p, int x, int y, int count, boolean reverse) {
        return controller(p, x, y, +1, 0, count, reverse);
    }

    public int controlUpRight(int p, int x, int y, int count, boolean reverse) {
        return controller(p, x, y, 1, -1, count, reverse);
    }

    public int controlUpLeft(int p, int x, int y, int count, boolean reverse) {
        return controller(p, x, y, -1, -1, count, reverse);
    }

    public int controlDownLeft(int p, int x, int y, int count, boolean reverse) {
        return controller(p, x, y, -1, 1, count, reverse);
    }

    public int controlDownRight(int p, int x, int y, int count, boolean reverse) {
        return controller(p, x, y, 1, 1, count, reverse);
    }

    // Control Methods End.....
    public boolean isFinish() {
        if(kareSay >= 64 && p2>p1 || p1 == 0){
            System.out.println("BEYAZ KAZANDI");
        }else if(kareSay >= 64 && p1>p2 ||p2 == 0){
            System.out.println("SİYAH KAZANDI");
        }

        return (kareSay >= 64 || p1 == 0 || p2 == 0);
    }


    public void deneme() {
//        put(2, 2, 3);
//        printBoard();System.out.println("");
//        put(1, 2, 2);
//        printBoard();System.out.println("");
//        put(2, 2, 1);
//        printBoard();System.out.println("");
//        put(1, 1, 3);
//        printBoard();System.out.println("");
//        put(2, 2, 4);
//        printBoard();System.out.println("");


        put(2, 2, 3);
        printBoard();
        System.out.println("");
        put(1, 2, 2);
        printBoard();
        System.out.println("");
        put(2, 2, 1);
        printBoard();
        System.out.println("");
        put(1, 1, 1);
        printBoard();
        System.out.println("");
        put(2, 0, 1);
        printBoard();
        System.out.println("");
        put(1, 1, 0);
        printBoard();
        System.out.println("");
    }
//    public static void main(String[] args) {
//        Othello othello = new Othello();
//
//
////        int p, posX, posY, count;
////        p = 1;
////        posX = 0;
////        posY = 0;
////        count = 0;
////
////        System.out.println("");
////        System.out.println("x: " + posX + " y: " + posY + " ( p: " + p + ", count: " + count + " )");
////        System.out.println("    UP:" + othello.controlUp(p, posX, posY, count, false));
////        System.out.println("  DOWN:" + othello.controlDown(p, posX, posY, count, false));
////        System.out.println("  LEFT:" + othello.controlLeft(p, posX, posY, count, false));
////        System.out.println(" RIGHT:" + othello.controlRight(p, posX, posY, count, false));
////        System.out.println("  UP_L:" + othello.controlUpLeft(p, posX, posY, count, false));
////        System.out.println("  UP_R:" + othello.controlUpRight(p, posX, posY, count, false));
////        System.out.println("DOWN_L:" + othello.controlDownLeft(p, posX, posY, count, false));
////        System.out.println("DOWN_R:" + othello.controlDownRight(p, posX, posY, count, false));
//
//        othello.deneme();
////        othello.printBoard();
//    }
}
