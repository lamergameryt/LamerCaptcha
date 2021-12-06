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
 * TextProducer implementation that will return a series of numbers.
 *
 * @author Harsh Patil
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
public class NumbersAnswerProducer implements TextProducer {
    private static final int DEFAULT_LENGTH = 5;
    private static final char[] NUMBERS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    private final TextProducer _txtProd;

    public NumbersAnswerProducer() {
        this(DEFAULT_LENGTH);
    }

    public NumbersAnswerProducer(int length) {
        _txtProd = new DefaultTextProducer(length, NUMBERS);
    }

    @Override
    public String getText() {
        return _txtProd.getText();
    }
}
