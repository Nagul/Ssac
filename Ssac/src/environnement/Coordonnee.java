package environnement;

public class Coordonnee {

	private int abscisse;
	private int ordonnee;

	public Coordonnee(int a, int o) {
		abscisse = a;
		ordonnee = o;
	}
	
	public int getAbscisse() {
		return abscisse;
	}
	
	public void modAbscisse(int mod) {
		this.abscisse += mod;
	}
	
	public int getOrdonnee() {
		return ordonnee;
	}
	
	public void modOrdonnee(int mod) {
		this.ordonnee += mod;
	}
	
	public int hashCode() {
		return abscisse*ordonnee;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Coordonnee) {
			Coordonnee o = (Coordonnee) other;
			return (o.abscisse == abscisse) && (o.ordonnee == ordonnee);
		}
		return false;
	}
}
