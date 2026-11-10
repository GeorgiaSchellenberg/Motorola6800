public class MemoryManager {
    // for handling addresses
    byte[] memory;

    public MemoryManager(){
        this.memory = new byte[65536];
    }

    public int readAddress(int address){
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
        return 0;
    }

    // read
    public int read(int address){
        int addressSpace = readAddress(address);
        byte data;
        if (addressSpace == 0){
            // RAM
            if (address <= 0x007F){
                data = memory[address];
            } else {
                // return dummy value
                return 0x00FF;
            }
        } else if (addressSpace == 1){
            // ROM
            data = memory[address];
        } else if (addressSpace == 2){
            //PIA
        } else if (addressSpace == 3){
            //ACIA
        }
        return (int) data;
    }



    // write to ram

    //

}
