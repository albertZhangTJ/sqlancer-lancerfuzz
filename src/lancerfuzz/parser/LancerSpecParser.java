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
		LINE_COMMENT=6, STRING_LITERAL=7, UNTERMINATED_STRING_LITERAL=8, BOOL_LITERAL=9, 
		INT_LITERAL=10, BEGIN_ACTION=11, DASH=12, ESC=13, OPTIONS=14, TOKENS=15, 
		CHANNELS=16, PERCENTAGE=17, NEGATE=18, IMPORT=19, FRAGMENT=20, LEXER=21, 
		PARSER=22, GRAMMAR=23, RETURNS=24, COLON=25, COLONCOLON=26, COMMA=27, 
		SEMI=28, LPAREN=29, RPAREN=30, LBRACE=31, RBRACE=32, LBRACK=33, RBRACK=34, 
		RARROW=35, LT=36, GT=37, ASSIGN=38, QUESTION=39, STAR=40, PLUS_ASSIGN=41, 
		PLUS=42, OR=43, DOLLAR=44, RANGE=45, DOT=46, AT=47, POUND=48, NOT=49, 
		ID=50, WS=51, ERRCHAR=52, END_ACTION=53, UNTERMINATED_ACTION=54, ACTION_CONTENT=55, 
		UNTERMINATED_CHAR_SET=56;
	public static final int
		RULE_grammarSpec = 0, RULE_grammarDecl = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_action_ = 8, RULE_tokensSpec = 9, RULE_actionBlock = 10, 
		RULE_idList = 11, RULE_argActionBlock = 12, RULE_arg = 13, RULE_rules = 14, 
		RULE_ruleSpec = 15, RULE_parserRuleSpec = 16, RULE_ruleReturns = 17, RULE_ruleModifier = 18, 
		RULE_altList = 19, RULE_alternative = 20, RULE_element = 21, RULE_predicate = 22, 
		RULE_expression = 23, RULE_mexpr = 24, RULE_lexpr = 25, RULE_variable = 26, 
		RULE_ebnf = 27, RULE_ebnfSuffix = 28, RULE_lexerAtom = 29, RULE_atom = 30, 
		RULE_weightage = 31, RULE_precedence = 32, RULE_notSet = 33, RULE_blockSet = 34, 
		RULE_setElement = 35, RULE_block = 36, RULE_characterRange = 37, RULE_terminal = 38, 
		RULE_compIdentifier = 39, RULE_identifier = 40, RULE_lexerRuleSpec = 41, 
		RULE_lexerAltList = 42, RULE_lexerAlt = 43, RULE_lexerElement = 44, RULE_lexerBlock = 45, 
		RULE_charSet = 46, RULE_charSetContent = 47, RULE_grammarOperator = 48;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarSpec", "grammarDecl", "prequelConstruct", "optionsSpec", "option", 
			"optionValue", "delegateGrammars", "delegateGrammar", "action_", "tokensSpec", 
			"actionBlock", "idList", "argActionBlock", "arg", "rules", "ruleSpec", 
			"parserRuleSpec", "ruleReturns", "ruleModifier", "altList", "alternative", 
			"element", "predicate", "expression", "mexpr", "lexpr", "variable", "ebnf", 
			"ebnfSuffix", "lexerAtom", "atom", "weightage", "precedence", "notSet", 
			"blockSet", "setElement", "block", "characterRange", "terminal", "compIdentifier", 
			"identifier", "lexerRuleSpec", "lexerAltList", "lexerAlt", "lexerElement", 
			"lexerBlock", "charSet", "charSetContent", "grammarOperator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"'-'", null, null, null, null, "'%'", "'!'", "'import'", "'fragment'", 
			"'lexer'", "'parser'", "'grammar'", "'returns'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TOKEN_REF", "RULE_REF", "LEXER_CHAR_SET", "DOC_COMMENT", "BLOCK_COMMENT", 
			"LINE_COMMENT", "STRING_LITERAL", "UNTERMINATED_STRING_LITERAL", "BOOL_LITERAL", 
			"INT_LITERAL", "BEGIN_ACTION", "DASH", "ESC", "OPTIONS", "TOKENS", "CHANNELS", 
			"PERCENTAGE", "NEGATE", "IMPORT", "FRAGMENT", "LEXER", "PARSER", "GRAMMAR", 
			"RETURNS", "COLON", "COLONCOLON", "COMMA", "SEMI", "LPAREN", "RPAREN", 
			"LBRACE", "RBRACE", "LBRACK", "RBRACK", "RARROW", "LT", "GT", "ASSIGN", 
			"QUESTION", "STAR", "PLUS_ASSIGN", "PLUS", "OR", "DOLLAR", "RANGE", "DOT", 
			"AT", "POUND", "NOT", "ID", "WS", "ERRCHAR", "END_ACTION", "UNTERMINATED_ACTION", 
			"ACTION_CONTENT", "UNTERMINATED_CHAR_SET"
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
			setState(98);
			grammarDecl();
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 140737488928768L) != 0)) {
				{
				{
				setState(99);
				prequelConstruct();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
			rules();
			setState(106);
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
			setState(108);
			match(GRAMMAR);
			setState(109);
			identifier();
			setState(110);
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
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPTIONS:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				optionsSpec();
				}
				break;
			case IMPORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				delegateGrammars();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				action_();
				}
				break;
			case TOKENS:
				enterOuterAlt(_localctx, 4);
				{
				setState(115);
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
			setState(118);
			match(OPTIONS);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TOKEN_REF || _la==RULE_REF) {
				{
				{
				setState(119);
				option();
				setState(120);
				match(SEMI);
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(127);
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
			setState(129);
			identifier();
			setState(130);
			match(ASSIGN);
			setState(131);
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
		public TerminalNode INT_LITERAL() { return getToken(LancerSpecParser.INT_LITERAL, 0); }
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
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				identifier();
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(134);
					match(DOT);
					setState(135);
					identifier();
					}
					}
					setState(140);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				match(STRING_LITERAL);
				}
				break;
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(142);
				actionBlock();
				}
				break;
			case INT_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(143);
				match(INT_LITERAL);
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
			setState(146);
			match(IMPORT);
			setState(147);
			delegateGrammar();
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(148);
				match(COMMA);
				setState(149);
				delegateGrammar();
				}
				}
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(155);
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
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				identifier();
				setState(158);
				match(ASSIGN);
				setState(159);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
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
			setState(164);
			match(AT);
			setState(165);
			identifier();
			setState(166);
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
			setState(168);
			match(TOKENS);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TOKEN_REF || _la==RULE_REF) {
				{
				setState(169);
				idList();
				}
			}

			setState(172);
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
			setState(174);
			match(BEGIN_ACTION);
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ACTION_CONTENT) {
				{
				{
				setState(175);
				match(ACTION_CONTENT);
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181);
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
			setState(183);
			identifier();
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(184);
					match(COMMA);
					setState(185);
					identifier();
					}
					} 
				}
				setState(190);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(191);
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
		enterRule(_localctx, 24, RULE_argActionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(LBRACK);
			setState(195);
			arg();
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(196);
				match(COMMA);
				setState(197);
				arg();
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(203);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		enterRule(_localctx, 26, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			expression();
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
		enterRule(_localctx, 28, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1048582L) != 0)) {
				{
				{
				setState(207);
				ruleSpec();
				}
				}
				setState(212);
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
		enterRule(_localctx, 30, RULE_ruleSpec);
		try {
			setState(215);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				parserRuleSpec();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
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
		public AltListContext altList() {
			return getRuleContext(AltListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LancerSpecParser.SEMI, 0); }
		public RuleModifierContext ruleModifier() {
			return getRuleContext(RuleModifierContext.class,0);
		}
		public ArgActionBlockContext argActionBlock() {
			return getRuleContext(ArgActionBlockContext.class,0);
		}
		public RuleReturnsContext ruleReturns() {
			return getRuleContext(RuleReturnsContext.class,0);
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
		enterRule(_localctx, 32, RULE_parserRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FRAGMENT) {
				{
				setState(217);
				ruleModifier();
				}
			}

			setState(220);
			match(RULE_REF);
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(221);
				argActionBlock();
				}
			}

			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(224);
				ruleReturns();
				}
			}

			setState(227);
			match(COLON);
			setState(228);
			altList();
			setState(229);
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
		enterRule(_localctx, 34, RULE_ruleReturns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(RETURNS);
			setState(232);
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
		enterRule(_localctx, 36, RULE_ruleModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
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
		enterRule(_localctx, 38, RULE_altList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			alternative();
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(237);
				match(OR);
				setState(238);
				alternative();
				}
				}
				setState(243);
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
		enterRule(_localctx, 40, RULE_alternative);
		int _la;
		try {
			setState(250);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case STRING_LITERAL:
			case BOOL_LITERAL:
			case INT_LITERAL:
			case BEGIN_ACTION:
			case LPAREN:
			case LT:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(245); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(244);
					element();
					}
					}
					setState(247); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 140806744706694L) != 0) );
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
	public static class ElementContext extends ParserRuleContext {
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public WeightageContext weightage() {
			return getRuleContext(WeightageContext.class,0);
		}
		public PrecedenceContext precedence() {
			return getRuleContext(PrecedenceContext.class,0);
		}
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public EbnfSuffixContext ebnfSuffix() {
			return getRuleContext(EbnfSuffixContext.class,0);
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
		enterRule(_localctx, 42, RULE_element);
		int _la;
		try {
			setState(261);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(252);
				actionBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(253);
				predicate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(254);
				weightage();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(255);
				precedence();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(256);
				arg();
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6047313952768L) != 0)) {
					{
					setState(257);
					ebnfSuffix();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(260);
				ebnf();
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
	public static class PredicateContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(LancerSpecParser.LT, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode GT() { return getToken(LancerSpecParser.GT, 0); }
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
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
		enterRule(_localctx, 44, RULE_predicate);
		try {
			setState(270);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				match(LT);
				setState(264);
				arg();
				setState(265);
				match(GT);
				}
				break;
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
				actionBlock();
				setState(268);
				match(QUESTION);
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
	public static class ExpressionContext extends ParserRuleContext {
		public List<MexprContext> mexpr() {
			return getRuleContexts(MexprContext.class);
		}
		public MexprContext mexpr(int i) {
			return getRuleContext(MexprContext.class,i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(LancerSpecParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(LancerSpecParser.ASSIGN, i);
		}
		public List<TerminalNode> PLUS_ASSIGN() { return getTokens(LancerSpecParser.PLUS_ASSIGN); }
		public TerminalNode PLUS_ASSIGN(int i) {
			return getToken(LancerSpecParser.PLUS_ASSIGN, i);
		}
		public List<TerminalNode> DOLLAR() { return getTokens(LancerSpecParser.DOLLAR); }
		public TerminalNode DOLLAR(int i) {
			return getToken(LancerSpecParser.DOLLAR, i);
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
		enterRule(_localctx, 46, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			mexpr();
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ASSIGN || _la==PLUS_ASSIGN) {
				{
				{
				setState(273);
				_la = _input.LA(1);
				if ( !(_la==ASSIGN || _la==PLUS_ASSIGN) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOLLAR) {
					{
					setState(274);
					match(DOLLAR);
					}
				}

				setState(277);
				mexpr();
				}
				}
				setState(282);
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
	public static class MexprContext extends ParserRuleContext {
		public List<LexprContext> lexpr() {
			return getRuleContexts(LexprContext.class);
		}
		public LexprContext lexpr(int i) {
			return getRuleContext(LexprContext.class,i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(LancerSpecParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(LancerSpecParser.ASSIGN, i);
		}
		public List<TerminalNode> NEGATE() { return getTokens(LancerSpecParser.NEGATE); }
		public TerminalNode NEGATE(int i) {
			return getToken(LancerSpecParser.NEGATE, i);
		}
		public List<TerminalNode> GT() { return getTokens(LancerSpecParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(LancerSpecParser.GT, i);
		}
		public List<TerminalNode> LT() { return getTokens(LancerSpecParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(LancerSpecParser.LT, i);
		}
		public MexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterMexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitMexpr(this);
		}
	}

	public final MexprContext mexpr() throws RecognitionException {
		MexprContext _localctx = new MexprContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_mexpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			lexpr();
			setState(299);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(294);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						setState(284);
						match(ASSIGN);
						setState(285);
						match(ASSIGN);
						}
						break;
					case 2:
						{
						setState(286);
						match(NEGATE);
						setState(287);
						match(ASSIGN);
						}
						break;
					case 3:
						{
						setState(288);
						match(GT);
						setState(289);
						match(ASSIGN);
						}
						break;
					case 4:
						{
						setState(290);
						match(LT);
						setState(291);
						match(ASSIGN);
						}
						break;
					case 5:
						{
						setState(292);
						match(GT);
						}
						break;
					case 6:
						{
						setState(293);
						match(LT);
						}
						break;
					}
					setState(296);
					lexpr();
					}
					} 
				}
				setState(301);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
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
	public static class LexprContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(LancerSpecParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(LancerSpecParser.PLUS, i);
		}
		public List<TerminalNode> DASH() { return getTokens(LancerSpecParser.DASH); }
		public TerminalNode DASH(int i) {
			return getToken(LancerSpecParser.DASH, i);
		}
		public LexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterLexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitLexpr(this);
		}
	}

	public final LexprContext lexpr() throws RecognitionException {
		LexprContext _localctx = new LexprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_lexpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			variable();
			setState(307);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(303);
					_la = _input.LA(1);
					if ( !(_la==DASH || _la==PLUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(304);
					variable();
					}
					} 
				}
				setState(309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
	public static class VariableContext extends ParserRuleContext {
		public CompIdentifierContext compIdentifier() {
			return getRuleContext(CompIdentifierContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(LancerSpecParser.STRING_LITERAL, 0); }
		public TerminalNode INT_LITERAL() { return getToken(LancerSpecParser.INT_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(LancerSpecParser.BOOL_LITERAL, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_variable);
		try {
			setState(314);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(310);
				compIdentifier();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(311);
				match(STRING_LITERAL);
				}
				break;
			case INT_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(312);
				match(INT_LITERAL);
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(313);
				match(BOOL_LITERAL);
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
		enterRule(_localctx, 54, RULE_ebnf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			block();
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6047313952768L) != 0)) {
				{
				setState(317);
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
		enterRule(_localctx, 56, RULE_ebnfSuffix);
		try {
			setState(325);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				match(QUESTION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(321);
				match(STAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(322);
				match(PLUS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(323);
				match(STAR);
				setState(324);
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
		enterRule(_localctx, 58, RULE_lexerAtom);
		try {
			setState(331);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(327);
				characterRange();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(328);
				terminal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(329);
				notSet();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(330);
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
		enterRule(_localctx, 60, RULE_atom);
		try {
			setState(337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(333);
				terminal();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				notSet();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(335);
				precedence();
				}
				break;
			case INT_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(336);
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
		public TerminalNode INT_LITERAL() { return getToken(LancerSpecParser.INT_LITERAL, 0); }
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
		enterRule(_localctx, 62, RULE_weightage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(INT_LITERAL);
			setState(340);
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
		public TerminalNode INT_LITERAL() { return getToken(LancerSpecParser.INT_LITERAL, 0); }
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
		enterRule(_localctx, 64, RULE_precedence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			match(AT);
			setState(343);
			match(INT_LITERAL);
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
		enterRule(_localctx, 66, RULE_notSet);
		try {
			setState(349);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(345);
				match(NOT);
				setState(346);
				setElement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(347);
				match(NOT);
				setState(348);
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
		enterRule(_localctx, 68, RULE_blockSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			match(LPAREN);
			setState(352);
			setElement();
			setState(357);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(353);
				match(OR);
				setState(354);
				setElement();
				}
				}
				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(360);
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
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
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
		enterRule(_localctx, 70, RULE_setElement);
		try {
			setState(366);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(363);
				match(STRING_LITERAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(364);
				characterRange();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(365);
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
		enterRule(_localctx, 72, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(LPAREN);
			setState(369);
			altList();
			setState(370);
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
		enterRule(_localctx, 74, RULE_characterRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			match(STRING_LITERAL);
			setState(373);
			match(RANGE);
			setState(374);
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
		enterRule(_localctx, 76, RULE_terminal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
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
		enterRule(_localctx, 78, RULE_compIdentifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			identifier();
			setState(380);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(379);
				argActionBlock();
				}
				break;
			}
			setState(386);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(382);
					match(DOT);
					setState(383);
					compIdentifier();
					}
					} 
				}
				setState(388);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
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
		enterRule(_localctx, 80, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
			_la = _input.LA(1);
			if ( !(_la==TOKEN_REF || _la==RULE_REF) ) {
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
		public LexerAltListContext lexerAltList() {
			return getRuleContext(LexerAltListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LancerSpecParser.SEMI, 0); }
		public RuleModifierContext ruleModifier() {
			return getRuleContext(RuleModifierContext.class,0);
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
		enterRule(_localctx, 82, RULE_lexerRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FRAGMENT) {
				{
				setState(391);
				ruleModifier();
				}
			}

			setState(394);
			match(TOKEN_REF);
			setState(395);
			match(COLON);
			setState(396);
			lexerAltList();
			setState(397);
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
		enterRule(_localctx, 84, RULE_lexerAltList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			lexerAlt();
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(400);
				match(OR);
				setState(401);
				lexerAlt();
				}
				}
				setState(406);
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
		public List<LexerElementContext> lexerElement() {
			return getRuleContexts(LexerElementContext.class);
		}
		public LexerElementContext lexerElement(int i) {
			return getRuleContext(LexerElementContext.class,i);
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
		enterRule(_localctx, 86, RULE_lexerAlt);
		int _la;
		try {
			setState(413);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case LEXER_CHAR_SET:
			case STRING_LITERAL:
			case BOOL_LITERAL:
			case INT_LITERAL:
			case BEGIN_ACTION:
			case LPAREN:
			case LBRACK:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(408); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(407);
					lexerElement();
					}
					}
					setState(410); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 562959080230542L) != 0) );
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
		enterRule(_localctx, 88, RULE_lexerElement);
		int _la;
		try {
			setState(429);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(415);
				actionBlock();
				setState(417);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUESTION) {
					{
					setState(416);
					match(QUESTION);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(419);
				expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(420);
				lexerAtom();
				setState(422);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6047313952768L) != 0)) {
					{
					setState(421);
					ebnfSuffix();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(424);
				lexerBlock();
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6047313952768L) != 0)) {
					{
					setState(425);
					ebnfSuffix();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(428);
				charSet();
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
		enterRule(_localctx, 90, RULE_lexerBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			match(LPAREN);
			setState(432);
			lexerAltList();
			setState(433);
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
		enterRule(_localctx, 92, RULE_charSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(LBRACK);
			setState(436);
			charSetContent();
			setState(437);
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
		enterRule(_localctx, 94, RULE_charSetContent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(441);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(439);
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
					setState(440);
					match(ESC);
					}
					break;
				}
				}
				setState(443); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 144115170895986686L) != 0) );
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
		enterRule(_localctx, 96, RULE_grammarOperator);
		try {
			setState(459);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(445);
				match(ASSIGN);
				setState(446);
				match(ASSIGN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(447);
				match(NEGATE);
				setState(448);
				match(ASSIGN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(449);
				match(PLUS);
				setState(450);
				match(ASSIGN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(451);
				match(PLUS);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(452);
				match(GT);
				setState(453);
				match(ASSIGN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(454);
				match(LT);
				setState(455);
				match(ASSIGN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(456);
				match(GT);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(457);
				match(LT);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(458);
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
		"\u0004\u00018\u01ce\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u0001\u0000\u0001\u0000"+
		"\u0005\u0000e\b\u0000\n\u0000\f\u0000h\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002u\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003{\b\u0003\n\u0003\f\u0003"+
		"~\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u0089\b\u0005"+
		"\n\u0005\f\u0005\u008c\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005\u0091\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005"+
		"\u0006\u0097\b\u0006\n\u0006\f\u0006\u009a\t\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u00a3\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0003\t"+
		"\u00ab\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0005\n\u00b1\b\n\n\n\f\n\u00b4"+
		"\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00bb"+
		"\b\u000b\n\u000b\f\u000b\u00be\t\u000b\u0001\u000b\u0003\u000b\u00c1\b"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00c7\b\f\n\f\f\f\u00ca"+
		"\t\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0005\u000e\u00d1\b\u000e"+
		"\n\u000e\f\u000e\u00d4\t\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u00d8"+
		"\b\u000f\u0001\u0010\u0003\u0010\u00db\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u00df\b\u0010\u0001\u0010\u0003\u0010\u00e2\b\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0005"+
		"\u0013\u00f0\b\u0013\n\u0013\f\u0013\u00f3\t\u0013\u0001\u0014\u0004\u0014"+
		"\u00f6\b\u0014\u000b\u0014\f\u0014\u00f7\u0001\u0014\u0003\u0014\u00fb"+
		"\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u0103\b\u0015\u0001\u0015\u0003\u0015\u0106\b\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0003\u0016\u010f\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u0114\b\u0017\u0001\u0017\u0005\u0017\u0117\b\u0017\n\u0017"+
		"\f\u0017\u011a\t\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0003\u0018\u0127\b\u0018\u0001\u0018\u0005\u0018\u012a\b"+
		"\u0018\n\u0018\f\u0018\u012d\t\u0018\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0005\u0019\u0132\b\u0019\n\u0019\f\u0019\u0135\t\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u013b\b\u001a\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u013f\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0003\u001c\u0146\b\u001c\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0003\u001d\u014c\b\u001d\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u0152\b\u001e\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0003!\u015e\b"+
		"!\u0001\"\u0001\"\u0001\"\u0001\"\u0005\"\u0164\b\"\n\"\f\"\u0167\t\""+
		"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0003#\u016f\b#\u0001$\u0001"+
		"$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001"+
		"\'\u0003\'\u017d\b\'\u0001\'\u0001\'\u0005\'\u0181\b\'\n\'\f\'\u0184\t"+
		"\'\u0001(\u0001(\u0001)\u0003)\u0189\b)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001*\u0001*\u0001*\u0005*\u0193\b*\n*\f*\u0196\t*\u0001+\u0004+\u0199"+
		"\b+\u000b+\f+\u019a\u0001+\u0003+\u019e\b+\u0001,\u0001,\u0003,\u01a2"+
		"\b,\u0001,\u0001,\u0001,\u0003,\u01a7\b,\u0001,\u0001,\u0003,\u01ab\b"+
		",\u0001,\u0003,\u01ae\b,\u0001-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001"+
		".\u0001.\u0001/\u0001/\u0004/\u01ba\b/\u000b/\f/\u01bb\u00010\u00010\u0001"+
		"0\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u0001"+
		"0\u00010\u00030\u01cc\b0\u00010\u0000\u00001\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02"+
		"468:<>@BDFHJLNPRTVXZ\\^`\u0000\u0004\u0002\u0000&&))\u0002\u0000\f\f*"+
		"*\u0001\u0000\u0001\u0002\u0001\u0000\"\"\u01ed\u0000b\u0001\u0000\u0000"+
		"\u0000\u0002l\u0001\u0000\u0000\u0000\u0004t\u0001\u0000\u0000\u0000\u0006"+
		"v\u0001\u0000\u0000\u0000\b\u0081\u0001\u0000\u0000\u0000\n\u0090\u0001"+
		"\u0000\u0000\u0000\f\u0092\u0001\u0000\u0000\u0000\u000e\u00a2\u0001\u0000"+
		"\u0000\u0000\u0010\u00a4\u0001\u0000\u0000\u0000\u0012\u00a8\u0001\u0000"+
		"\u0000\u0000\u0014\u00ae\u0001\u0000\u0000\u0000\u0016\u00b7\u0001\u0000"+
		"\u0000\u0000\u0018\u00c2\u0001\u0000\u0000\u0000\u001a\u00cd\u0001\u0000"+
		"\u0000\u0000\u001c\u00d2\u0001\u0000\u0000\u0000\u001e\u00d7\u0001\u0000"+
		"\u0000\u0000 \u00da\u0001\u0000\u0000\u0000\"\u00e7\u0001\u0000\u0000"+
		"\u0000$\u00ea\u0001\u0000\u0000\u0000&\u00ec\u0001\u0000\u0000\u0000("+
		"\u00fa\u0001\u0000\u0000\u0000*\u0105\u0001\u0000\u0000\u0000,\u010e\u0001"+
		"\u0000\u0000\u0000.\u0110\u0001\u0000\u0000\u00000\u011b\u0001\u0000\u0000"+
		"\u00002\u012e\u0001\u0000\u0000\u00004\u013a\u0001\u0000\u0000\u00006"+
		"\u013c\u0001\u0000\u0000\u00008\u0145\u0001\u0000\u0000\u0000:\u014b\u0001"+
		"\u0000\u0000\u0000<\u0151\u0001\u0000\u0000\u0000>\u0153\u0001\u0000\u0000"+
		"\u0000@\u0156\u0001\u0000\u0000\u0000B\u015d\u0001\u0000\u0000\u0000D"+
		"\u015f\u0001\u0000\u0000\u0000F\u016e\u0001\u0000\u0000\u0000H\u0170\u0001"+
		"\u0000\u0000\u0000J\u0174\u0001\u0000\u0000\u0000L\u0178\u0001\u0000\u0000"+
		"\u0000N\u017a\u0001\u0000\u0000\u0000P\u0185\u0001\u0000\u0000\u0000R"+
		"\u0188\u0001\u0000\u0000\u0000T\u018f\u0001\u0000\u0000\u0000V\u019d\u0001"+
		"\u0000\u0000\u0000X\u01ad\u0001\u0000\u0000\u0000Z\u01af\u0001\u0000\u0000"+
		"\u0000\\\u01b3\u0001\u0000\u0000\u0000^\u01b9\u0001\u0000\u0000\u0000"+
		"`\u01cb\u0001\u0000\u0000\u0000bf\u0003\u0002\u0001\u0000ce\u0003\u0004"+
		"\u0002\u0000dc\u0001\u0000\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001"+
		"\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gi\u0001\u0000\u0000\u0000"+
		"hf\u0001\u0000\u0000\u0000ij\u0003\u001c\u000e\u0000jk\u0005\u0000\u0000"+
		"\u0001k\u0001\u0001\u0000\u0000\u0000lm\u0005\u0017\u0000\u0000mn\u0003"+
		"P(\u0000no\u0005\u001c\u0000\u0000o\u0003\u0001\u0000\u0000\u0000pu\u0003"+
		"\u0006\u0003\u0000qu\u0003\f\u0006\u0000ru\u0003\u0010\b\u0000su\u0003"+
		"\u0012\t\u0000tp\u0001\u0000\u0000\u0000tq\u0001\u0000\u0000\u0000tr\u0001"+
		"\u0000\u0000\u0000ts\u0001\u0000\u0000\u0000u\u0005\u0001\u0000\u0000"+
		"\u0000v|\u0005\u000e\u0000\u0000wx\u0003\b\u0004\u0000xy\u0005\u001c\u0000"+
		"\u0000y{\u0001\u0000\u0000\u0000zw\u0001\u0000\u0000\u0000{~\u0001\u0000"+
		"\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u007f"+
		"\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000\u007f\u0080\u0005 "+
		"\u0000\u0000\u0080\u0007\u0001\u0000\u0000\u0000\u0081\u0082\u0003P(\u0000"+
		"\u0082\u0083\u0005&\u0000\u0000\u0083\u0084\u0003\n\u0005\u0000\u0084"+
		"\t\u0001\u0000\u0000\u0000\u0085\u008a\u0003P(\u0000\u0086\u0087\u0005"+
		".\u0000\u0000\u0087\u0089\u0003P(\u0000\u0088\u0086\u0001\u0000\u0000"+
		"\u0000\u0089\u008c\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000"+
		"\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u0091\u0001\u0000\u0000"+
		"\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008d\u0091\u0005\u0007\u0000"+
		"\u0000\u008e\u0091\u0003\u0014\n\u0000\u008f\u0091\u0005\n\u0000\u0000"+
		"\u0090\u0085\u0001\u0000\u0000\u0000\u0090\u008d\u0001\u0000\u0000\u0000"+
		"\u0090\u008e\u0001\u0000\u0000\u0000\u0090\u008f\u0001\u0000\u0000\u0000"+
		"\u0091\u000b\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u0013\u0000\u0000"+
		"\u0093\u0098\u0003\u000e\u0007\u0000\u0094\u0095\u0005\u001b\u0000\u0000"+
		"\u0095\u0097\u0003\u000e\u0007\u0000\u0096\u0094\u0001\u0000\u0000\u0000"+
		"\u0097\u009a\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000"+
		"\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u009b\u0001\u0000\u0000\u0000"+
		"\u009a\u0098\u0001\u0000\u0000\u0000\u009b\u009c\u0005\u001c\u0000\u0000"+
		"\u009c\r\u0001\u0000\u0000\u0000\u009d\u009e\u0003P(\u0000\u009e\u009f"+
		"\u0005&\u0000\u0000\u009f\u00a0\u0003P(\u0000\u00a0\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a3\u0003P(\u0000\u00a2\u009d\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a1\u0001\u0000\u0000\u0000\u00a3\u000f\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a5\u0005/\u0000\u0000\u00a5\u00a6\u0003P(\u0000\u00a6\u00a7"+
		"\u0003\u0014\n\u0000\u00a7\u0011\u0001\u0000\u0000\u0000\u00a8\u00aa\u0005"+
		"\u000f\u0000\u0000\u00a9\u00ab\u0003\u0016\u000b\u0000\u00aa\u00a9\u0001"+
		"\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001"+
		"\u0000\u0000\u0000\u00ac\u00ad\u0005 \u0000\u0000\u00ad\u0013\u0001\u0000"+
		"\u0000\u0000\u00ae\u00b2\u0005\u000b\u0000\u0000\u00af\u00b1\u00057\u0000"+
		"\u0000\u00b0\u00af\u0001\u0000\u0000\u0000\u00b1\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u00055\u0000\u0000\u00b6\u0015\u0001\u0000\u0000\u0000"+
		"\u00b7\u00bc\u0003P(\u0000\u00b8\u00b9\u0005\u001b\u0000\u0000\u00b9\u00bb"+
		"\u0003P(\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb\u00be\u0001\u0000"+
		"\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000"+
		"\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000"+
		"\u0000\u0000\u00bf\u00c1\u0005\u001b\u0000\u0000\u00c0\u00bf\u0001\u0000"+
		"\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u0017\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c3\u0005!\u0000\u0000\u00c3\u00c8\u0003\u001a\r"+
		"\u0000\u00c4\u00c5\u0005\u001b\u0000\u0000\u00c5\u00c7\u0003\u001a\r\u0000"+
		"\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00ca\u0001\u0000\u0000\u0000"+
		"\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000"+
		"\u00c9\u00cb\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000"+
		"\u00cb\u00cc\u0005\"\u0000\u0000\u00cc\u0019\u0001\u0000\u0000\u0000\u00cd"+
		"\u00ce\u0003.\u0017\u0000\u00ce\u001b\u0001\u0000\u0000\u0000\u00cf\u00d1"+
		"\u0003\u001e\u000f\u0000\u00d0\u00cf\u0001\u0000\u0000\u0000\u00d1\u00d4"+
		"\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d3"+
		"\u0001\u0000\u0000\u0000\u00d3\u001d\u0001\u0000\u0000\u0000\u00d4\u00d2"+
		"\u0001\u0000\u0000\u0000\u00d5\u00d8\u0003 \u0010\u0000\u00d6\u00d8\u0003"+
		"R)\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d7\u00d6\u0001\u0000\u0000"+
		"\u0000\u00d8\u001f\u0001\u0000\u0000\u0000\u00d9\u00db\u0003$\u0012\u0000"+
		"\u00da\u00d9\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000"+
		"\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00de\u0005\u0002\u0000\u0000"+
		"\u00dd\u00df\u0003\u0018\f\u0000\u00de\u00dd\u0001\u0000\u0000\u0000\u00de"+
		"\u00df\u0001\u0000\u0000\u0000\u00df\u00e1\u0001\u0000\u0000\u0000\u00e0"+
		"\u00e2\u0003\"\u0011\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e1\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4"+
		"\u0005\u0019\u0000\u0000\u00e4\u00e5\u0003&\u0013\u0000\u00e5\u00e6\u0005"+
		"\u001c\u0000\u0000\u00e6!\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005\u0018"+
		"\u0000\u0000\u00e8\u00e9\u0003\u0018\f\u0000\u00e9#\u0001\u0000\u0000"+
		"\u0000\u00ea\u00eb\u0005\u0014\u0000\u0000\u00eb%\u0001\u0000\u0000\u0000"+
		"\u00ec\u00f1\u0003(\u0014\u0000\u00ed\u00ee\u0005+\u0000\u0000\u00ee\u00f0"+
		"\u0003(\u0014\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00f0\u00f3\u0001"+
		"\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f2\'\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f6\u0003*\u0015\u0000\u00f5\u00f4\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8\u00fb\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fb\u0001\u0000\u0000\u0000\u00fa\u00f5\u0001\u0000\u0000"+
		"\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fb)\u0001\u0000\u0000\u0000"+
		"\u00fc\u0106\u0003\u0014\n\u0000\u00fd\u0106\u0003,\u0016\u0000\u00fe"+
		"\u0106\u0003>\u001f\u0000\u00ff\u0106\u0003@ \u0000\u0100\u0102\u0003"+
		"\u001a\r\u0000\u0101\u0103\u00038\u001c\u0000\u0102\u0101\u0001\u0000"+
		"\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103\u0106\u0001\u0000"+
		"\u0000\u0000\u0104\u0106\u00036\u001b\u0000\u0105\u00fc\u0001\u0000\u0000"+
		"\u0000\u0105\u00fd\u0001\u0000\u0000\u0000\u0105\u00fe\u0001\u0000\u0000"+
		"\u0000\u0105\u00ff\u0001\u0000\u0000\u0000\u0105\u0100\u0001\u0000\u0000"+
		"\u0000\u0105\u0104\u0001\u0000\u0000\u0000\u0106+\u0001\u0000\u0000\u0000"+
		"\u0107\u0108\u0005$\u0000\u0000\u0108\u0109\u0003\u001a\r\u0000\u0109"+
		"\u010a\u0005%\u0000\u0000\u010a\u010f\u0001\u0000\u0000\u0000\u010b\u010c"+
		"\u0003\u0014\n\u0000\u010c\u010d\u0005\'\u0000\u0000\u010d\u010f\u0001"+
		"\u0000\u0000\u0000\u010e\u0107\u0001\u0000\u0000\u0000\u010e\u010b\u0001"+
		"\u0000\u0000\u0000\u010f-\u0001\u0000\u0000\u0000\u0110\u0118\u00030\u0018"+
		"\u0000\u0111\u0113\u0007\u0000\u0000\u0000\u0112\u0114\u0005,\u0000\u0000"+
		"\u0113\u0112\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000"+
		"\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0117\u00030\u0018\u0000\u0116"+
		"\u0111\u0001\u0000\u0000\u0000\u0117\u011a\u0001\u0000\u0000\u0000\u0118"+
		"\u0116\u0001\u0000\u0000\u0000\u0118\u0119\u0001\u0000\u0000\u0000\u0119"+
		"/\u0001\u0000\u0000\u0000\u011a\u0118\u0001\u0000\u0000\u0000\u011b\u012b"+
		"\u00032\u0019\u0000\u011c\u011d\u0005&\u0000\u0000\u011d\u0127\u0005&"+
		"\u0000\u0000\u011e\u011f\u0005\u0012\u0000\u0000\u011f\u0127\u0005&\u0000"+
		"\u0000\u0120\u0121\u0005%\u0000\u0000\u0121\u0127\u0005&\u0000\u0000\u0122"+
		"\u0123\u0005$\u0000\u0000\u0123\u0127\u0005&\u0000\u0000\u0124\u0127\u0005"+
		"%\u0000\u0000\u0125\u0127\u0005$\u0000\u0000\u0126\u011c\u0001\u0000\u0000"+
		"\u0000\u0126\u011e\u0001\u0000\u0000\u0000\u0126\u0120\u0001\u0000\u0000"+
		"\u0000\u0126\u0122\u0001\u0000\u0000\u0000\u0126\u0124\u0001\u0000\u0000"+
		"\u0000\u0126\u0125\u0001\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000"+
		"\u0000\u0128\u012a\u00032\u0019\u0000\u0129\u0126\u0001\u0000\u0000\u0000"+
		"\u012a\u012d\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000\u0000"+
		"\u012b\u012c\u0001\u0000\u0000\u0000\u012c1\u0001\u0000\u0000\u0000\u012d"+
		"\u012b\u0001\u0000\u0000\u0000\u012e\u0133\u00034\u001a\u0000\u012f\u0130"+
		"\u0007\u0001\u0000\u0000\u0130\u0132\u00034\u001a\u0000\u0131\u012f\u0001"+
		"\u0000\u0000\u0000\u0132\u0135\u0001\u0000\u0000\u0000\u0133\u0131\u0001"+
		"\u0000\u0000\u0000\u0133\u0134\u0001\u0000\u0000\u0000\u01343\u0001\u0000"+
		"\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0136\u013b\u0003N\'"+
		"\u0000\u0137\u013b\u0005\u0007\u0000\u0000\u0138\u013b\u0005\n\u0000\u0000"+
		"\u0139\u013b\u0005\t\u0000\u0000\u013a\u0136\u0001\u0000\u0000\u0000\u013a"+
		"\u0137\u0001\u0000\u0000\u0000\u013a\u0138\u0001\u0000\u0000\u0000\u013a"+
		"\u0139\u0001\u0000\u0000\u0000\u013b5\u0001\u0000\u0000\u0000\u013c\u013e"+
		"\u0003H$\u0000\u013d\u013f\u00038\u001c\u0000\u013e\u013d\u0001\u0000"+
		"\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f7\u0001\u0000\u0000"+
		"\u0000\u0140\u0146\u0005\'\u0000\u0000\u0141\u0146\u0005(\u0000\u0000"+
		"\u0142\u0146\u0005*\u0000\u0000\u0143\u0144\u0005(\u0000\u0000\u0144\u0146"+
		"\u0005(\u0000\u0000\u0145\u0140\u0001\u0000\u0000\u0000\u0145\u0141\u0001"+
		"\u0000\u0000\u0000\u0145\u0142\u0001\u0000\u0000\u0000\u0145\u0143\u0001"+
		"\u0000\u0000\u0000\u01469\u0001\u0000\u0000\u0000\u0147\u014c\u0003J%"+
		"\u0000\u0148\u014c\u0003L&\u0000\u0149\u014c\u0003B!\u0000\u014a\u014c"+
		"\u0005\u0003\u0000\u0000\u014b\u0147\u0001\u0000\u0000\u0000\u014b\u0148"+
		"\u0001\u0000\u0000\u0000\u014b\u0149\u0001\u0000\u0000\u0000\u014b\u014a"+
		"\u0001\u0000\u0000\u0000\u014c;\u0001\u0000\u0000\u0000\u014d\u0152\u0003"+
		"L&\u0000\u014e\u0152\u0003B!\u0000\u014f\u0152\u0003@ \u0000\u0150\u0152"+
		"\u0003>\u001f\u0000\u0151\u014d\u0001\u0000\u0000\u0000\u0151\u014e\u0001"+
		"\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0151\u0150\u0001"+
		"\u0000\u0000\u0000\u0152=\u0001\u0000\u0000\u0000\u0153\u0154\u0005\n"+
		"\u0000\u0000\u0154\u0155\u0005\u0011\u0000\u0000\u0155?\u0001\u0000\u0000"+
		"\u0000\u0156\u0157\u0005/\u0000\u0000\u0157\u0158\u0005\n\u0000\u0000"+
		"\u0158A\u0001\u0000\u0000\u0000\u0159\u015a\u00051\u0000\u0000\u015a\u015e"+
		"\u0003F#\u0000\u015b\u015c\u00051\u0000\u0000\u015c\u015e\u0003D\"\u0000"+
		"\u015d\u0159\u0001\u0000\u0000\u0000\u015d\u015b\u0001\u0000\u0000\u0000"+
		"\u015eC\u0001\u0000\u0000\u0000\u015f\u0160\u0005\u001d\u0000\u0000\u0160"+
		"\u0165\u0003F#\u0000\u0161\u0162\u0005+\u0000\u0000\u0162\u0164\u0003"+
		"F#\u0000\u0163\u0161\u0001\u0000\u0000\u0000\u0164\u0167\u0001\u0000\u0000"+
		"\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000\u0000"+
		"\u0000\u0166\u0168\u0001\u0000\u0000\u0000\u0167\u0165\u0001\u0000\u0000"+
		"\u0000\u0168\u0169\u0005\u001e\u0000\u0000\u0169E\u0001\u0000\u0000\u0000"+
		"\u016a\u016f\u0003P(\u0000\u016b\u016f\u0005\u0007\u0000\u0000\u016c\u016f"+
		"\u0003J%\u0000\u016d\u016f\u0005\u0003\u0000\u0000\u016e\u016a\u0001\u0000"+
		"\u0000\u0000\u016e\u016b\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000"+
		"\u0000\u0000\u016e\u016d\u0001\u0000\u0000\u0000\u016fG\u0001\u0000\u0000"+
		"\u0000\u0170\u0171\u0005\u001d\u0000\u0000\u0171\u0172\u0003&\u0013\u0000"+
		"\u0172\u0173\u0005\u001e\u0000\u0000\u0173I\u0001\u0000\u0000\u0000\u0174"+
		"\u0175\u0005\u0007\u0000\u0000\u0175\u0176\u0005-\u0000\u0000\u0176\u0177"+
		"\u0005\u0007\u0000\u0000\u0177K\u0001\u0000\u0000\u0000\u0178\u0179\u0005"+
		"\u0007\u0000\u0000\u0179M\u0001\u0000\u0000\u0000\u017a\u017c\u0003P("+
		"\u0000\u017b\u017d\u0003\u0018\f\u0000\u017c\u017b\u0001\u0000\u0000\u0000"+
		"\u017c\u017d\u0001\u0000\u0000\u0000\u017d\u0182\u0001\u0000\u0000\u0000"+
		"\u017e\u017f\u0005.\u0000\u0000\u017f\u0181\u0003N\'\u0000\u0180\u017e"+
		"\u0001\u0000\u0000\u0000\u0181\u0184\u0001\u0000\u0000\u0000\u0182\u0180"+
		"\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000\u0000\u0183O\u0001"+
		"\u0000\u0000\u0000\u0184\u0182\u0001\u0000\u0000\u0000\u0185\u0186\u0007"+
		"\u0002\u0000\u0000\u0186Q\u0001\u0000\u0000\u0000\u0187\u0189\u0003$\u0012"+
		"\u0000\u0188\u0187\u0001\u0000\u0000\u0000\u0188\u0189\u0001\u0000\u0000"+
		"\u0000\u0189\u018a\u0001\u0000\u0000\u0000\u018a\u018b\u0005\u0001\u0000"+
		"\u0000\u018b\u018c\u0005\u0019\u0000\u0000\u018c\u018d\u0003T*\u0000\u018d"+
		"\u018e\u0005\u001c\u0000\u0000\u018eS\u0001\u0000\u0000\u0000\u018f\u0194"+
		"\u0003V+\u0000\u0190\u0191\u0005+\u0000\u0000\u0191\u0193\u0003V+\u0000"+
		"\u0192\u0190\u0001\u0000\u0000\u0000\u0193\u0196\u0001\u0000\u0000\u0000"+
		"\u0194\u0192\u0001\u0000\u0000\u0000\u0194\u0195\u0001\u0000\u0000\u0000"+
		"\u0195U\u0001\u0000\u0000\u0000\u0196\u0194\u0001\u0000\u0000\u0000\u0197"+
		"\u0199\u0003X,\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0199\u019a\u0001"+
		"\u0000\u0000\u0000\u019a\u0198\u0001\u0000\u0000\u0000\u019a\u019b\u0001"+
		"\u0000\u0000\u0000\u019b\u019e\u0001\u0000\u0000\u0000\u019c\u019e\u0001"+
		"\u0000\u0000\u0000\u019d\u0198\u0001\u0000\u0000\u0000\u019d\u019c\u0001"+
		"\u0000\u0000\u0000\u019eW\u0001\u0000\u0000\u0000\u019f\u01a1\u0003\u0014"+
		"\n\u0000\u01a0\u01a2\u0005\'\u0000\u0000\u01a1\u01a0\u0001\u0000\u0000"+
		"\u0000\u01a1\u01a2\u0001\u0000\u0000\u0000\u01a2\u01ae\u0001\u0000\u0000"+
		"\u0000\u01a3\u01ae\u0003.\u0017\u0000\u01a4\u01a6\u0003:\u001d\u0000\u01a5"+
		"\u01a7\u00038\u001c\u0000\u01a6\u01a5\u0001\u0000\u0000\u0000\u01a6\u01a7"+
		"\u0001\u0000\u0000\u0000\u01a7\u01ae\u0001\u0000\u0000\u0000\u01a8\u01aa"+
		"\u0003Z-\u0000\u01a9\u01ab\u00038\u001c\u0000\u01aa\u01a9\u0001\u0000"+
		"\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ae\u0001\u0000"+
		"\u0000\u0000\u01ac\u01ae\u0003\\.\u0000\u01ad\u019f\u0001\u0000\u0000"+
		"\u0000\u01ad\u01a3\u0001\u0000\u0000\u0000\u01ad\u01a4\u0001\u0000\u0000"+
		"\u0000\u01ad\u01a8\u0001\u0000\u0000\u0000\u01ad\u01ac\u0001\u0000\u0000"+
		"\u0000\u01aeY\u0001\u0000\u0000\u0000\u01af\u01b0\u0005\u001d\u0000\u0000"+
		"\u01b0\u01b1\u0003T*\u0000\u01b1\u01b2\u0005\u001e\u0000\u0000\u01b2["+
		"\u0001\u0000\u0000\u0000\u01b3\u01b4\u0005!\u0000\u0000\u01b4\u01b5\u0003"+
		"^/\u0000\u01b5\u01b6\u0005\"\u0000\u0000\u01b6]\u0001\u0000\u0000\u0000"+
		"\u01b7\u01ba\b\u0003\u0000\u0000\u01b8\u01ba\u0005\r\u0000\u0000\u01b9"+
		"\u01b7\u0001\u0000\u0000\u0000\u01b9\u01b8\u0001\u0000\u0000\u0000\u01ba"+
		"\u01bb\u0001\u0000\u0000\u0000\u01bb\u01b9\u0001\u0000\u0000\u0000\u01bb"+
		"\u01bc\u0001\u0000\u0000\u0000\u01bc_\u0001\u0000\u0000\u0000\u01bd\u01be"+
		"\u0005&\u0000\u0000\u01be\u01cc\u0005&\u0000\u0000\u01bf\u01c0\u0005\u0012"+
		"\u0000\u0000\u01c0\u01cc\u0005&\u0000\u0000\u01c1\u01c2\u0005*\u0000\u0000"+
		"\u01c2\u01cc\u0005&\u0000\u0000\u01c3\u01cc\u0005*\u0000\u0000\u01c4\u01c5"+
		"\u0005%\u0000\u0000\u01c5\u01cc\u0005&\u0000\u0000\u01c6\u01c7\u0005$"+
		"\u0000\u0000\u01c7\u01cc\u0005&\u0000\u0000\u01c8\u01cc\u0005%\u0000\u0000"+
		"\u01c9\u01cc\u0005$\u0000\u0000\u01ca\u01cc\u0005&\u0000\u0000\u01cb\u01bd"+
		"\u0001\u0000\u0000\u0000\u01cb\u01bf\u0001\u0000\u0000\u0000\u01cb\u01c1"+
		"\u0001\u0000\u0000\u0000\u01cb\u01c3\u0001\u0000\u0000\u0000\u01cb\u01c4"+
		"\u0001\u0000\u0000\u0000\u01cb\u01c6\u0001\u0000\u0000\u0000\u01cb\u01c8"+
		"\u0001\u0000\u0000\u0000\u01cb\u01c9\u0001\u0000\u0000\u0000\u01cb\u01ca"+
		"\u0001\u0000\u0000\u0000\u01cca\u0001\u0000\u0000\u00001ft|\u008a\u0090"+
		"\u0098\u00a2\u00aa\u00b2\u00bc\u00c0\u00c8\u00d2\u00d7\u00da\u00de\u00e1"+
		"\u00f1\u00f7\u00fa\u0102\u0105\u010e\u0113\u0118\u0126\u012b\u0133\u013a"+
		"\u013e\u0145\u014b\u0151\u015d\u0165\u016e\u017c\u0182\u0188\u0194\u019a"+
		"\u019d\u01a1\u01a6\u01aa\u01ad\u01b9\u01bb\u01cb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}