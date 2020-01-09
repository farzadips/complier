package Lexer;

public class Word extends Token {
    public final String lexeme;
    public static double value;
//    public final double value = 0.0;
    public Word(int t, String s){
        super(t);
        lexeme = new String(s);
    }
   
//    public Word(int t, String s, double value){
//        super(t);
//        lexeme = new String (s);
//        value = this.value;
//    }
}
