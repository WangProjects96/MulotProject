package game_mulot.basic;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * <p>
 * Objet compos� de BasicCellule dans un tableau. Il poss�de une position X etun
 * position Y et un Objet parent.
 * </p>
 * 
 * <p>
 * <strong>Liste des propri�t�s :</br>
 * </strong>
 * 
 * <ul>
 * <li>name</li>
 * <li>posX</li>
 * <li>posY</li>
 * <li>tabModelisation</li>
 * <li>parent</li>
 * </ul>
 * </p>
 * 
 * @author Calli
 *
 */
public abstract class BasicComponent extends Basic {

	// Variables.

	/**
	 * La position X de l'objet.
	 */
	protected int posX;
	
	/**
	 * La position Y de l'objet.
	 */
	protected int posY;

	/**
	 * La liste qui contient toutes les cellules qui vont mod�liser l'objet.
	 */
	protected ArrayList<BasicCellule> listModelisation;

	// Constructors.

	/**
	 * Initialise � 0, "" ou null toutes les propri�t�s.
	 */
	public BasicComponent() {
		super();
		this.initializeListModelisation();
		this.posX = 0;
		this.posY = 0;
		this.listModelisation = null;
	}

	public BasicComponent(String name, int posX, int posY, Basic parent) {
		super(name, parent);
		this.initializeListModelisation();
		this.setPosX(posX);
		this.setPosY(posY);

	}

	// Abstract methods.

	/**
	 * Initialise le tableau de BasicCellule.
	 */
	protected abstract void initializeListModelisation();

	/**
	 * Dessine l'objet avec un Objet Graphics.
	 * 
	 * @param g
	 *            - objet Graphic qui va dessiner l'objet.
	 */
	public abstract void draw(Graphics g);

	/**
	 * Dessine l'objet.
	 */
	public abstract void draw();

	/**
	 * Permet de v�rifier une position pour savoir si l'objet peut se d�place �
	 * cette position ou non. (M�thode utilis�e dans les setters de posX et
	 * posY).
	 * 
	 * @param posX
	 *            - la coordonn�e X de la position � v�rifier.
	 * @param posY
	 *            - la coordonn�e Y de la position � v�rifier.
	 * 
	 * @see BasicComponent#setPosX(int)
	 * @see BasicComponent#setPosY(int)
	 * 
	 * @return true si l'objet peut se d�placer � la position sinon false.
	 */
	public abstract boolean verifyPosition(int posX, int posY);

	// Public methods.

	@Override
	public String toString() {
		return "BasicObject [name=" + name + ", posX=" + posX + ", posY=" + posY + ", parent="
				+ ((parent == null) ? "NULL" : parent.toString()) + "]";
	}

	// Getters and Setters.

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Change de position X si la nouvelle position a �t� v�rifi�e est
	 * approuv�e.
	 * 
	 * @param posX
	 *            the posX to set
	 * 
	 * @see BasicCellule#verifyPosition(int, int)
	 */
	public void setPosX(int posX) {
		// On v�rifie si on peut se d�placer.
		if (this.verifyPosition(posX, this.getPosY())) {
			// On fait bouger toutes les cellules.
			for (BasicCellule cellule : this.listModelisation) {
				cellule.setPosX(posX - (this.getPosX() - cellule.getPosX()));
			}
			// On change la position.
			this.posX = posX;
		}
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Change de position Y si la nouvelle position a �t� v�rifi�e est
	 * approuv�e.
	 * 
	 * @param posY
	 *            the posY to set
	 * 
	 * @see BasicCellule#verifyPosition(int, int)
	 */
	public void setPosY(int posY) {
		// On v�rufue si on peut se d�placer.
		if (this.verifyPosition(this.getPosX(), posY)) {
			// On fait bouger toutes les cellules.
			for (BasicCellule cellule : this.listModelisation) {
				cellule.setPosY(posY - (this.getPosY() - cellule.getPosY()));
			}
			// On change la position.
			this.posY = posY;
		}
	}

	/**
	 * @return the tabModelisation
	 */
	public ArrayList<BasicCellule> getListModelisation() {
		return listModelisation;
	}

}
