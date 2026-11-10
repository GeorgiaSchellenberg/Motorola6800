public class CPU {
    // registers
    int accumulatorA;
    int accumulatorB;

    int indexRegister;
    int stackPointer;
    int programCounter;

    ConditionCodeRegister ccr;
    MemoryManager memory;

    public CPU() {
        // initialize
        this.accumulatorA = 0;
        this.accumulatorB = 0;


        this.indexRegister = 0;
        this.stackPointer = 0;
        this.programCounter = 0;

        ccr = new ConditionCodeRegister();
        memory = new MemoryManager();
    }

    //fetch - grabs next byte from memory and increments PC
    public int fetch() {
        // read instruction from memory
        int data = memory.read(programCounter);
        // increment program counter
        programCounter++;
        return data;
    }
    //decode

    //execute

    //add accumulator B to accumulator A
    public void ABA() {
        int res = accumulatorA + accumulatorB;
        // trim the result
        res = res & 0xFF;
        //check HalfCarry
        if (((accumulatorA & 0x08) & (accumulatorB & 0x08)) != 0) {
            ccr.setHalfCarry(true);
        } else if (((accumulatorB & 0x08) & (res & 0x08)) != 0) {
            ccr.setHalfCarry(true);
        } else if (((accumulatorA & 0x08) & (res & 0x08)) != 0) {
            ccr.setHalfCarry(true);
        } else {
            ccr.setHalfCarry(false);
        }
        //check Negative
        ccr.setNegative((res & 0x80) != 0);
        //check Zero
        ccr.setZero((res & 0xFF) == 0);
        //check Overflow
        //if (((accumulatorA & 0x08) == 1) & ((accumulatorB & 0x08) == 1) & ((res & 0x08) == 0){

        //}
        //chek Carry

    }

    //add with carr
    public void ADC() {

    }

    //add without carry
    public void ADD() {

    }

    //logical AND
    //accumulator A
    public void ANDA_IMM() {
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
    public void ANDA_DIR() {
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
    public void ANDA_EXT() {
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
    public void ANDA_IND() {
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
    public void ANDB_imm() {
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
    public void ANDB_DIR() {
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
    public void ANDB_EXT() {
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
    public void ANDB_IND() {
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
    public void ASLA() {
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
        if ((ccr.getNegative() && !ccr.getCarry()) || (!ccr.getNegative() && ccr.getCarry())) {
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }

    }

    // accumulator B
    public void ASLB() {
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
        if ((ccr.getNegative() && !ccr.getCarry()) || (!ccr.getNegative() && ccr.getCarry())) {
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }
    }

    //extended addressing
    public void ASL_EXT() {
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
        if ((ccr.getNegative() && !ccr.getCarry()) || (!ccr.getNegative() && ccr.getCarry())) {
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }

        // write back
        memory.write(address, (byte) (res & 0xFF));
    }

    //indexed addressing
    public void ASL_IND() {
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
        if ((ccr.getNegative() && !ccr.getCarry()) || (!ccr.getNegative() && ccr.getCarry())) {
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }

        // write back
        memory.write(address, (byte) (res & 0xFF));
    }

    //Arithmetic Shift Right
    //accumulator A
    public void ASRA() {
    }

    public void ASRB() {
    }

    public void ASR_EXT() {
    }

    public void ASR_IND() {
    }

    //Branch if Carry Clear
    public void BCC() {
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte) offset;

        // only branch if C is clear
        if (!ccr.getCarry()) {
            programCounter = programCounter + signedOffset;

        }
    }

    //Branch if Carry Set
    public void BCS() {
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte) offset;

        // only branch if C is set
        if (ccr.getCarry()) {
            programCounter = programCounter + signedOffset;

        }
    }

    //Branch if Equal
    public void BEQ() {
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte) offset;

        // only if Z is set
        if (ccr.getZero()) {
            programCounter = programCounter + signedOffset;
        }
    }

    //Branch if Greater than or Equal to Zero
    public void BGE() {
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte) offset;
        // check if N & V OR !N & !v
        if ((ccr.getNegative() && ccr.getOverflow()) || (!ccr.getNegative() && !ccr.getOverflow())) {
            programCounter = programCounter + signedOffset;
        }
    }

    //Branch if Greater than Zero
    public void BGT() {
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte) offset;
        // if (!Z & N & V) OR (!N & !V)
        if ((!ccr.getZero() & (ccr.getNegative() & ccr.getOverflow())) | (!ccr.getNegative() & !ccr.getOverflow())) {
            programCounter = programCounter + signedOffset;
        }
    }

    //Branch if Higher
    public void BHI() {
        int offset = fetch();
        byte signedOffset = (byte) offset;

        if (!ccr.getCarry() & !ccr.getZero()) {
            programCounter = programCounter + signedOffset;
        }
    }

    //Bit Test
    //Accumulator A
    //immediate
    public void BITA_IMM() {
        //fetch operand
        int operand = fetch();

        //don't update accumulator or memory
        int res = accumulatorA & operand;
        res = res & 0xFF;

        ccr.setCarry((res & 0x80) != 0);
        ccr.setZero((res & 0xFF) == 0);
        ccr.setOverflow(false);
    }

    //direct
    public void BITA_DIR() {
        int address = fetch();
        int operand = memory.read(address);

        int res = accumulatorA & operand;
        res = res & 0xFF;

        ccr.setCarry((res & 0x80) != 0);
        ccr.setZero((res & 0xFF) == 0);
        ccr.setOverflow(false);
    }

    //extended
    public void BITA_EXT() {
        int upperAddress = fetch();
        int lowerAddress = fetch();
        //combine 2 8-bit addresses
        int address = (upperAddress << 8) | lowerAddress;
        // get value to be shifted
        int operand = memory.read(address);

        int res = accumulatorA & operand;
        res = res & 0xFF;

        ccr.setCarry((res & 0x80) != 0);
        ccr.setZero((res & 0xFF) == 0);
        ccr.setOverflow(false);
    }

    //indexed
    public void BITA_IND() {
        int address = fetch() + indexRegister;
        int operand = memory.read(address);

        int res = accumulatorA & operand;
        res = res & 0xFF;

        ccr.setCarry((res & 0x80) != 0);
        ccr.setZero((res & 0xFF) == 0);
        ccr.setOverflow(false);
    }

    //Accumulator B
    //immediate
    public void BITB_IMM() {
        //fetch operand
        int operand = fetch();

        //don't update accumulator or memory
        int res = accumulatorB & operand;
        res = res & 0xFF;

        ccr.setCarry((res & 0x80) != 0);
        ccr.setZero((res & 0xFF) == 0);
        ccr.setOverflow(false);
    }

    //direct
    public void BITB_DIR() {
        int address = fetch();
        int operand = memory.read(address);

        int res = accumulatorB & operand;
        res = res & 0xFF;

        ccr.setCarry((res & 0x80) != 0);
        ccr.setZero((res & 0xFF) == 0);
        ccr.setOverflow(false);
    }

    //extended
    public void BITB_EXT() {
        int upperAddress = fetch();
        int lowerAddress = fetch();
        //combine 2 8-bit addresses
        int address = (upperAddress << 8) | lowerAddress;
        // get value to be shifted
        int operand = memory.read(address);

        int res = accumulatorB & operand;
        res = res & 0xFF;

        ccr.setCarry((res & 0x80) != 0);
        ccr.setZero((res & 0xFF) == 0);
        ccr.setOverflow(false);
    }

    //indexed
    public void BITB_IND() {
        int address = fetch() + indexRegister;
        int operand = memory.read(address);

        int res = accumulatorB & operand;
        res = res & 0xFF;

        ccr.setCarry((res & 0x80) != 0);
        ccr.setZero((res & 0xFF) == 0);
        ccr.setOverflow(false);
    }

    //clear carry
    public void CLC() {
        ccr.setCarry(false);
    }

    //clear interrupt
    public void CLI() {
        ccr.setInterrupt(false);
    }

    //clear
    //accumulator A
    public void CLRA() {
        accumulatorA = 0;
        ccr.setNegative(false);
        ccr.setZero(true);
        ccr.setOverflow(false);
        ccr.setCarry(false);
    }

    //accumulator B
    public void CLRB() {
        accumulatorB = 0;
        ccr.setNegative(false);
        ccr.setZero(true);
        ccr.setOverflow(false);
        ccr.setCarry(false);
    }

    //extended
    public void CLR_EXT() {
        int upperAddress = fetch();
        int lowerAddress = fetch();
        //combine 2 8-bit addresses
        int address = (upperAddress << 8) | lowerAddress;
        // write to address
        memory.write(address, (byte) 0);

        ccr.setNegative(false);
        ccr.setZero(true);
        ccr.setOverflow(false);
        ccr.setCarry(false);
    }

    //indexed
    public void CLR_IND() {
        int address = fetch() + indexRegister;
        memory.write(address, (byte) 0);

        ccr.setNegative(false);
        ccr.setZero(true);
        ccr.setOverflow(false);
        ccr.setCarry(false);
    }

    //Clear Two's Complement Overflow Bit
    public void CLV() {
        ccr.setOverflow(false);
    }
    //Compare
    //accumulator A
    //immediate
    //direct
    //extended
    //indexed
    //accumulator B
    //immediate
    //direct
    //extended
    //indexed
    //Complement
    //Compare Index Register
    //Decimal Adjust ACCA
    //Decrement
    //accumulatorA
    public void DECA(){
        int res = accumulatorA - 1;
        ccr.setNegative((accumulatorA & 0x80) != 0);
        ccr.setZero((accumulatorA & 0xFF) == 0);
        if (((accumulatorA & 0x80) != 0x80)){
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }
        accumulatorA = res;
    }
    //accumulatorB
    public void DECB(){
        int res = accumulatorB - 1;
        ccr.setNegative((accumulatorB & 0x80) != 0);
        ccr.setZero((accumulatorB & 0xFF) == 0);
        if (((accumulatorB & 0x80) != 0x80)){
            ccr.setOverflow(true);
        } else {
            ccr.setOverflow(false);
        }
        accumulatorB = res;
    }
    //extended
    public void DEC_EXT(){

    }
    //indexed
    public void DEC_IND(){

    }
    //Decrement Stack Pointer
    public void DES(){
        stackPointer = stackPointer - 4; // a nybble?
    }
    //Decrement Index Register
    public void DEX(){
        indexRegister = indexRegister - 1; // should be 4?

        // update
    }
    //Exclusive OR
    public void EOR(){

    }
    //Increment
    //accumulator A
    public void INCA(){

    }
    //accumulator B
    public void INCB(){

    }
    //extended
    public void INC_EXT(){

    }
    //indexed
    public void INC_IND(){

    }
    //Increment Stack Pointer
    public void INS(){
        stackPointer = stackPointer + 1;
    }
    //Increment Index Register
    public void INX(){
        indexRegister = indexRegister + 1;
        // update Z if all 16 bits are 0
    }

}