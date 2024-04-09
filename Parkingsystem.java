import java.util.ArrayList;
import java.util.Scanner;

public class ParkingSystem {
    public static boolean inMethod(String[][][] t, int r, int c, int f, int x) {
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < r; k++) {
                    if (t[i][j][k].equals("free")) {
                        System.out.printf("Parking slot is allocated for your vehicle.%d at :\nRow=%d\nColumn=%d\nfloor=%d\n", x, k + 1, j + 1, i + 1);
                        t[i][j][k] = "vehicle" + x;
                        return true;
                    }
                }
            }
        }
        System.out.printf("All the parking slots are FULL, can't park vehicle.%d\n", x);
        return false;
    }

    public static void outMethod(String[][][] t, int r, int c, int f, int x) {
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < r; k++) {
                    if (t[i][j][k].equals("vehicle" + x)) {
                        t[i][j][k] = "free";
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the maximum number of rows in your parking area : ");
        int r = scanner.nextInt();
        System.out.print("Enter the maximum number of columns in your parking area : ");
        int c = scanner.nextInt();
        System.out.print("How many floors do you have for parking? : ");
        int f = scanner.nextInt();
        String[][][] totalCapacity = new String[f][c][r];
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < r; k++) {
                    totalCapacity[i][j][k] = "free";
                }
            }
        }

        System.out.println("\n\tEnter 'in' to park a new vehicle");
        System.out.println("\tEnter 'out.vehicle<number>' to unpark a parked vehicle with its number");
        System.out.println("\tEnter '--display-slots' to see the available slots in a 3D-list format");
        System.out.println("\tEnter 'close' to terminate the program");

        String process = scanner.nextLine();
        int vno = 0;
        ArrayList<Integer> vnolist = new ArrayList<>();
        while (!process.equals("close")) {
            if (process.equals("in")) {
                boolean check = inMethod(totalCapacity, r, c, f, vno + 1);
                if (check) {
                    vno++;
                    vnolist.add(vno);
                }
            } else if (process.equals("--display-slots")) {
                System.out.println("\n\tDataBase -> " + totalCapacity);
            } else {
                String vehicleNumber = process.replace("out.vehicle", "");
                try {
                    int vehicleNum = Integer.parseInt(vehicleNumber);
                    if (vnolist.contains(vehicleNum)) {
                        vnolist.remove(Integer.valueOf(vehicleNum));
                        outMethod(totalCapacity, r, c, f, vehicleNum);
                        System.out.printf("Vehicle.%d is unparked successfully\n", vehicleNum);
                    } else {
                        System.out.println("The vehicle doesn't exist to be unparked");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Enter the correct input");
                }
            }
            process = scanner.nextLine();
        }
        System.out.println("The process is terminated successfully");
        System.out.print("ALERT! ");
        if (!vnolist.isEmpty()) {
            System.out.println("Below vehicle/s are yet to be unparked");
            for (int i = 0; i < vnolist.size(); i++) {
                System.out.printf("\tVehicle(%d)\n", vnolist.get(i));
            }
        }
    }
}

