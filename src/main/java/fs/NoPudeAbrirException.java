package fs;

public class NoPudeAbrirException extends RuntimeException{
public NoPudeAbrirException (){
  super("Hubo un error al abrir el archivo");
}

}
