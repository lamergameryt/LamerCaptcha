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

import java.security.SecureRandom;
import java.util.Random;

/**
 * Produces text of a given length from a given array of characters.
 *
 * @author Harsh Patil
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
@SuppressWarnings("unused")
public class DefaultTextProducer implements TextProducer {
    private static final Random RAND = new SecureRandom();
    private static final int DEFAULT_LENGTH = 5;
    private static final char[] DEFAULT_CHARS = new char[] {
        'a',
        'b',
        'c',
        'd',
        'e',
        'f',
        'g',
        'h',
        'k',
        'm',
        'n',
        'p',
        'r',
        'w',
        'x',
        'y',
        '2',
        '3',
        '4',
        '5',
        '6',
        '7',
        '8',
    };

    private final int _length;
    private final char[] _srcChars;

    public DefaultTextProducer() {
        this(DEFAULT_LENGTH, DEFAULT_CHARS);
    }

    public DefaultTextProducer(int length) {
        this(length, DEFAULT_CHARS);
    }

    public DefaultTextProducer(int length, char[] srcChars) {
        _length = length;
        _srcChars = copyOf(srcChars, srcChars.length);
    }

    @Override
    public String getText() {
        StringBuilder capText = new StringBuilder();
        for (int i = 0; i < _length; i++) {
            capText.append(_srcChars[RAND.nextInt(_srcChars.length)]);
        }

        return capText.toString();
    }

    private static char[] copyOf(char[] original, int newLength) {
        char[] copy = new char[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }
}
