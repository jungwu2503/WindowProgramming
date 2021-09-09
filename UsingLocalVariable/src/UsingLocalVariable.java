
public class UsingLocalVariable {
	
	void method(int arg) { 
		int localVar = 40; // arg , localVar은 final 특성을 가짐
		
		//arg = 31;
		//localVar = 41;
		
		MyFunctionalInterface fi = () -> {
			System.out.println("arg: " + arg);
			System.out.println("locarVar: " + localVar + "\n");
		};
		
		fi.method();
		
	}
	
}
