package game_mulot.perso;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicComponent;

/**
 * <p>
 * Représente un personnage. Il hérite de la class BasicObject et à quatre
 * méthodes abstraites en plus :</br>
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
	 * Déplace le Personage vers le haut.
	 * 
	 * @param deplacement
	 *            - le nombre de déplacement vers le haut.
	 */
	public abstract void moveUp(int deplacement);

	/**
	 * Déplace le Personage vers le bas.
	 * 
	 * @param deplacement
	 *            - le nombre de déplacement vers le bas.
	 */
	public abstract void moveDown(int deplacement);

	/**
	 * Déplace le Personage vers la droite.
	 * 
	 * @param deplacement
	 *            - le nombre de déplacement vers la droite.
	 */
	public abstract void moveRight(int deplacement);

	/**
	 * Déplace le Personage vers la gauche.
	 * 
	 * @param deplacement
	 *            - le nombre de déplacement vers la gauche.
	 */
	public abstract void moveLeft(int deplacement);

}
