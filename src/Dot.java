public class Dot {

    private boolean ifLeft;
    private boolean ifRight;
    private boolean ifTop;
    private boolean ifBottom;
    private int xCoordinate;
    private int yCoordinate;

    public Dot(String[][] array, int x , int y) {
        ifLeft = false;
        ifRight = false;
        ifTop = false;
        ifBottom = false;
        xCoordinate = x;
        yCoordinate = y;

        //checks left
        if (x - 1 >= 0) {
            if (array[y][x - 1].equals(".")) {
                ifLeft = true;
            }
        }
        //checks right
        if (x + 1 < array[0].length) {
            if (array[y][x + 1].equals(".")) {
                ifRight = true;
            }
        }
        //checks top
        if (y - 1 >= 0) {
            if (array[y - 1][x].equals(".")) {
                ifTop = true;
            }
        }
        //checks bottom
        if (y + 1 < array.length) {
            if (array[y + 1][x].equals(".")) {
                ifBottom = true;
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
