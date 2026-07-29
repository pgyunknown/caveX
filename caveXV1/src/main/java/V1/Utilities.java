package V1;

public final class Utilities {
    private Utilities() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static boolean isNull(String text){
        return text == null || text.trim().isEmpty();
    }

}
