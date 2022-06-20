package combates;


public class Personaje extends Competidor {

	// private TipoCompetidor type = TipoCompetidor.HEROE;
	public Personaje(boolean esHeroe, String nombre, String pseudo, int velocidad, int fuerza, int resistencia,
			int destreza) throws CaracteristicaNegativaException {
		super(esHeroe, nombre, pseudo, velocidad, fuerza, resistencia, destreza);
	}

	@Override
	public String toString() {
		return "Nombre= " + this.getNombre() + ", " + "Pseudo= " + this.getPseudo() + ", " + this.getTodasLasCaracteristicas();
	}

	@Override
	protected int size() {
		return 1;
	}

}
