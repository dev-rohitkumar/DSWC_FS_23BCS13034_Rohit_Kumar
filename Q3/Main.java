package Q3;
class DNASequencer {

    private StringBuilder dnaSequence;

    // Initial capacity = 100000 to avoid frequent resizing
    public DNASequencer(int capacity) {
        dnaSequence = new StringBuilder(capacity);
    }

    // Efficiently append sensor data
    public void ingestSequence(char[] sensorData) {
        for (char ch : sensorData) {
            dnaSequence.append(ch);
        }
    }

    // Replace first occurrence in-place
    public void mutateDNA(String target, String replacement) {

        int index = dnaSequence.indexOf(target);

        if (index != -1) {
            dnaSequence.replace(
                    index,
                    index + target.length(),
                    replacement
            );
        }
    }

    public String getSequence() {
        return dnaSequence.toString();
    }
}

public class Main {
    public static void main(String[] args) {

        DNASequencer sequencer = new DNASequencer(100000);

        char[] sensorData = {
                'A', 'C', 'T', 'G',
                'A', 'A', 'C', 'T'
        };

        sequencer.ingestSequence(sensorData);

        System.out.println("Original DNA:");
        System.out.println(sequencer.getSequence());

        sequencer.mutateDNA("ACT", "GGG");

        System.out.println("\nAfter Mutation:");
        System.out.println(sequencer.getSequence());
    }
}