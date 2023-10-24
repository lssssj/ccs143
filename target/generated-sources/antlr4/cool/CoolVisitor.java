// Generated from Cool.g4 by ANTLR 4.9.2
package cool;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoolVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_list(CoolParser.Class_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_(CoolParser.Class_Context ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#feature_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeature_list(CoolParser.Feature_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeature(CoolParser.FeatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(CoolParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal_list(CoolParser.Formal_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CoolParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#expression_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list(CoolParser.Expression_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minusExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusExpression(CoolParser.MinusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(CoolParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpression(CoolParser.ParenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integer}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(CoolParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolLiteral}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(CoolParser.BoolLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessThanExpresseion}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThanExpresseion(CoolParser.LessThanExpresseionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalsExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualsExpression(CoolParser.EqualsExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(CoolParser.CaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetExpression(CoolParser.LetExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessEqualExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessEqualExpression(CoolParser.LessEqualExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negateExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateExpression(CoolParser.NegateExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpression(CoolParser.IfExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code invokeExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeExpression(CoolParser.InvokeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code staticInvokeExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaticInvokeExpression(CoolParser.StaticInvokeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isVoidExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsVoidExpression(CoolParser.IsVoidExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileExpression(CoolParser.WhileExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpression(CoolParser.AssignExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpression(CoolParser.NewExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divideExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivideExpression(CoolParser.DivideExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionList}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(CoolParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockExpression(CoolParser.BlockExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpression(CoolParser.AddExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(CoolParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code object}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(CoolParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpression(CoolParser.MulExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#block_expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_expr_list(CoolParser.Block_expr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#attr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(CoolParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#let_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_list(CoolParser.Let_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#branch_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch_list(CoolParser.Branch_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(CoolParser.BranchContext ctx);
}