package poc;

import javax.swing.DebugGraphics;

public class httpgetrequest {
	
	static boolean t =true;
	
	public static void main(String[] args) {
		int outputArray[] = new int[50];
		int arrayinteger [] = {1,2,3,4,5,6,7,8,9,10};
		int targetnumber = 10 ;
	for (int i =0 ;  i<arrayinteger.length ; i++)
	{
		int first_compare = arrayinteger[i];
	
		for(int z =0 ;  z<arrayinteger.length ; z++)
		{	int pairingelement = arrayinteger[z]; 
		if(i==z) {
			System.out.println("empty array");
		}else {
			if(first_compare+pairingelement == targetnumber)
			{
				outputArray[0]=first_compare;
				outputArray[1]=pairingelement;

				System.out.println(outputArray.toString());
			}
		}
			
		}
	}
		
	}
	}





