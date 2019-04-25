package project;

import project.OS;


public class Processeur {
	
	private Processus P;
	private Processus Psortant;
	public int time = 0;
	public int quantum = 2;
	private FiledAttente Filo = new FiledAttente();
	
	public void afficher() {
		System.out.println("Dans le Processeur:");
		if(this.P == null) System.out.println("Pas de Processus");
		else { 
			System.out.println("Le processus entrain d'execution:");
			System.out.println("P.id="+this.getP().getId()+" de taille "+this.getP().getTailleAlloc());
		System.out.println("Temps d'execution restant: "+ this.getP().getTempsExec());
		}
	}
	
	public void MemoireToFileProcesseur(RAM M) {
		for(Zone Z : M.getZones()) {
			if(Z.isOccupe() && !Z.getP().isInPro()) {
				Z.getP().setInPro(true);
				Processus P = Z.getP();
				this.getFilo().enfiler(P);				
			} else continue;
		}
	}
	
	
	public void FileToProcesseur() {
		if(this.getFilo().isEmpty()) return;
		else this.setP(this.getFilo().defiler());
	}
	

	public void roundRobin(){
        if(!this.getFilo().isEmpty() || this.getP() != null) {
            if(this.getP() == null) {
                this.FileToProcesseur();
            }
            if(this.time < this.quantum) {
                if(this.getP().getTempsExec() > 0){
                    this.getP().decreaseTE();
                    this.time++;
                }else{
                    OS.liberation = true;
                    this.setPsortant(this.getP());
                    this.clearProcesseur();
                    this.FileToProcesseur();
                    this.time = 0; //not sure if 1 or 0, inclined on 1
                }
            }
            else {
                this.time = 0; // not sure if 1 or 0, inclined on 0
                if(this.getFilo().isEmpty()){
                    if(this.getP().getTempsExec() > 0){
                        this.getP().decreaseTE();
                        this.time++;
                    }else{
                        OS.liberation = true;
                        this.setPsortant(this.getP());
                        this.clearProcesseur();
                    }
                }else{
                    if(this.getP().getTempsExec() > 0){
                        this.getFilo().enfiler(this.getP());
                        this.FileToProcesseur();
                        this.getP().decreaseTE();
                    }else {
                        OS.liberation = true;
                        this.setPsortant(this.getP());
                        this.FileToProcesseur();
                    }
                }
                
                
            }
        }
     }

	public Processus getP() {
		return P;
	}

	public void setP(Processus p) {
		P = p;
	}

	public FiledAttente getFilo() {
		return Filo;
	}

	public void setFilo(FiledAttente filo) {
		Filo = filo;
	}
	
	public void clearProcesseur() {
		this.P = null;
	}


	public Processus getPsortant() {
		return Psortant;
	}


	public void setPsortant(Processus psortant) {
		Psortant = psortant;
	}

}
