package project;

import java.util.ArrayList;

public class AllocNZonesUneFile extends AllocStatique {
	
	private FiledAttente Filo = new FiledAttente();
	
	/*
	 * In this case we have one single Queue for the whole Memory
	 * 
	 */

	public AllocNZonesUneFile(Zone[] Z,int TailleRAM, FiledAttente F) {
		super(Z,Z.length,TailleRAM);
		this.setFilo(F);
	}
	
	@Override
	public void clean() {
		this.getFilo().clear();
		for(int i=0; i < this.getZones().length; i++) {
			this.getZones()[i].clearZone();
		}
	}
	
	
	@Override
	public void allouer() {
		if(!this.getFilo().isEmpty()) {
			if(this.getFilo().tetedeFile().getTailleAlloc() <= this.getZones()[0].getTaille())
			{
				for(int i=0; i< this.getZones().length; i++)
				{
					if(!this.getZones()[i].isOccupe())
					{
//						this.getZones()[i].set();
						this.getZones()[i].setP(this.getFilo().defiler());
						return;
					}
				}
				
			} else {
				//case when P.taille > Zones.taille
				this.getFilo().defiler();
			}
		}
		
		return;
	}

	@Override
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
