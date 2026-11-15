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

    public void step(){
        //retrieve instruction from memory;
        int instruction = fetch();
        //decode and execute
        System.out.println("Instruction: " + instruction);
        decoder(instruction);
    }


    //fetch - grabs next byte from memory and increments PC
    public int fetch() {
        // read instruction from memory
        System.out.println("Program Counter: " + programCounter);
        int data = memory.read(programCounter);
        // increment program counter
        programCounter++;
        return data;
    }
    //decode
    public void decoder(int opcode){
        System.out.println("OPCODE: " + opcode);
        switch(opcode) {
            //:::::::::::::: Add Accumulators
            case 0x1B: ABA();break;
            //:::::::::::::: Add With Carry
            case 0x89: accumulatorA = ADC(accumulatorA, 0);break;
            case 0x99: accumulatorA = ADC(accumulatorA, 1);break;
            case 0xB9: accumulatorA = ADC(accumulatorA,2);break;
            case 0xA9: accumulatorA = ADC(accumulatorA, 3);break;
            case 0xC9: accumulatorB = ADC(accumulatorB, 0);break;
            case 0xD9: accumulatorB = ADC(accumulatorB, 1);break;
            case 0xF9: accumulatorB = ADC(accumulatorB, 2);break;
            case 0xE9: accumulatorB = ADC(accumulatorB, 3);break;
            //:::::::::::::: Add Without Carry
            case 0x8B: accumulatorA = ADD(accumulatorA, 0);break;
            case 0x9B: accumulatorA = ADD(accumulatorA, 1);break;
            case 0xBB: accumulatorA = ADD(accumulatorA, 2);break;
            case 0xAB: accumulatorA = ADD(accumulatorA, 3);break;
            case 0xCB: accumulatorB = ADD(accumulatorB, 0);break;
            case 0xDB: accumulatorB = ADD(accumulatorB, 1);break;
            case 0xFB: accumulatorB = ADD(accumulatorB, 2);break;
            case 0xEB: accumulatorB = ADD(accumulatorB, 3);break;
            //:::::::::::::: Logical And
            case 0x84: accumulatorA = AND(accumulatorA, 0);break;
            case 0x94: accumulatorA = AND(accumulatorA, 1);break;
            case 0xB4: accumulatorA = AND(accumulatorA, 2);break;
            case 0xA4: accumulatorA = AND(accumulatorA, 3);break;
            case 0xC4: accumulatorB = AND(accumulatorB, 0);break;
            case 0xD4: accumulatorB = AND(accumulatorB, 1);break;
            case 0xF4: accumulatorB = AND(accumulatorB, 2);break;
            case 0xE4: accumulatorB = AND(accumulatorB, 3);break;
            //:::::::::::::: Arithmetic Shift Left
            case 0x48: ASL('A');break;
            case 0x58: ASL('B');break;
            case 0x78: ASL(2);break;
            case 0x68: ASL(3);break;
            //:::::::::::::: Arithmetic Shift Right
            case 0x47: ASR('A');break;
            case 0x57: ASR('B');break;
            case 0x77: ASR(2);break;
            case 0x67: ASR(3);break;
            //:::::::::::::: Branch if Carry Clear
            case 0x24: BCC();break;
            //:::::::::::::: Branch if Carry Set
            case 0x25: BCS();break;
            //:::::::::::::: Branch if Equal
            case 0x27: BEQ();break;
            //:::::::::::::: Branch if Greater than or Equal to Zero
            case 0x2C: BGE();break;
            //:::::::::::::: Branch if Greater than Zero
            case 0x2E: BGT();
            //:::::::::::::: Branch if Higher
            case 0x22: BHI();break;
            //:::::::::::::: Bit Test
            case 0x85: BIT('A', 0);break;
            case 0x95: BIT('A', 1);break;
            case 0xB5: BIT('A', 2);break;
            case 0xA5: BIT('A', 3);break;
            case 0xC5: BIT('B', 0);break;
            case 0xD5: BIT('B', 1);break;
            case 0xF5: BIT('B', 2);break;
            case 0xE5: BIT('B', 3);break;
            //:::::::::::::: Branch is Less than or Equal to Zero
            case 0x2F: BLE();break;
            //:::::::::::::: Branch if Lower or Same
            case 0x23: BLS();break;
            //:::::::::::::: Branch if Less than Zero
            case 0x2D: BLT();break;
            //:::::::::::::: Branch if Minus
            case 0x2B: BMI();break;
            //:::::::::::::: Branch if Not Equal
            case 0x26: BNE();break;
            //:::::::::::::: Branch if Plus
            case 0x2A: BPL();break;
            //:::::::::::::: Branch Always
            case 0x20: BRA();break;
            //:::::::::::::: Branch to Subroutine
            case 0x8D: BSR();break;
            //:::::::::::::: Branch if Overflow clear
            case 0x28: BVC();break;
            //:::::::::::::: Branch if Overflow set
            case 0x29: BVS();break;
            //:::::::::::::: Compare Accumulators
            case 0x11: CBA();break;
            //:::::::::::::: Clear Carry
            case 0x0C: CLC();break;
            //:::::::::::::: Clear Interrupt Mask
            case 0x0E: CLI();break;
            //:::::::::::::: Clear
            case 0x4F: CLR('A');break;
            case 0x5F: CLR('B');break;
            case 0x7F: CLR(2);break;
            case 0x6F: CLR(3);break;
            //:::::::::::::: Clear Two's Complement Overflow Bit
            case 0x0A: CLV();break;
            //:::::::::::::: Compare
            case 0x81: CMP('A', 0);break;
            case 0x91: CMP('A', 1);break;
            case 0xB1: CMP('A', 2);break;
            case 0xA1: CMP('A', 3);break;
            case 0xC1: CMP('B', 0);break;
            case 0xD1: CMP('B', 1);break;
            case 0xF1: CMP('B', 2);break;
            case 0xE1: CMP('B', 3);break;
            //:::::::::::::: Complement
            case 0x43: COM('A');break;
            case 0x53: COM('B');break;
            case 0x73: COM(2);break;
            case 0x63: COM(3);break;
            //:::::::::::::: Compare Index Register NOT DONE
            case 0x8C: CPX(0);break;
            case 0x9C: CPX(1);break;
            case 0xBC: CPX(2);break;
            case 0xAC: CPX(3);break;
            //:::::::::::::: Decimal Adjust ACCA NOT DONE
            case 0x19: DAA();break;
            //:::::::::::::: Decrement done
            case 0x4A: DEC('A');break;
            case 0x5A: DEC('B');break;
            case 0x7A: DEC(2);break;
            case 0x6A: DEC(3);break;
            //:::::::::::::: Decrement Stack Pointer
            case 0x34: DES();break;
            //:::::::::::::: Decrement Index Register
            case 0x09: DEX();break;
            //:::::::::::::: Exclusive OR
            case 0x88: EOR('A', 0);break;
            case 0x98: EOR('A', 1);break;
            case 0xB8: EOR('A', 2);break;
            case 0xA8: EOR('A', 3);break;
            case 0xC8: EOR('B', 0);break;
            case 0xD8: EOR('B', 1);break;
            case 0xF8: EOR('B', 2);break;
            case 0xE8: EOR('B', 3);break;
            //:::::::::::::: Increment
            case 0x4C: INC('A');break;
            case 0x5C: INC('B');break;
            case 0x7C: INC(2);break;
            case 0x6C: INC(3);break;
            //:::::::::::::: Increment Stack Pointer
            case 0x31: INS();break;
            //:::::::::::::: Increment Index Register
            case 0x08: INX();break;
            //:::::::::::::: Jump
            case 0x7E: JMP(2);break;
            case 0x6E: JMP(3);break;
            //:::::::::::::: Jump to Subroutine
            case 0xBD: JSR(2);break;
            case 0xAD: JSR(3);break;
            //:::::::::::::: Load Accumulator
            case 0x86: LDA('A', 0);break;
            case 0x96: LDA('A', 1);break;
            case 0xB6: LDA('A', 2);break;
            case 0xA6: LDA('A', 3);break;
            case 0xC6: LDA('B', 0);break;
            case 0xD6: LDA('B', 1);break;
            case 0xF6: LDA('B', 2);break;
            case 0xE6: LDA('B', 3);break;
            //:::::::::::::: Load Stack Pointer
            case 0x8E: LDS(0);break;
            case 0x9E: LDS(1);break;
            case 0xBE: LDS(2);break;
            case 0xAE: LDS(3);break;
            //:::::::::::::: Load Index Register
            case 0xCE: LDX(0);break;
            case 0xDE: LDX(1);break;
            case 0xFE: LDX(2);break;
            case 0xEE: LDX(3);break;
            //:::::::::::::: Logical Shift Right
            case 0x44: LSR('A');break;
            case 0x54: LSR('B');break;
            case 0x74: LSR(2);break;
            case 0x64: LSR(3);break;
            //:::::::::::::: Negate
            case 0x40: NEG('A');break;
            case 0x50: NEG('B');break;
            case 0x70: NEG(2);break;
            case 0x60: NEG(3);break;
            //:::::::::::::: No Operation
            case 0x01: NOP();break;
            //:::::::::::::: Inclusive OR
            case 0x8A: ORA('A', 0);break;
            case 0x9A: ORA('A', 1);break;
            case 0xBA: ORA('A', 2);break;
            case 0xAA: ORA('A', 3);break;
            case 0xCA: ORA('B', 0);break;
            case 0xDA: ORA('B', 1);break;
            case 0xFA: ORA('B', 2);break;
            case 0xEA: ORA('B', 3);break;
            //:::::::::::::: Push Data Onto Stack
            case 0x36: PSH('A');break;
            case 0x37: PSH('B');break;
            //:::::::::::::: Pull Data from Stack
            case 0x32: PUL('A');break;
            case 0x33: PUL('B');break;
            //:::::::::::::: Rotate Left
            case 0x49: ROL('A');break;
            case 0x59: ROL('B');break;
            case 0x79: ROL(2);break;
            case 0x69: ROL(3);break;
            //:::::::::::::: Rotate Right
            case 0x46: ROR('A');break;
            case 0x56: ROR('B');break;
            case 0x76: ROR(2);break;
            case 0x66: ROR(3);break;
            //:::::::::::::: Return from Interrupt
            case 0x3B: RTI();break;
            //:::::::::::::: Return from Subroutine
            case 0x39: RTS();break;
            //:::::::::::::: Subtract Accumulators
            case 0x10: SBA();break;
            //:::::::::::::: Subtract with Carry
//            case 0x82: SBCA_IMM();break;
//            case 0x92: SBCA_DIR();break;
//            case 0xB2: SBCA_EXT();break;
//            case 0xA2: SBCA_IND();break;
//            case 0xC2: SBCB_IMM();break;
//            case 0xD2: SBCB_DIR();break;
//            case 0xF2: SBCB_EXT();break;
//            case 0xE2: SBCB_IND();break;
            //:::::::::::::: Set Carry
            case 0x0D: SEC();break;
            //:::::::::::::: Set Interrupt Mask
            case 0x0F: SEI();break;
            //:::::::::::::: Set Two's Complement Overflow Bit
            case 0x0B: SEV();break;
            //:::::::::::::: Store Accumulator
//            case 0x97: STAA_DIR();break;
//            case 0xB7: STAA_EXT();break;
//            case 0xA7: STAA_IND();break;
//            case 0xD7: STAB_DIR();break;
//            case 0xF7: STAB_EXT();break;
//            case 0xE7: STAB_IND();break;
            //:::::::::::::: Store Stack Pointer
            case 0x9F: STS_DIR();break;
            case 0xBF: STS_EXT();break;
            case 0xAF: STS_IND();break;
            //:::::::::::::: STX
            case 0xDF: STX_DIR();break;
            case 0xFF: STX_EXT();break;
            case 0xEF: STX_IND();break;
            //:::::::::::::: Subtract
//            case 0x80: SUBA_IMM();break;
//            case 0x90: SUBA_DIR();break;
//            case 0xB0: SUBA_EXT();break;
//            case 0xA0: SUBA_IND();break;
//            case 0xC0: SUBB_IMM();break;
//            case 0xD0: SUBB_DIR();break;
//            case 0xF0: SUBB_EXT();break;
//            case 0xE0: SUBB_IND();break;
            //:::::::::::::: Software Interrupt
            case 0x3F: SWI();break;
            //:::::::::::::: Transfer from Accumulator A to Accumulator B
            case 0x16: TAB();break;
            //:::::::::::::: Transfer from Accumulator A to Processor CCR
            case 0x06: TAP();break;
            //:::::::::::::: Transfer from Accumulator B to Accumulator A
            case 0x17: TBA();break;
            //:::::::::::::: Transfer from Processor CCR to Accumulator A
            case 0x07: TPA();break;
            //:::::::::::::: Test
            case 0x4D: TSTA();break;
            case 0x5D: TSTB();break;
            case 0x7D: TST_EXT();break;
            case 0x6D: TST_IND();break;
            //:::::::::::::: Transfer from Stack Pointer to Index Register
            case 0x30: TSX();break;
            //:::::::::::::: transfer from Index Register to Stack Pointer
            case 0x35: TXS();break;
            //:::::::::::::: Wait for Interrupt
            case 0x3E: WAI();break;
        }
    }

    private int getAddress(int addressingMode){
        int address = 0;
        // check addressing mode
        if (addressingMode == 1){
            //direct
            address = fetch();
        } else if (addressingMode == 2){
            //extended
            int upperAddress = fetch();
            int lowerAddress = fetch();

            address = ((upperAddress << 8) | lowerAddress) & 0xFFFF;

        } else if (addressingMode == 3){
            //indexed
            address = (indexRegister + fetch()) & 0xFFFF;
        }
        return address;
    }


    //add accumulator B to accumulator A
    public void ABA() {
        int res = accumulatorA + accumulatorB;
        // trim the result
        res = res & 0xFF;
        //check HalfCarry
        boolean A3 = (accumulatorA & 0x08) != 0;
        boolean B3 = (accumulatorB & 0x08) != 0;
        boolean R3 = (res & 0x08) != 0;

        ccr.setHalfCarry((A3 && B3) || (B3 && !R3) || (!R3 && A3));
        //check Negative
        ccr.setNegative((res & 0x80) != 0);
        //check Zero
        ccr.setZero(res == 0);
        //check Overflow
        boolean A7 = (accumulatorA & 0x80) != 0;
        boolean B7 = (accumulatorB & 0x80) != 0;
        boolean R7 = (res & 0x80) != 0;
        ccr.setOverflow((A7 && B7 && !R7) || (!A7 && !B7 && R7));
        ccr.setCarry((A7 && B7) || (B7 && !R7) || (!R7 && A7));

        accumulatorA = res;
    }

    //Add With Carry
    public int ADC(int accx, int addressingMode){
        int operand;
        if (addressingMode == 0){
            operand = fetch();
        } else {
            operand = memory.read(getAddress(addressingMode));
        }

        int res = accx + operand;
        //if carry true, increment result
        if (ccr.getCarry()) res ++;
        res = res & 0xFF;

        boolean X3 = (accx & 0x08) != 0;
        boolean M3 = (operand & 0x08) != 0;
        boolean R3 = (res & 0x08) != 0;

        //set halfCarry
        ccr.setHalfCarry((X3 & M3) || (M3 & !R3) || (!R3 & X3));
        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);

        boolean X7 = (accx & 0x80) != 0;
        boolean M7 = (operand & 0x80) != 0;
        boolean R7 = (res & 0x80) != 0;
        ccr.setOverflow((X7 & M7 & !R7) || (!X7 & !M7 & R7));
        ccr.setCarry((X7 & M7) || (M7 & !R7) || (!R7 & X7));
        return res;
    }

    //Add Without Carry
    public int ADD(int accx, int addressingMode){
        int operand;
        if (addressingMode == 0){
            operand = fetch();
        } else {
            operand = memory.read(getAddress(addressingMode));
        }
        int res = accx + operand;
        res = res & 0xFF;

        boolean X3 = (accx & 0x08) != 0;
        boolean M3 = (operand & 0x08) != 0;
        boolean R3 = (res & 0x08) != 0;

        //set halfCarry
        ccr.setHalfCarry((X3 & M3) || (M3 & !R3) || (!R3 & X3));
        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);

        boolean X7 = (accx & 0x80) != 0;
        boolean M7 = (operand & 0x80) != 0;
        boolean R7 = (res & 0x80) != 0;
        ccr.setOverflow((X7 & M7 & !R7) || (!X7 & !M7 & R7));
        ccr.setCarry((X7 & M7) || (M7 & !R7) || (!R7 & X7));

        return res;
    }

    //Logical AND
    public int AND(int accx, int addressingMode){
        int operand;
        if (addressingMode == 0){
            operand = fetch();
        } else {
            operand = memory.read(getAddress(addressingMode));
        }

        int res = (accx & operand) & 0xFF;

        // check if MSB is set
        ccr.setNegative((res & 0x80) != 0);
        // check if all 0
        ccr.setZero(res == 0);
        // reset overflow
        ccr.setOverflow(false);

        return res;
    }
    //Arithmetic Shift Left
    public void ASL(int addressingMode){
        int res;
        if (addressingMode == 'A'){
            res = (accumulatorA << 1) & 0xFF;
            ccr.setCarry((accumulatorA & 0x80) != 0);
            accumulatorA = res;
        } else if (addressingMode == 'B'){
            res  = (accumulatorB << 1) & 0xFF;
            ccr.setCarry((accumulatorB & 0x80) != 0);
            accumulatorB = res;
        } else {
            int address = getAddress(addressingMode);
            int operand = memory.read(address);
            ccr.setCarry((operand & 0x80) != 0);
            res = (operand << 1) & 0xFF;
            memory.write(address, (byte)res);
        }

        // check negative
        ccr.setNegative((res & 0x80) != 0);
        // check zero
        ccr.setZero(res == 0);
        // check overflow
        ccr.setOverflow((ccr.getNegative() && !ccr.getCarry()) || (!ccr.getNegative() && ccr.getCarry()));
    }

    //Arithmetic Shift Right
    public void ASR (int addressingMode){
        int res;
        if (addressingMode == 'A'){
            int sign = accumulatorA & 0x80;
            ccr.setCarry((accumulatorA & 0x01) != 0);
            res = ((accumulatorA >> 1) | sign) & 0xFF;
            accumulatorA = res;
        } else if (addressingMode == 'B'){
            int sign = accumulatorB & 0x80;
            ccr.setCarry((accumulatorB & 0x01) != 0);
            res  = ((accumulatorB >> 1) | sign) & 0xFF;
            accumulatorB = res;
        } else {
            int address = getAddress(addressingMode);
            int operand = memory.read(address);
            int sign = operand & 0x80;
            ccr.setCarry((operand & 0x01) != 0);
            res = ((operand >> 1) | sign) & 0xFF;
            memory.write(address, (byte)res);
        }
        // check negative
        ccr.setNegative((res & 0x80) != 0);
        // check zero
        ccr.setZero(res == 0);
        // check overflow
        ccr.setOverflow((ccr.getNegative() && !ccr.getCarry()) || (!ccr.getNegative() && ccr.getCarry()));
    }
    //Branch if Carry Clear
    public void BCC() {
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;

        // only branch if C is clear
        if (!ccr.getCarry()) {
            programCounter = (programCounter + signedOffset) & 0xFFFF;

        }
    }
    //Branch if Carry Set
    public void BCS() {
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;

        // only branch if C is set
        if (ccr.getCarry()) {
            programCounter = (programCounter + signedOffset) & 0xFFFF;

        }
    }
    //Branch if Equal
    public void BEQ() {
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;

        // only if Z is set
        if (ccr.getZero()) {
            programCounter = (programCounter + signedOffset) & 0xFFFF;
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
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Branch if Greater than Zero
    public void BGT() {
        // fetch offset
        int offset = fetch();
        // convert to signed 7-bit integer
        byte signedOffset = (byte) offset;
        // if (!Z & ((N & V) OR (!N & !V))
        if (!ccr.getZero() && ((ccr.getNegative() && ccr.getOverflow()) || (!ccr.getNegative() && !ccr.getOverflow()))) {
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Branch if Higher
    public void BHI() {
        int offset = fetch();
        byte signedOffset = (byte) offset;

        if (!ccr.getCarry() && !ccr.getZero()) {
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Bit Test
    public void BIT(int accx, int addressingMode) {
        int operand;
        int res;
        if (addressingMode == 0){
            //immediate addressing
            operand = fetch();
        } else {
            int address = getAddress(addressingMode);
            operand = memory.read(address);
        }

        if (accx == 'A'){
            res = accumulatorA & operand;

        } else {
            res = accumulatorB & operand;
        }
        res = res & 0xFF;

        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
        ccr.setOverflow(false);

    }
    //Branch if Less than or Equal to Zero
    public void BLE(){
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;

        if (ccr.getZero() || ((ccr.getNegative() && !ccr.getOverflow()) || (!ccr.getNegative() && ccr.getOverflow()))){
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Branch if Lower or Same
    public void BLS(){
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;
        if (ccr.getCarry() || ccr.getZero()){
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Branch if Less than Zero
    public void BLT(){
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;
        if ((ccr.getNegative() && !ccr.getOverflow() || (!ccr.getNegative() && ccr.getOverflow()))){
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Branch if Minus
    public void BMI(){
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;
        if (ccr.getNegative()){
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Branch if Not Equal
    public void BNE(){
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;
        if (!ccr.getZero()){
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Branch is Plus
    public void BPL(){
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;
        if (!ccr.getNegative()){
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Branch Always
    public void BRA(){
        byte offset = (byte) fetch();
        programCounter = (programCounter + offset) & 0xFFFF;
    }
    //Branch to Subroutine
    public void BSR(){

    }
    //Branch if Overflow Clear
    public void BVC(){
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;
        if (!ccr.getOverflow()){
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }

    //Branch if Overflow Set
    public void BVS(){
        // fetch offset
        int offset = fetch();
        // convert to signed integer
        byte signedOffset = (byte) offset;
        if (ccr.getOverflow()){
            programCounter = (programCounter + signedOffset) & 0xFFFF;
        }
    }
    //Compare Accummulators
    public void CBA(){
        int res = accumulatorA - accumulatorB & 0xFF;

        boolean A7 = (accumulatorA & 0x80) != 0;
        boolean B7 = (accumulatorB & 0x80) != 0;
        boolean R7 = (res & 0x80) != 0;

        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
        ccr.setOverflow((A7 && !B7 && !R7) || (!A7 && B7 && R7));
        ccr.setCarry((!A7 && B7) || (B7 && R7) || (R7 & !A7));
    }
    //Clear Carry
    public void CLC() {
        ccr.setCarry(false);
    }
    //clear interrupt
    public void CLI() {
        ccr.setInterrupt(false);
    }
    //clear
    public void CLR(int addressingMode){
        switch (addressingMode){
            case 'A': accumulatorA = 0; break;
            case 'B': accumulatorB = 0; break;
            case 2:
            case 3:
                memory.write(getAddress(addressingMode), (byte)0);
                break;
        }

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
    public void CMP(int accx, int addressingMode){
        int accumulator;
        int operand;
        // fetch operand from memory
        if (addressingMode == 0){
            operand = fetch();
        } else {
            operand = memory.read(getAddress(addressingMode));
        }

        // perform comparison
        if (accx == 'A'){
            accumulator = accumulatorA;
        } else {
            accumulator = accumulatorB;
        }

        int res = accumulator - operand;
        res = res & 0xFF;

        boolean X7 = (accumulator & 0x80) != 0;
        boolean M7 = (operand & 0x80) != 0;
        boolean R7 = (res & 0x80) != 0;

        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
        ccr.setOverflow((X7 && !M7 && !R7) || (!X7 && M7 && R7));
        ccr.setCarry((!X7 && M7) || (M7 && R7) || (R7 && !X7));
    }
    //Complement
    public void COM(int addressingMode){
        int res;
        if (addressingMode == 'A'){
            accumulatorA = ~accumulatorA & 0xFF;
            res = accumulatorA;
        } else if (addressingMode == 'B'){
            accumulatorB = ~accumulatorB & 0xFF;
            res = accumulatorB;
        } else {
            int address = getAddress(addressingMode);
            int operand = memory.read(address);
            operand = ~operand & 0xFF;
            res = operand;
            memory.write(address, (byte) operand);
        }

        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
        ccr.setOverflow(false);
        ccr.setCarry(true);
    }
    //Compare Index Register NOT DONE!
    public void CPX(int addressingMode){
       int ub = 0;
       int lb = 0;
       if (addressingMode == 0){
            ub = fetch();
            lb = fetch();
       } else if (addressingMode == 1){
            int address = fetch();
            ub = memory.read(address);
            lb = memory.read(address + 1);

       } else if (addressingMode == 2){
            int upperAddress = fetch();
            int lowerAddress = fetch();

            int address = ((upperAddress << 8) | lowerAddress) & 0xFFFF;
            ub = memory.read(address) & 0xFF00;
            lb = memory.read(address) & 0x00FF;
       } else if (addressingMode == 3){
            int address = (indexRegister + fetch()) & 0xFFFF;

           ub = memory.read(address) & 0xFF00;
           lb = memory.read(address) & 0x00FF;
       }
       int rh = (indexRegister & 0xFF00) - ub;
       int lh = (indexRegister & 0x00FF) - lb;

       ccr.setNegative((rh & 0x80) != 0);
       ccr.setZero((rh == 0) && (lh == 0));

       boolean RH7 = ((rh & 0x80) != 0);
       boolean IXH7 = ((indexRegister & 0x8000) != 0);
       boolean M7 = ((lh & 0x80) != 0);
       ccr.setOverflow((IXH7 && !M7 && !RH7) || (!IXH7 && M7 && RH7));


    }
    //Decimal Adjust ACCA
    public void DAA(){
        //TODO
    }
    //Decrement
    public void DEC(int addressingMode){
        int res = 0;
        if (addressingMode == 'A'){
            res = accumulatorA - 1 & 0xFF;
            ccr.setOverflow(accumulatorA == 0x80);
            accumulatorA = res;
        } else if (addressingMode == 'B'){
            res = accumulatorB - 1 & 0xFF;
            ccr.setOverflow(accumulatorB == 0x80);
            accumulatorB = res;
        } else if (addressingMode == 2){
            //extended
            int upperAddress = fetch();
            int lowerAddress = fetch();

            int address = ((upperAddress << 8) | lowerAddress) & 0xFFFF;
            res = memory.read(address);
            ccr.setOverflow(res == 0x80);
            res = res - 1 & 0xFF;
            memory.write(address, (byte) res);

        } else if (addressingMode == 3){
            //indexed
            int address = indexRegister + fetch() & 0xFFFF;
            res = memory.read(address);
            ccr.setOverflow(res == 0x80);
            res = res - 1 & 0xFF;
            memory.write(address, (byte) res);
        }
        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
    }
    //Decrement Stack Pointer
    public void DES(){
        stackPointer = stackPointer - 1 & 0xFFFF;
    }
    //Decrement Index Register
    public void DEX(){
        indexRegister = indexRegister - 1 & 0xFFFF;

        // update
        ccr.setZero(indexRegister == 0);
    }
    //Exclusive OR
    public void EOR(int accx, int addressingMode){
        int operand;
        int res = 0;
        if (addressingMode == 0){
            operand = fetch();
        } else {
            int address = getAddress(addressingMode);
            operand = memory.read(address);
        }
        if (accx == 'A'){
            res = accumulatorA ^ operand;
            accumulatorA = res;
        } else if (accx == 'B'){
            res = accumulatorB ^ operand;
            accumulatorB = res;
        }
        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
        ccr.setOverflow(false);
    }
    //Increment
    public void INC(int addressingMode){
        int res;
        int operand;

        if (addressingMode == 'A'){
            operand = accumulatorA;
            res = operand + 1 & 0xFF;
            accumulatorA = res;
        } else if (addressingMode == 'B'){
            operand = accumulatorB;
            res = operand + 1 & 0xFF;
            accumulatorB = res;
        } else {
            int address = getAddress(addressingMode);
            operand = memory.read(address) & 0xFF;

            res = operand + 1 & 0xFF;
            memory.write(address, (byte) res);
        }

        ccr.setNegative((res & 0x80) != 0);
        ccr.setOverflow(operand == 0x7F);
        ccr.setZero(res == 0);
    }
    //Increment Stack Pointer
    public void INS(){
        stackPointer = stackPointer + 1 & 0xFFFF;
    }
    //Increment Index Register
    public void INX(){
        indexRegister = indexRegister + 1 & 0xFFFF;

        // update Z if all 16 bits are 0
        ccr.setZero(indexRegister == 0);
    }
    //Jump
    public void JMP(int addressingMode){
        programCounter = getAddress(addressingMode) & 0xFFFF;
    }

    //Jump to Subroutine
    public void JSR(int addressingMode){
        // getAddress already increments by : 2 for EXT, 1 for IND
        int address = getAddress(addressingMode);

        int pcl = programCounter & 0xFF;
        memory.write(stackPointer, (byte) pcl);
        stackPointer = (stackPointer - 1) & 0xFFFF;

        int pch = (programCounter >> 8) & 0xFF;
        memory.write(stackPointer, (byte) pch);
        stackPointer = (stackPointer - 1) & 0xFFFF;

        programCounter = address & 0xFFFF;
    }
    //Load Accumulator
    public void LDA(int accx, int addressingMode){
        int operand;
        if (addressingMode == 0){
            operand = fetch() & 0xFF;
        } else {
            int address = getAddress(addressingMode);
            operand = memory.read(address) & 0xFF;
        }

        if (accx == 'A'){
            accumulatorA = operand;
        } else if (accx == 'B'){
            accumulatorB = operand;
        }
        ccr.setNegative((operand & 0x80) != 0);
        ccr.setZero(operand == 0);
        ccr.setOverflow(false);
    }
    //Load Stack Pointer
    public void LDS(int addressingMode){
        int SPH = 0;
        int SPL = 0;
        if (addressingMode == 0){
            SPH = fetch();
            SPL = fetch();

        } else {
            int upperAddress = getAddress(addressingMode) & 0xFFFF;
            int lowerAddress = (upperAddress + 1) & 0xFFFF;

            SPH = memory.read(upperAddress);
            SPL = memory.read(lowerAddress);
        }

        stackPointer = ((SPH << 8) | SPL) & 0xFFFF;

        ccr.setNegative((stackPointer & 0x8000) != 0);
        ccr.setZero(stackPointer == 0);
        ccr.setOverflow(false);
    }

    //Load Index Register
    public void LDX(int addressingMode){
        int IXH = 0;
        int IXL = 0;

        if (addressingMode == 0){
            IXH = fetch();
            IXL = fetch();
        } else {
            int upperAddress = getAddress(addressingMode) & 0xFFFF;
            int lowerAddress = (upperAddress + 1) & 0xFFFF;
            IXH = memory.read(upperAddress);
            IXL = memory.read(lowerAddress);
        }
        indexRegister = ((IXH << 8) | IXL) & 0xFFFF;

        ccr.setNegative((IXH & 0x80) != 0);
        ccr.setZero(indexRegister == 0);
        ccr.setOverflow(false);
    }
    //Logical Shift Right
    public void LSR(int addressingMode){
        int  operand = 0;
        int res = 0;

        if (addressingMode == 'A'){
            operand = accumulatorA;
            res = operand >>> 1;
            accumulatorA = res;
        } else if (addressingMode == 'B'){
            operand = accumulatorB;
            res = operand >>> 1;
            accumulatorB = res;
        } else {
            int address = getAddress(addressingMode);
            operand = memory.read(address) & 0xFF;
            res = operand >>> 1;
            memory.write(address, (byte) res);
        }
        ccr.setCarry((operand & 0x01) != 0);
        ccr.setNegative(false);
        ccr.setZero(res == 0);
        ccr.setOverflow(!(ccr.getNegative()) == ccr.getCarry());
    }

    //Negate
    public void NEG(int addressingMode){
        int res;
        if (addressingMode == 'A'){
            res = (-accumulatorA) & 0xFF;
            accumulatorA = res;
        } else if (addressingMode == 'B'){
            res = (-accumulatorB) & 0xFF;
            accumulatorB = res;
        } else {
            int address = getAddress(addressingMode);
            int operand = memory.read(address);
            res = (-operand) & 0xFF;
            memory.write(address, (byte) res);
        }
        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
        ccr.setOverflow(res == 0x80);
        ccr.setCarry(res != 0);
    }
    //No Operation
    public void NOP(){
        programCounter++;
    }
    //Inclusive OR
    public void ORA(int accx, int addressingMode){
        int operand;
        int res = 0;
        if (addressingMode == 0){
            operand = fetch();
        } else {
            int address = getAddress(addressingMode);
            operand = memory.read(address) & 0xFF;
        }

        if (accx == 'A'){
            res = accumulatorA | operand;
            accumulatorA = res;
        } else if (accx == 'B'){
            res = accumulatorB | operand;
            accumulatorB = res;
        }
        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
        ccr.setOverflow(false);
    }
    //Push Data Onto Stack
    public void PSH(int accx){
        if (accx == 'A'){
            memory.write(stackPointer, (byte)accumulatorA);
        } else if (accx == 'B'){
            memory.write(stackPointer, (byte)accumulatorB);
        }
        stackPointer  = (stackPointer - 1) & 0xFFFF;
    }
    //Pull Data from Stack
    public void PUL(int accx){
        stackPointer = (stackPointer + 1) & 0xFFFF;
        int operand = memory.read(stackPointer) & 0xFF;
        if (accx == 'A'){
            accumulatorA = operand;
        } else if (accx == 'B'){
            accumulatorB = operand;
        }

    }
    //Rotate Left
    public void ROL(int addressingMode){
        int operand;
        int res;
        boolean oldCarry = ccr.getCarry();

        if (addressingMode == 'A'){
            operand = accumulatorA;
            ccr.setCarry((operand & 0x80) != 0);
            res = (operand << 1) & 0xFF;
            if (oldCarry){
                res = res | 0x01;
            }
            accumulatorA = res;
        } else if (addressingMode == 'B'){
            operand = accumulatorB;
            ccr.setCarry((operand & 0x80) != 0);
            res = (operand << 1) & 0xFF;
            if (oldCarry){
                res = res | 0x01;
            }
            accumulatorB = res;
        } else {
            int address = getAddress(addressingMode);
            operand = memory.read(address) & 0xFF;
            ccr.setCarry((operand & 0x80) != 0);
            res = (operand << 1) & 0xFF;
            if (oldCarry){
                res = res | 0x01;
            }
            memory.write(address, (byte) res);
        }

        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
        ccr.setOverflow(ccr.getNegative() != ccr.getCarry());

    }
    //Rotate Right
    public void ROR(int addressingMode){
        int operand;
        int res;
        boolean oldCarry = ccr.getCarry();

        if (addressingMode == 'A'){
            operand = accumulatorA;
            ccr.setCarry((operand & 0x01) != 0);
            res = (operand >>> 1) & 0xFF;
            if (oldCarry){
                res = res | 0x80;
            }
            accumulatorA = res;
        } else if (addressingMode == 'B'){
            operand = accumulatorB;
            ccr.setCarry((operand & 0x01) != 0);
            res = (operand >>> 1) & 0xFF;
            if (oldCarry){
                res = res | 0x80;
            }
            accumulatorB = res;
        } else {
            int address = getAddress(addressingMode);
            operand = memory.read(address) & 0xFF;
            ccr.setCarry((operand & 0x01) != 0);
            res = (operand >>> 1) & 0xFF;
            if (oldCarry){
                res = res | 0x80;
            }
            memory.write(address, (byte) res);
        }

        ccr.setNegative((res & 0x80) != 0);
        ccr.setZero(res == 0);
        ccr.setOverflow(ccr.getNegative() != ccr.getCarry());
    }

    //Return from Interrupt
    public void RTI(){
        stackPointer = (stackPointer + 1) & 0xFFFF;
        // restore condition codes
        int conditionCodeRegister = memory.read(stackPointer);
        //ccr.setState(ccr); --> implement this
        stackPointer = (stackPointer + 1) & 0xFFFF;
        // acca
        accumulatorA = memory.read(stackPointer);
        stackPointer = (stackPointer + 1) & 0xFFFF;
        // accb
        accumulatorB = memory.read(stackPointer);
        stackPointer = (stackPointer + 1) & 0xFFFF;
        // ixh
        int ixh = memory.read(stackPointer);
        stackPointer = (stackPointer + 1) & 0xFFFF;
        // ixl
        int ixl = memory.read(stackPointer);
        stackPointer = (stackPointer + 1) & 0xFFFF;
        indexRegister = ((ixh << 8) | ixl) & 0xFFFF;
        //pch
        int pch = memory.read(stackPointer);
        stackPointer = (stackPointer + 1) & 0xFFFF;
        //pcl
        int pcl = memory.read(stackPointer);
        stackPointer = (stackPointer + 1) & 0xFFFF;
        programCounter = ((pch << 8) | pcl) & 0xFFFF;
    }
    //Return from Subroutine
    public void RTS(){

    }
    //Subtract Accumulator
    public void SBA(){

    }
    //Subtract with Carry
    public void SBC(){}

    //Set Carry
    public void SEC(){
        ccr.setCarry(true);
    }
    //Set Interrupt Mask
    public void SEI(){
        ccr.setInterrupt(true);
    }
    //Set Two's Complement Overflow Bit
    public void SEV(){
        ccr.setOverflow(true);
    }
    //Store Accumulator
    public void STA(int accx, int addressingMode){}

    //Store Stack Pointer
    //direct
    public void STS_DIR(){

    }
    //extended
    public void STS_EXT(){

    }
    //indexed
    public void STS_IND(){

    }
    //Store Index Register
    //direct
    public void STX_DIR(){

    }
    //extended
    public void STX_EXT(){

    }
    //indexed
    public void STX_IND(){

    }
    //Subtract
    public void SUB(int accx, int addressingMode){}

    //Software Interrupt
    public void SWI(){
        ccr.setInterrupt(true);
    }
    //Transfer from Accumulator A to Accumulator B
    public void TAB(){
        accumulatorB = accumulatorA;
        accumulatorB = accumulatorB & 0xFF;
        ccr.setNegative((accumulatorB & 0x80) != 0);
        ccr.setZero(accumulatorB == 0);
        ccr.setOverflow(false);

    }
    //Transfer from Accumulator A to CCR
    public void TAP(){
        ccr.setCarry((accumulatorA & 0x01) != 0);
        ccr.setOverflow((accumulatorA & 0x02) != 0);
        ccr.setZero((accumulatorA & 0x04) != 0);
        ccr.setNegative((accumulatorA & 0x08)!= 0);
        ccr.setInterrupt((accumulatorA & 0x10) != 0);
        ccr.setHalfCarry((accumulatorA & 0x20) != 0);
    }
    //Transfer from Accumulator B to Accumulator A
    public void TBA(){
        accumulatorA = accumulatorB;
        accumulatorA = accumulatorA & 0xFF;
        ccr.setNegative((accumulatorA & 0x80) != 0);
        ccr.setZero(accumulatorA == 0);
        ccr.setOverflow(false);
    }
    //Transfer from CCR to accumulator A
    public void TPA(){
        int temp = 0;
        temp = temp | 0xC0;
        temp = temp | (ccr.getHalfCarry() ? 1 : 0) << 5;
        temp = temp | (ccr.getInterrupt() ? 1 : 0) << 4;
        temp = temp | (ccr.getNegative() ? 1 : 0) << 3;
        temp = temp | (ccr.getZero() ? 1 : 0) << 2;
        temp = temp | (ccr.getOverflow() ? 1 : 0) << 1;
        temp = temp | (ccr.getCarry() ? 1 : 0);

        accumulatorA = temp;
    }
    //Test
    //accumulator A
    public void TSTA(){
        ccr.setNegative((accumulatorA & 0x80) != 0);
        ccr.setZero(accumulatorA == 0);
        ccr.setOverflow(false);
        ccr.setCarry(false);
    }
    //accumulator B
    public void TSTB(){
        ccr.setNegative((accumulatorB & 0x80) != 0);
        ccr.setZero(accumulatorB == 0);
        ccr.setOverflow(false);
        ccr.setCarry(false);
    }
    //extended
    public void TST_EXT(){
        int upperAddress = fetch();
        int lowerAddress = fetch();

        int address = (upperAddress << 8 | lowerAddress) & 0xFFFF;
        int operand = memory.read(address);

        ccr.setNegative((operand & 0x80) != 0);
        ccr.setZero(operand == 0);
        ccr.setOverflow(false);
        ccr.setCarry(false);
    }
    //indexed
    public void TST_IND(){
        int address = indexRegister + fetch();
        int operand = memory.read(address);

        ccr.setNegative((operand & 0x80) != 0);
        ccr.setZero(operand == 0);
        ccr.setOverflow(false);
        ccr.setCarry(false);

    }
    //Transfer from Stack Pointer to Index Register
    public void TSX(){
        indexRegister = stackPointer + 1;

    }
    //Transfer from Index Register to Stack Pointer
    public void TXS(){
        stackPointer = indexRegister - 1;
    }
    //Wait for Interrupt
    public void WAI(){
        //increment PC
        //push pcl
        //dec sp
        //push pch
        //dec sp
        //push ixl
        //dec sp
        //push ixh
        //dec sp
        //acca
        //dec sp
        //accb
        //dec sp
        //cc
        //dec sp
    }

}