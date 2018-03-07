package imult;
/*
 * Class StudentCode: class for students to implement
 * the specific methods required by the assignment:
 * add()
 * sub()
 * koMul()
 * koMulOpt()
 * (See coursework handout for full method documentation)
 */

public class StudentCode {
    public static BigInt add(BigInt a, BigInt b) {
        Unsigned carry = new Unsigned(0);
        BigInt result = new BigInt();
        for (int i = 0; i <= Math.max(a.length(), b.length()); i++) {
            DigitAndCarry dc = Arithmetic.addDigits(a.getDigit(i), b.getDigit(i), carry);
            carry = dc.getCarry();
            result.setDigit(i, dc.getDigit());
        }

        return result;
    }

    public static BigInt sub(BigInt a, BigInt b) {
        Unsigned carry = new Unsigned(0);
        BigInt result = new BigInt();
        for (int i = 0; i <= Math.max(a.length(), b.length()); i++) {
            DigitAndCarry dc = Arithmetic.subDigits(a.getDigit(i), b.getDigit(i), carry);
            carry = dc.getCarry();
            result.setDigit(i, dc.getDigit());
        }

        return result;
    }

    public static BigInt koMul(BigInt a, BigInt b) {
        int n = Math.max(a.length(), b.length());

        if (n <= 1) {
            DigitAndCarry dc = Arithmetic.mulDigits(a.getDigit(0), b.getDigit(0));

            BigInt result = new BigInt();
            result.setDigit(0, dc.getDigit());
            result.setDigit(1, dc.getCarry());

            return result;
        }

        int lowerNum = Math.floorDiv(n, 2);

        BigInt a0 = a.split(0, lowerNum-1);
        BigInt b0 = b.split(0, lowerNum-1);

        BigInt a1 = a.split(lowerNum, n);
        BigInt b1 = b.split(lowerNum, n);
        BigInt l = koMul(a0, b0);
        BigInt h = koMul(a1, b1);

        BigInt m = sub(koMul(
                add(a0, a1),
                add(b0, b1)
        ), add(l ,h));

        m.lshift(lowerNum);
        h.lshift(lowerNum*2);

        return add(add(l, m), h);
    }

    public static BigInt koMulOpt(BigInt a, BigInt b) {
        int n = Math.max(a.length(), b.length());

        if (Math.min(a.length(), b.length()) <= 10) {
            return Arithmetic.schoolMul(a, b);
        }

        if (n <= 1) {
            DigitAndCarry dc = Arithmetic.mulDigits(a.getDigit(0), b.getDigit(0));

            BigInt result = new BigInt();
            result.setDigit(0, dc.getDigit());
            result.setDigit(1, dc.getCarry());

            return result;
        }

        int lowerNum = Math.floorDiv(n, 2);

        BigInt a0 = a.split(0, lowerNum-1);
        BigInt b0 = b.split(0, lowerNum-1);

        BigInt a1 = a.split(lowerNum, n);
        BigInt b1 = b.split(lowerNum, n);

        BigInt l = koMulOpt(a0, b0);
        BigInt h = koMulOpt(a1, b1);

        BigInt m = sub(koMulOpt(
                add(a0, a1),
                add(b0, b1)
        ), add(l ,h));

        m.lshift(lowerNum);
        h.lshift(lowerNum*2);

        return add(add(l, m), h);
    }

    public static void main(String argv[]) throws java.io.FileNotFoundException {
//        BigInt a = new BigInt(1);
//        BigInt b = new BigInt(1);
//
//        a.print();
//        System.out.println("+");
//        b.print();
//        System.out.println("=");
//
//        add(a, b).print();
//
//        System.out.println();
//
//        a.print();
//        System.out.println("-");
//        b.print();
//        System.out.println("=");
//
//        sub(a, b).print();
//        BigIntMul.mulVerify(new BigInt(1), new BigInt(2));
//        for (int i = 1; i <= 50; i++) {
//            BigIntMul.mulTest(new Unsigned(25), new Unsigned(i));
//        }


    }
} //end StudentCode class
