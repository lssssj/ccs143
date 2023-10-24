/*
Copyright (c) 2000 The Regents of the University of California.
All rights reserved.

Permission to use, copy, modify, and distribute this software for any
purpose, without fee, and without written agreement is hereby granted,
provided that the above copyright notice and the following two
paragraphs appear in all copies of this software.

IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR
DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE UNIVERSITY OF
CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
ON AN "AS IS" BASIS, AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATION TO
PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
*/

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java_cup.runtime.Symbol;

/** Static semantics driver class */
class Cgen {

  /** Reads AST from from consosle, and outputs the assembly code */
  public static void main(String[] args) {
    //args = Flags.handleFlags(args);
    String source = "class C {\n"
        + "\ta : Int;\n"
        + "\tb : Bool;\n"
        + "\tinit(x : Int, y : Bool) : C {\n"
        + "           {\n"
        + "\t\ta <- x;\n"
        + "\t\tb <- y;\n"
        + "\t\tself;\n"
        + "           }\n"
        + "\t};\n"
        + "};\n"
        + "\n"
        + "Class Main {\n"
        + "\tmain():C {\n"
        + "\t {\n"
        + "\t  (new C).init(1,1);\n"
        + "\t  (new C).init(1,true,3);\n"
        + "\t  (new C).iinit(1,true);\n"
        + "\t  (new C);\n"
        + "\t }\n"
        + "\t};\n"
        + "};\n";
    Reader reader = new StringReader(source);
    try {
      // ASTLexer lexer = new ASTLexer(new InputStreamReader(System.in));


      PrintStream output = System.out;
      String filename = null;
      if (Flags.out_filename == null) {
        if (Flags.in_filename != null) {
          filename = Flags.in_filename.substring(0,
              Flags.in_filename.lastIndexOf('.'))
              + ".s";
        }
      } else {
        filename = Flags.out_filename;
      }

      if (filename != null) {
        try {
          output = new PrintStream(new FileOutputStream(filename));
        } catch (IOException ex) {
          Utilities.fatalError("Cannot open output file " + filename);
        }
      }

      //((Program)result).cgen(output);
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    }
  }
}
