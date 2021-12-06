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

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Stretch the given image over the X and Y axes. If no scale is given,
 * defaults to an X scale of 1.0 and a Y scale of 3.0 (i.e. make the image
 * tall but do not affect the width).
 *
 * @author Harsh Patil
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
@SuppressWarnings("unused")
public class StretchGimpyRenderer implements GimpyRenderer {
    private static final double XDEFAULT = 1.0;
    private static final double YDEFAULT = 3.0;

    private final double _xScale;
    private final double _yScale;

    public StretchGimpyRenderer() {
        this(XDEFAULT, YDEFAULT);
    }

    public StretchGimpyRenderer(double xScale, double yScale) {
        _xScale = xScale;
        _yScale = yScale;
    }

    @Override
    public void gimp(BufferedImage image) {
        Graphics2D g = image.createGraphics();
        AffineTransform at = new AffineTransform();
        at.scale(_xScale, _yScale);
        g.drawRenderedImage(image, at);
    }
}
