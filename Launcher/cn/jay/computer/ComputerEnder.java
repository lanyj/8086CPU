package cn.jay.computer;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import cn.jay.computer.clk.CLK;
import cn.jay.computer.port.PortMgr;
import cn.jay.modelprovider.ModelMgr;
import modelinterface.ModelInterface;

public class ComputerEnder {
	public static final void end() {
		System.out.println("Starting shut down...");
		CLK.close();
		
		PortMgr.PORT_SERVER.close();
		
		Set<Entry<String, ModelInterface>> mods = ModelMgr.MODELS.entrySet();
		for (Iterator<Entry<String, ModelInterface>> mod = mods.iterator(); mod.hasNext();) {
			ModelInterface p = mod.next().getValue();
			p.undeploy();
			System.out.println(p.getModelName() + " - - - - shut down.");
		}
		
		System.out.println("Shut down complete...");
	}
}
