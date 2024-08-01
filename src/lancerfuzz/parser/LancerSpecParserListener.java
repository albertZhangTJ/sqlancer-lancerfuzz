package lancerfuzz.parser;
// Generated from grammars/LancerSpecParser.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LancerSpecParser}.
 */
public interface LancerSpecParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#grammarSpec}.
	 * @param ctx the parse tree
	 */
	void enterGrammarSpec(LancerSpecParser.GrammarSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#grammarSpec}.
	 * @param ctx the parse tree
	 */
	void exitGrammarSpec(LancerSpecParser.GrammarSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#grammarDecl}.
	 * @param ctx the parse tree
	 */
	void enterGrammarDecl(LancerSpecParser.GrammarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#grammarDecl}.
	 * @param ctx the parse tree
	 */
	void exitGrammarDecl(LancerSpecParser.GrammarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#prequelConstruct}.
	 * @param ctx the parse tree
	 */
	void enterPrequelConstruct(LancerSpecParser.PrequelConstructContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#prequelConstruct}.
	 * @param ctx the parse tree
	 */
	void exitPrequelConstruct(LancerSpecParser.PrequelConstructContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#optionsSpec}.
	 * @param ctx the parse tree
	 */
	void enterOptionsSpec(LancerSpecParser.OptionsSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#optionsSpec}.
	 * @param ctx the parse tree
	 */
	void exitOptionsSpec(LancerSpecParser.OptionsSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(LancerSpecParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(LancerSpecParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#optionValue}.
	 * @param ctx the parse tree
	 */
	void enterOptionValue(LancerSpecParser.OptionValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#optionValue}.
	 * @param ctx the parse tree
	 */
	void exitOptionValue(LancerSpecParser.OptionValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#delegateGrammars}.
	 * @param ctx the parse tree
	 */
	void enterDelegateGrammars(LancerSpecParser.DelegateGrammarsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#delegateGrammars}.
	 * @param ctx the parse tree
	 */
	void exitDelegateGrammars(LancerSpecParser.DelegateGrammarsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#delegateGrammar}.
	 * @param ctx the parse tree
	 */
	void enterDelegateGrammar(LancerSpecParser.DelegateGrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#delegateGrammar}.
	 * @param ctx the parse tree
	 */
	void exitDelegateGrammar(LancerSpecParser.DelegateGrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#action_}.
	 * @param ctx the parse tree
	 */
	void enterAction_(LancerSpecParser.Action_Context ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#action_}.
	 * @param ctx the parse tree
	 */
	void exitAction_(LancerSpecParser.Action_Context ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#actionBlock}.
	 * @param ctx the parse tree
	 */
	void enterActionBlock(LancerSpecParser.ActionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#actionBlock}.
	 * @param ctx the parse tree
	 */
	void exitActionBlock(LancerSpecParser.ActionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#weightBlock}.
	 * @param ctx the parse tree
	 */
	void enterWeightBlock(LancerSpecParser.WeightBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#weightBlock}.
	 * @param ctx the parse tree
	 */
	void exitWeightBlock(LancerSpecParser.WeightBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#repetitionBlock}.
	 * @param ctx the parse tree
	 */
	void enterRepetitionBlock(LancerSpecParser.RepetitionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#repetitionBlock}.
	 * @param ctx the parse tree
	 */
	void exitRepetitionBlock(LancerSpecParser.RepetitionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#errorBlock}.
	 * @param ctx the parse tree
	 */
	void enterErrorBlock(LancerSpecParser.ErrorBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#errorBlock}.
	 * @param ctx the parse tree
	 */
	void exitErrorBlock(LancerSpecParser.ErrorBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#argActionBlock}.
	 * @param ctx the parse tree
	 */
	void enterArgActionBlock(LancerSpecParser.ArgActionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#argActionBlock}.
	 * @param ctx the parse tree
	 */
	void exitArgActionBlock(LancerSpecParser.ArgActionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(LancerSpecParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(LancerSpecParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#ruleSpec}.
	 * @param ctx the parse tree
	 */
	void enterRuleSpec(LancerSpecParser.RuleSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#ruleSpec}.
	 * @param ctx the parse tree
	 */
	void exitRuleSpec(LancerSpecParser.RuleSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#parserRuleSpec}.
	 * @param ctx the parse tree
	 */
	void enterParserRuleSpec(LancerSpecParser.ParserRuleSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#parserRuleSpec}.
	 * @param ctx the parse tree
	 */
	void exitParserRuleSpec(LancerSpecParser.ParserRuleSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#ruleReturns}.
	 * @param ctx the parse tree
	 */
	void enterRuleReturns(LancerSpecParser.RuleReturnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#ruleReturns}.
	 * @param ctx the parse tree
	 */
	void exitRuleReturns(LancerSpecParser.RuleReturnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#localsSpec}.
	 * @param ctx the parse tree
	 */
	void enterLocalsSpec(LancerSpecParser.LocalsSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#localsSpec}.
	 * @param ctx the parse tree
	 */
	void exitLocalsSpec(LancerSpecParser.LocalsSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#ruleModifiers}.
	 * @param ctx the parse tree
	 */
	void enterRuleModifiers(LancerSpecParser.RuleModifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#ruleModifiers}.
	 * @param ctx the parse tree
	 */
	void exitRuleModifiers(LancerSpecParser.RuleModifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#ruleModifier}.
	 * @param ctx the parse tree
	 */
	void enterRuleModifier(LancerSpecParser.RuleModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#ruleModifier}.
	 * @param ctx the parse tree
	 */
	void exitRuleModifier(LancerSpecParser.RuleModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#ruleBlock}.
	 * @param ctx the parse tree
	 */
	void enterRuleBlock(LancerSpecParser.RuleBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#ruleBlock}.
	 * @param ctx the parse tree
	 */
	void exitRuleBlock(LancerSpecParser.RuleBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#ruleAltList}.
	 * @param ctx the parse tree
	 */
	void enterRuleAltList(LancerSpecParser.RuleAltListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#ruleAltList}.
	 * @param ctx the parse tree
	 */
	void exitRuleAltList(LancerSpecParser.RuleAltListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#labeledAlt}.
	 * @param ctx the parse tree
	 */
	void enterLabeledAlt(LancerSpecParser.LabeledAltContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#labeledAlt}.
	 * @param ctx the parse tree
	 */
	void exitLabeledAlt(LancerSpecParser.LabeledAltContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#altList}.
	 * @param ctx the parse tree
	 */
	void enterAltList(LancerSpecParser.AltListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#altList}.
	 * @param ctx the parse tree
	 */
	void exitAltList(LancerSpecParser.AltListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#alternative}.
	 * @param ctx the parse tree
	 */
	void enterAlternative(LancerSpecParser.AlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#alternative}.
	 * @param ctx the parse tree
	 */
	void exitAlternative(LancerSpecParser.AlternativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(LancerSpecParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(LancerSpecParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignment(LancerSpecParser.VariableAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignment(LancerSpecParser.VariableAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#ebnf}.
	 * @param ctx the parse tree
	 */
	void enterEbnf(LancerSpecParser.EbnfContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#ebnf}.
	 * @param ctx the parse tree
	 */
	void exitEbnf(LancerSpecParser.EbnfContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#ebnfSuffix}.
	 * @param ctx the parse tree
	 */
	void enterEbnfSuffix(LancerSpecParser.EbnfSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#ebnfSuffix}.
	 * @param ctx the parse tree
	 */
	void exitEbnfSuffix(LancerSpecParser.EbnfSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#lexerAtom}.
	 * @param ctx the parse tree
	 */
	void enterLexerAtom(LancerSpecParser.LexerAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#lexerAtom}.
	 * @param ctx the parse tree
	 */
	void exitLexerAtom(LancerSpecParser.LexerAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(LancerSpecParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(LancerSpecParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#notSet}.
	 * @param ctx the parse tree
	 */
	void enterNotSet(LancerSpecParser.NotSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#notSet}.
	 * @param ctx the parse tree
	 */
	void exitNotSet(LancerSpecParser.NotSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#blockSet}.
	 * @param ctx the parse tree
	 */
	void enterBlockSet(LancerSpecParser.BlockSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#blockSet}.
	 * @param ctx the parse tree
	 */
	void exitBlockSet(LancerSpecParser.BlockSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#setElement}.
	 * @param ctx the parse tree
	 */
	void enterSetElement(LancerSpecParser.SetElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#setElement}.
	 * @param ctx the parse tree
	 */
	void exitSetElement(LancerSpecParser.SetElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(LancerSpecParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(LancerSpecParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#characterRange}.
	 * @param ctx the parse tree
	 */
	void enterCharacterRange(LancerSpecParser.CharacterRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#characterRange}.
	 * @param ctx the parse tree
	 */
	void exitCharacterRange(LancerSpecParser.CharacterRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#terminal}.
	 * @param ctx the parse tree
	 */
	void enterTerminal(LancerSpecParser.TerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#terminal}.
	 * @param ctx the parse tree
	 */
	void exitTerminal(LancerSpecParser.TerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#compIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterCompIdentifier(LancerSpecParser.CompIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#compIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitCompIdentifier(LancerSpecParser.CompIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(LancerSpecParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(LancerSpecParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#lexerRuleSpec}.
	 * @param ctx the parse tree
	 */
	void enterLexerRuleSpec(LancerSpecParser.LexerRuleSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#lexerRuleSpec}.
	 * @param ctx the parse tree
	 */
	void exitLexerRuleSpec(LancerSpecParser.LexerRuleSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#lexerRuleBlock}.
	 * @param ctx the parse tree
	 */
	void enterLexerRuleBlock(LancerSpecParser.LexerRuleBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#lexerRuleBlock}.
	 * @param ctx the parse tree
	 */
	void exitLexerRuleBlock(LancerSpecParser.LexerRuleBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#lexerAltList}.
	 * @param ctx the parse tree
	 */
	void enterLexerAltList(LancerSpecParser.LexerAltListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#lexerAltList}.
	 * @param ctx the parse tree
	 */
	void exitLexerAltList(LancerSpecParser.LexerAltListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#lexerAlt}.
	 * @param ctx the parse tree
	 */
	void enterLexerAlt(LancerSpecParser.LexerAltContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#lexerAlt}.
	 * @param ctx the parse tree
	 */
	void exitLexerAlt(LancerSpecParser.LexerAltContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#lexerElements}.
	 * @param ctx the parse tree
	 */
	void enterLexerElements(LancerSpecParser.LexerElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#lexerElements}.
	 * @param ctx the parse tree
	 */
	void exitLexerElements(LancerSpecParser.LexerElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#lexerElement}.
	 * @param ctx the parse tree
	 */
	void enterLexerElement(LancerSpecParser.LexerElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#lexerElement}.
	 * @param ctx the parse tree
	 */
	void exitLexerElement(LancerSpecParser.LexerElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LancerSpecParser#lexerBlock}.
	 * @param ctx the parse tree
	 */
	void enterLexerBlock(LancerSpecParser.LexerBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LancerSpecParser#lexerBlock}.
	 * @param ctx the parse tree
	 */
	void exitLexerBlock(LancerSpecParser.LexerBlockContext ctx);
}