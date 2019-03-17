package project;

public class AllocUneZoneStatique extends AllocStatique{
	
	private FiledAttente Filo = new FiledAttente();

	public AllocUneZoneStatique(ZoneRAM[] Z,int TailleRAM, FiledAttente F) {
		super(Z,1,TailleRAM);
		this.setFilo(F);
	}

	@Override
	public void allouer() {
		if(!this.getFilo().isEmpty())
		{
			Processus P = this.getFilo().defiler();
			if(P.getTailleAlloc() <= this.getZones()[0].getTaille())
			{	
				if(!this.getZones()[0].isOccupe())
				{
					this.getZones()[0].set();
					this.getZones()[0].setP(P);
					return;
				}	
			}
		}
		return;
		
	}

	public FiledAttente getFilo() {
		return Filo;
	}

	public void setFilo(FiledAttente filo) {
		Filo = filo;
	}


}
