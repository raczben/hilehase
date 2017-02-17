package hu.beton.hilihase.testcases;

import hu.beton.hilihase.jfw.Global;
import hu.beton.hilihase.jfw.Signal;
import hu.beton.hilihase.jfw.SignalEvent;
import hu.beton.hilihase.jfw.TCThread;
import hu.beton.hilihase.jfw.ValueE;

/**
 * This is the first testcase which uses the full stack of Hilehase. (including
 * questa.)
 * 
 * Simpler/Higher-abstraction test: FullDutTest
 * 
 * @author ebenera
 *
 */
public class Minimal extends TCThread {

	static public void assertEquals(String message, Object expected, Object actual) {
//		message += ((Signal)actual).
	    if (expected == null && actual == null) {
	    	System.err.println("OK: " + message);
	        return;
	    }
	    if (expected != null && expected.equals(actual)) {
	    	System.err.println("OK: " + message);
	        return;
	    }
    	System.err.println("ERROR: Minimal: " + message + " exp: " +  expected.toString() + " actual: " +  actual.toString());
//	    failNotEquals(message, expected, actual);
	}
	
	static public void assertEquals(String message, int expected, int actual) {
	    if (expected == actual) {
	    	System.err.println("OK: " + message);
	        return;
	    }
    	System.err.println("ERROR: Minimal: " + message + " exp: " +  expected + " actual: " +  actual);
//	    failNotEquals(message, expected, actual);
	}
	
	@Override
	public void test() {
		System.out.println("Minimal TEST RUNNING!!!");
		Signal x, y, cin, out, carryout, clk;
		x = Global.get("top_x");
		y = Global.get("top_y");
		cin = Global.get("top_cin");
		out = Global.get("out");
		carryout = Global.get("carryout");
		clk = Global.get("clk");

	    System.out.println("POS");
		clk.WaitOn(SignalEvent.POSEDGE, this);
		assertEquals("simtime at ", 5, Global.getTime());
		assertEquals("set-get ", ValueE.HIGH, clk.get());
        
        
		assertEquals("set-get x", ValueE.LOW, x.get());
		assertEquals("set-get y ", ValueE.LOW, y.get());
		assertEquals("set-get cin", ValueE.LOW, cin.get());
        assertEquals("set-get out", ValueE.LOW, out.get());
        assertEquals("set-get carryout", ValueE.LOW, carryout.get());
		
        x.drive(ValueE.HIGH);
		x.WaitOn(SignalEvent.POSEDGE, this);

		assertEquals("set-get x", ValueE.HIGH, x.get());
		assertEquals("set-get y", ValueE.LOW, y.get());
		assertEquals("set-get cin", ValueE.LOW, cin.get());

	    System.out.println("NEG");
		clk.WaitOn(SignalEvent.NEGEDGE, this);
		assertEquals("set-get out", ValueE.HIGH, out.get());
		assertEquals("set-get carryout", ValueE.LOW, carryout.get());
		assertEquals("simtime at ", 10, Global.getTime());
		assertEquals("set-get clk", ValueE.LOW, clk.get());
        
        y.drive(ValueE.HIGH);
		y.WaitOn(SignalEvent.POSEDGE, this);

	    System.out.println("POS");
		clk.WaitOn(SignalEvent.POSEDGE, this);
		assertEquals("set-get x", ValueE.HIGH, x.get());
        assertEquals("set-get y", ValueE.HIGH, y.get());
        assertEquals("set-get cin", ValueE.LOW, cin.get());
        assertEquals("set-get out", ValueE.LOW, out.get());
        assertEquals("set-get cout", ValueE.HIGH, carryout.get());
        
		System.out.println("TC: FIN at " + Global.getTime());		

	}

}
