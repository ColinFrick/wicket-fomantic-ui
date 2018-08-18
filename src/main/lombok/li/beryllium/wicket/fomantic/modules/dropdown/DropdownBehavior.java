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
package li.beryllium.wicket.fomantic.modules.dropdown;

import com.github.openjson.JSONObject;
import li.beryllium.wicket.fomantic.resource.FomanticHeaderContributor;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.lang.Args;

/**
 * DropdownBehavior
 * <p>
 * A dropdown allows a user to select a value from a series of options
 * <p>
 * The behavior can be attached to a {@link org.apache.wicket.extensions.markup.html.form.select.Select}
 *
 * @see <a href="https://fomantic-ui.com/elements/dropdown.html#inline">Fomantic UI Dropdown</a>
 */
public class DropdownBehavior extends Behavior {

    private FormComponent<?> formComponent;

    private DropdownOptions dropdownOptions;

    /**
     * Constructor with default dropdown options
     */
    public DropdownBehavior() {
        this(DropdownOptions.builder().build());
    }

    /**
     * Constructor
     *
     * @param dropdownOptions Options for the dropdown behavior
     */
    public DropdownBehavior(DropdownOptions dropdownOptions) {
        this.dropdownOptions = dropdownOptions;
    }

    @Override
    public void bind(Component component) {
        Args.notNull(component, "component");

        if (!(component instanceof Select)) {
            throw new WicketRuntimeException("Behavior " + getClass().getName()
                    + " can only be added to an instance of a Select");
        }

        if (formComponent != null) {
            throw new IllegalStateException("this kind of handler cannot be attached to " +
                    "multiple components; it is already attached to component " + formComponent +
                    ", but component " + component + " wants to be attached too");
        }

        this.formComponent = (FormComponent<?>) component;

        formComponent
                .setRenderBodyOnly(false)
                .setOutputMarkupId(true);
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        FomanticHeaderContributor.get()
                .contributeComponent(response, "dropdown", false, true);

        JSONObject optionsObject = new JSONObject();
        // TODO Add all dropdown options
        response.render(OnDomReadyHeaderItem.forScript("$(\"#" + component.getMarkupId() + "\").dropdown(" + optionsObject.toString() + ")"));
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        dropdownOptions.detach();
    }
}
