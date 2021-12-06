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

package com.lamergameryt.lamercaptchatest;

import com.lamergameryt.lamercaptcha.audio.AudioCaptcha;
import de.sciss.jump3r.Main;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Generates a six character audio captcha as a WAV file.
 * LAME and Jump3r are required to convert the WAV file to an MP3 file.
 *
 * @author Harsh Patil
 */
public class AudioTest {
    public static void main(String[] args) throws IOException {
        AudioCaptcha captcha = new AudioCaptcha.Builder()
                .addNoise()
                .addAnswer(() -> Integer.toString((int) (Math.random() * 999999) + 1))
                .addVoice()
                .build();

        File file = File.createTempFile("captcha_" + System.currentTimeMillis(), ".wav");
        File outFile = new File("captcha_" + System.currentTimeMillis() + ".mp3");

        AudioSystem.write(captcha.getChallenge().getAudioInputStream(), AudioFileFormat.Type.WAVE, file);

        // Convert temporary WAVE file to MP3 using LAME and Jump3r.
        PrintStream out = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                // Set the output stream to an empty one.
            }
        }));

        String[] mp3Args = {"--preset", "standard",
                "-q", "0",
                "-m", "s",
                file.getAbsolutePath(),
                outFile.getAbsolutePath()
        };
        (new Main()).run(mp3Args);
        System.setOut(out);
        // Conversion End

        //noinspection ResultOfMethodCallIgnored
        file.delete();
        System.out.println("Captcha text: " + captcha.getAnswer());
    }
}
