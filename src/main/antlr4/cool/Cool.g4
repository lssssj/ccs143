grammar Cool;



//@header {
//    // 在这里可以编写 Java 代码
//    // 例如，定义变量、方法等
//    package cool;
//}


/*
	Add Grammar rules and appropriate actions for building AST below.
*/

program
  : class_list EOF
	;

/* [[class]]+ */
class_list
	:
		(class_ SEMICOLON)+
	;

class_  :
	/* class TYPE { [[feature]]* }	*/
	st=CLASS type=TYPEID LBRACE fl=feature_list RBRACE


	/* class TYPE [inherits TYPE] { [[feature]]* }	*/
	| st=CLASS type=TYPEID INHERITS p_type=TYPEID LBRACE fl=feature_list RBRACE
	;

feature_list
	:
	(c = feature SEMICOLON )*
  ;

feature  :
	/* ID: TYPE */
	func = method
	| var = attr
	;

method  :
	id=OBJECTID LPAREN RPAREN COLON type=TYPEID LBRACE expr=expression RBRACE
	| id=OBJECTID LPAREN fr_list=formal_list RPAREN COLON type=TYPEID LBRACE expr=expression RBRACE
	;

formal_list
	:
		c = formal (COMMA y = formal )*
	;


formal:
	id=OBJECTID COLON type=TYPEID
	;


/* [expr [[, expr]]* ] */
expression_list
	:
		( expr = expression (COMMA expr = expression)* )?
	;


expression  :
  /*expr.ID([expr [[, expr]]* ]) */
	expr=expression DOT id=OBJECTID LPAREN expr_list = expression_list RPAREN #invokeExpression

	/*expr[@TYPE].ID([expr [[, expr]]* ]) */
	| expr=expression ATSYM type=TYPEID DOT id=OBJECTID LPAREN expr_list = expression_list RPAREN #staticInvokeExpression

  /*ID ( [expr [[, expr]]* ]) */
  | id=OBJECTID LPAREN expr_list = expression_list RPAREN #expressionList

  /* ~ expr */
 	| st=NEGATE e1=expression #negateExpression

  /* isvoid expr */
  | st=ISVOID expr=expression #isVoidExpression

 	/* expr * expr */
  | e1=expression STAR e2=expression   #mulExpression

	/* expr / expr */
	| e1=expression SLASH e2=expression #divideExpression

	/* expr + expr */
	| e1=expression PLUS e2=expression #addExpression

	/* expr - expr */
	| e1=expression MINUS e2=expression #minusExpression

  /* expr < expr */
  | e1=expression LT e2=expression #lessThanExpresseion

  /* expr <= expr */
  | e1=expression LE e2=expression #lessEqualExpression

  /* expr = expr */
	| e1=expression EQUALS e2=expression #equalsExpression

   /* not expr */
  | st=NOT e1=expression #notExpression

	/* ID <- expr */
  | id=OBJECTID ASSIGN expr=expression #assignExpression

	| st=IF predicate=expression THEN ifbody=expression ELSE elsebody=expression FI #ifExpression

	| st=WHILE predicate=expression LOOP body=expression POOL #whileExpression

	/* expr < expr */
  | st=LBRACE el=block_expr_list RBRACE #blockExpression

  | st=LET ll=let_list IN e1=expression #letExpression

	| st=CASE predicate=expression OF bl=branch_list ESAC #caseExpression

	| st=NEW type=TYPEID #newExpression

	| st=LPAREN e1=expression RPAREN  # parenExpression

	/* ID */
	| id=OBJECTID #object
	/* integer */
	| v=INTEGER #integer

	/* string */
	| v=STRING  #stringLiteral

	/* true / false */
	| v=BOOL_CONST #boolLiteral

	;

block_expr_list
	:
		(expr = expression SEMICOLON)+
	;

attr :
	id=OBJECTID COLON type=TYPEID
	/* ID: TYPE [<- expr] */
	| id=OBJECTID COLON type=TYPEID ASSIGN expr=expression
	;

let_list
	:
	at_un=attr (COMMA at_deux = attr )*
	;


branch_list
	:
		(br=branch)+
	;

branch :
	id=OBJECTID COLON type=TYPEID DARROW expr=expression SEMICOLON
	;




CLASS      : ('c'|'C')('l'|'L')('a'|'A')('s'|'S')('s'|'S');
ELSE       : ('e'|'E')('l'|'L')('s'|'S')('e'|'E');
FI         : ('f'|'F')('i'|'I');
IF         : ('i'|'I')('f'|'F');
INHERITS   : ('i'|'I')('n'|'N')('h'|'H')('e'|'E')('r'|'R')('i'|'I')('t'|'T')('s'|'S');
ISVOID     : ('i'|'I')('s'|'S')('v'|'V')('o'|'O')('i'|'I')('d'|'D');
LET        : ('l'|'L')('e'|'E')('t'|'T');
LOOP       : ('l'|'L')('o'|'O')('o'|'O')('p'|'P');
POOL       : ('p'|'P')('o'|'O')('o'|'O')('l'|'L');
THEN       : ('t'|'T')('h'|'H')('e'|'E')('n'|'N');
WHILE      : ('w'|'W')('h'|'H')('i'|'I')('l'|'L')('e'|'E');
CASE       : ('c'|'C')('a'|'A')('s'|'S')('e'|'E');
ESAC       : ('e'|'E')('s'|'S')('a'|'A')('c'|'C');
NEW        : ('n'|'N')('e'|'E')('w'|'W');
OF         : ('o'|'O')('f'|'F');
NOT        : ('n'|'N')('o'|'O')('t'|'T');

IN         : 'in';

TYPEID     : [A-Z][a-z_]*;
OBJECTID   : [a-z]+;

COLON      : ':';

ASSIGN     : '<-';
DOT        : '.';
LBRACE     : '{';
RBRACE     : '}';
LPAREN     : '(';
RPAREN     : ')';
DARROW     : '->';
SEMICOLON  : ';';
ATSYM      : '@';
COMMA      : ',';
PLUS       : '+';
MINUS      : '-';
STAR       : '*';
SLASH      : '/';
LT         : '<';
LE         : '<=';
EQUALS     : '=';
NEGATE     : '~';

BOOL_CONST : TRUE_CONST | FALSE_CONST;

TRUE_CONST : 't'('r'|'R')('u'|'U')|('e'|'E');
FALSE_CONST: 'f'('a'|'A')('l'|'L')|('s'|'S')|('e'|'E');
INTEGER    : [0-9]+;
STRING     : '"' (~('"') | ('\\'.))* '"';


WS
    : [ \r\n\t]+ -> channel(HIDDEN)
    ;

// Catch-all for anything we can't recognize.
// We use this to be able to ignore and recover all the text
// when splitting statements with DelimiterLexer

