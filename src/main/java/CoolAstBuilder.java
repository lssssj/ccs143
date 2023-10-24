import cool.CoolBaseVisitor;
import cool.CoolParser.AddExpressionContext;
import cool.CoolParser.AssignExpressionContext;
import cool.CoolParser.AttrContext;
import cool.CoolParser.BlockExpressionContext;
import cool.CoolParser.Block_expr_listContext;
import cool.CoolParser.BoolLiteralContext;
import cool.CoolParser.BranchContext;
import cool.CoolParser.Branch_listContext;
import cool.CoolParser.CaseExpressionContext;
import cool.CoolParser.Class_Context;
import cool.CoolParser.Class_listContext;
import cool.CoolParser.DivideExpressionContext;
import cool.CoolParser.EqualsExpressionContext;
import cool.CoolParser.ExpressionContext;
import cool.CoolParser.ExpressionListContext;
import cool.CoolParser.Expression_listContext;
import cool.CoolParser.FeatureContext;
import cool.CoolParser.Feature_listContext;
import cool.CoolParser.FormalContext;
import cool.CoolParser.Formal_listContext;
import cool.CoolParser.IfExpressionContext;
import cool.CoolParser.IntegerContext;
import cool.CoolParser.InvokeExpressionContext;
import cool.CoolParser.IsVoidExpressionContext;
import cool.CoolParser.LessEqualExpressionContext;
import cool.CoolParser.LessThanExpresseionContext;
import cool.CoolParser.LetExpressionContext;
import cool.CoolParser.Let_listContext;
import cool.CoolParser.MethodContext;
import cool.CoolParser.MinusExpressionContext;
import cool.CoolParser.MulExpressionContext;
import cool.CoolParser.NegateExpressionContext;
import cool.CoolParser.NewExpressionContext;
import cool.CoolParser.NotExpressionContext;
import cool.CoolParser.ObjectContext;
import cool.CoolParser.ParenExpressionContext;
import cool.CoolParser.ProgramContext;
import cool.CoolParser.StaticInvokeExpressionContext;
import cool.CoolParser.StringLiteralContext;
import cool.CoolParser.WhileExpressionContext;

public class CoolAstBuilder extends CoolBaseVisitor<TreeNode> {

  private String fileName = "test.cl";

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public CoolAstBuilder() {

  }

  @Override
  public TreeNode visitProgram(ProgramContext ctx) {
    Classes classes = (Classes) ctx.class_list().accept(this);
    programc programc = new programc(ctx.start.getLine(), classes);
    return programc;
  }

  @Override
  public TreeNode visitClass_list(Class_listContext ctx) {
    Classes classes = new Classes(ctx.start.getLine());
    for (Class_Context classCtx : ctx.class_()) {
      classes.appendElement(classCtx.accept(this));
    }
    return classes;
  }

  @Override
  public TreeNode visitClass_(Class_Context ctx) {
    AbstractSymbol abstractSymbol = AbstractTable.idtable.addString(ctx.type.getText());
    AbstractSymbol parentSymbol;
    if (ctx.p_type != null) {
      parentSymbol = AbstractTable.idtable.addString(ctx.p_type.getText());
    } else {
      parentSymbol = TreeConstants.Object_;
    }
    Features features = (Features) ctx.fl.accept(this);
    AbstractSymbol filename = AbstractTable.stringtable.addString(fileName);
    return new class_c(ctx.start.getLine(), abstractSymbol, parentSymbol, features, filename);
  }

  @Override
  public TreeNode visitFeature_list(Feature_listContext ctx) {
    Features features = new Features(ctx.start.getLine());
    for (FeatureContext featureCtx : ctx.feature()) {
      features.appendElement(featureCtx.accept(this));
    }
    return features;
  }

  @Override
  public TreeNode visitFeature(FeatureContext ctx) {
    if (ctx.func != null) {
      return ctx.func.accept(this);
    }
    return ctx.var.accept(this);
  }

  @Override
  public TreeNode visitMethod(MethodContext ctx) {
    AbstractSymbol abstractSymbol = AbstractTable.idtable.addString(ctx.id.getText());
    Formals formals;
    if (ctx.fr_list != null) {
      formals = (Formals) ctx.fr_list.accept(this);
    } else {
      formals = new Formals(ctx.start.getLine());
    }
    AbstractSymbol typeSymbol = AbstractTable.idtable.addString(ctx.type.getText());
    Expression expression = (Expression) ctx.expr.accept(this);
    return new method(ctx.start.getLine(), abstractSymbol, formals, typeSymbol, expression);
  }

  @Override
  public TreeNode visitFormal_list(Formal_listContext ctx) {
    Formals formals = new Formals(ctx.start.getLine());
    for (FormalContext formalCtx : ctx.formal()) {
      formals.appendElement(formalCtx.accept(this));
    }
    return formals;
  }

  @Override
  public TreeNode visitFormal(FormalContext ctx) {
    AbstractSymbol idSymbol = AbstractTable.idtable.addString(ctx.id.getText());
    AbstractSymbol typeSymbol = AbstractTable.idtable.addString(ctx.type.getText());
    return new formalc(ctx.start.getLine(), idSymbol, typeSymbol);
  }

  @Override
  public TreeNode visitExpression_list(Expression_listContext ctx) {
    Expressions expressions = new Expressions(ctx.start.getLine());
    for (ExpressionContext ectx : ctx.expression()) {
      Expression expr = (Expression) ectx.accept(this);
      expressions.appendElement(expr);
    }
    return expressions;
  }

  @Override
  public TreeNode visitMinusExpression(MinusExpressionContext ctx) {
    Expression e1 = (Expression) ctx.e1.accept(this);
    Expression e2 = (Expression )ctx.e2.accept(this);
    return new sub(ctx.start.getLine(), e1, e2);
  }

  @Override
  public TreeNode visitNotExpression(NotExpressionContext ctx) {
    Expression expr = (Expression) ctx.e1.accept(this);
    return new neg(ctx.start.getLine(), expr);
  }

  @Override
  public TreeNode visitParenExpression(ParenExpressionContext ctx) {
    return ctx.e1.accept(this);
  }

  @Override
  public TreeNode visitInteger(IntegerContext ctx) {
    AbstractSymbol abstractSymbol = AbstractTable.inttable.addInt(
        Integer.parseInt(ctx.v.getText()));
    return new int_const(ctx.start.getLine(), abstractSymbol);
  }

  @Override
  public TreeNode visitBoolLiteral(BoolLiteralContext ctx) {
    Boolean b = Boolean.getBoolean(ctx.v.getText());
    return new bool_const(ctx.start.getLine(), b);
  }

  @Override
  public TreeNode visitLessThanExpresseion(LessThanExpresseionContext ctx) {
    Expression e1 = (Expression) ctx.e1.accept(this);
    Expression e2 = (Expression) ctx.e2.accept(this);
    return new lt(ctx.start.getLine(), e1, e2);
  }

  @Override
  public TreeNode visitEqualsExpression(EqualsExpressionContext ctx) {
    Expression e1 = (Expression)ctx.e1.accept(this);
    Expression e2 = (Expression)ctx.e2.accept(this);
    return new eq(ctx.start.getLine(), e1, e2);
  }

  @Override
  public TreeNode visitCaseExpression(CaseExpressionContext ctx) {
    Expression expr = (Expression) ctx.expression().accept(this);
    Cases branchs = (Cases) ctx.branch_list().accept(this);
    typcase type_case = new typcase(ctx.start.getLine(), expr, branchs);
    return type_case;
  }

  @Override
  public TreeNode visitLetExpression(LetExpressionContext ctx) {
    Features features = (Features) ctx.ll.accept(this);
    Expression body = (Expression) ctx.e1.accept(this);
    let let_expr = null;
    for (int i = features.getLength() - 1; i >= 0; i--) {
      attr nth = (attr) features.getNth(i);
      let_expr = new let(nth.lineNumber, nth.name, nth.type_decl, nth.init, body);
      body = let_expr;
    }
    return let_expr;
  }

  @Override
  public TreeNode visitLessEqualExpression(LessEqualExpressionContext ctx) {
    Expression e1 = (Expression) ctx.e1.accept(this);
    Expression e2 = (Expression )ctx.e2.accept(this);
    return new leq(ctx.start.getLine(), e1, e2);
  }

  @Override
  public TreeNode visitNegateExpression(NegateExpressionContext ctx) {
    Expression expr = (Expression) ctx.e1.accept(this);
    return new comp(ctx.start.getLine(), expr);
  }

  @Override
  public TreeNode visitIfExpression(IfExpressionContext ctx) {
    Expression predicate = (Expression) ctx.predicate.accept(this);
    Expression ifbody = (Expression) ctx.ifbody.accept(this);
    Expression elsebody = (Expression) ctx.elsebody.accept(this);
    return new cond(ctx.start.getLine(), predicate, ifbody, elsebody);
  }

  @Override
  public TreeNode visitInvokeExpression(InvokeExpressionContext ctx) {
    Expression object = (Expression) ctx.expr.accept(this);
    AbstractSymbol name = AbstractTable.idtable.addString(ctx.id.getText());
    Expressions actual = (Expressions) ctx.expr_list.accept(this);
    return new dispatch(ctx.start.getLine(), object, name, actual);
  }

  @Override
  public TreeNode visitStaticInvokeExpression(StaticInvokeExpressionContext ctx) {
    Expression object = (Expression) ctx.expr.accept(this);
    AbstractSymbol type = AbstractTable.idtable.addString(ctx.type.getText());
    AbstractSymbol name = AbstractTable.idtable.addString(ctx.id.getText());
    Expressions actual = (Expressions) ctx.expr_list.accept(this);
    return new static_dispatch(ctx.start.getLine(), object, type, name, actual);
  }

  @Override
  public TreeNode visitIsVoidExpression(IsVoidExpressionContext ctx) {
    Expression expr = (Expression) ctx.expr.accept(this);
    return new isvoid(ctx.start.getLine(), expr);
  }

  @Override
  public TreeNode visitWhileExpression(WhileExpressionContext ctx) {
    Expression predicate = (Expression) ctx.predicate.accept(this);
    Expression body = (Expression) ctx.body.accept(this);
    return new loop(ctx.start.getLine(), predicate, body);
  }

  @Override
  public TreeNode visitAssignExpression(AssignExpressionContext ctx) {
    AbstractSymbol id = AbstractTable.idtable.addString(ctx.id.getText());
    Expression expr = (Expression) ctx.expr.accept(this);
    return new assign(ctx.start.getLine(), id, expr);
  }

  @Override
  public TreeNode visitNewExpression(NewExpressionContext ctx) {
    AbstractSymbol abstractSymbol = AbstractTable.idtable.addString(ctx.type.getText());
    return new new_(ctx.start.getLine(), abstractSymbol);
  }

  @Override
  public TreeNode visitExpressionList(ExpressionListContext ctx) {
    Expressions expressions = new Expressions(ctx.start.getLine());
    for (ExpressionContext ex: ctx.expr_list.expression()) {
      Expression expression = (Expression) ex.accept(this);
      expressions.appendElement(expression);
    }
    return expressions;
  }

  @Override
  public TreeNode visitBlockExpression(BlockExpressionContext ctx) {
    Expressions expressions = new Expressions(ctx.start.getLine());
    for (ExpressionContext ex: ctx.block_expr_list().expression()) {
      Expression expression = (Expression) ex.accept(this);
      expressions.appendElement(expression);
    }
    return new block(ctx.start.getLine(), expressions);
  }

  @Override
  public TreeNode visitAddExpression(AddExpressionContext ctx) {
    Expression e1 = (Expression) ctx.e1.accept(this);
    Expression e2 = (Expression )ctx.e2.accept(this);
    return new plus(ctx.start.getLine(), e1, e2);
  }

  @Override
  public TreeNode visitStringLiteral(StringLiteralContext ctx) {
    AbstractSymbol abstractSymbol = AbstractTable.stringtable.addString(ctx.getText());
    return new string_const(ctx.start.getLine(), abstractSymbol);
  }

  @Override
  public TreeNode visitObject(ObjectContext ctx) {
    AbstractSymbol abstractSymbol = AbstractTable.idtable.addString(ctx.id.getText());
    return new object(ctx.start.getLine(), abstractSymbol);
  }

  @Override
  public TreeNode visitDivideExpression(DivideExpressionContext ctx) {
    Expression e1 = (Expression) ctx.e1.accept(this);
    Expression e2 = (Expression )ctx.e2.accept(this);
    return new divide(ctx.start.getLine(), e1, e2);
  }

  @Override
  public TreeNode visitMulExpression(MulExpressionContext ctx) {
    Expression e1 = (Expression) ctx.e1.accept(this);
    Expression e2 = (Expression )ctx.e2.accept(this);
    return new mul(ctx.start.getLine(), e1, e2);
  }

  @Override
  public TreeNode visitBlock_expr_list(Block_expr_listContext ctx) {
    return super.visitBlock_expr_list(ctx);
  }

  @Override
  public TreeNode visitAttr(AttrContext ctx) {
    AbstractSymbol idSymbol = AbstractTable.idtable.addString(ctx.id.getText());
    AbstractSymbol typeSymbol = null;
    if (ctx.type != null) {
      typeSymbol = AbstractTable.idtable.addString(ctx.type.getText());
    }
    Expression expression = null;
    if (ctx.expr != null) {
      expression = (Expression) ctx.expr.accept(this);
    }
    return new attr(ctx.start.getLine(), idSymbol, typeSymbol, expression);
  }

  @Override
  public TreeNode visitLet_list(Let_listContext ctx) {
    Features features = new Features(ctx.start.getLine());
    for (AttrContext attrContext : ctx.attr()) {
      TreeNode attr = attrContext.accept(this);
      features.appendElement(attr);
    }
    return features;
  }

  @Override
  public TreeNode visitBranch_list(Branch_listContext ctx) {
    Cases cases = new Cases(ctx.start.getLine());
    for (BranchContext branchContext : ctx.branch()) {
      TreeNode accept = branchContext.accept(this);
      cases.addElement(accept);
    }
    return cases;
  }

  @Override
  public TreeNode visitBranch(BranchContext ctx) {
    String text = ctx.OBJECTID().getText();
    AbstractSymbol objectSymbol = AbstractTable.idtable.addString(text);
    text = ctx.TYPEID().getText();
    AbstractSymbol typeSymbol = AbstractTable.idtable.addString(text);
    Expression expr = (Expression) ctx.expr.accept(this);
    return new branch(ctx.start.getLine(), objectSymbol, typeSymbol, expr);
  }
}
