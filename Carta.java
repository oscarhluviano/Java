/** @author Soto Jiménez Jonathan
 * @version 2.0
*/

/** Esta clase maneja la estructura basica de una carta<br>
*  Para poder trabajar con sus atributos en el mazo de cartas
*/
public class Carta 
{
	/*Atributos de la carta*/
	public int numero;					/*-> Numero de la carta, en impresion se maneja con las letras correspondientes*/
	public String color;				/*-> Color de la carta, en la impresion se manejan los colores visuales correspondientes*/
	public String palo;					/*-> Palo de la carta, en la impresion se maneja la figura correspondiente*/
	public Boolean oculta=true;			/*-> Visibilidad de la carta, inicialmente todas estan ocultas*/

	/*Contructor por default de la Carta*/
	public Carta(){}

/**
* Contructor de la carta
* @param numero Numero de la carta
* @param color Color de la carta
* @param palo Palo de la carta
*/
	public Carta (int numero, String color, String palo)
	{
		this.numero = numero;
		this.color = color;
		this.palo = palo;
	}
}