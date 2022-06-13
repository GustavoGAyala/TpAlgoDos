package combates;


import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class MenuDeCombatesTest {
	
	@Test
	public void compiteLigaHeroeConLigaVillanoGanaHeroe() throws IOException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException, CaracteristicaNegativaException {
		
		Competidor heroe = new Personaje(true, "Julian","El Invatible", 900, 800, 1000, 900);
		
		Competidor villano = new Personaje(false, "Patricio","Mr Musculo", 600, 790, 800, 900);
		Liga ligaVillano = new Liga(false, "ligaVillano");
		Liga ligaHeroe=new Liga(true, "ligaHeroe");
		ligaVillano.addCompetidor(villano);
		ligaHeroe.addCompetidor(heroe);
		
		Assert.assertTrue(ligaHeroe.esGanador(ligaVillano, Caracteristica.VELOCIDAD));
		
	}
	
	@Test
	public void empatanTodos() throws IOException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException, CaracteristicaNegativaException {
		
		Competidor heroe = new Personaje(true, "Julian","El Invatible", 100, 100, 100, 100);
		
		Competidor villano = new Personaje(false, "Patricio","Mr Musculo", 100, 100, 100, 100);
		
		Assert.assertFalse(heroe.esGanador(villano, Caracteristica.VELOCIDAD));
		
	}

	@Test
	public void compiteHeroeCOntraVIllanoGanaHeroe() throws IOException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException, CaracteristicaNegativaException {
		
		Competidor heroe = new Personaje(true, "Julian","El Invatible", 900, 800, 1000, 900);
		
		Competidor villano = new Personaje(false, "Patricio","Mr Musculo", 600, 790, 800, 900);
		
		Assert.assertTrue(heroe.esGanador(villano, Caracteristica.VELOCIDAD));
		
	}
	@Test
	public void compitevillanoContraHeroe2yPierde() throws IOException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException, CaracteristicaNegativaException {
		
		
		Competidor heroe2 = new Personaje(true, "pepito","josesito", 100,100,1000,100);
		Competidor villano = new Personaje(false, "Patricio","Mr Musculo", 600, 790, 800, 900);
		
		Assert.assertFalse(villano.esGanador(heroe2, Caracteristica.VELOCIDAD));
		
	}
	@Test
	public void compiteHeroeCOntraVIllanoGanaVillano() throws IOException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException, CaracteristicaNegativaException {
		
		Competidor heroe = new Personaje(true , "Julian","El Invatible", 600, 900, 600, 900);
		Competidor villano = new Personaje(false, "Patricio","Mr Musculo", 900, 800, 1000, 900);
		
		Assert.assertFalse(heroe.esGanador(villano, Caracteristica.FUERZA));
	
	}

}
