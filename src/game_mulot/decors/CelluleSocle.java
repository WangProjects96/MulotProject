package game_mulot.decors;

import java.awt.Color;
import java.awt.Graphics;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;
import game_mulot.niveau.Niveau;
import game_mulot.niveau.VoidCellule;

/**
 * <p>
 * La cellule qui va composer un <strong>Socle</strong>. Elle n'est pas
 * traversable et est par défaut représenter par un Character('-').
 * </p>
 * 
 *
 */
public class CelluleSocle extends BasicCellule {

	public CelluleSocle() {
		super();
		this.crossable = false;
		this.apparence = new Character('-');
	}

	public CelluleSocle(int posX, int posY, Basic parent) {
		super(posX, posY, true, parent);
		this.crossable = false;
		this.apparence = new Character('-');
	}

	public CelluleSocle(int posX, int posY, Color color, Object apparence, Basic parent) {
		super(posX, posY, false, color, apparence, parent);
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
		if (this.parent != null) { // Si on a pas un parent null.
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
