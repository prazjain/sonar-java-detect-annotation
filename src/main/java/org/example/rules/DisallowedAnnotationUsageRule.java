package org.example.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TypeTree;

@Rule(key = "DisallowGeneratedAnnotationUsage")
public class DisallowedAnnotationUsageRule extends BaseTreeVisitor implements JavaFileScanner {

  private JavaFileScannerContext context;

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
  }

  @Override
  public void visitAnnotation(AnnotationTree annotationTree) {
    TypeTree annotationType = annotationTree.annotationType();
    if (annotationType.is(Tree.Kind.IDENTIFIER)) {
      String idName = ((IdentifierTree) annotationType).name();
      if (idName.toLowerCase().contains("generated")) {
        context.reportIssue(this, annotationTree, "Not allowed to use annotation that has 'Generated' in name");
      }
    } else if (annotationType.is(Kind.MEMBER_SELECT)) {
      MemberSelectExpressionTree memberSelect = ((MemberSelectExpressionTree) annotationType);
      String idName = memberSelect.expression().symbolType().name() + memberSelect.operatorToken().text() + memberSelect.identifier().name();
      if (idName.equals("lombok.Generated") ) {
        context.reportIssue(this, annotationTree, "Not allowed to use annotation @lombok.Generated");
      }
    }
    super.visitAnnotation(annotationTree);
  }
}
