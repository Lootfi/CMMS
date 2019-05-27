package project;


public class FirstFit extends AllocDynamique {


	@Override
	public Zone[] getZones() {
		return null;
	}

	@Override
	public void allouer() {
		for(int i = 0; i < this.getPages().size(); i++) {
			if(this.getFilo().tetedeFile().getTailleAlloc() <= this.getPages().get(i).getTaille() && !this.getPages().get(i).isOccupe()) {
				this.getPages().get(i).setOccupe(true);
				if(this.getFilo().tetedeFile().getTailleAlloc() < this.getPages().get(i).getTaille()) {
					Page newPage = new Page();
					newPage.setOccupe(false);
//					newPage.setTaille(this.getPages().get(i).getTaille() - xxxx); xxxx = taille de i-eme segment
					this.getPages().add(i+1, newPage);
					
				}
			}	
		}		
	}


}
