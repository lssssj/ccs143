import java.util.List;

public class TestClassTable {
  public static void main(String[] args) {

    ClassTable classTable = new ClassTable();
    List<AbstractSymbol> inheritGraph = classTable.findInheritGraph(TreeConstants.Int);
    AbstractSymbol abstractSymbol = classTable.commonLeastAncestor(TreeConstants.Int,
        TreeConstants.Str);
    System.out.println(abstractSymbol);
  }
}
