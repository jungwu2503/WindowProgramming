// FigureList.java

//{{MODELER_BEFORE_CLASS(FigureList) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: Vector
//Purpose of FigureList class:
/**
*/

import java.util.Vector;

//}}MODELER_BEFORE_CLASS

class FigureList extends Vector 
{
// Attributes
    static final long serialVersionUID = -5507543155571264736L;

// Operations
    FigureList() {
      super();
    }
    Figure getAt(int index) {
      Object obj = super.get(index);
      Class theClass = obj.getClass();
      if (Figure.isKindOf(obj,"Figure")) {
         return (Figure)obj;
      } else {
         return null;
      }
    }
    void addTail(Figure ptr) {
      super.add(ptr);
    }
    void removeFigure(Figure ptr) {
      super.remove(ptr);
    }
}
