package hu.beton.hilihase.jfw;

public abstract class TCThread extends Thread   {
	private int ID;
	
//	TCThread(){
////		Thread th = new Thread();
//		
//	}
	
	void waitSim(int time){
		Global.waitSim(time, this);
	}
	
	public abstract void test();
	
	@Override
	public void run(){
		test();
		Global.tcThreadToSleep(this, -1);		
	}

	int getID() {
		return this.ID;
	}
	
	void setID(int ID) {
		this.ID = ID; 
	}
	
	
	

}