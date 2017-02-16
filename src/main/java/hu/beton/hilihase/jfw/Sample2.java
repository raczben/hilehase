package hu.beton.hilihase.jfw;

public class Sample2 implements IBase
{

//	private static java.lang.reflect.Field LIBRARIES = null;
//	static {
//		try {
//			LIBRARIES = ClassLoader.class.getDeclaredField("loadedLibraryNames");
//			LIBRARIES.setAccessible(true);
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private static void handleUnhandled(Exception ex) {
		System.out.println("[ CRITICAL WARNING ] Unhandled exception!!!");
		ex.printStackTrace();
	}

	public static void hilihase_log(String msg) {

		System.out.println("HILIHASE: " + msg);
	}

	public static int echo(int n) {
		return n;
	}

//	@Deprecated
	public static int hilihase_step(int current_time){
		try{
			Global.get(0).set(current_time);
//			Base.getBase().step(current_time);
		} catch (Exception ex){
			handleUnhandled(ex);
			return -1;
		}
		return 0;
	}

	public static int hilihase_register(int id, String name, byte initval){
		try{
			Sample2.hilihase_log("Signal registered. Id: " + id + " Name: " + name + " init val: " + initval);
			Global.register_signal(id, name, ValueE.ValueOf(initval));
		} catch (Exception ex){
			handleUnhandled(ex);
			return -1;
		}
		return 0;
	}

	/**
	 * 
	 * @param debugLevel : set 0 for normal operation
	 * @return
	 */
	public static int hilihase_init(int debugLevel){
		try{
			System.out.println("Initialize JAVA (myself)");
			Base.initBase();
			Global.init(debugLevel<1);
			System.out.println("[  OK  ]$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		} catch (Exception ex){
			handleUnhandled(ex);
			return -1;
		}
		return 0;
	}

	public static int hilihase_close(int param){
		try{
			System.out.println("Destroy Base...");
			Base.destroyBase();
			System.out.println("Destroing Base finished");
		} catch (Exception ex){
			handleUnhandled(ex);
			return -1;
		}
		return 0;
	}


	public static int hilihase_read(int id, byte val){
		try{
			Global.read_signal(id, val);
//			Base.getBase().read_signal(id, ValueE.ValueOf(val));
		} catch (Exception ex){
			handleUnhandled(ex);
			return -1;
		}
		return 0;
	}

	//    public static byte hilihase_drive(int id){
	//        return Base.getBase().get(id).drive();
	//    }

	public native static int hilihase_drve(int id, byte val);

	public static int hilihase_start_tc(String tcName){
		try{
			Global.startTC(tcName);
//			Base.getBase().startTC(tcName);
		} catch (Exception ex){
			handleUnhandled(ex);
			return -1;
		}
		return 0;
	}

}