package com.harrisoncircus.scafoldking;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.harrisoncircus.scafoldking");

        noClasses()
            .that()
            .resideInAnyPackage("com.harrisoncircus.scafoldking.service..")
            .or()
            .resideInAnyPackage("com.harrisoncircus.scafoldking.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.harrisoncircus.scafoldking.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
