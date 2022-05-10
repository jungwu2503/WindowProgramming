import java.rmi.*;

class TestRMI 
{
	public static void main(String[] args) 
	{
		try
		{
			MyRemote x = (MyRemote)Naming.lookup("rmi://172.22.205.76/Responser");
			String s = x.sayHello();
			System.out.println(s);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
