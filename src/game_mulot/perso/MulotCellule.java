package game_mulot.perso;

import java.awt.Color;
import java.awt.Graphics;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;
import game_mulot.basic.BasicMatrix;
import game_mulot.interfaces.AlertReceiver;
import game_mulot.interfaces.AlertSender;
import game_mulot.niveau.CelluleExit;
import game_mulot.niveau.Niveau;
import game_mulot.niveau.VoidCellule;

/**
 * Clas qui représente une cellule de mulot.
 * 
 *
 */
public class MulotCellule extends BasicCellule implements AlertSender {

	/**
	 * On initialise avec le constructor de la class mère et on met l'apparence
	 * à un Character('M'). La cellule les n'est pas traversable.
	 */
	public MulotCellule() {
		super();
		this.setApparence(new Character('M'));
	}

	/**
	 * Initialise avec un parent, une position X et une position Y et donne pour
	 * apparence un Character('M'). La cellule n'est pas traversable.
	 * 
	 * @param posX
	 *            - la position X de la cellule.
	 * @param posY
	 *            - la position Y de la cellule.
	 * @param parent
	 *            - le parent de la cellule.
	 */
	public MulotCellule(int posX, int posY, Basic parent) {
		super(posX, posY, false, parent);
		this.setCrossable(false);
		this.setParent(parent);
		this.setApparence(new Character('M'));
	}

	public MulotCellule(int posX, int posY, Color color, Object apparence, Basic parent) {
		super(posX, posY, false, color, apparence, parent);
	}

	// Abstract methods.

	@Override
	public void sendAlert(Enum<?> alert) {
		((MulotCellule.MulotCelluleAlert) alert).setSender(this);
		((AlertReceiver) this.getParent()).receivedAlert(alert);
	}

	@Override
	public void sendAlert(Enum<?> alert, AlertReceiver receiver) {
		((MulotCellule.MulotCelluleAlert) alert).setSender(this);
		receiver.receivedAlert(alert);
	}

	@Override
	public void draw(Graphics g) {
		// Non utiliser.
	}

	@Override
	public void draw() {
		System.out.print(this.getApparence());
	}

	@Override
	public boolean verifyPosition(int posX, int posY) {
		// On vérifie si notre parent n'est pas null.
		if (this.getParent() != null) {
			// On vérifie ensuite si on est bien dans une matrice.
			if (this.getParent().getParent() != null) {
				BasicMatrix matrix = (BasicMatrix) this.getParent().getParent();
				// On vérifie alors si on est bien dans la map.
				if ((posX >= 0 && posX < matrix.getColumns())
						&& (posY >= 0 && posY < matrix.getLines())) {
					Niveau niveau = (Niveau) matrix;
					// La cellule où l'on veut aller.
					BasicCellule cellule = niveau.getMatrix()[posY * niveau.getColumns() + posX];
					
					if (cellule.getClass() == CelluleExit.class) {
						this.sendAlert(MulotCellule.MulotCelluleAlert.ARRIVED);
						return true;
						// On vérifie si la cellule n'est pas une cellule vide
					} else if (cellule.getClass() == VoidCellule.class) {
						return true;
						// On vérifie si il y n'y pas collision.
					} else if (cellule.isCrossable()) {
						return true;
						// On vérifie si la cellule n'est pas une cellule d'un
						// autre mulot. Les mulots peuvent être sur la meme
						// case.
					} else if (cellule.getClass() == MulotCellule.class) {
						return true;
						// Sinon on ne peut pas bouger, il y a une collision.
					} else {
						this.sendAlert(MulotCellule.MulotCelluleAlert.COLLISION);
						return false;
					}
				} else {
					this.sendAlert(MulotCellule.MulotCelluleAlert.OUT_OF_MAP);
					return false;
				}
			} else {
				// Ici c'est lorsque l'on créer le Mulot ou la map.
				return true;
			}
		} else {
			// ici c'est une cellule quelconque sans parent sans rien donc comme
			// si c'etait le constructeur pas défault.
			return true;
		}
	}

	// Enumerations.

	public enum MulotCelluleAlert {
		COLLISION("COLLISION"), // Alerte lorsque la cellule entre en collision.
		OUT_OF_MAP("OUT_OF_MAP"), // Alerte lorsque le cellule est hors de la
									// map.
		ARRIVED("ARRIVED"); // Alerte lorsque la cellule est sur la case
							// arrivée.

		private String text = "";
		private Object sender;

		MulotCelluleAlert(String text) {
			this.setText(text);
			this.setSender(null);
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
	}

}
