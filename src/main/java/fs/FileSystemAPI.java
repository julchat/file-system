package fs;

import java.util.function.Consumer;

public class FileSystemAPI implements SistemasDeArchivos {
LowLevelFileSystem fileSystemPosta;

String ruta;
byte[] buffer;

int bufferStart;
int flujoArchivo;
int bufferEnd;


public FileSystemAPI(String ruta) {
	
	this.ruta = ruta;
	this.buffer = new byte[1000000];
	this.bufferStart = 0;
}

public void cambiarInicioBuffer(int posicion) {
	bufferStart = posicion;
}

public void cambiarBuffer(byte[] buffer, int comienzo) {
	this.buffer = buffer;
	this.bufferStart = comienzo;
}

public void dejarEspacioVacio(int cantidadEspacio) {
	bufferStart = bufferStart + cantidadEspacio;
}

public void leerArchivoSync(int bytes) {
	int bytesLeidos = 0;
	this.prepararFlujoYBuffer(bytes);
	bytesLeidos = fileSystemPosta.syncReadFile(flujoArchivo, buffer, bufferStart, bufferEnd);
	bufferStart += bytesLeidos;
}

public void leerArchivoAsync(int bytes, Consumer<Integer> callback) {
	this.prepararFlujoYBuffer(bytes);
	fileSystemPosta.asyncReadFile(flujoArchivo, buffer, bufferStart, bufferEnd, callback);
	bufferStart += callback.hashCode();
}

public void escribirArchivo(int bytes) {
	this.prepararFlujoYBuffer(bytes);
	fileSystemPosta.syncReadFile(flujoArchivo, buffer, bufferStart, bufferEnd);

}

public void cerrarArchivo() {
	fileSystemPosta.closeFile(flujoArchivo);
	flujoArchivo = -20;
}

public byte[] obtenerBuffer() {
	return buffer;
}

public boolean archivoNoAbierto() {
return (flujoArchivo == -20);
}
	
private void prepararFlujoYBuffer(int bytes){
	this.validarBytes(bytes);
	this.abrirArchivoSiNoEstaAbierto();
	bufferEnd = bufferStart + bytes - 1;
	this.validarLongitudBuffer();
}

private void validarBytes(int bytes) {
	if(bytes<1) {
		throw new CantBytesInvalidaException();
	}
}

private void abrirArchivoSiNoEstaAbierto() {
	if(archivoNoAbierto()){
	flujoArchivo = fileSystemPosta.openFile(ruta);
	if(flujoArchivo < 0) {
	throw new NoPudeAbrirException();
	}
}
}

private void validarLongitudBuffer() {
	if(bufferEnd > buffer.length - 1) {
		throw new bufferInsuficienteException();
	}
}

}


