
public class Carta 
{
	/*Atributos de la carta*/
	public int numero;					/*-> Numero de la carta, en impresion se maneja con las letras correspondientes*/
	public String color;				/*-> Color de la carta, en la impresion se manejan los colores visuales correspondientes*/
	public Boolean oculta=true;			/*-> Visibilidad de la carta, inicialmente todas estan ocultas*/

	/*Contructor por default de la Carta*/
	public Carta(){}

/**
* Constructor de la carta
* @param numero Numero de la carta
* @param color Color de la carta
* @param palo Palo de la carta
*/
	public Carta (int numero, String color, String palo)
	{
		this.numero = numero;
		this.color = color;
	}
}