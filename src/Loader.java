import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Loader {
    CPU cpu;
    public Loader(CPU cpu, String s19) {
        this.cpu = cpu;
        //load file into memory!

        try (Scanner sc = new Scanner(new File(s19))) {
            // read line by line
            while (sc.hasNext()) {
                String line = sc.nextLine();
                System.out.println(line);

                // check each line for s1
                if (line.charAt(1) == '1') {
                    // read length of data
                    int length = Integer.parseInt(line.substring(2, 4), 16);
                    System.out.println("length: " + length);
                    //read address where data goes
                    int address = Integer.parseInt(line.substring(4, 8), 16);
                    System.out.println(address);
                    //write data to memory
                    loadToRam(address, line.substring(8, line.length() -2));

                } else if (line.charAt(1) == '9') {
                    // set PC to location?
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not find S19 file: " + s19);
        }


    }

    private void loadToRam(int address, String line){
        int count = 0;
        for (int i = 0; i < line.length(); i = i + 2){
            int operand = Integer.parseInt(line.substring(i, i + 2), 16);
            System.out.println("address: " + address + "operand: " + operand);
            cpu.memory.write((address + count) & 0xFFFF, (byte) operand);
            count++;
        }
    }

}
