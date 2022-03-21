import java.lang.reflect.*;
import java.util.*;
import java.io.*;

class TestReflection 
{
	public static void main(String[] args) 
	{
	    try
	    {
		BufferedReader reader = new BufferedReader(new FileReader(new File("data.txt")));

		String className = reader.readLine();

		Class c = Class.forName(className);
		Object obj = c.newInstance();

		String command = null;
		while ((command = reader.readLine()) != null)
		{
		    System.out.println("> " + command);
		    String words[] = command.split(" ");
		    Method[] method = c.getDeclaredMethods();
		    Method m = null;
		    for (int i = 0; i < method.length; i++)
		    {
			if (method[i].getName().equals(words[0]))
			{
			    Class[] paraTypes = method[i].getParameterTypes();
			    if (paraTypes.length == words.length-1)
			    {
				m = method[i];
				break;
			    }
			}
		    }
		    Object[] paras = null;
		    if (words.length == 1)
		    {
			paras = null;
		    } else if (words.length == 2)
		    {
			paras = new Object[1];
			paras[0] = words[1];
		    } else if (words.length == 3)
		    {
			paras = new Object[2];
			paras[0] = words[1];
			paras[1] = words[2];
		    }
		    Object returnValue = m.invoke(obj, paras);
		    System.out.println(returnValue + ": "  + obj);
		}
	    }
	    catch (Exception ex)
	    {
		ex.printStackTrace();
	    }
	}


    /*
	public static void main(String[] args) 
	{
	    try
	    {
		Class st = Class.forName("java.util.Stack"); //class record
		Stack s = (Stack)st.newInstance(); // dynamic loading

		Method[] method = st.getDeclaredMethods();
		Method m = null;
		for (int i = 0; i < method.length; i++)
		{
		    if (method[i].getName().equals("push"))
		    {
			m = method[i];
			break;
		    }
		}
		m.invoke(s, 10); // = s.push(10)

		System.out.println(s);
	    }
	    catch (ClassNotFoundException ex)
	    {
		ex.printStackTrace();
	    }
	    catch (InstantiationException ex) {
		ex.printStackTrace();
	    }
	    catch (IllegalAccessException ex) {
		ex.printStackTrace();
	    }
	    catch (InvocationTargetException ex) {
		ex.printStackTrace();
	    }
		
	}
	*/
}
