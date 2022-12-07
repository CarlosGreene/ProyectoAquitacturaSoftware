package reflection;

public class ClassFinder {

    public Class<?> FindClass(String Fugitive_class) {
        Class<?> fugitive = null;
        try {
            fugitive = Class.forName(Fugitive_class);
        } catch (Exception e) { //Reemplazar por exception personalizada
            e.printStackTrace();
            System.exit(1);
        }
        return fugitive;
    }
}
