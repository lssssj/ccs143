// Generated from Cool.g4 by ANTLR 4.9.2
package cool;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CLASS=1, ELSE=2, FI=3, IF=4, INHERITS=5, ISVOID=6, LET=7, LOOP=8, POOL=9, 
		THEN=10, WHILE=11, CASE=12, ESAC=13, NEW=14, OF=15, NOT=16, IN=17, TYPEID=18, 
		OBJECTID=19, COLON=20, ASSIGN=21, DOT=22, LBRACE=23, RBRACE=24, LPAREN=25, 
		RPAREN=26, DARROW=27, SEMICOLON=28, ATSYM=29, COMMA=30, PLUS=31, MINUS=32, 
		STAR=33, SLASH=34, LT=35, LE=36, EQUALS=37, NEGATE=38, BOOL_CONST=39, 
		TRUE_CONST=40, FALSE_CONST=41, INTEGER=42, STRING=43, WS=44;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CLASS", "ELSE", "FI", "IF", "INHERITS", "ISVOID", "LET", "LOOP", "POOL", 
			"THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "NOT", "IN", "TYPEID", 
			"OBJECTID", "COLON", "ASSIGN", "DOT", "LBRACE", "RBRACE", "LPAREN", "RPAREN", 
			"DARROW", "SEMICOLON", "ATSYM", "COMMA", "PLUS", "MINUS", "STAR", "SLASH", 
			"LT", "LE", "EQUALS", "NEGATE", "BOOL_CONST", "TRUE_CONST", "FALSE_CONST", 
			"INTEGER", "STRING", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "'in'", null, null, "':'", "'<-'", "'.'", 
			"'{'", "'}'", "'('", "')'", "'->'", "';'", "'@'", "','", "'+'", "'-'", 
			"'*'", "'/'", "'<'", "'<='", "'='", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CLASS", "ELSE", "FI", "IF", "INHERITS", "ISVOID", "LET", "LOOP", 
			"POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "NOT", "IN", "TYPEID", 
			"OBJECTID", "COLON", "ASSIGN", "DOT", "LBRACE", "RBRACE", "LPAREN", "RPAREN", 
			"DARROW", "SEMICOLON", "ATSYM", "COMMA", "PLUS", "MINUS", "STAR", "SLASH", 
			"LT", "LE", "EQUALS", "NEGATE", "BOOL_CONST", "TRUE_CONST", "FALSE_CONST", 
			"INTEGER", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public String fileName;


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Cool.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2.\u0109\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\23\3\23\7\23\u00b0\n\23\f\23\16\23\u00b3\13\23\3\24\6"+
		"\24\u00b6\n\24\r\24\16\24\u00b7\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3(\3"+
		"(\5(\u00e5\n(\3)\3)\3)\3)\5)\u00eb\n)\3*\3*\3*\3*\5*\u00f1\n*\3+\6+\u00f4"+
		"\n+\r+\16+\u00f5\3,\3,\3,\3,\7,\u00fc\n,\f,\16,\u00ff\13,\3,\3,\3-\6-"+
		"\u0104\n-\r-\16-\u0105\3-\3-\2\2.\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.\3\2\32"+
		"\4\2EEee\4\2NNnn\4\2CCcc\4\2UUuu\4\2GGgg\4\2HHhh\4\2KKkk\4\2PPpp\4\2J"+
		"Jjj\4\2TTtt\4\2VVvv\4\2XXxx\4\2QQqq\4\2FFff\4\2RRrr\4\2YYyy\3\2C\\\4\2"+
		"aac|\3\2c|\4\2WWww\6\2GGUUgguu\3\2\62;\3\2$$\5\2\13\f\17\17\"\"\2\u0111"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\3[\3\2\2\2\5a\3\2\2\2\7f\3\2\2\2\ti\3"+
		"\2\2\2\13l\3\2\2\2\ru\3\2\2\2\17|\3\2\2\2\21\u0080\3\2\2\2\23\u0085\3"+
		"\2\2\2\25\u008a\3\2\2\2\27\u008f\3\2\2\2\31\u0095\3\2\2\2\33\u009a\3\2"+
		"\2\2\35\u009f\3\2\2\2\37\u00a3\3\2\2\2!\u00a6\3\2\2\2#\u00aa\3\2\2\2%"+
		"\u00ad\3\2\2\2\'\u00b5\3\2\2\2)\u00b9\3\2\2\2+\u00bb\3\2\2\2-\u00be\3"+
		"\2\2\2/\u00c0\3\2\2\2\61\u00c2\3\2\2\2\63\u00c4\3\2\2\2\65\u00c6\3\2\2"+
		"\2\67\u00c8\3\2\2\29\u00cb\3\2\2\2;\u00cd\3\2\2\2=\u00cf\3\2\2\2?\u00d1"+
		"\3\2\2\2A\u00d3\3\2\2\2C\u00d5\3\2\2\2E\u00d7\3\2\2\2G\u00d9\3\2\2\2I"+
		"\u00db\3\2\2\2K\u00de\3\2\2\2M\u00e0\3\2\2\2O\u00e4\3\2\2\2Q\u00ea\3\2"+
		"\2\2S\u00f0\3\2\2\2U\u00f3\3\2\2\2W\u00f7\3\2\2\2Y\u0103\3\2\2\2[\\\t"+
		"\2\2\2\\]\t\3\2\2]^\t\4\2\2^_\t\5\2\2_`\t\5\2\2`\4\3\2\2\2ab\t\6\2\2b"+
		"c\t\3\2\2cd\t\5\2\2de\t\6\2\2e\6\3\2\2\2fg\t\7\2\2gh\t\b\2\2h\b\3\2\2"+
		"\2ij\t\b\2\2jk\t\7\2\2k\n\3\2\2\2lm\t\b\2\2mn\t\t\2\2no\t\n\2\2op\t\6"+
		"\2\2pq\t\13\2\2qr\t\b\2\2rs\t\f\2\2st\t\5\2\2t\f\3\2\2\2uv\t\b\2\2vw\t"+
		"\5\2\2wx\t\r\2\2xy\t\16\2\2yz\t\b\2\2z{\t\17\2\2{\16\3\2\2\2|}\t\3\2\2"+
		"}~\t\6\2\2~\177\t\f\2\2\177\20\3\2\2\2\u0080\u0081\t\3\2\2\u0081\u0082"+
		"\t\16\2\2\u0082\u0083\t\16\2\2\u0083\u0084\t\20\2\2\u0084\22\3\2\2\2\u0085"+
		"\u0086\t\20\2\2\u0086\u0087\t\16\2\2\u0087\u0088\t\16\2\2\u0088\u0089"+
		"\t\3\2\2\u0089\24\3\2\2\2\u008a\u008b\t\f\2\2\u008b\u008c\t\n\2\2\u008c"+
		"\u008d\t\6\2\2\u008d\u008e\t\t\2\2\u008e\26\3\2\2\2\u008f\u0090\t\21\2"+
		"\2\u0090\u0091\t\n\2\2\u0091\u0092\t\b\2\2\u0092\u0093\t\3\2\2\u0093\u0094"+
		"\t\6\2\2\u0094\30\3\2\2\2\u0095\u0096\t\2\2\2\u0096\u0097\t\4\2\2\u0097"+
		"\u0098\t\5\2\2\u0098\u0099\t\6\2\2\u0099\32\3\2\2\2\u009a\u009b\t\6\2"+
		"\2\u009b\u009c\t\5\2\2\u009c\u009d\t\4\2\2\u009d\u009e\t\2\2\2\u009e\34"+
		"\3\2\2\2\u009f\u00a0\t\t\2\2\u00a0\u00a1\t\6\2\2\u00a1\u00a2\t\21\2\2"+
		"\u00a2\36\3\2\2\2\u00a3\u00a4\t\16\2\2\u00a4\u00a5\t\7\2\2\u00a5 \3\2"+
		"\2\2\u00a6\u00a7\t\t\2\2\u00a7\u00a8\t\16\2\2\u00a8\u00a9\t\f\2\2\u00a9"+
		"\"\3\2\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7p\2\2\u00ac$\3\2\2\2\u00ad"+
		"\u00b1\t\22\2\2\u00ae\u00b0\t\23\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b3\3"+
		"\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2&\3\2\2\2\u00b3\u00b1"+
		"\3\2\2\2\u00b4\u00b6\t\24\2\2\u00b5\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2"+
		"\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8(\3\2\2\2\u00b9\u00ba\7"+
		"<\2\2\u00ba*\3\2\2\2\u00bb\u00bc\7>\2\2\u00bc\u00bd\7/\2\2\u00bd,\3\2"+
		"\2\2\u00be\u00bf\7\60\2\2\u00bf.\3\2\2\2\u00c0\u00c1\7}\2\2\u00c1\60\3"+
		"\2\2\2\u00c2\u00c3\7\177\2\2\u00c3\62\3\2\2\2\u00c4\u00c5\7*\2\2\u00c5"+
		"\64\3\2\2\2\u00c6\u00c7\7+\2\2\u00c7\66\3\2\2\2\u00c8\u00c9\7/\2\2\u00c9"+
		"\u00ca\7@\2\2\u00ca8\3\2\2\2\u00cb\u00cc\7=\2\2\u00cc:\3\2\2\2\u00cd\u00ce"+
		"\7B\2\2\u00ce<\3\2\2\2\u00cf\u00d0\7.\2\2\u00d0>\3\2\2\2\u00d1\u00d2\7"+
		"-\2\2\u00d2@\3\2\2\2\u00d3\u00d4\7/\2\2\u00d4B\3\2\2\2\u00d5\u00d6\7,"+
		"\2\2\u00d6D\3\2\2\2\u00d7\u00d8\7\61\2\2\u00d8F\3\2\2\2\u00d9\u00da\7"+
		">\2\2\u00daH\3\2\2\2\u00db\u00dc\7>\2\2\u00dc\u00dd\7?\2\2\u00ddJ\3\2"+
		"\2\2\u00de\u00df\7?\2\2\u00dfL\3\2\2\2\u00e0\u00e1\7\u0080\2\2\u00e1N"+
		"\3\2\2\2\u00e2\u00e5\5Q)\2\u00e3\u00e5\5S*\2\u00e4\u00e2\3\2\2\2\u00e4"+
		"\u00e3\3\2\2\2\u00e5P\3\2\2\2\u00e6\u00e7\7v\2\2\u00e7\u00e8\t\13\2\2"+
		"\u00e8\u00eb\t\25\2\2\u00e9\u00eb\t\6\2\2\u00ea\u00e6\3\2\2\2\u00ea\u00e9"+
		"\3\2\2\2\u00ebR\3\2\2\2\u00ec\u00ed\7h\2\2\u00ed\u00ee\t\4\2\2\u00ee\u00f1"+
		"\t\3\2\2\u00ef\u00f1\t\26\2\2\u00f0\u00ec\3\2\2\2\u00f0\u00ef\3\2\2\2"+
		"\u00f1T\3\2\2\2\u00f2\u00f4\t\27\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f5\3"+
		"\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6V\3\2\2\2\u00f7\u00fd"+
		"\7$\2\2\u00f8\u00fc\n\30\2\2\u00f9\u00fa\7^\2\2\u00fa\u00fc\13\2\2\2\u00fb"+
		"\u00f8\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2"+
		"\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100"+
		"\u0101\7$\2\2\u0101X\3\2\2\2\u0102\u0104\t\31\2\2\u0103\u0102\3\2\2\2"+
		"\u0104\u0105\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107"+
		"\3\2\2\2\u0107\u0108\b-\2\2\u0108Z\3\2\2\2\f\2\u00b1\u00b7\u00e4\u00ea"+
		"\u00f0\u00f5\u00fb\u00fd\u0105\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}