package cz.czechitas.automation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.apache.commons.lang3.StringUtils;

/**
 * Test class for custom student tests
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class LowCodeAutomationTest extends TestRunner {

    final String randomName = StringUtils.capitalize(browser.generateRandomName(12));
    final String randomLastName = StringUtils.capitalize(browser.generateRandomName(10));
    final String password1 = "Qaz123edc";
    final String password2 = "New123";
    final String user1Email = "alisa.kapitanovaplt@gmail.com";
    final String termPyton = "05.02. - 09.02.2024";
    final String studentFirstName = "Alisa";
    final String StudentLastName = "Dali";
    @Test
    @DisplayName("should navigate to 'Pro rodiče -> Návody a formuláře'")
    void AT1_openSectionNavodyFormulare() {
        browser.headerMenu.goToInstructionsAndFormsForParentSection();
    }
    @Test
    @DisplayName("should navigate to 'Pro učitelé -> Objednávka pro MŠ/ZŠ' and select 'Příměstský tábor'")
    void AT2_openObjednavkaProUcitele() {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.selectSuburbanCampOption();
    }

    @Test
    @DisplayName("should test all navbar sections")
    void AT3_navigationHeaderOfPage() {
        browser.headerMenu.goToInstructionsAndFormsForParentSection();
        browser.headerMenu.goToInstructionsAndFormsForTeacherSection();
        browser.headerMenu.goToContactsSection();
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.headerMenu.goToCreateApplicationSection();
        browser.headerMenu.goToHomePage();
    }
    @ParameterizedTest()
    @DisplayName("should navigate to  'Pro učitelé -> Objednávka pro MŠ/ZŠ' and fill 'IČO'")
    @ValueSource(strings = {"000t54dg76"})
    void AT4_vyplniICO(String icoValue) {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(icoValue);
    }
    /* Test5
    mail alisa.kapitanovaplt@gmail.com
    pas Qaz123edc
     */

    @Test
    @DisplayName("should create new application")
    void AT6_positiveCreateNewApplication() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(user1Email);
        browser.loginSection.insertPassword(password1);
        browser.loginSection.clickLoginButton();
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreateApplicationButton();
    }

    @Test
    @DisplayName("should contain tile 'Programování' on the first page")
    void AT1_checkWindowProgramovani() {
        asserter.checkProgrammingSectionPresense();
    }

    @Test
    @DisplayName("should display registration button")
    void AT2_checkButtonRegistrace() {
        browser.headerMenu.goToCreateApplicationSection();
        asserter.checkRegistrationButtonPresense();
    }
    @Test
    @DisplayName("should create new application")
    void AT3_createNewApplication() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(user1Email);
        browser.loginSection.insertPassword(password1);
        browser.loginSection.clickLoginButton();
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreateApplicationButton();
        browser.applicationDetailsSection.selectTerm(termPyton);
        browser.applicationDetailsSection.insertStudentFirstName(studentFirstName);
        browser.applicationDetailsSection.insertStudentLastName(StudentLastName);
        browser.applicationDetailsSection.insertBirthdate("01.01.2000");
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.waitFor(5);
        browser.applicationDetailsSection. clickCreateApplicationButton();
    }

    @Test
    @DisplayName("should show correct payment type")
    void AT4_checkPayMetod() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(user1Email);
        browser.loginSection.insertPassword(password1);
        browser.loginSection.clickLoginButton();
        browser.applicationSection.openFirstApplicationDetailsPage();
        asserter.applicationDetailAction.checkPaymentMethod("Hotově");
    }

    @ParameterizedTest()
    @ValueSource(strings = {"000t54dg76"})
    void AT_Navigace_should_navigate_ProučiteléObjednávka_filLICOandPocetDeti (String icoValue) {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(icoValue);
        browser.orderSection.selectSchoolInNatureOption();
        browser.orderSection.insertChildrenCount(14);
    }

    @Test
    void AT_01_Assertace_Prihlashky_should_contain_sloupce_Jmeno_Kategorie(){
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("alisa.kapitanovaplt@gmail.com");
        browser.loginSection.insertPassword("Qaz123edc");
        browser.loginSection.clickLoginButton();
        browser.headerMenu.goToApplicationsSection();
        asserter.applicationSection.checkColumnExists("Jméno");
        asserter.applicationSection.checkColumnExists("Kategorie");
}

@Test
    void AT_01_KomplexnejsiTesty_should_create_new_application() {
    browser.loginSection.clickLoginMenuLink();
    browser.loginSection.insertEmail("alisa.kapitanovaplt@gmail.com");
    browser.loginSection.insertPassword("Qaz123edc");
    browser.loginSection.clickLoginButton();
    browser.applicationSection.clickCreateNewApplicationButton();
    browser.applicationSection.selectProgrammingSection();
    browser.applicationSection.clickCreateApplicationButton();
    browser.applicationDetailsSection.selectTerm("05.02. - 09.02.2024");
    browser.applicationDetailsSection.insertStudentFirstName("Jan");
    browser.applicationDetailsSection.insertStudentLastName("Dali");
    browser.applicationDetailsSection.insertBirthdate("01.01.2000");
    browser.applicationDetailsSection.insertNote("Poznamka");
    browser.applicationDetailsSection.selectCashPaymentMethod();
    browser.applicationDetailsSection.clickAcceptTermsCheckbox();
    browser.waitFor(5);
    browser.applicationDetailsSection. clickCreateApplicationButton();
    browser.headerMenu.goToApplicationsSection();
    asserter.applicationDetailAction.checkTerm("05.02. - 09.02.2024");
    asserter.applicationDetailAction.checkFirstName("Jan");
    asserter.applicationDetailAction.checkLastName("Dali");
    asserter.applicationDetailAction.checkDateOfBirth("01.01.2000");
    asserter.applicationDetailAction.checkNote("Poznamka");
}

@Test
    void AT_03_edit_application_pay_metod(){
    browser.loginSection.clickLoginMenuLink();
    browser.loginSection.insertEmail("alisa.kapitanovaplt@gmail.com");
    browser.loginSection.insertPassword("Qaz123edc");
    browser.loginSection.clickLoginButton();
    browser.headerMenu.goToApplicationsSection();
    browser.applicationSection.search("Tester001");
    browser.applicationSection.clickEditFirstApplicationButton();
    browser.applicationDetailsSection.selectBankTransferPaymentMethod();
    browser.applicationDetailsSection.clickEditApplicationButton();
    browser.applicationSection.search("Tester001");
    browser.applicationSection.openFirstApplicationDetailsPage();
    asserter.applicationDetailAction.checkPaymentMethod("Bankovní převod");
    asserter.applicationDetailAction.checkRemainingAmountToPay("1 800 Kč");
    asserter.applicationDetailAction.checkMessageContainsStudentLastName("Martin Tester001");
}

@Test
    void AT_06_applicationsOfOneUserAreNotVisibleToOtherUsers() {
    browser.loginSection.clickLoginMenuLink();
    browser.loginSection.insertEmail("alisa_kapitanova@ukr.net");
    browser.loginSection.insertPassword("Qaz123edc");
    browser.loginSection.clickLoginButton();
    browser.applicationSection.clickCreateNewApplicationButton();
    browser.applicationSection.selectProgrammingSection();
    browser.applicationSection.clickCreateApplicationButton();
    browser.applicationDetailsSection.selectTerm("05.02. - 09.02.2024");
    browser.applicationDetailsSection.insertStudentFirstName(randomName);
    browser.applicationDetailsSection.insertStudentLastName(randomLastName);
    browser.applicationDetailsSection.insertBirthdate("21.10.1999");
    browser.applicationDetailsSection.insertNote("PoznamkaNew");
    browser.applicationDetailsSection.selectCashPaymentMethod();
    browser.applicationDetailsSection.clickAcceptTermsCheckbox();
    browser.waitFor(5);
    browser.applicationDetailsSection. clickCreateApplicationButton();
    browser.headerMenu.goToApplicationsSection();
    browser.applicationSection.search(randomName);
    browser.applicationSection.openFirstApplicationDetailsPage();
    asserter.applicationDetailAction.checkFirstName(randomName);
    asserter.applicationDetailAction.checkLastName(randomLastName);
    browser.loginSection.logout();
    browser.loginSection.clickLoginMenuLink();
    browser.loginSection.insertEmail("alisa.kapitanovaplt@gmail.com");
    browser.loginSection.insertPassword("Qaz123edc");
    browser.loginSection.clickLoginButton();
    browser.headerMenu.goToApplicationsSection();
    browser.applicationSection.search(randomName);
    asserter.applicationSection.checkApplicationsTableIsEmpty();
}

@Test
    void AT_07_passwordChangeFunctionIsAvailable() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("alisa_kapitanova@ukr.net");
        browser.loginSection.insertPassword(password1);
        browser.loginSection.clickLoginButton();
        browser.profileSection.goToProfilePage();
        browser.profileSection.insertPassword(password2);
        browser.profileSection.insertPasswordVerification(password2);
        browser.profileSection.clickChangeButton();
    }
}
