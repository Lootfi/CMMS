package project;

public class Processus {
	static int counter = 0;
	private int id;
	private int tempsAriv;
	private int tempsExec;
	private int tailleAlloc;
	private String etat = "prêt";
	private int idFile;
	private boolean segmented = false;
	private Segment[] Segments;
	
	public Processus(int tempsarriv,int tempsexec, int taillealloc) {
		this.setId(counter);
		this.setTempsAriv(tempsarriv);
		this.setTempsExec(tempsexec);
		this.setTailleAlloc(taillealloc);
		counter++;
	}
	
	public Processus(int tempsarriv,int tempsexec,int taillelloc,Segment[] Segments) {
		this.setId(counter);
		this.setTempsAriv(tempsarriv);
		this.setTempsExec(tempsexec);
		this.setTailleAlloc(taillelloc);
		this.setSegments(Segments);
		counter++;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdFile() {
		return idFile;
	}

	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}

	public int getTempsAriv() {
		return tempsAriv;
	}

	public void setTempsAriv(int tempsAriv) {
		this.tempsAriv = tempsAriv;
	}

	public int getTempsExec() {
		return tempsExec;
	}

	public void setTempsExec(int tempsExec) {
		this.tempsExec = tempsExec;
	}

	public int getTailleAlloc() {
		return tailleAlloc;
	}

	public void setTailleAlloc(int tailleAlloc) {
		this.tailleAlloc = tailleAlloc;
	}

	public boolean isSegmented() {
		return segmented;
	}

	public void setSegmented(boolean segmented) {
		this.segmented = segmented;
	}

	public Segment[] getSegments() {
		return Segments;
	}

	public void setSegments(Segment[] segments) {
		Segments = segments;
	}

}
