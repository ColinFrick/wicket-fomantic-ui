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

import li.beryllium.wicket.fomantic.FomanticSettings;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * FomanticHeaderContributor
 * <p>
 * Contributes Fomantic CSS / JS files to the response
 */
public class FomanticHeaderContributor implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final FomanticHeaderContributor INSTANCE = new FomanticHeaderContributor();

    private static final Map<String, ResourceReference> referenceMap = new HashMap<>();

    /**
     * Provides the Fomantic UI header contributor
     *
     * @return FomanticHeaderContributor
     */
    public static FomanticHeaderContributor get() {
        return INSTANCE;
    }

    /**
     * Contribute CSS and / or JS for a Fomantic UI component
     *
     * @param response      Header Response
     * @param componentId   Id of the component
     * @param contributeJs  If the js file should be contributed
     * @param contributeCSS If the css file should be contributed
     */
    public FomanticHeaderContributor contributeComponent(@Nonnull IHeaderResponse response, @Nonnull String componentId, boolean contributeJs, boolean contributeCSS) {
        if (FomanticSettings.get().isLoadCompleteFramework()) {
            response.render(JavaScriptHeaderItem.forReference(FomanticUIJSResourceReference.get()));
            response.render(CssHeaderItem.forReference(FomanticUICSSResourceReference.get()));
        } else {
            String relPath = "fomantic/dist/components/" + componentId.toLowerCase();

            if (contributeJs) {
                response.render(JavaScriptHeaderItem.forReference(referenceMap.computeIfAbsent(componentId + "#JS",
                        key -> new JQueryPluginResourceReference(FomanticUIJSResourceReference.class, relPath + ".js"))));
            }
            if (contributeCSS) {
                response.render(CssHeaderItem.forReference(referenceMap.computeIfAbsent(componentId + "#CSS",
                        key -> new CssResourceReference(FomanticUICSSResourceReference.class, relPath + ".css"))));
            }
        }
        return this;
    }
}
