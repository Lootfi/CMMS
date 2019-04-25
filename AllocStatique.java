package project;

public abstract class AllocStatique extends RAM{

	private Zone[] Zones = null;
	
	
	public AllocStatique(Zone[] Z,int numZones, int Taille) {
		super(numZones,Taille);
		this.setZones(Z);
		
	}
	
	
	@Override
	public void liberer(Processus P) {
		for(int i = 0 ; i < this.getZones().length ; i++)
		{
			if(!this.getZones()[i].isOccupe()) continue;
			if(this.getZones()[i].getP().getId() == P.getId())
			{
				this.getZones()[i].getP().setInPro(false);
				this.getZones()[i].clearZone();
				return;
			}
		}
		
	}
	
	
	public Zone[] getZones() {
		return Zones;
	}

	public void setZones(Zone[] zones) {
		Zones = zones;
	}


}
