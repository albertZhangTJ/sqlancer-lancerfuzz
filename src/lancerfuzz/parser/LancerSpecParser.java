package lancerfuzz.parser;
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
		LINE_COMMENT=6, INT=7, STRING_LITERAL=8, ARG_STRING_LITERAL=9, UNTERMINATED_STRING_LITERAL=10, 
		BEGIN_ACTION=11, DASH=12, ESC=13, OPTIONS=14, TOKENS=15, CHANNELS=16, 
		PERCENTAGE=17, NEGATE=18, IMPORT=19, FRAGMENT=20, SCHEMA=21, EXPR=22, 
		STATEMENT=23, LEXER=24, PARSER=25, GRAMMAR=26, PROTECTED=27, PUBLIC=28, 
		PRIVATE=29, RETURNS=30, LOCALS=31, THROWS=32, CATCH=33, FINALLY=34, MODE=35, 
		BEGIN_ERR=36, BEGIN_REP=37, COLON=38, COLONCOLON=39, COMMA=40, SEMI=41, 
		LPAREN=42, RPAREN=43, LBRACE=44, RBRACE=45, LBRACK=46, RBRACK=47, RARROW=48, 
		LT=49, GT=50, ASSIGN=51, QUESTION=52, STAR=53, PLUS_ASSIGN=54, PLUS=55, 
		OR=56, DOLLAR=57, RANGE=58, DOT=59, AT=60, POUND=61, NOT=62, ID=63, WS=64, 
		ERRCHAR=65, END_ACTION=66, UNTERMINATED_ACTION=67, ACTION_CONTENT=68, 
		END_ERR_DECL=69, UNTERMINATED_ERR_DECL=70, ERR_CONTENT=71, UNTERMINATED_CHAR_SET=72;
	public static final int
		RULE_grammarSpec = 0, RULE_grammarDecl = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_action_ = 8, RULE_tokensSpec = 9, RULE_actionBlock = 10, 
		RULE_idList = 11, RULE_repetitionBlock = 12, RULE_errorBlock = 13, RULE_argActionBlock = 14, 
		RULE_arg = 15, RULE_rules = 16, RULE_ruleSpec = 17, RULE_parserRuleSpec = 18, 
		RULE_ruleReturns = 19, RULE_localsSpec = 20, RULE_ruleModifier = 21, RULE_ruleBlock = 22, 
		RULE_ruleAltList = 23, RULE_labeledAlt = 24, RULE_altList = 25, RULE_alternative = 26, 
		RULE_element = 27, RULE_predicate = 28, RULE_expression = 29, RULE_variableAssignment = 30, 
		RULE_ebnf = 31, RULE_ebnfSuffix = 32, RULE_lexerAtom = 33, RULE_atom = 34, 
		RULE_weightage = 35, RULE_precedence = 36, RULE_notSet = 37, RULE_blockSet = 38, 
		RULE_setElement = 39, RULE_block = 40, RULE_characterRange = 41, RULE_terminal = 42, 
		RULE_compIdentifier = 43, RULE_identifier = 44, RULE_lexerRuleSpec = 45, 
		RULE_lexerRuleBlock = 46, RULE_lexerAltList = 47, RULE_lexerAlt = 48, 
		RULE_lexerElements = 49, RULE_lexerElement = 50, RULE_lexerBlock = 51, 
		RULE_charSet = 52, RULE_charSetContent = 53, RULE_grammarOperator = 54;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarSpec", "grammarDecl", "prequelConstruct", "optionsSpec", "option", 
			"optionValue", "delegateGrammars", "delegateGrammar", "action_", "tokensSpec", 
			"actionBlock", "idList", "repetitionBlock", "errorBlock", "argActionBlock", 
			"arg", "rules", "ruleSpec", "parserRuleSpec", "ruleReturns", "localsSpec", 
			"ruleModifier", "ruleBlock", "ruleAltList", "labeledAlt", "altList", 
			"alternative", "element", "predicate", "expression", "variableAssignment", 
			"ebnf", "ebnfSuffix", "lexerAtom", "atom", "weightage", "precedence", 
			"notSet", "blockSet", "setElement", "block", "characterRange", "terminal", 
			"compIdentifier", "identifier", "lexerRuleSpec", "lexerRuleBlock", "lexerAltList", 
			"lexerAlt", "lexerElements", "lexerElement", "lexerBlock", "charSet", 
			"charSetContent", "grammarOperator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"'-'", null, null, null, null, "'%'", "'!'", "'import'", "'fragment'", 
			"'schema'", "'expr'", "'statement'", "'lexer'", "'parser'", "'grammar'", 
			"'protected'", "'public'", "'private'", "'returns'", "'locals'", "'throws'", 
			"'catch'", "'finally'", "'mode'", "'_e('", "'_r('"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TOKEN_REF", "RULE_REF", "LEXER_CHAR_SET", "DOC_COMMENT", "BLOCK_COMMENT", 
			"LINE_COMMENT", "INT", "STRING_LITERAL", "ARG_STRING_LITERAL", "UNTERMINATED_STRING_LITERAL", 
			"BEGIN_ACTION", "DASH", "ESC", "OPTIONS", "TOKENS", "CHANNELS", "PERCENTAGE", 
			"NEGATE", "IMPORT", "FRAGMENT", "SCHEMA", "EXPR", "STATEMENT", "LEXER", 
			"PARSER", "GRAMMAR", "PROTECTED", "PUBLIC", "PRIVATE", "RETURNS", "LOCALS", 
			"THROWS", "CATCH", "FINALLY", "MODE", "BEGIN_ERR", "BEGIN_REP", "COLON", 
			"COLONCOLON", "COMMA", "SEMI", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"LBRACK", "RBRACK", "RARROW", "LT", "GT", "ASSIGN", "QUESTION", "STAR", 
			"PLUS_ASSIGN", "PLUS", "OR", "DOLLAR", "RANGE", "DOT", "AT", "POUND", 
			"NOT", "ID", "WS", "ERRCHAR", "END_ACTION", "UNTERMINATED_ACTION", "ACTION_CONTENT", 
			"END_ERR_DECL", "UNTERMINATED_ERR_DECL", "ERR_CONTENT", "UNTERMINATED_CHAR_SET"
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
			setState(110);
			grammarDecl();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1152921504607420416L) != 0)) {
				{
				{
				setState(111);
				prequelConstruct();
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
			rules();
			setState(118);
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
			setState(120);
			match(GRAMMAR);
			setState(121);
			identifier();
			setState(122);
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
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPTIONS:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				optionsSpec();
				}
				break;
			case IMPORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				delegateGrammars();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				action_();
				}
				break;
			case TOKENS:
				enterOuterAlt(_localctx, 4);
				{
				setState(127);
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
			setState(130);
			match(OPTIONS);
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9223372036854775802L) != 0)) {
				{
				{
				setState(131);
				option();
				setState(132);
				match(SEMI);
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
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
			setState(141);
			identifier();
			setState(142);
			match(ASSIGN);
			setState(143);
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
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				identifier();
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(146);
					match(DOT);
					setState(147);
					identifier();
					}
					}
					setState(152);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(STRING_LITERAL);
				}
				break;
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(154);
				actionBlock();
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(155);
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
			setState(158);
			match(IMPORT);
			setState(159);
			delegateGrammar();
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(160);
				match(COMMA);
				setState(161);
				delegateGrammar();
				}
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(167);
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
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				identifier();
				setState(170);
				match(ASSIGN);
				setState(171);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
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
			setState(176);
			match(AT);
			setState(177);
			identifier();
			setState(178);
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
			setState(180);
			match(TOKENS);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9223372036854775802L) != 0)) {
				{
				setState(181);
				idList();
				}
			}

			setState(184);
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
			setState(186);
			match(BEGIN_ACTION);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ACTION_CONTENT) {
				{
				{
				setState(187);
				match(ACTION_CONTENT);
				}
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(193);
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
			setState(195);
			identifier();
			setState(200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(196);
					match(COMMA);
					setState(197);
					identifier();
					}
					} 
				}
				setState(202);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(203);
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
		enterRule(_localctx, 24, RULE_repetitionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(BEGIN_REP);
			setState(207);
			arg();
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(208);
				match(COMMA);
				setState(209);
				arg();
				}
				}
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(215);
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
		enterRule(_localctx, 26, RULE_errorBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(BEGIN_ERR);
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ERR_CONTENT) {
				{
				{
				setState(218);
				match(ERR_CONTENT);
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(224);
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
		enterRule(_localctx, 28, RULE_argActionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(LBRACK);
			setState(227);
			arg();
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(228);
				match(COMMA);
				setState(229);
				arg();
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(235);
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
		public TerminalNode ARG_STRING_LITERAL() { return getToken(LancerSpecParser.ARG_STRING_LITERAL, 0); }
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
		enterRule(_localctx, 30, RULE_arg);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case ID:
				{
				setState(237);
				compIdentifier();
				}
				break;
			case ARG_STRING_LITERAL:
				{
				setState(238);
				match(ARG_STRING_LITERAL);
				}
				break;
			case INT:
				{
				setState(239);
				match(INT);
				}
				break;
			case BEGIN_REP:
				{
				setState(240);
				repetitionBlock();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(248);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(243);
					grammarOperator();
					setState(244);
					arg();
					}
					} 
				}
				setState(250);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		enterRule(_localctx, 32, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1048582L) != 0)) {
				{
				{
				setState(251);
				ruleSpec();
				}
				}
				setState(256);
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
		enterRule(_localctx, 34, RULE_ruleSpec);
		try {
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				parserRuleSpec();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(258);
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
		public TerminalNode RULE_REF() { return getToken(LancerSpecParser.RULE_REF, 0); }
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
		enterRule(_localctx, 36, RULE_parserRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FRAGMENT) {
				{
				setState(261);
				ruleModifier();
				}
			}

			setState(264);
			match(RULE_REF);
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(265);
				argActionBlock();
				}
			}

			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(268);
				ruleReturns();
				setState(269);
				localsSpec();
				}
				break;
			case 2:
				{
				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOCALS) {
					{
					setState(271);
					localsSpec();
					}
				}

				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(274);
					ruleReturns();
					}
				}

				}
				break;
			}
			setState(279);
			match(COLON);
			setState(280);
			ruleBlock();
			setState(281);
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
		enterRule(_localctx, 38, RULE_ruleReturns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(RETURNS);
			setState(284);
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
		enterRule(_localctx, 40, RULE_localsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(LOCALS);
			setState(287);
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
		enterRule(_localctx, 42, RULE_ruleModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
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
		enterRule(_localctx, 44, RULE_ruleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
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
		enterRule(_localctx, 46, RULE_ruleAltList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			labeledAlt();
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(294);
				match(OR);
				setState(295);
				labeledAlt();
				}
				}
				setState(300);
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
		enterRule(_localctx, 48, RULE_labeledAlt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			alternative();
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POUND) {
				{
				setState(302);
				match(POUND);
				setState(303);
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
		enterRule(_localctx, 50, RULE_altList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			alternative();
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(307);
				match(OR);
				setState(308);
				alternative();
				}
				}
				setState(313);
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
		enterRule(_localctx, 52, RULE_alternative);
		int _la;
		try {
			setState(320);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case INT:
			case STRING_LITERAL:
			case BEGIN_ACTION:
			case BEGIN_ERR:
			case BEGIN_REP:
			case LPAREN:
			case LT:
			case AT:
			case NOT:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(315); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(314);
					element();
					}
					}
					setState(317); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -3458196959662175866L) != 0) );
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
		enterRule(_localctx, 54, RULE_element);
		int _la;
		try {
			setState(335);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(322);
				actionBlock();
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				predicate();
				}
				break;
			case BEGIN_ERR:
				enterOuterAlt(_localctx, 3);
				{
				setState(324);
				errorBlock();
				}
				break;
			case BEGIN_REP:
				enterOuterAlt(_localctx, 4);
				{
				setState(325);
				repetitionBlock();
				}
				break;
			case TOKEN_REF:
			case RULE_REF:
			case ID:
				enterOuterAlt(_localctx, 5);
				{
				setState(326);
				expression();
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 49539595901075456L) != 0)) {
					{
					setState(327);
					ebnfSuffix();
					}
				}

				}
				break;
			case INT:
			case STRING_LITERAL:
			case AT:
			case NOT:
				enterOuterAlt(_localctx, 6);
				{
				setState(330);
				atom();
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 49539595901075456L) != 0)) {
					{
					setState(331);
					ebnfSuffix();
					}
				}

				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 7);
				{
				setState(334);
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
		public TerminalNode LT() { return getToken(LancerSpecParser.LT, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode GT() { return getToken(LancerSpecParser.GT, 0); }
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
		enterRule(_localctx, 56, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			match(LT);
			setState(338);
			arg();
			setState(339);
			match(GT);
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
		enterRule(_localctx, 58, RULE_expression);
		try {
			setState(343);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(341);
				variableAssignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(342);
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
		enterRule(_localctx, 60, RULE_variableAssignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			compIdentifier();
			setState(346);
			_la = _input.LA(1);
			if ( !(_la==ASSIGN || _la==PLUS_ASSIGN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOLLAR) {
				{
				setState(347);
				match(DOLLAR);
				}
			}

			setState(350);
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
		enterRule(_localctx, 62, RULE_ebnf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			block();
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 49539595901075456L) != 0)) {
				{
				setState(353);
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
		enterRule(_localctx, 64, RULE_ebnfSuffix);
		try {
			setState(361);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(356);
				match(QUESTION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(357);
				match(STAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(358);
				match(PLUS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(359);
				match(STAR);
				setState(360);
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
		enterRule(_localctx, 66, RULE_lexerAtom);
		try {
			setState(367);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(363);
				characterRange();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(364);
				terminal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(365);
				notSet();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(366);
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
		enterRule(_localctx, 68, RULE_atom);
		try {
			setState(373);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				terminal();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(370);
				notSet();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(371);
				precedence();
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(372);
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
		enterRule(_localctx, 70, RULE_weightage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(INT);
			setState(376);
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
		enterRule(_localctx, 72, RULE_precedence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			match(AT);
			setState(379);
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
		enterRule(_localctx, 74, RULE_notSet);
		try {
			setState(385);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(381);
				match(NOT);
				setState(382);
				setElement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(383);
				match(NOT);
				setState(384);
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
		enterRule(_localctx, 76, RULE_blockSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(LPAREN);
			setState(388);
			setElement();
			setState(393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(389);
				match(OR);
				setState(390);
				setElement();
				}
				}
				setState(395);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(396);
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
		enterRule(_localctx, 78, RULE_setElement);
		try {
			setState(402);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(398);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(399);
				match(STRING_LITERAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(400);
				characterRange();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(401);
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
		enterRule(_localctx, 80, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			match(LPAREN);
			setState(405);
			altList();
			setState(406);
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
		enterRule(_localctx, 82, RULE_characterRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			match(STRING_LITERAL);
			setState(409);
			match(RANGE);
			setState(410);
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
		enterRule(_localctx, 84, RULE_terminal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
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
		enterRule(_localctx, 86, RULE_compIdentifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			identifier();
			setState(416);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(415);
				argActionBlock();
				}
				break;
			}
			setState(422);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(418);
					match(DOT);
					setState(419);
					compIdentifier();
					}
					} 
				}
				setState(424);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
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
		enterRule(_localctx, 88, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & -9223372036854775802L) != 0)) ) {
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
		public TerminalNode TOKEN_REF() { return getToken(LancerSpecParser.TOKEN_REF, 0); }
		public TerminalNode COLON() { return getToken(LancerSpecParser.COLON, 0); }
		public LexerRuleBlockContext lexerRuleBlock() {
			return getRuleContext(LexerRuleBlockContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LancerSpecParser.SEMI, 0); }
		public RuleModifierContext ruleModifier() {
			return getRuleContext(RuleModifierContext.class,0);
		}
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
		enterRule(_localctx, 90, RULE_lexerRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FRAGMENT) {
				{
				setState(427);
				ruleModifier();
				}
			}

			setState(430);
			match(TOKEN_REF);
			setState(432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONS) {
				{
				setState(431);
				optionsSpec();
				}
			}

			setState(434);
			match(COLON);
			setState(435);
			lexerRuleBlock();
			setState(436);
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
		enterRule(_localctx, 92, RULE_lexerRuleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
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
		enterRule(_localctx, 94, RULE_lexerAltList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			lexerAlt();
			setState(445);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(441);
				match(OR);
				setState(442);
				lexerAlt();
				}
				}
				setState(447);
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
		enterRule(_localctx, 96, RULE_lexerAlt);
		try {
			setState(450);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(448);
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
		enterRule(_localctx, 98, RULE_lexerElements);
		int _la;
		try {
			setState(458);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case LEXER_CHAR_SET:
			case STRING_LITERAL:
			case BEGIN_ACTION:
			case BEGIN_ERR:
			case BEGIN_REP:
			case LPAREN:
			case LBRACK:
			case NOT:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(453); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(452);
					lexerElement();
					}
					}
					setState(455); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -4611611045478266610L) != 0) );
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
		public CharSetContext charSet() {
			return getRuleContext(CharSetContext.class,0);
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
		enterRule(_localctx, 100, RULE_lexerElement);
		int _la;
		try {
			setState(476);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(460);
				actionBlock();
				setState(462);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUESTION) {
					{
					setState(461);
					match(QUESTION);
					}
				}

				}
				break;
			case BEGIN_ERR:
				enterOuterAlt(_localctx, 2);
				{
				setState(464);
				errorBlock();
				}
				break;
			case BEGIN_REP:
				enterOuterAlt(_localctx, 3);
				{
				setState(465);
				repetitionBlock();
				}
				break;
			case TOKEN_REF:
			case RULE_REF:
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(466);
				expression();
				}
				break;
			case LEXER_CHAR_SET:
			case STRING_LITERAL:
			case NOT:
				enterOuterAlt(_localctx, 5);
				{
				setState(467);
				lexerAtom();
				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 49539595901075456L) != 0)) {
					{
					setState(468);
					ebnfSuffix();
					}
				}

				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 6);
				{
				setState(471);
				lexerBlock();
				setState(473);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 49539595901075456L) != 0)) {
					{
					setState(472);
					ebnfSuffix();
					}
				}

				}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 7);
				{
				setState(475);
				charSet();
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
		enterRule(_localctx, 102, RULE_lexerBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478);
			match(LPAREN);
			setState(479);
			lexerAltList();
			setState(480);
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
	public static class CharSetContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(LancerSpecParser.LBRACK, 0); }
		public CharSetContentContext charSetContent() {
			return getRuleContext(CharSetContentContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(LancerSpecParser.RBRACK, 0); }
		public CharSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterCharSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitCharSet(this);
		}
	}

	public final CharSetContext charSet() throws RecognitionException {
		CharSetContext _localctx = new CharSetContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_charSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			match(LBRACK);
			setState(483);
			charSetContent();
			setState(484);
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
	public static class CharSetContentContext extends ParserRuleContext {
		public List<TerminalNode> ESC() { return getTokens(LancerSpecParser.ESC); }
		public TerminalNode ESC(int i) {
			return getToken(LancerSpecParser.ESC, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(LancerSpecParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(LancerSpecParser.RBRACK, i);
		}
		public CharSetContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charSetContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterCharSetContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitCharSetContent(this);
		}
	}

	public final CharSetContentContext charSetContent() throws RecognitionException {
		CharSetContentContext _localctx = new CharSetContentContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_charSetContent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(488);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					{
					setState(486);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==RBRACK) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case 2:
					{
					setState(487);
					match(ESC);
					}
					break;
				}
				}
				setState(490); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -140737488355330L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 511L) != 0) );
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
		enterRule(_localctx, 108, RULE_grammarOperator);
		try {
			setState(506);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(492);
				match(ASSIGN);
				setState(493);
				match(ASSIGN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(494);
				match(NEGATE);
				setState(495);
				match(ASSIGN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(496);
				match(PLUS);
				setState(497);
				match(ASSIGN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(498);
				match(PLUS);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(499);
				match(GT);
				setState(500);
				match(ASSIGN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(501);
				match(LT);
				setState(502);
				match(ASSIGN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(503);
				match(GT);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(504);
				match(LT);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(505);
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
		"\u0004\u0001H\u01fd\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0001"+
		"\u0000\u0001\u0000\u0005\u0000q\b\u0000\n\u0000\f\u0000t\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0081"+
		"\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u0087"+
		"\b\u0003\n\u0003\f\u0003\u008a\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005\u0095\b\u0005\n\u0005\f\u0005\u0098\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u009d\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006\u00a3\b\u0006\n\u0006\f\u0006\u00a6\t\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u00af\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0003\t\u00b7\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0005"+
		"\n\u00bd\b\n\n\n\f\n\u00c0\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0005\u000b\u00c7\b\u000b\n\u000b\f\u000b\u00ca\t\u000b\u0001"+
		"\u000b\u0003\u000b\u00cd\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005"+
		"\f\u00d3\b\f\n\f\f\f\u00d6\t\f\u0001\f\u0001\f\u0001\r\u0001\r\u0005\r"+
		"\u00dc\b\r\n\r\f\r\u00df\t\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0005\u000e\u00e7\b\u000e\n\u000e\f\u000e\u00ea\t\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u00f2\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f"+
		"\u00f7\b\u000f\n\u000f\f\u000f\u00fa\t\u000f\u0001\u0010\u0005\u0010\u00fd"+
		"\b\u0010\n\u0010\f\u0010\u0100\t\u0010\u0001\u0011\u0001\u0011\u0003\u0011"+
		"\u0104\b\u0011\u0001\u0012\u0003\u0012\u0107\b\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u010b\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u0111\b\u0012\u0001\u0012\u0003\u0012\u0114\b\u0012"+
		"\u0003\u0012\u0116\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0005\u0017\u0129\b\u0017\n\u0017\f\u0017\u012c\t\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0131\b\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0005\u0019\u0136\b\u0019\n\u0019\f\u0019\u0139\t\u0019"+
		"\u0001\u001a\u0004\u001a\u013c\b\u001a\u000b\u001a\f\u001a\u013d\u0001"+
		"\u001a\u0003\u001a\u0141\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0149\b\u001b\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u014d\b\u001b\u0001\u001b\u0003\u001b\u0150\b\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0003\u001d\u0158\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u015d\b\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0003\u001f"+
		"\u0163\b\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u016a\b \u0001"+
		"!\u0001!\u0001!\u0001!\u0003!\u0170\b!\u0001\"\u0001\"\u0001\"\u0001\""+
		"\u0003\"\u0176\b\"\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001%\u0001"+
		"%\u0001%\u0001%\u0003%\u0182\b%\u0001&\u0001&\u0001&\u0001&\u0005&\u0188"+
		"\b&\n&\f&\u018b\t&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0003"+
		"\'\u0193\b\'\u0001(\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001"+
		"*\u0001*\u0001+\u0001+\u0003+\u01a1\b+\u0001+\u0001+\u0005+\u01a5\b+\n"+
		"+\f+\u01a8\t+\u0001,\u0001,\u0001-\u0003-\u01ad\b-\u0001-\u0001-\u0003"+
		"-\u01b1\b-\u0001-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001/\u0001/\u0001"+
		"/\u0005/\u01bc\b/\n/\f/\u01bf\t/\u00010\u00010\u00030\u01c3\b0\u00011"+
		"\u00041\u01c6\b1\u000b1\f1\u01c7\u00011\u00031\u01cb\b1\u00012\u00012"+
		"\u00032\u01cf\b2\u00012\u00012\u00012\u00012\u00012\u00032\u01d6\b2\u0001"+
		"2\u00012\u00032\u01da\b2\u00012\u00032\u01dd\b2\u00013\u00013\u00013\u0001"+
		"3\u00014\u00014\u00014\u00014\u00015\u00015\u00045\u01e9\b5\u000b5\f5"+
		"\u01ea\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016"+
		"\u00016\u00016\u00016\u00016\u00016\u00036\u01fb\b6\u00016\u0000\u0000"+
		"7\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjl\u0000\u0003\u0002"+
		"\u00003366\u0002\u0000\u0001\u0002??\u0001\u0000//\u021b\u0000n\u0001"+
		"\u0000\u0000\u0000\u0002x\u0001\u0000\u0000\u0000\u0004\u0080\u0001\u0000"+
		"\u0000\u0000\u0006\u0082\u0001\u0000\u0000\u0000\b\u008d\u0001\u0000\u0000"+
		"\u0000\n\u009c\u0001\u0000\u0000\u0000\f\u009e\u0001\u0000\u0000\u0000"+
		"\u000e\u00ae\u0001\u0000\u0000\u0000\u0010\u00b0\u0001\u0000\u0000\u0000"+
		"\u0012\u00b4\u0001\u0000\u0000\u0000\u0014\u00ba\u0001\u0000\u0000\u0000"+
		"\u0016\u00c3\u0001\u0000\u0000\u0000\u0018\u00ce\u0001\u0000\u0000\u0000"+
		"\u001a\u00d9\u0001\u0000\u0000\u0000\u001c\u00e2\u0001\u0000\u0000\u0000"+
		"\u001e\u00f1\u0001\u0000\u0000\u0000 \u00fe\u0001\u0000\u0000\u0000\""+
		"\u0103\u0001\u0000\u0000\u0000$\u0106\u0001\u0000\u0000\u0000&\u011b\u0001"+
		"\u0000\u0000\u0000(\u011e\u0001\u0000\u0000\u0000*\u0121\u0001\u0000\u0000"+
		"\u0000,\u0123\u0001\u0000\u0000\u0000.\u0125\u0001\u0000\u0000\u00000"+
		"\u012d\u0001\u0000\u0000\u00002\u0132\u0001\u0000\u0000\u00004\u0140\u0001"+
		"\u0000\u0000\u00006\u014f\u0001\u0000\u0000\u00008\u0151\u0001\u0000\u0000"+
		"\u0000:\u0157\u0001\u0000\u0000\u0000<\u0159\u0001\u0000\u0000\u0000>"+
		"\u0160\u0001\u0000\u0000\u0000@\u0169\u0001\u0000\u0000\u0000B\u016f\u0001"+
		"\u0000\u0000\u0000D\u0175\u0001\u0000\u0000\u0000F\u0177\u0001\u0000\u0000"+
		"\u0000H\u017a\u0001\u0000\u0000\u0000J\u0181\u0001\u0000\u0000\u0000L"+
		"\u0183\u0001\u0000\u0000\u0000N\u0192\u0001\u0000\u0000\u0000P\u0194\u0001"+
		"\u0000\u0000\u0000R\u0198\u0001\u0000\u0000\u0000T\u019c\u0001\u0000\u0000"+
		"\u0000V\u019e\u0001\u0000\u0000\u0000X\u01a9\u0001\u0000\u0000\u0000Z"+
		"\u01ac\u0001\u0000\u0000\u0000\\\u01b6\u0001\u0000\u0000\u0000^\u01b8"+
		"\u0001\u0000\u0000\u0000`\u01c2\u0001\u0000\u0000\u0000b\u01ca\u0001\u0000"+
		"\u0000\u0000d\u01dc\u0001\u0000\u0000\u0000f\u01de\u0001\u0000\u0000\u0000"+
		"h\u01e2\u0001\u0000\u0000\u0000j\u01e8\u0001\u0000\u0000\u0000l\u01fa"+
		"\u0001\u0000\u0000\u0000nr\u0003\u0002\u0001\u0000oq\u0003\u0004\u0002"+
		"\u0000po\u0001\u0000\u0000\u0000qt\u0001\u0000\u0000\u0000rp\u0001\u0000"+
		"\u0000\u0000rs\u0001\u0000\u0000\u0000su\u0001\u0000\u0000\u0000tr\u0001"+
		"\u0000\u0000\u0000uv\u0003 \u0010\u0000vw\u0005\u0000\u0000\u0001w\u0001"+
		"\u0001\u0000\u0000\u0000xy\u0005\u001a\u0000\u0000yz\u0003X,\u0000z{\u0005"+
		")\u0000\u0000{\u0003\u0001\u0000\u0000\u0000|\u0081\u0003\u0006\u0003"+
		"\u0000}\u0081\u0003\f\u0006\u0000~\u0081\u0003\u0010\b\u0000\u007f\u0081"+
		"\u0003\u0012\t\u0000\u0080|\u0001\u0000\u0000\u0000\u0080}\u0001\u0000"+
		"\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u007f\u0001\u0000\u0000"+
		"\u0000\u0081\u0005\u0001\u0000\u0000\u0000\u0082\u0088\u0005\u000e\u0000"+
		"\u0000\u0083\u0084\u0003\b\u0004\u0000\u0084\u0085\u0005)\u0000\u0000"+
		"\u0085\u0087\u0001\u0000\u0000\u0000\u0086\u0083\u0001\u0000\u0000\u0000"+
		"\u0087\u008a\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000"+
		"\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u008b\u0001\u0000\u0000\u0000"+
		"\u008a\u0088\u0001\u0000\u0000\u0000\u008b\u008c\u0005-\u0000\u0000\u008c"+
		"\u0007\u0001\u0000\u0000\u0000\u008d\u008e\u0003X,\u0000\u008e\u008f\u0005"+
		"3\u0000\u0000\u008f\u0090\u0003\n\u0005\u0000\u0090\t\u0001\u0000\u0000"+
		"\u0000\u0091\u0096\u0003X,\u0000\u0092\u0093\u0005;\u0000\u0000\u0093"+
		"\u0095\u0003X,\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0095\u0098\u0001"+
		"\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001"+
		"\u0000\u0000\u0000\u0097\u009d\u0001\u0000\u0000\u0000\u0098\u0096\u0001"+
		"\u0000\u0000\u0000\u0099\u009d\u0005\b\u0000\u0000\u009a\u009d\u0003\u0014"+
		"\n\u0000\u009b\u009d\u0005\u0007\u0000\u0000\u009c\u0091\u0001\u0000\u0000"+
		"\u0000\u009c\u0099\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000"+
		"\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u000b\u0001\u0000\u0000"+
		"\u0000\u009e\u009f\u0005\u0013\u0000\u0000\u009f\u00a4\u0003\u000e\u0007"+
		"\u0000\u00a0\u00a1\u0005(\u0000\u0000\u00a1\u00a3\u0003\u000e\u0007\u0000"+
		"\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a3\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a7\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a8\u0005)\u0000\u0000\u00a8\r\u0001\u0000\u0000\u0000\u00a9"+
		"\u00aa\u0003X,\u0000\u00aa\u00ab\u00053\u0000\u0000\u00ab\u00ac\u0003"+
		"X,\u0000\u00ac\u00af\u0001\u0000\u0000\u0000\u00ad\u00af\u0003X,\u0000"+
		"\u00ae\u00a9\u0001\u0000\u0000\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000"+
		"\u00af\u000f\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005<\u0000\u0000\u00b1"+
		"\u00b2\u0003X,\u0000\u00b2\u00b3\u0003\u0014\n\u0000\u00b3\u0011\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b6\u0005\u000f\u0000\u0000\u00b5\u00b7\u0003"+
		"\u0016\u000b\u0000\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005"+
		"-\u0000\u0000\u00b9\u0013\u0001\u0000\u0000\u0000\u00ba\u00be\u0005\u000b"+
		"\u0000\u0000\u00bb\u00bd\u0005D\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000"+
		"\u0000\u00bd\u00c0\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000"+
		"\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005B\u0000\u0000"+
		"\u00c2\u0015\u0001\u0000\u0000\u0000\u00c3\u00c8\u0003X,\u0000\u00c4\u00c5"+
		"\u0005(\u0000\u0000\u00c5\u00c7\u0003X,\u0000\u00c6\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c7\u00ca\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9\u00cc\u0001\u0000"+
		"\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb\u00cd\u0005(\u0000"+
		"\u0000\u00cc\u00cb\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000"+
		"\u0000\u00cd\u0017\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005%\u0000\u0000"+
		"\u00cf\u00d4\u0003\u001e\u000f\u0000\u00d0\u00d1\u0005(\u0000\u0000\u00d1"+
		"\u00d3\u0003\u001e\u000f\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d6\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d7\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d7\u00d8\u0005+\u0000\u0000\u00d8\u0019"+
		"\u0001\u0000\u0000\u0000\u00d9\u00dd\u0005$\u0000\u0000\u00da\u00dc\u0005"+
		"G\u0000\u0000\u00db\u00da\u0001\u0000\u0000\u0000\u00dc\u00df\u0001\u0000"+
		"\u0000\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000"+
		"\u0000\u0000\u00de\u00e0\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000"+
		"\u0000\u0000\u00e0\u00e1\u0005E\u0000\u0000\u00e1\u001b\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e3\u0005.\u0000\u0000\u00e3\u00e8\u0003\u001e\u000f\u0000"+
		"\u00e4\u00e5\u0005(\u0000\u0000\u00e5\u00e7\u0003\u001e\u000f\u0000\u00e6"+
		"\u00e4\u0001\u0000\u0000\u0000\u00e7\u00ea\u0001\u0000\u0000\u0000\u00e8"+
		"\u00e6\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9"+
		"\u00eb\u0001\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00eb"+
		"\u00ec\u0005/\u0000\u0000\u00ec\u001d\u0001\u0000\u0000\u0000\u00ed\u00f2"+
		"\u0003V+\u0000\u00ee\u00f2\u0005\t\u0000\u0000\u00ef\u00f2\u0005\u0007"+
		"\u0000\u0000\u00f0\u00f2\u0003\u0018\f\u0000\u00f1\u00ed\u0001\u0000\u0000"+
		"\u0000\u00f1\u00ee\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000"+
		"\u0000\u00f1\u00f0\u0001\u0000\u0000\u0000\u00f2\u00f8\u0001\u0000\u0000"+
		"\u0000\u00f3\u00f4\u0003l6\u0000\u00f4\u00f5\u0003\u001e\u000f\u0000\u00f5"+
		"\u00f7\u0001\u0000\u0000\u0000\u00f6\u00f3\u0001\u0000\u0000\u0000\u00f7"+
		"\u00fa\u0001\u0000\u0000\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f8"+
		"\u00f9\u0001\u0000\u0000\u0000\u00f9\u001f\u0001\u0000\u0000\u0000\u00fa"+
		"\u00f8\u0001\u0000\u0000\u0000\u00fb\u00fd\u0003\"\u0011\u0000\u00fc\u00fb"+
		"\u0001\u0000\u0000\u0000\u00fd\u0100\u0001\u0000\u0000\u0000\u00fe\u00fc"+
		"\u0001\u0000\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000\u00ff!\u0001"+
		"\u0000\u0000\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0101\u0104\u0003"+
		"$\u0012\u0000\u0102\u0104\u0003Z-\u0000\u0103\u0101\u0001\u0000\u0000"+
		"\u0000\u0103\u0102\u0001\u0000\u0000\u0000\u0104#\u0001\u0000\u0000\u0000"+
		"\u0105\u0107\u0003*\u0015\u0000\u0106\u0105\u0001\u0000\u0000\u0000\u0106"+
		"\u0107\u0001\u0000\u0000\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108"+
		"\u010a\u0005\u0002\u0000\u0000\u0109\u010b\u0003\u001c\u000e\u0000\u010a"+
		"\u0109\u0001\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b"+
		"\u0115\u0001\u0000\u0000\u0000\u010c\u010d\u0003&\u0013\u0000\u010d\u010e"+
		"\u0003(\u0014\u0000\u010e\u0116\u0001\u0000\u0000\u0000\u010f\u0111\u0003"+
		"(\u0014\u0000\u0110\u010f\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000"+
		"\u0000\u0000\u0111\u0113\u0001\u0000\u0000\u0000\u0112\u0114\u0003&\u0013"+
		"\u0000\u0113\u0112\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000"+
		"\u0000\u0114\u0116\u0001\u0000\u0000\u0000\u0115\u010c\u0001\u0000\u0000"+
		"\u0000\u0115\u0110\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000"+
		"\u0000\u0117\u0118\u0005&\u0000\u0000\u0118\u0119\u0003,\u0016\u0000\u0119"+
		"\u011a\u0005)\u0000\u0000\u011a%\u0001\u0000\u0000\u0000\u011b\u011c\u0005"+
		"\u001e\u0000\u0000\u011c\u011d\u0003\u001c\u000e\u0000\u011d\'\u0001\u0000"+
		"\u0000\u0000\u011e\u011f\u0005\u001f\u0000\u0000\u011f\u0120\u0003\u001c"+
		"\u000e\u0000\u0120)\u0001\u0000\u0000\u0000\u0121\u0122\u0005\u0014\u0000"+
		"\u0000\u0122+\u0001\u0000\u0000\u0000\u0123\u0124\u0003.\u0017\u0000\u0124"+
		"-\u0001\u0000\u0000\u0000\u0125\u012a\u00030\u0018\u0000\u0126\u0127\u0005"+
		"8\u0000\u0000\u0127\u0129\u00030\u0018\u0000\u0128\u0126\u0001\u0000\u0000"+
		"\u0000\u0129\u012c\u0001\u0000\u0000\u0000\u012a\u0128\u0001\u0000\u0000"+
		"\u0000\u012a\u012b\u0001\u0000\u0000\u0000\u012b/\u0001\u0000\u0000\u0000"+
		"\u012c\u012a\u0001\u0000\u0000\u0000\u012d\u0130\u00034\u001a\u0000\u012e"+
		"\u012f\u0005=\u0000\u0000\u012f\u0131\u0003X,\u0000\u0130\u012e\u0001"+
		"\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000\u01311\u0001\u0000"+
		"\u0000\u0000\u0132\u0137\u00034\u001a\u0000\u0133\u0134\u00058\u0000\u0000"+
		"\u0134\u0136\u00034\u001a\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0136"+
		"\u0139\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0137"+
		"\u0138\u0001\u0000\u0000\u0000\u01383\u0001\u0000\u0000\u0000\u0139\u0137"+
		"\u0001\u0000\u0000\u0000\u013a\u013c\u00036\u001b\u0000\u013b\u013a\u0001"+
		"\u0000\u0000\u0000\u013c\u013d\u0001\u0000\u0000\u0000\u013d\u013b\u0001"+
		"\u0000\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u0141\u0001"+
		"\u0000\u0000\u0000\u013f\u0141\u0001\u0000\u0000\u0000\u0140\u013b\u0001"+
		"\u0000\u0000\u0000\u0140\u013f\u0001\u0000\u0000\u0000\u01415\u0001\u0000"+
		"\u0000\u0000\u0142\u0150\u0003\u0014\n\u0000\u0143\u0150\u00038\u001c"+
		"\u0000\u0144\u0150\u0003\u001a\r\u0000\u0145\u0150\u0003\u0018\f\u0000"+
		"\u0146\u0148\u0003:\u001d\u0000\u0147\u0149\u0003@ \u0000\u0148\u0147"+
		"\u0001\u0000\u0000\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149\u0150"+
		"\u0001\u0000\u0000\u0000\u014a\u014c\u0003D\"\u0000\u014b\u014d\u0003"+
		"@ \u0000\u014c\u014b\u0001\u0000\u0000\u0000\u014c\u014d\u0001\u0000\u0000"+
		"\u0000\u014d\u0150\u0001\u0000\u0000\u0000\u014e\u0150\u0003>\u001f\u0000"+
		"\u014f\u0142\u0001\u0000\u0000\u0000\u014f\u0143\u0001\u0000\u0000\u0000"+
		"\u014f\u0144\u0001\u0000\u0000\u0000\u014f\u0145\u0001\u0000\u0000\u0000"+
		"\u014f\u0146\u0001\u0000\u0000\u0000\u014f\u014a\u0001\u0000\u0000\u0000"+
		"\u014f\u014e\u0001\u0000\u0000\u0000\u01507\u0001\u0000\u0000\u0000\u0151"+
		"\u0152\u00051\u0000\u0000\u0152\u0153\u0003\u001e\u000f\u0000\u0153\u0154"+
		"\u00052\u0000\u0000\u01549\u0001\u0000\u0000\u0000\u0155\u0158\u0003<"+
		"\u001e\u0000\u0156\u0158\u0003V+\u0000\u0157\u0155\u0001\u0000\u0000\u0000"+
		"\u0157\u0156\u0001\u0000\u0000\u0000\u0158;\u0001\u0000\u0000\u0000\u0159"+
		"\u015a\u0003V+\u0000\u015a\u015c\u0007\u0000\u0000\u0000\u015b\u015d\u0005"+
		"9\u0000\u0000\u015c\u015b\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000"+
		"\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u015f\u0003V+\u0000"+
		"\u015f=\u0001\u0000\u0000\u0000\u0160\u0162\u0003P(\u0000\u0161\u0163"+
		"\u0003@ \u0000\u0162\u0161\u0001\u0000\u0000\u0000\u0162\u0163\u0001\u0000"+
		"\u0000\u0000\u0163?\u0001\u0000\u0000\u0000\u0164\u016a\u00054\u0000\u0000"+
		"\u0165\u016a\u00055\u0000\u0000\u0166\u016a\u00057\u0000\u0000\u0167\u0168"+
		"\u00055\u0000\u0000\u0168\u016a\u00055\u0000\u0000\u0169\u0164\u0001\u0000"+
		"\u0000\u0000\u0169\u0165\u0001\u0000\u0000\u0000\u0169\u0166\u0001\u0000"+
		"\u0000\u0000\u0169\u0167\u0001\u0000\u0000\u0000\u016aA\u0001\u0000\u0000"+
		"\u0000\u016b\u0170\u0003R)\u0000\u016c\u0170\u0003T*\u0000\u016d\u0170"+
		"\u0003J%\u0000\u016e\u0170\u0005\u0003\u0000\u0000\u016f\u016b\u0001\u0000"+
		"\u0000\u0000\u016f\u016c\u0001\u0000\u0000\u0000\u016f\u016d\u0001\u0000"+
		"\u0000\u0000\u016f\u016e\u0001\u0000\u0000\u0000\u0170C\u0001\u0000\u0000"+
		"\u0000\u0171\u0176\u0003T*\u0000\u0172\u0176\u0003J%\u0000\u0173\u0176"+
		"\u0003H$\u0000\u0174\u0176\u0003F#\u0000\u0175\u0171\u0001\u0000\u0000"+
		"\u0000\u0175\u0172\u0001\u0000\u0000\u0000\u0175\u0173\u0001\u0000\u0000"+
		"\u0000\u0175\u0174\u0001\u0000\u0000\u0000\u0176E\u0001\u0000\u0000\u0000"+
		"\u0177\u0178\u0005\u0007\u0000\u0000\u0178\u0179\u0005\u0011\u0000\u0000"+
		"\u0179G\u0001\u0000\u0000\u0000\u017a\u017b\u0005<\u0000\u0000\u017b\u017c"+
		"\u0005\u0007\u0000\u0000\u017cI\u0001\u0000\u0000\u0000\u017d\u017e\u0005"+
		">\u0000\u0000\u017e\u0182\u0003N\'\u0000\u017f\u0180\u0005>\u0000\u0000"+
		"\u0180\u0182\u0003L&\u0000\u0181\u017d\u0001\u0000\u0000\u0000\u0181\u017f"+
		"\u0001\u0000\u0000\u0000\u0182K\u0001\u0000\u0000\u0000\u0183\u0184\u0005"+
		"*\u0000\u0000\u0184\u0189\u0003N\'\u0000\u0185\u0186\u00058\u0000\u0000"+
		"\u0186\u0188\u0003N\'\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0188"+
		"\u018b\u0001\u0000\u0000\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189"+
		"\u018a\u0001\u0000\u0000\u0000\u018a\u018c\u0001\u0000\u0000\u0000\u018b"+
		"\u0189\u0001\u0000\u0000\u0000\u018c\u018d\u0005+\u0000\u0000\u018dM\u0001"+
		"\u0000\u0000\u0000\u018e\u0193\u0005?\u0000\u0000\u018f\u0193\u0005\b"+
		"\u0000\u0000\u0190\u0193\u0003R)\u0000\u0191\u0193\u0005\u0003\u0000\u0000"+
		"\u0192\u018e\u0001\u0000\u0000\u0000\u0192\u018f\u0001\u0000\u0000\u0000"+
		"\u0192\u0190\u0001\u0000\u0000\u0000\u0192\u0191\u0001\u0000\u0000\u0000"+
		"\u0193O\u0001\u0000\u0000\u0000\u0194\u0195\u0005*\u0000\u0000\u0195\u0196"+
		"\u00032\u0019\u0000\u0196\u0197\u0005+\u0000\u0000\u0197Q\u0001\u0000"+
		"\u0000\u0000\u0198\u0199\u0005\b\u0000\u0000\u0199\u019a\u0005:\u0000"+
		"\u0000\u019a\u019b\u0005\b\u0000\u0000\u019bS\u0001\u0000\u0000\u0000"+
		"\u019c\u019d\u0005\b\u0000\u0000\u019dU\u0001\u0000\u0000\u0000\u019e"+
		"\u01a0\u0003X,\u0000\u019f\u01a1\u0003\u001c\u000e\u0000\u01a0\u019f\u0001"+
		"\u0000\u0000\u0000\u01a0\u01a1\u0001\u0000\u0000\u0000\u01a1\u01a6\u0001"+
		"\u0000\u0000\u0000\u01a2\u01a3\u0005;\u0000\u0000\u01a3\u01a5\u0003V+"+
		"\u0000\u01a4\u01a2\u0001\u0000\u0000\u0000\u01a5\u01a8\u0001\u0000\u0000"+
		"\u0000\u01a6\u01a4\u0001\u0000\u0000\u0000\u01a6\u01a7\u0001\u0000\u0000"+
		"\u0000\u01a7W\u0001\u0000\u0000\u0000\u01a8\u01a6\u0001\u0000\u0000\u0000"+
		"\u01a9\u01aa\u0007\u0001\u0000\u0000\u01aaY\u0001\u0000\u0000\u0000\u01ab"+
		"\u01ad\u0003*\u0015\u0000\u01ac\u01ab\u0001\u0000\u0000\u0000\u01ac\u01ad"+
		"\u0001\u0000\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae\u01b0"+
		"\u0005\u0001\u0000\u0000\u01af\u01b1\u0003\u0006\u0003\u0000\u01b0\u01af"+
		"\u0001\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000\u0000\u0000\u01b1\u01b2"+
		"\u0001\u0000\u0000\u0000\u01b2\u01b3\u0005&\u0000\u0000\u01b3\u01b4\u0003"+
		"\\.\u0000\u01b4\u01b5\u0005)\u0000\u0000\u01b5[\u0001\u0000\u0000\u0000"+
		"\u01b6\u01b7\u0003^/\u0000\u01b7]\u0001\u0000\u0000\u0000\u01b8\u01bd"+
		"\u0003`0\u0000\u01b9\u01ba\u00058\u0000\u0000\u01ba\u01bc\u0003`0\u0000"+
		"\u01bb\u01b9\u0001\u0000\u0000\u0000\u01bc\u01bf\u0001\u0000\u0000\u0000"+
		"\u01bd\u01bb\u0001\u0000\u0000\u0000\u01bd\u01be\u0001\u0000\u0000\u0000"+
		"\u01be_\u0001\u0000\u0000\u0000\u01bf\u01bd\u0001\u0000\u0000\u0000\u01c0"+
		"\u01c3\u0003b1\u0000\u01c1\u01c3\u0001\u0000\u0000\u0000\u01c2\u01c0\u0001"+
		"\u0000\u0000\u0000\u01c2\u01c1\u0001\u0000\u0000\u0000\u01c3a\u0001\u0000"+
		"\u0000\u0000\u01c4\u01c6\u0003d2\u0000\u01c5\u01c4\u0001\u0000\u0000\u0000"+
		"\u01c6\u01c7\u0001\u0000\u0000\u0000\u01c7\u01c5\u0001\u0000\u0000\u0000"+
		"\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01cb\u0001\u0000\u0000\u0000"+
		"\u01c9\u01cb\u0001\u0000\u0000\u0000\u01ca\u01c5\u0001\u0000\u0000\u0000"+
		"\u01ca\u01c9\u0001\u0000\u0000\u0000\u01cbc\u0001\u0000\u0000\u0000\u01cc"+
		"\u01ce\u0003\u0014\n\u0000\u01cd\u01cf\u00054\u0000\u0000\u01ce\u01cd"+
		"\u0001\u0000\u0000\u0000\u01ce\u01cf\u0001\u0000\u0000\u0000\u01cf\u01dd"+
		"\u0001\u0000\u0000\u0000\u01d0\u01dd\u0003\u001a\r\u0000\u01d1\u01dd\u0003"+
		"\u0018\f\u0000\u01d2\u01dd\u0003:\u001d\u0000\u01d3\u01d5\u0003B!\u0000"+
		"\u01d4\u01d6\u0003@ \u0000\u01d5\u01d4\u0001\u0000\u0000\u0000\u01d5\u01d6"+
		"\u0001\u0000\u0000\u0000\u01d6\u01dd\u0001\u0000\u0000\u0000\u01d7\u01d9"+
		"\u0003f3\u0000\u01d8\u01da\u0003@ \u0000\u01d9\u01d8\u0001\u0000\u0000"+
		"\u0000\u01d9\u01da\u0001\u0000\u0000\u0000\u01da\u01dd\u0001\u0000\u0000"+
		"\u0000\u01db\u01dd\u0003h4\u0000\u01dc\u01cc\u0001\u0000\u0000\u0000\u01dc"+
		"\u01d0\u0001\u0000\u0000\u0000\u01dc\u01d1\u0001\u0000\u0000\u0000\u01dc"+
		"\u01d2\u0001\u0000\u0000\u0000\u01dc\u01d3\u0001\u0000\u0000\u0000\u01dc"+
		"\u01d7\u0001\u0000\u0000\u0000\u01dc\u01db\u0001\u0000\u0000\u0000\u01dd"+
		"e\u0001\u0000\u0000\u0000\u01de\u01df\u0005*\u0000\u0000\u01df\u01e0\u0003"+
		"^/\u0000\u01e0\u01e1\u0005+\u0000\u0000\u01e1g\u0001\u0000\u0000\u0000"+
		"\u01e2\u01e3\u0005.\u0000\u0000\u01e3\u01e4\u0003j5\u0000\u01e4\u01e5"+
		"\u0005/\u0000\u0000\u01e5i\u0001\u0000\u0000\u0000\u01e6\u01e9\b\u0002"+
		"\u0000\u0000\u01e7\u01e9\u0005\r\u0000\u0000\u01e8\u01e6\u0001\u0000\u0000"+
		"\u0000\u01e8\u01e7\u0001\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000"+
		"\u0000\u01ea\u01e8\u0001\u0000\u0000\u0000\u01ea\u01eb\u0001\u0000\u0000"+
		"\u0000\u01ebk\u0001\u0000\u0000\u0000\u01ec\u01ed\u00053\u0000\u0000\u01ed"+
		"\u01fb\u00053\u0000\u0000\u01ee\u01ef\u0005\u0012\u0000\u0000\u01ef\u01fb"+
		"\u00053\u0000\u0000\u01f0\u01f1\u00057\u0000\u0000\u01f1\u01fb\u00053"+
		"\u0000\u0000\u01f2\u01fb\u00057\u0000\u0000\u01f3\u01f4\u00052\u0000\u0000"+
		"\u01f4\u01fb\u00053\u0000\u0000\u01f5\u01f6\u00051\u0000\u0000\u01f6\u01fb"+
		"\u00053\u0000\u0000\u01f7\u01fb\u00052\u0000\u0000\u01f8\u01fb\u00051"+
		"\u0000\u0000\u01f9\u01fb\u00053\u0000\u0000\u01fa\u01ec\u0001\u0000\u0000"+
		"\u0000\u01fa\u01ee\u0001\u0000\u0000\u0000\u01fa\u01f0\u0001\u0000\u0000"+
		"\u0000\u01fa\u01f2\u0001\u0000\u0000\u0000\u01fa\u01f3\u0001\u0000\u0000"+
		"\u0000\u01fa\u01f5\u0001\u0000\u0000\u0000\u01fa\u01f7\u0001\u0000\u0000"+
		"\u0000\u01fa\u01f8\u0001\u0000\u0000\u0000\u01fa\u01f9\u0001\u0000\u0000"+
		"\u0000\u01fbm\u0001\u0000\u0000\u00007r\u0080\u0088\u0096\u009c\u00a4"+
		"\u00ae\u00b6\u00be\u00c8\u00cc\u00d4\u00dd\u00e8\u00f1\u00f8\u00fe\u0103"+
		"\u0106\u010a\u0110\u0113\u0115\u012a\u0130\u0137\u013d\u0140\u0148\u014c"+
		"\u014f\u0157\u015c\u0162\u0169\u016f\u0175\u0181\u0189\u0192\u01a0\u01a6"+
		"\u01ac\u01b0\u01bd\u01c2\u01c7\u01ca\u01ce\u01d5\u01d9\u01dc\u01e8\u01ea"+
		"\u01fa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}