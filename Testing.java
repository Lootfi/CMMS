package project;


public class Testing {
	
	public static void main(String[] args) {
			
			OS S = new OS();
			S.initSimulation();
			System.out.println("***********\nDans la mémoire :\n***********\n");
			for (Zone Z : S.getMemPrincipale().getZones()) {
				if(Z.isOccupe()) {
					System.out.println("P.id = "+ Z.getP().getId() + " P.ta = " + Z.getP().getTempsAriv());					
				}
			}
			System.out.println("***********\nDans le processeur :\n***********\n");
			System.out.println("Le Processus id="+S.getProcesseur().getP().getId());
	}

}
