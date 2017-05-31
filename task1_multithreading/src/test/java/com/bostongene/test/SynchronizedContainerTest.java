package com.bostongene.test;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.bostongene.SynchronizedContainer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Andry on 30.05.17.
 *
 * Test Synchronized Container
 */

@RunWith(ConcurrentTestRunner.class)
public class SynchronizedContainerTest {

    private SynchronizedContainer sc;

    private final static int THREAD_COUNT = 4;

    @Before
    public void init() {
        try {
            sc = new SynchronizedContainer();
        }catch (Exception e) {
            assertNotNull(null);
        }
    }

    @Test
    @ThreadCount(THREAD_COUNT)
    public void testSynchronizedContainer() {
        sc.setEnglishNumber("one");
        sc.setEnglishNumber("two");
        sc.setEnglishNumber("three hundred");
        sc.setEnglishNumber("nine thousand");
        sc.setEnglishNumber("eighteen");
    }


}
