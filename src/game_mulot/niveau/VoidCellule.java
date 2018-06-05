package game_mulot.niveau;

import java.awt.Color;
import java.awt.Graphics;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;

/**
 * Cellule qui représente le vide donc toujours traversable. Par défaut
 * l'apparence est un Character(' ').
 * 
 *
 */
public class VoidCellule extends BasicCellule {

	public VoidCellule() {
		super();
		this.crossable = true;
		this.apparence = new Character(' ');
	}

	public VoidCellule(int posX, int posY, Basic parent) {
		super(posX, posY, true, parent);
		this.apparence = new Character(' ');
	}

	public VoidCellule(int posX, int posY, Color color, Object apparence, Basic parent) {
		super(posX, posY, true, color, apparence, parent);
	}

	@Override
	public void draw(Graphics g) {
		// Non utilisé.
	}

	@Override
	public void draw() {
		System.out.print(this.getApparence());
	}

	@Override
	public boolean verifyPosition(int posX, int posY) {
		return true;
	}

}
