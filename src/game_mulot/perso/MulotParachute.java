package game_mulot.perso;

import java.util.ArrayList;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;
import game_mulot.niveau.Niveau;

/**
 * <p>
 * Mulot qui ne meurt pas d'une très grosse chute.
 * </p>
 * 
 *
 */
public class MulotParachute extends Mulot {

	// Constructors.

	public MulotParachute() {
		super();
		this.name = "Parachute";
		this.direction = Mulot.MulotDirection.SOUTH;
		this.statue = Mulot.MulotStatue.ALIVE;
		this.setCurrentAlert(null);
		this.fallCounter = 0;
	}

	public MulotParachute(String name, int posX, int posY, Basic Parent) {
		super(name, posX, posY, Parent);

	}

	public MulotParachute(String name, int posX, int posY, MulotDirection direction, Basic Parent) {
		super(name, posX, posY, direction, Parent);

	}

	// Override methos.

	@Override
	protected void initializeListModelisation() {
		this.listModelisation = new ArrayList<BasicCellule>();
		this.listModelisation.add(new MulotCellule(this.getPosX(), this.getPosY(), null, new Character('P'), this));
	}

	@Override
	public void dealAlert(Enum<?> alert) {
		MulotCellule.MulotCelluleAlert alertMulot = ((MulotCellule.MulotCelluleAlert) alert);
		if (alertMulot == MulotCellule.MulotCelluleAlert.COLLISION) {
			if (this.getDirection() == Mulot.MulotDirection.NORTH) {
				this.setDirection(Mulot.MulotDirection.SOUTH);
			} else if (this.getDirection() == Mulot.MulotDirection.SOUTH) {
				// On ne meurt pas quand on tombe.
				this.setDirection(Mulot.MulotDirection.EAST);
				Mulot mulot = new Mulot(this.getName(), this.getPosX(), this.getPosY(), this.getDirection(), this.getParent());
				// On donne l'apparence.
				for (BasicCellule cellule : mulot.getListModelisation()) {
					cellule.setApparence(mulot.getName().charAt(0));
				}
				this.changeSpeciality(mulot);
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

}
