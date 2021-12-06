/*
 *  Copyright 2021 Harsh Patil (LamerGamerYT)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.lamergameryt.lamercaptcha.text.producer;

/**
 * TextProducer implementation that will return Chinese characters.
 *
 * @author Harsh Patil
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
@SuppressWarnings("unused")
public class ChineseTextProducer implements TextProducer {
    static final int DEFAULT_LENGTH = 5;
    // Here's hoping none of the characters in this range are offensive.
    static final int CODE_POINT_START = 0x4E00;
    static final int CODE_POINT_END = 0x4F6F;
    private static final int NUM_CHARS = CODE_POINT_END - CODE_POINT_START;
    private static final char[] CHARS;

    static {
        CHARS = new char[NUM_CHARS];
        for (char c = CODE_POINT_START, i = 0; c < CODE_POINT_END; c++, i++) {
            CHARS[i] = c;
        }
    }

    private final TextProducer _txtProd; // Decorator

    public ChineseTextProducer() {
        this(DEFAULT_LENGTH);
    }

    public ChineseTextProducer(int length) {
        _txtProd = new DefaultTextProducer(length, CHARS);
    }

    @Override
    public String getText() {
        return _txtProd.getText();
    }
}
