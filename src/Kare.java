
public class Kare {

    boolean isFresh = true;
    int type = 0; // 1 Black, 2 White, 0 Empty

    public boolean reverse() {
        if (type == 0) {
            return false;
        }
        type = (type == 1) ? 2 : 1;
        isFresh = false;
        return true;
    }

    public void set(int s) {
        isFresh = false;
        type = s;
    }
}
