// Generated from LancerSpecParser.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LancerSpecParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TOKEN_REF=1, RULE_REF=2, LEXER_CHAR_SET=3, DOC_COMMENT=4, BLOCK_COMMENT=5, 
		LINE_COMMENT=6, INT=7, STRING_LITERAL=8, UNTERMINATED_STRING_LITERAL=9, 
		BEGIN_ACTION=10, OPTIONS=11, TOKENS=12, CHANNELS=13, PERCENTAGE=14, NEGATE=15, 
		IMPORT=16, FRAGMENT=17, SCHEMA=18, EXPR=19, STATEMENT=20, LEXER=21, PARSER=22, 
		GRAMMAR=23, PROTECTED=24, PUBLIC=25, PRIVATE=26, RETURNS=27, LOCALS=28, 
		THROWS=29, CATCH=30, FINALLY=31, MODE=32, BEGIN_ERR=33, BEGIN_REP=34, 
		BEGIN_WHT=35, COLON=36, COLONCOLON=37, COMMA=38, SEMI=39, LPAREN=40, RPAREN=41, 
		LBRACE=42, RBRACE=43, LBRACK=44, RBRACK=45, RARROW=46, LT=47, GT=48, ASSIGN=49, 
		QUESTION=50, STAR=51, PLUS_ASSIGN=52, PLUS=53, OR=54, DOLLAR=55, RANGE=56, 
		DOT=57, AT=58, POUND=59, NOT=60, ID=61, WS=62, ERRCHAR=63, END_ACTION=64, 
		UNTERMINATED_ACTION=65, ACTION_CONTENT=66, END_ERR_DECL=67, UNTERMINATED_ERR_DECL=68, 
		ERR_CONTENT=69, END_WGHT_DECL=70, WGHT_DECL=71, WGHT_CONTENT=72, UNTERMINATED_CHAR_SET=73;
	public static final int
		RULE_grammarSpec = 0, RULE_grammarDecl = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_action_ = 8, RULE_tokensSpec = 9, RULE_actionBlock = 10, 
		RULE_idList = 11, RULE_weightBlock = 12, RULE_repetitionBlock = 13, RULE_errorBlock = 14, 
		RULE_argActionBlock = 15, RULE_arg = 16, RULE_rules = 17, RULE_ruleSpec = 18, 
		RULE_parserRuleSpec = 19, RULE_ruleReturns = 20, RULE_localsSpec = 21, 
		RULE_ruleModifier = 22, RULE_ruleBlock = 23, RULE_ruleAltList = 24, RULE_labeledAlt = 25, 
		RULE_altList = 26, RULE_alternative = 27, RULE_element = 28, RULE_predicate = 29, 
		RULE_expression = 30, RULE_variableAssignment = 31, RULE_ebnf = 32, RULE_ebnfSuffix = 33, 
		RULE_lexerAtom = 34, RULE_atom = 35, RULE_weightage = 36, RULE_precedence = 37, 
		RULE_notSet = 38, RULE_blockSet = 39, RULE_setElement = 40, RULE_block = 41, 
		RULE_characterRange = 42, RULE_terminal = 43, RULE_compIdentifier = 44, 
		RULE_identifier = 45, RULE_lexerRuleSpec = 46, RULE_lexerRuleBlock = 47, 
		RULE_lexerAltList = 48, RULE_lexerAlt = 49, RULE_lexerElements = 50, RULE_lexerElement = 51, 
		RULE_lexerBlock = 52, RULE_grammarOperator = 53;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarSpec", "grammarDecl", "prequelConstruct", "optionsSpec", "option", 
			"optionValue", "delegateGrammars", "delegateGrammar", "action_", "tokensSpec", 
			"actionBlock", "idList", "weightBlock", "repetitionBlock", "errorBlock", 
			"argActionBlock", "arg", "rules", "ruleSpec", "parserRuleSpec", "ruleReturns", 
			"localsSpec", "ruleModifier", "ruleBlock", "ruleAltList", "labeledAlt", 
			"altList", "alternative", "element", "predicate", "expression", "variableAssignment", 
			"ebnf", "ebnfSuffix", "lexerAtom", "atom", "weightage", "precedence", 
			"notSet", "blockSet", "setElement", "block", "characterRange", "terminal", 
			"compIdentifier", "identifier", "lexerRuleSpec", "lexerRuleBlock", "lexerAltList", 
			"lexerAlt", "lexerElements", "lexerElement", "lexerBlock", "grammarOperator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "'%'", "'!'", "'import'", "'fragment'", "'schema'", "'expr'", 
			"'statement'", "'lexer'", "'parser'", "'grammar'", "'protected'", "'public'", 
			"'private'", "'returns'", "'locals'", "'throws'", "'catch'", "'finally'", 
			"'mode'", "'_e('", "'_r('", "'_w('"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TOKEN_REF", "RULE_REF", "LEXER_CHAR_SET", "DOC_COMMENT", "BLOCK_COMMENT", 
			"LINE_COMMENT", "INT", "STRING_LITERAL", "UNTERMINATED_STRING_LITERAL", 
			"BEGIN_ACTION", "OPTIONS", "TOKENS", "CHANNELS", "PERCENTAGE", "NEGATE", 
			"IMPORT", "FRAGMENT", "SCHEMA", "EXPR", "STATEMENT", "LEXER", "PARSER", 
			"GRAMMAR", "PROTECTED", "PUBLIC", "PRIVATE", "RETURNS", "LOCALS", "THROWS", 
			"CATCH", "FINALLY", "MODE", "BEGIN_ERR", "BEGIN_REP", "BEGIN_WHT", "COLON", 
			"COLONCOLON", "COMMA", "SEMI", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"LBRACK", "RBRACK", "RARROW", "LT", "GT", "ASSIGN", "QUESTION", "STAR", 
			"PLUS_ASSIGN", "PLUS", "OR", "DOLLAR", "RANGE", "DOT", "AT", "POUND", 
			"NOT", "ID", "WS", "ERRCHAR", "END_ACTION", "UNTERMINATED_ACTION", "ACTION_CONTENT", 
			"END_ERR_DECL", "UNTERMINATED_ERR_DECL", "ERR_CONTENT", "END_WGHT_DECL", 
			"WGHT_DECL", "WGHT_CONTENT", "UNTERMINATED_CHAR_SET"
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

	@Override
	public String getGrammarFileName() { return "LancerSpecParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LancerSpecParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GrammarSpecContext extends ParserRuleContext {
		public GrammarDeclContext grammarDecl() {
			return getRuleContext(GrammarDeclContext.class,0);
		}
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(LancerSpecParser.EOF, 0); }
		public List<PrequelConstructContext> prequelConstruct() {
			return getRuleContexts(PrequelConstructContext.class);
		}
		public PrequelConstructContext prequelConstruct(int i) {
			return getRuleContext(PrequelConstructContext.class,i);
		}
		public GrammarSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterGrammarSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitGrammarSpec(this);
		}
	}

	public final GrammarSpecContext grammarSpec() throws RecognitionException {
		GrammarSpecContext _localctx = new GrammarSpecContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_grammarSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			grammarDecl();
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 288230376151783424L) != 0)) {
				{
				{
				setState(109);
				prequelConstruct();
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			rules();
			setState(116);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GrammarDeclContext extends ParserRuleContext {
		public TerminalNode GRAMMAR() { return getToken(LancerSpecParser.GRAMMAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LancerSpecParser.SEMI, 0); }
		public GrammarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterGrammarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitGrammarDecl(this);
		}
	}

	public final GrammarDeclContext grammarDecl() throws RecognitionException {
		GrammarDeclContext _localctx = new GrammarDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_grammarDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(GRAMMAR);
			setState(119);
			identifier();
			setState(120);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrequelConstructContext extends ParserRuleContext {
		public OptionsSpecContext optionsSpec() {
			return getRuleContext(OptionsSpecContext.class,0);
		}
		public DelegateGrammarsContext delegateGrammars() {
			return getRuleContext(DelegateGrammarsContext.class,0);
		}
		public Action_Context action_() {
			return getRuleContext(Action_Context.class,0);
		}
		public TokensSpecContext tokensSpec() {
			return getRuleContext(TokensSpecContext.class,0);
		}
		public PrequelConstructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prequelConstruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterPrequelConstruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitPrequelConstruct(this);
		}
	}

	public final PrequelConstructContext prequelConstruct() throws RecognitionException {
		PrequelConstructContext _localctx = new PrequelConstructContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_prequelConstruct);
		try {
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPTIONS:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				optionsSpec();
				}
				break;
			case IMPORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				delegateGrammars();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				action_();
				}
				break;
			case TOKENS:
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				tokensSpec();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionsSpecContext extends ParserRuleContext {
		public TerminalNode OPTIONS() { return getToken(LancerSpecParser.OPTIONS, 0); }
		public TerminalNode RBRACE() { return getToken(LancerSpecParser.RBRACE, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(LancerSpecParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(LancerSpecParser.SEMI, i);
		}
		public OptionsSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionsSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterOptionsSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitOptionsSpec(this);
		}
	}

	public final OptionsSpecContext optionsSpec() throws RecognitionException {
		OptionsSpecContext _localctx = new OptionsSpecContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_optionsSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(OPTIONS);
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2305843009213693958L) != 0)) {
				{
				{
				setState(129);
				option();
				setState(130);
				match(SEMI);
				}
				}
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(137);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(LancerSpecParser.ASSIGN, 0); }
		public OptionValueContext optionValue() {
			return getRuleContext(OptionValueContext.class,0);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitOption(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			identifier();
			setState(140);
			match(ASSIGN);
			setState(141);
			optionValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionValueContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(LancerSpecParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(LancerSpecParser.DOT, i);
		}
		public TerminalNode STRING_LITERAL() { return getToken(LancerSpecParser.STRING_LITERAL, 0); }
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public TerminalNode INT() { return getToken(LancerSpecParser.INT, 0); }
		public OptionValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterOptionValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitOptionValue(this);
		}
	}

	public final OptionValueContext optionValue() throws RecognitionException {
		OptionValueContext _localctx = new OptionValueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_optionValue);
		int _la;
		try {
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				identifier();
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(144);
					match(DOT);
					setState(145);
					identifier();
					}
					}
					setState(150);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(STRING_LITERAL);
				}
				break;
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				actionBlock();
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
				match(INT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DelegateGrammarsContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(LancerSpecParser.IMPORT, 0); }
		public List<DelegateGrammarContext> delegateGrammar() {
			return getRuleContexts(DelegateGrammarContext.class);
		}
		public DelegateGrammarContext delegateGrammar(int i) {
			return getRuleContext(DelegateGrammarContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(LancerSpecParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LancerSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LancerSpecParser.COMMA, i);
		}
		public DelegateGrammarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delegateGrammars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterDelegateGrammars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitDelegateGrammars(this);
		}
	}

	public final DelegateGrammarsContext delegateGrammars() throws RecognitionException {
		DelegateGrammarsContext _localctx = new DelegateGrammarsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_delegateGrammars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(IMPORT);
			setState(157);
			delegateGrammar();
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(158);
				match(COMMA);
				setState(159);
				delegateGrammar();
				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DelegateGrammarContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(LancerSpecParser.ASSIGN, 0); }
		public DelegateGrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delegateGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterDelegateGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitDelegateGrammar(this);
		}
	}

	public final DelegateGrammarContext delegateGrammar() throws RecognitionException {
		DelegateGrammarContext _localctx = new DelegateGrammarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_delegateGrammar);
		try {
			setState(172);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				identifier();
				setState(168);
				match(ASSIGN);
				setState(169);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				identifier();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Action_Context extends ParserRuleContext {
		public TerminalNode AT() { return getToken(LancerSpecParser.AT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public Action_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterAction_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitAction_(this);
		}
	}

	public final Action_Context action_() throws RecognitionException {
		Action_Context _localctx = new Action_Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_action_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(AT);
			setState(175);
			identifier();
			setState(176);
			actionBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TokensSpecContext extends ParserRuleContext {
		public TerminalNode TOKENS() { return getToken(LancerSpecParser.TOKENS, 0); }
		public TerminalNode RBRACE() { return getToken(LancerSpecParser.RBRACE, 0); }
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public TokensSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tokensSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterTokensSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitTokensSpec(this);
		}
	}

	public final TokensSpecContext tokensSpec() throws RecognitionException {
		TokensSpecContext _localctx = new TokensSpecContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tokensSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(TOKENS);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2305843009213693958L) != 0)) {
				{
				setState(179);
				idList();
				}
			}

			setState(182);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActionBlockContext extends ParserRuleContext {
		public TerminalNode BEGIN_ACTION() { return getToken(LancerSpecParser.BEGIN_ACTION, 0); }
		public TerminalNode END_ACTION() { return getToken(LancerSpecParser.END_ACTION, 0); }
		public List<TerminalNode> ACTION_CONTENT() { return getTokens(LancerSpecParser.ACTION_CONTENT); }
		public TerminalNode ACTION_CONTENT(int i) {
			return getToken(LancerSpecParser.ACTION_CONTENT, i);
		}
		public ActionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterActionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitActionBlock(this);
		}
	}

	public final ActionBlockContext actionBlock() throws RecognitionException {
		ActionBlockContext _localctx = new ActionBlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_actionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(BEGIN_ACTION);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ACTION_CONTENT) {
				{
				{
				setState(185);
				match(ACTION_CONTENT);
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(191);
			match(END_ACTION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdListContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LancerSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LancerSpecParser.COMMA, i);
		}
		public IdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterIdList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitIdList(this);
		}
	}

	public final IdListContext idList() throws RecognitionException {
		IdListContext _localctx = new IdListContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_idList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			identifier();
			setState(198);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(194);
					match(COMMA);
					setState(195);
					identifier();
					}
					} 
				}
				setState(200);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(201);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WeightBlockContext extends ParserRuleContext {
		public TerminalNode BEGIN_WHT() { return getToken(LancerSpecParser.BEGIN_WHT, 0); }
		public TerminalNode END_WGHT_DECL() { return getToken(LancerSpecParser.END_WGHT_DECL, 0); }
		public List<TerminalNode> WGHT_CONTENT() { return getTokens(LancerSpecParser.WGHT_CONTENT); }
		public TerminalNode WGHT_CONTENT(int i) {
			return getToken(LancerSpecParser.WGHT_CONTENT, i);
		}
		public WeightBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weightBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterWeightBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitWeightBlock(this);
		}
	}

	public final WeightBlockContext weightBlock() throws RecognitionException {
		WeightBlockContext _localctx = new WeightBlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_weightBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(BEGIN_WHT);
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WGHT_CONTENT) {
				{
				{
				setState(205);
				match(WGHT_CONTENT);
				}
				}
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(211);
			match(END_WGHT_DECL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RepetitionBlockContext extends ParserRuleContext {
		public TerminalNode BEGIN_REP() { return getToken(LancerSpecParser.BEGIN_REP, 0); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(LancerSpecParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LancerSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LancerSpecParser.COMMA, i);
		}
		public RepetitionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repetitionBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterRepetitionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitRepetitionBlock(this);
		}
	}

	public final RepetitionBlockContext repetitionBlock() throws RecognitionException {
		RepetitionBlockContext _localctx = new RepetitionBlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_repetitionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(BEGIN_REP);
			setState(214);
			arg();
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(215);
				match(COMMA);
				setState(216);
				arg();
				}
				}
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(222);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ErrorBlockContext extends ParserRuleContext {
		public TerminalNode BEGIN_ERR() { return getToken(LancerSpecParser.BEGIN_ERR, 0); }
		public TerminalNode END_ERR_DECL() { return getToken(LancerSpecParser.END_ERR_DECL, 0); }
		public List<TerminalNode> ERR_CONTENT() { return getTokens(LancerSpecParser.ERR_CONTENT); }
		public TerminalNode ERR_CONTENT(int i) {
			return getToken(LancerSpecParser.ERR_CONTENT, i);
		}
		public ErrorBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_errorBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterErrorBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitErrorBlock(this);
		}
	}

	public final ErrorBlockContext errorBlock() throws RecognitionException {
		ErrorBlockContext _localctx = new ErrorBlockContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_errorBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(BEGIN_ERR);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ERR_CONTENT) {
				{
				{
				setState(225);
				match(ERR_CONTENT);
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(231);
			match(END_ERR_DECL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgActionBlockContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(LancerSpecParser.LBRACK, 0); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public TerminalNode RBRACK() { return getToken(LancerSpecParser.RBRACK, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LancerSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LancerSpecParser.COMMA, i);
		}
		public ArgActionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argActionBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterArgActionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitArgActionBlock(this);
		}
	}

	public final ArgActionBlockContext argActionBlock() throws RecognitionException {
		ArgActionBlockContext _localctx = new ArgActionBlockContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_argActionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(LBRACK);
			setState(234);
			arg();
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(235);
				match(COMMA);
				setState(236);
				arg();
				}
				}
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(242);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgContext extends ParserRuleContext {
		public CompIdentifierContext compIdentifier() {
			return getRuleContext(CompIdentifierContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(LancerSpecParser.STRING_LITERAL, 0); }
		public TerminalNode INT() { return getToken(LancerSpecParser.INT, 0); }
		public RepetitionBlockContext repetitionBlock() {
			return getRuleContext(RepetitionBlockContext.class,0);
		}
		public List<GrammarOperatorContext> grammarOperator() {
			return getRuleContexts(GrammarOperatorContext.class);
		}
		public GrammarOperatorContext grammarOperator(int i) {
			return getRuleContext(GrammarOperatorContext.class,i);
		}
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arg);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case ID:
				{
				setState(244);
				compIdentifier();
				}
				break;
			case STRING_LITERAL:
				{
				setState(245);
				match(STRING_LITERAL);
				}
				break;
			case INT:
				{
				setState(246);
				match(INT);
				}
				break;
			case BEGIN_REP:
				{
				setState(247);
				repetitionBlock();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(250);
					grammarOperator();
					setState(251);
					arg();
					}
					} 
				}
				setState(257);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RulesContext extends ParserRuleContext {
		public List<RuleSpecContext> ruleSpec() {
			return getRuleContexts(RuleSpecContext.class);
		}
		public RuleSpecContext ruleSpec(int i) {
			return getRuleContext(RuleSpecContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitRules(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FRAGMENT || _la==ID) {
				{
				{
				setState(258);
				ruleSpec();
				}
				}
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleSpecContext extends ParserRuleContext {
		public ParserRuleSpecContext parserRuleSpec() {
			return getRuleContext(ParserRuleSpecContext.class,0);
		}
		public LexerRuleSpecContext lexerRuleSpec() {
			return getRuleContext(LexerRuleSpecContext.class,0);
		}
		public RuleSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterRuleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitRuleSpec(this);
		}
	}

	public final RuleSpecContext ruleSpec() throws RecognitionException {
		RuleSpecContext _localctx = new RuleSpecContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ruleSpec);
		try {
			setState(266);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				parserRuleSpec();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(265);
				lexerRuleSpec();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParserRuleSpecContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LancerSpecParser.ID, 0); }
		public TerminalNode COLON() { return getToken(LancerSpecParser.COLON, 0); }
		public RuleBlockContext ruleBlock() {
			return getRuleContext(RuleBlockContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LancerSpecParser.SEMI, 0); }
		public RuleReturnsContext ruleReturns() {
			return getRuleContext(RuleReturnsContext.class,0);
		}
		public LocalsSpecContext localsSpec() {
			return getRuleContext(LocalsSpecContext.class,0);
		}
		public RuleModifierContext ruleModifier() {
			return getRuleContext(RuleModifierContext.class,0);
		}
		public ArgActionBlockContext argActionBlock() {
			return getRuleContext(ArgActionBlockContext.class,0);
		}
		public ParserRuleSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parserRuleSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterParserRuleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitParserRuleSpec(this);
		}
	}

	public final ParserRuleSpecContext parserRuleSpec() throws RecognitionException {
		ParserRuleSpecContext _localctx = new ParserRuleSpecContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_parserRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FRAGMENT) {
				{
				setState(268);
				ruleModifier();
				}
			}

			setState(271);
			match(ID);
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(272);
				argActionBlock();
				}
			}

			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(275);
				ruleReturns();
				setState(276);
				localsSpec();
				}
				break;
			case 2:
				{
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOCALS) {
					{
					setState(278);
					localsSpec();
					}
				}

				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(281);
					ruleReturns();
					}
				}

				}
				break;
			}
			setState(286);
			match(COLON);
			setState(287);
			ruleBlock();
			setState(288);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleReturnsContext extends ParserRuleContext {
		public TerminalNode RETURNS() { return getToken(LancerSpecParser.RETURNS, 0); }
		public ArgActionBlockContext argActionBlock() {
			return getRuleContext(ArgActionBlockContext.class,0);
		}
		public RuleReturnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleReturns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterRuleReturns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitRuleReturns(this);
		}
	}

	public final RuleReturnsContext ruleReturns() throws RecognitionException {
		RuleReturnsContext _localctx = new RuleReturnsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_ruleReturns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(RETURNS);
			setState(291);
			argActionBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocalsSpecContext extends ParserRuleContext {
		public TerminalNode LOCALS() { return getToken(LancerSpecParser.LOCALS, 0); }
		public ArgActionBlockContext argActionBlock() {
			return getRuleContext(ArgActionBlockContext.class,0);
		}
		public LocalsSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localsSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLocalsSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLocalsSpec(this);
		}
	}

	public final LocalsSpecContext localsSpec() throws RecognitionException {
		LocalsSpecContext _localctx = new LocalsSpecContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_localsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(LOCALS);
			setState(294);
			argActionBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleModifierContext extends ParserRuleContext {
		public TerminalNode FRAGMENT() { return getToken(LancerSpecParser.FRAGMENT, 0); }
		public RuleModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterRuleModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitRuleModifier(this);
		}
	}

	public final RuleModifierContext ruleModifier() throws RecognitionException {
		RuleModifierContext _localctx = new RuleModifierContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_ruleModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(FRAGMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleBlockContext extends ParserRuleContext {
		public RuleAltListContext ruleAltList() {
			return getRuleContext(RuleAltListContext.class,0);
		}
		public RuleBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterRuleBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitRuleBlock(this);
		}
	}

	public final RuleBlockContext ruleBlock() throws RecognitionException {
		RuleBlockContext _localctx = new RuleBlockContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ruleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			ruleAltList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleAltListContext extends ParserRuleContext {
		public List<LabeledAltContext> labeledAlt() {
			return getRuleContexts(LabeledAltContext.class);
		}
		public LabeledAltContext labeledAlt(int i) {
			return getRuleContext(LabeledAltContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(LancerSpecParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(LancerSpecParser.OR, i);
		}
		public RuleAltListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAltList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterRuleAltList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitRuleAltList(this);
		}
	}

	public final RuleAltListContext ruleAltList() throws RecognitionException {
		RuleAltListContext _localctx = new RuleAltListContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_ruleAltList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			labeledAlt();
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(301);
				match(OR);
				setState(302);
				labeledAlt();
				}
				}
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabeledAltContext extends ParserRuleContext {
		public AlternativeContext alternative() {
			return getRuleContext(AlternativeContext.class,0);
		}
		public TerminalNode POUND() { return getToken(LancerSpecParser.POUND, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public LabeledAltContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledAlt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLabeledAlt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLabeledAlt(this);
		}
	}

	public final LabeledAltContext labeledAlt() throws RecognitionException {
		LabeledAltContext _localctx = new LabeledAltContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_labeledAlt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			alternative();
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POUND) {
				{
				setState(309);
				match(POUND);
				setState(310);
				identifier();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AltListContext extends ParserRuleContext {
		public List<AlternativeContext> alternative() {
			return getRuleContexts(AlternativeContext.class);
		}
		public AlternativeContext alternative(int i) {
			return getRuleContext(AlternativeContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(LancerSpecParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(LancerSpecParser.OR, i);
		}
		public AltListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_altList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterAltList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitAltList(this);
		}
	}

	public final AltListContext altList() throws RecognitionException {
		AltListContext _localctx = new AltListContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_altList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			alternative();
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(314);
				match(OR);
				setState(315);
				alternative();
				}
				}
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlternativeContext extends ParserRuleContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public AlternativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterAlternative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitAlternative(this);
		}
	}

	public final AlternativeContext alternative() throws RecognitionException {
		AlternativeContext _localctx = new AlternativeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_alternative);
		int _la;
		try {
			setState(327);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case INT:
			case STRING_LITERAL:
			case BEGIN_ACTION:
			case BEGIN_ERR:
			case BEGIN_REP:
			case BEGIN_WHT:
			case LPAREN:
			case LBRACE:
			case AT:
			case NOT:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(322); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(321);
					element();
					}
					}
					setState(324); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 3747000447659935110L) != 0) );
				}
				break;
			case SEMI:
			case RPAREN:
			case OR:
			case POUND:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementContext extends ParserRuleContext {
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public WeightBlockContext weightBlock() {
			return getRuleContext(WeightBlockContext.class,0);
		}
		public ErrorBlockContext errorBlock() {
			return getRuleContext(ErrorBlockContext.class,0);
		}
		public RepetitionBlockContext repetitionBlock() {
			return getRuleContext(RepetitionBlockContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public EbnfSuffixContext ebnfSuffix() {
			return getRuleContext(EbnfSuffixContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public EbnfContext ebnf() {
			return getRuleContext(EbnfContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitElement(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_element);
		int _la;
		try {
			setState(343);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				actionBlock();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
				predicate();
				}
				break;
			case BEGIN_WHT:
				enterOuterAlt(_localctx, 3);
				{
				setState(331);
				weightBlock();
				}
				break;
			case BEGIN_ERR:
				enterOuterAlt(_localctx, 4);
				{
				setState(332);
				errorBlock();
				}
				break;
			case BEGIN_REP:
				enterOuterAlt(_localctx, 5);
				{
				setState(333);
				repetitionBlock();
				}
				break;
			case TOKEN_REF:
			case RULE_REF:
			case ID:
				enterOuterAlt(_localctx, 6);
				{
				setState(334);
				expression();
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12384898975268864L) != 0)) {
					{
					setState(335);
					ebnfSuffix();
					}
				}

				}
				break;
			case INT:
			case STRING_LITERAL:
			case AT:
			case NOT:
				enterOuterAlt(_localctx, 7);
				{
				setState(338);
				atom();
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12384898975268864L) != 0)) {
					{
					setState(339);
					ebnfSuffix();
					}
				}

				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 8);
				{
				setState(342);
				ebnf();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicateContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(LancerSpecParser.LBRACE, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(LancerSpecParser.RBRACE, 0); }
		public TerminalNode QUESTION() { return getToken(LancerSpecParser.QUESTION, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			match(LBRACE);
			setState(346);
			arg();
			setState(347);
			match(RBRACE);
			setState(348);
			match(QUESTION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public VariableAssignmentContext variableAssignment() {
			return getRuleContext(VariableAssignmentContext.class,0);
		}
		public CompIdentifierContext compIdentifier() {
			return getRuleContext(CompIdentifierContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expression);
		try {
			setState(352);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(350);
				variableAssignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
				compIdentifier();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableAssignmentContext extends ParserRuleContext {
		public List<CompIdentifierContext> compIdentifier() {
			return getRuleContexts(CompIdentifierContext.class);
		}
		public CompIdentifierContext compIdentifier(int i) {
			return getRuleContext(CompIdentifierContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(LancerSpecParser.ASSIGN, 0); }
		public TerminalNode PLUS_ASSIGN() { return getToken(LancerSpecParser.PLUS_ASSIGN, 0); }
		public TerminalNode DOLLAR() { return getToken(LancerSpecParser.DOLLAR, 0); }
		public VariableAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterVariableAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitVariableAssignment(this);
		}
	}

	public final VariableAssignmentContext variableAssignment() throws RecognitionException {
		VariableAssignmentContext _localctx = new VariableAssignmentContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_variableAssignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			compIdentifier();
			setState(355);
			_la = _input.LA(1);
			if ( !(_la==ASSIGN || _la==PLUS_ASSIGN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(357);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOLLAR) {
				{
				setState(356);
				match(DOLLAR);
				}
			}

			setState(359);
			compIdentifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EbnfContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public EbnfSuffixContext ebnfSuffix() {
			return getRuleContext(EbnfSuffixContext.class,0);
		}
		public EbnfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ebnf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterEbnf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitEbnf(this);
		}
	}

	public final EbnfContext ebnf() throws RecognitionException {
		EbnfContext _localctx = new EbnfContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_ebnf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			block();
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12384898975268864L) != 0)) {
				{
				setState(362);
				ebnfSuffix();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EbnfSuffixContext extends ParserRuleContext {
		public TerminalNode QUESTION() { return getToken(LancerSpecParser.QUESTION, 0); }
		public List<TerminalNode> STAR() { return getTokens(LancerSpecParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(LancerSpecParser.STAR, i);
		}
		public TerminalNode PLUS() { return getToken(LancerSpecParser.PLUS, 0); }
		public EbnfSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ebnfSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterEbnfSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitEbnfSuffix(this);
		}
	}

	public final EbnfSuffixContext ebnfSuffix() throws RecognitionException {
		EbnfSuffixContext _localctx = new EbnfSuffixContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_ebnfSuffix);
		try {
			setState(370);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(365);
				match(QUESTION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(366);
				match(STAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(367);
				match(PLUS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(368);
				match(STAR);
				setState(369);
				match(STAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LexerAtomContext extends ParserRuleContext {
		public CharacterRangeContext characterRange() {
			return getRuleContext(CharacterRangeContext.class,0);
		}
		public TerminalContext terminal() {
			return getRuleContext(TerminalContext.class,0);
		}
		public NotSetContext notSet() {
			return getRuleContext(NotSetContext.class,0);
		}
		public TerminalNode LEXER_CHAR_SET() { return getToken(LancerSpecParser.LEXER_CHAR_SET, 0); }
		public LexerAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerAtom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLexerAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLexerAtom(this);
		}
	}

	public final LexerAtomContext lexerAtom() throws RecognitionException {
		LexerAtomContext _localctx = new LexerAtomContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_lexerAtom);
		try {
			setState(376);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				characterRange();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				terminal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(374);
				notSet();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(375);
				match(LEXER_CHAR_SET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public TerminalContext terminal() {
			return getRuleContext(TerminalContext.class,0);
		}
		public NotSetContext notSet() {
			return getRuleContext(NotSetContext.class,0);
		}
		public PrecedenceContext precedence() {
			return getRuleContext(PrecedenceContext.class,0);
		}
		public WeightageContext weightage() {
			return getRuleContext(WeightageContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_atom);
		try {
			setState(382);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(378);
				terminal();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(379);
				notSet();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(380);
				precedence();
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(381);
				weightage();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WeightageContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(LancerSpecParser.INT, 0); }
		public TerminalNode PERCENTAGE() { return getToken(LancerSpecParser.PERCENTAGE, 0); }
		public WeightageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weightage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterWeightage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitWeightage(this);
		}
	}

	public final WeightageContext weightage() throws RecognitionException {
		WeightageContext _localctx = new WeightageContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_weightage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			match(INT);
			setState(385);
			match(PERCENTAGE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrecedenceContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(LancerSpecParser.AT, 0); }
		public TerminalNode INT() { return getToken(LancerSpecParser.INT, 0); }
		public PrecedenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precedence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterPrecedence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitPrecedence(this);
		}
	}

	public final PrecedenceContext precedence() throws RecognitionException {
		PrecedenceContext _localctx = new PrecedenceContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_precedence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(AT);
			setState(388);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NotSetContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(LancerSpecParser.NOT, 0); }
		public SetElementContext setElement() {
			return getRuleContext(SetElementContext.class,0);
		}
		public BlockSetContext blockSet() {
			return getRuleContext(BlockSetContext.class,0);
		}
		public NotSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterNotSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitNotSet(this);
		}
	}

	public final NotSetContext notSet() throws RecognitionException {
		NotSetContext _localctx = new NotSetContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_notSet);
		try {
			setState(394);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(390);
				match(NOT);
				setState(391);
				setElement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(392);
				match(NOT);
				setState(393);
				blockSet();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockSetContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(LancerSpecParser.LPAREN, 0); }
		public List<SetElementContext> setElement() {
			return getRuleContexts(SetElementContext.class);
		}
		public SetElementContext setElement(int i) {
			return getRuleContext(SetElementContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(LancerSpecParser.RPAREN, 0); }
		public List<TerminalNode> OR() { return getTokens(LancerSpecParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(LancerSpecParser.OR, i);
		}
		public BlockSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterBlockSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitBlockSet(this);
		}
	}

	public final BlockSetContext blockSet() throws RecognitionException {
		BlockSetContext _localctx = new BlockSetContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_blockSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			match(LPAREN);
			setState(397);
			setElement();
			setState(402);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(398);
				match(OR);
				setState(399);
				setElement();
				}
				}
				setState(404);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(405);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetElementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LancerSpecParser.ID, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(LancerSpecParser.STRING_LITERAL, 0); }
		public CharacterRangeContext characterRange() {
			return getRuleContext(CharacterRangeContext.class,0);
		}
		public TerminalNode LEXER_CHAR_SET() { return getToken(LancerSpecParser.LEXER_CHAR_SET, 0); }
		public SetElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterSetElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitSetElement(this);
		}
	}

	public final SetElementContext setElement() throws RecognitionException {
		SetElementContext _localctx = new SetElementContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_setElement);
		try {
			setState(411);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(407);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(408);
				match(STRING_LITERAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(409);
				characterRange();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(410);
				match(LEXER_CHAR_SET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(LancerSpecParser.LPAREN, 0); }
		public AltListContext altList() {
			return getRuleContext(AltListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LancerSpecParser.RPAREN, 0); }
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			match(LPAREN);
			setState(414);
			altList();
			setState(415);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharacterRangeContext extends ParserRuleContext {
		public List<TerminalNode> STRING_LITERAL() { return getTokens(LancerSpecParser.STRING_LITERAL); }
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(LancerSpecParser.STRING_LITERAL, i);
		}
		public TerminalNode RANGE() { return getToken(LancerSpecParser.RANGE, 0); }
		public CharacterRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_characterRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterCharacterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitCharacterRange(this);
		}
	}

	public final CharacterRangeContext characterRange() throws RecognitionException {
		CharacterRangeContext _localctx = new CharacterRangeContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_characterRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			match(STRING_LITERAL);
			setState(418);
			match(RANGE);
			setState(419);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TerminalContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(LancerSpecParser.STRING_LITERAL, 0); }
		public TerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitTerminal(this);
		}
	}

	public final TerminalContext terminal() throws RecognitionException {
		TerminalContext _localctx = new TerminalContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_terminal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompIdentifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ArgActionBlockContext argActionBlock() {
			return getRuleContext(ArgActionBlockContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(LancerSpecParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(LancerSpecParser.DOT, i);
		}
		public List<CompIdentifierContext> compIdentifier() {
			return getRuleContexts(CompIdentifierContext.class);
		}
		public CompIdentifierContext compIdentifier(int i) {
			return getRuleContext(CompIdentifierContext.class,i);
		}
		public CompIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterCompIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitCompIdentifier(this);
		}
	}

	public final CompIdentifierContext compIdentifier() throws RecognitionException {
		CompIdentifierContext _localctx = new CompIdentifierContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_compIdentifier);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			identifier();
			setState(425);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(424);
				argActionBlock();
				}
			}

			setState(431);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(427);
					match(DOT);
					setState(428);
					compIdentifier();
					}
					} 
				}
				setState(433);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode RULE_REF() { return getToken(LancerSpecParser.RULE_REF, 0); }
		public TerminalNode TOKEN_REF() { return getToken(LancerSpecParser.TOKEN_REF, 0); }
		public TerminalNode ID() { return getToken(LancerSpecParser.ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2305843009213693958L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LexerRuleSpecContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LancerSpecParser.ID, 0); }
		public TerminalNode COLON() { return getToken(LancerSpecParser.COLON, 0); }
		public LexerRuleBlockContext lexerRuleBlock() {
			return getRuleContext(LexerRuleBlockContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LancerSpecParser.SEMI, 0); }
		public OptionsSpecContext optionsSpec() {
			return getRuleContext(OptionsSpecContext.class,0);
		}
		public LexerRuleSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerRuleSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLexerRuleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLexerRuleSpec(this);
		}
	}

	public final LexerRuleSpecContext lexerRuleSpec() throws RecognitionException {
		LexerRuleSpecContext _localctx = new LexerRuleSpecContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_lexerRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			match(ID);
			setState(438);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONS) {
				{
				setState(437);
				optionsSpec();
				}
			}

			setState(440);
			match(COLON);
			setState(441);
			lexerRuleBlock();
			setState(442);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LexerRuleBlockContext extends ParserRuleContext {
		public LexerAltListContext lexerAltList() {
			return getRuleContext(LexerAltListContext.class,0);
		}
		public LexerRuleBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerRuleBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLexerRuleBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLexerRuleBlock(this);
		}
	}

	public final LexerRuleBlockContext lexerRuleBlock() throws RecognitionException {
		LexerRuleBlockContext _localctx = new LexerRuleBlockContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_lexerRuleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			lexerAltList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LexerAltListContext extends ParserRuleContext {
		public List<LexerAltContext> lexerAlt() {
			return getRuleContexts(LexerAltContext.class);
		}
		public LexerAltContext lexerAlt(int i) {
			return getRuleContext(LexerAltContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(LancerSpecParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(LancerSpecParser.OR, i);
		}
		public LexerAltListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerAltList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLexerAltList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLexerAltList(this);
		}
	}

	public final LexerAltListContext lexerAltList() throws RecognitionException {
		LexerAltListContext _localctx = new LexerAltListContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_lexerAltList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			lexerAlt();
			setState(451);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(447);
				match(OR);
				setState(448);
				lexerAlt();
				}
				}
				setState(453);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LexerAltContext extends ParserRuleContext {
		public LexerElementsContext lexerElements() {
			return getRuleContext(LexerElementsContext.class,0);
		}
		public LexerAltContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerAlt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLexerAlt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLexerAlt(this);
		}
	}

	public final LexerAltContext lexerAlt() throws RecognitionException {
		LexerAltContext _localctx = new LexerAltContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_lexerAlt);
		try {
			setState(456);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(454);
				lexerElements();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LexerElementsContext extends ParserRuleContext {
		public List<LexerElementContext> lexerElement() {
			return getRuleContexts(LexerElementContext.class);
		}
		public LexerElementContext lexerElement(int i) {
			return getRuleContext(LexerElementContext.class,i);
		}
		public LexerElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLexerElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLexerElements(this);
		}
	}

	public final LexerElementsContext lexerElements() throws RecognitionException {
		LexerElementsContext _localctx = new LexerElementsContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_lexerElements);
		int _la;
		try {
			setState(464);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case LEXER_CHAR_SET:
			case STRING_LITERAL:
			case BEGIN_ACTION:
			case BEGIN_ERR:
			case BEGIN_REP:
			case BEGIN_WHT:
			case LPAREN:
			case NOT:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(459); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(458);
					lexerElement();
					}
					}
					setState(461); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 3458765673461712142L) != 0) );
				}
				break;
			case SEMI:
			case RPAREN:
			case OR:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LexerElementContext extends ParserRuleContext {
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public TerminalNode QUESTION() { return getToken(LancerSpecParser.QUESTION, 0); }
		public WeightBlockContext weightBlock() {
			return getRuleContext(WeightBlockContext.class,0);
		}
		public ErrorBlockContext errorBlock() {
			return getRuleContext(ErrorBlockContext.class,0);
		}
		public RepetitionBlockContext repetitionBlock() {
			return getRuleContext(RepetitionBlockContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LexerAtomContext lexerAtom() {
			return getRuleContext(LexerAtomContext.class,0);
		}
		public EbnfSuffixContext ebnfSuffix() {
			return getRuleContext(EbnfSuffixContext.class,0);
		}
		public LexerBlockContext lexerBlock() {
			return getRuleContext(LexerBlockContext.class,0);
		}
		public LexerElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLexerElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLexerElement(this);
		}
	}

	public final LexerElementContext lexerElement() throws RecognitionException {
		LexerElementContext _localctx = new LexerElementContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_lexerElement);
		int _la;
		try {
			setState(482);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(466);
				actionBlock();
				setState(468);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUESTION) {
					{
					setState(467);
					match(QUESTION);
					}
				}

				}
				break;
			case BEGIN_WHT:
				enterOuterAlt(_localctx, 2);
				{
				setState(470);
				weightBlock();
				}
				break;
			case BEGIN_ERR:
				enterOuterAlt(_localctx, 3);
				{
				setState(471);
				errorBlock();
				}
				break;
			case BEGIN_REP:
				enterOuterAlt(_localctx, 4);
				{
				setState(472);
				repetitionBlock();
				}
				break;
			case TOKEN_REF:
			case RULE_REF:
			case ID:
				enterOuterAlt(_localctx, 5);
				{
				setState(473);
				expression();
				}
				break;
			case LEXER_CHAR_SET:
			case STRING_LITERAL:
			case NOT:
				enterOuterAlt(_localctx, 6);
				{
				setState(474);
				lexerAtom();
				setState(476);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12384898975268864L) != 0)) {
					{
					setState(475);
					ebnfSuffix();
					}
				}

				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 7);
				{
				setState(478);
				lexerBlock();
				setState(480);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12384898975268864L) != 0)) {
					{
					setState(479);
					ebnfSuffix();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LexerBlockContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(LancerSpecParser.LPAREN, 0); }
		public LexerAltListContext lexerAltList() {
			return getRuleContext(LexerAltListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LancerSpecParser.RPAREN, 0); }
		public LexerBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLexerBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLexerBlock(this);
		}
	}

	public final LexerBlockContext lexerBlock() throws RecognitionException {
		LexerBlockContext _localctx = new LexerBlockContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_lexerBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484);
			match(LPAREN);
			setState(485);
			lexerAltList();
			setState(486);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GrammarOperatorContext extends ParserRuleContext {
		public List<TerminalNode> ASSIGN() { return getTokens(LancerSpecParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(LancerSpecParser.ASSIGN, i);
		}
		public TerminalNode NEGATE() { return getToken(LancerSpecParser.NEGATE, 0); }
		public TerminalNode PLUS() { return getToken(LancerSpecParser.PLUS, 0); }
		public TerminalNode GT() { return getToken(LancerSpecParser.GT, 0); }
		public TerminalNode LT() { return getToken(LancerSpecParser.LT, 0); }
		public GrammarOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterGrammarOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitGrammarOperator(this);
		}
	}

	public final GrammarOperatorContext grammarOperator() throws RecognitionException {
		GrammarOperatorContext _localctx = new GrammarOperatorContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_grammarOperator);
		try {
			setState(501);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(488);
				match(ASSIGN);
				setState(489);
				match(ASSIGN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(490);
				match(NEGATE);
				setState(491);
				match(ASSIGN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(492);
				match(PLUS);
				setState(493);
				match(ASSIGN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(494);
				match(GT);
				setState(495);
				match(ASSIGN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(496);
				match(LT);
				setState(497);
				match(ASSIGN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(498);
				match(GT);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(499);
				match(LT);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(500);
				match(ASSIGN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001I\u01f8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u0001\u0000\u0001\u0000"+
		"\u0005\u0000o\b\u0000\n\u0000\f\u0000r\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u007f\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u0085\b\u0003\n\u0003"+
		"\f\u0003\u0088\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005"+
		"\u0093\b\u0005\n\u0005\f\u0005\u0096\t\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005\u009b\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0005\u0006\u00a1\b\u0006\n\u0006\f\u0006\u00a4\t\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u00ad\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0003\t\u00b5\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0005\n\u00bb\b\n"+
		"\n\n\f\n\u00be\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0005\u000b\u00c5\b\u000b\n\u000b\f\u000b\u00c8\t\u000b\u0001\u000b\u0003"+
		"\u000b\u00cb\b\u000b\u0001\f\u0001\f\u0005\f\u00cf\b\f\n\f\f\f\u00d2\t"+
		"\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u00da\b\r\n"+
		"\r\f\r\u00dd\t\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0005\u000e\u00e3"+
		"\b\u000e\n\u000e\f\u000e\u00e6\t\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00ee\b\u000f\n\u000f"+
		"\f\u000f\u00f1\t\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00f9\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u00fe\b\u0010\n\u0010\f\u0010\u0101\t\u0010\u0001"+
		"\u0011\u0005\u0011\u0104\b\u0011\n\u0011\f\u0011\u0107\t\u0011\u0001\u0012"+
		"\u0001\u0012\u0003\u0012\u010b\b\u0012\u0001\u0013\u0003\u0013\u010e\b"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0112\b\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0118\b\u0013\u0001\u0013\u0003"+
		"\u0013\u011b\b\u0013\u0003\u0013\u011d\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0130\b\u0018\n\u0018"+
		"\f\u0018\u0133\t\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u0138\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u013d\b"+
		"\u001a\n\u001a\f\u001a\u0140\t\u001a\u0001\u001b\u0004\u001b\u0143\b\u001b"+
		"\u000b\u001b\f\u001b\u0144\u0001\u001b\u0003\u001b\u0148\b\u001b\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0003\u001c\u0151\b\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0155"+
		"\b\u001c\u0001\u001c\u0003\u001c\u0158\b\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u0161\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0166\b"+
		"\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0003 \u016c\b \u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0003!\u0173\b!\u0001\"\u0001\"\u0001\"\u0001\""+
		"\u0003\"\u0179\b\"\u0001#\u0001#\u0001#\u0001#\u0003#\u017f\b#\u0001$"+
		"\u0001$\u0001$\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0003"+
		"&\u018b\b&\u0001\'\u0001\'\u0001\'\u0001\'\u0005\'\u0191\b\'\n\'\f\'\u0194"+
		"\t\'\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0003(\u019c\b(\u0001"+
		")\u0001)\u0001)\u0001)\u0001*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001"+
		",\u0001,\u0003,\u01aa\b,\u0001,\u0001,\u0005,\u01ae\b,\n,\f,\u01b1\t,"+
		"\u0001-\u0001-\u0001.\u0001.\u0003.\u01b7\b.\u0001.\u0001.\u0001.\u0001"+
		".\u0001/\u0001/\u00010\u00010\u00010\u00050\u01c2\b0\n0\f0\u01c5\t0\u0001"+
		"1\u00011\u00031\u01c9\b1\u00012\u00042\u01cc\b2\u000b2\f2\u01cd\u0001"+
		"2\u00032\u01d1\b2\u00013\u00013\u00033\u01d5\b3\u00013\u00013\u00013\u0001"+
		"3\u00013\u00013\u00033\u01dd\b3\u00013\u00013\u00033\u01e1\b3\u00033\u01e3"+
		"\b3\u00014\u00014\u00014\u00014\u00015\u00015\u00015\u00015\u00015\u0001"+
		"5\u00015\u00015\u00015\u00015\u00015\u00015\u00015\u00035\u01f6\b5\u0001"+
		"5\u0000\u00006\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhj\u0000"+
		"\u0002\u0002\u00001144\u0002\u0000\u0001\u0002==\u0215\u0000l\u0001\u0000"+
		"\u0000\u0000\u0002v\u0001\u0000\u0000\u0000\u0004~\u0001\u0000\u0000\u0000"+
		"\u0006\u0080\u0001\u0000\u0000\u0000\b\u008b\u0001\u0000\u0000\u0000\n"+
		"\u009a\u0001\u0000\u0000\u0000\f\u009c\u0001\u0000\u0000\u0000\u000e\u00ac"+
		"\u0001\u0000\u0000\u0000\u0010\u00ae\u0001\u0000\u0000\u0000\u0012\u00b2"+
		"\u0001\u0000\u0000\u0000\u0014\u00b8\u0001\u0000\u0000\u0000\u0016\u00c1"+
		"\u0001\u0000\u0000\u0000\u0018\u00cc\u0001\u0000\u0000\u0000\u001a\u00d5"+
		"\u0001\u0000\u0000\u0000\u001c\u00e0\u0001\u0000\u0000\u0000\u001e\u00e9"+
		"\u0001\u0000\u0000\u0000 \u00f8\u0001\u0000\u0000\u0000\"\u0105\u0001"+
		"\u0000\u0000\u0000$\u010a\u0001\u0000\u0000\u0000&\u010d\u0001\u0000\u0000"+
		"\u0000(\u0122\u0001\u0000\u0000\u0000*\u0125\u0001\u0000\u0000\u0000,"+
		"\u0128\u0001\u0000\u0000\u0000.\u012a\u0001\u0000\u0000\u00000\u012c\u0001"+
		"\u0000\u0000\u00002\u0134\u0001\u0000\u0000\u00004\u0139\u0001\u0000\u0000"+
		"\u00006\u0147\u0001\u0000\u0000\u00008\u0157\u0001\u0000\u0000\u0000:"+
		"\u0159\u0001\u0000\u0000\u0000<\u0160\u0001\u0000\u0000\u0000>\u0162\u0001"+
		"\u0000\u0000\u0000@\u0169\u0001\u0000\u0000\u0000B\u0172\u0001\u0000\u0000"+
		"\u0000D\u0178\u0001\u0000\u0000\u0000F\u017e\u0001\u0000\u0000\u0000H"+
		"\u0180\u0001\u0000\u0000\u0000J\u0183\u0001\u0000\u0000\u0000L\u018a\u0001"+
		"\u0000\u0000\u0000N\u018c\u0001\u0000\u0000\u0000P\u019b\u0001\u0000\u0000"+
		"\u0000R\u019d\u0001\u0000\u0000\u0000T\u01a1\u0001\u0000\u0000\u0000V"+
		"\u01a5\u0001\u0000\u0000\u0000X\u01a7\u0001\u0000\u0000\u0000Z\u01b2\u0001"+
		"\u0000\u0000\u0000\\\u01b4\u0001\u0000\u0000\u0000^\u01bc\u0001\u0000"+
		"\u0000\u0000`\u01be\u0001\u0000\u0000\u0000b\u01c8\u0001\u0000\u0000\u0000"+
		"d\u01d0\u0001\u0000\u0000\u0000f\u01e2\u0001\u0000\u0000\u0000h\u01e4"+
		"\u0001\u0000\u0000\u0000j\u01f5\u0001\u0000\u0000\u0000lp\u0003\u0002"+
		"\u0001\u0000mo\u0003\u0004\u0002\u0000nm\u0001\u0000\u0000\u0000or\u0001"+
		"\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000"+
		"qs\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000st\u0003\"\u0011\u0000"+
		"tu\u0005\u0000\u0000\u0001u\u0001\u0001\u0000\u0000\u0000vw\u0005\u0017"+
		"\u0000\u0000wx\u0003Z-\u0000xy\u0005\'\u0000\u0000y\u0003\u0001\u0000"+
		"\u0000\u0000z\u007f\u0003\u0006\u0003\u0000{\u007f\u0003\f\u0006\u0000"+
		"|\u007f\u0003\u0010\b\u0000}\u007f\u0003\u0012\t\u0000~z\u0001\u0000\u0000"+
		"\u0000~{\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~}\u0001\u0000"+
		"\u0000\u0000\u007f\u0005\u0001\u0000\u0000\u0000\u0080\u0086\u0005\u000b"+
		"\u0000\u0000\u0081\u0082\u0003\b\u0004\u0000\u0082\u0083\u0005\'\u0000"+
		"\u0000\u0083\u0085\u0001\u0000\u0000\u0000\u0084\u0081\u0001\u0000\u0000"+
		"\u0000\u0085\u0088\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000"+
		"\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0089\u0001\u0000\u0000"+
		"\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0089\u008a\u0005+\u0000\u0000"+
		"\u008a\u0007\u0001\u0000\u0000\u0000\u008b\u008c\u0003Z-\u0000\u008c\u008d"+
		"\u00051\u0000\u0000\u008d\u008e\u0003\n\u0005\u0000\u008e\t\u0001\u0000"+
		"\u0000\u0000\u008f\u0094\u0003Z-\u0000\u0090\u0091\u00059\u0000\u0000"+
		"\u0091\u0093\u0003Z-\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0093\u0096"+
		"\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095"+
		"\u0001\u0000\u0000\u0000\u0095\u009b\u0001\u0000\u0000\u0000\u0096\u0094"+
		"\u0001\u0000\u0000\u0000\u0097\u009b\u0005\b\u0000\u0000\u0098\u009b\u0003"+
		"\u0014\n\u0000\u0099\u009b\u0005\u0007\u0000\u0000\u009a\u008f\u0001\u0000"+
		"\u0000\u0000\u009a\u0097\u0001\u0000\u0000\u0000\u009a\u0098\u0001\u0000"+
		"\u0000\u0000\u009a\u0099\u0001\u0000\u0000\u0000\u009b\u000b\u0001\u0000"+
		"\u0000\u0000\u009c\u009d\u0005\u0010\u0000\u0000\u009d\u00a2\u0003\u000e"+
		"\u0007\u0000\u009e\u009f\u0005&\u0000\u0000\u009f\u00a1\u0003\u000e\u0007"+
		"\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a1\u00a4\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a5\u0001\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a6\u0005\'\u0000\u0000\u00a6\r\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a8\u0003Z-\u0000\u00a8\u00a9\u00051\u0000\u0000\u00a9\u00aa"+
		"\u0003Z-\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab\u00ad\u0003Z"+
		"-\u0000\u00ac\u00a7\u0001\u0000\u0000\u0000\u00ac\u00ab\u0001\u0000\u0000"+
		"\u0000\u00ad\u000f\u0001\u0000\u0000\u0000\u00ae\u00af\u0005:\u0000\u0000"+
		"\u00af\u00b0\u0003Z-\u0000\u00b0\u00b1\u0003\u0014\n\u0000\u00b1\u0011"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b4\u0005\f\u0000\u0000\u00b3\u00b5\u0003"+
		"\u0016\u000b\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005"+
		"+\u0000\u0000\u00b7\u0013\u0001\u0000\u0000\u0000\u00b8\u00bc\u0005\n"+
		"\u0000\u0000\u00b9\u00bb\u0005B\u0000\u0000\u00ba\u00b9\u0001\u0000\u0000"+
		"\u0000\u00bb\u00be\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00bf\u0001\u0000\u0000"+
		"\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005@\u0000\u0000"+
		"\u00c0\u0015\u0001\u0000\u0000\u0000\u00c1\u00c6\u0003Z-\u0000\u00c2\u00c3"+
		"\u0005&\u0000\u0000\u00c3\u00c5\u0003Z-\u0000\u00c4\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00ca\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00cb\u0005&\u0000"+
		"\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000"+
		"\u0000\u00cb\u0017\u0001\u0000\u0000\u0000\u00cc\u00d0\u0005#\u0000\u0000"+
		"\u00cd\u00cf\u0005H\u0000\u0000\u00ce\u00cd\u0001\u0000\u0000\u0000\u00cf"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d0"+
		"\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d3\u0001\u0000\u0000\u0000\u00d2"+
		"\u00d0\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005F\u0000\u0000\u00d4\u0019"+
		"\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005\"\u0000\u0000\u00d6\u00db\u0003"+
		" \u0010\u0000\u00d7\u00d8\u0005&\u0000\u0000\u00d8\u00da\u0003 \u0010"+
		"\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00da\u00dd\u0001\u0000\u0000"+
		"\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000"+
		"\u0000\u00dc\u00de\u0001\u0000\u0000\u0000\u00dd\u00db\u0001\u0000\u0000"+
		"\u0000\u00de\u00df\u0005)\u0000\u0000\u00df\u001b\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e4\u0005!\u0000\u0000\u00e1\u00e3\u0005E\u0000\u0000\u00e2\u00e1"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e6\u0001\u0000\u0000\u0000\u00e4\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7\u00e8"+
		"\u0005C\u0000\u0000\u00e8\u001d\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005"+
		",\u0000\u0000\u00ea\u00ef\u0003 \u0010\u0000\u00eb\u00ec\u0005&\u0000"+
		"\u0000\u00ec\u00ee\u0003 \u0010\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000"+
		"\u00ee\u00f1\u0001\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000"+
		"\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u00f2\u0001\u0000\u0000\u0000"+
		"\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005-\u0000\u0000\u00f3"+
		"\u001f\u0001\u0000\u0000\u0000\u00f4\u00f9\u0003X,\u0000\u00f5\u00f9\u0005"+
		"\b\u0000\u0000\u00f6\u00f9\u0005\u0007\u0000\u0000\u00f7\u00f9\u0003\u001a"+
		"\r\u0000\u00f8\u00f4\u0001\u0000\u0000\u0000\u00f8\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f8\u00f7\u0001\u0000\u0000"+
		"\u0000\u00f9\u00ff\u0001\u0000\u0000\u0000\u00fa\u00fb\u0003j5\u0000\u00fb"+
		"\u00fc\u0003 \u0010\u0000\u00fc\u00fe\u0001\u0000\u0000\u0000\u00fd\u00fa"+
		"\u0001\u0000\u0000\u0000\u00fe\u0101\u0001\u0000\u0000\u0000\u00ff\u00fd"+
		"\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000\u0100!\u0001"+
		"\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0102\u0104\u0003"+
		"$\u0012\u0000\u0103\u0102\u0001\u0000\u0000\u0000\u0104\u0107\u0001\u0000"+
		"\u0000\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000"+
		"\u0000\u0000\u0106#\u0001\u0000\u0000\u0000\u0107\u0105\u0001\u0000\u0000"+
		"\u0000\u0108\u010b\u0003&\u0013\u0000\u0109\u010b\u0003\\.\u0000\u010a"+
		"\u0108\u0001\u0000\u0000\u0000\u010a\u0109\u0001\u0000\u0000\u0000\u010b"+
		"%\u0001\u0000\u0000\u0000\u010c\u010e\u0003,\u0016\u0000\u010d\u010c\u0001"+
		"\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010f\u0001"+
		"\u0000\u0000\u0000\u010f\u0111\u0005=\u0000\u0000\u0110\u0112\u0003\u001e"+
		"\u000f\u0000\u0111\u0110\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000"+
		"\u0000\u0000\u0112\u011c\u0001\u0000\u0000\u0000\u0113\u0114\u0003(\u0014"+
		"\u0000\u0114\u0115\u0003*\u0015\u0000\u0115\u011d\u0001\u0000\u0000\u0000"+
		"\u0116\u0118\u0003*\u0015\u0000\u0117\u0116\u0001\u0000\u0000\u0000\u0117"+
		"\u0118\u0001\u0000\u0000\u0000\u0118\u011a\u0001\u0000\u0000\u0000\u0119"+
		"\u011b\u0003(\u0014\u0000\u011a\u0119\u0001\u0000\u0000\u0000\u011a\u011b"+
		"\u0001\u0000\u0000\u0000\u011b\u011d\u0001\u0000\u0000\u0000\u011c\u0113"+
		"\u0001\u0000\u0000\u0000\u011c\u0117\u0001\u0000\u0000\u0000\u011d\u011e"+
		"\u0001\u0000\u0000\u0000\u011e\u011f\u0005$\u0000\u0000\u011f\u0120\u0003"+
		".\u0017\u0000\u0120\u0121\u0005\'\u0000\u0000\u0121\'\u0001\u0000\u0000"+
		"\u0000\u0122\u0123\u0005\u001b\u0000\u0000\u0123\u0124\u0003\u001e\u000f"+
		"\u0000\u0124)\u0001\u0000\u0000\u0000\u0125\u0126\u0005\u001c\u0000\u0000"+
		"\u0126\u0127\u0003\u001e\u000f\u0000\u0127+\u0001\u0000\u0000\u0000\u0128"+
		"\u0129\u0005\u0011\u0000\u0000\u0129-\u0001\u0000\u0000\u0000\u012a\u012b"+
		"\u00030\u0018\u0000\u012b/\u0001\u0000\u0000\u0000\u012c\u0131\u00032"+
		"\u0019\u0000\u012d\u012e\u00056\u0000\u0000\u012e\u0130\u00032\u0019\u0000"+
		"\u012f\u012d\u0001\u0000\u0000\u0000\u0130\u0133\u0001\u0000\u0000\u0000"+
		"\u0131\u012f\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000"+
		"\u01321\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0134"+
		"\u0137\u00036\u001b\u0000\u0135\u0136\u0005;\u0000\u0000\u0136\u0138\u0003"+
		"Z-\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000"+
		"\u0000\u01383\u0001\u0000\u0000\u0000\u0139\u013e\u00036\u001b\u0000\u013a"+
		"\u013b\u00056\u0000\u0000\u013b\u013d\u00036\u001b\u0000\u013c\u013a\u0001"+
		"\u0000\u0000\u0000\u013d\u0140\u0001\u0000\u0000\u0000\u013e\u013c\u0001"+
		"\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f5\u0001\u0000"+
		"\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0141\u0143\u00038\u001c"+
		"\u0000\u0142\u0141\u0001\u0000\u0000\u0000\u0143\u0144\u0001\u0000\u0000"+
		"\u0000\u0144\u0142\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000\u0000"+
		"\u0000\u0145\u0148\u0001\u0000\u0000\u0000\u0146\u0148\u0001\u0000\u0000"+
		"\u0000\u0147\u0142\u0001\u0000\u0000\u0000\u0147\u0146\u0001\u0000\u0000"+
		"\u0000\u01487\u0001\u0000\u0000\u0000\u0149\u0158\u0003\u0014\n\u0000"+
		"\u014a\u0158\u0003:\u001d\u0000\u014b\u0158\u0003\u0018\f\u0000\u014c"+
		"\u0158\u0003\u001c\u000e\u0000\u014d\u0158\u0003\u001a\r\u0000\u014e\u0150"+
		"\u0003<\u001e\u0000\u014f\u0151\u0003B!\u0000\u0150\u014f\u0001\u0000"+
		"\u0000\u0000\u0150\u0151\u0001\u0000\u0000\u0000\u0151\u0158\u0001\u0000"+
		"\u0000\u0000\u0152\u0154\u0003F#\u0000\u0153\u0155\u0003B!\u0000\u0154"+
		"\u0153\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000\u0000\u0155"+
		"\u0158\u0001\u0000\u0000\u0000\u0156\u0158\u0003@ \u0000\u0157\u0149\u0001"+
		"\u0000\u0000\u0000\u0157\u014a\u0001\u0000\u0000\u0000\u0157\u014b\u0001"+
		"\u0000\u0000\u0000\u0157\u014c\u0001\u0000\u0000\u0000\u0157\u014d\u0001"+
		"\u0000\u0000\u0000\u0157\u014e\u0001\u0000\u0000\u0000\u0157\u0152\u0001"+
		"\u0000\u0000\u0000\u0157\u0156\u0001\u0000\u0000\u0000\u01589\u0001\u0000"+
		"\u0000\u0000\u0159\u015a\u0005*\u0000\u0000\u015a\u015b\u0003 \u0010\u0000"+
		"\u015b\u015c\u0005+\u0000\u0000\u015c\u015d\u00052\u0000\u0000\u015d;"+
		"\u0001\u0000\u0000\u0000\u015e\u0161\u0003>\u001f\u0000\u015f\u0161\u0003"+
		"X,\u0000\u0160\u015e\u0001\u0000\u0000\u0000\u0160\u015f\u0001\u0000\u0000"+
		"\u0000\u0161=\u0001\u0000\u0000\u0000\u0162\u0163\u0003X,\u0000\u0163"+
		"\u0165\u0007\u0000\u0000\u0000\u0164\u0166\u00057\u0000\u0000\u0165\u0164"+
		"\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000\u0000\u0000\u0166\u0167"+
		"\u0001\u0000\u0000\u0000\u0167\u0168\u0003X,\u0000\u0168?\u0001\u0000"+
		"\u0000\u0000\u0169\u016b\u0003R)\u0000\u016a\u016c\u0003B!\u0000\u016b"+
		"\u016a\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000\u0000\u016c"+
		"A\u0001\u0000\u0000\u0000\u016d\u0173\u00052\u0000\u0000\u016e\u0173\u0005"+
		"3\u0000\u0000\u016f\u0173\u00055\u0000\u0000\u0170\u0171\u00053\u0000"+
		"\u0000\u0171\u0173\u00053\u0000\u0000\u0172\u016d\u0001\u0000\u0000\u0000"+
		"\u0172\u016e\u0001\u0000\u0000\u0000\u0172\u016f\u0001\u0000\u0000\u0000"+
		"\u0172\u0170\u0001\u0000\u0000\u0000\u0173C\u0001\u0000\u0000\u0000\u0174"+
		"\u0179\u0003T*\u0000\u0175\u0179\u0003V+\u0000\u0176\u0179\u0003L&\u0000"+
		"\u0177\u0179\u0005\u0003\u0000\u0000\u0178\u0174\u0001\u0000\u0000\u0000"+
		"\u0178\u0175\u0001\u0000\u0000\u0000\u0178\u0176\u0001\u0000\u0000\u0000"+
		"\u0178\u0177\u0001\u0000\u0000\u0000\u0179E\u0001\u0000\u0000\u0000\u017a"+
		"\u017f\u0003V+\u0000\u017b\u017f\u0003L&\u0000\u017c\u017f\u0003J%\u0000"+
		"\u017d\u017f\u0003H$\u0000\u017e\u017a\u0001\u0000\u0000\u0000\u017e\u017b"+
		"\u0001\u0000\u0000\u0000\u017e\u017c\u0001\u0000\u0000\u0000\u017e\u017d"+
		"\u0001\u0000\u0000\u0000\u017fG\u0001\u0000\u0000\u0000\u0180\u0181\u0005"+
		"\u0007\u0000\u0000\u0181\u0182\u0005\u000e\u0000\u0000\u0182I\u0001\u0000"+
		"\u0000\u0000\u0183\u0184\u0005:\u0000\u0000\u0184\u0185\u0005\u0007\u0000"+
		"\u0000\u0185K\u0001\u0000\u0000\u0000\u0186\u0187\u0005<\u0000\u0000\u0187"+
		"\u018b\u0003P(\u0000\u0188\u0189\u0005<\u0000\u0000\u0189\u018b\u0003"+
		"N\'\u0000\u018a\u0186\u0001\u0000\u0000\u0000\u018a\u0188\u0001\u0000"+
		"\u0000\u0000\u018bM\u0001\u0000\u0000\u0000\u018c\u018d\u0005(\u0000\u0000"+
		"\u018d\u0192\u0003P(\u0000\u018e\u018f\u00056\u0000\u0000\u018f\u0191"+
		"\u0003P(\u0000\u0190\u018e\u0001\u0000\u0000\u0000\u0191\u0194\u0001\u0000"+
		"\u0000\u0000\u0192\u0190\u0001\u0000\u0000\u0000\u0192\u0193\u0001\u0000"+
		"\u0000\u0000\u0193\u0195\u0001\u0000\u0000\u0000\u0194\u0192\u0001\u0000"+
		"\u0000\u0000\u0195\u0196\u0005)\u0000\u0000\u0196O\u0001\u0000\u0000\u0000"+
		"\u0197\u019c\u0005=\u0000\u0000\u0198\u019c\u0005\b\u0000\u0000\u0199"+
		"\u019c\u0003T*\u0000\u019a\u019c\u0005\u0003\u0000\u0000\u019b\u0197\u0001"+
		"\u0000\u0000\u0000\u019b\u0198\u0001\u0000\u0000\u0000\u019b\u0199\u0001"+
		"\u0000\u0000\u0000\u019b\u019a\u0001\u0000\u0000\u0000\u019cQ\u0001\u0000"+
		"\u0000\u0000\u019d\u019e\u0005(\u0000\u0000\u019e\u019f\u00034\u001a\u0000"+
		"\u019f\u01a0\u0005)\u0000\u0000\u01a0S\u0001\u0000\u0000\u0000\u01a1\u01a2"+
		"\u0005\b\u0000\u0000\u01a2\u01a3\u00058\u0000\u0000\u01a3\u01a4\u0005"+
		"\b\u0000\u0000\u01a4U\u0001\u0000\u0000\u0000\u01a5\u01a6\u0005\b\u0000"+
		"\u0000\u01a6W\u0001\u0000\u0000\u0000\u01a7\u01a9\u0003Z-\u0000\u01a8"+
		"\u01aa\u0003\u001e\u000f\u0000\u01a9\u01a8\u0001\u0000\u0000\u0000\u01a9"+
		"\u01aa\u0001\u0000\u0000\u0000\u01aa\u01af\u0001\u0000\u0000\u0000\u01ab"+
		"\u01ac\u00059\u0000\u0000\u01ac\u01ae\u0003X,\u0000\u01ad\u01ab\u0001"+
		"\u0000\u0000\u0000\u01ae\u01b1\u0001\u0000\u0000\u0000\u01af\u01ad\u0001"+
		"\u0000\u0000\u0000\u01af\u01b0\u0001\u0000\u0000\u0000\u01b0Y\u0001\u0000"+
		"\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b2\u01b3\u0007\u0001"+
		"\u0000\u0000\u01b3[\u0001\u0000\u0000\u0000\u01b4\u01b6\u0005=\u0000\u0000"+
		"\u01b5\u01b7\u0003\u0006\u0003\u0000\u01b6\u01b5\u0001\u0000\u0000\u0000"+
		"\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001\u0000\u0000\u0000"+
		"\u01b8\u01b9\u0005$\u0000\u0000\u01b9\u01ba\u0003^/\u0000\u01ba\u01bb"+
		"\u0005\'\u0000\u0000\u01bb]\u0001\u0000\u0000\u0000\u01bc\u01bd\u0003"+
		"`0\u0000\u01bd_\u0001\u0000\u0000\u0000\u01be\u01c3\u0003b1\u0000\u01bf"+
		"\u01c0\u00056\u0000\u0000\u01c0\u01c2\u0003b1\u0000\u01c1\u01bf\u0001"+
		"\u0000\u0000\u0000\u01c2\u01c5\u0001\u0000\u0000\u0000\u01c3\u01c1\u0001"+
		"\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000\u0000\u0000\u01c4a\u0001\u0000"+
		"\u0000\u0000\u01c5\u01c3\u0001\u0000\u0000\u0000\u01c6\u01c9\u0003d2\u0000"+
		"\u01c7\u01c9\u0001\u0000\u0000\u0000\u01c8\u01c6\u0001\u0000\u0000\u0000"+
		"\u01c8\u01c7\u0001\u0000\u0000\u0000\u01c9c\u0001\u0000\u0000\u0000\u01ca"+
		"\u01cc\u0003f3\u0000\u01cb\u01ca\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001"+
		"\u0000\u0000\u0000\u01cd\u01cb\u0001\u0000\u0000\u0000\u01cd\u01ce\u0001"+
		"\u0000\u0000\u0000\u01ce\u01d1\u0001\u0000\u0000\u0000\u01cf\u01d1\u0001"+
		"\u0000\u0000\u0000\u01d0\u01cb\u0001\u0000\u0000\u0000\u01d0\u01cf\u0001"+
		"\u0000\u0000\u0000\u01d1e\u0001\u0000\u0000\u0000\u01d2\u01d4\u0003\u0014"+
		"\n\u0000\u01d3\u01d5\u00052\u0000\u0000\u01d4\u01d3\u0001\u0000\u0000"+
		"\u0000\u01d4\u01d5\u0001\u0000\u0000\u0000\u01d5\u01e3\u0001\u0000\u0000"+
		"\u0000\u01d6\u01e3\u0003\u0018\f\u0000\u01d7\u01e3\u0003\u001c\u000e\u0000"+
		"\u01d8\u01e3\u0003\u001a\r\u0000\u01d9\u01e3\u0003<\u001e\u0000\u01da"+
		"\u01dc\u0003D\"\u0000\u01db\u01dd\u0003B!\u0000\u01dc\u01db\u0001\u0000"+
		"\u0000\u0000\u01dc\u01dd\u0001\u0000\u0000\u0000\u01dd\u01e3\u0001\u0000"+
		"\u0000\u0000\u01de\u01e0\u0003h4\u0000\u01df\u01e1\u0003B!\u0000\u01e0"+
		"\u01df\u0001\u0000\u0000\u0000\u01e0\u01e1\u0001\u0000\u0000\u0000\u01e1"+
		"\u01e3\u0001\u0000\u0000\u0000\u01e2\u01d2\u0001\u0000\u0000\u0000\u01e2"+
		"\u01d6\u0001\u0000\u0000\u0000\u01e2\u01d7\u0001\u0000\u0000\u0000\u01e2"+
		"\u01d8\u0001\u0000\u0000\u0000\u01e2\u01d9\u0001\u0000\u0000\u0000\u01e2"+
		"\u01da\u0001\u0000\u0000\u0000\u01e2\u01de\u0001\u0000\u0000\u0000\u01e3"+
		"g\u0001\u0000\u0000\u0000\u01e4\u01e5\u0005(\u0000\u0000\u01e5\u01e6\u0003"+
		"`0\u0000\u01e6\u01e7\u0005)\u0000\u0000\u01e7i\u0001\u0000\u0000\u0000"+
		"\u01e8\u01e9\u00051\u0000\u0000\u01e9\u01f6\u00051\u0000\u0000\u01ea\u01eb"+
		"\u0005\u000f\u0000\u0000\u01eb\u01f6\u00051\u0000\u0000\u01ec\u01ed\u0005"+
		"5\u0000\u0000\u01ed\u01f6\u00051\u0000\u0000\u01ee\u01ef\u00050\u0000"+
		"\u0000\u01ef\u01f6\u00051\u0000\u0000\u01f0\u01f1\u0005/\u0000\u0000\u01f1"+
		"\u01f6\u00051\u0000\u0000\u01f2\u01f6\u00050\u0000\u0000\u01f3\u01f6\u0005"+
		"/\u0000\u0000\u01f4\u01f6\u00051\u0000\u0000\u01f5\u01e8\u0001\u0000\u0000"+
		"\u0000\u01f5\u01ea\u0001\u0000\u0000\u0000\u01f5\u01ec\u0001\u0000\u0000"+
		"\u0000\u01f5\u01ee\u0001\u0000\u0000\u0000\u01f5\u01f0\u0001\u0000\u0000"+
		"\u0000\u01f5\u01f2\u0001\u0000\u0000\u0000\u01f5\u01f3\u0001\u0000\u0000"+
		"\u0000\u01f5\u01f4\u0001\u0000\u0000\u0000\u01f6k\u0001\u0000\u0000\u0000"+
		"5p~\u0086\u0094\u009a\u00a2\u00ac\u00b4\u00bc\u00c6\u00ca\u00d0\u00db"+
		"\u00e4\u00ef\u00f8\u00ff\u0105\u010a\u010d\u0111\u0117\u011a\u011c\u0131"+
		"\u0137\u013e\u0144\u0147\u0150\u0154\u0157\u0160\u0165\u016b\u0172\u0178"+
		"\u017e\u018a\u0192\u019b\u01a9\u01af\u01b6\u01c3\u01c8\u01cd\u01d0\u01d4"+
		"\u01dc\u01e0\u01e2\u01f5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}