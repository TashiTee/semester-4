package sample;

import java.util.*;

public class StoreNum {
    int addAsr;
    int subAsr;
    int mulAsr;
    double divAsr;
    int correctNum;
    int wrongNum;

    public StoreNum(int add, int sub, int mul, double div) {
        this.addAsr = add;
        this.subAsr = sub;
        this.mulAsr = mul;
        this.divAsr = div;
    }

    public void setCorrectNum(int correct) {
        this.correctNum = correct;
    }

    public int getCorrectNum() {
        return this.correctNum;
    }

    public void setWrongNum(int wrong) {
        this.wrongNum = wrong;
    }

    public int getWrongNum() {
        return this.wrongNum;
    }

    @Override
    public String toString() {
        return "Answer Collection [Add answer=" + this.addAsr + ", Sub answer="
                + this.addAsr + ", Mul answer=" + this.mulAsr + ", Div answer="
                + this.divAsr + ", Correct=" + this.correctNum + ", Wrong="
                + this.wrongNum;
    }
}