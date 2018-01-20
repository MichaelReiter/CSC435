// $ANTLR 3.0.1 ul.g 2018-01-20 14:33:30

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ulLexer extends Lexer {
    public static final int PRINT=17;
    public static final int PRINTLN=18;
    public static final int CLOSEBRACE=8;
    public static final int COMMENT=33;
    public static final int MINUS=23;
    public static final int RETURN=16;
    public static final int SEMICOLON=9;
    public static final int WHITESPACE=32;
    public static final int STRINGCONSTANT=29;
    public static final int ELSE=20;
    public static final int CLOSEBRACKET=13;
    public static final int ID=26;
    public static final int EOF=-1;
    public static final int TYPE=10;
    public static final int IF=19;
    public static final int DOUBLEEQUALS=25;
    public static final int Tokens=34;
    public static final int COMMA=6;
    public static final int CHARACTERCONSTANT=28;
    public static final int OPENBRACE=7;
    public static final int OPENPARENTHESIS=4;
    public static final int TRUE=30;
    public static final int SINGLEEQUALS=14;
    public static final int FLOATCONSTANT=27;
    public static final int OPENBRACKET=11;
    public static final int CLOSEPARENTHESIS=5;
    public static final int INTEGERCONSTANT=12;
    public static final int STAR=21;
    public static final int LESSTHAN=24;
    public static final int WHILE=15;
    public static final int FALSE=31;
    public static final int PLUS=22;
    public ulLexer() {;} 
    public ulLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "ul.g"; }

    // $ANTLR start TYPE
    public final void mTYPE() throws RecognitionException {
        try {
            int _type = TYPE;
            // ul.g:207:5: ( 'int' | 'float' | 'char' | 'string' | 'boolean' | 'void' )
            int alt1=6;
            switch ( input.LA(1) ) {
            case 'i':
                {
                alt1=1;
                }
                break;
            case 'f':
                {
                alt1=2;
                }
                break;
            case 'c':
                {
                alt1=3;
                }
                break;
            case 's':
                {
                alt1=4;
                }
                break;
            case 'b':
                {
                alt1=5;
                }
                break;
            case 'v':
                {
                alt1=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("206:1: TYPE : ( 'int' | 'float' | 'char' | 'string' | 'boolean' | 'void' );", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // ul.g:207:10: 'int'
                    {
                    match("int"); 


                    }
                    break;
                case 2 :
                    // ul.g:208:10: 'float'
                    {
                    match("float"); 


                    }
                    break;
                case 3 :
                    // ul.g:209:10: 'char'
                    {
                    match("char"); 


                    }
                    break;
                case 4 :
                    // ul.g:210:10: 'string'
                    {
                    match("string"); 


                    }
                    break;
                case 5 :
                    // ul.g:211:10: 'boolean'
                    {
                    match("boolean"); 


                    }
                    break;
                case 6 :
                    // ul.g:212:10: 'void'
                    {
                    match("void"); 


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TYPE

    // $ANTLR start IF
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            // ul.g:215:18: ( 'if' )
            // ul.g:215:23: 'if'
            {
            match("if"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IF

    // $ANTLR start ELSE
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            // ul.g:217:18: ( 'else' )
            // ul.g:217:23: 'else'
            {
            match("else"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELSE

    // $ANTLR start WHILE
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            // ul.g:219:18: ( 'while' )
            // ul.g:219:23: 'while'
            {
            match("while"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHILE

    // $ANTLR start PRINT
    public final void mPRINT() throws RecognitionException {
        try {
            int _type = PRINT;
            // ul.g:221:18: ( 'print' )
            // ul.g:221:23: 'print'
            {
            match("print"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRINT

    // $ANTLR start PRINTLN
    public final void mPRINTLN() throws RecognitionException {
        try {
            int _type = PRINTLN;
            // ul.g:223:18: ( 'println' )
            // ul.g:223:23: 'println'
            {
            match("println"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRINTLN

    // $ANTLR start RETURN
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            // ul.g:225:18: ( 'return' )
            // ul.g:225:23: 'return'
            {
            match("return"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RETURN

    // $ANTLR start TRUE
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            // ul.g:227:18: ( 'true' )
            // ul.g:227:23: 'true'
            {
            match("true"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TRUE

    // $ANTLR start FALSE
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            // ul.g:229:18: ( 'false' )
            // ul.g:229:23: 'false'
            {
            match("false"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FALSE

    // $ANTLR start SEMICOLON
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            // ul.g:231:18: ( ';' )
            // ul.g:231:23: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SEMICOLON

    // $ANTLR start OPENPARENTHESIS
    public final void mOPENPARENTHESIS() throws RecognitionException {
        try {
            int _type = OPENPARENTHESIS;
            // ul.g:233:18: ( '(' )
            // ul.g:233:23: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OPENPARENTHESIS

    // $ANTLR start CLOSEPARENTHESIS
    public final void mCLOSEPARENTHESIS() throws RecognitionException {
        try {
            int _type = CLOSEPARENTHESIS;
            // ul.g:235:18: ( ')' )
            // ul.g:235:23: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLOSEPARENTHESIS

    // $ANTLR start OPENBRACKET
    public final void mOPENBRACKET() throws RecognitionException {
        try {
            int _type = OPENBRACKET;
            // ul.g:237:18: ( '[' )
            // ul.g:237:23: '['
            {
            match('['); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OPENBRACKET

    // $ANTLR start CLOSEBRACKET
    public final void mCLOSEBRACKET() throws RecognitionException {
        try {
            int _type = CLOSEBRACKET;
            // ul.g:239:18: ( ']' )
            // ul.g:239:23: ']'
            {
            match(']'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLOSEBRACKET

    // $ANTLR start OPENBRACE
    public final void mOPENBRACE() throws RecognitionException {
        try {
            int _type = OPENBRACE;
            // ul.g:241:18: ( '{' )
            // ul.g:241:23: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OPENBRACE

    // $ANTLR start CLOSEBRACE
    public final void mCLOSEBRACE() throws RecognitionException {
        try {
            int _type = CLOSEBRACE;
            // ul.g:243:18: ( '}' )
            // ul.g:243:23: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLOSEBRACE

    // $ANTLR start COMMA
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            // ul.g:245:18: ( ',' )
            // ul.g:245:23: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMA

    // $ANTLR start SINGLEEQUALS
    public final void mSINGLEEQUALS() throws RecognitionException {
        try {
            int _type = SINGLEEQUALS;
            // ul.g:247:18: ( '=' )
            // ul.g:247:23: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SINGLEEQUALS

    // $ANTLR start STAR
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            // ul.g:249:18: ( '*' )
            // ul.g:249:23: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STAR

    // $ANTLR start PLUS
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            // ul.g:251:18: ( '+' )
            // ul.g:251:23: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUS

    // $ANTLR start MINUS
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            // ul.g:253:18: ( '-' )
            // ul.g:253:23: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MINUS

    // $ANTLR start LESSTHAN
    public final void mLESSTHAN() throws RecognitionException {
        try {
            int _type = LESSTHAN;
            // ul.g:255:18: ( '<' )
            // ul.g:255:23: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LESSTHAN

    // $ANTLR start DOUBLEEQUALS
    public final void mDOUBLEEQUALS() throws RecognitionException {
        try {
            int _type = DOUBLEEQUALS;
            // ul.g:257:18: ( '==' )
            // ul.g:257:23: '=='
            {
            match("=="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOUBLEEQUALS

    // $ANTLR start INTEGERCONSTANT
    public final void mINTEGERCONSTANT() throws RecognitionException {
        try {
            int _type = INTEGERCONSTANT;
            // ul.g:260:5: ( ( '0' .. '9' )+ )
            // ul.g:260:10: ( '0' .. '9' )+
            {
            // ul.g:260:10: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ul.g:260:11: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INTEGERCONSTANT

    // $ANTLR start FLOATCONSTANT
    public final void mFLOATCONSTANT() throws RecognitionException {
        try {
            int _type = FLOATCONSTANT;
            // ul.g:264:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )+ )
            // ul.g:264:10: ( '0' .. '9' )+ '.' ( '0' .. '9' )+
            {
            // ul.g:264:10: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ul.g:264:11: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            match('.'); 
            // ul.g:264:26: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ul.g:264:27: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FLOATCONSTANT

    // $ANTLR start CHARACTERCONSTANT
    public final void mCHARACTERCONSTANT() throws RecognitionException {
        try {
            int _type = CHARACTERCONSTANT;
            // ul.g:268:5: ( '\\u0027' ( 'a' .. 'z' | 'A' .. 'Z' | ' ' | '0' .. '9' ) '\\u0027' )
            // ul.g:268:10: '\\u0027' ( 'a' .. 'z' | 'A' .. 'Z' | ' ' | '0' .. '9' ) '\\u0027'
            {
            match('\''); 
            if ( input.LA(1)==' '||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            match('\''); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CHARACTERCONSTANT

    // $ANTLR start STRINGCONSTANT
    public final void mSTRINGCONSTANT() throws RecognitionException {
        try {
            int _type = STRINGCONSTANT;
            // ul.g:272:5: ( '\\u0022' ( 'a' .. 'z' | 'A' .. 'Z' | ' ' | '0' .. '9' )* '\\u0022' )
            // ul.g:272:10: '\\u0022' ( 'a' .. 'z' | 'A' .. 'Z' | ' ' | '0' .. '9' )* '\\u0022'
            {
            match('\"'); 
            // ul.g:272:19: ( 'a' .. 'z' | 'A' .. 'Z' | ' ' | '0' .. '9' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==' '||(LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ul.g:
            	    {
            	    if ( input.LA(1)==' '||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match('\"'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRINGCONSTANT

    // $ANTLR start ID
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            // ul.g:276:5: ( ( ( 'a' .. 'z' | 'A' .. 'Z' ) | '_' ) ( ( 'a' .. 'z' | 'A' .. 'Z' ) | '_' | '0' .. '9' )* )
            // ul.g:276:10: ( ( 'a' .. 'z' | 'A' .. 'Z' ) | '_' ) ( ( 'a' .. 'z' | 'A' .. 'Z' ) | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ul.g:276:41: ( ( 'a' .. 'z' | 'A' .. 'Z' ) | '_' | '0' .. '9' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')||(LA6_0>='A' && LA6_0<='Z')||LA6_0=='_'||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ul.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ID

    // $ANTLR start WHITESPACE
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            // ul.g:280:5: ( ( '\\t' | ' ' | ( '\\r' | '\\n' ) )+ )
            // ul.g:280:10: ( '\\t' | ' ' | ( '\\r' | '\\n' ) )+
            {
            // ul.g:280:10: ( '\\t' | ' ' | ( '\\r' | '\\n' ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\t' && LA7_0<='\n')||LA7_0=='\r'||LA7_0==' ') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ul.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            channel = HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHITESPACE

    // $ANTLR start COMMENT
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            // ul.g:284:5: ( '//' (~ ( '\\r' | '\\n' ) )* )
            // ul.g:284:10: '//' (~ ( '\\r' | '\\n' ) )*
            {
            match("//"); 

            // ul.g:284:15: (~ ( '\\r' | '\\n' ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFE')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ul.g:284:15: ~ ( '\\r' | '\\n' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            channel = HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMENT

    public void mTokens() throws RecognitionException {
        // ul.g:1:8: ( TYPE | IF | ELSE | WHILE | PRINT | PRINTLN | RETURN | TRUE | FALSE | SEMICOLON | OPENPARENTHESIS | CLOSEPARENTHESIS | OPENBRACKET | CLOSEBRACKET | OPENBRACE | CLOSEBRACE | COMMA | SINGLEEQUALS | STAR | PLUS | MINUS | LESSTHAN | DOUBLEEQUALS | INTEGERCONSTANT | FLOATCONSTANT | CHARACTERCONSTANT | STRINGCONSTANT | ID | WHITESPACE | COMMENT )
        int alt9=30;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // ul.g:1:10: TYPE
                {
                mTYPE(); 

                }
                break;
            case 2 :
                // ul.g:1:15: IF
                {
                mIF(); 

                }
                break;
            case 3 :
                // ul.g:1:18: ELSE
                {
                mELSE(); 

                }
                break;
            case 4 :
                // ul.g:1:23: WHILE
                {
                mWHILE(); 

                }
                break;
            case 5 :
                // ul.g:1:29: PRINT
                {
                mPRINT(); 

                }
                break;
            case 6 :
                // ul.g:1:35: PRINTLN
                {
                mPRINTLN(); 

                }
                break;
            case 7 :
                // ul.g:1:43: RETURN
                {
                mRETURN(); 

                }
                break;
            case 8 :
                // ul.g:1:50: TRUE
                {
                mTRUE(); 

                }
                break;
            case 9 :
                // ul.g:1:55: FALSE
                {
                mFALSE(); 

                }
                break;
            case 10 :
                // ul.g:1:61: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 11 :
                // ul.g:1:71: OPENPARENTHESIS
                {
                mOPENPARENTHESIS(); 

                }
                break;
            case 12 :
                // ul.g:1:87: CLOSEPARENTHESIS
                {
                mCLOSEPARENTHESIS(); 

                }
                break;
            case 13 :
                // ul.g:1:104: OPENBRACKET
                {
                mOPENBRACKET(); 

                }
                break;
            case 14 :
                // ul.g:1:116: CLOSEBRACKET
                {
                mCLOSEBRACKET(); 

                }
                break;
            case 15 :
                // ul.g:1:129: OPENBRACE
                {
                mOPENBRACE(); 

                }
                break;
            case 16 :
                // ul.g:1:139: CLOSEBRACE
                {
                mCLOSEBRACE(); 

                }
                break;
            case 17 :
                // ul.g:1:150: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 18 :
                // ul.g:1:156: SINGLEEQUALS
                {
                mSINGLEEQUALS(); 

                }
                break;
            case 19 :
                // ul.g:1:169: STAR
                {
                mSTAR(); 

                }
                break;
            case 20 :
                // ul.g:1:174: PLUS
                {
                mPLUS(); 

                }
                break;
            case 21 :
                // ul.g:1:179: MINUS
                {
                mMINUS(); 

                }
                break;
            case 22 :
                // ul.g:1:185: LESSTHAN
                {
                mLESSTHAN(); 

                }
                break;
            case 23 :
                // ul.g:1:194: DOUBLEEQUALS
                {
                mDOUBLEEQUALS(); 

                }
                break;
            case 24 :
                // ul.g:1:207: INTEGERCONSTANT
                {
                mINTEGERCONSTANT(); 

                }
                break;
            case 25 :
                // ul.g:1:223: FLOATCONSTANT
                {
                mFLOATCONSTANT(); 

                }
                break;
            case 26 :
                // ul.g:1:237: CHARACTERCONSTANT
                {
                mCHARACTERCONSTANT(); 

                }
                break;
            case 27 :
                // ul.g:1:255: STRINGCONSTANT
                {
                mSTRINGCONSTANT(); 

                }
                break;
            case 28 :
                // ul.g:1:270: ID
                {
                mID(); 

                }
                break;
            case 29 :
                // ul.g:1:273: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 30 :
                // ul.g:1:284: COMMENT
                {
                mCOMMENT(); 

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\1\uffff\13\34\10\uffff\1\55\4\uffff\1\57\5\uffff\1\34\1\61\13\34"+
        "\4\uffff\1\75\1\uffff\13\34\1\uffff\2\34\1\75\2\34\1\75\1\115\3"+
        "\34\1\121\1\75\1\122\2\34\1\uffff\1\125\1\127\1\34\2\uffff\1\75"+
        "\1\34\1\uffff\1\34\1\uffff\1\133\1\75\1\134\2\uffff";
    static final String DFA9_eofS =
        "\135\uffff";
    static final String DFA9_minS =
        "\1\11\1\146\1\141\1\150\1\164\2\157\1\154\1\150\1\162\1\145\1\162"+
        "\10\uffff\1\75\4\uffff\1\56\5\uffff\1\164\1\60\1\157\1\154\1\141"+
        "\1\162\1\157\1\151\1\163\2\151\1\164\1\165\4\uffff\1\60\1\uffff"+
        "\1\141\1\163\1\162\1\151\1\154\1\144\1\145\1\154\1\156\1\165\1\145"+
        "\1\uffff\1\164\1\145\1\60\1\156\1\145\2\60\1\145\1\164\1\162\3\60"+
        "\1\147\1\141\1\uffff\2\60\1\156\2\uffff\1\60\1\156\1\uffff\1\156"+
        "\1\uffff\3\60\2\uffff";
    static final String DFA9_maxS =
        "\1\175\1\156\1\154\1\150\1\164\2\157\1\154\1\150\1\162\1\145\1\162"+
        "\10\uffff\1\75\4\uffff\1\71\5\uffff\1\164\1\172\1\157\1\154\1\141"+
        "\1\162\1\157\1\151\1\163\2\151\1\164\1\165\4\uffff\1\172\1\uffff"+
        "\1\141\1\163\1\162\1\151\1\154\1\144\1\145\1\154\1\156\1\165\1\145"+
        "\1\uffff\1\164\1\145\1\172\1\156\1\145\2\172\1\145\1\164\1\162\3"+
        "\172\1\147\1\141\1\uffff\2\172\1\156\2\uffff\1\172\1\156\1\uffff"+
        "\1\156\1\uffff\3\172\2\uffff";
    static final String DFA9_acceptS =
        "\14\uffff\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\uffff\1\23\1"+
        "\24\1\25\1\26\1\uffff\1\32\1\33\1\34\1\35\1\36\15\uffff\1\27\1\22"+
        "\1\31\1\30\1\uffff\1\2\13\uffff\1\1\17\uffff\1\3\3\uffff\1\10\1"+
        "\11\2\uffff\1\4\1\uffff\1\5\3\uffff\1\7\1\6";
    static final String DFA9_specialS =
        "\135\uffff}>";
    static final String[] DFA9_transitionS = {
            "\2\35\2\uffff\1\35\22\uffff\1\35\1\uffff\1\33\4\uffff\1\32\1"+
            "\15\1\16\1\25\1\26\1\23\1\27\1\uffff\1\36\12\31\1\uffff\1\14"+
            "\1\30\1\24\3\uffff\32\34\1\17\1\uffff\1\20\1\uffff\1\34\1\uffff"+
            "\1\34\1\5\1\3\1\34\1\7\1\2\2\34\1\1\6\34\1\11\1\34\1\12\1\4"+
            "\1\13\1\34\1\6\1\10\3\34\1\21\1\uffff\1\22",
            "\1\40\7\uffff\1\37",
            "\1\42\12\uffff\1\41",
            "\1\43",
            "\1\44",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\54",
            "",
            "",
            "",
            "",
            "\1\56\1\uffff\12\31",
            "",
            "",
            "",
            "",
            "",
            "\1\60",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "",
            "",
            "",
            "",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "",
            "\1\111",
            "\1\112",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\113",
            "\1\114",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\116",
            "\1\117",
            "\1\120",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\123",
            "\1\124",
            "",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\13\34\1\126\16\34",
            "\1\130",
            "",
            "",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\131",
            "",
            "\1\132",
            "",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( TYPE | IF | ELSE | WHILE | PRINT | PRINTLN | RETURN | TRUE | FALSE | SEMICOLON | OPENPARENTHESIS | CLOSEPARENTHESIS | OPENBRACKET | CLOSEBRACKET | OPENBRACE | CLOSEBRACE | COMMA | SINGLEEQUALS | STAR | PLUS | MINUS | LESSTHAN | DOUBLEEQUALS | INTEGERCONSTANT | FLOATCONSTANT | CHARACTERCONSTANT | STRINGCONSTANT | ID | WHITESPACE | COMMENT );";
        }
    }
 

}