public class Main {
    public static void main(String[] args){
        CPU c = new CPU();
        System.out.println("PC: " + c.programCounter);
        System.out.println("SP: " + c.stackPointer);
        System.out.println(c.accumulatorA);
        c.accumulatorA = 0xA9;
        c.memory.write(0, (byte)0x84);
        // A, immediate
        c.memory.write(1, (byte) 0x0F);
        c.step();

        System.out.println("PC: " + c.programCounter);
        System.out.println("SP: " + c.stackPointer);
        System.out.println(c.accumulatorA);
//        c.accumulatorA = 0x08;
//        c.accumulatorB = 0x08;
//        System.out.println(c.accumulatorA);
//        System.out.println(c.accumulatorB);
//
//        c.ABA();
//
//        System.out.println(c.accumulatorA);
//        System.out.println(c.accumulatorB);
//        System.out.println(c.ccr.toString());
//
//        c.accumulatorA = 0xA5;
//        c.memory.write(1, (byte)0x06);
//        c.step();
//        System.out.println(c.ccr.toString());
//        c.memory.write(2, (byte)0x07);
//        c.step();
//        System.out.println("ACCA: " + c.accumulatorA);

    }
}
