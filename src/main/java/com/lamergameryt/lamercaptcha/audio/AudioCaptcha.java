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

package com.lamergameryt.lamercaptcha.audio;

import com.lamergameryt.lamercaptcha.audio.noise.NoiseProducer;
import com.lamergameryt.lamercaptcha.text.producer.NumbersAnswerProducer;
import com.lamergameryt.lamercaptcha.text.producer.TextProducer;
import com.lamergameryt.lamercaptcha.audio.noise.RandomNoiseProducer;
import com.lamergameryt.lamercaptcha.audio.producer.RandomNumberVoiceProducer;
import com.lamergameryt.lamercaptcha.audio.producer.VoiceProducer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A builder for generating a CAPTCHA audio/answer pair.
 *
 * <p>
 * Example for generating a new CAPTCHA:
 * </p>
 *
 * <pre>
 * AudioCaptcha ac = new AudioCaptcha.Builder()
 *   .addAnswer()
 *   .addNoise()
 *   .build();
 * </pre>
 * <p>
 * Note that the <code>build()</code> method must always be called last. Other
 * methods are optional.
 * </p>
 *
 * @author Harsh Patil
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
@SuppressWarnings("unused")
public final class AudioCaptcha {
    public static final String NAME = "audioCaptcha";
    private static final Random RAND = new SecureRandom();

    private final Builder _builder;

    private AudioCaptcha(Builder builder) {
        _builder = builder;
    }

    public boolean isCorrect(String answer) {
        return answer.equals(_builder._answer);
    }

    public String getAnswer() {
        return _builder._answer;
    }

    public Sample getChallenge() {
        return _builder._challenge;
    }

    @Override
    public String toString() {
        return _builder.toString();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static class Builder {
        private final List<VoiceProducer> _voiceProds;
        private final List<NoiseProducer> _noiseProds;
        private String _answer = "";
        private Sample _challenge;

        public Builder() {
            _voiceProds = new ArrayList<>();
            _noiseProds = new ArrayList<>();
        }

        public Builder addAnswer() {
            return addAnswer(new NumbersAnswerProducer());
        }

        public Builder addAnswer(TextProducer ansProd) {
            _answer += ansProd.getText();

            return this;
        }

        public Builder addVoice() {
            _voiceProds.add(new RandomNumberVoiceProducer());

            return this;
        }

        public Builder addVoice(VoiceProducer vProd) {
            _voiceProds.add(vProd);

            return this;
        }

        public Builder addNoise() {
            return addNoise(new RandomNoiseProducer());
        }

        public Builder addNoise(NoiseProducer noiseProd) {
            _noiseProds.add(noiseProd);

            return this;
        }

        public AudioCaptcha build() {
            // Make sure we have at least one voiceProducer
            if (_voiceProds.size() == 0) {
                addVoice();
            }

            // Convert answer to an array
            char[] ansAry = _answer.toCharArray();

            // Make a List of Samples for each character
            VoiceProducer vProd;
            List<Sample> samples = new ArrayList<>();
            Sample sample;
            for (char c : ansAry) {
                // Create Sample for this character from one of the
                // VoiceProducers
                vProd = _voiceProds.get(RAND.nextInt(_voiceProds.size()));
                sample = vProd.getVocalization(c);
                samples.add(sample);
            }

            // 3. Add noise, if any, and return the result
            if (_noiseProds.size() > 0) {
                NoiseProducer nProd = _noiseProds.get(RAND.nextInt(_noiseProds.size()));
                _challenge = nProd.addNoise(samples);

                return new AudioCaptcha(this);
            }

            _challenge = Mixer.append(samples);

            return new AudioCaptcha(this);
        }

        @Override
        public String toString() {
            return "[Answer: " +
                    _answer +
                    "]";
        }
    }
}
