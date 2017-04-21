/** @author Soto Jiménez Jonathan
 * @version 2.0
 * Esta clase maneja el programa principal
*/
/*Imports necesarios para manejar el ingreso de las jugadas, */
import java.util.Scanner;

/**Clase principal, con ella se maneja todo el juego de Solitario*/
public class Solitario
{
	/*Se anejaron estaticos los atributos ya que puse un metodo fuera del main y se requerian de esta manera*/
	static String jugada;								/*-> Jugada ingresada por el usuario*/
	static Mazo mazo= new Mazo();						/*-> Se instancia un objeto tipo Mazo para realizar todos los metodos*/
	static Scanner scan = new Scanner(System.in);		/*-> Se instanca un Scanner*/

	/**
	 * Metodo main para manejar la estructura principal del programa
	 * @param args Argumentos del programa
	 * @see Mazo#crearMazo()
	 * @see Mazo#barajarMazo()
	 * @see Mazo#repartir()
	 * @see Mazo#imprimirMazo()
	 * @see #pedirAccion()
	 * @see #jugar()
	 * @see Mazo#ganar()
	*/
	public static void main (String args[])
	{
		mazo.crearMazo();								/*-> Se crea el mazo para jugar*/
		mazo.barajarMazo();								/*-> Se barajea el mazo*/
		mazo.repartir();								/*-> Se reparte entre las pilas*/
		mazo.imprimirMazo();							/*-> Muestra las pilas*/
		
		while (true)									/*-> Entra en un ciclo infinito donde se rompe si el jugador gana o ingresa "exit"*/
		{
			pedirAccion();								/*-> Pide la jugada o comando*/
			jugar();									/*-> Manda a llamar al metodo jugar, que es un metodo compuesto por submetodos*/
			mazo.ganar();								/*-> Si las 4 pilas de A's estan llenas (y ordenadas) ganas la partida*/
		}
	}

	/**
	* Metodo jugar es una coleccion de metodos que simulan el juego de Solitario
	* @see Mazo#imprimirMazo()
	* @see Mazo#checar_carta(String)
	* @see Mazo#moverCarta()
	* @see #pedirAccion()
	*/
	public static void jugar()
	{
		try
		{
			mazo.imprimirMazo();							/*-> Muestra el mazo*/
			if (mazo.checar_carta(jugada))					/*-> Verifica si la jugada ingresada por el usuario es valida, si lo es, continua*/
				{	
				if (mazo.moverCarta())						/*-> Mueve la carta, si los parametros para mover (origen-destino) son correctos, mueve la carta*/
					{
						mazo.eliminarCarta();				/*-> Elimina las cartas de la pila origen */
						mazo.imprimirMazo();				/*-> Muestra el nuevo mazo*/
					}
					else				
						pedirAccion();						/*->Si no se pudo mover la carta por cualquier situacion, vuelve a pedir la jugada o comando*/
				}
				else
					System.out.println("");					/*-> Si no pudo ejecutar la jugada o comando, lo vuelve a pedir*/
		}catch(Exception e)
		{
			System.out.println("Debe ingresar una jugada valida, si tiene dudas pregunte al administrador o LEA EL INSTRUCTIVO ANEXO");
		}
	}

	/**
	* Metodo pedirAccion verifica si la jugada es un comando y lo ejecuta
	* @see Mazo#imprimirMazo()
	* @see  #pedirAccion()
	* @see Mazo#nextReserva()
	* @see #pedirAccion()
	*/
	public static void pedirAccion()
	{
		try												/*->Se manejaron excepciones ya que puede arrojar una por el tamaño del String ingresado*/
		{
			System.out.print("Ingrese su jugada: ");
			jugada = scan.nextLine();
			if (jugada.equals("r") || jugada.equals("R"))						/*->Si el usuario ingresa "r" pedira otra carta a la pila reserva*/
			{
				System.out.println("Se ha tomado otra carta de la reserva");
				mazo.nextReserva();						/*->Metodo para pedir otra carta de la pila reserva*/
				mazo.imprimirMazo();
				pedirAccion();
			}
			else if (jugada.equals("exit"))				/*->Si ingresa exit se sale del programa*/
			{
				System.out.println("Gracias por jugar con el PrimO Solitario!");
				System.exit(0);
			}
			else if (jugada.equals(""))
			{
				System.out.println("Tiene que elegir alguna carta!!");	/*->Si no ingresa nada, le exige que ingrese algo*/
				mazo.imprimirMazo();
				pedirAccion();
			}
			else if (jugada.equals("allVisible"))		/*->Seccion de trucos, allVisible destapa todas las cartas (trabajando en ello)*/ 
			{
				mazo.allVisible();
				mazo.imprimirMazo();
				pedirAccion();
			}else if (jugada.length() < 3)
			System.out.println("SS");
		}
		catch (Exception e)
		{
			System.out.println("");
		}
	}		
}


