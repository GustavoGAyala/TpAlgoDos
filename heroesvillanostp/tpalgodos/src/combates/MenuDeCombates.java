package combates;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MenuDeCombates {

	private List<Competidor> listaDeHeroes = new ArrayList<Competidor>();
	private List<Competidor> listaDeVillanos = new ArrayList<Competidor>();
	private Map<String, Competidor> listaDePersonajes = new HashMap<String, Competidor>();
	private Map<String, Competidor> listaDeLigas = new HashMap<String,Competidor>();
	
	
	public MenuDeCombates() throws IOException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException {
		//cargar competidores leera del archivo y los separa en treeSet de villanos o de heroes
		cargarCompetidores("src/personajes.in");
		//cargar ligas lo mismo con ligas
		cargarLigas("src/ligas.in");
	}

	private void cargarLigas(String archivoLigas) throws TipoDeCompetidorInvalidoException, IOException, NoHayArchivoDeEntrada, YaEstoyEnLigaException, CaracteristicaNegativaException {
		
		FileReader in = new FileReader(archivoLigas);
		BufferedReader lector = new BufferedReader(in);
		
		try {
			
			String linea = lector.readLine();
			
			while(linea != null) {
				linea = linea.trim();
				if(!linea.isEmpty()) {
					
					Competidor liga = agregarLiga(linea);
					
					if(liga.esHeroe() == true) {
						listaDeHeroes.add(liga);
					}else {
						listaDeVillanos.add(liga);
					}
					listaDeLigas.put(liga.getNombre(), liga);
					
					linea = lector.readLine();
					
				}
			
		  }
		}catch(FileNotFoundException e){
			throw new NoHayArchivoDeEntrada("No se leyo el archivo");
		}
		finally {
			lector.close();
		}
	}
	
	private Competidor agregarLiga(String linea) throws YaEstoyEnLigaException, TipoDeCompetidorInvalidoException, CaracteristicaNegativaException {
		
		Liga liga = null;
		String[] campos = linea.split(",");
		
		try {
			if(listaDePersonajes.get(campos[1]).esHeroe()) {
				liga = new Liga(true, campos[0].trim());
			}else {
				liga = new Liga(false, campos[0].trim());
			}
			
			
			for(int i = 1; i < campos.length; i++ ) {
				listaDePersonajes.get(campos[i]).setEstoyEnLiga(false);
				liga.addCompetidor(listaDePersonajes.get(campos[i]));
			}
		}catch(YaEstoyEnLigaException e) {
		
		}
		return liga;
	}

	public Competidor elegirCompetidorConMayorCaracteristica(Competidor heroeOVillano, Caracteristica c) {
		
		Competidor contrincante = elegirCompetidor(heroeOVillano);
		
		
		
		
		return contrincante;
	}

	/**@pre :Carga un competidor de la lista de villanos o de la lista de heroes seg�n
	 *        sea contrario el tipo de Competidor usado en la batalla.
	 * @post:retorna un competidor.
	**/
	private Competidor elegirCompetidor(Competidor competidor) {
		
		
		Competidor heroeOVillano;
		
		if(competidor.esHeroe()){
			int numero = (int)(Math.random()*listaDeVillanos.size());
			
			heroeOVillano = listaDeVillanos.get(numero);
		}else {
			int numero = (int)(Math.random()*listaDeVillanos.size());
			heroeOVillano = listaDeHeroes.get(numero);
		}
		 
		return heroeOVillano;
	}

	private void cargarCompetidores(String archivoEntrada) throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException {
		
		FileReader in = new FileReader(archivoEntrada);
		BufferedReader lector = new BufferedReader(in);
		
		try {
			
			String linea = lector.readLine();
			
			while(linea != null) {
				linea = linea.trim();
				if(!linea.isEmpty()) {
					
					Competidor personaje = agregarPersonaje(linea);
					
					if(personaje.esHeroe()) {
						listaDeHeroes.add(personaje);
					}else {
						listaDeVillanos.add(personaje);
					}
					linea = lector.readLine();
					
				}
			
			}
		}finally {
			lector.close();
		}
		
	}

	private Competidor agregarPersonaje(String linea) throws CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException {
		
		Competidor competidor = null;
		String[] campos = linea.split(",");
		
		try {
			if(campos[0].trim().equals("Heroe")) {
				
				competidor = new Personaje(true, campos[1].trim(), 
						campos[2].trim(), Integer.parseInt(campos[3].trim()),Integer.parseInt(campos[4].trim()), 
						Integer.parseInt(campos[5].trim()), Integer.parseInt(campos[6].trim()));
			}else if(campos[0].trim().equals("Villano")) {
				
				competidor = new Personaje(false, campos[1].trim(), 
						campos[2].trim(), Integer.parseInt(campos[3].trim()),Integer.parseInt(campos[4].trim()), 
						Integer.parseInt(campos[5].trim()), Integer.parseInt(campos[6].trim()));
			}
			listaDePersonajes.put(competidor.getPseudo(), competidor);
			
		}catch(NumberFormatException nfe) {
			throw new CompetidorCaracteristicaInvalidaException("Las caracter�sticas de batalla deben ser enteros");
		}
		return competidor;
	}
	
}
