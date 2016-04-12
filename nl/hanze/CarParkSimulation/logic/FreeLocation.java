package nl.hanze.CarParkSimulation.logic;

public final class FreeLocation {
    /**
     * The first free spot is on the first floor
     * on the first row, the first place
     */
    public static Location location = new Location(0,0,0);

    /**
     * Method to set the first free parking place that is available in the car park.
     */
    public static void setNext() {

        if(location.getPlace() == CarPark.getNumberOfPlaces() -1 ){

            if(location.getRow() == CarPark.getNumberOfRows() -1 ) {

                if (location.getFloor() == CarPark.getNumberOfFloors () -1) {
                    location = null;
                }else{
                    int nextFloor = location.getFloor() +1;
                    location = new Location(nextFloor, 0, 0);
                }
            }else{
                int nextRow = location.getRow() + 1;
                location = new Location(location.getFloor(),nextRow,0);
            }
        }else {
            int nextPlace = location.getPlace() + 1;
            location = new Location(location.getFloor(), location.getRow(),nextPlace);
        }

        /**
         * Make this method recursive until we have a free spot.
         */
        if(CarPark.getCar(location) != null){
            setNext();
        }

    }
}