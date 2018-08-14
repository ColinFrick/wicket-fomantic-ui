/*
 * The MIT License
 * Copyright (c) 2018 Colin Frick
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package li.beryllium.wicket.fomantic;

import javax.annotation.Nonnull;

/**
 * FomanticSize
 * <p>
 * Defines the different sizes for several Fomantic UI components
 * <p>
 * Attention for the size {@link #MASSIVE}: Not all components support this size variation, the size huge will be used in that case
 */
public enum FomanticSize {

    /**
     * Mini size
     */
    MINI,

    /**
     * Tiny size
     */
    TINY,

    /**
     * Small size
     */
    SMALL,

    /**
     * Medium size
     */
    MEDIUM,

    /**
     * Large size
     */
    LARGE,

    /**
     * Huge size
     */
    HUGE,

    /**
     * Massive size
     */
    MASSIVE;

    /**
     * Returns the css class to be used for the size
     *
     * @param supportsMassive indicates if the component calling the method, supports the MASSIVE size variation
     * @return CSS class string
     */
    @Nonnull
    public String getSizeName(boolean supportsMassive) {
        return (!supportsMassive && MASSIVE.equals(this) ? HUGE : this).name().toLowerCase();
    }

}
