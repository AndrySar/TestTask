package com.bostongene.test;

import com.bostongene.YandexTranslate;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Andry on 30.05.17.
 */
public class YandexTranslateTest {

    private YandexTranslate yat;

    @Before
    public void init() {
        try {
            yat = new YandexTranslate();
        }catch (Exception e) {
            assertNotNull(null);
        }
    }

    @Test
    public void translateText() {
        try {
            assertEquals("средне-коричневый", yat.translate("the mid-brown"));
            assertEquals("Новые Тома публикуются от имени Общества Оксфордского университета",
                    yat.translate("New volumes are published on behalf of the Society by the Oxford University Press"));

            assertEquals("Translation error", yat.translate(""));

            assertEquals("Люди считают, что есть только 54 из них осталось в дикой природе",
                    yat.translate("People believe that there are only 54 of them left in the wild"));
            assertEquals("сложные слова", yat.translate("difficult words"));
            assertEquals("национальный парк", yat.translate("a national park"));
            assertEquals("модифицированное представление", yat.translate("a modified representation"));

        }catch (IOException e) {
            assertNull(null);
        }
    }
}
