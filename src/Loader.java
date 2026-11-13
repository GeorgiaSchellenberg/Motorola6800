import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Loader {

    public Loader(CPU cpu, String s19) {
        //load file into memory!

        try (Scanner sc = new Scanner(new File(s19))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                System.out.println(line);

                // check each line for s1
                if (line.charAt(1) == '1'){
                // read length
                    //read address where data goes
                    //write data to memory

                } else if (line.charAt(1) == '9'){
                // set PC to location?
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not find S19 file: " + s19);
        }

        //2 bytes for address, 4 bytes of data, 1 byte for checksum).


    }
}
