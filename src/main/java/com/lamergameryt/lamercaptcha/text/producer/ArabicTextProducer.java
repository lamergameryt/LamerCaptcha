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

@SuppressWarnings("unused")
public class ArabicTextProducer implements TextProducer {
    static final int DEFAULT_LENGTH = 5;

    private static final char[] ARABIC_CHARS = {
        '\u0627',
        '\u0628',
        '\u062a',
        '\u062b',
        '\u062c',
        '\u062d',
        '\u062e',
        '\u062f',
        '\u0630',
        '\u0631',
        '\u0632',
        '\u0633',
        '\u0634',
        '\u0635',
        '\u0636',
        '\u0637',
        '\u0638',
        '\u0639',
        '\u063a',
        '\u0641',
        '\u0642',
        '\u0643',
        '\u0644',
        '\u0645',
        '\u0646',
        '\u0647',
        '\u0648',
        '\u064a',
    };

    private final TextProducer _txtProd;

    public ArabicTextProducer() {
        this(DEFAULT_LENGTH);
    }

    public ArabicTextProducer(int length) {
        _txtProd = new DefaultTextProducer(length, ARABIC_CHARS);
    }

    @Override
    public String getText() {
        return new StringBuffer(_txtProd.getText()).reverse().toString();
    }
}
