package Parser;

import Lexer.*;

import java.util.*;

public class Parser {
    Lexer lex = new Lexer();
    char[] chars;
    HashSet<Character> setc = new HashSet<>();
    static int m = 0;
    ArrayList<Character> characters = new ArrayList<>();
    double initial_x = 0;
    double initial_y = 0;
    static int sizem = 0;
    static double digit = 0;
    boolean valuable = false;
    boolean mant = true;
    boolean manfi = false;
    static ArrayList<String> arrayList = new ArrayList<>();
    public Stack stack = new Stack();
    String negetives_before = "amalgar";
    static Token lookahead;
    static Token forhead;

    public Parser(String s) throws Exception {
        Scanner scn = new Scanner(System.in);

        if (val(s)) {
            if (sizem == 1) {
                initial_x = scn.nextDouble();
            } else if (sizem == 2) {
                initial_x = scn.nextDouble();
                initial_y = scn.nextDouble();

            }
        }
        chars = new char[s.length() + 1];
        for (int a = 0; a < s.length(); a++) {
            chars[a] = s.charAt(a);
        }
        lookahead = lex.forward(chars);
    }


    public void jam_menha() throws Exception {
        zarb_taghsim();
        while (true) {
            if (lookahead.tag == '+') {
                match(new Token('+'));
                zarb_taghsim();
                arrayList.add("+ ");
                double b = ((Num) stack.pop()).value;
                double a = ((Num) stack.pop()).value;
                stack.push(new Num(a + b));
            } else if (lookahead.tag == '-' && negetives_before.equals("adadletter")) {
                match(new Token('-'));
                zarb_taghsim();
                arrayList.add("- ");
                double b = ((Num) stack.pop()).value;
                double a = ((Num) stack.pop()).value;
                stack.push(new Num(a - b));
            } else
                return;
        }
    }

    private void zarb_taghsim() throws Exception {
        tavan();
        while (true) {
            if (lookahead.tag == '*') {
                match(new Token('*'));
                tavan();
                arrayList.add("* ");
                double b = ((Num) stack.pop()).value;
                double a = ((Num) stack.pop()).value;
                stack.push(new Num(a * b));
            } else if (lookahead.tag == '/') {
                match(new Token('/'));
                tavan();
                arrayList.add("/ ");
                double b = ((Num) stack.pop()).value;
                double a = ((Num) stack.pop()).value;
                stack.push(new Num(a / b));
            } else if (lookahead.tag == Tag.DIV) {
                match(new Token(Tag.DIV));
                tavan();

                arrayList.add("div ");
                double b = ((Num) stack.pop()).value;
                double a = ((Num) stack.pop()).value;
                stack.push(new Num((int) (a / b)));
            } else if (lookahead.tag == Tag.MOD) {
                match(new Token(Tag.MOD));
                tavan();

                arrayList.add("mod ");
                double b = ((Num) stack.pop()).value;
                double a = ((Num) stack.pop()).value;
                stack.push(new Num(a % b));
            } else
                return;
        }
    }

    private void tavan() throws Exception {
        tavabe();
        while (true) {
            if (lookahead.tag == '-' && negetives_before.equals("amalgar")) {
                manfi = true;
                match((new Token('-')));
                tavabe();
                double a = 0;
                double b = ((Num) stack.pop()).value;
                stack.push(new Num(a - b));
            }
            if (lookahead.tag == '^') {
                match(new Token('^'));
                tavabe();
                arrayList.add("^ ");
                double b = ((Num) stack.pop()).value;
                double a = ((Num) stack.pop()).value;
                stack.push(new Num(Math.pow(a, b)));
            } else if (lookahead.tag == Tag.e) {
                match((new Token(Tag.e)));
                stack.push(new Num(Math.E));
                arrayList.add("e ");
            } else if (lookahead.tag == Tag.PI) {
                match((new Token(Tag.PI)));
                stack.push(new Num(Math.PI));
                arrayList.add("pi ");
            } else
                return;
        }
    }

    private void tavabe() throws Exception {
        while (true) {

            if (lookahead.tag == Tag.SIN) {
                match(new Token(Tag.SIN));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("sin ");
                stack.push(new Num(Math.sin(((Num) stack.pop()).value)));
            } else if (lookahead.tag == Tag.SINH) {
                match(new Token(Tag.SINH));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("sinh ");
                stack.push(new Num(Math.sinh((((Num) stack.pop()).value))));
            } else if (lookahead.tag == Tag.ARCsin) {
                match(new Token(Tag.ARCsin));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("Arcsin ");
                stack.push(new Num(Math.asin(((Num) stack.pop()).value)));
            } else if (lookahead.tag == Tag.COS) {
                match(new Token(Tag.COS));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("cos ");
                stack.push(new Num(Math.cos((((Num) stack.pop()).value))));
            } else if (lookahead.tag == Tag.COSH) {
                match(new Token(Tag.COSH));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("cosh ");
                stack.push(new Num(Math.cosh((((Num) stack.pop()).value))));
            } else if (lookahead.tag == Tag.ARCcos) {
                match(new Token(Tag.ARCcos));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("Arccos ");
                stack.push(new Num(Math.acos(((Num) stack.pop()).value)));
            } else if (lookahead.tag == Tag.TAN) {
                match(new Token(Tag.TAN));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("tan ");
                stack.push(new Num(Math.tan((((Num) stack.pop()).value))));
            } else if (lookahead.tag == Tag.TANH) {
                match(new Token(Tag.TANH));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("tanh ");
                stack.push(new Num(Math.tanh((((Num) stack.pop()).value))));
            } else if (lookahead.tag == Tag.Arctan) {
                match(new Token(Tag.Arctan));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("Arctan ");
                stack.push(new Num(Math.atan(((Num) stack.pop()).value)));
            } else if (lookahead.tag == Tag.COT) {
                match(new Token(Tag.COT));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("cot ");
                stack.push(new Num(1.0 / Math.tan((((Num) stack.pop()).value))));
            } else if (lookahead.tag == Tag.COTH) {
                match(new Token(Tag.COTH));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("coth");
                stack.push(new Num(1.0 / Math.tanh((((Num) stack.pop()).value))));
            } else if (lookahead.tag == Tag.Arccot) {
                match(new Token(Tag.Arccot));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("Arccot ");
                stack.push(new Num(1.0 / Math.atan((((Num) stack.pop()).value))));
            } else if (lookahead.tag == Tag.SQRT) {
                match(new Token(Tag.SQRT));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("sqrt ");
                stack.push(new Num(Math.sqrt(((Num) stack.pop()).value)));
            } else if (lookahead.tag == Tag.log) {
                match(new Token(Tag.log));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("log ");
                stack.push(new Num(Math.log(((Num) stack.pop()).value)));
            } else if (lookahead.tag == Tag.exp) {
                match(new Token(Tag.exp));
                match(new Token('('));
                jam_menha();
                match(new Token(')'));
                arrayList.add("exp ");
                stack.push(new Num(Math.exp(((Num) stack.pop()).value)));
            } else if (lookahead.tag == Tag.NUM) {
                DigitOrLetter();
                if (lookahead.tag == 257) {
                    throw new Error(" you can't create a valuable with a Integer and String in a row" +
                            "in Line " + lex.line);
                } else {
                    arrayList.add(String.valueOf(digit + " "));
                    stack.push(forhead);
                }
                return;
            } else if (lookahead.tag == Tag.ID) {
                DigitOrLetter();
                return;
            } else if (lookahead.tag == Tag.ID1) {
                DigitOrLetter();
                return;
            } else if (lookahead.tag == '(') {
                DigitOrLetter();
                return;
            } else {
                negetives_before = "amalgar";
                return;
            }

        }
    }

    private void DigitOrLetter() throws Exception {
        if (lookahead.tag == Tag.NUM) {
            negetives_before = "adadletter";
            forhead = lookahead;
            if (!manfi) {
                digit = ((Num) lookahead).value;
            } else {
                digit = -1 * ((Num) lookahead).value;
            }
            manfi = false;
            match(lookahead);
        } else if (lookahead.tag == Tag.ID) {
            negetives_before = "adadletter";

            stack.push(new Num(initial_x));
            arrayList.add(String.valueOf(initial_x + " "));
            match(lookahead);
        } else if (lookahead.tag == Tag.ID1) {
            negetives_before = "adadletter";

            stack.push(new Num(initial_y));
            arrayList.add(String.valueOf(initial_y + " "));
            match(lookahead);
        } else if (lookahead.tag == '(') {
            match(new Token('('));
            jam_menha();
            match(new Token(')'));
        } else
            throw new Error("Syntax Error on line: " + lex.line);
    }

    void match(Token t) throws Exception {
        if (lookahead.tag == t.tag) {
            lookahead = lex.forward();
        } else {
            throw new Exception(" Syntax(Match) Error on line: " + lex.line);
        }
    }


    boolean val(String s) {
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();
            if (Character.isLetter(s.charAt(i))) {
                sb.delete(0, sb.length());
                for (int j = 0; j <s.length() ; j++) {
                    if (s.charAt(j)=='x'){
                        setc.add(s.charAt(j));
                    }
                    if (s.charAt(j)=='y'){
                        setc.add(s.charAt(j));
                    }
                }
                for (int j = i; j < s.length(); j++) {
//                    sb.append(s.charAt(j))/

                    if (s.charAt(j) == '(' || s.charAt(j) == ')' || s.charAt(j) == '+' || s.charAt(j) == '-'
                            || s.charAt(j) == '*' || s.charAt(j) == '/' || s.charAt(j) == '^' ||
                            Character.isDigit(s.charAt(j))) {
//                        break;
                    }
                    i = j;
                }
                String ss = sb.toString();
                Word w = (Word) Lexer.hashtable.get(ss);
                if (w == null) {
                    valuable = true;
                }
            }
        }
        sizem = setc.size();
        return valuable;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
//        System.out.println("تعداد متغیر را وارد کنید");
//        m = scn.nextInt();
        System.out.println("please enter equation");//معادله را وارد کنید
        String str = scn.nextLine();
        Parser parse = new Parser(str);
        parse.jam_menha();
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();
        System.out.println(((Num) parse.stack.pop()).value);
    }
}


