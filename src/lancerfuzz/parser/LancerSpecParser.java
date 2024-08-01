package lancerfuzz.parser;
// Generated from grammars/LancerSpecParser.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import net.sf.jsqlparser.statement.alter.Alter;

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
		BEGIN_ARGUMENT=10, BEGIN_ACTION=11, OPTIONS=12, TOKENS=13, CHANNELS=14, 
		IMPORT=15, FRAGMENT=16, SCHEMA=17, EXPR=18, STATEMENT=19, LEXER=20, PARSER=21, 
		GRAMMAR=22, PROTECTED=23, PUBLIC=24, PRIVATE=25, RETURNS=26, LOCALS=27, 
		THROWS=28, CATCH=29, FINALLY=30, MODE=31, BEGIN_ERR=32, BEGIN_REP=33, 
		BEGIN_WHT=34, COLON=35, COLONCOLON=36, COMMA=37, SEMI=38, LPAREN=39, RPAREN=40, 
		LBRACE=41, RBRACE=42, RARROW=43, LT=44, GT=45, ASSIGN=46, QUESTION=47, 
		STAR=48, PLUS_ASSIGN=49, PLUS=50, OR=51, DOLLAR=52, RANGE=53, DOT=54, 
		AT=55, POUND=56, NOT=57, ID=58, WS=59, ERRCHAR=60, END_ARGUMENT=61, UNTERMINATED_ARGUMENT=62, 
		ARGUMENT_CONTENT=63, END_ACTION=64, UNTERMINATED_ACTION=65, ACTION_CONTENT=66, 
		END_ERR_DECL=67, UNTERMINATED_ERR_DECL=68, ERR_CONTENT=69, END_WGHT_DECL=70, 
		WGHT_DECL=71, WGHT_CONTENT=72, END_REP_DECL=73, UNTERMINATED_REP_DECL=74, 
		REP_CONTENT=75, UNTERMINATED_CHAR_SET=76;
	public static final int
		RULE_grammarSpec = 0, RULE_grammarDecl = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_action_ = 8, RULE_actionBlock = 9, RULE_weightBlock = 10, 
		RULE_repetitionBlock = 11, RULE_errorBlock = 12, RULE_argActionBlock = 13, 
		RULE_rules = 14, RULE_ruleSpec = 15, RULE_parserRuleSpec = 16, RULE_ruleReturns = 17, 
		RULE_localsSpec = 18, RULE_ruleModifiers = 19, RULE_ruleModifier = 20, 
		RULE_ruleBlock = 21, RULE_ruleAltList = 22, RULE_labeledAlt = 23, RULE_altList = 24, 
		RULE_alternative = 25, RULE_element = 26, RULE_variableAssignment = 27, 
		RULE_ebnf = 28, RULE_ebnfSuffix = 29, RULE_lexerAtom = 30, RULE_atom = 31, 
		RULE_notSet = 32, RULE_blockSet = 33, RULE_setElement = 34, RULE_block = 35, 
		RULE_characterRange = 36, RULE_terminal = 37, RULE_compIdentifier = 38, 
		RULE_identifier = 39, RULE_lexerRuleSpec = 40, RULE_lexerRuleBlock = 41, 
		RULE_lexerAltList = 42, RULE_lexerAlt = 43, RULE_lexerElements = 44, RULE_lexerElement = 45, 
		RULE_lexerBlock = 46;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarSpec", "grammarDecl", "prequelConstruct", "optionsSpec", "option", 
			"optionValue", "delegateGrammars", "delegateGrammar", "action_", "actionBlock", 
			"weightBlock", "repetitionBlock", "errorBlock", "argActionBlock", "rules", 
			"ruleSpec", "parserRuleSpec", "ruleReturns", "localsSpec", "ruleModifiers", 
			"ruleModifier", "ruleBlock", "ruleAltList", "labeledAlt", "altList", 
			"alternative", "element", "variableAssignment", "ebnf", "ebnfSuffix", 
			"lexerAtom", "atom", "notSet", "blockSet", "setElement", "block", "characterRange", 
			"terminal", "compIdentifier", "identifier", "lexerRuleSpec", "lexerRuleBlock", 
			"lexerAltList", "lexerAlt", "lexerElements", "lexerElement", "lexerBlock"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "'import'", "'fragment'", "'schema'", "'expr'", "'statement'", 
			"'lexer'", "'parser'", "'grammar'", "'protected'", "'public'", "'private'", 
			"'returns'", "'locals'", "'throws'", "'catch'", "'finally'", "'mode'", 
			"'_e('", "'_r('", "'_w('"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TOKEN_REF", "RULE_REF", "LEXER_CHAR_SET", "DOC_COMMENT", "BLOCK_COMMENT", 
			"LINE_COMMENT", "INT", "STRING_LITERAL", "UNTERMINATED_STRING_LITERAL", 
			"BEGIN_ARGUMENT", "BEGIN_ACTION", "OPTIONS", "TOKENS", "CHANNELS", "IMPORT", 
			"FRAGMENT", "SCHEMA", "EXPR", "STATEMENT", "LEXER", "PARSER", "GRAMMAR", 
			"PROTECTED", "PUBLIC", "PRIVATE", "RETURNS", "LOCALS", "THROWS", "CATCH", 
			"FINALLY", "MODE", "BEGIN_ERR", "BEGIN_REP", "BEGIN_WHT", "COLON", "COLONCOLON", 
			"COMMA", "SEMI", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "RARROW", "LT", 
			"GT", "ASSIGN", "QUESTION", "STAR", "PLUS_ASSIGN", "PLUS", "OR", "DOLLAR", 
			"RANGE", "DOT", "AT", "POUND", "NOT", "ID", "WS", "ERRCHAR", "END_ARGUMENT", 
			"UNTERMINATED_ARGUMENT", "ARGUMENT_CONTENT", "END_ACTION", "UNTERMINATED_ACTION", 
			"ACTION_CONTENT", "END_ERR_DECL", "UNTERMINATED_ERR_DECL", "ERR_CONTENT", 
			"END_WGHT_DECL", "WGHT_DECL", "WGHT_CONTENT", "END_REP_DECL", "UNTERMINATED_REP_DECL", 
			"REP_CONTENT", "UNTERMINATED_CHAR_SET"
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

	// This class is an encapsulation of the FlexibleParserRuleContext class
	// Provides an API to call these methods of any subclasses of FlexibleParserRuleContext
	public static class FlexibleParserRuleContext extends ParserRuleContext {
		public FlexibleParserRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		public ArgActionBlockContext argActionBlock(){
			return null;
		}
		public ActionBlockContext actionBlock() {
			return null;
		}
		public LocalsSpecContext localsSpec(){
			return null;
		}
		public RuleReturnsContext ruleReturns(){
			return null;
		}
		public List<TerminalNode> QUESTION(){
			return null;
		}
		public TerminalNode QUESTION(int i){
			return null;
		}
		public List<ElementContext> element() {
			return null;
		}
		public ElementContext element(int i) {
			return null;
		}
		public List<LexerElementContext> lexerElement() {
			return null;
		}
		public LexerElementContext lexerElement(int i) {
			return null;
		}
		public List<AlternativeContext> alternative() {
			return null;
		}
		public AlternativeContext alternative(int i) {
			return null;
		}
		// really not a fan of the naming convention here
		// but hey, this whole parser is auto-generated lol
		public LexerElementsContext lexerElements(){
			return null;
		}
		public EbnfSuffixContext ebnfSuffix(){
			return null;
		}
		public EbnfContext ebnf() {
			return null;
		}
		public AtomContext atom() {
			return null;
		}
		public BlockContext block(){
			return null;
		}
		public List<TerminalNode> DOT(){
			return null;
		}
		public NotSetContext notSet(){
			return null;
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GrammarSpecContext extends FlexibleParserRuleContext {
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
			setState(94);
			grammarDecl();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028797019000832L) != 0)) {
				{
				{
				setState(95);
				prequelConstruct();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			rules();
			setState(102);
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
	public static class GrammarDeclContext extends FlexibleParserRuleContext {
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
			setState(104);
			match(GRAMMAR);
			setState(105);
			identifier();
			setState(106);
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
	public static class PrequelConstructContext extends FlexibleParserRuleContext {
		public OptionsSpecContext optionsSpec() {
			return getRuleContext(OptionsSpecContext.class,0);
		}
		public DelegateGrammarsContext delegateGrammars() {
			return getRuleContext(DelegateGrammarsContext.class,0);
		}
		public Action_Context action_() {
			return getRuleContext(Action_Context.class,0);
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
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPTIONS:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				optionsSpec();
				}
				break;
			case IMPORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				delegateGrammars();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				action_();
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
	public static class OptionsSpecContext extends FlexibleParserRuleContext {
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
			setState(113);
			match(OPTIONS);
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TOKEN_REF || _la==RULE_REF) {
				{
				{
				setState(114);
				option();
				setState(115);
				match(SEMI);
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
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
	public static class OptionContext extends FlexibleParserRuleContext {
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
			setState(124);
			identifier();
			setState(125);
			match(ASSIGN);
			setState(126);
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
	public static class OptionValueContext extends FlexibleParserRuleContext {
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
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				identifier();
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(129);
					match(DOT);
					setState(130);
					identifier();
					}
					}
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				match(STRING_LITERAL);
				}
				break;
			case BEGIN_ACTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				actionBlock();
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(138);
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
	public static class DelegateGrammarsContext extends FlexibleParserRuleContext {
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
			setState(141);
			match(IMPORT);
			setState(142);
			delegateGrammar();
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(143);
				match(COMMA);
				setState(144);
				delegateGrammar();
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(150);
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
	public static class DelegateGrammarContext extends FlexibleParserRuleContext {
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
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				identifier();
				setState(153);
				match(ASSIGN);
				setState(154);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
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
	public static class Action_Context extends FlexibleParserRuleContext {
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
			setState(159);
			match(AT);
			setState(160);
			identifier();
			setState(161);
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
	public static class ActionBlockContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 18, RULE_actionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(BEGIN_ACTION);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ACTION_CONTENT) {
				{
				{
				setState(164);
				match(ACTION_CONTENT);
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(170);
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
	public static class WeightBlockContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 20, RULE_weightBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(BEGIN_WHT);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WGHT_CONTENT) {
				{
				{
				setState(173);
				match(WGHT_CONTENT);
				}
				}
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(179);
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
	public static class RepetitionBlockContext extends FlexibleParserRuleContext {
		public TerminalNode BEGIN_REP() { return getToken(LancerSpecParser.BEGIN_REP, 0); }
		public TerminalNode END_REP_DECL() { return getToken(LancerSpecParser.END_REP_DECL, 0); }
		public List<TerminalNode> REP_CONTENT() { return getTokens(LancerSpecParser.REP_CONTENT); }
		public TerminalNode REP_CONTENT(int i) {
			return getToken(LancerSpecParser.REP_CONTENT, i);
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
		enterRule(_localctx, 22, RULE_repetitionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(BEGIN_REP);
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==REP_CONTENT) {
				{
				{
				setState(182);
				match(REP_CONTENT);
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(188);
			match(END_REP_DECL);
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
	public static class ErrorBlockContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 24, RULE_errorBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(BEGIN_ERR);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ERR_CONTENT) {
				{
				{
				setState(191);
				match(ERR_CONTENT);
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(197);
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
	public static class ArgActionBlockContext extends FlexibleParserRuleContext {
		public TerminalNode BEGIN_ARGUMENT() { return getToken(LancerSpecParser.BEGIN_ARGUMENT, 0); }
		public TerminalNode END_ARGUMENT() { return getToken(LancerSpecParser.END_ARGUMENT, 0); }
		public List<TerminalNode> ARGUMENT_CONTENT() { return getTokens(LancerSpecParser.ARGUMENT_CONTENT); }
		public TerminalNode ARGUMENT_CONTENT(int i) {
			return getToken(LancerSpecParser.ARGUMENT_CONTENT, i);
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
		enterRule(_localctx, 26, RULE_argActionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(BEGIN_ARGUMENT);
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ARGUMENT_CONTENT) {
				{
				{
				setState(200);
				match(ARGUMENT_CONTENT);
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(206);
			match(END_ARGUMENT);
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
	public static class RulesContext extends FlexibleParserRuleContext {
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
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 917510L) != 0)) {
				{
				{
				setState(208);
				ruleSpec();
				}
				}
				setState(213);
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
	public static class RuleSpecContext extends FlexibleParserRuleContext {
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
			setState(216);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RULE_REF:
			case SCHEMA:
			case EXPR:
			case STATEMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				parserRuleSpec();
				}
				break;
			case TOKEN_REF:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				lexerRuleSpec();
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
	public static class ParserRuleSpecContext extends FlexibleParserRuleContext {
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
		public RuleModifiersContext ruleModifiers() {
			return getRuleContext(RuleModifiersContext.class,0);
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
		enterRule(_localctx, 32, RULE_parserRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 917504L) != 0)) {
				{
				setState(218);
				ruleModifiers();
				}
			}

			setState(221);
			match(RULE_REF);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BEGIN_ARGUMENT) {
				{
				setState(222);
				argActionBlock();
				}
			}

			setState(234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(225);
				ruleReturns();
				setState(226);
				localsSpec();
				}
				break;
			case 2:
				{
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOCALS) {
					{
					setState(228);
					localsSpec();
					}
				}

				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(231);
					ruleReturns();
					}
				}

				}
				break;
			}
			setState(236);
			match(COLON);
			setState(237);
			ruleBlock();
			setState(238);
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
	public static class RuleReturnsContext extends FlexibleParserRuleContext {
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
			setState(240);
			match(RETURNS);
			setState(241);
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
	public static class LocalsSpecContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 36, RULE_localsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(LOCALS);
			setState(244);
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
	public static class RuleModifiersContext extends FlexibleParserRuleContext {
		public List<RuleModifierContext> ruleModifier() {
			return getRuleContexts(RuleModifierContext.class);
		}
		public RuleModifierContext ruleModifier(int i) {
			return getRuleContext(RuleModifierContext.class,i);
		}
		public RuleModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).enterRuleModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LancerSpecParserListener ) ((LancerSpecParserListener)listener).exitRuleModifiers(this);
		}
	}

	public final RuleModifiersContext ruleModifiers() throws RecognitionException {
		RuleModifiersContext _localctx = new RuleModifiersContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_ruleModifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(246);
				ruleModifier();
				}
				}
				setState(249); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 917504L) != 0) );
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
	public static class RuleModifierContext extends FlexibleParserRuleContext {
		public TerminalNode SCHEMA() { return getToken(LancerSpecParser.SCHEMA, 0); }
		public TerminalNode EXPR() { return getToken(LancerSpecParser.EXPR, 0); }
		public TerminalNode STATEMENT() { return getToken(LancerSpecParser.STATEMENT, 0); }
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
		enterRule(_localctx, 40, RULE_ruleModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 917504L) != 0)) ) {
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
	public static class RuleBlockContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 42, RULE_ruleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
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
	public static class RuleAltListContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 44, RULE_ruleAltList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			labeledAlt();
			setState(260);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(256);
				match(OR);
				setState(257);
				labeledAlt();
				}
				}
				setState(262);
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
	public static class LabeledAltContext extends FlexibleParserRuleContext {
		public List<AlternativeContext> alternative() {
			List<AlternativeContext> res = new ArrayList<>();
			res.add(getRuleContext(AlternativeContext.class,0));
			return res;
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
		enterRule(_localctx, 46, RULE_labeledAlt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			alternative();
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POUND) {
				{
				setState(264);
				match(POUND);
				setState(265);
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
	public static class AltListContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 48, RULE_altList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			alternative();
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(269);
				match(OR);
				setState(270);
				alternative();
				}
				}
				setState(275);
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
	public static class AlternativeContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 50, RULE_alternative);
		int _la;
		try {
			setState(282);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case STRING_LITERAL:
			case BEGIN_ACTION:
			case BEGIN_ERR:
			case BEGIN_REP:
			case BEGIN_WHT:
			case LPAREN:
			case DOLLAR:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(277); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(276);
					element();
					}
					}
					setState(279); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 148619367523813638L) != 0) );
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
	public static class ElementContext extends FlexibleParserRuleContext {
		public VariableAssignmentContext variableAssignment() {
			return getRuleContext(VariableAssignmentContext.class,0);
		}
		public EbnfSuffixContext ebnfSuffix() {
			return getRuleContext(EbnfSuffixContext.class,0);
		}
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public List<TerminalNode> QUESTION() { 
			List<TerminalNode> res = new ArrayList<>();
			res.add(getToken(LancerSpecParser.QUESTION, 0));
			return res; 
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
		enterRule(_localctx, 52, RULE_element);
		int _la;
		try {
			setState(300);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(284);
				variableAssignment();
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1548112371908608L) != 0)) {
					{
					setState(285);
					ebnfSuffix();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(288);
				actionBlock();
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUESTION) {
					{
					setState(289);
					match(QUESTION);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(292);
				weightBlock();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(293);
				errorBlock();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(294);
				repetitionBlock();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(295);
				atom();
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1548112371908608L) != 0)) {
					{
					setState(296);
					ebnfSuffix();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(299);
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
	public static class VariableAssignmentContext extends FlexibleParserRuleContext {
		public CompIdentifierContext compIdentifier() {
			return getRuleContext(CompIdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(LancerSpecParser.ASSIGN, 0); }
		public TerminalNode PLUS_ASSIGN() { return getToken(LancerSpecParser.PLUS_ASSIGN, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
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
		enterRule(_localctx, 54, RULE_variableAssignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			compIdentifier();
			setState(303);
			_la = _input.LA(1);
			if ( !(_la==ASSIGN || _la==PLUS_ASSIGN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_REF:
			case RULE_REF:
			case STRING_LITERAL:
			case DOLLAR:
			case NOT:
				{
				setState(304);
				atom();
				}
				break;
			case LPAREN:
				{
				setState(305);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class EbnfContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 56, RULE_ebnf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			block();
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1548112371908608L) != 0)) {
				{
				setState(309);
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
	public static class EbnfSuffixContext extends FlexibleParserRuleContext {
		public List<TerminalNode> QUESTION() { 
			List<TerminalNode> res = new ArrayList<>();
			res.add(getToken(LancerSpecParser.QUESTION, 0));
			return res; 
		}
		public TerminalNode STAR() { return getToken(LancerSpecParser.STAR, 0); }
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
		enterRule(_localctx, 58, RULE_ebnfSuffix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1548112371908608L) != 0)) ) {
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
	public static class LexerAtomContext extends FlexibleParserRuleContext {
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
		public CompIdentifierContext compIdentifier() {
			return getRuleContext(CompIdentifierContext.class,0);
		}
		public TerminalNode DOLLAR() { return getToken(LancerSpecParser.DOLLAR, 0); }
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
		enterRule(_localctx, 60, RULE_lexerAtom);
		int _la;
		try {
			setState(322);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(314);
				characterRange();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(315);
				terminal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(316);
				notSet();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(317);
				match(LEXER_CHAR_SET);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(319);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOLLAR) {
					{
					setState(318);
					match(DOLLAR);
					}
				}

				setState(321);
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
	public static class AtomContext extends FlexibleParserRuleContext {
		public TerminalContext terminal() {
			return getRuleContext(TerminalContext.class,0);
		}
		public CompIdentifierContext compIdentifier() {
			return getRuleContext(CompIdentifierContext.class,0);
		}
		public TerminalNode DOLLAR() { return getToken(LancerSpecParser.DOLLAR, 0); }
		public NotSetContext notSet() {
			return getRuleContext(NotSetContext.class,0);
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
		enterRule(_localctx, 62, RULE_atom);
		int _la;
		try {
			setState(330);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(324);
				terminal();
				}
				break;
			case TOKEN_REF:
			case RULE_REF:
			case DOLLAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(326);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOLLAR) {
					{
					setState(325);
					match(DOLLAR);
					}
				}

				setState(328);
				compIdentifier();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(329);
				notSet();
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
	public static class NotSetContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 64, RULE_notSet);
		try {
			setState(336);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(332);
				match(NOT);
				setState(333);
				setElement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				match(NOT);
				setState(335);
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
	public static class BlockSetContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 66, RULE_blockSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			match(LPAREN);
			setState(339);
			setElement();
			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(340);
				match(OR);
				setState(341);
				setElement();
				}
				}
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(347);
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
	public static class SetElementContext extends FlexibleParserRuleContext {
		public TerminalNode TOKEN_REF() { return getToken(LancerSpecParser.TOKEN_REF, 0); }
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
		enterRule(_localctx, 68, RULE_setElement);
		try {
			setState(353);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(349);
				match(TOKEN_REF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(350);
				match(STRING_LITERAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(351);
				characterRange();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(352);
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
	public static class BlockContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 70, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(LPAREN);
			setState(356);
			altList();
			setState(357);
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
	public static class CharacterRangeContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 72, RULE_characterRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(STRING_LITERAL);
			setState(360);
			match(RANGE);
			setState(361);
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
	public static class TerminalContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 74, RULE_terminal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
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
	public static class CompIdentifierContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 76, RULE_compIdentifier);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			identifier();
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BEGIN_ARGUMENT) {
				{
				setState(366);
				argActionBlock();
				}
			}

			setState(373);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(369);
					match(DOT);
					setState(370);
					compIdentifier();
					}
					} 
				}
				setState(375);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
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
	public static class IdentifierContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 78, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
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
	public static class LexerRuleSpecContext extends FlexibleParserRuleContext {
		public TerminalNode TOKEN_REF() { return getToken(LancerSpecParser.TOKEN_REF, 0); }
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
		enterRule(_localctx, 80, RULE_lexerRuleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			match(TOKEN_REF);
			setState(380);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONS) {
				{
				setState(379);
				optionsSpec();
				}
			}

			setState(382);
			match(COLON);
			setState(383);
			lexerRuleBlock();
			setState(384);
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
	public static class LexerRuleBlockContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 82, RULE_lexerRuleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
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
	public static class LexerAltListContext extends FlexibleParserRuleContext {
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
			setState(388);
			lexerAlt();
			setState(393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(389);
				match(OR);
				setState(390);
				lexerAlt();
				}
				}
				setState(395);
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
	public static class LexerAltContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 86, RULE_lexerAlt);
		try {
			setState(398);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(396);
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
	public static class LexerElementsContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 88, RULE_lexerElements);
		int _la;
		try {
			setState(406);
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
			case DOLLAR:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(401); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(400);
					lexerElement();
					}
					}
					setState(403); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 148619367523813646L) != 0) );
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
	public static class LexerElementContext extends FlexibleParserRuleContext {
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public List<TerminalNode> QUESTION() { 
			List<TerminalNode> res = new ArrayList<>();
			res.add(getToken(LancerSpecParser.QUESTION, 0));
			return res; 
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
		public VariableAssignmentContext variableAssignment() {
			return getRuleContext(VariableAssignmentContext.class,0);
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
		enterRule(_localctx, 90, RULE_lexerElement);
		int _la;
		try {
			setState(424);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(408);
				actionBlock();
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUESTION) {
					{
					setState(409);
					match(QUESTION);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(412);
				weightBlock();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(413);
				errorBlock();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(414);
				repetitionBlock();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(415);
				variableAssignment();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(416);
				lexerAtom();
				setState(418);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1548112371908608L) != 0)) {
					{
					setState(417);
					ebnfSuffix();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(420);
				lexerBlock();
				setState(422);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1548112371908608L) != 0)) {
					{
					setState(421);
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
	public static class LexerBlockContext extends FlexibleParserRuleContext {
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
		enterRule(_localctx, 92, RULE_lexerBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(LPAREN);
			setState(427);
			lexerAltList();
			setState(428);
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

	public static final String _serializedATN =
		"\u0004\u0001L\u01af\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"-\u0007-\u0002.\u0007.\u0001\u0000\u0001\u0000\u0005\u0000a\b\u0000\n"+
		"\u0000\f\u0000d\t\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002p\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0005\u0003v\b\u0003\n\u0003\f\u0003y\t\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005\u0084\b\u0005\n\u0005\f\u0005\u0087\t\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u008c\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u0092\b\u0006\n\u0006\f\u0006"+
		"\u0095\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u009e\b\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0005\t\u00a6\b\t\n\t\f\t\u00a9\t\t\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0005\n\u00af\b\n\n\n\f\n\u00b2\t\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0005\u000b\u00b8\b\u000b\n\u000b\f\u000b\u00bb"+
		"\t\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0005\f\u00c1\b\f\n\f"+
		"\f\f\u00c4\t\f\u0001\f\u0001\f\u0001\r\u0001\r\u0005\r\u00ca\b\r\n\r\f"+
		"\r\u00cd\t\r\u0001\r\u0001\r\u0001\u000e\u0005\u000e\u00d2\b\u000e\n\u000e"+
		"\f\u000e\u00d5\t\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u00d9\b\u000f"+
		"\u0001\u0010\u0003\u0010\u00dc\b\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u00e0\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u00e6\b\u0010\u0001\u0010\u0003\u0010\u00e9\b\u0010\u0003\u0010\u00eb"+
		"\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0004"+
		"\u0013\u00f8\b\u0013\u000b\u0013\f\u0013\u00f9\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016"+
		"\u0103\b\u0016\n\u0016\f\u0016\u0106\t\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u010b\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0005"+
		"\u0018\u0110\b\u0018\n\u0018\f\u0018\u0113\t\u0018\u0001\u0019\u0004\u0019"+
		"\u0116\b\u0019\u000b\u0019\f\u0019\u0117\u0001\u0019\u0003\u0019\u011b"+
		"\b\u0019\u0001\u001a\u0001\u001a\u0003\u001a\u011f\b\u001a\u0001\u001a"+
		"\u0001\u001a\u0003\u001a\u0123\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0003\u001a\u012a\b\u001a\u0001\u001a\u0003\u001a"+
		"\u012d\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b"+
		"\u0133\b\u001b\u0001\u001c\u0001\u001c\u0003\u001c\u0137\b\u001c\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0003\u001e\u0140\b\u001e\u0001\u001e\u0003\u001e\u0143\b\u001e"+
		"\u0001\u001f\u0001\u001f\u0003\u001f\u0147\b\u001f\u0001\u001f\u0001\u001f"+
		"\u0003\u001f\u014b\b\u001f\u0001 \u0001 \u0001 \u0001 \u0003 \u0151\b"+
		" \u0001!\u0001!\u0001!\u0001!\u0005!\u0157\b!\n!\f!\u015a\t!\u0001!\u0001"+
		"!\u0001\"\u0001\"\u0001\"\u0001\"\u0003\"\u0162\b\"\u0001#\u0001#\u0001"+
		"#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001%\u0001%\u0001&\u0001&\u0003"+
		"&\u0170\b&\u0001&\u0001&\u0005&\u0174\b&\n&\f&\u0177\t&\u0001\'\u0001"+
		"\'\u0001(\u0001(\u0003(\u017d\b(\u0001(\u0001(\u0001(\u0001(\u0001)\u0001"+
		")\u0001*\u0001*\u0001*\u0005*\u0188\b*\n*\f*\u018b\t*\u0001+\u0001+\u0003"+
		"+\u018f\b+\u0001,\u0004,\u0192\b,\u000b,\f,\u0193\u0001,\u0003,\u0197"+
		"\b,\u0001-\u0001-\u0003-\u019b\b-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001"+
		"-\u0003-\u01a3\b-\u0001-\u0001-\u0003-\u01a7\b-\u0003-\u01a9\b-\u0001"+
		".\u0001.\u0001.\u0001.\u0001.\u0000\u0000/\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0246"+
		"8:<>@BDFHJLNPRTVXZ\\\u0000\u0004\u0001\u0000\u0011\u0013\u0002\u0000."+
		".11\u0002\u0000/022\u0001\u0000\u0001\u0002\u01c3\u0000^\u0001\u0000\u0000"+
		"\u0000\u0002h\u0001\u0000\u0000\u0000\u0004o\u0001\u0000\u0000\u0000\u0006"+
		"q\u0001\u0000\u0000\u0000\b|\u0001\u0000\u0000\u0000\n\u008b\u0001\u0000"+
		"\u0000\u0000\f\u008d\u0001\u0000\u0000\u0000\u000e\u009d\u0001\u0000\u0000"+
		"\u0000\u0010\u009f\u0001\u0000\u0000\u0000\u0012\u00a3\u0001\u0000\u0000"+
		"\u0000\u0014\u00ac\u0001\u0000\u0000\u0000\u0016\u00b5\u0001\u0000\u0000"+
		"\u0000\u0018\u00be\u0001\u0000\u0000\u0000\u001a\u00c7\u0001\u0000\u0000"+
		"\u0000\u001c\u00d3\u0001\u0000\u0000\u0000\u001e\u00d8\u0001\u0000\u0000"+
		"\u0000 \u00db\u0001\u0000\u0000\u0000\"\u00f0\u0001\u0000\u0000\u0000"+
		"$\u00f3\u0001\u0000\u0000\u0000&\u00f7\u0001\u0000\u0000\u0000(\u00fb"+
		"\u0001\u0000\u0000\u0000*\u00fd\u0001\u0000\u0000\u0000,\u00ff\u0001\u0000"+
		"\u0000\u0000.\u0107\u0001\u0000\u0000\u00000\u010c\u0001\u0000\u0000\u0000"+
		"2\u011a\u0001\u0000\u0000\u00004\u012c\u0001\u0000\u0000\u00006\u012e"+
		"\u0001\u0000\u0000\u00008\u0134\u0001\u0000\u0000\u0000:\u0138\u0001\u0000"+
		"\u0000\u0000<\u0142\u0001\u0000\u0000\u0000>\u014a\u0001\u0000\u0000\u0000"+
		"@\u0150\u0001\u0000\u0000\u0000B\u0152\u0001\u0000\u0000\u0000D\u0161"+
		"\u0001\u0000\u0000\u0000F\u0163\u0001\u0000\u0000\u0000H\u0167\u0001\u0000"+
		"\u0000\u0000J\u016b\u0001\u0000\u0000\u0000L\u016d\u0001\u0000\u0000\u0000"+
		"N\u0178\u0001\u0000\u0000\u0000P\u017a\u0001\u0000\u0000\u0000R\u0182"+
		"\u0001\u0000\u0000\u0000T\u0184\u0001\u0000\u0000\u0000V\u018e\u0001\u0000"+
		"\u0000\u0000X\u0196\u0001\u0000\u0000\u0000Z\u01a8\u0001\u0000\u0000\u0000"+
		"\\\u01aa\u0001\u0000\u0000\u0000^b\u0003\u0002\u0001\u0000_a\u0003\u0004"+
		"\u0002\u0000`_\u0001\u0000\u0000\u0000ad\u0001\u0000\u0000\u0000b`\u0001"+
		"\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000ce\u0001\u0000\u0000\u0000"+
		"db\u0001\u0000\u0000\u0000ef\u0003\u001c\u000e\u0000fg\u0005\u0000\u0000"+
		"\u0001g\u0001\u0001\u0000\u0000\u0000hi\u0005\u0016\u0000\u0000ij\u0003"+
		"N\'\u0000jk\u0005&\u0000\u0000k\u0003\u0001\u0000\u0000\u0000lp\u0003"+
		"\u0006\u0003\u0000mp\u0003\f\u0006\u0000np\u0003\u0010\b\u0000ol\u0001"+
		"\u0000\u0000\u0000om\u0001\u0000\u0000\u0000on\u0001\u0000\u0000\u0000"+
		"p\u0005\u0001\u0000\u0000\u0000qw\u0005\f\u0000\u0000rs\u0003\b\u0004"+
		"\u0000st\u0005&\u0000\u0000tv\u0001\u0000\u0000\u0000ur\u0001\u0000\u0000"+
		"\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000"+
		"\u0000\u0000xz\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000z{\u0005"+
		"*\u0000\u0000{\u0007\u0001\u0000\u0000\u0000|}\u0003N\'\u0000}~\u0005"+
		".\u0000\u0000~\u007f\u0003\n\u0005\u0000\u007f\t\u0001\u0000\u0000\u0000"+
		"\u0080\u0085\u0003N\'\u0000\u0081\u0082\u00056\u0000\u0000\u0082\u0084"+
		"\u0003N\'\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0084\u0087\u0001"+
		"\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001"+
		"\u0000\u0000\u0000\u0086\u008c\u0001\u0000\u0000\u0000\u0087\u0085\u0001"+
		"\u0000\u0000\u0000\u0088\u008c\u0005\b\u0000\u0000\u0089\u008c\u0003\u0012"+
		"\t\u0000\u008a\u008c\u0005\u0007\u0000\u0000\u008b\u0080\u0001\u0000\u0000"+
		"\u0000\u008b\u0088\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000"+
		"\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008c\u000b\u0001\u0000\u0000"+
		"\u0000\u008d\u008e\u0005\u000f\u0000\u0000\u008e\u0093\u0003\u000e\u0007"+
		"\u0000\u008f\u0090\u0005%\u0000\u0000\u0090\u0092\u0003\u000e\u0007\u0000"+
		"\u0091\u008f\u0001\u0000\u0000\u0000\u0092\u0095\u0001\u0000\u0000\u0000"+
		"\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000"+
		"\u0094\u0096\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000"+
		"\u0096\u0097\u0005&\u0000\u0000\u0097\r\u0001\u0000\u0000\u0000\u0098"+
		"\u0099\u0003N\'\u0000\u0099\u009a\u0005.\u0000\u0000\u009a\u009b\u0003"+
		"N\'\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009e\u0003N\'\u0000"+
		"\u009d\u0098\u0001\u0000\u0000\u0000\u009d\u009c\u0001\u0000\u0000\u0000"+
		"\u009e\u000f\u0001\u0000\u0000\u0000\u009f\u00a0\u00057\u0000\u0000\u00a0"+
		"\u00a1\u0003N\'\u0000\u00a1\u00a2\u0003\u0012\t\u0000\u00a2\u0011\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a7\u0005\u000b\u0000\u0000\u00a4\u00a6\u0005"+
		"B\u0000\u0000\u00a5\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a9\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a8\u00aa\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ab\u0005@\u0000\u0000\u00ab\u0013\u0001\u0000\u0000"+
		"\u0000\u00ac\u00b0\u0005\"\u0000\u0000\u00ad\u00af\u0005H\u0000\u0000"+
		"\u00ae\u00ad\u0001\u0000\u0000\u0000\u00af\u00b2\u0001\u0000\u0000\u0000"+
		"\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b1\u00b3\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b4\u0005F\u0000\u0000\u00b4\u0015\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b9\u0005!\u0000\u0000\u00b6\u00b8\u0005K\u0000\u0000\u00b7\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b8\u00bb\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001"+
		"\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bc\u0001"+
		"\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005"+
		"I\u0000\u0000\u00bd\u0017\u0001\u0000\u0000\u0000\u00be\u00c2\u0005 \u0000"+
		"\u0000\u00bf\u00c1\u0005E\u0000\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c4\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005C\u0000\u0000\u00c6"+
		"\u0019\u0001\u0000\u0000\u0000\u00c7\u00cb\u0005\n\u0000\u0000\u00c8\u00ca"+
		"\u0005?\u0000\u0000\u00c9\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cd\u0001"+
		"\u0000\u0000\u0000\u00cb\u00c9\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001"+
		"\u0000\u0000\u0000\u00cc\u00ce\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001"+
		"\u0000\u0000\u0000\u00ce\u00cf\u0005=\u0000\u0000\u00cf\u001b\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d2\u0003\u001e\u000f\u0000\u00d1\u00d0\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d5\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u001d\u0001\u0000"+
		"\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000\u00d6\u00d9\u0003 \u0010"+
		"\u0000\u00d7\u00d9\u0003P(\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8"+
		"\u00d7\u0001\u0000\u0000\u0000\u00d9\u001f\u0001\u0000\u0000\u0000\u00da"+
		"\u00dc\u0003&\u0013\u0000\u00db\u00da\u0001\u0000\u0000\u0000\u00db\u00dc"+
		"\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd\u00df"+
		"\u0005\u0002\u0000\u0000\u00de\u00e0\u0003\u001a\r\u0000\u00df\u00de\u0001"+
		"\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00ea\u0001"+
		"\u0000\u0000\u0000\u00e1\u00e2\u0003\"\u0011\u0000\u00e2\u00e3\u0003$"+
		"\u0012\u0000\u00e3\u00eb\u0001\u0000\u0000\u0000\u00e4\u00e6\u0003$\u0012"+
		"\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e8\u0001\u0000\u0000\u0000\u00e7\u00e9\u0003\"\u0011\u0000"+
		"\u00e8\u00e7\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000"+
		"\u00e9\u00eb\u0001\u0000\u0000\u0000\u00ea\u00e1\u0001\u0000\u0000\u0000"+
		"\u00ea\u00e5\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000"+
		"\u00ec\u00ed\u0005#\u0000\u0000\u00ed\u00ee\u0003*\u0015\u0000\u00ee\u00ef"+
		"\u0005&\u0000\u0000\u00ef!\u0001\u0000\u0000\u0000\u00f0\u00f1\u0005\u001a"+
		"\u0000\u0000\u00f1\u00f2\u0003\u001a\r\u0000\u00f2#\u0001\u0000\u0000"+
		"\u0000\u00f3\u00f4\u0005\u001b\u0000\u0000\u00f4\u00f5\u0003\u001a\r\u0000"+
		"\u00f5%\u0001\u0000\u0000\u0000\u00f6\u00f8\u0003(\u0014\u0000\u00f7\u00f6"+
		"\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000\u0000\u00f9\u00f7"+
		"\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\'\u0001"+
		"\u0000\u0000\u0000\u00fb\u00fc\u0007\u0000\u0000\u0000\u00fc)\u0001\u0000"+
		"\u0000\u0000\u00fd\u00fe\u0003,\u0016\u0000\u00fe+\u0001\u0000\u0000\u0000"+
		"\u00ff\u0104\u0003.\u0017\u0000\u0100\u0101\u00053\u0000\u0000\u0101\u0103"+
		"\u0003.\u0017\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0103\u0106\u0001"+
		"\u0000\u0000\u0000\u0104\u0102\u0001\u0000\u0000\u0000\u0104\u0105\u0001"+
		"\u0000\u0000\u0000\u0105-\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000"+
		"\u0000\u0000\u0107\u010a\u00032\u0019\u0000\u0108\u0109\u00058\u0000\u0000"+
		"\u0109\u010b\u0003N\'\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010a"+
		"\u010b\u0001\u0000\u0000\u0000\u010b/\u0001\u0000\u0000\u0000\u010c\u0111"+
		"\u00032\u0019\u0000\u010d\u010e\u00053\u0000\u0000\u010e\u0110\u00032"+
		"\u0019\u0000\u010f\u010d\u0001\u0000\u0000\u0000\u0110\u0113\u0001\u0000"+
		"\u0000\u0000\u0111\u010f\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000"+
		"\u0000\u0000\u01121\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000\u0000"+
		"\u0000\u0114\u0116\u00034\u001a\u0000\u0115\u0114\u0001\u0000\u0000\u0000"+
		"\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0001\u0000\u0000\u0000\u0118\u011b\u0001\u0000\u0000\u0000"+
		"\u0119\u011b\u0001\u0000\u0000\u0000\u011a\u0115\u0001\u0000\u0000\u0000"+
		"\u011a\u0119\u0001\u0000\u0000\u0000\u011b3\u0001\u0000\u0000\u0000\u011c"+
		"\u011e\u00036\u001b\u0000\u011d\u011f\u0003:\u001d\u0000\u011e\u011d\u0001"+
		"\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u012d\u0001"+
		"\u0000\u0000\u0000\u0120\u0122\u0003\u0012\t\u0000\u0121\u0123\u0005/"+
		"\u0000\u0000\u0122\u0121\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000"+
		"\u0000\u0000\u0123\u012d\u0001\u0000\u0000\u0000\u0124\u012d\u0003\u0014"+
		"\n\u0000\u0125\u012d\u0003\u0018\f\u0000\u0126\u012d\u0003\u0016\u000b"+
		"\u0000\u0127\u0129\u0003>\u001f\u0000\u0128\u012a\u0003:\u001d\u0000\u0129"+
		"\u0128\u0001\u0000\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000\u012a"+
		"\u012d\u0001\u0000\u0000\u0000\u012b\u012d\u00038\u001c\u0000\u012c\u011c"+
		"\u0001\u0000\u0000\u0000\u012c\u0120\u0001\u0000\u0000\u0000\u012c\u0124"+
		"\u0001\u0000\u0000\u0000\u012c\u0125\u0001\u0000\u0000\u0000\u012c\u0126"+
		"\u0001\u0000\u0000\u0000\u012c\u0127\u0001\u0000\u0000\u0000\u012c\u012b"+
		"\u0001\u0000\u0000\u0000\u012d5\u0001\u0000\u0000\u0000\u012e\u012f\u0003"+
		"L&\u0000\u012f\u0132\u0007\u0001\u0000\u0000\u0130\u0133\u0003>\u001f"+
		"\u0000\u0131\u0133\u0003F#\u0000\u0132\u0130\u0001\u0000\u0000\u0000\u0132"+
		"\u0131\u0001\u0000\u0000\u0000\u01337\u0001\u0000\u0000\u0000\u0134\u0136"+
		"\u0003F#\u0000\u0135\u0137\u0003:\u001d\u0000\u0136\u0135\u0001\u0000"+
		"\u0000\u0000\u0136\u0137\u0001\u0000\u0000\u0000\u01379\u0001\u0000\u0000"+
		"\u0000\u0138\u0139\u0007\u0002\u0000\u0000\u0139;\u0001\u0000\u0000\u0000"+
		"\u013a\u0143\u0003H$\u0000\u013b\u0143\u0003J%\u0000\u013c\u0143\u0003"+
		"@ \u0000\u013d\u0143\u0005\u0003\u0000\u0000\u013e\u0140\u00054\u0000"+
		"\u0000\u013f\u013e\u0001\u0000\u0000\u0000\u013f\u0140\u0001\u0000\u0000"+
		"\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0143\u0003L&\u0000\u0142"+
		"\u013a\u0001\u0000\u0000\u0000\u0142\u013b\u0001\u0000\u0000\u0000\u0142"+
		"\u013c\u0001\u0000\u0000\u0000\u0142\u013d\u0001\u0000\u0000\u0000\u0142"+
		"\u013f\u0001\u0000\u0000\u0000\u0143=\u0001\u0000\u0000\u0000\u0144\u014b"+
		"\u0003J%\u0000\u0145\u0147\u00054\u0000\u0000\u0146\u0145\u0001\u0000"+
		"\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0148\u0001\u0000"+
		"\u0000\u0000\u0148\u014b\u0003L&\u0000\u0149\u014b\u0003@ \u0000\u014a"+
		"\u0144\u0001\u0000\u0000\u0000\u014a\u0146\u0001\u0000\u0000\u0000\u014a"+
		"\u0149\u0001\u0000\u0000\u0000\u014b?\u0001\u0000\u0000\u0000\u014c\u014d"+
		"\u00059\u0000\u0000\u014d\u0151\u0003D\"\u0000\u014e\u014f\u00059\u0000"+
		"\u0000\u014f\u0151\u0003B!\u0000\u0150\u014c\u0001\u0000\u0000\u0000\u0150"+
		"\u014e\u0001\u0000\u0000\u0000\u0151A\u0001\u0000\u0000\u0000\u0152\u0153"+
		"\u0005\'\u0000\u0000\u0153\u0158\u0003D\"\u0000\u0154\u0155\u00053\u0000"+
		"\u0000\u0155\u0157\u0003D\"\u0000\u0156\u0154\u0001\u0000\u0000\u0000"+
		"\u0157\u015a\u0001\u0000\u0000\u0000\u0158\u0156\u0001\u0000\u0000\u0000"+
		"\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u015b\u0001\u0000\u0000\u0000"+
		"\u015a\u0158\u0001\u0000\u0000\u0000\u015b\u015c\u0005(\u0000\u0000\u015c"+
		"C\u0001\u0000\u0000\u0000\u015d\u0162\u0005\u0001\u0000\u0000\u015e\u0162"+
		"\u0005\b\u0000\u0000\u015f\u0162\u0003H$\u0000\u0160\u0162\u0005\u0003"+
		"\u0000\u0000\u0161\u015d\u0001\u0000\u0000\u0000\u0161\u015e\u0001\u0000"+
		"\u0000\u0000\u0161\u015f\u0001\u0000\u0000\u0000\u0161\u0160\u0001\u0000"+
		"\u0000\u0000\u0162E\u0001\u0000\u0000\u0000\u0163\u0164\u0005\'\u0000"+
		"\u0000\u0164\u0165\u00030\u0018\u0000\u0165\u0166\u0005(\u0000\u0000\u0166"+
		"G\u0001\u0000\u0000\u0000\u0167\u0168\u0005\b\u0000\u0000\u0168\u0169"+
		"\u00055\u0000\u0000\u0169\u016a\u0005\b\u0000\u0000\u016aI\u0001\u0000"+
		"\u0000\u0000\u016b\u016c\u0005\b\u0000\u0000\u016cK\u0001\u0000\u0000"+
		"\u0000\u016d\u016f\u0003N\'\u0000\u016e\u0170\u0003\u001a\r\u0000\u016f"+
		"\u016e\u0001\u0000\u0000\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170"+
		"\u0175\u0001\u0000\u0000\u0000\u0171\u0172\u00056\u0000\u0000\u0172\u0174"+
		"\u0003L&\u0000\u0173\u0171\u0001\u0000\u0000\u0000\u0174\u0177\u0001\u0000"+
		"\u0000\u0000\u0175\u0173\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000"+
		"\u0000\u0000\u0176M\u0001\u0000\u0000\u0000\u0177\u0175\u0001\u0000\u0000"+
		"\u0000\u0178\u0179\u0007\u0003\u0000\u0000\u0179O\u0001\u0000\u0000\u0000"+
		"\u017a\u017c\u0005\u0001\u0000\u0000\u017b\u017d\u0003\u0006\u0003\u0000"+
		"\u017c\u017b\u0001\u0000\u0000\u0000\u017c\u017d\u0001\u0000\u0000\u0000"+
		"\u017d\u017e\u0001\u0000\u0000\u0000\u017e\u017f\u0005#\u0000\u0000\u017f"+
		"\u0180\u0003R)\u0000\u0180\u0181\u0005&\u0000\u0000\u0181Q\u0001\u0000"+
		"\u0000\u0000\u0182\u0183\u0003T*\u0000\u0183S\u0001\u0000\u0000\u0000"+
		"\u0184\u0189\u0003V+\u0000\u0185\u0186\u00053\u0000\u0000\u0186\u0188"+
		"\u0003V+\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0188\u018b\u0001\u0000"+
		"\u0000\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189\u018a\u0001\u0000"+
		"\u0000\u0000\u018aU\u0001\u0000\u0000\u0000\u018b\u0189\u0001\u0000\u0000"+
		"\u0000\u018c\u018f\u0003X,\u0000\u018d\u018f\u0001\u0000\u0000\u0000\u018e"+
		"\u018c\u0001\u0000\u0000\u0000\u018e\u018d\u0001\u0000\u0000\u0000\u018f"+
		"W\u0001\u0000\u0000\u0000\u0190\u0192\u0003Z-\u0000\u0191\u0190\u0001"+
		"\u0000\u0000\u0000\u0192\u0193\u0001\u0000\u0000\u0000\u0193\u0191\u0001"+
		"\u0000\u0000\u0000\u0193\u0194\u0001\u0000\u0000\u0000\u0194\u0197\u0001"+
		"\u0000\u0000\u0000\u0195\u0197\u0001\u0000\u0000\u0000\u0196\u0191\u0001"+
		"\u0000\u0000\u0000\u0196\u0195\u0001\u0000\u0000\u0000\u0197Y\u0001\u0000"+
		"\u0000\u0000\u0198\u019a\u0003\u0012\t\u0000\u0199\u019b\u0005/\u0000"+
		"\u0000\u019a\u0199\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000"+
		"\u0000\u019b\u01a9\u0001\u0000\u0000\u0000\u019c\u01a9\u0003\u0014\n\u0000"+
		"\u019d\u01a9\u0003\u0018\f\u0000\u019e\u01a9\u0003\u0016\u000b\u0000\u019f"+
		"\u01a9\u00036\u001b\u0000\u01a0\u01a2\u0003<\u001e\u0000\u01a1\u01a3\u0003"+
		":\u001d\u0000\u01a2\u01a1\u0001\u0000\u0000\u0000\u01a2\u01a3\u0001\u0000"+
		"\u0000\u0000\u01a3\u01a9\u0001\u0000\u0000\u0000\u01a4\u01a6\u0003\\."+
		"\u0000\u01a5\u01a7\u0003:\u001d\u0000\u01a6\u01a5\u0001\u0000\u0000\u0000"+
		"\u01a6\u01a7\u0001\u0000\u0000\u0000\u01a7\u01a9\u0001\u0000\u0000\u0000"+
		"\u01a8\u0198\u0001\u0000\u0000\u0000\u01a8\u019c\u0001\u0000\u0000\u0000"+
		"\u01a8\u019d\u0001\u0000\u0000\u0000\u01a8\u019e\u0001\u0000\u0000\u0000"+
		"\u01a8\u019f\u0001\u0000\u0000\u0000\u01a8\u01a0\u0001\u0000\u0000\u0000"+
		"\u01a8\u01a4\u0001\u0000\u0000\u0000\u01a9[\u0001\u0000\u0000\u0000\u01aa"+
		"\u01ab\u0005\'\u0000\u0000\u01ab\u01ac\u0003T*\u0000\u01ac\u01ad\u0005"+
		"(\u0000\u0000\u01ad]\u0001\u0000\u0000\u00001bow\u0085\u008b\u0093\u009d"+
		"\u00a7\u00b0\u00b9\u00c2\u00cb\u00d3\u00d8\u00db\u00df\u00e5\u00e8\u00ea"+
		"\u00f9\u0104\u010a\u0111\u0117\u011a\u011e\u0122\u0129\u012c\u0132\u0136"+
		"\u013f\u0142\u0146\u014a\u0150\u0158\u0161\u016f\u0175\u017c\u0189\u018e"+
		"\u0193\u0196\u019a\u01a2\u01a6\u01a8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}