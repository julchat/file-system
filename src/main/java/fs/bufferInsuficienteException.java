package fs;

public class bufferInsuficienteException extends RuntimeException {
	public bufferInsuficienteException(){
		super("El buffer no tiene suficientes bytes para operar con el archivo, intente con un nuevo buffer o con menos bytes");
	}
}
