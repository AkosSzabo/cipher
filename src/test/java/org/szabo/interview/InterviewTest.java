package org.szabo.interview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InterviewTest
{

    @Test
    public void shouldEncodeText()
    {
            assertEquals("WECRL TEERD SOEEF EAOCA IVDEN", Interview.encode("WE ARE DISCOVERED. FLEE AT ONCE",3));
    }

    @Test
    public void shouldDecodeText()
    {
        assertEquals("WEAREDISCOVEREDFLEEATONCE", Interview.decode("WECRL TEERD SOEEF EAOCA IVDEN",3));
    }
}
