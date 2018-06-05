package game_mulot.basic;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * <p>
 * Matrix de basic cellule qui peut représenter une map par exemple.
 * </p>
 * <p>
 * Cette objet possède comme propriétés le nombre de line, le nombre de
 * colonnes, le tableau de Basic à une dimension qui représente la matrice et la
 * liste des composants à afficher dans la matrice.
 * </p>
 * 
 *
 */
public abstract class BasicMatrix extends Basic {

	// Variables.

	/**
	 * Le nombre de lignes de la matrice.
	 */
	protected int lines;

	/**
	 * Le nombre de colonnes de la matrice.
	 */
	protected int columns;

	/**
	 * Le tableau de cellules qui représente la matrice.
	 */
	protected BasicCellule matrix[];

	// Constructors

	public BasicMatrix() {
		super();
		this.lines = 1;
		this.columns = 1;
		this.initializeMatrix();
	}

	public BasicMatrix(String name, int lines, int columns, ArrayList<BasicComponent> listComponent, Basic parent) {
		super(name, parent);
		if (lines >= 1)
			this.lines = lines;
		else
			this.lines = 1;
		if (columns >= 1)
			this.columns = columns;
		else
			this.columns = 1;
		this.initializeMatrix();
	}

	// Abstract methods.

	/**
	 * <p>
	 * Permet d'initialiser la matrice de BasicCellules. (Cette méthode est
	 * utilisée dans le constructeur de la class <strong>BasicObject</strong>)
	 * </p>
	 * 
	 * @see BasicMatrix#BasicMatrix()
	 * @see BasicMatrix#BasicMatrix(String, int, int, ArrayList, Basic)
	 * @see BasicMatrix
	 */
	protected abstract void initializeMatrix();

	/**
	 * Permet d'inclure les composants dans la matrices.
	 */
	protected abstract void includeComponentInMatrix();

	/**
	 * Permet de dessiner la matrix à partir d'un objet Graphics.
	 * 
	 * @param g
	 *            - Graphic qui va dessiner la BasicMatrix.
	 */
	public abstract void draw(Graphics g);

	/**
	 * Permet de dessiner la matrice.
	 */
	public abstract void draw();

	// Public methods.

	@Override
	public String toString() {
		return "BasicMatrix [name=" + name + ", lines=" + lines + ", columns=" + columns + ",parent=" + parent + "]";
	}

	// Getters and Setters.

	/**
	 * @return the lines
	 */
	public int getLines() {
		return lines;
	}

	/**
	 * @param lines
	 *            the lines to set
	 */
	public void setLines(int lines) {
		if (lines >= 1) {
			this.lines = lines;
			this.initializeMatrix();
		}
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(int columns) {
		if (columns >= 1) {
			this.columns = columns;
			this.initializeMatrix();
		}
	}

	/**
	 * @return the matrix
	 */
	public BasicCellule[] getMatrix() {
		return matrix;
	}

}
