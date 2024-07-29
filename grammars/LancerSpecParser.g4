/*
 * [The "BSD license"]
 *  Copyright (c) 2012-2014 Terence Parr
 *  Copyright (c) 2012-2014 Sam Harwell
 *  Copyright (c) 2015 Gerald Rosenberg
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*	A grammar for ANTLR v4 written in ANTLR v4.
 *
 *	Modified 2015.06.16 gbr
 *	-- update for compatibility with Antlr v4.5
 *	-- add mode for channels
 *	-- moved members to LexerAdaptor
 * 	-- move fragments to imports
 */
parser grammar LancerSpecParser;


options { tokenVocab = LancerSpecLexer; }
// The main entry point for parsing a v4 grammar.
grammarSpec
   : grammarDecl prequelConstruct* rules EOF
   ;

grammarDecl
   : GRAMMAR identifier SEMI
   ;


   // This is the list of all constructs that can be declared before
   // the set of rules that compose the grammar, and is invoked 0..n
   // times by the grammarPrequel rule.

prequelConstruct
   : optionsSpec //confirm to be needed
   | delegateGrammars //confirm to be needed
   | tokensSpec 
   | action_ 
   ;
   // ------------
   // Options - things that affect analysis and/or code generation

//confirm to be needed
optionsSpec
   : OPTIONS (option SEMI)* RBRACE
   ;

//confirm to be needed
option
   : identifier ASSIGN optionValue
   ;

optionValue
   : identifier (DOT identifier)*
   | STRING_LITERAL
   | actionBlock
   | INT
   ;
   // ------------
   // Delegates

//confirm to be needed
delegateGrammars
   : IMPORT delegateGrammar (COMMA delegateGrammar)* SEMI
   ;

//confirm to be needed
delegateGrammar
   : identifier ASSIGN identifier
   | identifier
   ;
   // ------------
   // Tokens & Channels

tokensSpec
   : TOKENS idList? RBRACE
   ;


idList
   : identifier (COMMA identifier)* COMMA?
   ;

// Match stuff like @members {int i;}
action_
   : AT identifier actionBlock
   ;

actionBlock
   : BEGIN_ACTION ACTION_CONTENT* END_ACTION
   ;

weightBlock
   : BEGIN_WHT WGHT_CONTENT* END_WGHT_DECL
   ;


repetitionBlock
   : BEGIN_REP REP_CONTENT* END_REP_DECL
   ;

errorBlock
   : BEGIN_ERR ERR_CONTENT* END_ERR_DECL
   ;

argActionBlock
   : BEGIN_ARGUMENT ARGUMENT_CONTENT* END_ARGUMENT
   ;

rules
   : ruleSpec*
   ;

ruleSpec
   : parserRuleSpec
   | lexerRuleSpec
   ;

parserRuleSpec
   : ruleModifiers? RULE_REF argActionBlock?  (ruleReturns localsSpec | localsSpec? ruleReturns?) COLON ruleBlock SEMI
   ;



ruleReturns
   : RETURNS argActionBlock
   ;


localsSpec
   : LOCALS argActionBlock
   ;



ruleModifiers
   : ruleModifier+
   ;
   // An individual access modifier for a rule. The 'fragment' modifier
   // is an internal indication for lexer rules that they do not match
   // from the input but are like subroutines for other lexer rules to
   // reuse for certain lexical patterns. The other modifiers are passed
   // to the code generation templates and may be ignored by the template
   // if they are of no use in that language.

ruleModifier
   : SCHEMA
   | EXPR
   | STATEMENT
   ;

ruleBlock
   : ruleAltList
   ;

ruleAltList
   : labeledAlt (OR labeledAlt)*
   ;

labeledAlt
   : alternative (POUND identifier)?
   ;
   // --------------------
   // Lexer rules

lexerRuleSpec
   : TOKEN_REF optionsSpec? COLON lexerRuleBlock SEMI
   ;

lexerRuleBlock
   : lexerAltList
   ;

lexerAltList
   : lexerAlt (OR lexerAlt)*
   ;

lexerAlt
   : lexerElements
   |
   // explicitly allow empty alts
   ;

lexerElements
   : lexerElement+
   |
   ;

lexerElement
   : actionBlock QUESTION?
   | weightBlock
   | errorBlock
   | repetitionBlock
   | variableAccess
   | variableAssignment
   | lexerAtom ebnfSuffix?
   | lexerBlock ebnfSuffix?
   
   ;
   // but preds can be anywhere

lexerBlock
   : LPAREN lexerAltList RPAREN
   ;
   

altList
   : alternative (OR alternative)*
   ;

alternative
   : element+
   |
   // explicitly allow empty alts
   ;

variableAccess
   : DOLLAR ID
   ;

element
   : variableAssignment (ebnfSuffix |)
   | variableAccess
   | actionBlock QUESTION?
   | weightBlock
   | errorBlock
   | repetitionBlock
   | atom (ebnfSuffix |)
   | ebnf
   ;

variableAssignment
   : labeledElement
   ;

labeledElement
   : compIdentifier (ASSIGN | PLUS_ASSIGN) (atom | block)
   ;
   // --------------------
   // EBNF and blocks

ebnf
   : block blockSuffix?
   ;

blockSuffix
   : ebnfSuffix
   ;

//nongreedy matching are no longer supported
ebnfSuffix
   : QUESTION
   | STAR
   | PLUS
   ;

lexerAtom
   : characterRange
   | terminal
   | notSet
   | LEXER_CHAR_SET
   ;

atom
   : terminal
   | ruleref
   | notSet
   ;

// --------------------
// Inverted element set
//confirm to be needed
notSet
   : NOT setElement
   | NOT blockSet
   ;

blockSet
   : LPAREN setElement (OR setElement)* RPAREN
   ;

setElement
   : TOKEN_REF
   | STRING_LITERAL 
   | characterRange
   | LEXER_CHAR_SET
   ;

// -------------
// Grammar Block
block
   : LPAREN altList RPAREN
   ;

// ----------------
// Parser rule ref
ruleref
   : RULE_REF argActionBlock?
   ;

// ---------------
// Character Range
characterRange
   : STRING_LITERAL RANGE STRING_LITERAL
   ;

terminal
   : TOKEN_REF
   | STRING_LITERAL
   ;

compIdentifier
   : identifier argActionBlock? ( DOT compIdentifier)*
   ;

identifier
   : RULE_REF
   | TOKEN_REF
   ;
   
