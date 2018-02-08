package assets;

import enums.Direction;

import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.Vector;


public class Snake {

    /**
     * Variable storing what direction the assets is currently going
     */
    private Direction snakeDirection;

    /**
     * Variable keeping track of each individual joint or 'dot' making up the snake
     */
    private List<Joint> snakeJoints;

    /**
     * Constructor requiring the board dimensions, in order to ensure that the snake is spawned in bounds
     * @param boardCells the number of cells available on the board. Allowing us to center the snake
     */
    public Snake(int boardCells) {

        //Midpoint of the array
        int jointPoint = boardCells / 2;

        //We are starting with three joints
        snakeJoints = new Vector<>();

        for(int i = 0; i < 3; i++) {
            //Since the snake starts out going right, spawn the pieces to the left
            snakeJoints.add(new Joint((jointPoint - i), jointPoint));
        }
    }

    //Get the head coordinate of the snake
    public Joint getSnakeHeadLocation() {

        //The head will always be element zero of the list
        return snakeJoints.get(0);
        
    }

    public void moveSnake() {

        //Each joint of the snake travels in the direction of the one in front of it. Iterate through IDs
        for(int i = 0; i < snakeJoints.size(); i++) {

            Joint currentJoint = snakeJoints.get(i);

            //If we are working with the head (id 0) do not get an adjacent joint and follow the snake direction
            Direction leadDirection;

            if(i == 0) {
                leadDirection = snakeDirection;
            }

            else {

                //Otherwise, learn where the adjacent joint is in comparison and match that direction
                leadDirection = getDirectionOfAdjacentJoint(snakeJoints.get(i), snakeJoints.get(i - 1));
            }

            //If the direction is null for some reason, do nothing
            if(leadDirection == null)
                continue;

            //Otherwise, bump the coordinates of the joint given the direction it travels
            switch(leadDirection) {

                case UP: currentJoint.setY(currentJoint.getY() + 1); break;
                case DOWN: currentJoint.setY(currentJoint.getY() - 1); break;
                case LEFT: currentJoint.setX(currentJoint.getX() - 1); break;
                case RIGHT: currentJoint.setX(currentJoint.getX() + 1); break;
                
            }
        }
    }

    private Direction getDirectionOfAdjacentJoint(Joint trail, Joint lead) {

        //Case if the trail is to the right of the lead
        if(trail.getX() == (lead.getX() - 1)) {

            return Direction.LEFT;
        }

        //Case if the trail is to the left of the lead
        else if(trail.getX() == (lead.getX() + 1)) {

            return Direction.RIGHT;
        }

        //Case if the trail is above the lead
        else if(trail.getY() == (lead.getY() - 1)) {

            return Direction.DOWN;
        }

        //Case if the trail is below the lead
        else if(trail.getY() == (lead.getY() + 1)) {

            return Direction.UP;
        }

        else
            return null;
    }

    public Direction getSnakeDirection() {
        return snakeDirection;
    }

    public void setSnakeDirection(Direction snakeDirection) {
        this.snakeDirection = snakeDirection;
    }
}
