package fs;

import java.util.function.Consumer;

public interface SistemasDeArchivos {
	void leerArchivoSync(int bytes);
	void escribirArchivo(int bytes);
	void leerArchivoAsync(int bytes, Consumer<Integer> callback);
	void cerrarArchivo();
}
