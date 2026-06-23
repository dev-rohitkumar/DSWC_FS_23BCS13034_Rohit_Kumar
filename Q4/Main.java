package Q4;

// Custom Checked Exception
class HardwareLockException extends Exception {

    public HardwareLockException(String message) {
        super(message);
    }
}

// Custom Unchecked Exception
class SensorCorruptionException extends RuntimeException {

    public SensorCorruptionException(String message) {
        super(message);
    }
}

// Resource Class
class TelemetryStream implements AutoCloseable {

    public TelemetryStream() {
        System.out.println("Telemetry Stream Opened");
    }

    public void readData() {
        System.out.println("Reading Telemetry Data...");
    }

    @Override
    public void close() {
        System.out.println("Telemetry Stream Closed");
    }
}

// Parser Class
class TelemetryParser {

    public void parseTelemetry(boolean fileLocked,
                               double temperature,
                               boolean sensorDisconnected)
            throws HardwareLockException {

        try (TelemetryStream stream = new TelemetryStream()) {

            if (fileLocked) {
                throw new HardwareLockException(
                        "Telemetry file is locked by OS."
                );
            }

            stream.readData();

            if (temperature > 100) {
                throw new SensorCorruptionException(
                        "Invalid temperature detected: "
                                + temperature + "°C"
                );
            }

            if (sensorDisconnected) {
                throw new SensorCorruptionException(
                        "Pressure sensor disconnected."
                );
            }

            System.out.println("Telemetry parsed successfully.");

        } // close() automatically called here
    }
}

public class Main {

    public static void main(String[] args) {

        TelemetryParser parser = new TelemetryParser();

        try {

            parser.parseTelemetry(
                    false,   // fileLocked
                    500.0,   // impossible temperature
                    false
            );

        }
        catch (HardwareLockException e) {

            System.out.println(
                    "FATAL HARDWARE ERROR: "
                            + e.getMessage()
            );
        }
        catch (SensorCorruptionException e) {

            System.out.println(
                    "Sensor Glitch Logged: "
                            + e.getMessage()
            );
        }
    }
}
