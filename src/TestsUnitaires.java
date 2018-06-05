import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import game_mulot.basic.BasicComponent;
import game_mulot.decors.Socle;
import game_mulot.niveau.Niveau1;

public class TestsUnitaires {

	@Test
	public void test() {

		ArrayList<BasicComponent> list1 = new ArrayList<BasicComponent>();
		list1.add(new Socle(55, 20, 15, Socle.SocleOrientation.HORIZONTAL, null));

		Niveau1 n1 = new Niveau1(list1, null);

		n1.draw();
		n1.tic(); // Ajoute mulot

		assertEquals(10, n1.getEnter().getPosX());
		assertEquals(0, n1.getEnter().getPosY());
		assertEquals(69, n1.getExit().getPosX());
		assertEquals(19, n1.getExit().getPosY());
		assertNotNull("La liste de mulot n'est pas nul", n1.getListMulot());
	}

}
