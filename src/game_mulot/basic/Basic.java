package game_mulot.basic;

/**
 * <p>
 * Cette class est la class basic dont tous les objets hérites. Elle est
 * composée d'une variable <strong>name</strong> qui lui permet d'avoir un nom
 * et aussi d'une variable Basic <strong>parent</strong> qui permet de récupérer
 * son parent qui lui même aura un parent, on peut donc grâce à ce système avoir
 * accès à des objets facilement comme le principe d'une liste chaînée.
 * </p>
 * 
 *
 */
public class Basic {

	// Variables.

	/**
	 * Le nom du Basic.
	 */
	protected String name;

	/**
	 * Le parent du Basic.
	 */
	protected Basic parent;

	// Constructors.

	/**
	 * Initialise le nom à "" et le parent null.
	 */
	public Basic() {
		this.name = "";
		this.parent = null;
	}

	/**
	 * Donne un nom est un parent au Basic.
	 * 
	 * @param name
	 *            - le nom du Basic.
	 * @param parent
	 *            - le parent du Basic.
	 */
	public Basic(String name, Basic parent) {
		this.name = name;
		this.parent = parent;
	}

	// Public methods.

	@Override
	public String toString() {
		return "Basic [name=" + name + ", parent=" + ((parent == null) ? "NULL" : parent) + "]";
	}

	// Getters and Setters.

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parent
	 */
	public Basic getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Basic parent) {
		this.parent = parent;
	}

}
