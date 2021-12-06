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

package com.lamergameryt.lamercaptcha.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import com.lamergameryt.lamercaptcha.audio.Sample;

public class FileUtil {
    /**
     * Get a file resource and return it as an InputStream. Intended primarily
     * to read in binary files which are contained in a jar.
     *
     * @return An @{link InputStream} to the file
     */
    public static InputStream readResource(String filename) {
        InputStream jarIs = FileUtil.class.getResourceAsStream(filename);
        if (jarIs == null) {
            throw new RuntimeException(new FileNotFoundException("File '" + filename + "' not found."));
        }

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        byte[] data = new byte[16384];
        int nRead;

        try {
            while ((nRead = jarIs.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            jarIs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(buffer.toByteArray());
    }

    public static Sample readSample(String filename) {
        InputStream is = readResource(filename);
        return new Sample(is);
    }
}
