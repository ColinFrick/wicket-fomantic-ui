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
package li.beryllium.wicket.fomantic.resource;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * FomanticUICSSResourceReference
 * <p>
 * Resource Reference to the complete Fomantic CSS library
 */
public class FomanticUICSSResourceReference extends CssResourceReference {

    private static final long serialVersionUID = 1L;

    private static final String FOMANTIC_DIST_SEMANTIC_CSS = "fomantic/dist/semantic.css";

    private static final FomanticUICSSResourceReference INSTANCE = new FomanticUICSSResourceReference();

    /**
     * Provides the CSS resource reference for Fomantic UI
     *
     * @return CssResourceReference
     */
    public static FomanticUICSSResourceReference get() {
        return INSTANCE;
    }

    /**
     * Constructor
     */
    private FomanticUICSSResourceReference() {
        super(FomanticUICSSResourceReference.class, FOMANTIC_DIST_SEMANTIC_CSS);
    }
}