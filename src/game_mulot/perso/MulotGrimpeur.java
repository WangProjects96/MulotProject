package game_mulot.perso;

import java.util.ArrayList;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;
import game_mulot.niveau.Niveau;
import game_mulot.niveau.VoidCellule;

/**
 * <p>
 * Le mulot grimpeur qui sait grimper aux murs.
 * </p>
 * 
 *
 */
public class MulotGrimpeur extends Mulot {

	// Constructors.

	public MulotGrimpeur() {
		super();
	}

	public MulotGrimpeur(String name, int posX, int posY, Basic Parent) {
		super(name, posX, posY, Parent);
	}

	public MulotGrimpeur(String name, int posX, int posY, Mulot.MulotDirection direction, Basic parent) {
		super(name, posX, posY, direction, parent);
	}

	// Override methods

	@Override
	protected void initializeListModelisation() {
		this.listModelisation = new ArrayList<BasicCellule>();
		this.listModelisation.add(new MulotCellule(this.getPosX(), this.getPosY(), null, new Character('G'), this));
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
				this.setDirection(Mulot.MulotDirection.NORTH);
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
	public void advance(int deplacement) {
		// On vérifie l'état du mulot.
		if (this.getStatue() != Mulot.MulotStatue.DEAD) {
			try {
				// On vérifie si il y a un sol en dessous du mulot et que le
				// mulot
				// ne soit pas en train de grimper.
				if (this.getDirection() != Mulot.MulotDirection.NORTH) {
					BasicCellule celluleDessous = ((Niveau) this.getParent())
							.getMatrix()[(this.getListModelisation().get(0).getPosY() + 1)
									* ((Niveau) this.getParent()).getColumns()
									+ (this.getListModelisation().get(0).getPosX())];
					if (celluleDessous.getClass() == VoidCellule.class
							|| celluleDessous.getClass() == MulotCellule.class) {
						this.setDirection(Mulot.MulotDirection.SOUTH);
					}
				}
				// On vérifie si le mulot n'est pas arriver au bout du mur.
				else {
					BasicCellule celluleDroite = ((Niveau) this.getParent())
							.getMatrix()[this.getListModelisation().get(0).getPosY()
									* ((Niveau) this.getParent()).getColumns()
									+ (this.getListModelisation().get(0).getPosX() + 1)];
					if (celluleDroite.getClass() == VoidCellule.class
							|| celluleDroite.getClass() == MulotCellule.class) {
						this.setDirection(Mulot.MulotDirection.EAST);
						// On fait bouger le mulot à la prochaine place car on
						// lui changera la spécialité que aprè avoir fait bouger
						// le grimpeur vers la droite.
						Mulot mulot = new Mulot(this.getName(), this.getPosX() + deplacement, this.getPosY(),
								Mulot.MulotDirection.EAST, this.getParent());
						// On donne l'apparence.
						for (BasicCellule cellule : mulot.getListModelisation()) {
							cellule.setApparence(mulot.getName().charAt(0));
						}
						this.changeSpeciality(mulot);
					}
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

}
