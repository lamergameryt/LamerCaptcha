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

package com.lamergameryt.lamercaptcha.gimpy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Random;

@SuppressWarnings("unused")
public class ShearGimpyRenderer implements GimpyRenderer {
    private static final Random RAND = new SecureRandom();
    private final Color _color;

    public ShearGimpyRenderer() {
        this(Color.GRAY);
    }

    public ShearGimpyRenderer(Color color) {
        _color = color;
    }

    @Override
    public void gimp(BufferedImage bi) {
        Graphics2D g = bi.createGraphics();
        shearX(g, bi.getWidth(), bi.getHeight());
        shearY(g, bi.getWidth(), bi.getHeight());
        g.dispose();
    }

    private void shearX(Graphics2D g, int w1, int h1) {
        int period = RAND.nextInt(10) + 5;

        int frames = 15;
        int phase = RAND.nextInt(5) + 2;

        for (int i = 0; i < h1; i++) {
            double d = (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * phase) / frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            g.setColor(_color);
            g.drawLine((int) d, i, 0, i);
            g.drawLine((int) d + w1, i, w1, i);
        }
    }

    private void shearY(Graphics2D g, int w1, int h1) {
        int period = RAND.nextInt(30) + 10; // 50;

        int frames = 15;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (period >> 1) * Math.sin((float) i / period + (6.2831853071795862D * phase) / frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            g.setColor(_color);
            g.drawLine(i, (int) d, i, 0);
            g.drawLine(i, (int) d + h1, i, h1);
        }
    }
}
