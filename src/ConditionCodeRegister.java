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

    public void checkAndSetNegative(){}

}
