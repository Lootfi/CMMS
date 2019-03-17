package project;

public class AllocNZonesNFiles extends AllocStatique {
	
	
	
	public AllocNZonesNFiles(ZoneRAM[] Zones, int TailleMemoire) {
		super(Zones,Zones.length,TailleMemoire);
	}

	@Override
	public void allouer() {
		for(int i = 0; i < this.getZones().length ; i++) {
			if(this.getZones()[i].getFiledeZone().tetedeFile() != null && !this.getZones()[i].isOccupe()) {
				Processus P = this.getZones()[i].getFiledeZone().defiler();
				this.getZones()[i].set();
				this.getZones()[i].setP(P);
			}
		}
	}

	@Override
	public FiledAttente getFilo() {
		return null;
	}
}
