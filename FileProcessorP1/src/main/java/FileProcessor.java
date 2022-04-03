import java.util.*;
import java.util.stream.Collectors;

public class FileProcessor {

    private final Map<String,File> files;
    private final Map<String, FileCollection> collections;
    private Integer fileSystemSize;

    public FileProcessor() {
        this.files = new HashMap<>();
        this.collections = new HashMap<>();
        this.fileSystemSize = 0;
    }

    public File addFile(String fileName, Integer size){

        File file = files.getOrDefault(fileName, new File(fileName,size));
        if(files.get(fileName) == null)
        {
            fileSystemSize += file.getSize();
            files.put(fileName,file);
        }
        return file;
    }

    public File addFile(String fileName, Integer size, String collection){
        File file = addFile(fileName,size);
        FileCollection target = collections.getOrDefault(collection, new FileCollection(collection));
        target.addFile(file);
        collections.put(collection,target);
        return file;
    };

    public File addFile(String fileName, Integer size, List<String> collections){
        collections.forEach(collection -> addFile(fileName, size, collection));
        return files.get(fileName);
    };

    public FileCollection addCollection(String source, String target){

        FileCollection sourceCollection = collections.getOrDefault(source,new FileCollection(source));
        FileCollection targetCollection = collections.getOrDefault(target, new FileCollection(target));

        targetCollection = targetCollection.addCollection(sourceCollection);
        sourceCollection.addParent(targetCollection);
        return  targetCollection;
    }

    public List<FileCollection> findTopKCollection(Integer k) {
        List<FileCollection> fileCollections = new ArrayList<>(this.collections.values());
        fileCollections.sort(Comparator.comparingInt(FileCollection::getSize).reversed());
        return fileCollections.stream().limit(k).collect(Collectors.toList());
    }

    public Integer size(){
        return fileSystemSize;
    }

}
