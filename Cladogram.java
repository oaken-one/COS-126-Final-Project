// what is this???
public class Cladogram {

    public static void main(String[] args) {

        OrganizeSequence orca = new OrganizeSequence("Orca", "aaacattacatt");
        CompareSequences tester = new CompareSequences(orca);

        StdDraw.setXscale(0, 0.8);
        StdDraw.setYscale(0, 1);

        // Number of species and cladogram branches
        int branches = tester.getSpeciesNum();

        // Main branch coordinates
        double mx0 = 0.1;
        double my0 = 0.1;
        double mx1 = 0.7;
        double my1 = 0.7;

        // draw main cladogram branch (this branch will have our selected
        // species at the top
        StdDraw.line(0.1, 0.1, 0.7, 0.7);

        // Species branch coordinates
        double x0s = (mx1 - mx0) / (branches);
        double y0s = (my1 - my0) / (branches);


        String[] textHolder = tester.relateAll();
        StdDraw.text(mx1, my1 + 0.02, textHolder[branches - 1]);

        for (int i = 1; i < branches; i++) {
            double x0 = i * x0s + 0.1;
            double y0 = i * y0s + 0.1;
            double x1 = x0 - (0.1 / i);
            StdDraw.line(x0, y0, x1, my1);
            StdDraw.text(x1, my1 + 0.02, textHolder[i - 1]);
        }


    }
}
