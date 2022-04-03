import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    @Test
    void addFile() {
        FileProcessor processor = new FileProcessor();
        processor.addFile("a" , 100);
        processor.addFile("g" , 400);
        processor.addFile("b" , 100 , "d2");
        processor.addFile("c" , 200 , "d1");
        processor.addFile("d" , 400 , "d3");
        processor.addFile("e" , 500 , "d3");
        processor.addFile("f" , 300 , "d4");

        System.out.println(processor.size());
        System.out.println(processor.findTopKCollection(1));
        System.out.println(processor.findTopKCollection(2));
        System.out.println(processor.findTopKCollection(3));
        System.out.println(processor.findTopKCollection(4));
        System.out.println(processor.findTopKCollection(5));

        processor.addFile("h" , 1000 , List.of("d1" , "d4"));
        processor.addCollection("d1", "d4");
        System.out.println(processor.findTopKCollection(4));
        processor.addCollection("d3", "d1");
        System.out.println(processor.findTopKCollection(4));
        processor.addFile("k" , 3000 , "d3");
        System.out.println(processor.findTopKCollection(4));
        processor.addFile("j" , 10000 , "d5");
        processor.addCollection("d5" , "d1");
        System.out.println(processor.findTopKCollection(6));


    }

    @Test
    void findTopKCollection() {
    }
}