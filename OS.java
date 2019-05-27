package project;
import java.util.Scanner;

public class OS {
		
	Scanner scan = new Scanner(System.in);
	private FiledAttente FileInitial = new FiledAttente();
	private FiledAttente clonedFileInitial = null;
	private RAM MemPrincipale;
	private Processeur Processeur = new Processeur();
	public int typeAlloc;
	public int time = 0;
	
	public static boolean liberation = false;

	public void initSimulation() {
		/*
		 * if 1 : will restart simulation
		 * if 2 : will quit simulation
		 */
		int restart = 2;
		/*
		 * if 1 : will use the same processus as the previous simulation
		 * if 2 : will use new processus
		 */
		int useSameProc = 2;
		
		System.out.println("This is a Computer Memory Allocation and Overall Behavior simulation\nEnjoy ...");
		do {
		initMP();
		if(useSameProc == 2) {
			this.initMultipProcessus();
			this.setClonedFileInitial(this.getFileInitial());
		} else {
			this.setFileInitial(clonedFileInitial);
		}
		
		int execTime = 0;
		for(Processus P : this.getFileInitial()) {
			execTime += P.getTempsExec();
		}
		/*Start of methods that excecute every time unit*/
		// ----------------------------------------------------------
		do {
		this.FileInitialtoFileMemoire();
		this.getMemPrincipale().fileMemoireToMemoire(typeAlloc);
		this.getProcesseur().MemoireToFileProcesseur(MemPrincipale);
		this.getProcesseur().roundRobin();
		if(liberation) {
			this.getMemPrincipale().liberer(this.getProcesseur().getPsortant());
			OS.liberation = false;
		}
		
		System.out.println("A l'instant "+this.time+" :");
		this.getMemPrincipale().afficher();
		this.getProcesseur().afficher();
		System.out.println("time dans Processeur: "+this.getProcesseur().time);
		System.out.println("\n");
		this.checkFilePro();
		System.out.println("\n******************\n");
		
		this.time++;
		}
		while(this.time < execTime + 1);
		// ----------------------------------------------------------
		/*End of methods that excecute every unit*/
		
		
		
		/* End of Simulation Process */
		do {
		System.out.println("Do you want to restart the simulation?");
		System.out.println("1- Yes");
		System.out.println("2- No");
		restart = scan.nextInt();
		}while(restart > 2 || restart < 1);
		
		if(restart == 1) {
			this.time = 0;
			this.clean();
			do {
			System.out.println("Do you want to use the same processus as the previous simulation?");
			System.out.println("1- Yes");
			System.out.println("2- No");
			useSameProc = scan.nextInt();
			
			}while(useSameProc < 1 || useSameProc > 2);
			if(useSameProc == 2) clonedFileInitial = new FiledAttente();
		}
		
		}while(restart == 1);
	}

	

	
	public void checkFilePro() {
		for(Processus P: this.getProcesseur().getFilo()) {
			System.out.println("dans file Processeur P.id="+P.getId());
		}
	}
	
	public void initMP() {
		int typeMem = 0;
		
		do {
		System.out.println("Initialisation d'une Mémoire Principale ...");
		System.out.println("Donner le type d'allocation Mémoire: ");
		System.out.println("I - Allocation statique contigue: ");
		System.out.println("\t 1- Allocation d'une zone en statique");
		System.out.println("\t 2- Allocation de N zones en statique avec une seule file");
		System.out.println("\t 3- Allocation de N zones en statique avec N files");
		typeMem = scan.nextInt();
				} while(typeMem < 1 || typeMem > 3);
		
		switch(typeMem) {
		
		case 1: {
			int TailleZone;
			Zone[] Zones = new Zone[1];
			
			do {
			System.out.println("Donnez la taille de la zone/mémoire: ");
			TailleZone = scan.nextInt(); } while(TailleZone < 1);
			
			Zones[0] = new Zone(TailleZone);

			FiledAttente F = new FiledAttente();
			
			MemPrincipale = new AllocUneZoneStatique(Zones,TailleZone,F);
			MemPrincipale.setTailleMemoire(TailleZone);
			typeAlloc = 1;
			break;
		}
		
		case 2: {
			int TailleZone,TailleMemoire;
			do {
				System.out.println("Donnez la taille de la mémoire principale");
				TailleMemoire = scan.nextInt();
			}while(TailleMemoire < 1);
			do {
				System.out.println("Donnez la taille de la zone: ");
				TailleZone = scan.nextInt();
			} while(TailleZone < 1 && TailleZone >= TailleMemoire);
			
			Zone[] Zones = new Zone[TailleMemoire / TailleZone];
			
			for(int i = 0; i <Zones.length; i++) {
				Zones[i] = new Zone(TailleZone);
			}
			
			FiledAttente F = new FiledAttente();
		
			
			MemPrincipale = new AllocNZonesUneFile(Zones,TailleMemoire,F);
			
			typeAlloc = 2;
			
			break;
		}
		
		case 3: {
			int TailleZone,TailleMemoire;
			do {
				System.out.println("Donnez la taille de la mémoire principale");
				TailleMemoire = scan.nextInt();
			}while(TailleMemoire < 1);
			do {
				System.out.println("Donnez la taille de la zone: ");
				TailleZone = scan.nextInt();
			} while(TailleZone < 1 && TailleZone >= TailleMemoire);
			
			Zone[] Zones = new Zone[TailleMemoire / TailleZone];
			
			for(int i = 0; i <Zones.length; i++) {
				FiledAttente F = new FiledAttente();
				Zones[i] = new Zone(TailleZone,F);
			}
			
			MemPrincipale = new AllocNZonesNFiles(Zones,TailleMemoire);

			typeAlloc = 3;
			
			break;
		}
		
		default:{
			break;
		}
		
		}
}
	
	public void FileInitialtoFileMemoire() {
		
		if(typeAlloc == 1 || typeAlloc == 2) {
			while(!this.getFileInitial().isEmpty() && this.getFileInitial().tetedeFile().getTempsAriv() == time) {
					this.getMemPrincipale().getFilo().enfiler(this.getFileInitial().defiler());
			}			
		}
		else if(typeAlloc == 3){
			while(!this.getFileInitial().isEmpty() && this.getFileInitial().tetedeFile().getTempsAriv() == time) {
					Processus P = this.getFileInitial().defiler();
					for (Zone zone : this.getMemPrincipale().getZones()) {
						if(P.getIdFile() == zone.getFiledeZone().id) {
							zone.getFiledeZone().enfiler(P);
							break;
						}
					}
			}
		}
	}
	
	
	public void initMultipProcessus() {
		int init;
		do {
			System.out.println("Initialisation des processus .. ");
			System.out.println("1- Initialiser un nouveau Processus");
			System.out.println("2- Quit");
			init = scan.nextInt();
			if(init == 1) {
				this.initProcessus();
			}			
		} while(init != 2);
	}
	
	public void initProcessus() {
		
		int tempsA,tempsE,tailleA;
		
		System.out.println("Initialisation d'un processus ...");
		do {
		System.out.println("Le temps d'arrivé: ");
		if(this.getFileInitial().tetedeFile() != null) {
			System.out.println("Min: "+ this.getFileInitial().getLastProcTA());	
		}
		tempsA = scan.nextInt();} while(tempsA < 0 || this.getFileInitial().getLastProcTA() > tempsA);
		
		do {
		System.out.println("Le temps d'éxécution: ");
		 tempsE = scan.nextInt();} while(tempsE <= 0);
		
		do {
		System.out.println("La taille d'allocation: ");
		 tailleA = scan.nextInt();} while(tailleA <= 0);
		
		Processus P = new Processus(tempsA,tempsE,tailleA);
		
		if(this.getMemPrincipale().getClass().getSimpleName().equals("AllocNZonesNFiles")) {
			
			int indexFile;
		
			do {
			System.out.println("L'index de File d'Attente: ");
			System.out.print("[");
			for(int i=0; i< this.getMemPrincipale().getZones().length; i++) {
				System.out.print(" "+this.getMemPrincipale().getZones()[i].getFiledeZone().id+" ");
			}
			System.out.println("]");
			indexFile = scan.nextInt();
			} while(indexFile< this.getMemPrincipale().getZones()[0].getFiledeZone().id || (indexFile > this.getMemPrincipale().getNumZonesPages() - 1));
		
			P.setIdFile(indexFile);
			
		}
		
		this.getFileInitial().enfiler(P);
		
	}

	public void clean() {
		this.getFileInitial().clear();
		this.getMemPrincipale().clean();
	}
//	public void FileInitialtoFileMemoire() {
//	
//		if(typeAlloc == 1 || typeAlloc == 2) {
//			while(!this.getFileInitial().isEmpty() && this.getFileInitial().tetedeFile().getTempsAriv() <= time) {
//					this.getMemPrincipale().getFilo().enfiler(this.getFileInitial().defiler());
//			}			
//		}
//		else if(typeAlloc == 3){
//			while(!this.getFileInitial().isEmpty() && this.getFileInitial().tetedeFile().getTempsAriv() <= time) {
//					Processus P = this.getFileInitial().defiler();
//					for (ZoneRAM zone : this.getMemPrincipale().getZones()) {
//						if(P.getIdFile() == zone.getFiledeZone().id) {
//							zone.getFiledeZone().enfiler(P);
//							break;
//						}
//					}
//			}
//		}
//	}

	public RAM getMemPrincipale() {
		return MemPrincipale;
	}




	
	public Processeur getProcesseur() {
		return this.Processeur;
	}


	public FiledAttente getFileInitial() {
		return FileInitial;
	}


	public void setFileInitial(FiledAttente fileInitial) {
		FileInitial = fileInitial;
	}




	public FiledAttente getClonedFileInitial() {
		return clonedFileInitial;
	}




	public void setClonedFileInitial(FiledAttente clonedFileInitial) {
		this.clonedFileInitial = clonedFileInitial;
	}

}
