package properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTest {
    @Test
    @Tag("prop")
    void systemPropertiesTest(){
        String browser = System.getProperty("browser", "mozilla");
        System.out.println(browser);


    }
}
