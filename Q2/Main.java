package Q2;
class ColonyGridPowerManager {

    // 8 bits represent 8 sectors
    private byte sectorStates = 0;

    // Turn ON a sector
    public void turnOnSector(int sectorIndex) {
        sectorStates = (byte) (sectorStates | (1 << sectorIndex));
    }

    // Turn OFF a sector
    public void turnOffSector(int sectorIndex) {
        sectorStates = (byte) (sectorStates & ~(1 << sectorIndex));
    }

    // Check if a sector is ON
    public boolean isSectorOn(int sectorIndex) {
        return (sectorStates & (1 << sectorIndex)) != 0;
    }

    // Display all sector states
    public void displayStates() {
        System.out.print("Sector States: ");
        for (int i = 7; i >= 0; i--) {
            System.out.print((sectorStates >> i) & 1);
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {

        ColonyGridPowerManager manager = new ColonyGridPowerManager();

        manager.turnOnSector(0); // Sector 0 ON
        manager.turnOnSector(3); // Sector 3 ON
        manager.turnOnSector(7); // Sector 7 ON

        manager.displayStates();

        System.out.println("Sector 0 ON? " + manager.isSectorOn(0));
        System.out.println("Sector 1 ON? " + manager.isSectorOn(1));
        System.out.println("Sector 3 ON? " + manager.isSectorOn(3));

        manager.turnOffSector(3);

        manager.displayStates();

        System.out.println("Sector 3 ON? " + manager.isSectorOn(3));
    }
}