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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

@SuppressWarnings("unused")
public class ImageUtil {
    public static void applyFilter(BufferedImage img, BufferedImageOp filter) {
        BufferedImage destImage = filter.createCompatibleDestImage(img, img.getColorModel());
        filter.filter(img, destImage);
        Graphics2D g = img.createGraphics();
        g.drawImage(destImage, 0, 0, null, null);
        g.dispose();
    }
}
