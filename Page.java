package project;

public class Page {
	private int id;
	private int taille;
	private boolean occupe = false;
	private Segment Segment = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public boolean isOccupe() {
		return occupe;
	}

	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}

	public Segment getSeg() {
		return Segment;
	}

	public void setProcSeg(Segment procSeg) {
		this.Segment = procSeg;
	}

}
