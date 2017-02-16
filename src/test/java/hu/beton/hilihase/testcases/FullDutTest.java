package hu.beton.hilihase.testcases;

import static org.junit.Assert.assertEquals;
import hu.beton.hilihase.jfw.Global;
import hu.beton.hilihase.jfw.Signal;
import hu.beton.hilihase.jfw.SignalEvent;
import hu.beton.hilihase.jfw.TCThread;
import hu.beton.hilihase.jfw.ValueE;

public class FullDutTest extends TCThread {

	@Override
	public void test() {
		System.out.println("TEST RUNNING!!!");
		Signal sig1;
		sig1 = Global.get("clk");
		System.out.println("POS");
		sig1.WaitOn(SignalEvent.POSEDGE, this);
		assertEquals("simtime", 2, Global.getTime());
		assertEquals("set-get", ValueE.HIGH, sig1.get());
		System.out.println("TC: FIN");		

	}

}