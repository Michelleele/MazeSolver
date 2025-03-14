import java.util.ArrayList;
import java.util.Arrays;

public class Route {

    private ArrayList<Dot> dotArray = new ArrayList<Dot>();
    private String[][] map;

    public Route (String[][] mapArray) {
        map = mapArray;
        appendDotArray();
    }

    private void appendDotArray() {
        Dot previous = new Dot(map, 0, 0);
        dotArray.add(previous);
        int[] nextCoordinates = evaluateFirst(previous);

        Dot current = new Dot(map, nextCoordinates[0], nextCoordinates[1]);

        while ((current.getXCoordinate() < map[0].length - 1) || (current.getYCoordinate() < map.length - 1)) {
            nextCoordinates = evaluatePoint(current, previous);

            System.out.print( "(" + current.getYCoordinate() + "," + current.getXCoordinate() + ")" + " ");


            current.setYCoordinate(nextCoordinates[0]);
            current.setXCoordinate(nextCoordinates[1]);
            previous.setYCoordinate(current.getYCoordinate());
            previous.setXCoordinate(current.getXCoordinate());


        }


    }

    //[x, y]

    private int[] evaluateFirst(Dot first) {
        if (first.isIfBottom()) {
            first.setMove("b");
            return new int[] {0, 1};
        }
        if (first.isIfRight()) {
            first.setMove("r");
            return new int[] {1, 0};
        }
        return new int[] {-1, -1};
    }

    private boolean isFound(Dot target) {
        for (Dot dot : dotArray) {
            if ((dot.getYCoordinate() == target.getXCoordinate()) && (dot.getXCoordinate() == target.getYCoordinate())) {
                return true;
            }
        }
        return false;
    }

    //returns an integer array with the points of the next point
    // [x, y]
    private int[] evaluatePoint(Dot thisDot, Dot previousDot) {
        int x = -1;
        int y = -1;

//        System.out.println("Top = " + thisDot.isIfTop());
//        System.out.println("previous move = " + previousDot.getMove());
//        System.out.println("Bottom = " + thisDot.isIfBottom());

        if (thisDot.isIfTop() && !previousDot.getMove().equals("b")) {
            System.out.println("hi");
            x = thisDot.getXCoordinate();
            y = thisDot.getYCoordinate() - 1;
            thisDot.setMove("t");
        }
        if (thisDot.isIfBottom() && !previousDot.getMove().equals("t")) {
            System.out.println("hit");
            x = thisDot.getXCoordinate();
            y = thisDot.getYCoordinate() + 1;
            thisDot.setMove("b");
        }
        if (thisDot.isIfLeft() && !previousDot.getMove().equals("r")) {
            x = thisDot.getXCoordinate() - 1;
            y = thisDot.getYCoordinate();
            thisDot.setMove("l");
        }
        if (thisDot.isIfRight() && !previousDot.getMove().equals("l")) {
            x = thisDot.getXCoordinate() + 1;
            y = thisDot.getYCoordinate();
            thisDot.setMove("r");
        }
        return new int[] {x, y};
    }

    public String toString() {
        String answer = "";
        int index = 0;
        for (Dot dot : dotArray) {
            answer += "(" + dot.getXCoordinate() + ", " + dot.getYCoordinate() + ") ";
            if (index != dotArray.size() - 1) {
                answer += "---> ";
            }
        }
        return answer;
    }

}
