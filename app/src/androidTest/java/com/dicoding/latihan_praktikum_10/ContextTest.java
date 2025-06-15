package com.dicoding.latihan_praktikum_10;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContextTest {
    @Test
    public void testPackageName() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.dicoding.latihan_praktikum_10", context.getPackageName());
    }
}