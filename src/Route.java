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
        Dot current = evaluateFirst(previous);

        int x = 0;

        while ((x != 2) && (current.getXCoordinate() < map[0].length - 1) || (current.getYCoordinate() < map.length - 1)) {
            dotArray.add(current);
            previous = new Dot(map, current.getXCoordinate(), current.getYCoordinate());
            current = evaluatePoint(previous);
            x ++;
        }
        dotArray.add(current);
    }

    private Dot evaluateFirst(Dot first) {
        if (first.isIfBottom()) {
            return new Dot(map, 0, 1);
        }
        if (first.isIfRight()) {
            return new Dot(map, 1, 0);
        }
        return null;
    }

    private ArrayList<Dot> findAllPossibleOutcome(Dot previous) {
        ArrayList<Dot> possiblePoints = new ArrayList<Dot>();
        if (previous.isIfBottom()) {
            possiblePoints.add(moveBottom(previous));
        }
        if (previous.isIfLeft()) {
            possiblePoints.add(moveLeft(previous));
        }
        if (previous.isIfTop()) {
            possiblePoints.add(moveTop(previous));
        }
        if (previous.isIfRight()) {
            possiblePoints.add(moveRight(previous));
        }
        return possiblePoints;
    }

    private Dot findSolution(ArrayList<Dot> allPossibleSolutions) {
        for (Dot dot : allPossibleSolutions) {
            if (!isFound(dot)) {
                return dot;
            }
        }
        return null;
    }

    private Dot moveTop(Dot previous) {
        int y = previous.getYCoordinate() - 1;
        return new Dot(map, previous.getXCoordinate(), y);
    }

    private Dot moveBottom(Dot previous) {
        int y = previous.getYCoordinate() + 1;
        return new Dot(map, previous.getXCoordinate(), y);
    }

    private Dot moveLeft(Dot previous) {
        int x = previous.getXCoordinate() - 1;
        return new Dot(map, x, previous.getYCoordinate());
    }

    private Dot moveRight(Dot previous) {
        int x = previous.getXCoordinate() + 1;
        return new Dot(map, x, previous.getYCoordinate());
    }

    private boolean isFound(Dot target) {
        for (Dot dot : dotArray) {
            if ((dot.getYCoordinate() == target.getYCoordinate()) && (dot.getXCoordinate() == target.getXCoordinate())) {
                return true;
            }
        }
        return false;
    }

    private Dot evaluatePoint(Dot previousDot) {
        ArrayList<Dot> possiblePoints = findAllPossibleOutcome(previousDot);
        Dot solution = findSolution(possiblePoints);
        return solution;
    }

    public String toString() {
        String answer = "";
        int index = 0;
        for (Dot dot : dotArray) {
            answer += "(" + dot.getYCoordinate() + ", " + dot.getXCoordinate() + ") ";
            if (index != dotArray.size() - 1) {
                answer += "---> ";
            }
            index ++;
        }
        return answer;
    }

}