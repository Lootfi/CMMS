package project;

public class Testing {

	public static void main(String[] args) {
			SysExploitation S = new SysExploitation();
			S.initSimulation();
			for (Processus P : S.getMemPrincipale().getFilo()) {
				System.out.println("P.id = "+ P.getId() + " P.ta = " + P.getTempsAriv());
			}
	}

}
