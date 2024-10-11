package org.example.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

@Rule(key = "BadAnnotationNameRule")
public class BadAnnotationNameRule extends BaseTreeVisitor implements JavaFileScanner {

  private JavaFileScannerContext context;

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
  }

  @Override
  public void visitClass(ClassTree tree) {
    if (tree.is(Kind.ANNOTATION_TYPE) && tree.simpleName().name().toLowerCase().contains("generated")) {
      context.reportIssue(this, tree.simpleName(), "Not allowed to create an annotation with name that contains 'Generated'");
    }
    super.visitClass(tree);
  }}
