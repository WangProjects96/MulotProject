package game_mulot.niveau;

import java.awt.Graphics;
import java.util.ArrayList;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;
import game_mulot.basic.BasicComponent;

/**
 * Sortie d'un niveau.
 * 
 *
 */
public class NiveauExit extends BasicComponent {

	public NiveauExit() {
		super();
	}

	public NiveauExit(int posX, int posY, Basic Parent) {
		super("Exit", posX, posY, Parent);
	}

	@Override
	protected void initializeListModelisation() {
		this.listModelisation = new ArrayList<BasicCellule>();
		this.listModelisation.add(new CelluleExit(this.getPosX(), this.getPosY(), this));
	}

	@Override
	public void draw(Graphics g) {
		for (BasicCellule cellule : this.listModelisation) {
			cellule.draw(g);
		}
	}

	@Override
	public void draw() {
		for (BasicCellule cellule : this.listModelisation) {
			cellule.draw();
		}
	}

	@Override
	public boolean verifyPosition(int posX, int posY) {
		boolean verif = false;

		if (this.getListModelisation() != null) {
			for (BasicCellule cellule : this.getListModelisation()) {
				verif = cellule.verifyPosition(posX - (this.getPosX() - cellule.getPosX()),
						posY - (this.getPosY() - cellule.getPosY()));
				if (!verif) {
					break;
				}
			}
		} else {
			verif = true;
		}

		return verif;
	}

}
