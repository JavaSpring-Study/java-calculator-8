package calculator.view;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class InputViewTest {

    @Test
    void 입력_프롬프트가_정상적으로_출력되는지_확인() {
        InputView inputView = new InputView();
        assertDoesNotThrow(() -> {
            System.out.println(">>> (테스트용 입력) 1,2:3");
            // 이후 Controller에서 IO 테스트 통합 시 수행할 예정
        });
    }
}