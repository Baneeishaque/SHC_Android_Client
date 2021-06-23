package ndk.ccetv_group8.shc;

import org.junit.Test;

import ndk.ccetv_group8.shc.to_utils.StringUtils;

import static org.junit.Assert.assertEquals;

public class RemoveFirstAndLastCharactersUnitTest {

    @Test
    public void removeFirstAndLastCharacters_isCorrect() {
        assertEquals("a\"bc", StringUtils.removeFirstAndLastCharacters("\"a\"bc\""));
    }
}
