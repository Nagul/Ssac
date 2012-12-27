package affichage;
import java.util.Random;

import pathFinding.AStarPathFinder;

import environnement.EnvironnementImpl;

import com.trolltech.qt.gui.QApplication;
 
public class TestQt {
	
	public static int date;
	public static Random rand;
	public static EnvironnementImpl environnement;
	public static AStarPathFinder aStar;
	
	public static void main(String[] args) {
		
		date = 0;
		rand = new Random();
		
		int longueur = 500;
		int largeur = 500;
		environnement = new EnvironnementImpl(longueur, largeur);
		
		aStar = new AStarPathFinder(TestQt.environnement.getTerrain(), 50, true);

		QApplication.initialize(args);
		
		Widget test = new Widget();
		test.show();
		
		QApplication.exec();

	}
}