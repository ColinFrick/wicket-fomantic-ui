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
package li.beryllium.wicket.fomantic.modules.rating;

import com.github.openjson.JSONObject;
import li.beryllium.wicket.fomantic.FomanticConstants;
import li.beryllium.wicket.fomantic.resource.FomanticHeaderContributor;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.CallbackParameter;
import org.apache.wicket.ajax.json.JSONFunction;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.IRequestParameters;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Rating
 * <p>
 * A rating indicates user interest in content
 * <p>
 * This feature is interactive by default. Set enabled to false to deactivate it
 *
 * @param <T> The type of the number
 * @see <a href="https://fomantic-ui.com/modules/rating.html">Fomantic UI Rating</a>
 */
public class Rating<T extends Number & Comparable<T>> extends FormComponent<T> {

    private static final String RATING_VALUE_PARAMETER = "value";

    private final RatingOptions options;

    private final RateBehavior rateBehavior = new RateBehavior();

    /**
     * Constructor with default options
     *
     * @param id    Wicket ID
     * @param model Model with current rating
     */
    public Rating(@Nonnull String id, @Nonnull IModel<T> model, @Nullable Class<T> type) {
        this(id, model, type, RatingOptions.builder().build());
    }

    /**
     * Constructor
     *
     * @param id      Wicket Id
     * @param model   Model with current ratin
     * @param options Rating options
     */
    public Rating(@Nonnull String id, @Nonnull IModel<T> model, @Nullable Class<T> type, @Nonnull RatingOptions options) {
        super(id, model);

        this.options = options;
        setOutputMarkupId(true);
        setType(type);

        add(rateBehavior);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        StringJoiner cssClasses = new StringJoiner(" ")
                .add(FomanticConstants.BASE_CSS_CLASS)
                .add("rating");

        if (!RatingType.DEFAULT.equals(options.getType())) {
            cssClasses.add(options.getType().name().toLowerCase());
        }

        if (options.getSize() != null) {
            cssClasses.add(options.getSize().getSizeName(true));
        }
        tag.put("class", cssClasses.toString());

        tag.put("data-rating", getRating());
        tag.put("data-max-rating", options.getMaxRating());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        FomanticHeaderContributor.get().contributeComponent(response, "rating", true, true);

        JSONObject jsonOptions = new JSONObject();
        jsonOptions.put("clearable", options.isClearable());
        jsonOptions.put("interactive", isEnabled());
        if (isEnabled()) {
            jsonOptions.put("onRate", new JSONFunction(rateBehavior.getCallbackFunction(CallbackParameter.explicit(RATING_VALUE_PARAMETER))));
        }
        response.render(OnDomReadyHeaderItem.forScript("$(\"#" + getMarkupId() + "\").rating(" + jsonOptions.toString() + ")"));
    }

    /**
     * Get rating from the internal model and round it to an integer
     *
     * @return Current rating
     */
    protected Integer getRating() {
        return Optional.ofNullable(getDefaultModelObject())
                .map(o -> o instanceof Integer ? (int) o :
                        o instanceof Number ? Math.round(((Number) o).floatValue()) : 0)
                .orElse(0);
    }

    @Override
    protected void onDetach() {
        super.onDetach();

        options.detach();
    }

    /**
     * RateBehavior
     * <p>
     * Behavior which is called when the rating was changed
     */
    private final class RateBehavior extends AbstractDefaultAjaxBehavior {
        @Override
        protected void respond(AjaxRequestTarget target) {
            IRequestParameters parameters = getRequest().getRequestParameters();

            // Convert the rating to the correct number type
            setDefaultModelObject(getConverter(getType())
                    .convertToObject(parameters.getParameterValue(RATING_VALUE_PARAMETER).toString(), Locale.ENGLISH));
        }

        @Override
        public boolean isEnabled(Component component) {
            // Only enable the behavior if the component is enabled as well
            return super.isEnabled(component) && component.isEnabled();
        }
    }

    /**
     * Create a rating component with a integer model
     *
     * @param id    Id of the wicket component
     * @param model Model with current rating
     * @return Rating component
     */
    public static Rating<Integer> ofInteger(@Nonnull String id, @Nonnull IModel<Integer> model) {
        return new Rating<>(id, model, Integer.class);
    }

    /**
     * Create a rating component with a integer model
     *
     * @param id      Id of the wicket component
     * @param model   Model with current rating
     * @param options Rating options
     * @return Rating component
     */
    public static Rating<Integer> ofInteger(@Nonnull String id, @Nonnull IModel<Integer> model, @Nonnull RatingOptions options) {
        return new Rating<>(id, model, Integer.class, options);
    }
}