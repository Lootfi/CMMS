package project;



public abstract class MemoirePrincipale{

	private int TailleMemoire; 
	private int numZonesPages;
	
	
	public MemoirePrincipale() {
		
	}
	public MemoirePrincipale(int numZones,int Taille) {
		this.setNumZonesPages(numZones);
		this.setTailleMemoire(Taille);
	}
	
	public abstract void allouer();
	
	public abstract void liberer(Processus P);
	
	public abstract FiledAttente getFilo();
	
	public int getTailleMemoire() {
		return TailleMemoire;
	}

	public void setTailleMemoire(int tailleMemoire) {
		TailleMemoire = tailleMemoire;
	}

	public int getNumZonesPages() {
		return numZonesPages;
	}

	public void setNumZonesPages(int numZonesPages) {
		this.numZonesPages = numZonesPages;
	}
	
	public abstract ZoneRAM[] getZones();

}
