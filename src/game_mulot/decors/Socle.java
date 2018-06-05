package game_mulot.decors;

import java.awt.Graphics;
import java.util.ArrayList;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;
import game_mulot.basic.BasicComponent;

/**
 * <p>
 * Objet qui représente un socle soit horizontal, soit vertical. Ses positions X
 * et Y sont celle de la première cellule à gauche dans le cas où le socle est
 * Horizontal, sinon la cellule en haut. Le socle possède aussi une longueur qui
 * n'a pas d'unité (peut-être en case de tableau ou en pixel ou autre).
 * </p>
 * 
 *
 */
public class Socle extends BasicComponent {

	// Variables.

	/**
	 * La longueur du Socle
	 */
	protected int length;

	/**
	 * L'orientation du socle.
	 */
	protected Socle.SocleOrientation orientation;

	// Constructors.

	public Socle() {
		super();
		this.name = "Socle";
		this.length = 1;
		this.orientation = Socle.SocleOrientation.HORIZONTAL;
		// On re initialise le tableau.
		this.initializeListModelisation();
	}

	public Socle(int posX, int posY, int length, Socle.SocleOrientation orientation, Basic Parent) {
		super("Socle", posX, posY, Parent);

		if (length >= 1) {
			this.length = length;
		} else {
			this.length = 1;
		}

		if (orientation != null) {
			this.orientation = orientation;
		} else {
			this.orientation = Socle.SocleOrientation.HORIZONTAL;
		}

		// On re initialise le tableau.
		this.initializeListModelisation();
	}

	// Inherited methods.

	@Override
	protected void initializeListModelisation() {
		if (this.orientation == Socle.SocleOrientation.HORIZONTAL) {
			this.listModelisation = new ArrayList<BasicCellule>();
			for (int i = 0; i < this.length; i++) {
				this.listModelisation
						.add(new CelluleSocle(this.getPosX() + i, this.getPosY(), null, new Character('-'), this));
			}
		} else {
			this.listModelisation = new ArrayList<BasicCellule>();
			for (int i = 0; i < this.length; i++) {
				this.listModelisation
						.add(new CelluleSocle(this.getPosX(), this.getPosY() + i, null, new Character('|'), this));
			}
		}
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
			if (!this.getListModelisation().isEmpty()) {
				// On vérifie si toutes les cellule peuvent être placée à leur
				// endroit respective.
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
		} else {
			verif = true;
		}
		return verif;
	}

	// Getters and Setters.

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		if (length >= 1) {
			this.length = length;
			this.initializeListModelisation();
		}
	}

	/**
	 * @return the orientation
	 */
	public Socle.SocleOrientation getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation
	 *            the orientation to set
	 */
	public void setOrientation(Socle.SocleOrientation orientation) {
		if (this.orientation != null) {
			this.orientation = orientation;
			this.initializeListModelisation();
		}
	}

	// Enumerations.

	public enum SocleOrientation {
		HORIZONTAL("HORIZONTAL"), VERTICAL("VERTICAl");

		private String text = "";

		SocleOrientation(String text) {
			this.setText(text);
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @param text
		 *            the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
	}

}
