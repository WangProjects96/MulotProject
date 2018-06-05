package game_mulot.niveau;

import java.awt.Color;
import java.awt.Graphics;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;

/**
 * Cellule de sortie d'un niveau.
 * 
 *
 */
public class CelluleExit extends BasicCellule {

	// Constructors.

	public CelluleExit() {
		super();
		this.crossable = true;
		this.apparence = new Character('S');
	}

	public CelluleExit(int posX, int posY, Basic parent) {
		super(posX, posY, true, parent);
		this.apparence = new Character('S');
	}

	public CelluleExit(int posX, int posY, Color color, Object apparence, Basic parent) {
		super(posX, posY, true, color, apparence, parent);
	}

	// Inherited methods.

	@Override
	public void draw(Graphics g) {

	}

	@Override
	public void draw() {
		System.out.print(this.getApparence());
	}

	@Override
	public boolean verifyPosition(int posX, int posY) {
		if (this.parent != null) { // Si on a pas un parent null.
			// Si notre parent est bien un niveau (RAJOUTER PLUS TARD DES
			// CONDITIONS).
			if (this.parent.getParent() != null) {
				if (this.parent.getParent().getClass() == Niveau.class) {
					Niveau n = ((Niveau) this.parent.getParent());
					// Si la case où l'on veut aller est une VoidCellule.
					if (n.getMatrix()[posY * n.getColumns() + posY].getClass() == VoidCellule.class) {
						return true;
					} else {
						return false;
					}
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

}
