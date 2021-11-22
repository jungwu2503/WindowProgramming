// MainFrame.java

//{{MODELER_BEFORE_CLASS(MainFrame) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: JFrame, WindowListener
//Purpose of MainFrame class:
/**
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//}}MODELER_BEFORE_CLASS

public class MainFrame extends JFrame implements WindowListener 
{

// Operations
    public MainFrame(String title) {
      super(title);
      Dimension screenSize = getToolkit().getScreenSize();
      setSize(screenSize.width,screenSize.height);

      final DrawerView view = new DrawerView(screenSize.width,screenSize.height);
      getContentPane().add(view);
      
      JMenuBar menus = new JMenuBar();
      setJMenuBar(menus);

      JMenu fileMenu = new JMenu("파일(F)");
      fileMenu.setMnemonic('F');
      menus.add(fileMenu);

	  JMenu toolMenu = new JMenu("Tool");
	  menus.add(toolMenu);

	  JMenuItem treeForm = new JMenuItem("TreeForm");
	  toolMenu.add(treeForm);
	  treeForm.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            view.showTreeForm();
         }
      });

	  JMenuItem tableForm = new JMenuItem("TableForm");
	  toolMenu.add(tableForm);
	  tableForm.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            view.showTableForm();
         }
      });

      JMenuItem newFile = new JMenuItem("새 파일(N)");
      newFile.setMnemonic('N');
      newFile.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            view.getModel().onFileNew();
         }
      });
      fileMenu.add(newFile);

      JMenuItem openFile = new JMenuItem("열기(O)");
      openFile.setMnemonic('O');
      openFile.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            view.getModel().onFileOpen();
         }
      });
      fileMenu.add(openFile);

      JMenuItem saveFile = new JMenuItem("저장(S)");
      saveFile.setMnemonic('S');
      saveFile.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            view.getModel().onFileSave();
         }
      });
      fileMenu.add(saveFile);

      JMenuItem anotherFile = new JMenuItem("다른 이름으로 저장(A)");
      anotherFile.setMnemonic('A');
      anotherFile.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            view.getModel().onFileSaveAs();
         }
      });
      fileMenu.add(anotherFile);

      fileMenu.addSeparator();

      JMenuItem exit = new JMenuItem("종료(X)");
      exit.setMnemonic('X');
      exit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });
      fileMenu.add(exit);
    }
    public void windowClosing(WindowEvent e) {
      System.exit(0); 
    }
    public void windowOpened(WindowEvent e) {

    }
    public void windowClosed(WindowEvent e) {

    }
    public void windowIconified(WindowEvent e) {

    }
    public void windowDeiconified(WindowEvent e) {

    }
    public void windowActivated(WindowEvent e) {

    }
    public void windowDeactivated(WindowEvent e) {

    }
}
