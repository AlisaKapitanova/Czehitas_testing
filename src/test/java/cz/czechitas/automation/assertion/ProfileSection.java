package cz.czechitas.automation.assertion;

import cz.czechitas.automation.ElementFinder;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Application specific assertions
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@ParametersAreNonnullByDefault
public final class ProfileSection {

    private final ElementFinder elementFinder;

    ProfilePageAssertion(ElementFinder elementFinder) {
        this.elementFinder = Objects.requireNonNull(elementFinder);
    }

    ApplicationAssertion

    public void checkPasswordSuccessfullyChanged() {
        var popUpWindow = elementFinder.findByCssSelector("#toast-container");
        assertThat(popUpWindow.getText()).isEqualTo("Údaje byly úspěšně uloženy");
    }
}
