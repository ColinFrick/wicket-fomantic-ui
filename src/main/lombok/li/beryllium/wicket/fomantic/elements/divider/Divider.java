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
package li.beryllium.wicket.fomantic.elements.divider;

import li.beryllium.wicket.fomantic.FomanticConstants;
import li.beryllium.wicket.fomantic.resource.FomanticHeaderContributor;
import lombok.val;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.head.IHeaderResponse;

import javax.annotation.Nonnull;
import java.util.StringJoiner;

/**
 * Divider
 * <p>
 * A divider visually segments content into groups
 *
 * @see <a href="https://fomantic-ui.com/elements/divider.html">Fomantic UI Divider</a>
 */
public class Divider extends MarkupContainer {

    private final DividerOptions options;

    /**
     * Constructor
     *
     * @param id Wicket ID
     */
    public Divider(@Nonnull String id) {
        this(id, DividerOptions.builder().build());
    }

    /**
     * Constructor
     *
     * @param id      Wicket ID
     * @param options Divider Options
     */
    public Divider(@Nonnull String id, @Nonnull DividerOptions options) {
        super(id);
        this.options = options;
    }

    @Override
    protected final void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        val cssClasses = new StringJoiner(" ")
                .add(FomanticConstants.BASE_CSS_CLASS)
                .add("divider");

        // Add divider type class if type is not DividerType.DEFAULT
        if (!DividerType.DEFAULT.equals(options.getType())) {
            cssClasses.add(options.getType().name().toLowerCase());
        }

        // Add any variations
        if (options.isInverted()) {
            cssClasses.add("inverted");
        }
        if (options.isFitted()) {
            cssClasses.add("fitted");
        }
        if (options.isHidden()) {
            cssClasses.add("hidden");
        }
        if (options.isSection()) {
            cssClasses.add("section");
        }
        if (options.isClearing()) {
            cssClasses.add("clearing");
        }

        tag.append("class", cssClasses.toString(), " ");
    }

    @Override
    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        // If divider is of type default, we prevent rendering any content
        if (!DividerType.DEFAULT.equals(options.getType())) {
            super.onComponentTagBody(markupStream, openTag);
        }
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        FomanticHeaderContributor.get()
                .contributeComponent(response, "divider", false, true);
    }

    /**
     * Returns the options for this divider instance
     *
     * @return Divider options
     */
    @Nonnull
    public final DividerOptions getOptions() {
        return options;
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        // We detach the options, to ensure that internal option models can detach as well
        options.detach();
    }

}
