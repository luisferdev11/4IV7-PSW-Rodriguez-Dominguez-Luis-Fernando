
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {
    static boolean comilla(String texto) {
        Pattern pattern = Pattern.compile("'");
        Matcher matcher = pattern.matcher(texto);
        boolean matchFound = matcher.find();
        return matchFound;
    }
    
    static boolean numero(String numero) {
    if (numero == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(numero);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
    }
    
    static boolean texto(String texto){
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(texto);
        boolean matchFound = matcher.find();
        return matchFound;
    }
    
    static boolean correo(String correo){
        Pattern pattern = Pattern.compile("@");
        Matcher matcher = pattern.matcher(correo);
        boolean matchFound = matcher.find();
        return matchFound;
    }
    static boolean edad(int edad){
        return edad<1;
    }
}
