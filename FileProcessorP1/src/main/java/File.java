public class File {
    private final String file;
    private final Integer size;

    public File(String file , Integer size) {
        this.file = file;
        this.size = size;
    }

    public String getFile() {
        return file;
    }

    public Integer getSize() {
        return size;
    }

}
