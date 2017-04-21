/** @author Soto Jiménez Jonathan
 * @version 2.0
 * Esta clase maneja el programa principal
*/
/*Imports necesarios para manejar la estructura del juego */
import java.util.ArrayList;									/*-> Manejo de los arrayList*/
import java.util.Iterator;									/*-> Manejo de los iteradores*/
import java.util.Random;									/*-> Barajear el mazo*/
import java.util.Collections;								/*-> Para poder barajear el mazo */
import java.util.ListIterator;								/*-> Para poder utilizar algunas funciones del arrayList*/

/**
* La clase Mazo hereda de Carta, para tener algunas funciones
*/
public class Mazo extends Carta
{
	int numero;												/*-> Variable para usar en crearMazo, representa el numero de cartas por mazo /(13)*/
	int repeat = 1;											/*-> Variable para ver en que mazo se estan metiendo las cartas*/
	int numero_mazo_origen;									/*-> Variable donde se maneja el mazo origen que ingreso el usuario*/
	int carta_a_mover;										/*-> Variable donde se maneja la carta que quiere mover el usuario*/
	int numero_mazo_destino;								/*-> Variable donde se maneja el mazo destino que ingreso el usuario*/
	String palo = "♥";										/*-> Primer opcion de palo para la creacion del mazo*/
	String color = "Rojo";									/*-> Primer opcion de color para la creacion del mazo*/	
	String jugadaP;											/*-> Es una copia de la jugada que ingreso el usuario para poder trabajar copn ella, se tuvo que crear ya que trabajamos con la original por referencia*/

	/**____________________________ArrayList de las pilas necesarias____________________________*/	
	ArrayList<Carta> cartas_a_obtener = new ArrayList<Carta>(); /*-> Para obtener las cartas que se necesitan quedar en el mazo origen al momento de moverlas*/
	ArrayList<Carta> mazo = new ArrayList<Carta>(); /*-> Mazo completo de cartas*/
	ArrayList<Carta> pila1 = new ArrayList<Carta>();
	ArrayList<Carta> pila2 = new ArrayList<Carta>();
	ArrayList<Carta> pila3 = new ArrayList<Carta>();
	ArrayList<Carta> pila4 = new ArrayList<Carta>();

	ArrayList<Carta> pila_Reserva = new ArrayList<Carta>();

	ArrayList<Carta> pila_origen;					/*-> Para manejar los datos que ingreso el usuario relacionados a la pila origen*/
	ArrayList<Carta> pila_destino;					/*-> Para manejar los datos que ingreso el usuario relacionados a la pila destino*/

	/**Metodo que crea el mazo */						/*-> Va iterando del 1 al 13 ingresando los datos que se le indican*/
	public void crearMazo()
	{
		for (numero=1 ; numero<=13 ; numero++)
		{
		Carta carta = new Carta();
		carta.numero = numero;
		carta.palo   = palo;
		carta.color  = color;
		mazo.add(carta);							/*-> Al tener los datos de la primer carta se ingresa en el arrayList*/

		//System.out.println(carta.numero);
		if (numero == 13 && repeat==1)				/*-> Si ya acabo con un palo, se sigue con otro*/
		{
			palo ="♦";
			numero = 0;
			repeat = 2;
		}
		else if (numero == 13 && repeat==2)			/*-> Si ya acabo dos palos, cambia de color y de palo*/
			{
			numero = 0;
			palo = "♠";
			color = "Negro";
			repeat = 3;
			}
		else if (numero == 13 && repeat==3)			/*-> Si ya acabo 3 palos, cambia la variable al ultimo palo*/
			{
			numero = 0;
			palo = "♣";
			repeat = 4;
			}
		}
	}

	/**
	* Metodo que barajea el mazo completo
	*/
	public void barajarMazo()
	{
		long seed = System.nanoTime();
		Collections.shuffle(mazo, new Random(seed));
	}

	/**
	* Metodo que reparte las cartas barajeadas a todas las pilas
	*/

	public void repartir()
	{
		int i=0;
		Iterator<Carta> Iterador_Repartir = mazo.iterator();	/*-> Se maneja un ietarador para este proceso, ya que estamos manejando ArrayList*/
		while(Iterador_Repartir.hasNext())						/*-> Mientras el arrayList tenga otro elemento...*/
		{
			i++;
			Carta elemento = Iterador_Repartir.next();			/*-> Se le asigna a elemento (tipo carta) ese siguiente elemento*/
			if ( i==1)											/*-> Aqui se ingresan las cartas a las pilas, todo depende de i*/
				pila1.add(elemento);							/*Si quisieramos aumentar/disminuir las cartas por pila, se modifica esta parte (la i)*/
			else if (i>=2 && i<=3)
				pila2.add(elemento);
			else if (i>=4 && i<=6)
				pila3.add(elemento);
			else if (i>=7 && i<=10)
				pila4.add(elemento);
			else if (i>=11 && i<=15)
				pila5.add(elemento);
			else if (i>=16 && i<=21)
				pila6.add(elemento);
			else if (i>=22 && i<=28)
				pila7.add(elemento);
			else 
				pila_Reserva.add(elemento);
		}
	}

	/**
	* Metodo que imprime todas las pilas en pantalla (me quebre mucho la cabeza, no pude hacer que fueran en paralelo =/)
	*/
	public void imprimirMazo()
	{
		Iterator<Carta> Iterador_Print;						/*-> Iterador que se estara usando para imprimir todas las pilas*/
		Carta elemento;										/*-> Variable tipo carta que se usara para checar atributo oculta*/
		/*Pila 1*/
		Iterador_Print = pila1.iterator();					/*-> Se asigna el iterador de la pila1 a Iterador_Print*/
		System.out.println("\n    Pila 1");
		System.out.println("═══════════════════════════");
		int x=1;
		try
		{
			if (pila1.isEmpty())							/*-> Si la pila esta vacia, no imprime nada*/
				System.out.print("                ");
			else											/*-> Si no..*/
			{
				elemento = pila1.get(pila1.size()-1);		/*-> Obtiene el ultimo elemento del arrayList */
				elemento.oculta = false;					/*-> Destapa la carta*/
				while(Iterador_Print.hasNext())				/*-> Mientras exista otro...*/
				{
					elemento = Iterador_Print.next();		/*-> Se asigna ese elemento a la variable elemento*/
					if (elemento.color == "Rojo")			/*-> Checa que color es e imprime dependiendo el caso*/
					{
						System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
						x++;
					}
					else
					{
						System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");	
						x++;
					}
				}
			}
		}catch (Exception e)								/*-> Cachamos la excepcion de cuando la pila esta vacia*/
		{ 
			System.out.print(" ");
		}		
		System.out.print("\n═══════════════════════════\n");

															/*-> Este proceso se repite para todas las pilas*/
		/*Pila 2*/
		Iterador_Print = pila2.iterator();
		System.out.println("\n    Pila 2");
		System.out.println("═══════════════════════════");
		x=1;
		try
		{
			if (pila2.isEmpty())
				System.out.print("                ");
			else
			{
				elemento = pila2.get(pila2.size()-1);
				elemento.oculta = false;
				while(Iterador_Print.hasNext())
				{
					elemento = Iterador_Print.next();
					if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");	
							x++;
						}
					}
					else
					{
						System.out.print("    █");
						x++;	
					}	
					
				}
			}
		}catch (Exception e)
		{ 
			System.out.print(" ");
		}		
		System.out.print("\n═══════════════════════════\n");

		/*Pila 3*/
		Iterador_Print = pila3.iterator();
		System.out.println("\n    Pila 3");
		System.out.println("═══════════════════════════");
		x=1;
		elemento = pila3.get(pila3.size()-1);
		elemento.oculta = false;
		try
		{
			if (pila3.isEmpty())
				System.out.print("                ");
			else
			{
				while(Iterador_Print.hasNext())
				{
				elemento = Iterador_Print.next();
					if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");	
							x++;
						}
					}
					else
					{
						System.out.print("    █");
						x++;	
					}
				}
			}
		}catch (Exception e)
		{ 
			System.out.print(" ");
		}	

		System.out.print("\n═══════════════════════════\n");


		/*Pila 4*/
		Iterador_Print = pila4.iterator();
		System.out.println("\n    Pila 4");
		System.out.println("═══════════════════════════");
		x=1;
		elemento = pila4.get(pila4.size()-1);
		elemento.oculta = false;
		try
		{
			if (pila4.isEmpty())
				System.out.print("                ");
			else
			{
				while(Iterador_Print.hasNext())
				{
				elemento = Iterador_Print.next();
				if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");	
							x++;
						}
					}
					else
					{
						System.out.print("    █");
						x++;	
					}	
				}
			}
		}catch (Exception e)
		{ 
			System.out.print(" ");
		}

		System.out.print("\n═══════════════════════════\n");


		/*Pila 5*/
		Iterador_Print = pila5.iterator();
		System.out.println("\n    Pila 5");
		System.out.println("═══════════════════════════");
		x=1;
		elemento = pila5.get(pila5.size()-1);
		elemento.oculta = false;
		try
		{
			if (pila5.isEmpty())
				System.out.print("                ");
			else
			{
				while(Iterador_Print.hasNext())
				{
				elemento = Iterador_Print.next();
				if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");	
							x++;
						}
					}
					else
					{
						System.out.print("    █");
						x++;	
					}	
				}
			}
		}catch (Exception e)
		{ 
			System.out.print(" ");
		}

		System.out.print("\n═══════════════════════════\n");


				/*Pila 6*/
		Iterador_Print = pila6.iterator();
		System.out.println("\n    Pila 6");
		System.out.println("═══════════════════════════");
		x=1;
		elemento = pila6.get(pila6.size()-1);
		elemento.oculta = false;
		try
		{
			if (pila6.isEmpty())
				System.out.print("                ");
			else
			{
				while(Iterador_Print.hasNext())
				{
				elemento = Iterador_Print.next();
				if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");	
							x++;
						}
					}
					else
					{
						System.out.print("    █");
						x++;	
					}	
				}
			}
		}catch (Exception e)
		{ 
			System.out.print(" ");
		}

		System.out.print("\n═══════════════════════════\n");


				/*Pila 7*/
		Iterador_Print = pila7.iterator();
		System.out.println("\n    Pila 7");
		System.out.println("═══════════════════════════");
		x=1;
		elemento = pila7.get(pila7.size()-1);
		elemento.oculta = false;
		try
		{
			if (pila7.isEmpty())
				System.out.print("                ");
			else
			{
				while(Iterador_Print.hasNext())
				{
				elemento = Iterador_Print.next();
				if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");	
							x++;
						}
					}
					else
					{
						System.out.print("    █");
						x++;	
					}	
				}
			}
		}catch (Exception e)
		{ 
			System.out.print(" ");
		}

		System.out.print("\n═══════════════════════════\n");


		/*Pila Reserva*/
		Iterador_Print = pila_Reserva.iterator();
		System.out.println("\n  Pila Reserva [r]");
		System.out.println("═══════════════════════════");
		x=1;
		elemento = pila_Reserva.get(pila_Reserva.size()-1);
		elemento.oculta = false;
		try
		{
			if (pila_Reserva.isEmpty())
				System.out.print("                ");
			else
			{
				while(Iterador_Print.hasNext())
				{
				elemento = Iterador_Print.next();
				if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");	
							x++;
						}
					}
					else
					{
						System.out.print("    █");
						x++;	
					}	
				}
			}
		}catch (Exception e)
		{ 
			System.out.print(" ");
		}	

		System.out.print("\n═══════════════════════════\n");


		/*ila A1*/
		System.out.println("\n    Pila A1 [a]");
		System.out.println("═══════════════════════════");
		x=1;
		try
		{
			if (pila_A1.isEmpty())
			{
				System.out.print("                ");
				x++;
			}
			else
				{
					Iterador_Print = pila_A1.iterator();
					elemento = pila_A1.get(pila_A1.size()-1);
					elemento.oculta = false;
					while(Iterador_Print.hasNext())
					{
					elemento = Iterador_Print.next();
					if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");		
							x++;
						}
					}
				}
			}

		}catch (Exception e)
		{ 
			System.out.print(" ");
		}	
		System.out.print("\n═══════════════════════════\n");

		/*Pila A2*/
		System.out.println("\n    Pila A2 [b]");
		System.out.println("═══════════════════════════");
		x=1;
		try
		{
			if (pila_A2.isEmpty())
			{
				System.out.print("                ");
				x++;
			}
			else
				{
					Iterador_Print = pila_A2.iterator();
					elemento = pila_A2.get(pila_A2.size()-1);
					elemento.oculta = false;
					while(Iterador_Print.hasNext())
					{
					elemento = Iterador_Print.next();
					if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");		
							x++;
						}
					}
				}
			}

		}catch (Exception e)
		{ 
			System.out.print(" ");
		}	
		System.out.print("\n═══════════════════════════\n");

		/*Pila A3*/
		System.out.println("\n    Pila A3 [c]");
		System.out.println("═══════════════════════════");
		x=1;
		try
		{
			if (pila_A3.isEmpty())
			{
				System.out.print("                ");
				x++;
			}
			else
				{
					Iterador_Print = pila_A3.iterator();
					elemento = pila_A3.get(pila_A3.size()-1);
					elemento.oculta = false;
					while(Iterador_Print.hasNext())
					{
					elemento = Iterador_Print.next();
					if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");		
							x++;
						}
					}
				}
			}

		}catch (Exception e)
		{ 
			System.out.print(" ");
		}	
		System.out.print("\n═══════════════════════════\n");

		/*Pila A4*/
		System.out.println("\n    Pila A4 [d]");
		System.out.println("═══════════════════════════");
		x=1;
		try
		{
			if (pila_A4.isEmpty())
			{
				System.out.print("                ");
				x++;
			}
			else
				{
					Iterador_Print = pila_A4.iterator();
					elemento = pila_A4.get(pila_A4.size()-1);
					elemento.oculta = false;
					while(Iterador_Print.hasNext())
					{
					elemento = Iterador_Print.next();
					if (!elemento.oculta)
					{
						if (elemento.color == "Rojo")
						{
							System.out.print("\033[31m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");
							x++;
						}
						else
						{
							System.out.print("\033[34m    " + x+"| " + cambiarNumcarta(elemento.numero) +" "+elemento.palo + " |"+"\033[37m");		
							x++;
						}
					}
				}
			}

		}catch (Exception e)
		{ 
			System.out.print(" ");
		}	
		System.out.print("\n═══════════════════════════\n");
	}

	/**
	* 	Este metodo verifica las condiciones de la jugada que ingreso el usuario, si son validas, continua, si es false, se sale en el main
	* 	@param jugada JUgada que ingreso el usuario
	*	@return Boolean
	*/

	public Boolean checar_carta(String jugada)
	{
		jugadaP = jugada;												/*-> Se crea una copia local de la jugada del usuario*/
		setPilaOrigenDestino(jugada.substring(0,1),jugada.substring(3,4), jugada.substring(1,2));	/*-> Se parte en subcadenas y se le mandan a la funcion para que las trabaje*/
		selectorDePilas(numero_mazo_origen,numero_mazo_destino);		/*-> Se mandan los datos a la funcion selector de pilas para que trabaje la informacion*/
		if (numero_mazo_origen == 20)									/*-> Si se eligio de origen la reserva, solo se puede mover la ultima carta, por lo tanto se cambia la eleccion a la ultima de la reserva*/
			{
				System.out.println("Usted quizo mover la carta " + (carta_a_mover+1) + " de la pila de reserva sólo se puede mover la ultima pieza\nCambiando eleccion a la ultima carta de la pila de la reserva...");
				carta_a_mover = pila_origen.size()-1;
			}
		if (checarSiVacia())  											/*-> Checa si la pila de origen esta vacia, si lo esta, se manda un mensaje y se sale de la funcion*/
			{			
			if (checarOculta() && checkNum_Color(pila_origen,pila_destino,carta_a_mover)) /*-> Si cumple con que no esta oculta y el numero y el color estan en el orden requerido, procede*/
				{
					return true; 										/*-> Los return sirven para cachar en el main el flujo del programa*/
				}
			else
				{
					return false;	
				}
			}
			else
			{
				return false;
			}
	}	

	/**
	* checkNum_Color Nos dice si el color y el numero son los correctos para mover la carta
	* @param pila_origen Pila donde se esta tomando la carta
	* @param pila_destino Pila donde se pondra la carta
	* @param carta_a_mover Carta que se movera de la pila origen
	*	@return Boolean
	*/
	public Boolean checkNum_Color(ArrayList<Carta> pila_origen, ArrayList<Carta> pila_destino, int carta_a_mover)
	{	
		if (pila_destino.isEmpty())										/*-> Si la pila destino es vacia, regresa true,y lo manda directamente*/
		{
			return true;
		}
		else															/*-> Si no, comprueba el numero y el color*/
		{
			if ((pila_origen.get(carta_a_mover).numero == (pila_destino.get(pila_destino.size()-1).numero)-1) && !(pila_origen.get(carta_a_mover).color == pila_destino.get(pila_destino.size()-1).color))
			{
				return true;	
			}
			else														/*-> Si no cumple con esto, manda un mensaje, regresa false y en el main se sale*/
			{
				System.out.println("El movimiento no cumple con los requisitos.\nEl número de la carta a mover debe ser un numero menor al de la pila destino");
				System.out.println("El color de la carta que quiere mover debe ser diferente al de la pila donde desea moverla");
				return false;	
				}		
		}	
	}

	/**
	*	Este metodo se realizo para poder trabajar con pilas a, b, c, d haciendo referencia a los aces y poder manejar todas las pilas 
	*	con menos codigo, ya que si no se tendria que hacer un switch case con cada mazo origen, y despues un switch case con cada mazo destino
	* 	@param origen Pila origen
	* 	@param destino Pila destino
	*/
	public void selectorDePilas(int origen, int destino)
	{
		switch(origen)
		{
			case 1:
			pila_origen = pila1;
			break;

			case 2:
			pila_origen = pila2;
			break;

			case 3:
			pila_origen = pila3;
			break;

			case 4:
			pila_origen = pila4;
			break;

			case 5:
			pila_origen = pila5;
			break;

			case 6:
			pila_origen = pila6;
			break;

			case 7:
			pila_origen = pila7;
			break;

			case 10:
			pila_origen = pila_A1;
			break;

			case 11:
			pila_origen = pila_A2;
			break;

			case 12:
			pila_origen = pila_A3;
			break;

			case 13:
			pila_origen = pila_A4;
			break;

			case 20:
			pila_origen = pila_Reserva;
			break;

			default:
			System.out.println("¡¡Ingrese una pila válida!!\nEl origen puede ser cualquier pila excepto la pila de reserva");
			break;

		}

		switch(destino)
		{
			case 1:
			pila_destino = pila1;
			break;

			case 2:
			pila_destino = pila2;
			break;

			case 3:
			pila_destino = pila3;
			break;

			case 4:
			pila_destino = pila4;
			break;

			case 5:
			pila_destino = pila5;
			break;

			case 6:
			pila_destino = pila6;
			break;

			case 7:
			pila_destino = pila7;
			break;

			case 10:
			pila_destino = pila_A1;
			break;

			case 11:
			pila_destino = pila_A2;
			break;

			case 12:
			pila_destino = pila_A3;
			break;

			case 13:
			pila_destino = pila_A4;
			break;

			default:
			System.out.println("¡¡Ingrese una pila válida!!\nEl destino puede ser cualquier pila excepto la pila de reserva descubierta");
			break;

		}
	}

	/**
	* 	Este metodo verifica si la carta esta oculta, si lo esta, no se puede mover la carta. Regresa false y se trabaja en la funcion que la llama
	*	@return Boolean
	*/
	public Boolean checarOculta()
	{	
		if (numero_mazo_origen == 20)
			{
				System.out.println("");
			}
		else
		{
			if (pila_origen.get(carta_a_mover).oculta)
			{
				System.out.println("No puedes mover esa carta por que no esta descubierta\n");
				return false;
			}else
			{
				return true;	
			}
		}
		return true;
	}

	/**
	*	Este metodo mueve la carta una vez que todo ya ha sido comprobado
	*	@return Boolean
	*/
	public Boolean moverCarta()
	{
		Iterator<Carta> Iterador_Move;								/*-> Se crea un iterador*/
		Carta elemento;												/*-> Elemento de tipo Carta para manejar el iterador*/
		Iterador_Move = pila_origen.iterator();						/*-> Asignamos al iterador la pila origen (Por esto se hice el selector de pilas)*/

		if (carta_a_mover < 0)
		{
			System.out.println("El indice no puede ser 0!!");		/*-> Si el usuario ingreso el indice 0, avisa y se sale*/
			return false;
		}
			while(Iterador_Move.hasNext())							/*-> Mientras que el iterador tenga otro elemento*/
				{
					elemento = Iterador_Move.next();				/*-> Se lo asiga a elemento*/ 
					if (pila_origen.indexOf(elemento) >= (carta_a_mover)) /*-> Comprueba cual fue la carta que quizo mover, por si esta debajo de otras, si esta, mueve el grupo de cartas*/
					{
						//System.out.println("Se movio el index "+ (pila_origen.indexOf(elemento)+1));
						pila_destino.add(pila_origen.get(pila_origen.indexOf(elemento)));
					}
					else
					{
						//System.out.println("Se copio el index "+ (pila_origen.indexOf(elemento)+1));
						cartas_a_obtener.add(elemento);				/*-> Las cartas que no cumplen la condicion se guardan en este arreglo, para su manejo posterior (en eliminar carta)*/
					}	
				}
			return true;											/*-> Si todo salio bien, regresa true*/
	}

	/**
	* 	Este metodo elimina las cartas que se movieron a otras pilas
	*/
	public void eliminarCarta()				
	{
		pila_origen.retainAll(cartas_a_obtener);					/*-> La pila origen solo se quedara las cartas que estan en la pila cartas_a_obtener, que son las que no se movieron*/
		cartas_a_obtener.clear();									/*-> Se limpia el arrayList*/
	}

	/**
	* 	Este metodo selecciona el numero de la pila origen, destino ynumero de carta a mover
	*	se guardan en las variables globales que usan las demas funciones, como en selector_pila
	*	@ param numero_mazo_origenTemp Numero del mazo origen
	*	@ param numero_mazo_destinoTemp Numero del mazo destino
	*	@ param carta_a_moverTemp Numero de la carta a mover
	*/
	private void setPilaOrigenDestino(String numero_mazo_origenTemp, String numero_mazo_destinoTemp, String carta_a_moverTemp)
	{
		switch (carta_a_moverTemp)
		{
			case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9": 
			carta_a_mover = Integer.parseInt(carta_a_moverTemp)-1;
			break; 

			case "a": case "A": 
			carta_a_mover= 10;
			break;

			case "b": case "B": 
			carta_a_mover= 11;
			break;

			case "c": case "C": 
			carta_a_mover= 12;
			break;

			case "d": case "D": 
			carta_a_mover= 13;
			break;

			default:
			carta_a_mover=0;
			break;
		}

		switch (numero_mazo_origenTemp)
		{
			case "1": case "2": case "3": case "4": case "5": case "6": case "7": 
			numero_mazo_origen = Integer.parseInt(numero_mazo_origenTemp);
			break; 

			case "a": case "A": 
			numero_mazo_origen= 10;
			break;

			case "b":case "B": 
			numero_mazo_origen= 11;
			break;

			case "c": case "C": 
			numero_mazo_origen= 12;
			break;

			case "d": case "D": 
			numero_mazo_origen= 13;
			break;

			case "r": case "R": 
			numero_mazo_origen= 20;
			break;
		}

		switch (numero_mazo_destinoTemp)
		{
			case "1": case "2": case "3": case "4": case "5":  case "6":  case "7":
			numero_mazo_destino = Integer.parseInt(numero_mazo_destinoTemp);
			
			break; 

			case "a": case "A": 
			numero_mazo_destino= 10;
			break;

			case "b": case "B": 
			numero_mazo_destino= 11;
			break;

			case "c": case "C": 
			numero_mazo_destino= 12;
			break;

			case "d": case "D": 
			numero_mazo_destino= 13;
			break;

			case "r": case "R": 
			numero_mazo_destino= 20;
			break;
		}
	}

	/**
	*	Metodo que checa si la pila origen esta vacia, si lo esta, te inica que no se puede usar y regresa false
	*	Se sale de la funcion que la llama, en este caso main, y ahi vuelve a pedir la juagada
	*/
	private Boolean checarSiVacia()
	{	
		try
		{
			if (pila_origen.isEmpty())
			{
				System.out.print("No se puede usar la pila [" + jugadaP.substring(0,1) +"] esta vacia");
				return false;
			}
			else
			{
				return true;
			}

		}catch (Exception e)
		{ 
			System.out.println("");
		}
		return true;
	}

	/*
	*	Metodo que simula que se pidio otra carta de la pila reserva
	*/
	public void nextReserva()
	{
		Carta elemento;
		elemento = pila_Reserva.get(pila_Reserva.size()-1);				/*-> Agarra el ultimo elemento que tenia*/
		elemento.oculta = true;											/*-> Le pone el atributo oculta, si no se moveria pero descubierta*/
		long seed = System.nanoTime();
		Collections.shuffle(pila_Reserva, new Random(seed));
	}

	/**
	* Metodo que imprime el caracter que necesitamos para la baraja, A, J, Q, K, y los numeros
	* @param elementoNumero	Numero a evaluar
	*/
	private String cambiarNumcarta(int elementoNumero)
	{
		switch (elementoNumero)
		{
			case 1:
			return "A";

			case 2:
			return "2";

			case 3:
			return "3";

			case 4:
			return "4";

			case 5:
			return "5";

			case 6:
			return "6";

			case 7:
			return "7";

			case 8:
			return "8";

			case 9:
			return "9";

			case 10:
			return "10";

			case 11:
			return "J";

			case 12:
			return "Q";

			case 13:
			return "K";

		}
		return "K";
	}

	/**
	*	Metodo que verifica si has ganado
	*	Como ya se manejo que las cartas no pueden ingresar en otro  modo distinto al preestablecido
	*	solo basta con verificar que existan 13 cartas en cada pila de aces.
	*/
	protected void ganar()
	{
		if (pila_A1.size() == 13 && pila_A2.size() == 13 && pila_A3.size() == 13 && pila_A4.size() == 13)
		{	
				System.out.println("Ha ganado el juego!!!!");
				System.exit(0);
		}
	}

	/**
	*	Seccion de trucos, metodo que vuelve a todas las cartas visibles
	*/
	public void allVisible()
	{
		oculta= false;
		System.out.println("Truco activado!! Se han descubierto todas las cartas");
	}
}		