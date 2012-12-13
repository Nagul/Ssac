package affichage;
import java.util.ArrayList;

import pathFinding.AStarPathFinder;
import pathFinding.Path;

import agent.agents.*;
import environnement.Coordonnee;
import environnement.Terrain;
import environnement.TypeTerrain;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QDockWidget;
import com.trolltech.qt.gui.QFrame;
import com.trolltech.qt.gui.QGraphicsPixmapItem;
import com.trolltech.qt.gui.QGraphicsScene;
import com.trolltech.qt.gui.QGraphicsView;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QListWidgetItem;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QPen;
import com.trolltech.qt.gui.QPixmap;
import com.trolltech.qt.gui.QToolBar;
import com.trolltech.qt.gui.QWidget;



public class Widget extends QMainWindow {
	
	private QGraphicsView view;
	private QGraphicsScene scene;
	private QToolBar toolbar;
	private QDockWidget dockWidget;
	private Terrain terrain;
	

	public Widget() {
		super();

		setToolbar();
		setDockWidget();
		setScene();
		setPinceauxNTerrain();
	}
	
	
	private void setToolbar() {
		toolbar = new QToolBar("Toolbar");
		QIcon runButton = new QIcon("./ressources/runButton.png");
		toolbar.addAction(runButton, "Run", this, "run()");
		QIcon stopButton = new QIcon("./ressources/stopButton.png");
		toolbar.addAction(stopButton, "Stop");
		QIcon optionButton = new QIcon("./ressources/optionButton.png");
		toolbar.addAction(optionButton, "Option");
		QIcon zoomButton = new QIcon("./ressources/zoomButton.png");
		toolbar.addAction(zoomButton, "Zoom", this, "zoom()");
		QIcon dezoomButton = new QIcon("./ressources/dezoomButton.png");
		toolbar.addAction(dezoomButton, "Dézoom", this, "dezoom()");

		this.addToolBar(toolbar);
	}
	
	private void setDockWidget() {
		QWidget myWidget = new QWidget();
		QGridLayout layout = new QGridLayout();

		QComboBox sortWidget = new QComboBox();
		sortWidget.addItem("Type d'agent");
		sortWidget.addItem("But");
		sortWidget.addItem("Rôle");
		sortWidget.activated.connect(this, "sortBy(String)");
		layout.addWidget(sortWidget, 0, 0);

		dockWidget = new QDockWidget(tr("Agents"), this);

		QListWidget4Agent content = new QListWidget4Agent();
		content.itemDoubleClicked.connect(this, "onDoubleClick(QListWidgetItem)");
		content.itemDoubleClicked.connect(this, "fogOfWar(QListWidgetItem)");
		ArrayList<Agent> agents = (ArrayList<Agent>) TestQt.environnement.getAgents();
		for (Agent a : agents) {
			QListWidgetItem4Agent it = new QListWidgetItem4Agent(a);
			content.addItem(it);
		}
		content.refresh();

		layout.addWidget(content, 1, 0);
		myWidget.setLayout(layout);

		dockWidget.setAllowedAreas(Qt.DockWidgetArea.LeftDockWidgetArea);
		dockWidget.setWidget(myWidget);
		this.addDockWidget(Qt.DockWidgetArea.LeftDockWidgetArea, dockWidget);
	}

	private void fogOfWar(QListWidgetItem item) {
		System.out.println("Brouillard de guerre à implémenter");
	}

	private void sortBy(String type) {
		QListWidget4Agent content = getDockContent();

		if (type.equals("Type d'agent")) {
			content.sort(SortType.TypeAgent);
		}
		else if (type.equals("But")) {
			content.sort(SortType.Goal);
		}
		else if (type.equals("Rôle")) {
			content.sort(SortType.Role);
		}
	}
	
	private void onDoubleClick(QListWidgetItem item) {
		// cacher / afficher la liste
		if (!(item instanceof QListWidgetItem4Agent)) {
			if (!item.text().equals("")) {
				QListWidget4Agent content = getDockContent();

				int i = 0;
				while (item != content.item(i)) {
					i++;
				}
				toggleItems(i);
			}
		}
		// montrer les détails de l'agent
		else {
			QListWidgetItem4Agent item4Agent = (QListWidgetItem4Agent) item;
			Agent agent = item4Agent.getAgent();
			view.centerOn(agent.getCoordonnee().getAbscisse()*32, 
					agent.getCoordonnee().getOrdonnee()*32);
			QFrame details = new QFrame4Details(agent);
			details.show();
		}
	}

	private void toggleItems(int fromIndex) {
		QListWidget4Agent content = getDockContent();

		int i = fromIndex + 1;
		QListWidgetItem item = content.item(i);
		while (item instanceof QListWidgetItem4Agent) {
			item.setHidden(!item.isHidden());
			item = content.item(++i);
		}
	}

	private void setScene() {
		scene = new QGraphicsScene(this);
		scene.setSceneRect(0, 0, TestQt.environnement.getTerrain().getLongueur()*32,
				TestQt.environnement.getTerrain().getLargeur()*32);
	}
	
	
	
	
	private void setPinceauxNTerrain() {
		
		int longueur = TestQt.environnement.getTerrain().getLongueur(); 
		int largeur = TestQt.environnement.getTerrain().getLargeur();
		
		//terrain
		int demiLongueur = longueur/2;
		int demiLargeur = largeur/2;
		int coef = demiLongueur/2 + demiLargeur/2;
		GenerateurTerrain terrainG = new GenerateurTerrain(longueur, largeur, 1);
		GenerateurTerrain foretG = new GenerateurTerrain(longueur, largeur, 4);
		terrain = TestQt.environnement.getTerrain();
		byte[][] t = new byte[longueur][largeur];
		byte[][] f = new byte[longueur][largeur];
		t = terrainG.getTerrain();
		f = foretG.getTerrain();
		ArrayList<Coordonnee> rivieres = new ArrayList<Coordonnee>();
		int i,j;
		for(i=0; i<longueur; i++) {
			for(j=0; j<largeur; j++) {
				double x;
				if(t[i][j]<0) {
					x = t[i][j] + 256;
				} else {
					x= t[i][j];
				}
				double d = Math.pow(demiLongueur-i, 2) + Math.pow(demiLargeur-j, 2);
				x *= fctGauss(d, coef);
				if(x<110) {
					terrain.setTerrain(i, j, environnement.TypeTerrain.EauDouce);
				} else if(x<140 && x>=110) {
					if(Math.abs(f[i][j])<100) {
						terrain.setTerrain(i, j, environnement.TypeTerrain.Foret);
					} else {
						terrain.setTerrain(i, j, environnement.TypeTerrain.Terre);
					}
				} else {
					terrain.setTerrain(i, j, environnement.TypeTerrain.Montagne);
					if(TestQt.rand.nextInt(2000)==0) {
						rivieres.add(new Coordonnee(i, j));
					}
				}
			}
		}
		
		/*adoucir eaux interieures
		 * par recurence, tout ce qui est relie à (0;0) est de l'eau sale
		 */
		ArrayList<Coordonnee> eauSale = new ArrayList<Coordonnee>();
		eauSale.add(new Coordonnee(0, 0));
		Coordonnee titleCourant;
		int atc, otc;
		while(eauSale.size() != 0) {
			titleCourant = eauSale.get(0);
			atc = titleCourant.getAbscisse();
			otc = titleCourant.getOrdonnee();
			if(terrain.getCase(atc, otc).getType()==environnement.TypeTerrain.EauDouce) {
				terrain.setTerrain(atc, otc, environnement.TypeTerrain.EauDeMer);
				
				if(atc>0) {
					eauSale.add(new Coordonnee(atc-1, otc));
				}
				if(otc>0) {
					eauSale.add(new Coordonnee(atc, otc-1));
				}
				if(atc<longueur-1) {
					eauSale.add(new Coordonnee(atc+1, otc));
				}
				if(otc<largeur-1) {
					eauSale.add(new Coordonnee(atc, otc+1));
				}
			}
			eauSale.remove(0);
		}
		
		//rivieres
		//TODO : si ambiguite, chemin aleatoire
		boolean nonRelie;
		int x,y;
		int xp,yp;
		int fx, fy;
		int min;
		int frx = 0;
		int fry = 0;
		int wtf;
		boolean lac;
		for(i=0; i<rivieres.size(); i++) {
			fx = rivieres.get(i).getAbscisse();
			fy = rivieres.get(i).getOrdonnee();
			terrain.setTerrain(fx, fy, environnement.TypeTerrain.EauDouce);
			lac = true;
			do {
				nonRelie = false;
				if(t[fx][fy]<0) {
					min = t[fx][fy] + 256;
				} else {
					min= t[fx][fy];
				}
				for(x=-1; x<2; x++) {
					for(y=-1; y<2; y++) {
						//title courant, rien à faire
						if ((x == 0) && (y == 0)) {
							continue;
						}
						xp = x + fx;
						yp = y + fy;
						//si dejà visite, rien faire
						if(terrain.getCase(xp, yp).getType().equals(environnement.TypeTerrain.EauDouce)) {
							continue;
						}
						if(t[xp][yp]<0) {
							wtf = t[xp][yp] + 256;
						} else {
							wtf= t[xp][yp];
						}
						if(wtf<min) {
							min = wtf;
							frx = xp;
							fry = yp;
							nonRelie = true;
							//si c'est la mer ou un lac, on arrête.
							if(wtf<110) {
								nonRelie = false;
								lac = false;
							}
						}
						if(wtf==min && wtf>=110) {
							min = wtf;
							frx = xp;
							fry = yp;
							nonRelie = true;
						}
					}
				}
				if (nonRelie) {
					terrain.setTerrain(frx, fry, environnement.TypeTerrain.EauDouce);
					fx = frx;
					fy = fry;
				} else {
					//creer un lac d'eau douce
					if(lac) {
						for(x=-2; x<3; x++) {
							for(y=-2; y<3; y++) {
								if(Math.abs(x) + Math.abs(y) < 4) {
									xp = x + fx;
									yp = y + fy;
									terrain.setTerrain(xp, yp, environnement.TypeTerrain.EauDouce);
								}
							}
						}
					}
				}
			} while (nonRelie);
		}
		
		//pinceaux
		QPen penGreen = new QPen(QColor.green);
		QBrush brushGreen = new QBrush(QColor.green, Qt.BrushStyle.SolidPattern);
		QPen penDarkGreen = new QPen(QColor.darkGreen);
		QBrush brushDarkGreen = new QBrush(QColor.darkGreen, Qt.BrushStyle.SolidPattern);
		QPen penBlue = new QPen(QColor.blue);
		QBrush brushBlue = new QBrush(QColor.blue, Qt.BrushStyle.SolidPattern);
		QPen penDarkBlue = new QPen(QColor.darkBlue);
		QBrush brushDarkBlue = new QBrush(QColor.darkBlue, Qt.BrushStyle.SolidPattern);
		QPen penGray = new QPen(QColor.gray);
		QBrush brushGray = new QBrush(QColor.gray, Qt.BrushStyle.SolidPattern);
		QPen penRed = new QPen(QColor.red);
		QBrush brushRed = new QBrush(QColor.red, Qt.BrushStyle.SolidPattern);
		
		//Maintenant, on dessine :
		int mi, mj;
		for(i=0; i<longueur; i++) {
			mi = i << 5;
			for(j=0; j<largeur; j++) {
				mj = j << 5;
				switch(terrain.getCase(i, j).getType()) {
				case EauDouce:
					scene.addRect(mi, mj, 32, 32, penBlue, brushBlue);
					break;
				case EauDeMer:
					scene.addRect(mi, mj, 32, 32, penDarkBlue, brushDarkBlue);
					break;
				case Foret:
					scene.addRect(mi, mj, 32, 32, penDarkGreen, brushDarkGreen);
					break;
				case Montagne:
					scene.addRect(mi, mj, 32, 32, penGray, brushGray);
					break;
				case Terre:
					scene.addRect(mi, mj, 32, 32, penGreen, brushGreen);
					break;
				default :
					scene.addRect(mi, mj, 32, 32, penRed, brushRed);
				}
			}
		}
		
		//génération des populations initiales
		GenerateurTerrain animauxG = new GenerateurTerrain(longueur, largeur, 6);
		byte[][] animaux = new byte[longueur][largeur];
		animaux = animauxG.getTerrain();
		int rand;
		for(i=0; i<longueur; i++) {
			for(j=0; j<largeur; j++) {
				//réduire la population
				rand = TestQt.rand.nextInt(10);
				if(rand!=1)
					continue;
				if(((animaux[i][j]==127)||(animaux[i][j]==126))
						&&(TestQt.environnement.getTerrain().getCase(i, j).getType()==TypeTerrain.Foret)) {
					TestQt.environnement.addAgent(new AgentLoup(new Coordonnee(i,j), 0, null));
				} else if((animaux[i][j]==125)
						&&(TestQt.environnement.getTerrain().getCase(i, j).getType()==TypeTerrain.Terre)) {
					TestQt.environnement.addAgent(new AgentMouton(new Coordonnee(i,j), 0));
				} else if(animaux[i][j]==124) {
					TestQt.environnement.addAgent(new AgentOiseau(new Coordonnee(i, j), 0));
				} else if(((animaux[i][j]==123)||(animaux[i][j]==122))
						&&(TestQt.environnement.getTerrain().getCase(i, j).getType()==TypeTerrain.Foret)) {
					TestQt.environnement.addAgent(new AgentOurs(new Coordonnee(i,j), 0));
				} else if((animaux[i][j]==121)
						&&((TestQt.environnement.getTerrain().getCase(i, j).getType()==TypeTerrain.EauDeMer)
								||(TestQt.environnement.getTerrain().getCase(i, j).getType()==TypeTerrain.EauDeMer))) {
					TestQt.environnement.addAgent(new AgentPoisson(new Coordonnee(i,j), 0, null));
				} else if((animaux[i][j]==120)
						&&((TestQt.environnement.getTerrain().getCase(i, j).getType()==TypeTerrain.Terre)
								||(TestQt.environnement.getTerrain().getCase(i, j).getType()==TypeTerrain.Foret))) {
					TestQt.environnement.addAgent(new AgentRongeur(new Coordonnee(i,j), 0));
				}
			}
		}

		/*test de AStar
		Coordonnee c = new environnement.Coordonnee(250, 250);
		AgentHumainImpl arg = new AgentHumainImpl(c);
		AStarPathFinder pf = new AStarPathFinder(terrain, 150, true);
		Path path = pf.findPath(arg, 200, 200, 300, 300);
		scene.addRect(200, 200, 1, 1, penRed, brushRed);
		scene.addRect(300, 300, 1, 1, penRed, brushRed);
		if (path!=null) {
			int l;
			for(l=0; l<path.getLength(); l++) {
				Coordonnee s = path.getCoordonnee(l);
				scene.addRect(s.getAbscisse(), s.getOrdonnee(), 1, 1, penRed, brushRed);
			}
		}*/
		
		
		view = new QGraphicsView(scene);
		view.setBackgroundBrush(brushDarkBlue);
		
		this.setCentralWidget(view);
	}
	
	private double fctGauss(double q, int coef) {
		
		return Math.exp(-q/(2*Math.pow(coef, 2)));
		
	}
	
	public void zoom() {
		view.scale(2,2);
	}
	
	public void dezoom() {
		view.scale(0.5,0.5);
	}
	
	public void run() {
		TestQt.environnement.step();
		QPixmap imageLoup = new QPixmap("./ressources/Wolf.png");
		QPixmap imageMouton = new QPixmap("./ressources/Sheep.png");
		QPixmap imageRongeur = new QPixmap("./ressources/Rodent.png");
		QPixmap imageOurs = new QPixmap("./ressources/Bear.png");
		QPixmap imageOiseau = new QPixmap("./ressources/Bird.png");
		QPixmap imagePoisson = new QPixmap("./ressources/Fish.png");
		QGraphicsPixmapItem im = new QGraphicsPixmapItem();
		for(Agent a : TestQt.environnement.getAgents()) {
			if(a instanceof AgentLoup) {
				im = scene.addPixmap(imageLoup);
			}
			if(a instanceof AgentMouton) {
				im = scene.addPixmap(imageMouton);
			}
			if(a instanceof AgentOiseau) {
				im = scene.addPixmap(imageOiseau);
			}
			if(a instanceof AgentOurs) {
				im = scene.addPixmap(imageOurs);
			}
			if(a instanceof AgentPoisson) {
				im = scene.addPixmap(imagePoisson);
			}
			if(a instanceof AgentRongeur) {
				im = scene.addPixmap(imageRongeur);
			}
			im.setPos(a.getCoordonnee().getAbscisse()*32, a.getCoordonnee().getOrdonnee()*32);
		}
		refreshDockWidget();
	}

	private QListWidget4Agent getDockContent() {
		return (QListWidget4Agent) ((QGridLayout) dockWidget.widget().layout()).itemAtPosition(1, 0).widget();
	}

	private void refreshDockWidget() {
		ArrayList<Agent> agents = (ArrayList<Agent>) TestQt.environnement.getAgents();

		QListWidget4Agent content = getDockContent();
		ArrayList<QListWidgetItem4Agent> widgetAgentItems = content.listItems();
		ArrayList<Agent> widgetAgents = new ArrayList<Agent>();
		for (QListWidgetItem4Agent item : widgetAgentItems) {
			widgetAgents.add(item.getAgent());
		}

		for (Agent a : agents) {
			if (!widgetAgents.contains(a)) {
				QListWidgetItem4Agent it = new QListWidgetItem4Agent(a);
				content.addItem(it);
			}
		}

		content.refresh();
	}

}