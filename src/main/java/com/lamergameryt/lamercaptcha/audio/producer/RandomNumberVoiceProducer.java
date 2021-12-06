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

package com.lamergameryt.lamercaptcha.audio.producer;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.lamergameryt.lamercaptcha.util.FileUtil;
import com.lamergameryt.lamercaptcha.audio.Sample;

/**
 * <p>
 * {@link VoiceProducer} which generates a vocalization for a given number,
 * randomly selecting from a list of voices. The default voices are located in
 * the jar in the <code>sounds/en/numbers</code> directory, and have filenames
 * with a format of <i>num</i>-<i>voice</i>.wav, e.g.:
 * <code>sounds/en/numbers/1-alex.wav</code>.
 * </p>
 *
 * @author Harsh Patil
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
@SuppressWarnings("unused")
public class RandomNumberVoiceProducer implements VoiceProducer {
    private static final Random RAND = new SecureRandom();
    private static final String[] DEFAULT_VOICES = {"alex", "bruce", "fred", "ralph", "kathy", "vicki", "victoria"};
    private static final Map<Integer, String[]> DEFAULT_VOICES_MAP;

    static {
        DEFAULT_VOICES_MAP = new HashMap<>();
        String[] files_for_num;
        StringBuilder sb;

        for (int i = 0; i < 10; i++) {
            files_for_num = new String[DEFAULT_VOICES.length];
            for (int j = 0; j < files_for_num.length; j++) {
                sb = new StringBuilder("/sounds/en/numbers/");
                sb.append(i);
                sb.append("-");
                sb.append(DEFAULT_VOICES[j]);
                sb.append(".wav");
                files_for_num[j] = sb.toString();
            }
            DEFAULT_VOICES_MAP.put(i, files_for_num);
        }
    }

    private final Map<Integer, String[]> _voices;

    public RandomNumberVoiceProducer() {
        this(DEFAULT_VOICES_MAP);
    }

    /**
     * Creates a <code>RandomNumberVoiceProducer</code> for the given
     * <code>voices</code>, a map of numbers to their corresponding filenames.
     * Conceptually the map must look like the following:
     *
     * <pre>
     * {1 => ["/my_sounds/1-quiet.wav", "/my_sounds/1-loud.wav"],
     *  2 => ["/my_sounds/2-quiet.wav", "/my_sounds/2-loud.wav"]}
     * </pre>
     */
    public RandomNumberVoiceProducer(Map<Integer, String[]> voices) {
        _voices = voices;
    }

    public Map<Integer, String[]> getVoices() {
        return Collections.unmodifiableMap(_voices);
    }

    @Override
    public final Sample getVocalization(char num) {
        try {
            Integer.parseInt(num + "");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected <num> to be a number, got '" + num + "' instead.", e);
        }

        int idx = Integer.parseInt(num + "");
        String[] files = _voices.get(idx);
        String filename = files[RAND.nextInt(files.length)];

        return FileUtil.readSample(filename);
    }
}
