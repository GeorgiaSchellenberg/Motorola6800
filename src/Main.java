public class Main {
    public static void main(String[] args){
        CPU c = new CPU();
        c.memory.write(0, (byte)3);
        c.accumulatorA = 14;
        System.out.println(c.accumulatorA);
        c.ANDA_imm();
        System.out.println(c.accumulatorA);


    }
}
