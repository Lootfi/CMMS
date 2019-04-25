package project;
import java.util.ArrayList;

public abstract class AllocDynamique extends RAM {
	
	private FiledAttente Filo = new FiledAttente();
	private ArrayList<Page> Pages = new ArrayList<Page>();

	
	public abstract void allouer();

	@Override
	public void liberer(Processus P) {
		
	}

	@Override
	public FiledAttente getFilo() {
		return Filo;
	};

	
	@Override
	public void clean() {
		
	}
	
	@Override
	public ArrayList<Page> getPages() {
		return Pages;
	}

	public void setPages(ArrayList<Page> pages) {
		Pages = pages;
	}
	
}
