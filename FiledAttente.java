package project;
import java.util.*;

public class FiledAttente extends LinkedList<Processus> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // All Serializable classes must have this UID
	public static int counter = 0;
	public int id;
	
	@Override
	public FiledAttente clone() {
		return this;
	}
	
	public FiledAttente() {
		this.id = counter;
		counter++;
	}
	public void enfiler(Processus P) {
		this.offer(P);
	}
	
	public Processus defiler() {
		return this.poll();
		}
	public Processus tetedeFile() {
		return this.peek();
	}
	
	public int getLastProcTA() {
		return this.isEmpty() ? 0 : this.getLast().getTempsAriv();
	}
	
}
