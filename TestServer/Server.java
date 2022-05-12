import java.rmi.*;

public interface Server extends Remote 
{
	public String report(String ip, int salesData) throws RemoteException;
	public int query(String ip) throws RemoteException;
}
