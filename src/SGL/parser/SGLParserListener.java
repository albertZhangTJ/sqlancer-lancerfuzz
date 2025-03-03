package SGL.parser;
// Generated from SGLParser.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SGLParser}.
 */
public interface SGLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SGLParser#grammarSpec}.
	 * @param ctx the parse tree
	 */
	void enterGrammarSpec(SGLParser.GrammarSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#grammarSpec}.
	 * @param ctx the parse tree
	 */
	void exitGrammarSpec(SGLParser.GrammarSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#grammarDecl}.
	 * @param ctx the parse tree
	 */
	void enterGrammarDecl(SGLParser.GrammarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#grammarDecl}.
	 * @param ctx the parse tree
	 */
	void exitGrammarDecl(SGLParser.GrammarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#prequelConstruct}.
	 * @param ctx the parse tree
	 */
	void enterPrequelConstruct(SGLParser.PrequelConstructContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#prequelConstruct}.
	 * @param ctx the parse tree
	 */
	void exitPrequelConstruct(SGLParser.PrequelConstructContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#optionsSpec}.
	 * @param ctx the parse tree
	 */
	void enterOptionsSpec(SGLParser.OptionsSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#optionsSpec}.
	 * @param ctx the parse tree
	 */
	void exitOptionsSpec(SGLParser.OptionsSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(SGLParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(SGLParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#optionValue}.
	 * @param ctx the parse tree
	 */
	void enterOptionValue(SGLParser.OptionValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#optionValue}.
	 * @param ctx the parse tree
	 */
	void exitOptionValue(SGLParser.OptionValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#delegateGrammars}.
	 * @param ctx the parse tree
	 */
	void enterDelegateGrammars(SGLParser.DelegateGrammarsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#delegateGrammars}.
	 * @param ctx the parse tree
	 */
	void exitDelegateGrammars(SGLParser.DelegateGrammarsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#delegateGrammar}.
	 * @param ctx the parse tree
	 */
	void enterDelegateGrammar(SGLParser.DelegateGrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#delegateGrammar}.
	 * @param ctx the parse tree
	 */
	void exitDelegateGrammar(SGLParser.DelegateGrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#action_}.
	 * @param ctx the parse tree
	 */
	void enterAction_(SGLParser.Action_Context ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#action_}.
	 * @param ctx the parse tree
	 */
	void exitAction_(SGLParser.Action_Context ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#tokensSpec}.
	 * @param ctx the parse tree
	 */
	void enterTokensSpec(SGLParser.TokensSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#tokensSpec}.
	 * @param ctx the parse tree
	 */
	void exitTokensSpec(SGLParser.TokensSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#actionBlock}.
	 * @param ctx the parse tree
	 */
	void enterActionBlock(SGLParser.ActionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#actionBlock}.
	 * @param ctx the parse tree
	 */
	void exitActionBlock(SGLParser.ActionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(SGLParser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(SGLParser.IdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#argActionBlock}.
	 * @param ctx the parse tree
	 */
	void enterArgActionBlock(SGLParser.ArgActionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#argActionBlock}.
	 * @param ctx the parse tree
	 */
	void exitArgActionBlock(SGLParser.ArgActionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(SGLParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(SGLParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(SGLParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(SGLParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#ruleSpec}.
	 * @param ctx the parse tree
	 */
	void enterRuleSpec(SGLParser.RuleSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#ruleSpec}.
	 * @param ctx the parse tree
	 */
	void exitRuleSpec(SGLParser.RuleSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#parserRuleSpec}.
	 * @param ctx the parse tree
	 */
	void enterParserRuleSpec(SGLParser.ParserRuleSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#parserRuleSpec}.
	 * @param ctx the parse tree
	 */
	void exitParserRuleSpec(SGLParser.ParserRuleSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#ruleReturns}.
	 * @param ctx the parse tree
	 */
	void enterRuleReturns(SGLParser.RuleReturnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#ruleReturns}.
	 * @param ctx the parse tree
	 */
	void exitRuleReturns(SGLParser.RuleReturnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#ruleModifier}.
	 * @param ctx the parse tree
	 */
	void enterRuleModifier(SGLParser.RuleModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#ruleModifier}.
	 * @param ctx the parse tree
	 */
	void exitRuleModifier(SGLParser.RuleModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#altList}.
	 * @param ctx the parse tree
	 */
	void enterAltList(SGLParser.AltListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#altList}.
	 * @param ctx the parse tree
	 */
	void exitAltList(SGLParser.AltListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#alternative}.
	 * @param ctx the parse tree
	 */
	void enterAlternative(SGLParser.AlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#alternative}.
	 * @param ctx the parse tree
	 */
	void exitAlternative(SGLParser.AlternativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(SGLParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(SGLParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(SGLParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(SGLParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SGLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SGLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#expr_op}.
	 * @param ctx the parse tree
	 */
	void enterExpr_op(SGLParser.Expr_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#expr_op}.
	 * @param ctx the parse tree
	 */
	void exitExpr_op(SGLParser.Expr_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#mexpr}.
	 * @param ctx the parse tree
	 */
	void enterMexpr(SGLParser.MexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#mexpr}.
	 * @param ctx the parse tree
	 */
	void exitMexpr(SGLParser.MexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#mexpr_op}.
	 * @param ctx the parse tree
	 */
	void enterMexpr_op(SGLParser.Mexpr_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#mexpr_op}.
	 * @param ctx the parse tree
	 */
	void exitMexpr_op(SGLParser.Mexpr_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#lexpr}.
	 * @param ctx the parse tree
	 */
	void enterLexpr(SGLParser.LexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#lexpr}.
	 * @param ctx the parse tree
	 */
	void exitLexpr(SGLParser.LexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#lexpr_op}.
	 * @param ctx the parse tree
	 */
	void enterLexpr_op(SGLParser.Lexpr_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#lexpr_op}.
	 * @param ctx the parse tree
	 */
	void exitLexpr_op(SGLParser.Lexpr_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(SGLParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(SGLParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#ebnf}.
	 * @param ctx the parse tree
	 */
	void enterEbnf(SGLParser.EbnfContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#ebnf}.
	 * @param ctx the parse tree
	 */
	void exitEbnf(SGLParser.EbnfContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#ebnfSuffix}.
	 * @param ctx the parse tree
	 */
	void enterEbnfSuffix(SGLParser.EbnfSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#ebnfSuffix}.
	 * @param ctx the parse tree
	 */
	void exitEbnfSuffix(SGLParser.EbnfSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#lexerAtom}.
	 * @param ctx the parse tree
	 */
	void enterLexerAtom(SGLParser.LexerAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#lexerAtom}.
	 * @param ctx the parse tree
	 */
	void exitLexerAtom(SGLParser.LexerAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#weightage}.
	 * @param ctx the parse tree
	 */
	void enterWeightage(SGLParser.WeightageContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#weightage}.
	 * @param ctx the parse tree
	 */
	void exitWeightage(SGLParser.WeightageContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#precedence}.
	 * @param ctx the parse tree
	 */
	void enterPrecedence(SGLParser.PrecedenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#precedence}.
	 * @param ctx the parse tree
	 */
	void exitPrecedence(SGLParser.PrecedenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#blockSet}.
	 * @param ctx the parse tree
	 */
	void enterBlockSet(SGLParser.BlockSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#blockSet}.
	 * @param ctx the parse tree
	 */
	void exitBlockSet(SGLParser.BlockSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#setElement}.
	 * @param ctx the parse tree
	 */
	void enterSetElement(SGLParser.SetElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#setElement}.
	 * @param ctx the parse tree
	 */
	void exitSetElement(SGLParser.SetElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SGLParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SGLParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#characterRange}.
	 * @param ctx the parse tree
	 */
	void enterCharacterRange(SGLParser.CharacterRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#characterRange}.
	 * @param ctx the parse tree
	 */
	void exitCharacterRange(SGLParser.CharacterRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#terminal}.
	 * @param ctx the parse tree
	 */
	void enterTerminal(SGLParser.TerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#terminal}.
	 * @param ctx the parse tree
	 */
	void exitTerminal(SGLParser.TerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#compIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterCompIdentifier(SGLParser.CompIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#compIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitCompIdentifier(SGLParser.CompIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(SGLParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(SGLParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#lexerRuleSpec}.
	 * @param ctx the parse tree
	 */
	void enterLexerRuleSpec(SGLParser.LexerRuleSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#lexerRuleSpec}.
	 * @param ctx the parse tree
	 */
	void exitLexerRuleSpec(SGLParser.LexerRuleSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#lexerAltList}.
	 * @param ctx the parse tree
	 */
	void enterLexerAltList(SGLParser.LexerAltListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#lexerAltList}.
	 * @param ctx the parse tree
	 */
	void exitLexerAltList(SGLParser.LexerAltListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#lexerAlt}.
	 * @param ctx the parse tree
	 */
	void enterLexerAlt(SGLParser.LexerAltContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#lexerAlt}.
	 * @param ctx the parse tree
	 */
	void exitLexerAlt(SGLParser.LexerAltContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#lexerElement}.
	 * @param ctx the parse tree
	 */
	void enterLexerElement(SGLParser.LexerElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#lexerElement}.
	 * @param ctx the parse tree
	 */
	void exitLexerElement(SGLParser.LexerElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#lexerBlock}.
	 * @param ctx the parse tree
	 */
	void enterLexerBlock(SGLParser.LexerBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#lexerBlock}.
	 * @param ctx the parse tree
	 */
	void exitLexerBlock(SGLParser.LexerBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#charSet}.
	 * @param ctx the parse tree
	 */
	void enterCharSet(SGLParser.CharSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#charSet}.
	 * @param ctx the parse tree
	 */
	void exitCharSet(SGLParser.CharSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SGLParser#charSetContent}.
	 * @param ctx the parse tree
	 */
	void enterCharSetContent(SGLParser.CharSetContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SGLParser#charSetContent}.
	 * @param ctx the parse tree
	 */
	void exitCharSetContent(SGLParser.CharSetContentContext ctx);
}