import java.io.*;

class TestLinkedList 
{
	static int readInt() {
		String s = System.console().readLine();
		return Integer.parseInt(s);
	}

	public static void saveS(String fileName, LinkedList<Integer> list) {
		try
		{
		    ObjectOutputStream os = 
			new ObjectOutputStream(new FileOutputStream(new File(fileName)));
		
		    os.writeObject(list);

		    os.close();
		}
		catch (IOException ex)
		{
		    ex.printStackTrace();
		}
	}

	public static LinkedList<Integer> loadS(String fileName) {
		LinkedList<Integer> list = null;
		try
		{
		    ObjectInputStream is = 
			new ObjectInputStream(new FileInputStream(new File(fileName)));
		
		    list = (LinkedList<Integer>)is.readObject();

		    is.close();

		}
		catch (Exception ex)
		{
		    ex.printStackTrace();
		}
		
		return list;
	}

	public static void save(String fileName, LinkedList<Integer> list) {
	    try
	    {
		BufferedWriter wr = new BufferedWriter(new FileWriter(new File(fileName)));
		wr.write(""+list.size()+"\n");
		String s = "";
		ListIterator<Integer> li = list.listIterator();
		while(li.hasNext()) {
		    int data = li.next();
		    s = s + data + " ";
		}
		wr.write(s);
		wr.close();
	    }
	    catch (IOException ex)
	    {
		ex.printStackTrace();
	    }
	}

	public static void load(String fileName, LinkedList<Integer> list) {
		try
		{
			BufferedReader rd = new BufferedReader(new FileReader(new File(fileName)));
			String line = rd.readLine();
			int n = Integer.parseInt(line);
			line = rd.readLine();
			String num[] = line.split(" ");
			for (int i = 0; i < n; i++)
			{
			    list.addLast(Integer.parseInt(num[i]));
			}

			rd.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		//LinkedList<Integer> aList = new LinkedList<Integer>();

		//load("data.txt", aList);
		LinkedList<Integer> aList = loadS("data.ser");

		while(true) {
			int select;
			System.out.print("What do you want ? <1>addFront, <2>addTail, <3>remove, <4>quit : ");
			select = readInt();
			if (select < 1 || select > 3) {
				break;
			}
			System.out.print("Type data : ");
			int data;
			data = readInt();
			switch (select) {
			case 1 :
				aList.addFirst(data);
				System.out.println(aList);
				break;
			case 2 :
				aList.addLast(data);
				System.out.println(aList);
				break;
			case 3 :
				if (aList.remove(data) == true) {
					System.out.println(aList);
				}
				break;
			default :
				System.out.println(aList);
				break;
			}
		}
		// test other operations
		System.out.println("size of the list = " + aList.size());

		System.out.println("The list can be traversed like the following style.");
		ListIterator<Integer> i = aList.listIterator();
		while(i.hasNext()) {
			int data = i.next();
			System.out.println(data);
		}

		aList.add(2,1000);
		System.out.println(aList);

		//save("data.txt", aList);
		saveS("data.ser", aList);
	}
}
