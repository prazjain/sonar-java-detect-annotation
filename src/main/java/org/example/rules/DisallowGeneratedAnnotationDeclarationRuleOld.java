package org.example.rules;

import java.util.Collections;
import java.util.List;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TypeTree;

@Rule(key = "DisallowGeneratedAnnotationDeclaration")
public class DisallowGeneratedAnnotationDeclarationRuleOld extends IssuableSubscriptionVisitor {

  @Override
  public List<Tree.Kind> nodesToVisit() {
    // Register to the kind of nodes you want to be called upon visit.
    return Collections.singletonList(Kind.ANNOTATION_TYPE);
  }

  @Override
  public void visitNode(Tree tree) {
    ClassTree classTree = (ClassTree) tree;
    // If this is not an Annotation with name "Generated" then exit
    if (!classTree.simpleName().name().toLowerCase().contains("generated")) {
      return;
    }
    context.reportIssue(this, classTree, "Not allowed to create an annotation with name that contains 'Generated'");
    //boolean retainedFlag = hasRuntimeRetention(classTree);
    //if (retainedFlag) {
    //  context.reportIssue(this, classTree, String.format("Not allowed to create annotation @%s", classTree.simpleName().name()));
    //}
  }

  private boolean hasRuntimeRetention(ClassTree classTree) {
    // If this is an Annotation with name "Generated" then check if it has annotation of type Runtime
    List<AnnotationTree> annotations = classTree.modifiers().annotations();
    for (AnnotationTree annotationTree : annotations) {
      TypeTree annotationType = annotationTree.annotationType();
      IdentifierTree annotation = (IdentifierTree) annotationType;
      if (annotation.name().equals("Retention")) {
        Arguments args = annotationTree.arguments();
          for (Tree argTree : args) {
            MemberSelectExpressionTree memberSelectExpressionTree = (MemberSelectExpressionTree) argTree;
            String nm = memberSelectExpressionTree.identifier().name();
            if (argTree.is(Kind.MEMBER_SELECT) && (nm.equals("RUNTIME") || nm.equals("CLASS"))) {
                  return true;
            }
          }
      }
    }
    return false;
  }
}
