import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TableDialog extends JDialog {

	static class FigureTableModel implements TableModel{
		DrawerView view;
		ArrayList<Figure> figures;
		static final String[] columnNames = new String[] {
				"Figure Type", "x1", "y1", "x2", "y2"
		};
		static final Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Integer.class, Integer.class
		};
		
		FigureTableModel(DrawerView view) {
			this.view = view;
			figures = view.getFigures();
		}
		
		@Override
		public int getRowCount() {
			return figures.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return columnNames[columnIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Figure ptr = figures.get(rowIndex);
			switch(columnIndex) {
				case 0: return ptr.getClass().getName();
				case 1: return ptr.getX1();
				case 2: return ptr.getY1();
				case 3: return (ptr.getX2() > 0 ? ptr.getX2() : null);
				case 4: return (ptr.getY2() > 0 ? ptr.getY2() : null);
				default: return null;
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			
		}
		
	}
	
	static class FigureTable extends JTable {
		FigureTable(DrawerView view) {
			super(new FigureTableModel(view));
			DefaultListSelectionModel selectionModel
			= new DefaultListSelectionModel();
			setSelectionModel(selectionModel);
			
			TableColumnModel colModel = getColumnModel();
			TableColumn nameColumn = colModel.getColumn(0);
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(SwingConstants.CENTER);
			nameColumn.setCellRenderer(renderer);
		}
		public int getSelectedIndex() {
			return selectionModel.getMinSelectionIndex(); 
		}
	}
	
	static class DialogPanel extends JPanel implements ActionListener{		
		JDialog dialog;
		DrawerView view;
		JButton done;
		JButton remove;
		FigureTable table;
		
		DialogPanel(JDialog dialog, DrawerView view) {
			this.view = view;
			this.dialog = dialog;
			setLayout(new BorderLayout());
			
			table = new FigureTable(view);
			JScrollPane sp = new JScrollPane(table);
			add(sp,BorderLayout.CENTER);
			
			JPanel bottom = new JPanel();
			bottom.add(remove = new JButton("Remove"));
			bottom.add(done = new JButton("Done"));
			add(bottom,BorderLayout.SOUTH);
			
			done.addActionListener(this);
			remove.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == done) {
				dialog.setVisible(false);
			} else if (event.getSource() == remove) {
				view.removeFromFigures(table.getSelectedIndex());
				updateUI();
			}
		}
		
		public void updateUI() {
			if (table != null) table.updateUI();
		}
	}
	
	TableDialog(String title, DrawerView view) {
		super((JFrame)null,title);
		setLocation(200,300);
		setSize(400,300);
		
		Container container = getContentPane();
		JPanel panel = new DialogPanel(this,view);
		container.add(panel);		
		
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				panel.updateUI();
			}
		});
		
	}
	
}
