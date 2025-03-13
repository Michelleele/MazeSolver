import java.util.ArrayList;
public class Route {

    private ArrayList<Dot> dotArray = new ArrayList<Dot>();
    private String[][] map;

    public Route (String[][] mapArray) {
        map = mapArray;
        appendDotArray();
    }

    private void appendDotArray() {
        Dot currentDot = new Dot(map, 0, 0);
        dotArray.add(currentDot);
        int[] nextPoint;

        //while the current dot is not at bottom right of map
        while ((currentDot.getXCoordinate() != map[0].length - 1) && (currentDot.getYCoordinate() != map.length - 1)) {

            nextPoint = evaluatePoint(currentDot);
            currentDot = new Dot(map, nextPoint[0], nextPoint[1]);

            if (!isFound(currentDot)) {
                dotArray.add(currentDot);
            }
            // else statement for part two: fork intersections
//            else {
//
//
//
//            }

        }
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
    private int[] evaluatePoint(Dot dot) {
        int x = -1;
        int y = -1;
        if (dot.isIfBottom()) {
            x = dot.getXCoordinate();
            y = dot.getYCoordinate() + 1;
        }
        if (dot.isIfTop()) {
            x = dot.getXCoordinate();
            y = dot.getYCoordinate() - 1;
        }
        if (dot.isIfLeft()) {
            x = dot.getXCoordinate() - 1;
            y = dot.getYCoordinate();
        }
        if (dot.isIfRight()) {
            x = dot.getXCoordinate() + 1;
            y = dot.getYCoordinate();
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
