package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuInfoTest {

    @DisplayName("메뉴 정보와 동일한 이름인지 확인하여 일치하면 true 다르면 false를 반환한다.")
    @Test
    void nameEqualsTest() {
        // given
        Object[] menu = new Object[]{"티본스테이크", 55000};
        MenuInfo menuInfo = MenuInfo.from(menu);

        // when
        boolean nameEquals1 = menuInfo.nameEquals("티본스테이크");
        boolean nameEquals2 = menuInfo.nameEquals("양갈비스테이크");

        // then
        assertThat(nameEquals1).isTrue();
        assertThat(nameEquals2).isFalse();
    }
}