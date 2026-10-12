public class ParkingNode {
    String myParkingLOT;
    int myPermitClass, myDayType, myTime;
    double fees;
    ParkingNode next;

    public ParkingNode(String myParkingLOT, int myPermitClass, int myDayType, int myTime) {
        this.myParkingLOT = myParkingLOT;
        this.myPermitClass = myPermitClass;
        this.myDayType = myDayType;
        this.myTime = myTime;
        this.fees = 0.0;
        this.next = null;
    }
}
