// creates 
public class OrganizeSequence {
    // instance variables
    private ST<String, String> codonMap; // <codon, protein>
    private String[] sequence; // protein sequence
    private String species; // name of species
    private String dna; // DNA string

    // Constructor: Initializes species and dna, creates new symbol table
    // codonMap and inputs all codons and proteins, and creates array sequence
    // containing proteins based on DNA sequence
    public OrganizeSequence(String species, String dna) {
        this.species = species;
        this.dna = dna;
        this.sequence = new String[dna.length() / 3];
        this.codonMap = new ST<String, String>();
        setCodonMap();
        String[] codons = codons();
        for (int i = 0; i < codons.length; i++) {
            this.sequence[i] = codonMap.get(codons[i]);
        }
    }

    // Codon map that translates each codon to protein
    private void setCodonMap() {

        // Protein letter codes
        String g = "gly";
        String a = "ala";
        String le = "leu";
        String m = "met";
        String f = "phe";
        String w = "trp";
        String k = "lys";
        String q = "gln";
        String e = "glu";
        String s = "ser";
        String p = "pro";
        String v = "val";
        String i = "ile";
        String c = "cys";
        String y = "tyr";
        String h = "his";
        String r = "arg";
        String n = "asn";
        String d = "asp";
        String t = "thr";
        String stop = "stop";

        // Maps codons to proteins
        codonMap.put("uuu", f);
        codonMap.put("uuc", f);
        codonMap.put("uua", le);
        codonMap.put("uug", le);
        codonMap.put("ucu", s);
        codonMap.put("ucc", s);
        codonMap.put("uca", s);
        codonMap.put("ucg", s);
        codonMap.put("uau", y);
        codonMap.put("uac", y);
        codonMap.put("uaa", stop);
        codonMap.put("uag", stop);
        codonMap.put("ugu", c);
        codonMap.put("ugc", c);
        codonMap.put("uga", stop);
        codonMap.put("ugg", w);
        codonMap.put("cuu", le);
        codonMap.put("cuc", le);
        codonMap.put("cua", le);
        codonMap.put("cug", le);
        codonMap.put("ccu", p);
        codonMap.put("ccc", p);
        codonMap.put("cca", p);
        codonMap.put("ccg", p);
        codonMap.put("cau", h);
        codonMap.put("cac", h);
        codonMap.put("caa", q);
        codonMap.put("cag", q);
        codonMap.put("cgu", r);
        codonMap.put("cgc", r);
        codonMap.put("cga", r);
        codonMap.put("cgg", r);
        codonMap.put("auu", i);
        codonMap.put("auc", i);
        codonMap.put("aua", i);
        codonMap.put("aug", m);
        codonMap.put("acu", t);
        codonMap.put("acc", t);
        codonMap.put("aca", t);
        codonMap.put("acg", t);
        codonMap.put("aau", n);
        codonMap.put("aac", n);
        codonMap.put("aaa", k);
        codonMap.put("aag", k);
        codonMap.put("agu", s);
        codonMap.put("agc", s);
        codonMap.put("aga", r);
        codonMap.put("agg", r);
        codonMap.put("guu", v);
        codonMap.put("guc", v);
        codonMap.put("gua", v);
        codonMap.put("gug", v);
        codonMap.put("gcu", a);
        codonMap.put("gcc", a);
        codonMap.put("gca", a);
        codonMap.put("gcg", a);
        codonMap.put("gau", d);
        codonMap.put("gac", d);
        codonMap.put("gaa", e);
        codonMap.put("gag", e);
        codonMap.put("ggu", g);
        codonMap.put("ggc", g);
        codonMap.put("gga", g);
        codonMap.put("ggg", g);
    }

    // Returns species name
    public String getSpecies() {
        return species;
    }

    // Returns DNA string
    public String getDNA() {
        return this.dna;
    }

    // Returns DNA sequence length
    public int length() {
        return getDNA().length();
    }

    // Returns protein sequence
    public String[] getSequence() {
        return this.sequence;
    }


    // Transcribes DNA sequence to RNA and creates and returns String array of
    // RNA codons
    // If sequence is not multiple of 3, ignores extra nucleotides
    private String[] codons() {
        String rna = dna.replace('t', 'u');
        int size = rna.length();
        String[] codonList = new String[size / 3];
        int count = 0;
        for (int i = 0; i < size; i = i + 3) {
            codonList[count] = rna.substring(i, i + 3);
            count++;
        }
        return codonList;
    }

    // Translates an RNA codon to protein ?? is this needed?? no??
    public String protein(String codon) {
        String protein = codonMap.get(codon);
        return protein;
    }

    // Returns number of proteins in sequence
    public int proteinLength() {
        return this.sequence.length;
    }

    // Calculates percent relatedness of two sequences based on DNA sequence
    public int relatedness(OrganizeSequence otherSequence) {

        int length;
        if (length() > otherSequence.length()) {
            length = otherSequence.length();
        }
        else {
            length = length();
        }

        int count = 0;
        for (int i = 0; i < length; i++) {
            if (getDNA().charAt(i) == otherSequence.getDNA().charAt(i)) {
                count += 1;
            }
        }
        double decimal = (double) count / length * 100;
        int percent = (int) Math.round(decimal);
        return percent;
    }


    /* // Aligns two sequences using the Tanimoto Coefficient
    public double align(OrganizeSequence otherSequence) {
        int length;
        if (getDNA().length() > otherSequence.getDNA().length()) {
            length = getDNA().length();
        }
        else {
            length = otherSequence.getDNA().length();
        }

        int simNum = 0;

        for (int i = 0; i < length; i++) {
            int count = 0;
            for (int j = 0; j < otherSequence.getDNA().length(); j++) {
                if (getDNA().charAt(i) == otherSequence.getDNA().charAt(j)) {
                    count += 1;
                }
            }
            if (count > simNum)
                simNum += 1;

        }

        StdOut.println(simNum);

        return (double) simNum / ((getDNA().length() +
                otherSequence.getDNA().length()) - simNum);
    } */

    // Main method tests all methods
    public static void main(String[] args) {
        OrganizeSequence fakeSpecies1 = new OrganizeSequence("orca", "aaacattacatt");

        StdOut.println("Species Name: " + fakeSpecies1.getSpecies());
        StdOut.println("DNA Sequence String: " + fakeSpecies1.getDNA());
        StdOut.println("RNA Sequence Codons: "
                               + java.util.Arrays.toString(fakeSpecies1.codons()));
        StdOut.println("Protein List: "
                               + java.util.Arrays.toString(fakeSpecies1.getSequence()));
        StdOut.println("Number of Proteins: " + fakeSpecies1.proteinLength());


        OrganizeSequence fakeSpecies2 = new OrganizeSequence("narwal", "aaacattacctt");
        StdOut.println("Species Name: " + fakeSpecies2.getSpecies());
        StdOut.println("DNA Sequence String: " + fakeSpecies2.getDNA());
        StdOut.println("RNA Sequence Codons: "
                               + java.util.Arrays.toString(fakeSpecies2.codons()));
        StdOut.println("Protein List: "
                               + java.util.Arrays.toString(fakeSpecies2.getSequence()));
        StdOut.println("Number of Proteins: " + fakeSpecies2.proteinLength());

        StdOut.println("Relatedness: " + fakeSpecies1.relatedness(fakeSpecies2));


    }
}
