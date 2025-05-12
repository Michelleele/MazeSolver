public class Dot {

    private boolean ifLeft;
    private boolean ifRight;
    private boolean ifTop;
    private boolean ifBottom;
    private int xCoordinate;
    private int yCoordinate;
    private boolean isDeadEnd;
    private boolean isEnd;

    public Dot(String[][] array, int x , int y) {
        ifLeft = false;
        ifRight = false;
        ifTop = false;
        ifBottom = false;
        xCoordinate = x;
        yCoordinate = y;
        isDeadEnd = false;
        isEnd = false;
        int count = 0;

        //checks left
        if (x - 1 >= 0) {
            if (array[y][x - 1].equals(".")) {
                ifLeft = true;
            }
            else {
                count ++;
            }
        }
        //checks right
        if (x + 1 < array[0].length) {
            if (array[y][x + 1].equals(".")) {
                ifRight = true;
            }
            else {
                count ++;
            }
        }

        //checks top
        if (y - 1 >= 0) {
            if (array[y - 1][x].equals(".")) {
                ifTop = true;
            }
            else {
                count ++;
            }
        }
        //checks bottom
        if (y + 1 < array.length) {
            if (array[y + 1][x].equals(".")) {
                ifBottom = true;
            }
            else {
                count ++;
            }
        }

        if (count == 1) {
            if (x == array[0].length && y == array.length) {
                isEnd = true;
            }
            else {
                isDeadEnd = true;
            }
        }

    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public boolean isIfBottom() {
        return ifBottom;
    }

    public void setIfBottom(boolean ifBottom) {
        this.ifBottom = ifBottom;
    }

    public boolean isIfTop() {
        return ifTop;
    }

    public void setIfTop(boolean ifTop) {
        this.ifTop = ifTop;
    }

    public boolean isIfRight() {
        return ifRight;
    }

    public void setIfRight(boolean ifRight) {
        this.ifRight = ifRight;
    }

    public boolean isIfLeft() {
        return ifLeft;
    }

    public void setIfLeft(boolean ifLeft) {
        this.ifLeft = ifLeft;
    }
}