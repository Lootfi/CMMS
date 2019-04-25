package project;

import java.util.ArrayList;

public class AllocUneZoneStatique extends AllocStatique{
	
	private FiledAttente Filo = new FiledAttente();

	public AllocUneZoneStatique(Zone[] Z,int TailleRAM, FiledAttente F) {
		super(Z,1,TailleRAM);
		this.setFilo(F);
	}

	@Override
	public void allouer() {
		if(!this.getFilo().isEmpty() && !this.getZones()[0].isOccupe())
		{
			Processus P = this.getFilo().defiler();
			if(P.getTailleAlloc() <= this.getZones()[0].getTaille())
			{
				//this.getZones()[0].set();
				this.getZones()[0].setP(P);
				return;
			}
			else {
				System.out.println("Le processus avec id="+P.getId()+" a une taille plus grande que la zone");
			}
			
		}
		return;
		
	}
	@Override
	public void clean() {
		this.getFilo().clear();
		this.getZones()[0].clearZone();
	}

	public FiledAttente getFilo() {
		return Filo;
	}

	public void setFilo(FiledAttente filo) {
		Filo = filo;
	}

	@Override
	public ArrayList<Page> getPages() {
		// TODO Auto-generated method stub
		return null;
	}


}
