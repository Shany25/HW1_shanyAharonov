package model;

/**
 * Represents a file with a name and type.
 * Can be used to attach files to messages.
 */
public class File {
    /** The name of the file. */
    private String fileName;
    /** The type of the file. */
    private String fileType;

    // ======= Getters and Setters ======= //
    /**
     * Gets the name of the file.
     * 
     * @return The file name as a string.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the type of the file.
     * 
     * @return The file type as a string.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Sets the name of the file.
     * Validates that the name is not null or empty.
     * 
     * @param fileName The name to set for the file.
     * @throws IllegalArgumentException If the name is null or empty.
     */
    public void setFileName(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name cannot be null or blank");
        }
        this.fileName = fileName.trim();
    }

    /**
     * Sets the type of the file.
     * Validates that the type is not null or empty.
     * 
     * @param fileType The type to set for the file.
     * @throws IllegalArgumentException If the type is null or empty.
     */
    public void setFileType(String fileType) {
        if (fileType == null || fileType.isBlank()) {
            throw new IllegalArgumentException("File type cannot be null or blank");
        }
        this.fileType = fileType.trim();
    }

    // ======= Constructors ======= //
    /**
     * Default constructor for the File class.
     * Initializes the file with default values.
     */
    public File() {
    }

    /**
     * Constructs a File with name and type.
     * 
     * @param fileName The name of the file.
     * @param fileType The type of the file.
     * @throws IllegalArgumentException If the name or type is invalid.
     */
    public File(String fileName, String fileType) {
        setFileName(fileName);
        setFileType(fileType);
    }

    /**
     * Returns a string representation of the file, including its name and type.
     * 
     * @return A string describing the file.
     */
    @Override
    public String toString() {
        return "File {name='" + fileName + "', type='" + fileType + "'}";
    }

    /**
     * Compares this File object with another object.
     * Two File objects are considered equal if both their file names and file types
     * are equal, ignoring case differences.
     * 
     * @param o The object to compare with this File.
     * @return true if the given object is a File with the same name and type, false
     *         otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof File f))
            return false;
        if (this.fileName == null || this.fileType == null
                || f.fileName == null || f.fileType == null) {
            return false;
        }
        return this.fileName.equalsIgnoreCase(f.fileName)
                && this.fileType.equalsIgnoreCase(f.fileType);
    }

    /**
     * Generates a hash code for this File object based on its file name and file
     * type.
     * The hash code is computed using lowercase versions of the name and type to
     * maintain consistency with the equals method.
     * 
     * @return The hash code value for this File.
     */
    @Override
    public int hashCode() {
        String n = (fileName == null) ? "" : fileName.toLowerCase();
        String t = (fileType == null) ? "" : fileType.toLowerCase();
        return java.util.Objects.hash(n, t);
    }
}
