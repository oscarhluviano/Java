public class Barajear{
	public void barajarMazo()
	{
		long seed = System.nanoTime();
		Collections.shuffle(mazo, new Random(seed));
	}

}