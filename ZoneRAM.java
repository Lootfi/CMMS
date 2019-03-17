package project;

public class ZoneRAM {
	public static int counter = 0;
	public int id;
	private int taille;
	private Processus P = null;
	public boolean occupe = false;
	private FiledAttente FiledeZone = null;

	
	public ZoneRAM(int taille,FiledAttente F) {
		this.id = counter;
		counter++;
		this.setTaille(taille);
		this.setFiledeZone(F);
	}
	
	public ZoneRAM(int taille) {
		this.id = counter;
		counter++;
		this.setTaille(taille);
	}
	
	public ZoneRAM(FiledAttente F) {
		this.id = counter;
		counter++;
		this.setFiledeZone(F);
	}
	
	public int getTaille() {
		return taille;
	}
	
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	public Processus getP() {
		return P;
	}
	
	public void setP(Processus p) {
		P = p;
	}
	
	public boolean isOccupe() {
		return this.occupe ? true : false;
	}
	
	public void set() {
		this.occupe = !this.occupe;
	}
	
	public void clearZone() {
		this.set();
		this.P = null;
	}
	
	public FiledAttente getFiledeZone() {
		return FiledeZone;
	}

	public void setFiledeZone(FiledAttente filedeZone) {
		FiledeZone = filedeZone;
	}
	
}
