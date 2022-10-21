package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    // 요구사항1
    @Test
    void split() {
        String[] values = "1,2".split(",");
        assertThat(values).containsExactly("1", "2");
        String[] value = "1".split(",");
        assertThat(value).contains("1");
    }

    // 요구사항2
    @Test
    void substring() {
        String value = "(1,2)";
        String valueSubstr = value.substring(1, value.length()-1);
        assertThat(valueSubstr).isEqualTo("1,2");
    }

    // 요구사항3
    @Test
    @DisplayName("charAt 테스트")
    void charAt() {
        assertThat("abc".charAt(1)).isEqualTo('b');

        assertThatThrownBy(() -> "abc".charAt(3))
                .isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageMatching("String index out of range: 3");

        assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
                .isThrownBy(() -> "abc".charAt(3))
                .withMessageMatching("String index out of range: \\d+");
    }
}
