import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class TypeChecker implements AstVisitor<AbstractSymbol, SymbolTable> {

  private int semantErrors = 0;
  private PrintStream errorStream = System.err;

  private class_c current_class;

  private ClassTable classTable;

  public TypeChecker(ClassTable classTable) {
    this.classTable = classTable;
  }


  @Override
  public AbstractSymbol visitProgramc(programc node, SymbolTable context) {
    context.enterScope();
    node.classes.accept(this, context);
    context.exitScope();
    return null;
  }


  @Override
  public AbstractSymbol visitClasses(Classes node, SymbolTable context) {
    for (int i = 0; i < node.getLength(); i++) {
      class_c nth = (class_c) node.getNth(i);
      context.addId(nth.name, nth.name);
      context.enterScope();
      node.getNth(i).accept(this, context);
      context.exitScope();
    }
    return null;
  }

  @Override
  public AbstractSymbol visitFeatures(Features node, SymbolTable context) {
    for (int i = 0; i < node.getLength(); i++) {
      if (node.getNth(i) instanceof attr) {
        node.getNth(i).accept(this, context);
      }
    }
    for (int i = 0; i < node.getLength(); i++) {
      if (node.getNth(i) instanceof method) {
        context.enterScope();
        node.getNth(i).accept(this, context);
        context.exitScope();
      }
    }
    return null;
  }

  @Override
  public AbstractSymbol visitFormals(Formals node, SymbolTable context) {
    Set<AbstractSymbol> parameters = new HashSet<>();
    for (int i = 0; i < node.getLength(); i++) {
      formalc f = (formalc) node.getNth(i);
      if (!parameters.add(f.name)) {
        semantError(current_class.getFilename(), node).printf("Formal parameter %s is multiply defined.\n", f.name.str);
        exit();
      }
    }
    for (int i = 0; i < node.getLength(); i++) {
      node.getNth(i).accept(this, context);
    }
    return null;
  }

  @Override
  public AbstractSymbol visitFormalc(formalc node, SymbolTable context) {
    if (node.name.equals(TreeConstants.self)) {
      semantError(current_class.getFilename(), node).println("'self' cannot be the name of a formal parameter.");
      exit();
    }
    if (classTable.findClass(node.type_decl) == null) {
      semantError(current_class.getFilename(), node).printf("Class %s of formal parameter i is undefined.\n", node.type_decl.str);
    }
    context.addId(node.name, node.type_decl);
    return node.type_decl;
  }

  @Override
  public AbstractSymbol visitExpressions(Expressions node, SymbolTable context) {
    AbstractSymbol type = null;
    for (int i = 0; i < node.getLength(); i++) {
      type = node.getNth(i).accept(this, context);
    }
    return type;
  }

  @Override
  public AbstractSymbol visitCases(Cases node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitClass_c(class_c node, SymbolTable context) {
    current_class = node;
    node.features.accept(this, context);
    return null;
  }

  @Override
  public AbstractSymbol visitMethod(method node, SymbolTable context) {
    class_c parent_class = classTable.findClass(current_class.parent);
    method inherited_method = findInheritMethod(parent_class, node.name);
    if (inherited_method != null) {
      checkMethodSignature(node, inherited_method);
    }
    node.formals.accept(this, context);
    AbstractSymbol actualType = node.expr.accept(this, context);
    if (!node.return_type.equals(classTable.commonLeastAncestor(node.return_type, actualType))) {
      semantError(current_class.getFilename(), node).printf("Inferred return type %s of method %s does not conform to declared return type %s.", actualType.str, node.name.str, node.return_type.str);
    }
    return null;
  }

  private void checkMethodSignature(method node, method inherited_method) {
    if (!node.return_type.equals(inherited_method)) {
      semantError(current_class.getFilename(), node).printf("In redefined method %s, return type %s is different from original return type %s.\n", node.name.str, node.return_type.str, inherited_method.return_type.str);
      exit();
    }
    if (node.formals.getLength() != inherited_method.formals.getLength()) {
      semantError(current_class.getFilename(), node).printf("Incompatible number of formal parameters in redefined method %s.\n", node.name.str);
      exit();
    }
    for (int i = 0; i < node.formals.getLength(); i++) {
      formalc n1 = (formalc) node.formals.getNth(i);
      formalc n2 = (formalc) inherited_method.formals.getNth(i);
      if (!n1.type_decl.equals(n2.type_decl)) {
        semantError(current_class.getFilename(), node).printf("In redefined method %s, parameter type %s is different from original type %s.\n", node.name.str, n1.type_decl.str, n2.type_decl.str);
        exit();
      }
    }

  }

  private method findInheritMethod(class_c node, AbstractSymbol methodName) {
    for (int i = 0; i < node.features.getLength(); i++) {
      if (node.features.getNth(i) instanceof method) {
        method m = (method) node.features.getNth(i);
        if (m.name.equals(methodName)) {
          return m;
        }
      }
    }
    if (node.name.equals(TreeConstants.Object_)) {
      return null;
    }
    return findInheritMethod(classTable.findClass(node.parent), methodName);
  }

  @Override
  public AbstractSymbol visitAttr(attr node, SymbolTable context) {
    if (node.init != null) {
      AbstractSymbol actual_type = node.init.accept(this, context);
      if (!classTable.commonLeastAncestor(node.type_decl, actual_type).equals(node.type_decl)) {
        semantError(current_class.getFilename(), node).printf(
            "Inferred type %s of initialization of attribute %s does not conform to declared type %s.",
            actual_type.str, node.name.str, node.type_decl.str);
        exit();
      }
    }
    if (checkParentClassExitAttr(classTable.findClass(current_class.parent), node.name)) {
      semantError(current_class.getFilename(), node).printf("Attribute %s is an attribute of an inherited class.\n", node.name.str);
      exit();
    }
    context.addId(node.name, node.type_decl);
    return null;
  }

  private boolean checkParentClassExitAttr(class_c node, AbstractSymbol attr_name) {
    if (node.name.equals(TreeConstants.Object_)) {
      return false;
    }
    for (int i = 0; i < node.features.getLength(); i++) {
      if (node.features.getNth(i) instanceof attr) {
        attr a = (attr) node.features.getNth(i);
        if (a.name.equals(attr_name)) {
          return true;
        }
      }
    }
    return checkParentClassExitAttr(classTable.findClass(node.parent), attr_name);
  }

  @Override
  public AbstractSymbol visitBranch(branch node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitAssign(assign node, SymbolTable context) {
    AbstractSymbol type = (AbstractSymbol) context.lookup(node.name);
    if (type == null) {
      semantError(current_class.getFilename(), node).printf("assign lvalue %s hasn't been defined.\n", node.name.str);
      exit();
    }
    AbstractSymbol valType = node.expr.accept(this, context);
    if (!type.equals(classTable.commonLeastAncestor(type, valType))) {
      semantError(current_class.getFilename(), node).printf("assign incompatible type, need %s, but found %s.\n", type.str, valType.str);
      exit();
    }
    return valType;
  }

  @Override
  public AbstractSymbol visitStaticDispatch(static_dispatch node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitDispatch(dispatch node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitCond(cond node, SymbolTable context) {
    AbstractSymbol predType = node.pred.accept(this, context);
    if (!predType.equals(TreeConstants.Bool)) {
      semantError(current_class.getFilename(), node).printf("if pred expression's type should be Bool, But found %s\n", predType.str);
      exit();
    }
    AbstractSymbol elseType = node.else_exp.accept(this, context);
    AbstractSymbol thenType = node.then_exp.accept(this, context);
    return classTable.commonLeastAncestor(elseType, thenType);
  }

  @Override
  public AbstractSymbol visitLoop(loop node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitTypeCase(typcase node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitBlock(block node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitLet(let node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitPlus(plus node, SymbolTable context) {
    AbstractSymbol e1type = node.e1.accept(this, context);
    AbstractSymbol e2type = node.e2.accept(this, context);
    if (!e1type.equals(TreeConstants.Int) || !e2type.equals(TreeConstants.Int)) {
      semantError(current_class.getFilename(), node).println("plus operator's type should be Int.");
      exit();
    }
    return TreeConstants.Int;
  }

  @Override
  public AbstractSymbol visitSub(sub node, SymbolTable context) {
    AbstractSymbol e1type = node.e1.accept(this, context);
    AbstractSymbol e2type = node.e2.accept(this, context);
    if (!e1type.equals(TreeConstants.Int) || !e2type.equals(TreeConstants.Int)) {
      semantError(current_class.getFilename(), node).println("sub operator's type should be Int.");
      exit();
    }
    return TreeConstants.Int;
  }

  @Override
  public AbstractSymbol visitMul(mul node, SymbolTable context) {
    AbstractSymbol e1type = node.e1.accept(this, context);
    AbstractSymbol e2type = node.e2.accept(this, context);
    if (!e1type.equals(TreeConstants.Int) || !e2type.equals(TreeConstants.Int)) {
      semantError(current_class.getFilename(), node).println("mul operator's type should be Int.");
      exit();
    }
    return TreeConstants.Int;
  }

  @Override
  public AbstractSymbol visitDivide(divide node, SymbolTable context) {
    AbstractSymbol e1type = node.e1.accept(this, context);
    AbstractSymbol e2type = node.e2.accept(this, context);
    if (!e1type.equals(TreeConstants.Int) || !e2type.equals(TreeConstants.Int)) {
      semantError(current_class.getFilename(), node).println("divide operator's type should be Int.");
      exit();
    }
    return TreeConstants.Int;
  }

  @Override
  public AbstractSymbol visitNegate(neg node, SymbolTable context) {
    AbstractSymbol e1type = node.e1.accept(this, context);
    if (!e1type.equals(TreeConstants.Bool)) {
      semantError(current_class.getFilename(), node).println("not operator's type should be Bool.");
      exit();
    }
    return TreeConstants.Bool;
  }

  @Override
  public AbstractSymbol visitLt(lt node, SymbolTable context) {
    AbstractSymbol e1type = node.e1.accept(this, context);
    AbstractSymbol e2type = node.e2.accept(this, context);
    if (!e1type.equals(TreeConstants.Int) || !e2type.equals(TreeConstants.Int)) {
      semantError(current_class.getFilename(), node).println("< operator's type should be Int.");
      exit();
    }
    return TreeConstants.Bool;
  }

  @Override
  public AbstractSymbol visitEq(eq node, SymbolTable context) {
    AbstractSymbol e1type = node.e1.accept(this, context);
    AbstractSymbol e2type = node.e2.accept(this, context);
    if (e1type.equals(TreeConstants.Int) && !e2type.equals(TreeConstants.Int) || !e1type.equals(TreeConstants.Int) && e2type.equals(TreeConstants.Int)) {
      semantError(current_class.getFilename(), node).printf("= operator's type should all be Int, but one is %s, the another is %s.", e1type.str, e2type.str);
      exit();
    } else if (e1type.equals(TreeConstants.Str) && !e2type.equals(TreeConstants.Str) || !e1type.equals(TreeConstants.Str) && e2type.equals(TreeConstants.Str)) {
      semantError(current_class.getFilename(), node).printf("= operator's type should all be String, but one is %s, the another is %s.", e1type.str, e2type.str);
      exit();
    } else if (e1type.equals(TreeConstants.Bool) && !e2type.equals(TreeConstants.Bool) || !e1type.equals(TreeConstants.Bool) && e2type.equals(TreeConstants.Bool)) {
      semantError(current_class.getFilename(), node).printf("= operator's type should all be Bool, but one is %s, the another is %s.", e1type.str, e2type.str);
      exit();
    }
    return TreeConstants.Bool;
  }

  @Override
  public AbstractSymbol visitLeq(leq node, SymbolTable context) {
    AbstractSymbol e1type = node.e1.accept(this, context);
    AbstractSymbol e2type = node.e2.accept(this, context);
    if (!e1type.equals(TreeConstants.Int) || !e2type.equals(TreeConstants.Int)) {
      semantError(current_class.getFilename(), node).println("<= operator's type should be Int.");
      exit();
    }
    return TreeConstants.Bool;
  }

  @Override
  public AbstractSymbol visitComp(comp node, SymbolTable context) {
    AbstractSymbol type = node.e1.accept(this, context);
    if (!type.equals(TreeConstants.Int)) {
      semantError(current_class.getFilename(), node).printf("complement operator's type should be Int, but found %s.\n", type.str);
      exit();
    }
    return TreeConstants.Int;
  }

  @Override
  public AbstractSymbol visitIntConst(int_const node, SymbolTable context) {
    return TreeConstants.Int;
  }

  @Override
  public AbstractSymbol visitBoolConst(bool_const node, SymbolTable context) {
    return TreeConstants.Bool;
  }

  @Override
  public AbstractSymbol visitStringConst(string_const node, SymbolTable context) {
    return TreeConstants.Str;
  }

  @Override
  public AbstractSymbol visitNew(new_ node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitIsVoid(isvoid node, SymbolTable context) {
    node.e1.accept(this, context);
    return TreeConstants.Bool;
  }

  @Override
  public AbstractSymbol visitObject(object node, SymbolTable context) {
    Object type = context.lookup(node.getName());
    if (type == null) {
      semantError(current_class.getFilename(), node).printf("objectIdentifier %s hasn't been defined.\n", node.getName().str);
      exit();
      // context.addId(node.getName(), TreeConstants.Object_);
    }
    return (AbstractSymbol) context.lookup(node.getName());
  }

  @Override
  public AbstractSymbol visitNoExpr(no_expr node, SymbolTable context) {
    return null;
  }

  @Override
  public AbstractSymbol visitTreeNode(TreeNode node, SymbolTable context) {
    return null;
  }


  /** Prints line number and file name of the given class.
   *
   * Also increments semantic error count.
   *
   * @param c the class
   * @return a print stream to which the rest of the error message is
   * to be printed.
   *
   * */
  public PrintStream semantError(class_c c) {
    return semantError(c.getFilename(), c);
  }

  /** Prints the file name and the line number of the given tree node.
   *
   * Also increments semantic error count.
   *
   * @param filename the file name
   * @param t the tree node
   * @return a print stream to which the rest of the error message is
   * to be printed.
   *
   * */
  public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
  }

  /** Increments semantic error count and returns the print stream for
   * error messages.
   *
   * @return a print stream to which the error message is
   * to be printed.
   *
   * */
  public PrintStream semantError() {
    semantErrors++;
    return errorStream;
  }

  public void exit() {
    exit(-1);
  }

  public void exit(int status) {
    System.err.print("Compilation halted due to static semantic errors.");
    System.exit(status);
  }

  /** Returns true if there are any static semantic errors. */
  public boolean errors() {
    return semantErrors != 0;
  }
}
