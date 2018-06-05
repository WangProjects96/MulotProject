package game_mulot.main;

import java.util.ArrayList;
import java.util.Scanner;

import game_mulot.basic.BasicComponent;
import game_mulot.basic.Outils;
import game_mulot.decors.Socle;
import game_mulot.niveau.Niveau1;

public class Main {

	public static void main(String[] args) {

		ArrayList<BasicComponent> list1 = new ArrayList<BasicComponent>();
//		list1.add(new Socle(0, 0, 24, Socle.SocleOrientation.VERTICAL, null));
//		list1.add(new Socle(74, 0, 24, Socle.SocleOrientation.VERTICAL, null));
//		list1.add(new Socle(3, 3, 15, Socle.SocleOrientation.HORIZONTAL, null));
//		list1.add(new Socle(5, 20, 20, Socle.SocleOrientation.HORIZONTAL, null));
//		list1.add(new Socle(25, 17, 3, Socle.SocleOrientation.VERTICAL, null));
//		list1.add(new Socle(25, 16, 5, Socle.SocleOrientation.HORIZONTAL, null));
//		list1.add(new Socle(30, 13, 3, Socle.SocleOrientation.VERTICAL, null));
//		list1.add(new Socle(30, 12, 5, Socle.SocleOrientation.HORIZONTAL, null));
//		list1.add(new Socle(34, 13, 3, Socle.SocleOrientation.VERTICAL, null));
//		list1.add(new Socle(35, 16, 5, Socle.SocleOrientation.HORIZONTAL, null));
//		list1.add(new Socle(35, 20, 10, Socle.SocleOrientation.HORIZONTAL, null));
//		list1.add(new Socle(45, 18, 2, Socle.SocleOrientation.VERTICAL, null));
//		list1.add(new Socle(45, 17, 6, Socle.SocleOrientation.HORIZONTAL, null));
//		list1.add(new Socle(50, 13, 4, Socle.SocleOrientation.VERTICAL, null));
//		list1.add(new Socle(50, 12, 10, Socle.SocleOrientation.HORIZONTAL, null));
//		list1.add(new Socle(55, 20, 15, Socle.SocleOrientation.HORIZONTAL, null));
		
		list1.add(new Socle(0, 0, 24, Socle.SocleOrientation.VERTICAL, null));
		list1.add(new Socle(74, 0, 24, Socle.SocleOrientation.VERTICAL, null));
		list1.add(new Socle(3, 3, 15, Socle.SocleOrientation.HORIZONTAL, null));
		list1.add(new Socle(5, 20, 20, Socle.SocleOrientation.HORIZONTAL, null));
		list1.add(new Socle(25, 14, 6, Socle.SocleOrientation.VERTICAL, null));
		list1.add(new Socle(25, 14, 15, Socle.SocleOrientation.HORIZONTAL, null));
		list1.add(new Socle(35, 20, 35, Socle.SocleOrientation.HORIZONTAL, null));
		

		Niveau1 n1 = new Niveau1(list1, null);
 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		do {
			n1.tic();
			n1.draw();

			if (Outils.clavier()) {
				String s = sc.next();

				
				if (s.length() >= 2){
					n1.changeSpecialityMulot(s.charAt(0), s.charAt(1));
				}else 
					System.out.println("il faut une lettre et un chiffre");
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while (!n1.isFinish());
		
		n1.draw();
		n1.tic();
		

		if (n1.win()) {
			System.out.println("GAGNE!!!!!");
		} else {
			System.out.println("PERDU OUUUUUUUUUUUUUUUUUUUUUUUUUUUU!!!");
		}

		
	}

}
