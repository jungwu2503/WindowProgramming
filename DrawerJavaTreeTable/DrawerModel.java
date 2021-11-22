// DrawerModel.java

//{{MODELER_BEFORE_CLASS(DrawerModel) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: 
//Purpose of DrawerModel class:
/**
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//}}MODELER_BEFORE_CLASS

public class DrawerModel 
{
// Attributes
    private DrawerView _view;
    private FigureList _figures;
    private String _fileName;
    private boolean _modifiedFlag;

// Operations
    private void doSave() {
      try {
         FileOutputStream  fos = new FileOutputStream(_fileName);
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(_figures);
         oos.flush();
         oos.close();
         fos.close();
      } catch(IOException ex) {
      }
      _modifiedFlag = false;
    }
    public DrawerModel(DrawerView view) {
      super();
      _view = view;
      _figures = new FigureList();
      _fileName = "";
      _modifiedFlag = true;
    }
    public void setModifiedFlag() {
      _modifiedFlag = true;
    }
    FigureList getFigures() {
      return _figures;
    }
    void addFigure(Figure ptr) {
      _figures.addTail(ptr);
    }
    void removeFigure(Figure ptr) {
      _figures.removeFigure(ptr);
    }
    public void actionPerformed(ActionEvent event) {
      String command = event.getActionCommand();
    }
    public void onFileNew() {
      _figures.removeAllElements();
      _view.resetSelectedFigure();
      _modifiedFlag = true;
      _view.repaint();
    }
    public void onFileOpen() {
      JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
      chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      chooser.setDialogType(JFileChooser.OPEN_DIALOG);
      int returnVal = chooser.showOpenDialog(null);
      if (returnVal != JFileChooser.APPROVE_OPTION) return;
      _fileName = chooser.getSelectedFile().getPath();
      _figures.removeAllElements();
      _view.resetSelectedFigure();
      try {
         FileInputStream fis = new FileInputStream(_fileName);
         ObjectInputStream ois = new ObjectInputStream(fis);
         _figures = (FigureList)(ois.readObject());
         for(int i = 0; i < _figures.size(); i++) {
            Figure ptr = _figures.getAt(i);
            ptr.makeRegion();
            if (ptr.getClass().getName().equals("Point")) {
               ptr.setPopup(_view.pointPopup);
            } else if (ptr.getClass().getName().equals("Line")) {
               ptr.setPopup(_view.linePopup);
            } else if (ptr.getClass().getName().equals("Box")) {
               ptr.setPopup(_view.boxPopup);
            } else if (ptr.getClass().getName().equals("Circle")) {
               ptr.setPopup(_view.circlePopup);
            } else if (ptr.getClass().getName().equals("TV")) {
               ptr.setPopup(_view.tvPopup);
            } else if (ptr.getClass().getName().equals("Star")) {
                ptr.setPopup(_view.starPopup);
             }
         }
         ois.close();
         fis.close();
      } catch(ClassNotFoundException ex) {
      } catch(IOException ex) {
      }
      _modifiedFlag = false;
      _view.repaint();
    }
    public void onFileSave() {
      if (_modifiedFlag == false) return;
      if (_fileName != null && _fileName.length() > 0) {
         doSave();
         return;
      }
      onFileSaveAs();
    }
    public void onFileSaveAs() {
      JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
      chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      chooser.setDialogType(JFileChooser.SAVE_DIALOG);
      int returnVal = chooser.showSaveDialog(null);
      if (returnVal != JFileChooser.APPROVE_OPTION) return;
      String fileName = chooser.getSelectedFile().getPath();
      if (fileName == null) return;
      if (fileName.length() == 0) return;
      _fileName = fileName;
      doSave();
    }
}
