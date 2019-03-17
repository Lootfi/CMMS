package project;

public class AllocNZonesUneFile extends AllocStatique {
	
	private FiledAttente Filo = new FiledAttente();
	
	/*
	 * In this case we have one single Queue for the whole Memory
	 * 
	 */

	public AllocNZonesUneFile(ZoneRAM[] Z,int TailleRAM, FiledAttente F) {
		super(Z,Z.length,TailleRAM);
		this.setFilo(F);
	}
	
	@Override
	public void allouer() {
		if(!this.getFilo().isEmpty()) {
			Processus P = this.getFilo().defiler();
			if(P.getTailleAlloc() <= this.getZones()[0].getTaille())
			{
				for(int i=0; i< this.getZones().length; i++)
				{
					if(!this.getZones()[i].isOccupe())
					{
						this.getZones()[i].set();
						this.getZones()[i].setP(P);
						return;
					}
				}
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

}
