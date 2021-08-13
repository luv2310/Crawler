package poc;

import javax.swing.DebugGraphics;

public class httpgetrequest {
	
	static boolean t =true;
	
	public static void main(String[] args) {
		
	
	try {
		new httpgetrequest().debug(t);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		System.out.println(t);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void debug(boolean t)
	{
		t = false;
	}
}






