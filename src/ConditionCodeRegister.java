public class ConditionCodeRegister {
    private boolean carry;
    private boolean overflow;
    private boolean zero;
    private boolean negative;
    private boolean interrupt;
    private boolean halfCarry;


    public ConditionCodeRegister(){
        this.carry = false;
        this.overflow = false;
        this.zero = false;
        this.negative = false;
        this.interrupt = false;
        this.halfCarry = false;
    }

    public void set (boolean c, boolean v, boolean z, boolean n , boolean i, boolean h){
        this.carry = c;
        this.overflow = v;
        this.zero = z;
        this.negative = n;
        this.interrupt = i;
        this.halfCarry = h;
    }

    public boolean getCarry(){
        return carry;
    }

    public boolean getOverflow(){
        return overflow;
    }

    public boolean getZero(){
        return zero;
    }

    public boolean getNegative(){
        return negative;
    }

    public boolean getInterrupt(){
        return interrupt;
    }

    public boolean getHalfCarry(){
        return halfCarry;
    }

    public void setCarry(boolean c){
        this.carry = c;
    }

    public void setOverflow(boolean v){
        this.overflow = v;
    }

    public void setNegative(boolean n){
        this.negative = n;
    }

    public void setZero(boolean z){
        this.zero = z;
    }

    public void setInterrupt(boolean i){
        this.interrupt = i;
    }

    public void setHalfCarry(boolean h){
        this.halfCarry = h;
    }

    public String toString(){
        return ("C: " + carry + " V: " + overflow + " Z: " +
                zero + " N: " + negative + " I: " + interrupt + " H: " + halfCarry);
    }

    public void setState(int c){
        this.carry = ((c & 0x01)  != 0);
        this.overflow = ((c & 0x02)  != 0);
        this.zero = ((c & 0x04)  != 0);
        this.negative = ((c & 0x08)  != 0);
        this.interrupt = ((c & 0x10)  != 0);
        this.halfCarry = ((c & 0x20)  != 0);
    }

    public int getState(){
        int c = 0;
        c = c | 0xC0;
        c = c | (halfCarry ? 1 : 0) << 5;
        c = c | (interrupt ? 1 : 0) << 4;
        c = c | (negative ? 1 : 0) << 3;
        c = c | (zero ? 1 : 0) << 2;
        c = c | (overflow ? 1 : 0) << 1;
        c = c | (carry ? 1 : 0);

        return c & 0xFF;
    }
}
