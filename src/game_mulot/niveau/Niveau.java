package game_mulot.niveau;

import java.util.ArrayList;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;
import game_mulot.basic.BasicMatrix;
import game_mulot.basic.BasicComponent;
import game_mulot.interfaces.AlertReceiver;
import game_mulot.perso.Mulot;

/**
 * Class qui repr�sente un niveau du jeu.
 * 
 *
 */
public abstract class Niveau extends BasicMatrix implements AlertReceiver {

	// Variables.

	/**
	 * L'entr�e du niveau.
	 */
	protected NiveauEnter enter;

	/**
	 * La sortie du niveau.
	 */
	protected NiveauExit exit;

	/**
	 * Le score du joueur.
	 */
	protected int score;

	/**
	 * Nombre de mulots qui le niveau doit cr�er.
	 */
	protected int numberMaxMulot;

	/**
	 * Nombre minimum de mulots que le joueurs doit faire sortir.
	 */
	protected int numberMiniMulot;

	/**
	 * La liste de composants qui vont �tre inclus dans la matrice.
	 */
	protected ArrayList<BasicComponent> listComponent;

	/**
	 * <p>
	 * Nombre de fois o� le niveau a g�n�r� un mulot. Il augmente � chaque appel
	 * de la fonction <strong>generateMulot</strong>
	 * </p>
	 */
	protected int numberGenerationMulot;

	/**
	 * Nombre de mulots morts.
	 */
	protected int numberDeadMulot;

	/**
	 * Nombre de mulots sauv�.
	 */
	protected int numberSaveMulot;

	/**
	 * La liste des Mulots.
	 */
	protected ArrayList<Mulot> listMulot;

	protected Enum<?> currentAlert;

	// Constructors.

	public Niveau() {
		super();
		this.enter = new NiveauEnter(0, 0, this);
		this.exit = new NiveauExit(this.getColumns() - 1, this.getLines() - 1, this);
		this.score = 0;
		this.numberMaxMulot = 1;
		this.numberMiniMulot = 1;
		this.numberGenerationMulot = 0;
		this.numberDeadMulot = 0;
		this.numberSaveMulot = 0;
		this.currentAlert = null;
		this.listMulot = new ArrayList<Mulot>();

		this.listComponent = new ArrayList<BasicComponent>();
		this.listComponent.add(this.enter);
		this.listComponent.add(this.exit);
		this.includeComponentInMatrix();
	}

	@SuppressWarnings("unchecked")
	public Niveau(String name, int lines, int columns, ArrayList<BasicComponent> listComponent, int nombreMaxMulot,
			int nombreMiniMulot, Basic parent) {
		super(name, lines, columns, listComponent, parent);
		this.enter = new NiveauEnter(0, 0, this);
		this.exit = new NiveauExit(this.getColumns() - 1, this.getLines() - 1, this);
		this.score = 0;
		this.numberMaxMulot = nombreMaxMulot;
		this.numberMiniMulot = nombreMiniMulot;
		this.numberGenerationMulot = 0;
		this.numberDeadMulot = 0;
		this.numberSaveMulot = 0;
		this.currentAlert = null;
		this.listMulot = new ArrayList<Mulot>();

		this.listComponent = new ArrayList<BasicComponent>();
		// On donne � tous les composants comme parent le niveau.
		if (listComponent != null) {
			this.listComponent = (ArrayList<BasicComponent>) listComponent.clone();
			for (BasicComponent component : this.getListComponent()) {
				component.setParent(this);
			}
		}

		this.listComponent.add(this.enter);
		this.listComponent.add(this.exit);
		this.includeComponentInMatrix();
	}

	// Abstract methods.

	/**
	 * 
	 * @return true si le niveau est fini sinon false;
	 */
	public abstract boolean isFinish();

	/**
	 * Fait tourner le jeu.
	 */
	public abstract void tic();

	/**
	 * G�n�re un Mulot.
	 */
	protected abstract void generateMulot();

	/**
	 * Change la matrice avec les nouveaux changements.
	 */
	public abstract void refresh();

	/**
	 * Permet de v�rifier si le niveau est gagner ou non.
	 * 
	 * @return true si le niveau est gagner sinon false.
	 */
	public abstract boolean win();

	/**
	 * Change le mulot qui a le num�ro entr� en param�tre en la sp�cialit� entr�
	 * en param�tre.
	 * 
	 * @param numberMulot
	 *            - le numeros en caract�re du mulot.
	 * @param speciality
	 *            - la sp�cialit� en carat�re que le mulot doit prendre.
	 */
	public abstract void changeSpecialityMulot(char numberMulot, char speciality);

	/**
	 * Permet de chercher un mulot qui porte le num�ro entr� en param�tre.
	 * 
	 * @param numberMulot
	 *            - le num�ro en caract�re du mulot.
	 * 
	 * @return le mulot qui porte le num�ro entr� en param�tre.
	 */
	protected abstract Mulot mulotAt(char numberMulot);

	// Inherited methods.

	@Override
	public void receivedAlert(Enum<?> alert) {
		if (this.getListMulot().contains((Mulot) ((Mulot.MulotAlert) alert).getSender())) {
			this.setCurrentAlert((Mulot.MulotAlert) alert);
		}
	}
	
	@Override
	public void dealAlert(Enum<?> alert) {
		Mulot.MulotAlert mulotAlert = (Mulot.MulotAlert) alert;
		
		if (mulotAlert == Mulot.MulotAlert.CHANGE_SPECIALITY) {
			// On change le mulot de sp�cialit�.
			this.listMulot.set(this.listMulot.indexOf(mulotAlert.getSender()), mulotAlert.getNewSpeciality());
		}
		
		// On remet l'alert � null.
		this.setCurrentAlert(null);
	}

	@Override
	protected void initializeMatrix() {
		this.matrix = new BasicCellule[this.getLines() * this.getColumns()];
		for (int i = 0; i < this.getLines(); i++) { // Parcours des lignes.
			for (int j = 0; j < this.getColumns(); j++) { // Parcours des
															// colonnes.
				this.matrix[i * this.getColumns() + j] = new VoidCellule(j, i, this);
			}
		}
	}

	@Override
	protected void includeComponentInMatrix() {
		// On met d'abord les composants.
		for (BasicComponent component : this.getListComponent()) {
			for (BasicCellule componentCellule : component.getListModelisation()) {
				// On remplace la cellule vide par la cellule du composant.
				this.matrix[componentCellule.getPosY() * this.getColumns()
						+ componentCellule.getPosX()] = componentCellule;
			}
		}

		// On met ensuite les mulots.
		for (BasicComponent component : this.getListMulot()) {
			for (BasicCellule componentCellule : component.getListModelisation()) {
				// On remplace la cellule vide par la cellule du composant.
				this.matrix[componentCellule.getPosY() * this.getColumns()
						+ componentCellule.getPosX()] = componentCellule;
			}
		}
	}

	// Public methods.

	@Override
	public String toString() {
		return "Niveau [" + "score=" + score + ", nombreMaxMulot=" + numberMaxMulot + ", nombreMiniMulot="
				+ numberMiniMulot + ", numberGenerationMulot=" + numberGenerationMulot + ", numberDeadMulot="
				+ numberDeadMulot + ", numberSaveMulot=" + numberSaveMulot + ", listMulot=" + listMulot + "]";
	}

	/**
	 * Ajouter un composant dans la liste des composants de la matrice.
	 * 
	 * @param component
	 *            - le composant � aujouter.
	 */
	public void addComponent(BasicComponent component) {
		component.setParent(this);
		this.listComponent.add(component);
	}

	/**
	 * Supprime un composant dans la liste des composants de la matrice.
	 * 
	 * @param component
	 *            - le composant � supprimer.
	 */
	public void removeComponent(BasicComponent component) {
		if (component.getParent() == this)
			component.setParent(null);
		this.listComponent.remove(component);
	}

	// Getters and Setters.

	/**
	 * @return the enter
	 */
	public NiveauEnter getEnter() {
		return enter;
	}

	/**
	 * @param enter
	 *            the enter to set
	 */
	public void setEnter(NiveauEnter enter) {
		this.enter = enter;
		if (this.enter != null)
			this.enter.setParent(this);
		else
			this.enter = new NiveauEnter(this.getColumns() - 1, this.getLines() - 1, this);
	}

	/**
	 * @return the exit
	 */
	public NiveauExit getExit() {
		return exit;
	}

	/**
	 * @param exit
	 *            the exit to set
	 */
	public void setExit(NiveauExit exit) {
		this.exit = exit;
		if (this.exit != null)
			this.exit.setParent(this);
		else
			this.exit = new NiveauExit(this.getColumns() - 1, this.getLines() - 1, this);
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the nombreMaxMulot
	 */
	public int getNumberMaxMulot() {
		return numberMaxMulot;
	}

	/**
	 * @return the nombreMiniMulot
	 */
	public int getNumberMiniMulot() {
		return numberMiniMulot;
	}

	/**
	 * @return the listMulot
	 */
	public ArrayList<Mulot> getListMulot() {
		return listMulot;
	}

	/**
	 * @return the numberGenerationMulot
	 */
	public int getNumberGenerationMulot() {
		return numberGenerationMulot;
	}

	/**
	 * @return the numberDeadMulot
	 */
	public int getNumberDeadMulot() {
		return numberDeadMulot;
	}

	/**
	 * @param numberDeadMulot
	 *            the numberDeadMulot to set
	 */
	public void setNumberDeadMulot(int numberDeadMulot) {
		this.numberDeadMulot = numberDeadMulot;
	}

	/**
	 * @return the numberSaveMulot
	 */
	public int getNumberSaveMulot() {
		return numberSaveMulot;
	}

	/**
	 * @param numberSaveMulot
	 *            the numberSaveMulot to set
	 */
	public void setNumberSaveMulot(int numberSaveMulot) {
		this.numberSaveMulot = numberSaveMulot;
	}

	/**
	 * @return the currentAlert
	 */
	public Enum<?> getCurrentAlert() {
		return currentAlert;
	}

	/**
	 * @param currentAlert
	 *            the currentAlert to set
	 */
	public void setCurrentAlert(Enum<?> currentAlert) {
		this.currentAlert = currentAlert;
	}

	/**
	 * @return the listComponent
	 */
	public ArrayList<BasicComponent> getListComponent() {
		return listComponent;
	}

}
