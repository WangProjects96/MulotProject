
package game_mulot.basic;

import java.awt.Color;
import java.awt.Graphics;

/**
 * <p>
 * Class qui repr�sente une cellule d'un GraphicObject. Un GraphicObject peut
 * donc contenir une ou plusiseurs BasicCellule.
 * </p>
 * <p>
 * Une BasicCellule poss�de une coordonn�e X et une coordonn�e Y repr�sentant sa
 * position (A vous de voir si la position est celle dans l'objet dans laquell
 * elle est ou si c'est dans un autre objet).
 * </p>
 * <p>
 * Une basicCellule poss�de aussi un Objet parent qui permet de savoir � qui
 * elle est rali�e. (ce parent peut-�tre null).
 * </p>
 * <p>
 * Elle poss�de aussi un boolean disant si cette cellule est traversable ou non,
 * cela permet de v�rifier lors de d�placements si l'on peut se trouver ou non �
 * la m�me place qu'elle.
 * </p>
 * <p>
 * Enfin elle poss�de une couleur et un objet qui sera son apparence. Il faudra
 * alors ensuite implementer les m�thodes <strong>draw(Graphic g)</strong> et
 * <strong>draw()</strong> pour d�finir comment utiliser comme vous le voulez
 * ces propri�t�es.
 * </p>
 * 
 * <p>
 * <strong>Liste des propri�t�s:</br>
 * </strong>
 * <ul>
 * <li>posX</li>
 * <li>posY</li>
 * <li>crossable</li>
 * <li>parent</li>
 * <li>color</li>
 * <li>apparence</li>
 * </ul>
 * </p>
 * 
 *
 */
public abstract class BasicCellule extends Basic {

	// Variables.

	/**
	 * La coordonn�e X de la cellule.
	 */
	protected int posX;

	/**
	 * La coordonn�e Y de la cellule.
	 */
	protected int posY;

	/**
	 * Le boolean pour savoir si la cellule peut �tre travers�e ou non.
	 */
	protected boolean crossable;

	/**
	 * La couleur de la cellule.
	 */
	protected Color color;

	/**
	 * L'objet qui peut repr�senter la cellule.
	 */
	protected Object apparence;

	// Constructors.

	/**
	 * Initialise toutes les propri�t�s � z�ro, true ou null.
	 */
	public BasicCellule() {
		super();
		this.posX = 0;
		this.posY = 0;
		this.crossable = true;
		this.parent = null;
		this.color = null;
		this.apparence = null;
	}

	/**
	 * <p>
	 * Donne des positions X et Y � la cellule, un �tat traversable ou non et un
	 * parent.
	 * </p>
	 * 
	 * @param posX
	 *            - la position X de la cellule.
	 * @param posY
	 *            - la position Y de la cellule.
	 * @param crossable
	 *            - l'�tat traversable ou non de la cellule.
	 * @param parent
	 *            - le parent de la celllule.
	 */
	public BasicCellule(int posX, int posY, boolean crossable, Basic parent) {
		super("Cellule", parent);
		this.setPosX(posX);
		this.setPosY(posY);
		this.setCrossable(crossable);
		this.setColor(null);
		this.setApparence(null);
	}

	/**
	 * <p>
	 * Permet d'initialiser toutes les propri�t� de la cellule.
	 * </p>
	 * 
	 * @param posX
	 *            - la position X.
	 * @param posY
	 *            - la position Y.
	 * @param crossable
	 *            - le boolean pour savoir si elle est traversable ou non.
	 * @param color
	 *            - la couleur.
	 * @param apparence
	 *            - l'apparence.
	 * @param parent
	 *            - le parent.
	 */
	public BasicCellule(int posX, int posY, boolean crossable, Color color, Object apparence, Basic parent) {
		super("Cellule", parent);
		this.setPosX(posX);
		this.setPosY(posY);
		this.setCrossable(crossable);
		this.setColor(color);
		this.setApparence(apparence);
	}

	// Abstract methods.

	/**
	 * Permet de dessiner la cellule avec un Graphics.
	 * 
	 * @param g
	 *            - l'objet qui va dessiner l'objet.
	 */
	public abstract void draw(Graphics g);

	/**
	 * Permet de dessiner l'objet.
	 */
	public abstract void draw();

	/**
	 * <p>
	 * V�rifie la position indiqu�e en param�tre pour savoir si la cellule peut
	 * �tre d�placer � cette position ou non.
	 * </p>
	 * 
	 * <strong>Utilis� dans les setters de posX et posY.</strong>
	 * 
	 * @param posX
	 *            - la coordonn�e x de la position � v�rifier.
	 * @param posY
	 *            - la coordonn�e y de la position � v�rifier.
	 * 
	 * @return true si la cellule peut �tre d�placer aux positions indiqu�es
	 *         sinon false.
	 */
	public abstract boolean verifyPosition(int posX, int posY);

	// Public methods.

	@Override
	public String toString() {
		return "BasicCellule [posX=" + posX + ", posY=" + posY + ", crossable=" + crossable + ", parent="
				+ ((parent == null) ? "NULL" : parent.toString()) + ", color="
				+ ((color == null) ? "NULL" : color.toString()) + ", apparence="
				+ ((apparence == null) ? "NULL" : apparence.toString()) + "]";
	}

	// Getters and Setters.

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Change de position X
	 * 
	 * @param posX
	 *            the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Change de position Y.
	 * 
	 * @param posY
	 *            the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return the crossable
	 */
	public boolean isCrossable() {
		return crossable;
	}

	/**
	 * @param crossable
	 *            the crossable to set
	 */
	public void setCrossable(boolean crossable) {
		this.crossable = crossable;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the apparence
	 */
	public Object getApparence() {
		return apparence;
	}

	/**
	 * @param apparence
	 *            the apparence to set
	 */
	public void setApparence(Object apparence) {
		this.apparence = apparence;
	}

}
