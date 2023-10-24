public interface AstVisitor<R, C> {
    R visitProgramc(programc node, C context);

    R visitClasses(Classes node, C context);

    R visitFeatures(Features node, C context);

    R visitFormals(Formals node, C context);

    R visitExpressions(Expressions node, C context);

    R visitCases(Cases node, C context);

    R visitClass_c(class_c node, C context);

    R visitMethod(method node, C context);

    R visitAttr(attr node, C context);

    R visitFormalc(formalc node, C context);

    R visitBranch(branch node, C context);

    R visitAssign(assign node, C context);

    R visitStaticDispatch(static_dispatch node, C context);

    R visitDispatch(dispatch node, C context);

    R visitCond(cond node, C context);

    R visitLoop(loop node, C context);

    R visitTypeCase(typcase node, C context);

    R visitBlock(block node, C context);

    R visitLet(let node, C context);

    R visitPlus(plus node, C context);

    R visitSub(sub node, C context);

    R visitMul(mul node, C context);

    R visitDivide(divide node, C context);

    R visitNegate(neg node, C context);

    R visitLt(lt node, C context);

    R visitEq(eq node, C context);

    R visitLeq(leq node, C context);

    R visitComp(comp node, C context);

    R visitIntConst(int_const node, C context);

    R visitBoolConst(bool_const node, C context);

    R visitStringConst(string_const node, C context);

    R visitNew(new_ node, C context);

    R visitIsVoid(isvoid node, C context);

    R visitObject(object node, C context);

    R visitNoExpr(no_expr node, C context);

    R visitTreeNode(TreeNode node, C context);
}
