package Q1;
abstract class SpaceVessel {
    protected short shipId;
    protected boolean operationalStatus;
    protected char fleetClassification;

    public SpaceVessel(short shipId, boolean operationalStatus, char fleetClassification) {
        this.shipId = shipId;
        this.operationalStatus = operationalStatus;
        this.fleetClassification = fleetClassification;
    }
}

class MiningShip extends SpaceVessel {

    private float[][] cargoHold;

    public MiningShip(short shipId,
                      boolean operationalStatus,
                      char fleetClassification,
                      byte bays,
                      byte containersPerBay) {

        super(shipId, operationalStatus, fleetClassification);
        cargoHold = new float[bays][containersPerBay];
    }

    public void setOreWeight(int bay, int container, float weight) {
        cargoHold[bay][container] = weight;
    }

    public float calculateTotalOreWeight() {
        float total = 0;

        for (int i = 0; i < cargoHold.length; i++) {
            for (int j = 0; j < cargoHold[i].length; j++) {
                total += cargoHold[i][j];
            }
        }

        return total;
    }

    public float findHeaviestContainer() {
        float maxWeight = cargoHold[0][0];

        for (int i = 0; i < cargoHold.length; i++) {
            for (int j = 0; j < cargoHold[i].length; j++) {
                if (cargoHold[i][j] > maxWeight) {
                    maxWeight = cargoHold[i][j];
                }
            }
        }

        return maxWeight;
    }
}

public class Main {
    public static void main(String[] args) {

        // 1D Array of Objects (Fleet)
        SpaceVessel[] fleet = new SpaceVessel[2];

        MiningShip ship1 = new MiningShip(
                (short)1001,
                true,
                'A',
                (byte)3,
                (byte)4
        );

        ship1.setOreWeight(0, 0, 1200.5f);
        ship1.setOreWeight(0, 1, 2400.0f);
        ship1.setOreWeight(1, 2, 5200.75f);
        ship1.setOreWeight(2, 3, 4100.25f);

        fleet[0] = ship1;

        System.out.println("Total Ore Weight: "
                + ship1.calculateTotalOreWeight() + " kg");

        System.out.println("Heaviest Container: "
                + ship1.findHeaviestContainer() + " kg");

        long totalFleetValue = 45000000000L;
        System.out.println("Total Fleet Value: "
                + totalFleetValue + " credits");
    }
}