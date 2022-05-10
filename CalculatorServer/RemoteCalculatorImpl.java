import java.rmi.*;
import java.rmi.server.*;

public class RemoteCalculatorImpl extends UnicastRemoteObject implements RemoteCalculator
{
	public RemoteCalculatorImpl() throws RemoteException {
	}

	public int add(int x, int y) throws RemoteException {
		return x+y;
	}
	public int sub(int x, int y) throws RemoteException {
		return x-y;
	}
	public int mul(int x, int y) throws RemoteException {
		return x*y;
	}
	public int div(int x, int y) throws RemoteException {
		return x/y;
	}

	public static void main(String[] args) 
	{
		try
		{
			RemoteCalculatorImpl x = new RemoteCalculatorImpl();
			Naming.rebind("Calc", x);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
