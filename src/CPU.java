public class CPU {
    // registers
    int accumulatorA;
    int accumulatorB;
    ConditionCodeRegister ccr;

    int indexRegister;
    int stackPointer;
    int programCounter;

    MemoryManager memory;

    public CPU(){
        // initialize
        this.accumulatorA = 0;
        this.accumulatorB = 0;
        ccr = new ConditionCodeRegister();

        this.indexRegister = 0;
        this.stackPointer = 0;
        this.programCounter = 0;

        memory = new MemoryManager();
    }

    //fetch
    public int fetch(){
        // read instruction from memory
        int data = memory.read(programCounter);
        // increment program counter
        programCounter++;
        return data;
    }
    //decode

    //execute

    //add accumulator B to accumulator A
    public void ABA(){
        // OP 1B
        int res = accumulatorA + accumulatorB;

    }

    //add with carr
    public void ADC(){

    }

    //add without carry
    public void ADD(){

    }

    //logical AND
    //accumulator A
    public void ANDA_imm(){
        // get immediate value
        int operand = fetch();
        accumulatorA = accumulatorA & operand;

        // check if MSB is set
        ccr.setNegative((accumulatorA & 0x80) != 0);
        // check if all 0
        ccr.setZero((accumulatorA & 0xFF) == 0);
        // reset overflow
        ccr.setOverflow(false);
    }
    //direct addressing
    public void ANDA_DIR(){
        int address = fetch();
        int operand = memory.read(address);

        accumulatorA = accumulatorA & operand;

        // check if MSB is set
        ccr.setNegative((accumulatorA & 0x80) != 0);
        // check if all 0
        ccr.setZero((accumulatorA & 0xFF) == 0);
        // reset overflow
        ccr.setOverflow(false);
    }
    //extended addressing
    public void ANDA_EXT(){
        int upperAddress = fetch();
        int lowerAddress = fetch();

        // shift by 8 to move
        int address = upperAddress << 8 | lowerAddress;
        int operand = memory.read(address);

        accumulatorA = accumulatorA & operand;

        // check if MSB is set
        ccr.setNegative((accumulatorA & 0x80) != 0);
        // check if all 0
        ccr.setZero((accumulatorA & 0xFF) == 0);
        // reset overflow
        ccr.setOverflow(false);
    }
    //indexed addressing
    public void ANDA_IND(){
        int address = fetch() + indexRegister;
        int operand = memory.read(address);

        accumulatorA = accumulatorA & operand;

        // check if MSB is set
        ccr.setNegative((accumulatorA & 0x80) != 0);
        // check if all 0
        ccr.setZero((accumulatorA & 0xFF) == 0);
        // reset overflow
        ccr.setOverflow(false);
    }
    //accumulator B
    //immediate addressing
    public void ANDB_imm(){
        // get immediate value
        int operand = fetch();
        accumulatorB = accumulatorB & operand;

        // check if MSB is set
        ccr.setNegative((accumulatorB & 0x80) != 0);
        // check if all 0
        ccr.setZero((accumulatorB & 0xFF) == 0);
        // reset overflow
        ccr.setOverflow(false);
    }
    //direct addressing
    public void ANDB_DIR(){
        int address = fetch();
        int operand = memory.read(address);

        accumulatorB = accumulatorB & operand;

        // check if MSB is set
        ccr.setNegative((accumulatorB & 0x80) != 0);
        // check if all 0
        ccr.setZero((accumulatorB & 0xFF) == 0);
        // reset overflow
        ccr.setOverflow(false);
    }
    //extended addressing
    public void ANDB_EXT(){
        int upperAddress = fetch();
        int lowerAddress = fetch();

        // shift by 8 to move
        int address = upperAddress << 8 | lowerAddress;
        int operand = memory.read(address);

        accumulatorB = accumulatorB & operand;

        // check if MSB is set
        ccr.setNegative((accumulatorB & 0x80) != 0);
        // check if all 0
        ccr.setZero((accumulatorB & 0xFF) == 0);
        // reset overflow
        ccr.setOverflow(false);
    }
    //indexed addressing
    public void ANDB_IND(){
        int address = fetch() + indexRegister;
        int operand = memory.read(address);

        accumulatorB = accumulatorB & operand;

        // check if MSB is set
        ccr.setNegative((accumulatorB & 0x80) != 0);
        // check if all 0
        ccr.setZero((accumulatorB & 0xFF) == 0);
        // reset overflow
        ccr.setOverflow(false);
    }
    //Arithmetic Shift Left
    //accumulator A
    public void ASLA(){
        int res = accumulatorA << 1;
    }
    public void ASLB(){}
    public void ASLA_EXT(){}
    public void ASLA_IND(){}

}
