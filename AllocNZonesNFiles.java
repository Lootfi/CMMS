package project;

import java.util.ArrayList;

public class AllocNZonesNFiles extends AllocStatique {
	
	
	
	public AllocNZonesNFiles(Zone[] Zones, int TailleMemoire) {
		super(Zones,Zones.length,TailleMemoire);
	}

	@Override
	public void allouer() {
		for(int i = 0; i < this.getZones().length ; i++) {
			if(!this.getZones()[i].getFiledeZone().isEmpty() && !this.getZones()[i].isOccupe()) {
				Processus P = this.getZones()[i].getFiledeZone().defiler();
				if(this.getZones()[i].getFiledeZone().tetedeFile().getTailleAlloc() <= this.getZones()[i].getTaille()) {
//					this.getZones()[i].set();
					this.getZones()[i].setP(P);					
				}
			}
		}
	}
	@Override
	public void clean() {
		for(int i = 0; i < this.getZones().length; i++) {
			this.getZones()[i].clearZone();
			this.getZones()[i].getFiledeZone().clear();
		}
	}
	@Override
	public FiledAttente getFilo() {
		return null;
	}

	@Override
	public ArrayList<Page> getPages() {
		// TODO Auto-generated method stub
		return null;
	}
}
