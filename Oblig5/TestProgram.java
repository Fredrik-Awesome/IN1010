import java.io.File;
import java.io.FileNotFoundException;

class TestProgram {
    public static void main(String[] args) {

        try {
            File f = new File("7.in");
            Labyrint l = null;
            l = l.lesFraFil(f);
            System.out.println(l);
            System.out.println(l.finnUtveiFra(3,1)); //(bredde, høyde)
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil");
        }
    }
}
