import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    public static int calculateLiftTicks(final int[] weights, final int[] destinedFloors, final int floorCount,
                                         final int maxLiftPeopleCount, final int maxLiftWeight) {

        int tick = 0;

        int movedPeopleCount = 0;


        int elevatorWeight = 0;
        int elevatorPeopleCount = 0;

        while (movedPeopleCount < weights.length) {
            elevatorWeight += weights[movedPeopleCount];
            elevatorPeopleCount++;
            movedPeopleCount++;
//            LOGGER.log(Level.INFO, String.format("Load people, elevatorWeight: %d, elevatorPeopleCount: %d, movedPeopleCount: %d", elevatorWeight, elevatorPeopleCount, movedPeopleCount));
            if (elevatorWeight >= maxLiftWeight || elevatorPeopleCount >= maxLiftPeopleCount || movedPeopleCount >= weights.length) {
//                LOGGER.log(Level.INFO, String.format("People loaded, elevatorWeight: %d, elevatorPeopleCount: %d", elevatorWeight, elevatorPeopleCount));
                //Elevator full
                tick++;
                int floor = 0;
                //Its is a trade-off between number of operations and memory allocation here. We could allocate an array
                // and track people currently loaded on the elevator but I assume that it is cheaper to loop through
                // them on every floor
                int currentRunLoad = elevatorPeopleCount;
                while (elevatorPeopleCount > 0) {
                    LOGGER.log(Level.INFO, String.format("Move floor up, elevatorWeight: %d, elevatorPeopleCount: %d", elevatorWeight, elevatorPeopleCount));
                    floor++;
                    tick++;
                    boolean stoppedOnCurrentFloor = false;
                    for (int person = movedPeopleCount - currentRunLoad; person < movedPeopleCount; person++) {
                        LOGGER.log(Level.INFO, String.format("Check unload person: %d, movedPeopleCount: %d, currentRunLoad: %d", person, movedPeopleCount, currentRunLoad));
                        if (destinedFloors[person] == floor) {
                            stoppedOnCurrentFloor = true;
                            elevatorPeopleCount--;
                        }
                    }
                    if (stoppedOnCurrentFloor) {
                        LOGGER.log(Level.INFO, String.format("Unload people, elevatorWeight: %d, elevatorPeopleCount: %d", elevatorWeight, elevatorPeopleCount));
                        tick++;
                        //Reset
                        stoppedOnCurrentFloor = false;
                    }
                }
                while (floor > 0) {
                    LOGGER.log(Level.INFO, String.format("Move floor down, elevatorWeight: %d, elevatorPeopleCount: %d", elevatorWeight, elevatorPeopleCount));
                    floor--;
                    tick++;
                }
                //Reset
                elevatorWeight = 0;
            }
        }
        return tick;

    }
}
