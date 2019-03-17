package project;

public abstract class AllocStatique extends MemoirePrincipale{

	private ZoneRAM[] Zones = null;
	
	
	public AllocStatique(ZoneRAM[] Z,int numZones, int Taille) {
		super(numZones,Taille);
		this.setZones(Z);
		
	}
	
	
	@Override
	public void liberer(Processus P) {
		for(int i = 0 ; i < this.getZones().length ; i++)
		{
			if(this.getZones()[i].getP().getId() == P.getId())
			{
				this.getZones()[i].clearZone();
				return;
			}
		}
		
	}
	
	
	public ZoneRAM[] getZones() {
		return Zones;
	}

	public void setZones(ZoneRAM[] zones) {
		Zones = zones;
	}
	
	
	public void infoMemory() {
		System.out.println("La taille de votre mémoire:\t" + super.getTailleMemoire());
		System.out.println("La taille des zones:\t" + this.getZones()[0].getTaille());
		System.out.println("Le nombre des zones:\t" + (super.getTailleMemoire() / this.getZones()[0].getTaille()));
		
	}

}
