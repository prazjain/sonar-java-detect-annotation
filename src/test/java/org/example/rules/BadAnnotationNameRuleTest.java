package org.example.rules;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

class BadAnnotationNameRuleTest {

    @Test
    void testHasNoIssues() {
        var check = new DisallowGeneratedAnnotationDeclarationRuleOld();

        CheckVerifier.newVerifier()
                .onFile("src/test/files/generatedinterface/ManagedObject.java")
                .withCheck(check)
                .verifyNoIssues();
    }
    @Test
    void testHasIssuesWithSourceRetention() {
        var check = new DisallowGeneratedAnnotationDeclarationRuleOld();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedinterface/GeneratedSource.java")
            .withCheck(check)
            .verifyIssues();
    }
    @Test
    void testHasIssues() {
        var check = new DisallowGeneratedAnnotationDeclarationRuleOld();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedinterface/Generated.java")
            .withCheck(check)
            .verifyIssues();
    }
    @Test
    void testHasIssuesWithNameContainingGenerated() {
        var check = new DisallowGeneratedAnnotationDeclarationRuleOld();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedinterface/IgnoreGeneratedJacocoCoverage.java")
            .withCheck(check)
            .verifyIssues();
    }
}