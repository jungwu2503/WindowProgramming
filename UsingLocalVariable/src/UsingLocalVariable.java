
public class UsingLocalVariable {
	
	void method(int arg) { 
		int localVar = 40; // arg , localVar�� final Ư���� ����
		
		//arg = 31;
		//localVar = 41;
		
		MyFunctionalInterface fi = () -> {
			System.out.println("arg: " + arg);
			System.out.println("locarVar: " + localVar + "\n");
		};
		
		fi.method();
		
	}
	
}
