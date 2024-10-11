package org.example;

import org.example.rules.DisallowGeneratedAnnotationDeclarationRuleOld;
import org.example.rules.DisallowedAnnotationUsageRule;
import org.sonar.plugins.java.api.JavaCheck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RulesProvider {

    private RulesProvider() {
    }

    public static List<Class<? extends JavaCheck>> getChecks() {
        List<Class<? extends JavaCheck>> checks = new ArrayList<>();
        checks.addAll(getJavaChecks());
        checks.addAll(getJavaTestChecks());
        return Collections.unmodifiableList(checks);
    }

    /**
     * These rules are going to target MAIN code only
     */
    public static List<Class<? extends JavaCheck>> getJavaChecks() {
        return List.of(DisallowGeneratedAnnotationDeclarationRuleOld.class, DisallowedAnnotationUsageRule.class);
    }

    /**
     * These rules are going to target TEST code only
     */
    public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
        return List.of();
    }
}
