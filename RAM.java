package project;

import java.util.*;

public abstract class RAM{
	Scanner scan = new Scanner(System.in);

	private int TailleMemoire; 
	private int numZonesPages;
	
	public void afficher() {
		System.out.println("\nDans la RAM:");
		for(Zone Z: this.getZones()) {
			System.out.print("Zone '"+Z.id+"' ");
			if(Z.isOccupe()) System.out.println("est occupé - P.id="+Z.getP().getId()+" TE="+Z.getP().getTempsExec());
			else System.out.println("est libre");
		}
		System.out.println("\n");
	}
	
	
	public RAM() {}
	public RAM(int numZones,int Taille) {
		this.setNumZonesPages(numZones);
		this.setTailleMemoire(Taille);
	}
	
	public abstract void allouer();
	
	public abstract void liberer(Processus P);
	
	public abstract void liberer();
	
	public abstract void clean();
	
	
	public void fileMemoireToMemoire(int typeAlloc) {
		switch (typeAlloc) {
		case 1: {
			//Une Zone Une File
			this.allouer();
			break;
		}			

		case 2: {
			// N Zones Une File
			boolean memvide = true;
			/*
			 * loop to check if memory has an empty space
			 */
			for(int i = 0; i < this.getZones().length; i++) {
				if(!this.getZones()[i].isOccupe()) {
					memvide = true;
					break;
				} else {
					memvide = false;
				}
			}
			/***********************/
			/*
			 * this while loop will only stop in case the RAM is totally full or the RAM-Queue(Filo) is empty 
			 */
			while(!this.getFilo().isEmpty() && memvide) {
				this.allouer();
				//checking again if there is an empty space available
				for(int i = 0; i < this.getZones().length; i++) {
					if(!this.getZones()[i].isOccupe()) {
						memvide = true;
						break;
					} else {
						memvide = false;
					}
				}
				
			}
			break;
		}
		case 3: {
			//N Zones N Files
			
			this.allouer();
			
			/*for(int i = 0; i < this.getZones().length; i++) {
				if(!this.getZones()[i].getFiledeZone().isEmpty()) {
						this.allouer(); // every time unit for each zone							
				}
			}*/
			
			break;
		}
		default: break;
		}
	}
	
	
	
	
	
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
	
	public abstract Zone[] getZones();
	public abstract ArrayList<Page> getPages();
	
	public boolean isTotallyEmpty() {
		for(int i=0; i< this.getZones().length; i++) {
			if(this.getZones()[i].isOccupe()) return false;
		}
		return true;
	}
	
	
	
	

}
