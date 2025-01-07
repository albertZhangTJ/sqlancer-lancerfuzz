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
   | action_ 
   | tokensSpec
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
   | INT_LITERAL
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



// Match stuff like @members {int i;}
action_
   : AT identifier actionBlock
   ;

tokensSpec
   : TOKENS idList? RBRACE
   ;

actionBlock
   : BEGIN_ACTION ACTION_CONTENT* END_ACTION
   ;

idList
   : identifier (COMMA identifier)* COMMA?
   ;

argActionBlock
   : LBRACK arg (COMMA arg)* RBRACK
   ;

arg
   : expression
   ;

rules
   : ruleSpec*
   ;

ruleSpec
   : parserRuleSpec
   | lexerRuleSpec
   ;

parserRuleSpec
   : ruleModifier? RULE_REF argActionBlock? ruleReturns? COLON altList SEMI
   ;



ruleReturns
   : RETURNS argActionBlock
   ;

ruleModifier
   : FRAGMENT
   ;


altList
   : alternative (OR alternative)*
   ;

alternative
   : element+
   |
   // explicitly allow empty alts
   ;

element
   : actionBlock
   | predicate
   | weightage
   | precedence
   | arg ebnfSuffix?
   | ebnf //non-root alternation nodes, parenthesized repetitions, and basically just any blocks
   ;

predicate
   : LT arg GT
   | actionBlock QUESTION
   ;

//should a expression be suppressed, it is done here
//I seriously cannot think of a use case for silencing w/o assignment
//so this shld be fine for now
expression
   : mexpr ((ASSIGN | PLUS_ASSIGN) DOLLAR? mexpr)*
   ;

mexpr
   : lexpr ((ASSIGN ASSIGN | NEGATE ASSIGN | GT ASSIGN | LT ASSIGN | GT | LT) lexpr)*
   ;

lexpr
   : variable ((PLUS | DASH) variable)*
   ;
   // --------------------
   // EBNF and blocks

variable
   : compIdentifier
   | STRING_LITERAL
   | INT_LITERAL
   | BOOL_LITERAL
   ;

ebnf
   : block ebnfSuffix?
   ;

//nongreedy matching are no longer supported
ebnfSuffix
   : QUESTION
   | STAR
   | PLUS
   | STAR STAR // for customized listing support
   ;

lexerAtom
   : characterRange 
   | terminal
   | notSet
   | LEXER_CHAR_SET
   ;

atom
   : terminal
   | notSet
   | precedence
   | weightage
   ;

weightage
   : INT_LITERAL PERCENTAGE
   ;

precedence
   : AT INT_LITERAL
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
   : identifier
   | STRING_LITERAL 
   | characterRange
   | LEXER_CHAR_SET
   ;

// -------------
// Grammar Block
block
   : LPAREN altList RPAREN
   ;


// ---------------
// Character Range
characterRange
   : STRING_LITERAL RANGE STRING_LITERAL
   ;

terminal
   : STRING_LITERAL
   ;

compIdentifier
   : identifier argActionBlock? ( DOT compIdentifier)*
   ;

identifier
   : RULE_REF
   | TOKEN_REF
   ;
   


lexerRuleSpec
   : ruleModifier? TOKEN_REF COLON lexerAltList SEMI
   ;

lexerAltList
   : lexerAlt (OR lexerAlt)*
   ;

lexerAlt
   : lexerElement+
   |
   // explicitly allow empty alts
   ;

lexerElement
   : actionBlock QUESTION?
   | expression
   | lexerAtom ebnfSuffix?
   | lexerBlock ebnfSuffix?
   | charSet
   ;
   // but preds can be anywhere

lexerBlock
   : LPAREN lexerAltList RPAREN
   ;

charSet
   : LBRACK charSetContent RBRACK
   ;

charSetContent
   : (~(RBRACK) | ESC)+
   ;

grammarOperator
   : ASSIGN ASSIGN
   | NEGATE ASSIGN
   | PLUS ASSIGN
   | PLUS
   | GT ASSIGN
   | LT ASSIGN
   | GT
   | LT
   | ASSIGN
   ;