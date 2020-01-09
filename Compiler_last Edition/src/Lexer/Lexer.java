package Lexer;

import java.util.*;

public class Lexer {
    public static int line = 1;
    char[] charlist;
    public static Hashtable<String, Word> hashtable = new Hashtable<String, Word>();

    void res(Word t) {
        hashtable.put(t.lexeme, t);
    }

    public Lexer() {
        res(new Word(Tag.TRUE, "true"));
        res(new Word(Tag.FALSE, "false"));
        res(new Word(Tag.SIN, "sin"));
        res(new Word(Tag.COS, "cos"));
        res(new Word(Tag.TAN, "tan"));
        res(new Word(Tag.COT, "cot"));
        res(new Word(Tag.log, "log"));
        res(new Word(Tag.exp, "exp"));
        res(new Word(Tag.e, "e"));
        res(new Word(Tag.PI, "pi"));
        res(new Word(Tag.DIV, "div"));
        res(new Word(Tag.MOD, "mod"));
        res(new Word(Tag.SQRT, "sqrt"));
        res(new Word(Tag.SINH, "sinh"));
        res(new Word(Tag.COSH, "cosh"));
        res(new Word(Tag.TANH, "tanh"));
        res(new Word(Tag.ARCsin, "Arcsin"));
        res(new Word(Tag.ARCcos, "Arccos"));
        res(new Word(Tag.Arctan, "Arctan"));
        res(new Word(Tag.Arccot, "Arccot"));

    }

    private static char lookhead;
    private static int counter = 0;

    public Token forward() {
        for (; counter < charlist.length; counter++) {
            lookhead = charlist[counter];
            if (lookhead == ' ') {
            }
            if (lookhead == '\t') {
            } else if (lookhead == '\n') {
                line++;
                return new Token('\n');
            } else
                break;
        }

        if (Character.isDigit(lookhead)) {
            double sahih = 0, ashahri = 0.0;
            while (Character.isDigit(lookhead)) {
                sahih = 10 * sahih + Character.getNumericValue(lookhead);
                counter++;
                lookhead = charlist[counter];
            }
            int ashar = 0;
            if (lookhead == ',' || lookhead == '.') {
                counter++;
                while (Character.isDigit(charlist[counter])) {
                    lookhead = charlist[counter];
                    counter++;
                    ashahri = 10 * ashahri + Character.getNumericValue(lookhead);
                    ashar++;
                }
            }
            sahih += ashahri / Math.pow(10, ashar);
            return new Num(sahih);
        }
        if (Character.isLetter(lookhead)) {
            StringBuffer b = new StringBuffer();
            do {
                b.append(lookhead);
                lookhead = charlist[++counter];
            } while (Character.isLetterOrDigit(lookhead));
            String str = b.toString();
            if (str.equals("x")){
            Word w = (Word) hashtable.get(str);
            if (w != null)
                return w;
            w = new Word(Tag.ID, str);

            hashtable.put(str, w);
            return w;
        }else if (str.equals("y")){
                Word w = (Word) hashtable.get(str);
                if (w != null)
                    return w;
                w = new Word(Tag.ID1, str);

                hashtable.put(str, w);
                return w;
            }
            Word w = (Word) hashtable.get(str);
            if (w != null)
                return w;
            w = new Word(Tag.ID, str);

            hashtable.put(str, w);
            return w;

        }
        Token t = new Token(lookhead);
        counter++;
        return t;
    }

    public Token forward(char[] chars) {
        charlist = new char[chars.length];
        for (int j = 0; j < chars.length; j++)
            charlist[j] = chars[j];

        for (; counter < chars.length; counter++) {
            lookhead = chars[counter];
            if (lookhead == ' ' || lookhead == '\t')
                continue;
            else if (lookhead == '\n')
                line++;
            else
                break;
        }

        if (Character.isDigit(lookhead)) {
            double sahih = 0.0, ashahri = 0.0;
            while (Character.isDigit(lookhead)) {
                sahih = 10 * sahih + Character.getNumericValue(lookhead);
                counter++;
                lookhead = charlist[counter];
            }
            int ashar = 0;
            if (lookhead == ',' || lookhead == '.') {
                counter++;
                while (Character.isDigit(charlist[counter])) {
                    lookhead = charlist[counter];
                    counter++;
                    ashahri = 10 * ashahri + Character.getNumericValue(lookhead);
                    ashar++;
                }
            }
            sahih += ashahri / Math.pow(10, ashar);
            return new Num(sahih);
        }
        if (Character.isLetter(lookhead)) {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(lookhead);
                lookhead = charlist[++counter];
            } while (Character.isLetterOrDigit(lookhead));
            String s = sb.toString();
            if (s.equals("x")) {
                Word w = hashtable.get(s);
                if (w != null) {
                    return w;
                }
                w = new Word(Tag.ID, s);
                hashtable.put(s, w);
                return w;
            }
            if (s.equals("y")) {
                Word w = hashtable.get(s);
                if (w != null) {
                    return w;
                }
                w = new Word(Tag.ID1, s);
                hashtable.put(s, w);
                return w;
            }
            Word w = hashtable.get(s);
            if (w != null) {
                return w;
            }
            w = new Word(Tag.ID, s);
            hashtable.put(s, w);
            return w;
        }

        Token t = new Token(lookhead);
        counter++;
        return t;
    }
}
