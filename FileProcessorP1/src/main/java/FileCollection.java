import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileCollection {

    private final String name;
    private final Set<File> files;
    private final Set<FileCollection> children;
    private final Set<FileCollection> parents;
    private Integer size;

    public FileCollection(String name) {
        this.name = name;
        this.files = new HashSet<>();
        this.children = new HashSet<>();
        this.parents = new HashSet<>();
        this.size = 0;
    }

    public String getName() {
        return name;
    }

    public Set<File> getFiles() {
        return files;
    }

    public Set<FileCollection> getChildren() {
        return children;
    }

    public Set<FileCollection> getParents() {
        return parents;
    }

    public Integer getSize() {
        return size;
    }

    public void incrementSize(Integer sizeOfChild){
        this.size += sizeOfChild;
    }
    public void addFile(File file){
        if(!files.contains(file)){
            if(this.getParents().size() != 0){
                Set<File> thisFile = new HashSet<>();
                thisFile.add(file);
                for(FileCollection parent:this.getParents())
                    incrementParentSize(parent, thisFile);
            }
            this.size += file.getSize();
            files.add(file);
        }
    }

    public void addParent(FileCollection parent){
        this.parents.add(parent);
    }
    private void incrementParentSize(FileCollection parent, Set<File> childFiles){
        if(parent.getParents().size() == 0 ){
            Set<File> filesInParent = new HashSet<>(parent.getFiles());
            filesInParent.addAll(getFilesInChildren(parent));
            childFiles.removeAll(filesInParent);
            parent.incrementSize(childFiles.stream().mapToInt(File::getSize).sum());
            return;
        }

        for(FileCollection p : parent.getParents()){
            incrementParentSize(p,childFiles);
            Set<File> filesInParent = new HashSet<>(parent.getFiles());
            filesInParent.addAll(getFilesInChildren(parent));
            childFiles.removeAll(filesInParent);
            parent.incrementSize(childFiles.stream().mapToInt(File::getSize).sum());
        }
    }
    private List<File> getFilesInChildren(FileCollection child){
        if(child.getChildren().size() == 0){
            return new ArrayList<>(child.getFiles());
        }
        List<File> filesInChildren = new ArrayList<>(child.getFiles());
        for(FileCollection children : child.getChildren()){
            filesInChildren.addAll(getFilesInChildren(children));
        }

        return filesInChildren;
    }

    public FileCollection addCollection(FileCollection child){
        if(!children.contains(child)){
            Set<File> childFiles =  child.getFiles();
            if(this.getParents().size() != 0 ){
                for( FileCollection parent: this.getParents()){
                    incrementParentSize(parent,childFiles);
                }
            }
            size = getSizeofFilesInChildren(child);
            children.add(child);

        }
        return  this;
    }

    private  Integer getSizeofFilesInChildren(FileCollection child){
        Set<File> filesInCollection = new HashSet<>(getFilesInChildren(child));
        filesInCollection.addAll(files);
        return filesInCollection.stream().mapToInt(File::getSize).sum();
    }
}
