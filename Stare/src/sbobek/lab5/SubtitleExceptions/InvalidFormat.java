package sbobek.lab5.SubtitleExceptions;

public class InvalidFormat extends Exception {
    public InvalidFormat(String line) {
        super("Invalid format on line ".concat(line));
    }
}
