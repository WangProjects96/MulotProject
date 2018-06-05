package game_mulot.niveau;

import java.awt.Graphics;
import java.util.ArrayList;

import game_mulot.basic.Basic;
import game_mulot.basic.BasicCellule;
import game_mulot.basic.BasicComponent;
import game_mulot.perso.Mulot;
import game_mulot.perso.MulotGrimpeur;
import game_mulot.perso.MulotParachute;

/**
 * Le niveau 1.
 * 
 *
 */
public class Niveau1 extends Niveau {

	protected int numberMaxGrimpeur = 10;

	protected int numberMaxParachute = 10;

	// Constructors.

	public Niveau1() {
		super("Niveau 1", 10, 10, null, 1, 1, null);

		this.listComponent.remove(this.enter);
		this.listComponent.remove(this.exit);
		this.enter = new NiveauEnter(3, 0, this);
		this.exit = new NiveauExit(9, 8, this);
		this.listComponent.add(this.enter);
		this.listComponent.add(this.exit);

		this.refresh();
	}

	public Niveau1(ArrayList<BasicComponent> listComponent, Basic parent) {
		super("Niveau 1", 25, 75, listComponent, 10, 1, parent);

		this.listComponent.remove(this.enter);
		this.listComponent.remove(this.exit);
		this.enter = new NiveauEnter(10, 0, this);
		this.exit = new NiveauExit(69, 19, this);
		this.listComponent.add(this.enter);
		this.listComponent.add(this.exit);

		this.refresh();
	}

	// Inhereted methods.

	@Override
	public boolean isFinish() {
		// On vérifie si le nombre de mulots morts n'empêche pas de gagner.
		if (this.getNumberMaxMulot() - this.getNumberDeadMulot() >= this.getNumberMiniMulot()) {
			// Si tous les mulots sont sortie.
			if (this.getNumberSaveMulot() == this.getNumberMaxMulot()) {
				return true;
			} else if (this.getScore() >= this.getNumberMiniMulot() && this.getListMulot().isEmpty()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public void tic() {
		// On crée un nouveau mulot.
		this.generateMulot();

		// On fait avancé tout les mulots créés.
		for (Mulot mulot : this.getListMulot()) {
			mulot.advance(1);
		}

		// On vérifie si il n'y a pas eu d'alert.
		if (this.getCurrentAlert() != null) {
			this.dealAlert(this.getCurrentAlert());
		}

		// On rafraîchi le niveau.
		this.refresh();

	}

	@Override
	protected void generateMulot() {
		// On vérifie si on à pas généré le nombre maximum de mulot.
		if (this.getNumberGenerationMulot() < this.getNumberMaxMulot()) {
			Mulot m = new Mulot(String.valueOf(this.getNumberGenerationMulot()), this.getEnter().getPosX(),
					this.getEnter().getPosY(), this);
			for (BasicCellule cellule : m.getListModelisation()) {
				cellule.setApparence(m.getName().charAt(0));
			}
			this.listMulot.add(m);

			this.numberGenerationMulot++;
		}
	}

	@Override
	public void refresh() {
		// On réinitialise la matrice.
		this.initializeMatrix();

		// On nettoie la liste de Mulot (tous les morts sont remove.
		for (int i = 0; i < this.getListMulot().size(); i++) {
			if (this.getListMulot().get(i).getStatue() == Mulot.MulotStatue.DEAD) {
				this.getListMulot().remove(this.getListMulot().get(i));
			}
		}

		// On met les composants avec leur nouvelle place.
		this.includeComponentInMatrix();
	}

	@Override
	public boolean win() {
		if (this.getScore() >= this.getNumberMiniMulot()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Cherche dans la liste des mulot un mulot ayant pour numéros le numéros
	 * indiqué. Si le mulot n'est pas trouvé dans la liste, la méthod return
	 * null.
	 */
	@Override
	protected Mulot mulotAt(char numberMulot) {
		Mulot m = null;
		// On cherche le mulot dans la liste de mulot.
		for (int i = 0; i < this.getListMulot().size(); i++) {
			boolean escape = false;
			// Si le mulot a bien le numéro que l'on cherche.
			if (this.getListMulot().get(i).getName().charAt(0) == numberMulot) {
				m = this.getListMulot().get(i);
				escape = true;
			}
			if (escape)
				break;
		}

		return m;
	}

	@Override
	public void changeSpecialityMulot(char numberMulot, char speciality) {
		switch (speciality) {
		case 'g':
			Mulot m1 = this.mulotAt(numberMulot);
			
			// On change le mulot.
			if (this.numberMaxGrimpeur > 0 && m1 != null) {
				MulotGrimpeur mg = new MulotGrimpeur(m1.getName(), m1.getPosX(), m1.getPosY(), m1.getDirection(), m1.getParent());
				for (BasicCellule cellule : mg.getListModelisation()) {
					cellule.setApparence(m1.getName().charAt(0));
				}
				m1.changeSpeciality(mg);
				this.numberMaxGrimpeur--;
			}
			break;
		case 'p':
			Mulot m2 = this.mulotAt(numberMulot);

			// On change le mulot.
			if (this.numberMaxParachute > 0 && m2 != null) {
				MulotParachute mp = new MulotParachute(m2.getName(), m2.getPosX(), m2.getPosY(), m2.getDirection(), m2.getParent());
				for (BasicCellule cellule : mp.getListModelisation()) {
					cellule.setApparence(m2.getName().charAt(0));
				}
				m2.changeSpeciality(mp);
				this.numberMaxParachute--;
			}
			break;
		default:
			System.out.println("Tu t'es trompé de lettre");
			break;
		}
	}

	@Override
	public void draw(Graphics g) {

	}

	@Override
	public void draw() {
		this.includeComponentInMatrix();
		for (int i = 0; i < this.getLines(); i++) {
			for (int j = 0; j < this.getColumns(); j++) {
				this.matrix[i * this.getColumns() + j].draw();
			}
			System.out.println();
		}

		System.out.println("G : " + this.numberMaxGrimpeur + "\tP : " + this.numberMaxParachute);
	}

	// Getters and Setters.

	/**
	 * @return the numberMaxGrimpeur
	 */
	public int getNumberMaxGrimpeur() {
		return numberMaxGrimpeur;
	}

	/**
	 * @param numberMaxGrimpeur
	 *            the numberMaxGrimpeur to set
	 */
	public void setNumberMaxGrimpeur(int numberMaxGrimpeur) {
		this.numberMaxGrimpeur = numberMaxGrimpeur;
	}

	/**
	 * @return the numberMaxParachute
	 */
	public int getNumberMaxParachute() {
		return numberMaxParachute;
	}

	/**
	 * @param numberMaxParachute
	 *            the numberMaxParachute to set
	 */
	public void setNumberMaxParachute(int numberMaxParachute) {
		this.numberMaxParachute = numberMaxParachute;
	}

}
