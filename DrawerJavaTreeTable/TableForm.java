import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;

class MyCellRenderer extends JLabel implements TableCellRenderer
{
	public MyCellRenderer() {
		super();
	}
	public Component getTableCellRendererComponent(JTable table,
                                        Object value,
                                        boolean isSelected,
                                        boolean hasFocus,
                                        int row,
                                        int column) {
		Integer v = (Integer)value;
		if (column == 2)
		{
			setForeground(Color.red);
		} else {
			setForeground(Color.black);
		}
		setText(""+v);
		return this;
	}
}

class Data
{
	String shape;
	int x1,y1,x2,y2;
}
class FigureTableModel extends AbstractTableModel
{
	/*static int MAX = 100;
	String shape[] = new String[MAX];
	int v[][] = new int[MAX][4];
	int count = 0;
	*/
	Vector<Data> v = new Vector<Data>();
	
	public static String[] cNames = {
		"Type","x1","y1","x2","y2"
	};

	public int getRowCount() {
		return v.size();
	}
	public int getColumnCount() {
		return 5;
	}
	public String getColumnName(int column) {
		return cNames[column];
	}
	public Class getColumnClass(int columnIndex) {
		if (columnIndex == 0)
		{
			return String.class;
		} else {
			return Integer.class;
		}
	}
	public void setValueAt(Object aValue,int rowIndex,int columnIndex) {
		// 
	}
	public Object getValueAt(int rowIndex,int columnIndex) {
		Data data = v.elementAt(rowIndex);
		switch (columnIndex)
		{
		case 0:
			return data.shape;
		case 1:
			return data.x1;
		case 2:
			return data.y1;
		case 3:
			return data.x2;
		case 4:		
			return data.y2;
		}
		return null;
	}
	public void addFigure(Figure f) {
		Data data = new Data();
		if (f instanceof Point)
		{
			data.shape = "Point";
			//shape[count] = "Point";
		} else if (f instanceof Line)
		{
			data.shape = "Line";
			//shape[count] = "Line";
		} else if (f instanceof Box)
		{
			data.shape = "Box";
			//shape[count] = "Box";
		} else if (f instanceof Circle)
		{
			data.shape = "Circle";
			//shape[count] = "Circle";
		} else if (f instanceof TV)
		{
			data.shape = "TV";
			//shape[count] = "TV";
		}  else if (f instanceof Star)
		{
			data.shape = "Star";
			//shape[count] = "Star";
		}
		data.x1 = f.getX1();
		data.x2 = f.getX2();
		data.y1 = f.getY1();
		data.y2 = f.getY2();
		v.add(data);
		/*v[count][0] = f.getX1();
		v[count][1] = f.getY1();
		v[count][2] = f.getX2();
		v[count][3] = f.getY2();
		count++;*/
	}
}
class TableForm extends JDialog
{
	JPanel tablePanel;
	JPanel buttonPanel;
	DrawerView view;
	JTable table;
	FigureTableModel figureTable;

	TableForm(DrawerView view) {
		setSize(600,500);

		this.view = view;
		tablePanel = new JPanel();
		buttonPanel = new JPanel();

		figureTable = new FigureTableModel();
		table = new JTable(figureTable);
//		table.setPreferredSize(new Dimension(600,400));
		tablePanel.setLayout(new BorderLayout());
//		tablePanel.add(table,BorderLayout.CENTER);
		JScrollPane jsp = new JScrollPane(table);
		tablePanel.add(jsp,BorderLayout.CENTER);
		table.setDefaultRenderer(Integer.class,
			new MyCellRenderer());

		this.add(tablePanel,"Center");

		JButton deleteButton = new JButton("Delete");
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(
			new ActionListener() {
			 public void actionPerformed(ActionEvent ev) {
				 setVisible(false);
			 }
			}
		);
		buttonPanel.add(deleteButton);
		buttonPanel.add(cancelButton);
		this.add(buttonPanel,"South");
	}
	void showForm() {
		setVisible(true);
	}
	public void addFigure(Figure f){
		figureTable.addFigure(f);
		table.updateUI();
	}
}
