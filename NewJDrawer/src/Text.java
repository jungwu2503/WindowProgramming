import java.awt.*;

public class Text extends Box {

	String[] lines;
	
	Text(Color color) {
		super(color,0.1f);
	}
	Text(Color color, int x, int y) {
		super(color,0.1f,x,y);
	}
	Text(Color color, int x1, int y1, int x2, int y2, String lines[]) {
		super(color,0.1f,x1,y1,x2,y2);
		this.lines = lines;
	}
	void setColor(Color color) {
		super.setColor(color);
	}
	
	void draw(Graphics g) {
		g.setColor(color);

		Font f = g.getFont();
		FontMetrics fm = g.getFontMetrics(f);
		int height = fm.getHeight();
		int ascent = fm.getAscent();
		for (int i = 0; i < lines.length; i++) {
			g.drawString(lines[i], x1, y1+(ascent+i*height));
		}
	}	
	
	Figure copy() {
		Text newText = new Text(color,x1,y1,x2,y2,lines);
		newText.popup = popup;
		newText.move(MOVE_DX, MOVE_DY);
		return newText;
	}
	
}
