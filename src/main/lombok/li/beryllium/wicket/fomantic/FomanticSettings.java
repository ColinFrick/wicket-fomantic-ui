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

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.WicketRuntimeException;

import java.io.Serializable;

/**
 * FomanticSettings
 * <p/>
 * Singleton for the Fomantic Settings
 */
public class FomanticSettings implements Serializable {

    private static final long serialVersionUID = 6869236788458218205L;

    private boolean loadCompleteFramework;

    private static final MetaDataKey<FomanticSettings> FOMANTIC_SETTINGS_META_DATA_KEY = new MetaDataKey<FomanticSettings>() {
        private static final long serialVersionUID = 3196964187962254018L;
    };

    /**
     * Get FomanticSettings
     *
     * @return The current thread's FomanticSettings
     */
    public static FomanticSettings get() {
        Application application = Application.get();
        if (application == null) {
            throw new WicketRuntimeException("There is no application attached to current thread " +
                    Thread.currentThread().getName());
        }

        FomanticSettings settings = application.getMetaData(FOMANTIC_SETTINGS_META_DATA_KEY);
        if (settings == null) {
            settings = new FomanticSettings();
            application.setMetaData(FOMANTIC_SETTINGS_META_DATA_KEY, settings);
        }
        return settings;
    }

    /**
     * Indicates if the complete Fomantic UI framework (css / js) is loaded instead of only the required css / js per component
     * Use this setting if many components are used
     *
     * @return
     */
    public boolean isLoadCompleteFramework() {
        return loadCompleteFramework;
    }

    /**
     * Set true if many components are used
     *
     * @param loadCompleteFramework
     */
    public void setLoadCompleteFramework(boolean loadCompleteFramework) {
        this.loadCompleteFramework = loadCompleteFramework;
    }

}
