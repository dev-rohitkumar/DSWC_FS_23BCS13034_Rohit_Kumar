package Q5;

import java.util.concurrent.atomic.AtomicInteger;

class DroneHive {

    // Thread-safe counter
    private AtomicInteger totalDronesReturned = new AtomicInteger(0);

    // Visibility guarantee across all threads
    private volatile boolean emergencyAbort = false;

    // Called when a drone lands
    public void droneReturned() {
        totalDronesReturned.incrementAndGet();
    }

    // Radar detects storm
    public void triggerEmergencyAbort() {
        emergencyAbort = true;
        System.out.println("EMERGENCY ABORT ACTIVATED!");
    }

    // Drones check this continuously
    public boolean isEmergencyAbort() {
        return emergencyAbort;
    }

    public int getTotalDronesReturned() {
        return totalDronesReturned.get();
    }
}

class Drone extends Thread {

    private DroneHive hive;
    private int droneId;

    public Drone(int droneId, DroneHive hive) {
        this.droneId = droneId;
        this.hive = hive;
    }

    @Override
    public void run() {

        while (!hive.isEmergencyAbort()) {
            // Drone is flying
        }

        System.out.println(
                "Drone " + droneId +
                " received abort signal."
        );

        hive.droneReturned();
    }
}

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        DroneHive hive = new DroneHive();

        // Simulate 10 drones (can scale to 10,000)
        for (int i = 1; i <= 10; i++) {
            new Drone(i, hive).start();
        }

        Thread.sleep(2000);

        // Storm detected
        hive.triggerEmergencyAbort();

        Thread.sleep(1000);

        System.out.println(
                "Total Drones Returned: "
                + hive.getTotalDronesReturned()
        );
    }
}