import org.junit.jupiter.api.Test;
import announcer.Announcer;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncerTest {

    @Test
    void newGame() {
        assertDoesNotThrow(Announcer::newGame);
    }

    @Test
    void endGame() {
        assertDoesNotThrow(Announcer::endGame);
    }
}
