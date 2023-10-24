import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** This class may be used to contain the semantic information such as
 * the inheritance graph.  You may use it or not as you like: it is only
 * here to provide a container for the supplied methods.  */
class ClassTable {
  private int semantErrors;
  private PrintStream errorStream;

  /** Creates data structures representing basic Cool classes (Object,
   * IO, Int, Bool, String).  Please note: as is this method does not
   * do anything useful; you will need to edit it to make if do what
   * you want.
   * */
  private void installBasicClasses() {
    AbstractSymbol filename
        = AbstractTable.stringtable.addString("<basic class>");

    // The following demonstrates how to create dummy parse trees to
    // refer to basic Cool classes.  There's no need for method
    // bodies -- these are already built into the runtime system.

    // IMPORTANT: The results of the following expressions are
    // stored in local variables.  You will want to do something
    // with those variables at the end of this method to make this
    // code meaningful.

    // The Object class has no parent class. Its methods are
    //        cool_abort() : Object    aborts the program
    //        type_name() : Str        returns a string representation
    //                                 of class name
    //        copy() : SELF_TYPE       returns a copy of the object

    class_c Object_class =
        new class_c(0,
            TreeConstants.Object_,
            TreeConstants.No_class,
            new Features(0)
                .appendElement(new method(0,
                    TreeConstants.cool_abort,
                    new Formals(0),
                    TreeConstants.Object_,
                    new no_expr(0)))
                .appendElement(new method(0,
                    TreeConstants.type_name,
                    new Formals(0),
                    TreeConstants.Str,
                    new no_expr(0)))
                .appendElement(new method(0,
                    TreeConstants.copy,
                    new Formals(0),
                    TreeConstants.SELF_TYPE,
                    new no_expr(0))),
            filename);

    // The IO class inherits from Object. Its methods are
    //        out_string(Str) : SELF_TYPE  writes a string to the output
    //        out_int(Int) : SELF_TYPE      "    an int    "  "     "
    //        in_string() : Str            reads a string from the input
    //        in_int() : Int                "   an int     "  "     "

    class_c IO_class =
        new class_c(0,
            TreeConstants.IO,
            TreeConstants.Object_,
            new Features(0)
                .appendElement(new method(0,
                    TreeConstants.out_string,
                    new Formals(0)
                        .appendElement(new formalc(0,
                            TreeConstants.arg,
                            TreeConstants.Str)),
                    TreeConstants.SELF_TYPE,
                    new no_expr(0)))
                .appendElement(new method(0,
                    TreeConstants.out_int,
                    new Formals(0)
                        .appendElement(new formalc(0,
                            TreeConstants.arg,
                            TreeConstants.Int)),
                    TreeConstants.SELF_TYPE,
                    new no_expr(0)))
                .appendElement(new method(0,
                    TreeConstants.in_string,
                    new Formals(0),
                    TreeConstants.Str,
                    new no_expr(0)))
                .appendElement(new method(0,
                    TreeConstants.in_int,
                    new Formals(0),
                    TreeConstants.Int,
                    new no_expr(0))),
            filename);

    // The Int class has no methods and only a single attribute, the
    // "val" for the integer.

    class_c Int_class =
        new class_c(0,
            TreeConstants.Int,
            TreeConstants.Object_,
            new Features(0)
                .appendElement(new attr(0,
                    TreeConstants.val,
                    TreeConstants.prim_slot,
                    new no_expr(0))),
            filename);

    // Bool also has only the "val" slot.
    class_c Bool_class =
        new class_c(0,
            TreeConstants.Bool,
            TreeConstants.Object_,
            new Features(0)
                .appendElement(new attr(0,
                    TreeConstants.val,
                    TreeConstants.prim_slot,
                    new no_expr(0))),
            filename);

    // The class Str has a number of slots and operations:
    //       val                              the length of the string
    //       str_field                        the string itself
    //       length() : Int                   returns length of the string
    //       concat(arg: Str) : Str           performs string concatenation
    //       substr(arg: Int, arg2: Int): Str substring selection

    class_c Str_class =
        new class_c(0,
            TreeConstants.Str,
            TreeConstants.Object_,
            new Features(0)
                .appendElement(new attr(0,
                    TreeConstants.val,
                    TreeConstants.Int,
                    new no_expr(0)))
                .appendElement(new attr(0,
                    TreeConstants.str_field,
                    TreeConstants.prim_slot,
                    new no_expr(0)))
                .appendElement(new method(0,
                    TreeConstants.length,
                    new Formals(0),
                    TreeConstants.Int,
                    new no_expr(0)))
                .appendElement(new method(0,
                    TreeConstants.concat,
                    new Formals(0)
                        .appendElement(new formalc(0,
                            TreeConstants.arg,
                            TreeConstants.Str)),
                    TreeConstants.Str,
                    new no_expr(0)))
                .appendElement(new method(0,
                    TreeConstants.substr,
                    new Formals(0)
                        .appendElement(new formalc(0,
                            TreeConstants.arg,
                            TreeConstants.Int))
                        .appendElement(new formalc(0,
                            TreeConstants.arg2,
                            TreeConstants.Int)),
                    TreeConstants.Str,
                    new no_expr(0))),
            filename);

	/* Do somethind with Object_class, IO_class, Int_class,
           Bool_class, and Str_class here */
    name2class.put(TreeConstants.Object_, Object_class);
    name2class.put(TreeConstants.IO, IO_class);
    name2class.put(TreeConstants.Str, Str_class);
    name2class.put(TreeConstants.Int, Int_class);
    name2class.put(TreeConstants.Bool, Bool_class);
  }


  private Map<AbstractSymbol, class_c> name2class = new HashMap<>();

  public ClassTable() {
    semantErrors = 0;
    errorStream = System.err;
    installBasicClasses();
  }

  public ClassTable(Classes cls) {
    semantErrors = 0;
    errorStream = System.err;
    installBasicClasses();

    // 检查类的继承图
    // 1。 父类不能是自己
    // 2。 父类必须是类
    // 3。 只能单继承
    // 4. 一些类不能被继承（String, Bool, Int)

    for (int i = 0; i < cls.getLength(); i++) {
      class_c nth = (class_c) cls.getNth(i);
      if (nth.name.equals("SELF_TYPE")) {
        semantError(nth).println(" class's name can't be SELF_TYPE");
      }
      if (nth.name.equals(nth.parent)) {
        semantError(nth).printf(" class %s can't inherit self.\n", nth.name.str);
      }
      if (nth.parent.equals(TreeConstants.Bool)) {
        semantError(nth).printf(" class Bool can't be inherited by %s.\n", nth.name.str);
      }
      if (nth.parent.equals(TreeConstants.Int)) {
        semantError(nth).printf(" class Int can't be inherited by %s.\n", nth.name.str);
      }
      if (nth.parent.equals(TreeConstants.Str)) {
        semantError(nth).printf(" class String can't be inherited by %s.\n", nth.name.str);
      }
      if (name2class.containsKey(nth.name.str)) {
        semantError(nth).printf(" class %s has been defined.\n", nth.name.str);
      }
      name2class.put(nth.name, nth);
    }
    for (int i = 0; i < cls.getLength(); i++) {
      class_c nth = (class_c) cls.getNth(i);
      if (!name2class.containsKey(nth.parent)) {
        semantError(nth).printf(" class %s's parent %s is not defined.\n", nth.name.str, nth.parent.str);
      }
    }
    if (!name2class.containsKey(TreeConstants.Main)) {
      semantErrors++;
      errorStream.println("Found No Main class.");
    }
    // 检查有没有类被循环继承如，A inherits B, B inherits A
    for (int i = 0; i < cls.getLength(); i++) {
      Set<String> childs = new HashSet<>();
      class_c nth = (class_c) cls.getNth(i);

      while (!nth.parent.str.equals("Object")) {
        childs.add(nth.name.str);
        nth = name2class.get(nth.parent);
        if (childs.contains(nth.name.str)) {
          semantError(nth).printf(" found cycle inherits: class %s inherits %s.\n", nth.name.str, nth.parent.str);
          break;
        }
      }

    }
    /* fill this in */
  }

  public AbstractSymbol commonLeastAncestor(AbstractSymbol type1, AbstractSymbol type2) {
    List<AbstractSymbol> list1 = findInheritGraph(type1);
    List<AbstractSymbol> list2 = findInheritGraph(type2);
    int index1 = list1.size() - 1, index2 = list2.size() - 1;
    AbstractSymbol ret = TreeConstants.Object_;
    while (index1 >= 0 && index2 >= 0) {
      if (list1.get(index1).equals(list2.get(index2))) {
        ret = list1.get(index1);
        index1--;
        index2--;
      } else {
        break;
      }
    }
    return ret;
  }

  public List<AbstractSymbol> findInheritGraph(AbstractSymbol type) {
    List<AbstractSymbol> types = new ArrayList<>();
    while (!type.equals(TreeConstants.Object_)) {
      types.add(type);
      type = name2class.get(type).parent;
    }
    types.add(TreeConstants.Object_);
    return types;
  }

  public class_c findClass(AbstractSymbol className) {
    return name2class.get(className);
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

  /** Returns true if there are any static semantic errors. */
  public boolean errors() {
    return semantErrors != 0;
  }
}


