package game_mulot.perso;

import java.awt.Graphics;
import java.util.ArrayList;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;
import game_mulot.interfaces.AlertReceiver;
import game_mulot.interfaces.AlertSender;
import game_mulot.niveau.Niveau;
import game_mulot.niveau.VoidCellule;

/**
 * <p>
 * La class qui représente le mulot.
 * </p>
 * <p>
 * <strong>Précision :</strong>Il faut prendre en compte qu'ici les Y
 * représentent les lignes et que les X représentent les colonnes
 * </p>
 *
 * 
 *
 */
public class Mulot extends Personage implements AlertReceiver, AlertSender {

	// Variables.

	/**
	 * La direction dans laquelle se dirige le mulot.
	 */
	protected Mulot.MulotDirection direction;

	/**
	 * Le statue du mulot, mort ou vivant.
	 */
	protected Mulot.MulotStatue statue;

	/**
	 * Alerte courante toujours null sauf quand il y a une alert.
	 */
	protected MulotCellule.MulotCelluleAlert currentAlert;

	/**
	 * Compteur pour compter le nombre de cases de chute.
	 */
	protected int fallCounter;

	// Constructors.

	public Mulot() {
		super();
		this.name = "Mulot";
		this.direction = Mulot.MulotDirection.SOUTH; // On tombe de l'entrée.
		this.statue = Mulot.MulotStatue.ALIVE;
		this.setCurrentAlert(null);
		this.fallCounter = 0;
	}

	public Mulot(String name, int posX, int posY, Basic Parent) {
		super(name, posX, posY, Parent);
		this.direction = Mulot.MulotDirection.SOUTH; // On tombe de l'entrée.
		this.statue = Mulot.MulotStatue.ALIVE;
		this.setCurrentAlert(null);
		this.fallCounter = 0;
	}

	public Mulot(String name, int posX, int posY, Mulot.MulotDirection direction, Basic Parent) {
		super(name, posX, posY, Parent);
		this.direction = direction;

		if (this.direction == null) {
			this.direction = Mulot.MulotDirection.SOUTH;
		}

		this.statue = Mulot.MulotStatue.ALIVE;
		this.setCurrentAlert(null);
		this.fallCounter = 0;
	}

	// Inherited methods.

	@Override
	public void receivedAlert(Enum<?> alert) {
		if (this.getListModelisation().contains((MulotCellule) ((MulotCellule.MulotCelluleAlert) alert).getSender())) {
			this.setCurrentAlert((MulotCellule.MulotCelluleAlert) alert);
		}
	}

	@Override
	public void dealAlert(Enum<?> alert) {
		MulotCellule.MulotCelluleAlert alertMulot = ((MulotCellule.MulotCelluleAlert) alert);
		if (alertMulot == MulotCellule.MulotCelluleAlert.COLLISION) {
			if (this.getDirection() == Mulot.MulotDirection.NORTH) {
				this.setDirection(Mulot.MulotDirection.SOUTH);
			} else if (this.getDirection() == Mulot.MulotDirection.SOUTH) {
				if (this.getFallCounter() >= 10) {
					this.setStatue(Mulot.MulotStatue.DEAD);
				} else {
					this.setDirection(Mulot.MulotDirection.EAST);
					this.fallCounter = 0;
				}
			} else if (this.getDirection() == Mulot.MulotDirection.EAST) {
				this.setDirection(Mulot.MulotDirection.WEST);
			} else if (this.getDirection() == Mulot.MulotDirection.WEST) {
				this.setDirection(Mulot.MulotDirection.EAST);
			}
		} else if (alertMulot == MulotCellule.MulotCelluleAlert.ARRIVED) {
			((Niveau) this.getParent()).setNumberSaveMulot(((Niveau) this.getParent()).getNumberSaveMulot() + 1);
			((Niveau) this.getParent()).setScore(((Niveau) this.getParent()).getScore() + 1);
			// On le ren dmort pour qu'il n'avance plus.
			this.statue = Mulot.MulotStatue.DEAD;
		} else if (alertMulot == MulotCellule.MulotCelluleAlert.OUT_OF_MAP) {
			this.setStatue(Mulot.MulotStatue.DEAD);
		}

		// On remet l'alerte courante à null.
		this.currentAlert = null;
	}

	@Override
	public void sendAlert(Enum<?> alert) {
		((Mulot.MulotAlert) alert).setSender(this);
		((AlertReceiver) this.getParent()).receivedAlert(alert);
	}

	@Override
	public void sendAlert(Enum<?> alert, AlertReceiver receiver) {
		((Mulot.MulotAlert) alert).setSender(this);
		receiver.receivedAlert(alert);
	}

	/**
	 * Déplace le mulot vers le haut.
	 */
	@Override
	public void moveUp(int deplacement) {
		this.setPosY(this.getPosY() - deplacement);

		// Si il y a eu une alerte, l'objet n'a pas bouger et on traite alors
		// l'alerte.
		if (this.getCurrentAlert() != null) {
			// On traite l'alerte.
			this.dealAlert(this.getCurrentAlert());
		}
	}

	/**
	 * Déplace le mulot vers le bas.
	 */
	@Override
	public void moveDown(int deplacement) {
		this.setPosY(this.getPosY() + deplacement);

		// Si il y a eu une alerte, l'objet n'a pas bouger et on traite alors
		// l'alerte.
		if (this.getCurrentAlert() != null) {
			// On traite l'alerte.
			this.dealAlert(this.getCurrentAlert());
		} else {
			// On ajoute le deplacement au counter de chute car on tombe.
			this.fallCounter += deplacement;
		}
	}

	/**
	 * Déplace le mulot vers la droite.
	 */
	@Override
	public void moveRight(int deplacement) {
		this.setPosX(this.getPosX() + deplacement);

		// Si il y a eu une alerte, l'objet n'a pas bouger et on traite alors
		// l'alerte.
		if (this.getCurrentAlert() != null) {
			// On traite l'alerte.
			this.dealAlert(this.getCurrentAlert());
		}
	}

	/**
	 * Déplace le mulot vers la gauche.
	 */
	@Override
	public void moveLeft(int deplacement) {
		this.setPosX(this.getPosX() - deplacement);

		// Si il y a eu une alerte, l'objet n'a pas bouger et on traite alors
		// l'alerte.
		if (this.getCurrentAlert() != null) {
			// On traite l'alerte.
			this.dealAlert(this.getCurrentAlert());
		}
	}

	@Override
	protected void initializeListModelisation() {
		this.listModelisation = new ArrayList<BasicCellule>();
		this.listModelisation.add(new MulotCellule(this.getPosX(), this.getPosY(), this));
	}

	@Override
	public void draw(Graphics g) {
		if (this.getListModelisation() != null) {
			for (BasicCellule cellule : this.getListModelisation()) {
				cellule.draw(g);
			}
		}
	}

	@Override
	public void draw() {
		if (this.getListModelisation() != null) {
			for (BasicCellule cellule : this.getListModelisation()) {
				cellule.draw();
			}
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

	// Public methods.

	/**
	 * Déplace le mulot d'autant de case que de déplacement entré en paramètre
	 * dans la direction qu'il possède actuellement.
	 * 
	 * @param deplacement
	 *            - le nombre de déplacement du mulot.
	 */
	public void advance(int deplacement) {
		// On vérifie l'état du mulot.
		if (this.getStatue() != Mulot.MulotStatue.DEAD) {
			// On vérifie si il y a un sol en dessous du mulot.
			try {
				BasicCellule celluleDessous = ((Niveau) this.getParent())
						.getMatrix()[(this.getListModelisation().get(0).getPosY() + 1)
								* ((Niveau) this.getParent()).getColumns()
								+ (this.getListModelisation().get(0).getPosX())];
				if (celluleDessous.getClass() == VoidCellule.class || celluleDessous.getClass() == MulotCellule.class) {
					this.setDirection(Mulot.MulotDirection.SOUTH);
				}
			}
			// Si on cherche endehors de la map
			catch (ArrayIndexOutOfBoundsException e) {

			}
			// On fait bouger dans tous les cas le mulot.
			finally {

				if (this.getDirection() == Mulot.MulotDirection.NORTH) {
					this.moveUp(deplacement);
				} else if (this.getDirection() == Mulot.MulotDirection.SOUTH) {
					this.moveDown(deplacement);
				} else if (this.getDirection() == Mulot.MulotDirection.EAST) {
					this.moveRight(deplacement);
				} else if (this.getDirection() == Mulot.MulotDirection.WEST) {
					this.moveLeft(deplacement);
				}
			}
		}
	}

	/**
	 * Change la spécialité du mulot. Envoie un ealerte au niveau dans lequel on
	 * est pour qu'il puisse changer la spécialité du mulot.
	 * 
	 * @param newSpecility
	 *            - la nouvelle spécialité du mulot.
	 */
	public void changeSpeciality(Mulot newSpeciality) {
		// On vérifie si le mulot est bien dans un niveau
		if (this.getParent() != null) {
			Mulot.MulotAlert alert = Mulot.MulotAlert.CHANGE_SPECIALITY;
			alert.setSender(this);
			alert.setNewSpeciality(newSpeciality);
			((Niveau) this.getParent()).receivedAlert(alert);
		}
	}

	// Getters and Setters.

	/**
	 * @return the direction
	 */
	public Mulot.MulotDirection getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(Mulot.MulotDirection direction) {
		this.direction = direction;
	}

	/**
	 * @return the statue
	 */
	public Mulot.MulotStatue getStatue() {
		return statue;
	}

	/**
	 * @param statue
	 *            the statue to set
	 */
	public void setStatue(Mulot.MulotStatue statue) {
		this.statue = statue;
		// A CHANGER EN FAISANT IMPLEMENTER LES OBJECTS BASIC AVEC LES INTERFACE
		// SERNDER ET RECEIVER ALERT!!!
		if (this.statue == Mulot.MulotStatue.DEAD) {
			// On le supprime de la liste.
			((Niveau) this.parent).setNumberDeadMulot(((Niveau) this.parent).getNumberDeadMulot() + 1);
		}
	}

	/**
	 * @return the currentAlert
	 */
	public MulotCellule.MulotCelluleAlert getCurrentAlert() {
		return currentAlert;
	}

	/**
	 * @param currentAlert
	 *            the currentAlert to set
	 */
	public void setCurrentAlert(MulotCellule.MulotCelluleAlert currentAlert) {
		this.currentAlert = currentAlert;
	}

	/**
	 * @return the fallCounter
	 */
	public int getFallCounter() {
		return fallCounter;
	}

	// Enumerations.

	/**
	 * <p>
	 * Enumeration qui contient toute les direction dans laquelle un Mulot peut
	 * se diriger.
	 * </p>
	 * 
	 * <p>
	 * <strong>Liste des directions :</br>
	 * </strong>
	 * <ul>
	 * <li>NORTH</li>
	 * <li>SOUTH</li>
	 * <li>EAST</li>
	 * <li>WEST</li>
	 * </ul>
	 * </p>
	 * 
	 * 
	 * @author Calli
	 *
	 */
	public enum MulotDirection {
		NORTH("NORTH"), // La direction NORD (haut).
		SOUTH("SOUTH"), // La direction SUD (bas).
		EAST("EAST"), // La directtion EST (droite).
		WEST("WEST"); // La direction OUEST (gauche).

		private String text = "";

		MulotDirection(String text) {
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

	public enum MulotStatue {
		DEAD("DEAD"), // Statue mort.
		ALIVE("ALIVE"); // Statue vivant.

		private String text = "";

		MulotStatue(String text) {
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

	public enum MulotAlert {
		CHANGE_SPECIALITY("CHANGE_SPECIALITY");

		private String text = "";

		private Object sender = null;

		private Mulot newSpeciality = null;

		MulotAlert(String text) {
			this.setText(text);
			this.sender = null;
			this.newSpeciality = null;
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

		/**
		 * @return the sender
		 */
		public Object getSender() {
			return sender;
		}

		/**
		 * @param sender
		 *            the sender to set
		 */
		public void setSender(Object sender) {
			this.sender = sender;
		}

		/**
		 * @return the newSpeciality
		 */
		public Mulot getNewSpeciality() {
			return newSpeciality;
		}

		/**
		 * @param newSpeciality
		 *            the newSpeciality to set
		 */
		public void setNewSpeciality(Mulot newSpeciality) {
			this.newSpeciality = newSpeciality;
		}
	}

}
