public class Main {
    public static void main(String[] args){
        CPU c = new CPU();
        System.out.println("PC: " + c.programCounter);
        c.memory.write(0, (byte)3);
        c.accumulatorA = 14;
        System.out.println(c.accumulatorA);
        c.ANDA_IMM();
        System.out.println(c.accumulatorA);

        System.out.println("PC: " + c.programCounter);
    }
}
