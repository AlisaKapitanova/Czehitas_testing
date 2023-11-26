package cz.czechitas.automation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.apache.commons.lang3.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;

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
    final String user2Email = "alisa_kapitanova@ukr.net";
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
        browser.applicationDetailsSection.clickCreateApplicationButton();
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

// Lekce 2
    @ParameterizedTest()
    @ValueSource(strings = {"000t54dg76"})
    @DisplayName("should create new application and fill number of children")
    void AT_Navigace_should_navigate_ProučiteléObjednávka_filLICOandPocetDeti (String icoValue) {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(icoValue);
        browser.orderSection.selectSchoolInNatureOption();
        browser.orderSection.insertChildrenCount(14);
    }

    @Test
    @DisplayName("should verifies columns named")
    void AT_01_Assertace_Prihlashky_should_contain_sloupce_Jmeno_Kategorie(){
        loginAsUser(user1Email, password1);

        browser.headerMenu.goToApplicationsSection();
        asserter.applicationSection.checkColumnExists("Jméno");
        asserter.applicationSection.checkColumnExists("Kategorie");
}

    @Test
    @DisplayName("should create new application")
    void AT_KomplexnejsiTesty_should_create_new_application() {

    loginAsUser(user1Email, password1);

    browser.applicationSection.clickCreateNewApplicationButton();
    browser.applicationSection.selectProgrammingSection();
    browser.applicationSection.clickCreateApplicationButton();
    browser.applicationDetailsSection.selectTerm(termPyton);
    browser.applicationDetailsSection.insertStudentFirstName("Jan");
    browser.applicationDetailsSection.insertStudentLastName(StudentLastName);
    browser.applicationDetailsSection.insertBirthdate("01.01.2000");
    browser.applicationDetailsSection.insertNote("Poznamka");
    browser.applicationDetailsSection.selectCashPaymentMethod();
    browser.applicationDetailsSection.clickAcceptTermsCheckbox();
    browser.waitFor(5);
    browser.applicationDetailsSection. clickCreateApplicationButton();
    browser.headerMenu.goToApplicationsSection();
    asserter.applicationDetailAction.checkTerm(termPyton);
    asserter.applicationDetailAction.checkFirstName("Jan");
    asserter.applicationDetailAction.checkLastName(StudentLastName);
    asserter.applicationDetailAction.checkDateOfBirth("01.01.2000");
    asserter.applicationDetailAction.checkNote("Poznamka");
}

@Test
@DisplayName("should edit pay metod")
    void AT_03_edit_application_pay_metod(){
    loginAsUser(user1Email, password1);

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
    @DisplayName("Checking visibility of requests between users")
    void AT_06_applicationsOfOneUserAreNotVisibleToOtherUsers() {
    browser.loginSection.clickLoginMenuLink();
    browser.loginSection.insertEmail(user2Email);
    browser.loginSection.insertPassword(password1);
    browser.loginSection.clickLoginButton();

    browser.applicationSection.clickCreateNewApplicationButton();
    browser.applicationSection.selectProgrammingSection();
    browser.applicationSection.clickCreateApplicationButton();
    browser.applicationDetailsSection.selectTerm(termPyton);
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
    browser.loginSection.insertEmail(user1Email);
    browser.loginSection.insertPassword(password1);
    browser.loginSection.clickLoginButton();
    browser.headerMenu.goToApplicationsSection();
    browser.applicationSection.search(randomName);
    asserter.applicationSection.checkApplicationsTableIsEmpty();
}

    @Test
    @DisplayName("should edit password of user")
    void AT_07_passwordChangeFunctionIsAvailable() {

        loginAsUser(user2Email, password1);
        changePassword(password2);
        browser.headerMenu.goToHomePage();
        browser.loginSection.logout();

        loginAsUser(user2Email, password2);
        changePassword(password1);
        browser.headerMenu.goToHomePage();
        browser.loginSection.logout();

        loginAsUser(user2Email, password1);
    }

    private void loginAsUser(String username, String password) {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(username);
        browser.loginSection.insertPassword(password);
        browser.loginSection.clickLoginButton();
        asserter.checkIsLoggedIn();
    }

    private void changePassword(String newPassword) {
        browser.profileSection.goToProfilePage();
        browser.profileSection.insertPassword(newPassword);
        browser.profileSection.insertPasswordVerification(newPassword);
        browser.profileSection.clickChangeButton();
    }

    //HomeWork


    @Test
    @DisplayName("should create new application, be able to changes the payment method")
    void AT_e2eTest() {
        loginAsUser(user1Email, password1);

        browser.applicationSection.search(randomLastName);
        asserter.applicationSection.checkSearchResultIsNotFound();
        asserter.applicationSection.checkApplicationsTableIsEmpty();

        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreateApplicationButton();
        browser.applicationDetailsSection.selectTerm(termPyton);
        browser.applicationDetailsSection.insertStudentFirstName(randomName);
        browser.applicationDetailsSection.insertStudentLastName(randomLastName);
        browser.applicationDetailsSection.insertBirthdate("01.12.2000");
        browser.applicationDetailsSection.insertNote("PoznamkaNew");
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection. clickCreateApplicationButton();

        browser.headerMenu.goToApplicationsSection();
        browser.applicationSection.search(randomLastName);
        asserter.applicationSection.checkSearchResult(randomLastName);

        browser.headerMenu.goToApplicationsSection();
        browser.applicationSection.search(randomLastName);
        browser.applicationSection.clickEditFirstApplicationButton();
        browser.applicationDetailsSection.selectBankTransferPaymentMethod();
        browser.applicationDetailsSection.clickEditApplicationButton();

        browser.applicationSection.search(randomLastName);
        browser.applicationSection.openFirstApplicationDetailsPage();
        asserter.applicationDetailAction.checkPaymentMethod("Bankovní převod");

    }
}
