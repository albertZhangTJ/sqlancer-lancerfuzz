package SGL.parser;
// Generated from SGLParser.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SGLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TOKEN_REF=1, RULE_REF=2, LEXER_CHAR_SET=3, DOC_COMMENT=4, BLOCK_COMMENT=5, 
		LINE_COMMENT=6, STRING_LITERAL=7, UNTERMINATED_STRING_LITERAL=8, BOOL_LITERAL=9, 
		INT_LITERAL=10, BOOL_OP=11, BEGIN_ACTION=12, DASH=13, ESC=14, OPTIONS=15, 
		TOKENS=16, CHANNELS=17, PERCENTAGE=18, NEGATE=19, IMPORT=20, FRAGMENT=21, 
		LEXER=22, PARSER=23, GRAMMAR=24, RETURNS=25, COLON=26, COLONCOLON=27, 
		COMMA=28, SEMI=29, LPAREN=30, RPAREN=31, LBRACE=32, RBRACE=33, LBRACK=34, 
		RBRACK=35, RARROW=36, LT=37, GT=38, ASSIGN=39, QUESTION=40, STAR=41, PLUS_ASSIGN=42, 
		PLUS=43, OR=44, DOLLAR=45, RANGE=46, DOT=47, AT=48, POUND=49, NOT=50, 
		ID=51, WS=52, ERRCHAR=53, END_ACTION=54, UNTERMINATED_ACTION=55, ACTION_CONTENT=56, 
		UNTERMINATED_CHAR_SET=57;
	public static final int
		RULE_grammarSpec = 0, RULE_grammarDecl = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_action_ = 8, RULE_tokensSpec = 9, RULE_actionBlock = 10, 
		RULE_idList = 11, RULE_argActionBlock = 12, RULE_arg = 13, RULE_rules = 14, 
		RULE_ruleSpec = 15, RULE_parserRuleSpec = 16, RULE_ruleReturns = 17, RULE_ruleModifier = 18, 
		RULE_altList = 19, RULE_alternative = 20, RULE_element = 21, RULE_predicate = 22, 
		RULE_expression = 23, RULE_expr_op = 24, RULE_mexpr = 25, RULE_mexpr_op = 26, 
		RULE_bexpr = 27, RULE_lexpr = 28, RULE_lexpr_op = 29, RULE_variable = 30, 
		RULE_ebnf = 31, RULE_ebnfSuffix = 32, RULE_lexerAtom = 33, RULE_weightage = 34, 
		RULE_precedence = 35, RULE_blockSet = 36, RULE_setElement = 37, RULE_block = 38, 
		RULE_characterRange = 39, RULE_terminal = 40, RULE_compIdentifier = 41, 
		RULE_identifier = 42, RULE_lexerRuleSpec = 43, RULE_lexerAltList = 44, 
		RULE_lexerAlt = 45, RULE_lexerElement = 46, RULE_lexerBlock = 47, RULE_charSet = 48, 
		RULE_charSetContent = 49;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarSpec", "grammarDecl", "prequelConstruct", "optionsSpec", "option", 
			"optionValue", "delegateGrammars", "delegateGrammar", "action_", "tokensSpec", 
			"actionBlock", "idList", "argActionBlock", "arg", "rules", "ruleSpec", 
			"parserRuleSpec", "ruleReturns", "ruleModifier", "altList", "alternative", 
			"element", "predicate", "expression", "expr_op", "mexpr", "mexpr_op", 
			"bexpr", "lexpr", "lexpr_op", "variable", "ebnf", "ebnfSuffix", "lexerAtom", 
			"weightage", "precedence", "blockSet", "setElement", "block", "characterRange", 
			"terminal", "compIdentifier", "identifier", "lexerRuleSpec", "lexerAltList", 
			"lexerAlt", "lexerElement", "lexerBlock", "charSet", "charSetContent"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "'-'", null, null, null, null, "'%'", "'!'", "'import'", "'fragment'", 
			"'lexer'", "'parser'", "'grammar'", "'returns'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TOKEN_REF", "RULE_REF", "LEXER_CHAR_SET", "DOC_COMMENT", "BLOCK_COMMENT", 
			"LINE_COMMENT", "STRING_LITERAL", "UNTERMINATED_STRING_LITERAL", "BOOL_LITERAL", 
			"INT_LITERAL", "BOOL_OP", "BEGIN_ACTION", "DASH", "ESC", "OPTIONS", "TOKENS", 
			"CHANNELS", "PERCENTAGE", "NEGATE", "IMPORT", "FRAGMENT", "LEXER", "PARSER", 
			"GRAMMAR", "RETURNS", "COLON", "COLONCOLON", "COMMA", "SEMI", "LPAREN", 
			"RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "RARROW", "LT", "GT", 
			"ASSIGN", "QUESTION", "STAR", "PLUS_ASSIGN", "PLUS", "OR", "DOLLAR", 
			"RANGE", "DOT", "AT", "POUND", "NOT", "ID", "WS", "ERRCHAR", "END_ACTION", 
			"UNTERMINATED_ACTION", "ACTION_CONTENT", "UNTERMINATED_CHAR_SET"
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
	public String getGrammarFileName() { return "SGLParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SGLParser(TokenStream input) {
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
		public TerminalNode EOF() { return getToken(SGLParser.EOF, 0); }
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterGrammarSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitGrammarSpec(this);
		}
	}

	public final GrammarSpecContext grammarSpec() throws RecognitionException {
		GrammarSpecContext _localctx = new GrammarSpecContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_grammarSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			grammarDecl();
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 281474977857536L) != 0)) {
				{
				{
				setState(101);
				prequelConstruct();
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(107);
			rules();
			setState(108);
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
		public TerminalNode GRAMMAR() { return getToken(SGLParser.GRAMMAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(SGLParser.SEMI, 0); }
		public GrammarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterGrammarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitGrammarDecl(this);
		}
	}

	public final GrammarDeclContext grammarDecl() throws RecognitionException {
		GrammarDeclContext _localctx = new GrammarDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_grammarDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(GRAMMAR);
			setState(111);
			identifier();
			setState(112);
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterPrequelConstruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitPrequelConstruct(this);
		}
	}

	public final PrequelConstructContext prequelConstruct() throws RecognitionException {
		PrequelConstructContext _localctx = new PrequelConstructContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_prequelConstruct);
		try {
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPTIONS:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				optionsSpec();
				}
				break;
			case IMPORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				delegateGrammars();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				action_();
				}
				break;
			case TOKENS:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
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
		public TerminalNode OPTIONS() { return getToken(SGLParser.OPTIONS, 0); }
		public TerminalNode RBRACE() { return getToken(SGLParser.RBRACE, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(SGLParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(SGLParser.SEMI, i);
		}
		public OptionsSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionsSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterOptionsSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitOptionsSpec(this);
		}
	}

	public final OptionsSpecContext optionsSpec() throws RecognitionException {
		OptionsSpecContext _localctx = new OptionsSpecContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_optionsSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(OPTIONS);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TOKEN_REF || _la==RULE_REF) {
				{
				{
				setState(121);
				option();
				setState(122);
				match(SEMI);
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
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
		public TerminalNode ASSIGN() { return getToken(SGLParser.ASSIGN, 0); }
		public OptionValueContext optionValue() {
			return getRuleContext(OptionValueContext.class,0);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitOption(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			identifier();
			setState(132);
			match(ASSIGN);
			setState(133);
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
		public List<TerminalNode> DOT() { return getTokens(SGLParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(SGLParser.DOT, i);
		}
		public TerminalNode STRING_LITERAL() { return getToken(SGLParser.STRING_LITERAL, 0); }
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public TerminalNode INT_LITERAL() { return getToken(SGLParser.INT_LITERAL, 0); }
		public OptionValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterOptionValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitOptionValue(this);
		}
	}

	public final OptionValueContext optionValue() throws RecognitionException {
		OptionValueContext _localctx = new OptionValueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_optionValue);
		int _la;
		try {
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				identifier();
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(136);
					match(DOT);
					setState(137);
					identifier();
					}
					}
					setState(142);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				match(STRING_LITERAL);
				}
				break;
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(144);
				actionBlock();
				}
				break;
			case INT_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(145);
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
		public TerminalNode IMPORT() { return getToken(SGLParser.IMPORT, 0); }
		public List<DelegateGrammarContext> delegateGrammar() {
			return getRuleContexts(DelegateGrammarContext.class);
		}
		public DelegateGrammarContext delegateGrammar(int i) {
			return getRuleContext(DelegateGrammarContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(SGLParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SGLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SGLParser.COMMA, i);
		}
		public DelegateGrammarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delegateGrammars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterDelegateGrammars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitDelegateGrammars(this);
		}
	}

	public final DelegateGrammarsContext delegateGrammars() throws RecognitionException {
		DelegateGrammarsContext _localctx = new DelegateGrammarsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_delegateGrammars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(IMPORT);
			setState(149);
			delegateGrammar();
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(150);
				match(COMMA);
				setState(151);
				delegateGrammar();
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(157);
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
		public TerminalNode ASSIGN() { return getToken(SGLParser.ASSIGN, 0); }
		public DelegateGrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delegateGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterDelegateGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitDelegateGrammar(this);
		}
	}

	public final DelegateGrammarContext delegateGrammar() throws RecognitionException {
		DelegateGrammarContext _localctx = new DelegateGrammarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_delegateGrammar);
		try {
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				identifier();
				setState(160);
				match(ASSIGN);
				setState(161);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
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
		public TerminalNode AT() { return getToken(SGLParser.AT, 0); }
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterAction_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitAction_(this);
		}
	}

	public final Action_Context action_() throws RecognitionException {
		Action_Context _localctx = new Action_Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_action_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(AT);
			setState(167);
			identifier();
			setState(168);
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
		public TerminalNode TOKENS() { return getToken(SGLParser.TOKENS, 0); }
		public TerminalNode RBRACE() { return getToken(SGLParser.RBRACE, 0); }
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public TokensSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tokensSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterTokensSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitTokensSpec(this);
		}
	}

	public final TokensSpecContext tokensSpec() throws RecognitionException {
		TokensSpecContext _localctx = new TokensSpecContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tokensSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(TOKENS);
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TOKEN_REF || _la==RULE_REF) {
				{
				setState(171);
				idList();
				}
			}

			setState(174);
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
		public TerminalNode BEGIN_ACTION() { return getToken(SGLParser.BEGIN_ACTION, 0); }
		public TerminalNode END_ACTION() { return getToken(SGLParser.END_ACTION, 0); }
		public List<TerminalNode> ACTION_CONTENT() { return getTokens(SGLParser.ACTION_CONTENT); }
		public TerminalNode ACTION_CONTENT(int i) {
			return getToken(SGLParser.ACTION_CONTENT, i);
		}
		public ActionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterActionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitActionBlock(this);
		}
	}

	public final ActionBlockContext actionBlock() throws RecognitionException {
		ActionBlockContext _localctx = new ActionBlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_actionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(BEGIN_ACTION);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ACTION_CONTENT) {
				{
				{
				setState(177);
				match(ACTION_CONTENT);
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(183);
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
		public List<TerminalNode> COMMA() { return getTokens(SGLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SGLParser.COMMA, i);
		}
		public IdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterIdList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitIdList(this);
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
			setState(185);
			identifier();
			setState(190);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(186);
					match(COMMA);
					setState(187);
					identifier();
					}
					} 
				}
				setState(192);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(193);
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
		public TerminalNode LBRACK() { return getToken(SGLParser.LBRACK, 0); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public TerminalNode RBRACK() { return getToken(SGLParser.RBRACK, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SGLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SGLParser.COMMA, i);
		}
		public ArgActionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argActionBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterArgActionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitArgActionBlock(this);
		}
	}

	public final ArgActionBlockContext argActionBlock() throws RecognitionException {
		ArgActionBlockContext _localctx = new ArgActionBlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_argActionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(LBRACK);
			setState(197);
			arg();
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(198);
				match(COMMA);
				setState(199);
				arg();
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(205);
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitRules(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2097158L) != 0)) {
				{
				{
				setState(209);
				ruleSpec();
				}
				}
				setState(214);
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterRuleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitRuleSpec(this);
		}
	}

	public final RuleSpecContext ruleSpec() throws RecognitionException {
		RuleSpecContext _localctx = new RuleSpecContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ruleSpec);
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				parserRuleSpec();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
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
		public TerminalNode RULE_REF() { return getToken(SGLParser.RULE_REF, 0); }
		public TerminalNode COLON() { return getToken(SGLParser.COLON, 0); }
		public AltListContext altList() {
			return getRuleContext(AltListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(SGLParser.SEMI, 0); }
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterParserRuleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitParserRuleSpec(this);
		}
	}

	public final ParserRuleSpecContext parserRuleSpec() throws RecognitionException {
		ParserRuleSpecContext _localctx = new ParserRuleSpecContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_parserRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FRAGMENT) {
				{
				setState(219);
				ruleModifier();
				}
			}

			setState(222);
			match(RULE_REF);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(223);
				argActionBlock();
				}
			}

			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(226);
				ruleReturns();
				}
			}

			setState(229);
			match(COLON);
			setState(230);
			altList();
			setState(231);
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
		public TerminalNode RETURNS() { return getToken(SGLParser.RETURNS, 0); }
		public ArgActionBlockContext argActionBlock() {
			return getRuleContext(ArgActionBlockContext.class,0);
		}
		public RuleReturnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleReturns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterRuleReturns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitRuleReturns(this);
		}
	}

	public final RuleReturnsContext ruleReturns() throws RecognitionException {
		RuleReturnsContext _localctx = new RuleReturnsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_ruleReturns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(RETURNS);
			setState(234);
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
		public TerminalNode FRAGMENT() { return getToken(SGLParser.FRAGMENT, 0); }
		public RuleModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterRuleModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitRuleModifier(this);
		}
	}

	public final RuleModifierContext ruleModifier() throws RecognitionException {
		RuleModifierContext _localctx = new RuleModifierContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ruleModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
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
		public List<TerminalNode> OR() { return getTokens(SGLParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(SGLParser.OR, i);
		}
		public AltListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_altList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterAltList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitAltList(this);
		}
	}

	public final AltListContext altList() throws RecognitionException {
		AltListContext _localctx = new AltListContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_altList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			alternative();
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(239);
				match(OR);
				setState(240);
				alternative();
				}
				}
				setState(245);
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterAlternative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitAlternative(this);
		}
	}

	public final AlternativeContext alternative() throws RecognitionException {
		AlternativeContext _localctx = new AlternativeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_alternative);
		int _la;
		try {
			setState(252);
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
				setState(247); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(246);
					element();
					}
					}
					setState(249); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 281613489411718L) != 0) );
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitElement(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_element);
		int _la;
		try {
			setState(263);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				actionBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				predicate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(256);
				weightage();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(257);
				precedence();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(258);
				arg();
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12094627905536L) != 0)) {
					{
					setState(259);
					ebnfSuffix();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(262);
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
		public TerminalNode LT() { return getToken(SGLParser.LT, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode GT() { return getToken(SGLParser.GT, 0); }
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public TerminalNode QUESTION() { return getToken(SGLParser.QUESTION, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_predicate);
		try {
			setState(272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				match(LT);
				setState(266);
				arg();
				setState(267);
				match(GT);
				}
				break;
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				actionBlock();
				setState(270);
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
		public List<BexprContext> bexpr() {
			return getRuleContexts(BexprContext.class);
		}
		public BexprContext bexpr(int i) {
			return getRuleContext(BexprContext.class,i);
		}
		public Expr_opContext expr_op() {
			return getRuleContext(Expr_opContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			bexpr();
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN || _la==PLUS_ASSIGN) {
				{
				setState(275);
				expr_op();
				setState(276);
				bexpr();
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
	public static class Expr_opContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(SGLParser.ASSIGN, 0); }
		public TerminalNode PLUS_ASSIGN() { return getToken(SGLParser.PLUS_ASSIGN, 0); }
		public TerminalNode DOLLAR() { return getToken(SGLParser.DOLLAR, 0); }
		public Expr_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterExpr_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitExpr_op(this);
		}
	}

	public final Expr_opContext expr_op() throws RecognitionException {
		Expr_opContext _localctx = new Expr_opContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_expr_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_la = _input.LA(1);
			if ( !(_la==ASSIGN || _la==PLUS_ASSIGN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOLLAR) {
				{
				setState(281);
				match(DOLLAR);
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
	public static class MexprContext extends ParserRuleContext {
		public List<LexprContext> lexpr() {
			return getRuleContexts(LexprContext.class);
		}
		public LexprContext lexpr(int i) {
			return getRuleContext(LexprContext.class,i);
		}
		public List<Mexpr_opContext> mexpr_op() {
			return getRuleContexts(Mexpr_opContext.class);
		}
		public Mexpr_opContext mexpr_op(int i) {
			return getRuleContext(Mexpr_opContext.class,i);
		}
		public MexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterMexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitMexpr(this);
		}
	}

	public final MexprContext mexpr() throws RecognitionException {
		MexprContext _localctx = new MexprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_mexpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			lexpr();
			setState(290);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(285);
					mexpr_op();
					setState(286);
					lexpr();
					}
					} 
				}
				setState(292);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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
	public static class Mexpr_opContext extends ParserRuleContext {
		public List<TerminalNode> ASSIGN() { return getTokens(SGLParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(SGLParser.ASSIGN, i);
		}
		public TerminalNode NEGATE() { return getToken(SGLParser.NEGATE, 0); }
		public TerminalNode GT() { return getToken(SGLParser.GT, 0); }
		public TerminalNode LT() { return getToken(SGLParser.LT, 0); }
		public Mexpr_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mexpr_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterMexpr_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitMexpr_op(this);
		}
	}

	public final Mexpr_opContext mexpr_op() throws RecognitionException {
		Mexpr_opContext _localctx = new Mexpr_opContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_mexpr_op);
		try {
			setState(303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				match(ASSIGN);
				setState(294);
				match(ASSIGN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(295);
				match(NEGATE);
				setState(296);
				match(ASSIGN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(297);
				match(GT);
				setState(298);
				match(ASSIGN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(299);
				match(LT);
				setState(300);
				match(ASSIGN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(301);
				match(GT);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(302);
				match(LT);
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
	public static class BexprContext extends ParserRuleContext {
		public List<MexprContext> mexpr() {
			return getRuleContexts(MexprContext.class);
		}
		public MexprContext mexpr(int i) {
			return getRuleContext(MexprContext.class,i);
		}
		public List<TerminalNode> BOOL_OP() { return getTokens(SGLParser.BOOL_OP); }
		public TerminalNode BOOL_OP(int i) {
			return getToken(SGLParser.BOOL_OP, i);
		}
		public BexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterBexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitBexpr(this);
		}
	}

	public final BexprContext bexpr() throws RecognitionException {
		BexprContext _localctx = new BexprContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_bexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			mexpr();
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BOOL_OP) {
				{
				{
				setState(306);
				match(BOOL_OP);
				setState(307);
				mexpr();
				}
				}
				setState(312);
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
	public static class LexprContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<Lexpr_opContext> lexpr_op() {
			return getRuleContexts(Lexpr_opContext.class);
		}
		public Lexpr_opContext lexpr_op(int i) {
			return getRuleContext(Lexpr_opContext.class,i);
		}
		public LexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterLexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitLexpr(this);
		}
	}

	public final LexprContext lexpr() throws RecognitionException {
		LexprContext _localctx = new LexprContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_lexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			variable();
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOLLAR) {
				{
				{
				setState(314);
				lexpr_op();
				setState(315);
				variable();
				}
				}
				setState(321);
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
	public static class Lexpr_opContext extends ParserRuleContext {
		public TerminalNode DOLLAR() { return getToken(SGLParser.DOLLAR, 0); }
		public TerminalNode PLUS() { return getToken(SGLParser.PLUS, 0); }
		public TerminalNode DASH() { return getToken(SGLParser.DASH, 0); }
		public Lexpr_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexpr_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterLexpr_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitLexpr_op(this);
		}
	}

	public final Lexpr_opContext lexpr_op() throws RecognitionException {
		Lexpr_opContext _localctx = new Lexpr_opContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_lexpr_op);
		try {
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(322);
				match(DOLLAR);
				setState(323);
				match(PLUS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(324);
				match(DOLLAR);
				setState(325);
				match(DASH);
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
	public static class VariableContext extends ParserRuleContext {
		public CompIdentifierContext compIdentifier() {
			return getRuleContext(CompIdentifierContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(SGLParser.STRING_LITERAL, 0); }
		public TerminalNode INT_LITERAL() { return getToken(SGLParser.INT_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(SGLParser.BOOL_LITERAL, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_variable);
		try {
			setState(332);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(328);
				compIdentifier();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				match(STRING_LITERAL);
				}
				break;
			case INT_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(330);
				match(INT_LITERAL);
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(331);
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterEbnf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitEbnf(this);
		}
	}

	public final EbnfContext ebnf() throws RecognitionException {
		EbnfContext _localctx = new EbnfContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_ebnf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			block();
			setState(336);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12094627905536L) != 0)) {
				{
				setState(335);
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
		public TerminalNode QUESTION() { return getToken(SGLParser.QUESTION, 0); }
		public List<TerminalNode> STAR() { return getTokens(SGLParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(SGLParser.STAR, i);
		}
		public TerminalNode PLUS() { return getToken(SGLParser.PLUS, 0); }
		public EbnfSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ebnfSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterEbnfSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitEbnfSuffix(this);
		}
	}

	public final EbnfSuffixContext ebnfSuffix() throws RecognitionException {
		EbnfSuffixContext _localctx = new EbnfSuffixContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_ebnfSuffix);
		try {
			setState(343);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(338);
				match(QUESTION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(339);
				match(STAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(340);
				match(PLUS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(341);
				match(STAR);
				setState(342);
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
		public TerminalNode NOT() { return getToken(SGLParser.NOT, 0); }
		public CharSetContext charSet() {
			return getRuleContext(CharSetContext.class,0);
		}
		public LexerAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerAtom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterLexerAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitLexerAtom(this);
		}
	}

	public final LexerAtomContext lexerAtom() throws RecognitionException {
		LexerAtomContext _localctx = new LexerAtomContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_lexerAtom);
		int _la;
		try {
			setState(353);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(345);
					match(NOT);
					}
				}

				setState(348);
				characterRange();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(350);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(349);
					match(NOT);
					}
				}

				setState(352);
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
	public static class WeightageContext extends ParserRuleContext {
		public TerminalNode INT_LITERAL() { return getToken(SGLParser.INT_LITERAL, 0); }
		public TerminalNode PERCENTAGE() { return getToken(SGLParser.PERCENTAGE, 0); }
		public WeightageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weightage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterWeightage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitWeightage(this);
		}
	}

	public final WeightageContext weightage() throws RecognitionException {
		WeightageContext _localctx = new WeightageContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_weightage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(INT_LITERAL);
			setState(356);
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
		public TerminalNode AT() { return getToken(SGLParser.AT, 0); }
		public TerminalNode INT_LITERAL() { return getToken(SGLParser.INT_LITERAL, 0); }
		public PrecedenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precedence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterPrecedence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitPrecedence(this);
		}
	}

	public final PrecedenceContext precedence() throws RecognitionException {
		PrecedenceContext _localctx = new PrecedenceContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_precedence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			match(AT);
			setState(359);
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
	public static class BlockSetContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(SGLParser.LPAREN, 0); }
		public List<SetElementContext> setElement() {
			return getRuleContexts(SetElementContext.class);
		}
		public SetElementContext setElement(int i) {
			return getRuleContext(SetElementContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(SGLParser.RPAREN, 0); }
		public List<TerminalNode> OR() { return getTokens(SGLParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(SGLParser.OR, i);
		}
		public BlockSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterBlockSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitBlockSet(this);
		}
	}

	public final BlockSetContext blockSet() throws RecognitionException {
		BlockSetContext _localctx = new BlockSetContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_blockSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			match(LPAREN);
			setState(362);
			setElement();
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(363);
				match(OR);
				setState(364);
				setElement();
				}
				}
				setState(369);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
	public static class SetElementContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(SGLParser.STRING_LITERAL, 0); }
		public CharacterRangeContext characterRange() {
			return getRuleContext(CharacterRangeContext.class,0);
		}
		public SetElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterSetElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitSetElement(this);
		}
	}

	public final SetElementContext setElement() throws RecognitionException {
		SetElementContext _localctx = new SetElementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_setElement);
		try {
			setState(375);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				match(STRING_LITERAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(374);
				characterRange();
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
		public TerminalNode LPAREN() { return getToken(SGLParser.LPAREN, 0); }
		public AltListContext altList() {
			return getRuleContext(AltListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SGLParser.RPAREN, 0); }
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			match(LPAREN);
			setState(378);
			altList();
			setState(379);
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
		public List<TerminalNode> STRING_LITERAL() { return getTokens(SGLParser.STRING_LITERAL); }
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(SGLParser.STRING_LITERAL, i);
		}
		public TerminalNode RANGE() { return getToken(SGLParser.RANGE, 0); }
		public CharacterRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_characterRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterCharacterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitCharacterRange(this);
		}
	}

	public final CharacterRangeContext characterRange() throws RecognitionException {
		CharacterRangeContext _localctx = new CharacterRangeContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_characterRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			match(STRING_LITERAL);
			setState(382);
			match(RANGE);
			setState(383);
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
		public TerminalNode STRING_LITERAL() { return getToken(SGLParser.STRING_LITERAL, 0); }
		public TerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitTerminal(this);
		}
	}

	public final TerminalContext terminal() throws RecognitionException {
		TerminalContext _localctx = new TerminalContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_terminal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
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
		public TerminalNode DOT() { return getToken(SGLParser.DOT, 0); }
		public CompIdentifierContext compIdentifier() {
			return getRuleContext(CompIdentifierContext.class,0);
		}
		public CompIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterCompIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitCompIdentifier(this);
		}
	}

	public final CompIdentifierContext compIdentifier() throws RecognitionException {
		CompIdentifierContext _localctx = new CompIdentifierContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_compIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			identifier();
			setState(389);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(388);
				argActionBlock();
				}
				break;
			}
			setState(393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(391);
				match(DOT);
				setState(392);
				compIdentifier();
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
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode RULE_REF() { return getToken(SGLParser.RULE_REF, 0); }
		public TerminalNode TOKEN_REF() { return getToken(SGLParser.TOKEN_REF, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
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
		public TerminalNode TOKEN_REF() { return getToken(SGLParser.TOKEN_REF, 0); }
		public TerminalNode COLON() { return getToken(SGLParser.COLON, 0); }
		public LexerAltListContext lexerAltList() {
			return getRuleContext(LexerAltListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(SGLParser.SEMI, 0); }
		public RuleModifierContext ruleModifier() {
			return getRuleContext(RuleModifierContext.class,0);
		}
		public LexerRuleSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerRuleSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterLexerRuleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitLexerRuleSpec(this);
		}
	}

	public final LexerRuleSpecContext lexerRuleSpec() throws RecognitionException {
		LexerRuleSpecContext _localctx = new LexerRuleSpecContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_lexerRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FRAGMENT) {
				{
				setState(397);
				ruleModifier();
				}
			}

			setState(400);
			match(TOKEN_REF);
			setState(401);
			match(COLON);
			setState(402);
			lexerAltList();
			setState(403);
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
		public List<TerminalNode> OR() { return getTokens(SGLParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(SGLParser.OR, i);
		}
		public LexerAltListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerAltList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterLexerAltList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitLexerAltList(this);
		}
	}

	public final LexerAltListContext lexerAltList() throws RecognitionException {
		LexerAltListContext _localctx = new LexerAltListContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_lexerAltList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			lexerAlt();
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(406);
				match(OR);
				setState(407);
				lexerAlt();
				}
				}
				setState(412);
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterLexerAlt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitLexerAlt(this);
		}
	}

	public final LexerAltContext lexerAlt() throws RecognitionException {
		LexerAltContext _localctx = new LexerAltContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_lexerAlt);
		int _la;
		try {
			setState(419);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case STRING_LITERAL:
			case BOOL_LITERAL:
			case INT_LITERAL:
			case BEGIN_ACTION:
			case LPAREN:
			case LBRACK:
			case LT:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(414); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(413);
					lexerElement();
					}
					}
					setState(416); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1126055599412870L) != 0) );
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
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public EbnfSuffixContext ebnfSuffix() {
			return getRuleContext(EbnfSuffixContext.class,0);
		}
		public LexerAtomContext lexerAtom() {
			return getRuleContext(LexerAtomContext.class,0);
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
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterLexerElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitLexerElement(this);
		}
	}

	public final LexerElementContext lexerElement() throws RecognitionException {
		LexerElementContext _localctx = new LexerElementContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_lexerElement);
		int _la;
		try {
			setState(435);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(421);
				actionBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(422);
				predicate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(423);
				expression();
				setState(425);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12094627905536L) != 0)) {
					{
					setState(424);
					ebnfSuffix();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(427);
				lexerAtom();
				setState(429);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12094627905536L) != 0)) {
					{
					setState(428);
					ebnfSuffix();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(431);
				lexerBlock();
				setState(433);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12094627905536L) != 0)) {
					{
					setState(432);
					ebnfSuffix();
					}
				}

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
		public TerminalNode LPAREN() { return getToken(SGLParser.LPAREN, 0); }
		public LexerAltListContext lexerAltList() {
			return getRuleContext(LexerAltListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SGLParser.RPAREN, 0); }
		public LexerBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterLexerBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitLexerBlock(this);
		}
	}

	public final LexerBlockContext lexerBlock() throws RecognitionException {
		LexerBlockContext _localctx = new LexerBlockContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_lexerBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			match(LPAREN);
			setState(438);
			lexerAltList();
			setState(439);
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
		public TerminalNode LBRACK() { return getToken(SGLParser.LBRACK, 0); }
		public CharSetContentContext charSetContent() {
			return getRuleContext(CharSetContentContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(SGLParser.RBRACK, 0); }
		public CharSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterCharSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitCharSet(this);
		}
	}

	public final CharSetContext charSet() throws RecognitionException {
		CharSetContext _localctx = new CharSetContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_charSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			match(LBRACK);
			setState(442);
			charSetContent();
			setState(443);
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
		public List<TerminalNode> ESC() { return getTokens(SGLParser.ESC); }
		public TerminalNode ESC(int i) {
			return getToken(SGLParser.ESC, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(SGLParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(SGLParser.RBRACK, i);
		}
		public CharSetContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charSetContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).enterCharSetContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SGLParserListener ) ((SGLParserListener)listener).exitCharSetContent(this);
		}
	}

	public final CharSetContentContext charSetContent() throws RecognitionException {
		CharSetContentContext _localctx = new CharSetContentContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_charSetContent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(447);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(445);
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
					setState(446);
					match(ESC);
					}
					break;
				}
				}
				setState(449); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 288230341791973374L) != 0) );
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
		"\u0004\u00019\u01c4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0001"+
		"\u0000\u0001\u0000\u0005\u0000g\b\u0000\n\u0000\f\u0000j\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002w\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003}\b"+
		"\u0003\n\u0003\f\u0003\u0080\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005\u008b\b\u0005\n\u0005\f\u0005\u008e\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u0093\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006\u0099\b\u0006\n\u0006\f\u0006\u009c\t\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u00a5\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0003\t\u00ad\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0005"+
		"\n\u00b3\b\n\n\n\f\n\u00b6\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0005\u000b\u00bd\b\u000b\n\u000b\f\u000b\u00c0\t\u000b\u0001"+
		"\u000b\u0003\u000b\u00c3\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005"+
		"\f\u00c9\b\f\n\f\f\f\u00cc\t\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e"+
		"\u0005\u000e\u00d3\b\u000e\n\u000e\f\u000e\u00d6\t\u000e\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u00da\b\u000f\u0001\u0010\u0003\u0010\u00dd\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00e1\b\u0010\u0001\u0010\u0003\u0010"+
		"\u00e4\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0005\u0013\u00f2\b\u0013\n\u0013\f\u0013\u00f5\t\u0013\u0001"+
		"\u0014\u0004\u0014\u00f8\b\u0014\u000b\u0014\f\u0014\u00f9\u0001\u0014"+
		"\u0003\u0014\u00fd\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u0105\b\u0015\u0001\u0015\u0003\u0015"+
		"\u0108\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u0111\b\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0003\u0017\u0117\b\u0017\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u011b\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0005\u0019\u0121\b\u0019\n\u0019\f\u0019\u0124\t\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0130\b\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0005\u001b\u0135\b\u001b\n\u001b\f\u001b\u0138\t\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0005\u001c\u013e\b\u001c"+
		"\n\u001c\f\u001c\u0141\t\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0003\u001d\u0147\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0003\u001e\u014d\b\u001e\u0001\u001f\u0001\u001f\u0003\u001f\u0151"+
		"\b\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u0158\b \u0001!\u0003"+
		"!\u015b\b!\u0001!\u0001!\u0003!\u015f\b!\u0001!\u0003!\u0162\b!\u0001"+
		"\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0005"+
		"$\u016e\b$\n$\f$\u0171\t$\u0001$\u0001$\u0001%\u0001%\u0001%\u0003%\u0178"+
		"\b%\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"(\u0001(\u0001)\u0001)\u0003)\u0186\b)\u0001)\u0001)\u0003)\u018a\b)\u0001"+
		"*\u0001*\u0001+\u0003+\u018f\b+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001"+
		",\u0001,\u0001,\u0005,\u0199\b,\n,\f,\u019c\t,\u0001-\u0004-\u019f\b-"+
		"\u000b-\f-\u01a0\u0001-\u0003-\u01a4\b-\u0001.\u0001.\u0001.\u0001.\u0003"+
		".\u01aa\b.\u0001.\u0001.\u0003.\u01ae\b.\u0001.\u0001.\u0003.\u01b2\b"+
		".\u0003.\u01b4\b.\u0001/\u0001/\u0001/\u0001/\u00010\u00010\u00010\u0001"+
		"0\u00011\u00011\u00041\u01c0\b1\u000b1\f1\u01c1\u00011\u0000\u00002\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`b\u0000\u0003\u0002\u0000\'\'"+
		"**\u0001\u0000\u0001\u0002\u0001\u0000##\u01d7\u0000d\u0001\u0000\u0000"+
		"\u0000\u0002n\u0001\u0000\u0000\u0000\u0004v\u0001\u0000\u0000\u0000\u0006"+
		"x\u0001\u0000\u0000\u0000\b\u0083\u0001\u0000\u0000\u0000\n\u0092\u0001"+
		"\u0000\u0000\u0000\f\u0094\u0001\u0000\u0000\u0000\u000e\u00a4\u0001\u0000"+
		"\u0000\u0000\u0010\u00a6\u0001\u0000\u0000\u0000\u0012\u00aa\u0001\u0000"+
		"\u0000\u0000\u0014\u00b0\u0001\u0000\u0000\u0000\u0016\u00b9\u0001\u0000"+
		"\u0000\u0000\u0018\u00c4\u0001\u0000\u0000\u0000\u001a\u00cf\u0001\u0000"+
		"\u0000\u0000\u001c\u00d4\u0001\u0000\u0000\u0000\u001e\u00d9\u0001\u0000"+
		"\u0000\u0000 \u00dc\u0001\u0000\u0000\u0000\"\u00e9\u0001\u0000\u0000"+
		"\u0000$\u00ec\u0001\u0000\u0000\u0000&\u00ee\u0001\u0000\u0000\u0000("+
		"\u00fc\u0001\u0000\u0000\u0000*\u0107\u0001\u0000\u0000\u0000,\u0110\u0001"+
		"\u0000\u0000\u0000.\u0112\u0001\u0000\u0000\u00000\u0118\u0001\u0000\u0000"+
		"\u00002\u011c\u0001\u0000\u0000\u00004\u012f\u0001\u0000\u0000\u00006"+
		"\u0131\u0001\u0000\u0000\u00008\u0139\u0001\u0000\u0000\u0000:\u0146\u0001"+
		"\u0000\u0000\u0000<\u014c\u0001\u0000\u0000\u0000>\u014e\u0001\u0000\u0000"+
		"\u0000@\u0157\u0001\u0000\u0000\u0000B\u0161\u0001\u0000\u0000\u0000D"+
		"\u0163\u0001\u0000\u0000\u0000F\u0166\u0001\u0000\u0000\u0000H\u0169\u0001"+
		"\u0000\u0000\u0000J\u0177\u0001\u0000\u0000\u0000L\u0179\u0001\u0000\u0000"+
		"\u0000N\u017d\u0001\u0000\u0000\u0000P\u0181\u0001\u0000\u0000\u0000R"+
		"\u0183\u0001\u0000\u0000\u0000T\u018b\u0001\u0000\u0000\u0000V\u018e\u0001"+
		"\u0000\u0000\u0000X\u0195\u0001\u0000\u0000\u0000Z\u01a3\u0001\u0000\u0000"+
		"\u0000\\\u01b3\u0001\u0000\u0000\u0000^\u01b5\u0001\u0000\u0000\u0000"+
		"`\u01b9\u0001\u0000\u0000\u0000b\u01bf\u0001\u0000\u0000\u0000dh\u0003"+
		"\u0002\u0001\u0000eg\u0003\u0004\u0002\u0000fe\u0001\u0000\u0000\u0000"+
		"gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000"+
		"\u0000ik\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000kl\u0003\u001c"+
		"\u000e\u0000lm\u0005\u0000\u0000\u0001m\u0001\u0001\u0000\u0000\u0000"+
		"no\u0005\u0018\u0000\u0000op\u0003T*\u0000pq\u0005\u001d\u0000\u0000q"+
		"\u0003\u0001\u0000\u0000\u0000rw\u0003\u0006\u0003\u0000sw\u0003\f\u0006"+
		"\u0000tw\u0003\u0010\b\u0000uw\u0003\u0012\t\u0000vr\u0001\u0000\u0000"+
		"\u0000vs\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000vu\u0001\u0000"+
		"\u0000\u0000w\u0005\u0001\u0000\u0000\u0000x~\u0005\u000f\u0000\u0000"+
		"yz\u0003\b\u0004\u0000z{\u0005\u001d\u0000\u0000{}\u0001\u0000\u0000\u0000"+
		"|y\u0001\u0000\u0000\u0000}\u0080\u0001\u0000\u0000\u0000~|\u0001\u0000"+
		"\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0081\u0001\u0000\u0000"+
		"\u0000\u0080~\u0001\u0000\u0000\u0000\u0081\u0082\u0005!\u0000\u0000\u0082"+
		"\u0007\u0001\u0000\u0000\u0000\u0083\u0084\u0003T*\u0000\u0084\u0085\u0005"+
		"\'\u0000\u0000\u0085\u0086\u0003\n\u0005\u0000\u0086\t\u0001\u0000\u0000"+
		"\u0000\u0087\u008c\u0003T*\u0000\u0088\u0089\u0005/\u0000\u0000\u0089"+
		"\u008b\u0003T*\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008b\u008e\u0001"+
		"\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c\u008d\u0001"+
		"\u0000\u0000\u0000\u008d\u0093\u0001\u0000\u0000\u0000\u008e\u008c\u0001"+
		"\u0000\u0000\u0000\u008f\u0093\u0005\u0007\u0000\u0000\u0090\u0093\u0003"+
		"\u0014\n\u0000\u0091\u0093\u0005\n\u0000\u0000\u0092\u0087\u0001\u0000"+
		"\u0000\u0000\u0092\u008f\u0001\u0000\u0000\u0000\u0092\u0090\u0001\u0000"+
		"\u0000\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093\u000b\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0005\u0014\u0000\u0000\u0095\u009a\u0003\u000e"+
		"\u0007\u0000\u0096\u0097\u0005\u001c\u0000\u0000\u0097\u0099\u0003\u000e"+
		"\u0007\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0099\u009c\u0001\u0000"+
		"\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000"+
		"\u0000\u0000\u009b\u009d\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u0005\u001d\u0000\u0000\u009e\r\u0001\u0000\u0000"+
		"\u0000\u009f\u00a0\u0003T*\u0000\u00a0\u00a1\u0005\'\u0000\u0000\u00a1"+
		"\u00a2\u0003T*\u0000\u00a2\u00a5\u0001\u0000\u0000\u0000\u00a3\u00a5\u0003"+
		"T*\u0000\u00a4\u009f\u0001\u0000\u0000\u0000\u00a4\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a5\u000f\u0001\u0000\u0000\u0000\u00a6\u00a7\u00050\u0000\u0000"+
		"\u00a7\u00a8\u0003T*\u0000\u00a8\u00a9\u0003\u0014\n\u0000\u00a9\u0011"+
		"\u0001\u0000\u0000\u0000\u00aa\u00ac\u0005\u0010\u0000\u0000\u00ab\u00ad"+
		"\u0003\u0016\u000b\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0005!\u0000\u0000\u00af\u0013\u0001\u0000\u0000\u0000\u00b0\u00b4\u0005"+
		"\f\u0000\u0000\u00b1\u00b3\u00058\u0000\u0000\u00b2\u00b1\u0001\u0000"+
		"\u0000\u0000\u00b3\u00b6\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00b8\u00056\u0000"+
		"\u0000\u00b8\u0015\u0001\u0000\u0000\u0000\u00b9\u00be\u0003T*\u0000\u00ba"+
		"\u00bb\u0005\u001c\u0000\u0000\u00bb\u00bd\u0003T*\u0000\u00bc\u00ba\u0001"+
		"\u0000\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000\u0000\u00be\u00bc\u0001"+
		"\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c1\u00c3\u0005"+
		"\u001c\u0000\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c3\u0017\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005"+
		"\"\u0000\u0000\u00c5\u00ca\u0003\u001a\r\u0000\u00c6\u00c7\u0005\u001c"+
		"\u0000\u0000\u00c7\u00c9\u0003\u001a\r\u0000\u00c8\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000"+
		"\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cd\u0001\u0000\u0000"+
		"\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005#\u0000\u0000"+
		"\u00ce\u0019\u0001\u0000\u0000\u0000\u00cf\u00d0\u0003.\u0017\u0000\u00d0"+
		"\u001b\u0001\u0000\u0000\u0000\u00d1\u00d3\u0003\u001e\u000f\u0000\u00d2"+
		"\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d6\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5"+
		"\u001d\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7"+
		"\u00da\u0003 \u0010\u0000\u00d8\u00da\u0003V+\u0000\u00d9\u00d7\u0001"+
		"\u0000\u0000\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00da\u001f\u0001"+
		"\u0000\u0000\u0000\u00db\u00dd\u0003$\u0012\u0000\u00dc\u00db\u0001\u0000"+
		"\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000"+
		"\u0000\u0000\u00de\u00e0\u0005\u0002\u0000\u0000\u00df\u00e1\u0003\u0018"+
		"\f\u0000\u00e0\u00df\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e3\u0001\u0000\u0000\u0000\u00e2\u00e4\u0003\"\u0011\u0000"+
		"\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000"+
		"\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005\u001a\u0000\u0000"+
		"\u00e6\u00e7\u0003&\u0013\u0000\u00e7\u00e8\u0005\u001d\u0000\u0000\u00e8"+
		"!\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005\u0019\u0000\u0000\u00ea\u00eb"+
		"\u0003\u0018\f\u0000\u00eb#\u0001\u0000\u0000\u0000\u00ec\u00ed\u0005"+
		"\u0015\u0000\u0000\u00ed%\u0001\u0000\u0000\u0000\u00ee\u00f3\u0003(\u0014"+
		"\u0000\u00ef\u00f0\u0005,\u0000\u0000\u00f0\u00f2\u0003(\u0014\u0000\u00f1"+
		"\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f5\u0001\u0000\u0000\u0000\u00f3"+
		"\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4"+
		"\'\u0001\u0000\u0000\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f6\u00f8"+
		"\u0003*\u0015\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001"+
		"\u0000\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001"+
		"\u0000\u0000\u0000\u00fa\u00fd\u0001\u0000\u0000\u0000\u00fb\u00fd\u0001"+
		"\u0000\u0000\u0000\u00fc\u00f7\u0001\u0000\u0000\u0000\u00fc\u00fb\u0001"+
		"\u0000\u0000\u0000\u00fd)\u0001\u0000\u0000\u0000\u00fe\u0108\u0003\u0014"+
		"\n\u0000\u00ff\u0108\u0003,\u0016\u0000\u0100\u0108\u0003D\"\u0000\u0101"+
		"\u0108\u0003F#\u0000\u0102\u0104\u0003\u001a\r\u0000\u0103\u0105\u0003"+
		"@ \u0000\u0104\u0103\u0001\u0000\u0000\u0000\u0104\u0105\u0001\u0000\u0000"+
		"\u0000\u0105\u0108\u0001\u0000\u0000\u0000\u0106\u0108\u0003>\u001f\u0000"+
		"\u0107\u00fe\u0001\u0000\u0000\u0000\u0107\u00ff\u0001\u0000\u0000\u0000"+
		"\u0107\u0100\u0001\u0000\u0000\u0000\u0107\u0101\u0001\u0000\u0000\u0000"+
		"\u0107\u0102\u0001\u0000\u0000\u0000\u0107\u0106\u0001\u0000\u0000\u0000"+
		"\u0108+\u0001\u0000\u0000\u0000\u0109\u010a\u0005%\u0000\u0000\u010a\u010b"+
		"\u0003\u001a\r\u0000\u010b\u010c\u0005&\u0000\u0000\u010c\u0111\u0001"+
		"\u0000\u0000\u0000\u010d\u010e\u0003\u0014\n\u0000\u010e\u010f\u0005("+
		"\u0000\u0000\u010f\u0111\u0001\u0000\u0000\u0000\u0110\u0109\u0001\u0000"+
		"\u0000\u0000\u0110\u010d\u0001\u0000\u0000\u0000\u0111-\u0001\u0000\u0000"+
		"\u0000\u0112\u0116\u00036\u001b\u0000\u0113\u0114\u00030\u0018\u0000\u0114"+
		"\u0115\u00036\u001b\u0000\u0115\u0117\u0001\u0000\u0000\u0000\u0116\u0113"+
		"\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117/\u0001"+
		"\u0000\u0000\u0000\u0118\u011a\u0007\u0000\u0000\u0000\u0119\u011b\u0005"+
		"-\u0000\u0000\u011a\u0119\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000"+
		"\u0000\u0000\u011b1\u0001\u0000\u0000\u0000\u011c\u0122\u00038\u001c\u0000"+
		"\u011d\u011e\u00034\u001a\u0000\u011e\u011f\u00038\u001c\u0000\u011f\u0121"+
		"\u0001\u0000\u0000\u0000\u0120\u011d\u0001\u0000\u0000\u0000\u0121\u0124"+
		"\u0001\u0000\u0000\u0000\u0122\u0120\u0001\u0000\u0000\u0000\u0122\u0123"+
		"\u0001\u0000\u0000\u0000\u01233\u0001\u0000\u0000\u0000\u0124\u0122\u0001"+
		"\u0000\u0000\u0000\u0125\u0126\u0005\'\u0000\u0000\u0126\u0130\u0005\'"+
		"\u0000\u0000\u0127\u0128\u0005\u0013\u0000\u0000\u0128\u0130\u0005\'\u0000"+
		"\u0000\u0129\u012a\u0005&\u0000\u0000\u012a\u0130\u0005\'\u0000\u0000"+
		"\u012b\u012c\u0005%\u0000\u0000\u012c\u0130\u0005\'\u0000\u0000\u012d"+
		"\u0130\u0005&\u0000\u0000\u012e\u0130\u0005%\u0000\u0000\u012f\u0125\u0001"+
		"\u0000\u0000\u0000\u012f\u0127\u0001\u0000\u0000\u0000\u012f\u0129\u0001"+
		"\u0000\u0000\u0000\u012f\u012b\u0001\u0000\u0000\u0000\u012f\u012d\u0001"+
		"\u0000\u0000\u0000\u012f\u012e\u0001\u0000\u0000\u0000\u01305\u0001\u0000"+
		"\u0000\u0000\u0131\u0136\u00032\u0019\u0000\u0132\u0133\u0005\u000b\u0000"+
		"\u0000\u0133\u0135\u00032\u0019\u0000\u0134\u0132\u0001\u0000\u0000\u0000"+
		"\u0135\u0138\u0001\u0000\u0000\u0000\u0136\u0134\u0001\u0000\u0000\u0000"+
		"\u0136\u0137\u0001\u0000\u0000\u0000\u01377\u0001\u0000\u0000\u0000\u0138"+
		"\u0136\u0001\u0000\u0000\u0000\u0139\u013f\u0003<\u001e\u0000\u013a\u013b"+
		"\u0003:\u001d\u0000\u013b\u013c\u0003<\u001e\u0000\u013c\u013e\u0001\u0000"+
		"\u0000\u0000\u013d\u013a\u0001\u0000\u0000\u0000\u013e\u0141\u0001\u0000"+
		"\u0000\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u013f\u0140\u0001\u0000"+
		"\u0000\u0000\u01409\u0001\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000"+
		"\u0000\u0142\u0143\u0005-\u0000\u0000\u0143\u0147\u0005+\u0000\u0000\u0144"+
		"\u0145\u0005-\u0000\u0000\u0145\u0147\u0005\r\u0000\u0000\u0146\u0142"+
		"\u0001\u0000\u0000\u0000\u0146\u0144\u0001\u0000\u0000\u0000\u0147;\u0001"+
		"\u0000\u0000\u0000\u0148\u014d\u0003R)\u0000\u0149\u014d\u0005\u0007\u0000"+
		"\u0000\u014a\u014d\u0005\n\u0000\u0000\u014b\u014d\u0005\t\u0000\u0000"+
		"\u014c\u0148\u0001\u0000\u0000\u0000\u014c\u0149\u0001\u0000\u0000\u0000"+
		"\u014c\u014a\u0001\u0000\u0000\u0000\u014c\u014b\u0001\u0000\u0000\u0000"+
		"\u014d=\u0001\u0000\u0000\u0000\u014e\u0150\u0003L&\u0000\u014f\u0151"+
		"\u0003@ \u0000\u0150\u014f\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000"+
		"\u0000\u0000\u0151?\u0001\u0000\u0000\u0000\u0152\u0158\u0005(\u0000\u0000"+
		"\u0153\u0158\u0005)\u0000\u0000\u0154\u0158\u0005+\u0000\u0000\u0155\u0156"+
		"\u0005)\u0000\u0000\u0156\u0158\u0005)\u0000\u0000\u0157\u0152\u0001\u0000"+
		"\u0000\u0000\u0157\u0153\u0001\u0000\u0000\u0000\u0157\u0154\u0001\u0000"+
		"\u0000\u0000\u0157\u0155\u0001\u0000\u0000\u0000\u0158A\u0001\u0000\u0000"+
		"\u0000\u0159\u015b\u00052\u0000\u0000\u015a\u0159\u0001\u0000\u0000\u0000"+
		"\u015a\u015b\u0001\u0000\u0000\u0000\u015b\u015c\u0001\u0000\u0000\u0000"+
		"\u015c\u0162\u0003N\'\u0000\u015d\u015f\u00052\u0000\u0000\u015e\u015d"+
		"\u0001\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015f\u0160"+
		"\u0001\u0000\u0000\u0000\u0160\u0162\u0003`0\u0000\u0161\u015a\u0001\u0000"+
		"\u0000\u0000\u0161\u015e\u0001\u0000\u0000\u0000\u0162C\u0001\u0000\u0000"+
		"\u0000\u0163\u0164\u0005\n\u0000\u0000\u0164\u0165\u0005\u0012\u0000\u0000"+
		"\u0165E\u0001\u0000\u0000\u0000\u0166\u0167\u00050\u0000\u0000\u0167\u0168"+
		"\u0005\n\u0000\u0000\u0168G\u0001\u0000\u0000\u0000\u0169\u016a\u0005"+
		"\u001e\u0000\u0000\u016a\u016f\u0003J%\u0000\u016b\u016c\u0005,\u0000"+
		"\u0000\u016c\u016e\u0003J%\u0000\u016d\u016b\u0001\u0000\u0000\u0000\u016e"+
		"\u0171\u0001\u0000\u0000\u0000\u016f\u016d\u0001\u0000\u0000\u0000\u016f"+
		"\u0170\u0001\u0000\u0000\u0000\u0170\u0172\u0001\u0000\u0000\u0000\u0171"+
		"\u016f\u0001\u0000\u0000\u0000\u0172\u0173\u0005\u001f\u0000\u0000\u0173"+
		"I\u0001\u0000\u0000\u0000\u0174\u0178\u0003T*\u0000\u0175\u0178\u0005"+
		"\u0007\u0000\u0000\u0176\u0178\u0003N\'\u0000\u0177\u0174\u0001\u0000"+
		"\u0000\u0000\u0177\u0175\u0001\u0000\u0000\u0000\u0177\u0176\u0001\u0000"+
		"\u0000\u0000\u0178K\u0001\u0000\u0000\u0000\u0179\u017a\u0005\u001e\u0000"+
		"\u0000\u017a\u017b\u0003&\u0013\u0000\u017b\u017c\u0005\u001f\u0000\u0000"+
		"\u017cM\u0001\u0000\u0000\u0000\u017d\u017e\u0005\u0007\u0000\u0000\u017e"+
		"\u017f\u0005.\u0000\u0000\u017f\u0180\u0005\u0007\u0000\u0000\u0180O\u0001"+
		"\u0000\u0000\u0000\u0181\u0182\u0005\u0007\u0000\u0000\u0182Q\u0001\u0000"+
		"\u0000\u0000\u0183\u0185\u0003T*\u0000\u0184\u0186\u0003\u0018\f\u0000"+
		"\u0185\u0184\u0001\u0000\u0000\u0000\u0185\u0186\u0001\u0000\u0000\u0000"+
		"\u0186\u0189\u0001\u0000\u0000\u0000\u0187\u0188\u0005/\u0000\u0000\u0188"+
		"\u018a\u0003R)\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189\u018a\u0001"+
		"\u0000\u0000\u0000\u018aS\u0001\u0000\u0000\u0000\u018b\u018c\u0007\u0001"+
		"\u0000\u0000\u018cU\u0001\u0000\u0000\u0000\u018d\u018f\u0003$\u0012\u0000"+
		"\u018e\u018d\u0001\u0000\u0000\u0000\u018e\u018f\u0001\u0000\u0000\u0000"+
		"\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u0191\u0005\u0001\u0000\u0000"+
		"\u0191\u0192\u0005\u001a\u0000\u0000\u0192\u0193\u0003X,\u0000\u0193\u0194"+
		"\u0005\u001d\u0000\u0000\u0194W\u0001\u0000\u0000\u0000\u0195\u019a\u0003"+
		"Z-\u0000\u0196\u0197\u0005,\u0000\u0000\u0197\u0199\u0003Z-\u0000\u0198"+
		"\u0196\u0001\u0000\u0000\u0000\u0199\u019c\u0001\u0000\u0000\u0000\u019a"+
		"\u0198\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b"+
		"Y\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000\u0000\u019d\u019f"+
		"\u0003\\.\u0000\u019e\u019d\u0001\u0000\u0000\u0000\u019f\u01a0\u0001"+
		"\u0000\u0000\u0000\u01a0\u019e\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001"+
		"\u0000\u0000\u0000\u01a1\u01a4\u0001\u0000\u0000\u0000\u01a2\u01a4\u0001"+
		"\u0000\u0000\u0000\u01a3\u019e\u0001\u0000\u0000\u0000\u01a3\u01a2\u0001"+
		"\u0000\u0000\u0000\u01a4[\u0001\u0000\u0000\u0000\u01a5\u01b4\u0003\u0014"+
		"\n\u0000\u01a6\u01b4\u0003,\u0016\u0000\u01a7\u01a9\u0003.\u0017\u0000"+
		"\u01a8\u01aa\u0003@ \u0000\u01a9\u01a8\u0001\u0000\u0000\u0000\u01a9\u01aa"+
		"\u0001\u0000\u0000\u0000\u01aa\u01b4\u0001\u0000\u0000\u0000\u01ab\u01ad"+
		"\u0003B!\u0000\u01ac\u01ae\u0003@ \u0000\u01ad\u01ac\u0001\u0000\u0000"+
		"\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae\u01b4\u0001\u0000\u0000"+
		"\u0000\u01af\u01b1\u0003^/\u0000\u01b0\u01b2\u0003@ \u0000\u01b1\u01b0"+
		"\u0001\u0000\u0000\u0000\u01b1\u01b2\u0001\u0000\u0000\u0000\u01b2\u01b4"+
		"\u0001\u0000\u0000\u0000\u01b3\u01a5\u0001\u0000\u0000\u0000\u01b3\u01a6"+
		"\u0001\u0000\u0000\u0000\u01b3\u01a7\u0001\u0000\u0000\u0000\u01b3\u01ab"+
		"\u0001\u0000\u0000\u0000\u01b3\u01af\u0001\u0000\u0000\u0000\u01b4]\u0001"+
		"\u0000\u0000\u0000\u01b5\u01b6\u0005\u001e\u0000\u0000\u01b6\u01b7\u0003"+
		"X,\u0000\u01b7\u01b8\u0005\u001f\u0000\u0000\u01b8_\u0001\u0000\u0000"+
		"\u0000\u01b9\u01ba\u0005\"\u0000\u0000\u01ba\u01bb\u0003b1\u0000\u01bb"+
		"\u01bc\u0005#\u0000\u0000\u01bca\u0001\u0000\u0000\u0000\u01bd\u01c0\b"+
		"\u0002\u0000\u0000\u01be\u01c0\u0005\u000e\u0000\u0000\u01bf\u01bd\u0001"+
		"\u0000\u0000\u0000\u01bf\u01be\u0001\u0000\u0000\u0000\u01c0\u01c1\u0001"+
		"\u0000\u0000\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c1\u01c2\u0001"+
		"\u0000\u0000\u0000\u01c2c\u0001\u0000\u0000\u00002hv~\u008c\u0092\u009a"+
		"\u00a4\u00ac\u00b4\u00be\u00c2\u00ca\u00d4\u00d9\u00dc\u00e0\u00e3\u00f3"+
		"\u00f9\u00fc\u0104\u0107\u0110\u0116\u011a\u0122\u012f\u0136\u013f\u0146"+
		"\u014c\u0150\u0157\u015a\u015e\u0161\u016f\u0177\u0185\u0189\u018e\u019a"+
		"\u01a0\u01a3\u01a9\u01ad\u01b1\u01b3\u01bf\u01c1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}