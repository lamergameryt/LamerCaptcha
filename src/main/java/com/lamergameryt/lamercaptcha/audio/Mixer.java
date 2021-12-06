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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.sound.sampled.AudioInputStream;

/**
 * Helper class for operating on audio {@link Sample}s.
 *
 * @author Harsh Patil
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
public class Mixer {
    public static Sample append(List<Sample> samples) {
        if (samples.size() == 0) {
            return buildSample(0, new double[0]);
        }

        int sampleCount = 0;

        // append voices to each other
        double[] first = samples.get(0).getInterleavedSamples();
        sampleCount += samples.get(0).getSampleCount();

        double[][] samples_ary = new double[samples.size() - 1][];
        for (int i = 0; i < samples_ary.length; i++) {
            samples_ary[i] = samples.get(i + 1).getInterleavedSamples();
            sampleCount += samples.get(i + 1).getSampleCount();
        }

        double[] appended = concatAll(first, samples_ary);

        return buildSample(sampleCount, appended);
    }

    public static Sample mix(Sample sample1, double volAdj1, Sample sample2, double volAdj2) {
        double[] s1_ary = sample1.getInterleavedSamples();
        double[] s2_ary = sample2.getInterleavedSamples();

        double[] mixed = mix(s1_ary, volAdj1, s2_ary, volAdj2);

        return buildSample(sample1.getSampleCount(), mixed);
    }

    private static double[] concatAll(double[] first, double[]... rest) {
        int totalLength = first.length;
        for (double[] array : rest) {
            totalLength += array.length;
        }
        double[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (double[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    private static double[] mix(double[] sample1, double volAdj1, double[] sample2, double volAdj2) {
        for (int i = 0; i < sample1.length; i++) {
            if (i >= sample2.length) {
                sample1[i] = 0;
                break;
            }
            sample1[i] = (sample1[i] * volAdj1) + (sample2[i] * volAdj2);
        }
        return sample1;
    }

    private static AudioInputStream buildStream(long sampleCount, double[] sample) {
        byte[] buffer = Sample.asByteArray(sampleCount, sample);
        InputStream bais = new ByteArrayInputStream(buffer);
        return new AudioInputStream(bais, Sample.SC_AUDIO_FORMAT, sampleCount);
    }

    private static Sample buildSample(long sampleCount, double[] sample) {
        AudioInputStream ais = buildStream(sampleCount, sample);
        return new Sample(ais);
    }
}
