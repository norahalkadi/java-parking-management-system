import java.io.*;

public class ParkingLL {
    private ParkingNode head;
    private int size;

    public ParkingLL() {
        head = null;
        size = 0;
    }

    public void addData(ParkingNode newNode) {
        if (head != null) {
            ParkingNode temporaryNode = head;
            while(temporaryNode.next!=null){
                temporaryNode = temporaryNode.next;
            }
            temporaryNode.next = newNode;
        } else {
            head = newNode;
        }
        size++;
    }


    public void readDataFromFile() throws IOException {
        BufferedReader file = new BufferedReader(new FileReader("parking_input.txt"));
        String readerLine;
        while ((readerLine = file.readLine()) != null) {
            String[] fields = readerLine.split(";");
            String lotTYPE = fields[0].trim();
            int classType = Integer.parseInt(fields[1].trim());
            int dayType = Integer.parseInt(fields[2].trim());
            int time = Integer.parseInt(fields[3].trim().replace(";", ""));
            ParkingNode newNode = new ParkingNode(lotTYPE, classType, dayType, time);
            addData(newNode);
        }
        file.close();
    }

    public void processTheData(){
        ParkingNode tempNode = head;
        for(int i = 0; i<size;i++){
            double fees = getFees(tempNode);

            if(tempNode.myDayType == 2 || tempNode.myDayType == 3 || tempNode.myDayType == 4){
                if (tempNode.myTime >= 6 && tempNode.myTime < 8)  {
                    fees = fees * 0.85;
                }
                if (tempNode.myTime >= 8 && tempNode.myTime < 10 ||tempNode.myTime == 12 ) {
                    fees = fees * 1.15;
                }
                if (tempNode.myTime >= 15 && tempNode.myTime < 18) {
                    fees = fees * 0.85;
                }
                if (tempNode.myTime >= 18) fees= 0;
            }

            if ((tempNode.myPermitClass == 2 || tempNode.myPermitClass == 3 || tempNode.myPermitClass == 4) && tempNode.myParkingLOT.startsWith("P") ) {
                fees =  fees * 0.85;
            }else if((tempNode.myPermitClass == 1 || tempNode.myPermitClass == 2) && (tempNode.myParkingLOT.equals("B102") || tempNode.myParkingLOT.equals("B104")) || (tempNode.myPermitClass == 1 || tempNode.myPermitClass == 3) && (tempNode.myParkingLOT.startsWith("B") && !(tempNode.myParkingLOT.equals("B102") || tempNode.myParkingLOT.equals("B104")))){
                fees =  fees * 1.15;
            }  else {
                fees = fees * 1;
            }

            fees = fees * 1.15;

            tempNode.fees = fees;
            tempNode = tempNode.next;
        }
    }

    private static double getFees(ParkingNode tempNode) {
        double userFees;
        int permitClasOfUser = tempNode.myPermitClass;
        if(permitClasOfUser == 1){
            userFees = 20;
        } else if (permitClasOfUser == 2) {
            userFees = 40;
        } else if(permitClasOfUser == 3){
            userFees = 70;
        } else if(permitClasOfUser == 4){
            userFees = 100;
        } else{
            userFees = 0;
        }

        int dayTypeUser = tempNode.myDayType;
        if(dayTypeUser == 1){
            userFees = userFees*2;
        } else if(dayTypeUser == 2){
            userFees = userFees * 1.25;
        } else if (dayTypeUser == 3) {
            userFees = userFees ;
        } else if (dayTypeUser == 4) {
            userFees = userFees * 0.75;
        } else{
            userFees = 0;
        }
        return userFees;
    }

    public void printDataToFile() {
        ParkingNode tempNode = head;
        try (BufferedWriter Buffer = new BufferedWriter(new FileWriter("output.txt"))) {
            while (tempNode != null) {
                Buffer.write(tempNode.myParkingLOT + "; " + tempNode.myPermitClass + "; " + tempNode.myDayType + "; " + tempNode.myTime + "; " + Math.round(tempNode.fees));
                Buffer.newLine();
                tempNode = tempNode.next;
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
