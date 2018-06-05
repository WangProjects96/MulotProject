package game_mulot.perso;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicComponent;

/**
 * <p>
 * Repr�sente un personnage. Il h�rite de la class BasicObject et � quatre
 * m�thodes abstraites en plus :</br>
 * -<strong>moveUp</strong></br>
 * -<strong>moveDown</strong></br>
 * -<strong>moveRight</strong></br>
 * -<strong>moveLeft</strong>
 * </p>
 * 
 *
 */
public abstract class Personage extends BasicComponent {

	// Constructors.

	public Personage() {
		super();
	}

	public Personage(String name, int posX, int posY, Basic Parent) {
		super(name, posX, posY, Parent);
	}

	// Abstract methods.

	/**
	 * D�place le Personage vers le haut.
	 * 
	 * @param deplacement
	 *            - le nombre de d�placement vers le haut.
	 */
	public abstract void moveUp(int deplacement);

	/**
	 * D�place le Personage vers le bas.
	 * 
	 * @param deplacement
	 *            - le nombre de d�placement vers le bas.
	 */
	public abstract void moveDown(int deplacement);

	/**
	 * D�place le Personage vers la droite.
	 * 
	 * @param deplacement
	 *            - le nombre de d�placement vers la droite.
	 */
	public abstract void moveRight(int deplacement);

	/**
	 * D�place le Personage vers la gauche.
	 * 
	 * @param deplacement
	 *            - le nombre de d�placement vers la gauche.
	 */
	public abstract void moveLeft(int deplacement);

}
