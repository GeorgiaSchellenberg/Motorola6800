public class MemoryManager {
    // for handling addresses
    byte[] memory;

    public MemoryManager(){
        this.memory = new byte[65536];
    }


    private int readAddress(int address){
        // 0 = RAM
        // 1 = ROM
        // 2 = PIA
        // 3 = ACIA
        if (address >= 0 && address <= 0x007F){
            return 0;
        } else if (address >= 0XC000 && address <= 0xC3FF){
            return 1;
        } else if (address >= 0x4004 && address <= 0x4007){
            return 2;
        } else if (address >= 0x4008 && address <= 0x4009){
            return 3;
        }
        // un-mappable!
        return -1;
    }

    // read
    public int read(int address){
        // check if address is no longer than 2 ^ 16
        int addressSpace = readAddress(address & 0xFFFF);

        if (addressSpace == 0){
            // RAM
            if (address <= 0x007F){
                // only read if in RAM's address range
                return memory[address] & 0xFF;
            } else {
                // return dummy value if not in the range of ram
                return 0xFF;
            }
        } else if (addressSpace == 1){
            // ROM
            return memory[address] & 0xFF;
        } else if (addressSpace == 2){
            //PIA
            return readPIA(address);
        } else if (addressSpace == 3) {
            //ACIA
            return readACIA(address);
        } else {
            return 0xFF;
        }
    }

    // write to ram or io
    public void write (int address, byte data){
        int addressSpace = readAddress(address);

        if (addressSpace == 1){
            // WRONG CANNOT WRITE TO ROM
            System.out.println("No can do! Don't write to ROM!");
        }

        if (addressSpace == 0){
            // RAM
            memory[address] = data;
        } else if (addressSpace == 2){
            // PIA
            writePIA(address, data);
        } else if (addressSpace == 3){
            // ACIA
            writeACIA(address, data);
        }
    }
// TO FIGURE OUT LATER
    private int readPIA(int address){
        return 0;
    }

    private int readACIA(int address){
        return 0;
    }

    private void writePIA(int address, byte data){

    }

    private void writeACIA(int address, byte data){

    }
}
