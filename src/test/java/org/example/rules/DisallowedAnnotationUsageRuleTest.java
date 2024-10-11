package org.example.rules;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

class DisallowedAnnotationUsageRuleTest {

    @Test
    void testHasNoIssuesForClass() {
        var check = new DisallowedAnnotationUsageRule();

        CheckVerifier.newVerifier()
                .onFile("src/test/files/generatedannotation/Person.java")
                .withCheck(check)
                .verifyNoIssues();
    }

    @Test
    void testHasNoIssuesForInterface() {
        var check = new DisallowedAnnotationUsageRule();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedannotation/Vehicle.java")
            .withCheck(check)
            .verifyNoIssues();
    }

    @Test
    void testHasIssuesInClassWithGeneratedMethod() {
        var check = new DisallowedAnnotationUsageRule();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedannotation/Employee.java")
            .withCheck(check)
            .verifyIssues();
    }
    @Test
    void testHasIssuesInGeneratedClass() {
        var check = new DisallowedAnnotationUsageRule();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedannotation/GeneratedPerson.java")
            .withCheck(check)
            .verifyIssues();
    }
    @Test
    void testHasIssuesInGeneratedInterface() {
        var check = new DisallowedAnnotationUsageRule();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedannotation/GeneratedVehicle.java")
            .withCheck(check)
            .verifyIssues();
    }
    @Test
    void testHasIssuesInInterfaceWithGeneratedMethod() {
        var check = new DisallowedAnnotationUsageRule();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedannotation/GeneratedVehicleDrive.java")
            .withCheck(check)
            .verifyIssues();
    }
    @Test
    void testHasIssuesInLombokGeneratedClass() {
        var check = new DisallowedAnnotationUsageRule();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedannotation/LombokGeneratedPerson.java")
            .withCheck(check)
            .verifyIssues();
    }
    @Test
    void testHasIssuesInLombokGeneratedMethod() {
        var check = new DisallowedAnnotationUsageRule();

        CheckVerifier.newVerifier()
            .onFile("src/test/files/generatedannotation/LombokGeneratedVehicleDrive.java")
            .withCheck(check)
            .verifyIssues();
    }
}