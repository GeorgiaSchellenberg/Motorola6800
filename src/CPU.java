public class CPU {
    // registers
    int accumulatorA;
    int accumulatorB;

    int indexRegister;
    int stackPointer;
    int programCounter;

    ConditionCodeRegister ccr;
    MemoryManager memory;

    public CPU(){
        // initialize
        this.accumulatorA = 0;
        this.accumulatorB = 0;


        this.indexRegister = 0;
        this.stackPointer = 0;
        this.programCounter = 0;

        ccr = new ConditionCodeRegister();
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
        int res = accumulatorA | accumulatorB;
        // trim the result
        res = res & 0xFF;
        //check HalfCarry
        if (((accumulatorA & 0x04) & (accumulatorB & 0x04)) != 0){
            ccr.setHalfCarry(true);
        }
        else if (((accumulatorB & 0x04) & (res & 0x04)) != 0){
            ccr.setHalfCarry(true);
        } else if (((accumulatorA & 0x04) & (res & 0x04)) != 0){
            ccr.setHalfCarry(true);
        } else {
            ccr.setHalfCarry(false);
        }
        //check Negative
        ccr.setNegative((res & 0x80) != 0);
        //check Zero
        ccr.setZero((res & 0xFF) == 0);
        //check Overflow

        //chek Carry

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

        // set if it was set before the operation
        ccr.setCarry((accumulatorA & 0x80) != 0);
        // put result back in accumulator
        accumulatorA = res & 0xFF;

        // check negative
        ccr.setNegative((res & 0x80) != 0);
        // check zero
        ccr.setZero((res & 0xFF) == 0);
        // check overflow
        if ((ccr.getNegative() && !ccr.getCarry()) ||(!ccr.getNegative() && ccr.getCarry())){
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }

    }
    // accumulator B
    public void ASLB(){
        int res = accumulatorB << 1;

        // set if it was set before the operation
        ccr.setCarry((accumulatorB & 0x80) != 0);
        // put result back in accumulator
        accumulatorB = res & 0xFF;

        // check negative
        ccr.setNegative((res & 0x80) != 0);
        // check zero
        ccr.setZero((res & 0xFF) == 0);
        // check overflow
        if ((ccr.getNegative() && !ccr.getCarry()) ||(!ccr.getNegative() && ccr.getCarry())){
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }
    }
    //extended addressing
    public void ASL_EXT(){
        int upperAddress = fetch();
        int lowerAddress = fetch();
        //combine 2 8-bit addresses
        int address = (upperAddress << 8) | lowerAddress;
        // get value to be shifted
        int operand = memory.read(address);
        // shift one spot into res
        int res = operand << 1;
        // set if it was set before the operation
        ccr.setCarry((operand & 0x80) != 0);
        // cap res @ 8-bits
        res = res & 0xFF;

        // check negative
        ccr.setNegative((res & 0x80) != 0);
        // check zero
        ccr.setZero((res & 0xFF) == 0);
        // check overflow
        if ((ccr.getNegative() && !ccr.getCarry()) ||(!ccr.getNegative() && ccr.getCarry())){
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }

        // write back
        memory.write(address, (byte) (res & 0xFF));
    }
    //indexed addressing
    public void ASL_IND(){
        int address = fetch() + indexRegister;

        int operand = memory.read(address);
        // shift one spot into res
        int res = operand << 1;
        // set if it was set before the operation
        ccr.setCarry((operand & 0x80) != 0);
        // cap res @ 8-bits
        res = res & 0xFF;

        // check negative
        ccr.setNegative((res & 0x80) != 0);
        // check zero
        ccr.setZero((res & 0xFF) == 0);
        // check overflow
        if ((ccr.getNegative() && !ccr.getCarry()) ||(!ccr.getNegative() && ccr.getCarry())){
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }

        // write back
        memory.write(address, (byte) (res & 0xFF));
    }
    //Arithmetic Shift Right
    //accumulator A
    public void ASRA(){
    }
    public void ASRB(){
    }
    public void ASR_EXT(){
    }
    public void ASR_IND(){
    }
    //Branch if Carry Clear
    public void BCC(){
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte) offset;

        // only branch if C is clear
        if (!ccr.getCarry()){
            programCounter = programCounter + signedOffset;

        }
    }
    //Branch if Carry Set
    public void BCS(){
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte) offset;

        // only branch if C is set
        if (ccr.getCarry()){
            programCounter = programCounter + signedOffset;

        }
    }
    //Branch if Equal
    public void BEQ(){
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte) offset;

        // only if Z is set
        if (ccr.getZero()){
            programCounter = programCounter + signedOffset;
        }
    }
    //Branch if Greater than or Equal to Zero
    public void BGE(){
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte)offset;
        // check if N & V OR !N & !v
        if ((ccr.getNegative() && ccr.getOverflow()) || (!ccr.getNegative() && !ccr.getOverflow())){
            programCounter = programCounter + signedOffset;
        }
    }
}
