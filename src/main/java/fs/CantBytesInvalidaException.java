package fs;

public class CantBytesInvalidaException extends RuntimeException{
	public CantBytesInvalidaException(){
		super("Ingrese una cantidad de bytes positiva");
	}
}
