// what is this???

public class CompareSequences {
    // instance variable
    private ST<Integer, OrganizeSequence> sequences; // what is this
    private OrganizeSequence submitedSequence; // and what is this
    private int speciesNum; // number of sequences in dataset

    // Constructor
    public CompareSequences(OrganizeSequence sequenceOne) {
        sequences = new ST<Integer, OrganizeSequence>();
        submitedSequence = sequenceOne;
        speciesNum = StdIn.readInt();

        while (!StdIn.isEmpty()) {
            String species = StdIn.readString();
            String sequence = StdIn.readString();
            OrganizeSequence temp = new OrganizeSequence(species, sequence);
            sequences.put(temp.relatedness(submitedSequence), temp);
            StdOut.println(temp.relatedness(submitedSequence) + " " + species);
        }
    }

    // Returns speciesNum
    public int getSpeciesNum() {
        return speciesNum;
    }

    // Adds new species and sequences
    public void addSpeciesSequence(OrganizeSequence extraSequence) {
        sequences.put(extraSequence.relatedness(submitedSequence), extraSequence);
    }

    // Creates an array of species in order from least related to most related
    public String[] relateAll() {
        String[] orderedSpecies = new String[speciesNum];

        int i = 0;
        for (int key : sequences.keys()) {
            orderedSpecies[i] = sequences.get(key).getSpecies();
            i++;
        }

        return orderedSpecies;
    }


    // main
    public static void main(String[] args) {
        OrganizeSequence newSeq = new OrganizeSequence("Orca", "aaacattacatt");
        CompareSequences tester = new CompareSequences(newSeq);
        tester.relateAll();

        for (int i = 0; i < tester.speciesNum; i++) {
            StdOut.println(tester.relateAll());
        }

    }
}
