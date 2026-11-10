public class Main {
    public static void main(String[] args){
        System.out.println((new byte[8]).toString());
        // NOTE: the quirk with reading bytes in java :)
        Byte a = (byte) 254;
        int b = (int) a & 0xFF;
        System.out.println(a);
        System.out.println(b);

        int c = 0xFFFF;
        int tooBig = 0x11170;
        int adjusted = tooBig & c;
        System.out.print (tooBig + " -> " + adjusted);

    }
}
